package com.demo.parent.drugdubboserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.parent.commondubboservice.dto.RecordDTO;
import com.demo.parent.drugdubboserver.dao.RecordMapper;
import com.demo.parent.drugdubboserver.entity.DrugRecordEntity;
import com.demo.parent.drugdubboserver.service.DrugRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * projectName demo
 * className DrugRecordServiceImpl
 * description TODO
 *
 * @author yzh
 * @date 2020/3/30 12:32 上午
 */
@Service
public class DrugRecordServiceImpl implements DrugRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int saveRecord(RecordDTO record) {
        if(null == record){
            return 0;
        }
        DrugRecordEntity entity  = new DrugRecordEntity();
        BeanUtils.copyProperties(record,entity);
        return recordMapper.insertSelective(entity);
    }
}
