package com.demo.parent.admin.enums;

import org.apache.shiro.authc.AuthenticationException;

/**
 * projectName demo
 * enumName ExceptionEnum
 *
 * @author yzh
 * @date 2020/3/19 8:25 上午
 */
public enum ResponseEnum {


         /*操作*/
         SUCCESS(200,"操作成功"),
         ERROR(202,"操作失败"),
        /*用户相关异常*/
        ACCOUNT_IS_EMPTY(100,"账号参数为空"),
        ACCOUNT_IS_FORBIDDEN(101,"账号被禁用"),
        PARAMS_IS_EMPTY(102,"参数为空"),
        ACCOUNT_IS_EXIST(103,"账号信息已存在"),
        /*验证权限相关异常*/
        INCORRECT_CREDENTIALS(301,"密码不正确"),
        UNKNOWN_ACCOUNT(302,"账号不存在"),
        AUTHENTICATION_EXCEPTION(303,"状态不正常"),
        AUTHENTICATION_SUCCESS(300,"认证成功"),
        /*药品相关异常*/
        DRUG_IS_FORBIDDEN(501,"药品已被停用"),
        DRUG_IS_NOT_EXIST(502,"药品信息不存在"),
        REDUCE_OVER_DRUG_AMOUNT(503,"超出现有库存数量"),
        DRUG_IS_EXIST(504,"该药品信息已存在");

        private Integer code;
        private String msg;

        ResponseEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        ResponseEnum(String msg){
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "[返回码：" + this.code + "返回信息：" + this.msg + "]";
        }
    }
