package com.demo.parent.commondubboservice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * projectName demo
 * className RecordDTO
 * description TODO
 *
 * @author yzh
 * @date 2020/3/29 11:38 下午
 */
@Data
public class RecordDTO implements Serializable {

    private static final long serialVersionUID = 656573972800095359L;

    private Integer id;
    private Integer operatorId;
    private String operationTime;
    private String drugName;
    private String type;
    private Integer amount;
}
