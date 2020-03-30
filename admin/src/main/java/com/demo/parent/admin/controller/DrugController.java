package com.demo.parent.admin.controller;

import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.service.DrugService;
import com.demo.parent.admin.vo.DrugInfoAddVO;
import com.demo.parent.admin.vo.DrugInfoEditVO;
import com.demo.parent.admin.vo.DrugStockVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.DrugDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * projectName demo
 * className DrugController
 *
 * @author yzh
 * @date 2020/3/26 5:28 下午
 */
@RestController
public class DrugController {

    @Autowired
    private DrugService drugService;

    @RequestMapping(value="/queryDrugInfoByPage")
    public PageInfo<DrugDTO> queryDrugInfoByPage(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
    ){
        PageInfo<DrugDTO> pages  = drugService.queryDrugInfoByPage(pageNum,pageSize);
        return pages;
    }

    @GetMapping("/queryDrugInfoById")
    public Response<DrugDTO> queryUserById(
            @RequestParam(value = "id")Integer id
    ){
        return drugService.queryDrugInfoById(id);
    }

    @GetMapping("/queryBarGraphList")
    public Response<Map<String,Object>> queryBarGraphList(){
        return drugService.queryBarGraphList();
    }

    @PostMapping(value="/updateDrug")
    public Response updateDrug(@RequestBody DrugInfoEditVO drug){
        return drugService.updateDrug(drug);
    }

    @PostMapping("/addDrug")
    public Response addDrug(@RequestBody DrugInfoAddVO drug){
        return drugService.addDrug(drug);
    }


    @PostMapping("/increase")
    public Response addDrug(@RequestBody DrugStockVO stock){
        return drugService.addStock(stock);
    }

    @PostMapping("/reduce")
    public Response reduceDrug(@RequestBody DrugStockVO stock){
        return drugService.reduceStock(stock);
    }

}
