package com.demo.parent.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * projectName demo
 * className DrugAddVO
 * description TODO
 *
 * @author yzh
 * @date 2020/3/29 9:07 下午
 */
@Data
public class DrugInfoAddVO implements Serializable {

    private static final long serialVersionUID = -8777961784738232562L;
    private String id;
    private String drugCode;
    private String drugName;
    private String genericNameAdd;
    private Integer drugType;
    private String specAdd;
    private Integer status;
    private Integer stock;
    private String areaAdd;
}
