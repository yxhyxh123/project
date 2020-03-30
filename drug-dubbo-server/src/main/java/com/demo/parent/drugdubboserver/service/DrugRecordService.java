package com.demo.parent.drugdubboserver.service;

import com.demo.parent.commondubboservice.dto.DrugDTO;
import com.demo.parent.commondubboservice.dto.RecordDTO;

/**
 * projectName demo
 * interfaceName DrugRecordService
 * description TODO
 *
 * @author yzh
 * @date 2020/3/30 12:29 上午
 */
public interface DrugRecordService {

    /**
     * 新增操作记录
     * @param record
     * @return  int
     * @author  yzh
     * @date    2020/3/28
     */
    int saveRecord(RecordDTO record);
}
