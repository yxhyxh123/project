package com.demo.parent.drugdubboserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.DrugDTO;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.commondubboservice.util.BeanUtil;
import com.demo.parent.commondubboservice.util.PageUtil;
import com.demo.parent.drugdubboserver.dao.DrugMapper;
import com.demo.parent.drugdubboserver.entity.DrugEntity;
import com.demo.parent.drugdubboserver.service.DrugInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * projectName demo
 * className DrugInfoServiceImpl
 * description TODO
 *
 * @author yzh
 * @date 2020/3/26 9:40 下午
 */
@Service
public class DrugInfoServiceImpl implements DrugInfoService {

    @Autowired
    private DrugMapper drugMapper;

    @Override
    public PageInfo<DrugDTO> queryDrugInfoByPage(Integer pageNum, Integer pageSize) {
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == pageSize){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<DrugEntity> drugs = drugMapper.selectAll();
        List<DrugDTO> dtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(drugs)){
            dtos = BeanUtil.copyListProperties(DrugDTO.class,drugs);
        }
        dtos.stream().forEach(p->{
            if(p.getModifyTime() != null){
                Date date = new Date(p.getModifyTime());
                p.setModifyDate(date);
            }
            if(p.getCreateTime() != null){
                Date date = new Date(p.getCreateTime());
                p.setCreateDate(date);
            }
        });
        PageInfo<DrugDTO> page = new PageInfo<DrugDTO>();
        int total = drugMapper.queryAllCount();
        PageUtil.buildListPage(pageNum,pageSize,dtos,page,total);
        return page;
    }

    @Override
    public DrugDTO queryDrugInfoById(Integer id) {
        DrugDTO dto = new DrugDTO();
        if (null == id){
            return dto;
        }
        DrugEntity en = new DrugEntity();
        en.setId(id);
        en = drugMapper.selectOne(en);
        BeanUtils.copyProperties(en,dto);
        return dto;
    }

    @Override
    public List<DrugDTO> queryStockList() {
        List<DrugEntity> entities = drugMapper.queryStockList();
       return BeanUtil.copyListProperties(DrugDTO.class,entities);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateDrugInfo(DrugDTO drug) {
        if(null ==  drug){
            return 0;
        }
        DrugEntity entity  = new DrugEntity();
        BeanUtils.copyProperties(drug,entity);
        Long modifyTime = System.currentTimeMillis();
        entity.setModifyTime(modifyTime);
        Example example= new Example(DrugEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",entity.getId());
        return drugMapper.updateByExampleSelective(entity,example);
    }

    @Override
    public int saveDrugInfo(DrugDTO drug) {
        if(null == drug){
            return 0;
        }
        DrugEntity entity  = new DrugEntity();
        BeanUtils.copyProperties(drug,entity);
        Long createTime = System.currentTimeMillis();
        entity.setCreateTime(createTime);
        return drugMapper.insertSelective(entity);
    }

    @Override
    public Boolean isDrugExist(String drugCode, String drugName) {
        DrugEntity entity = new DrugEntity();
        entity.setDrugCode(drugCode);
        Example example= new Example(DrugEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("DRUG_CODE",drugCode);
        criteria.orEqualTo("DRUG_NAME",drugName);
        DrugEntity drug = drugMapper.selectOneByExample(criteria);
        if(drug.getId() == null){
            return false;
        }
        return true;
    }
}
