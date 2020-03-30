package com.demo.parent.drugdubboserver.dao;

import com.demo.parent.drugdubboserver.entity.DrugEntity;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * projectName demo
 * interfaceName DrugMapper
 *
 * @author yzh
 * @date 2020/3/26 4:20 下午
 */
@Repository
public interface DrugMapper extends Mapper<DrugEntity> {

    /**
     * 查询分页 数据总数
     * @return  count
     * @author  yzh
     * @date    2020/3/27
     */
    @SelectProvider(type = DrugInfoSqlProvider.class,method = "queryAllCount")
    int queryAllCount();

    /**
     * 查询药品库存信息
     * @return  List
     * @author  yzh
     * @date    2020/3/27
     */
    @SelectProvider(type = DrugInfoSqlProvider.class,method = "queryStockList")
    List<DrugEntity> queryStockList();
}
