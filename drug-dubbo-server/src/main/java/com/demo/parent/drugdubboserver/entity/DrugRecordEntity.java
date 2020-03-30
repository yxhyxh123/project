package com.demo.parent.drugdubboserver.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * projectName demo
 * className DrugRecordEntity
 *
 * @author yzh
 * @date 2020/3/30 12:11 上午
 */
@Data
@Table(name ="SYSTEM.OPERATION_RECORD" )
public class DrugRecordEntity implements Serializable {

    private static final long serialVersionUID = -4651979576266544714L;

    @Id
    private Integer id;
    @Column(name = "OPERATOR_ID")
    private Integer operatorId;
    @Column(name = "OPERATION_TIME")
    private String operationTime;
    @Column(name = "OPERATION_TYPE")
    private String type;
    @Column(name = "OPERATION_AMOUNT")
    private Integer amount;
    @Column(name = "DRUG_NAME")
    private String drugName;

}
