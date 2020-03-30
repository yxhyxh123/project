package com.demo.parent.drugdubboserver.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * projectName demo
 * className DrugEntity
 *
 * @author yzh
 * @date 2020/3/26 4:22 下午
 */
@Data
@Table(name ="SYSTEM.DRUG_INFO" )
public class DrugEntity implements Serializable {

    private static final long serialVersionUID = 4244083654290981204L;

    @Id
    private Integer id;
    @Column(name = "DRUG_CODE")
    private String drugCode;
    @Column(name = "DRUG_NAME")
    private String drugName;
    @Column(name = "GENERIC_NAME")
    private String genericName;
    @Column(name = "DRUG_TYPE")
    private Integer drugType;
    @Column(name = "SPEC")
    private String spec;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "STOCK")
    private Integer stock;
    @Column(name = "MODIFY_TIME")
    private Long modifyTime;
    @Column(name = "CREATE_TIME")
    private Long createTime;
    @Column(name = "AREA")
    private String area;
}
