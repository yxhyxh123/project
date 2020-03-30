package com.demo.parent.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * projectName demo
 * className DrugInfoEditVO
 * description TODO
 *
 * @author yzh
 * @date 2020/3/26 11:19 下午
 */
@Data
public class DrugInfoEditVO implements Serializable {

    private static final long serialVersionUID = 4889606515785236834L;

    private Integer id;
    private String drugCode;
    private String drugName;
    private String genericName;
    private Integer drugType;
    private String spec;
    private Integer status;
    private Integer stock;
    private String area;
}
