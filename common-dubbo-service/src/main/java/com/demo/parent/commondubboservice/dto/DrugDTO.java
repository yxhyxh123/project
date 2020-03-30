package com.demo.parent.commondubboservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * projectName demo
 * className DrugDTO
 *
 * @author yzh
 * @date 2020/3/26 9:18 下午
 */
@Data
public class DrugDTO implements Serializable {

    private static final long serialVersionUID = 7617861106067800411L;
    private Integer id;
    private String drugCode;
    private String drugName;
    private String genericName;
    private Integer drugType;
    private String spec;
    private Integer status;
    private Integer stock;
    private Long modifyTime;
    private Long createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    private String area;

}
