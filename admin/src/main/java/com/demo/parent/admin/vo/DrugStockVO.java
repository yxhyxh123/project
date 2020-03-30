package com.demo.parent.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * projectName demo
 * className DrugStockVO
 * description TODO
 *
 * @author yzh
 * @date 2020/3/29 11:19 下午
 */
@Data
public class DrugStockVO implements Serializable {

    private static final long serialVersionUID = 1617058975040800221L;
    private Integer id;
    private Integer stock;
}
