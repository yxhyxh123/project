package com.demo.parent.admin.service;

import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.vo.DrugInfoAddVO;
import com.demo.parent.admin.vo.DrugInfoEditVO;
import com.demo.parent.admin.vo.DrugStockVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.DrugDTO;

import java.util.Map;

/**
 * projectName demo
 * interfaceName DrugService
 *
 * @author yzh
 * @date 2020/3/26 9:16 下午
 */
public interface DrugService {

    /**
     * 查询药品信息
     * @return  分页信息
     * @param pageNum
     * @param pageSize
     * @author  yzh
     * @date    2020/3/26
     */
    PageInfo<DrugDTO> queryDrugInfoByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据id查询用户信息
     * @param   id 主键
     * @return  UserEditVO 编辑窗口展示信息
     * @author  yzh
     * @date    2020/3/26
     */
    Response<DrugDTO> queryDrugInfoById(Integer id);

    /**
     * 查询柱状图显示的库存信息
     * @return  Response 统一返回
     * @author  yzh
     * @date    2020/3/27
     */
    Response<Map<String,Object>> queryBarGraphList();

    /**
     * 更新药品信息
     * @param vo
     * @return  Response
     * @author  yzh
     * @date    2020/3/26
     */
    Response updateDrug(DrugInfoEditVO vo);

    /**
     * 新增药品信息
     * @param  vo
     * @return Response
     * @author  yzh
     * @date    2020/3/29
     */
    Response addDrug(DrugInfoAddVO vo);

    /**
     * @药品入库
     * @param  vo
     * @return  Response
     * @author  yzh
     * @date    2020/3/29
     */
    Response addStock(DrugStockVO vo);


    /**
     * @药品出库
     * @param  vo
     * @return  Response
     * @author  yzh
     * @date    2020/3/29
     */
    Response reduceStock(DrugStockVO vo);
}
