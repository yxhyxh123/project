package com.demo.parent.commondubboservice.enums;

import lombok.Data;

/**
 * projectName demo
 * enumName RoleEnum
 *
 * @author yzh
 * @date 2020/3/25 10:00 上午
 */
public enum RoleEnum {

    ADMIN(1,"admin"),USER(2,"user");

    private Integer type;
    private String name;

    private RoleEnum(Integer type,String name){
        this.type = type;
        this.name = name;
    }

    public static String getName(Integer type){
        for (RoleEnum role:RoleEnum.values()){
            if (role.type.equals(type)){
                return role.name;
            }
        }
        return null;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name =  name;
    }

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this.type = type;
    }
}
