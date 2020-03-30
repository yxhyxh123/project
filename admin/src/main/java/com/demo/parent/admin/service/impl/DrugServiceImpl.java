package com.demo.parent.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.service.DrugService;
import com.demo.parent.admin.service.UserService;
import com.demo.parent.admin.vo.DrugInfoAddVO;
import com.demo.parent.admin.vo.DrugInfoEditVO;
import com.demo.parent.admin.vo.DrugStockVO;
import com.demo.parent.admin.vo.UserEditVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.DrugDTO;
import com.demo.parent.commondubboservice.dto.RecordDTO;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.commondubboservice.util.DateUtil;
import com.demo.parent.drugdubboserver.service.DrugInfoService;
import com.demo.parent.drugdubboserver.service.DrugRecordService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.demo.parent.admin.enums.ResponseEnum.*;


/**
 * projectName demo
 * className DrugServiceImpl
 *
 * @author yzh
 * @date 2020/3/26 9:20 下午
 */
@Service
public class DrugServiceImpl implements DrugService {

    private static String STOCK_IN  = "入库";
    private static String STOCK_OUT = "出库";

    @Reference
    private DrugInfoService drugInfoService;

    @Reference
    private DrugRecordService drugRecordService;

    @Autowired
    private UserService userService;

    @Override
    public PageInfo<DrugDTO> queryDrugInfoByPage(Integer pageNum, Integer pageSize) {
        PageInfo<DrugDTO> pages  = drugInfoService.queryDrugInfoByPage(pageNum,pageSize);
        return pages;
    }

    @Override
    public Response<DrugDTO> queryDrugInfoById(Integer id) {
        if (null == id){
            return Response.build(PARAMS_IS_EMPTY);
        }
        DrugDTO dto = drugInfoService.queryDrugInfoById(id);
        return Response.build(SUCCESS,dto);
    }

    @Override
    public Response<Map<String, Object>> queryBarGraphList() {
        List<DrugDTO> list = drugInfoService.queryStockList();
        //装载查询出的不同的数据
        Map<String,Object> maps = new HashMap<>();
        Integer[] counts = new Integer[list.size()];
        String[] names = new String[list.size()];
        int i=0;
        for(DrugDTO drug:list){
            counts[i] = drug.getStock();
            names[i] = drug.getGenericName();
            i++;
        }
        maps.put("counts",counts);
        maps.put("name",names);
        Response<Map<String,Object>> response = new Response<>();
        response.setData(maps);
        return response;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Response updateDrug(DrugInfoEditVO vo) {
        DrugDTO dto =  new DrugDTO();
        BeanUtils.copyProperties(vo,dto);
        drugInfoService.updateDrugInfo(dto);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Response addDrug(DrugInfoAddVO vo) {
        DrugDTO dto =  new DrugDTO();
        BeanUtils.copyProperties(vo,dto);
        dto.setArea(vo.getAreaAdd());
        dto.setGenericName(vo.getGenericNameAdd());
        dto.setSpec(vo.getSpecAdd());
        drugInfoService.saveDrugInfo(dto);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Response addStock(DrugStockVO vo) {
        DrugDTO dto = drugInfoService.queryDrugInfoById(vo.getId());
        if(dto ==  null){
            return Response.build(DRUG_IS_NOT_EXIST);
        }
        if(dto.getStatus() !=1 ){
            return Response.build(DRUG_IS_FORBIDDEN);
        }
        dto.setStock(dto.getStock() + vo.getStock() );
        if (drugInfoService.updateDrugInfo(dto) == 1){
            RecordDTO record = new RecordDTO();
            UserDTO operator = (UserDTO) SecurityUtils.getSubject().getPrincipal();
            String currentDate = DateUtil.formatDate(new Date(),"yyyy/MM/dd hh:mm:ss");
            record.setOperationTime(currentDate);
            record.setDrugName(dto.getDrugName());
            record.setOperatorId(operator.getId());
            record.setType(STOCK_IN);
            record.setAmount(vo.getStock());
            drugRecordService.saveRecord(record);
        }
        return  Response.build(SUCCESS);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Response reduceStock(DrugStockVO vo) {
        DrugDTO dto = drugInfoService.queryDrugInfoById(vo.getId());
        if(dto ==  null){
            return Response.build(DRUG_IS_NOT_EXIST);
        }
        if(dto.getStatus() !=1 ){
            return Response.build(DRUG_IS_FORBIDDEN);
        }
        if(dto.getStock() < vo.getStock()){
            return Response.build(REDUCE_OVER_DRUG_AMOUNT);
        }
        dto.setStock(dto.getStock() - vo.getStock() );
        if (drugInfoService.updateDrugInfo(dto) == 1){
            RecordDTO record = new RecordDTO();
            UserDTO operator = (UserDTO) SecurityUtils.getSubject().getPrincipal();
            String currentDate = DateUtil.formatDate(new Date(),"yyyy/MM/dd hh:mm:ss");
            record.setOperationTime(currentDate);
            record.setDrugName(dto.getDrugName());
            record.setOperatorId(operator.getId());
            record.setType(STOCK_OUT);
            record.setAmount(vo.getStock());
            drugRecordService.saveRecord(record);
        }
        return  Response.build(SUCCESS);    }
}
