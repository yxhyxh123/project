package com.demo.parent.drugdubboserver.dao;

import com.demo.parent.drugdubboserver.entity.DrugRecordEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * projectName demo
 * interfaceName RecordMapper
 * description TODO
 *
 * @author yzh
 * @date 2020/3/30 12:31 上午
 */
@Repository
public interface RecordMapper extends Mapper<DrugRecordEntity> {


}
