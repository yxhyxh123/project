package com.demo.parent.drugdubboserver.service;

import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.DrugDTO;
import com.demo.parent.commondubboservice.dto.UserDTO;

import java.util.List;

/**
 * projectName demo
 * interfaceName DrugInfoService
 *
 * @author yzh
 * @date 2020/3/26 9:37 下午
 */
public interface DrugInfoService {

    /**
     * 查询信息分页
     * @param pageNum
     * @param pageSize
     * @return PageInfo
     * @author  yzh
     * @date    2020/3/26
     */
    PageInfo<DrugDTO> queryDrugInfoByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据id查询信息
     * @param   id
     * @return  DrugDTO
     * @author  yzh
     * @date    2020/3/26
     */
    DrugDTO queryDrugInfoById(Integer id);

    /**
     * 查询药品库存
     * @return  DrugDTO
     * @author  yzh
     * @date    2020/3/27
     */
    List<DrugDTO> queryStockList();


    /**
     * 药品信息更新
     * @param drug
     * @return int
     * @author  yzh
     * @date    2020/3/26
     */
    int updateDrugInfo(DrugDTO drug);

    /**
     * 新增药品
     * @param drug
     * @return  int
     * @author  yzh
     * @date    2020/3/28
     */
    int saveDrugInfo(DrugDTO drug);


    /**
     * 验证药品是否已经存在
     * @param
     * @return  int
     * @author  yzh
     * @date    2020/3/26
     */
    Boolean isDrugExist(String drugCode,String drugName);
}
