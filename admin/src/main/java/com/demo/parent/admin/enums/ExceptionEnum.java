package com.demo.parent.admin.enums;

/**
 * projectName demo
 * enumName ExceptionEnum
 *
 * @author yzh
 * @date 2020/3/19 8:25 上午
 */
public enum ExceptionEnum {


    /*用户相关异常*/
        ACCOUNT_IS_EMPTY(100,"账号参数为空"),
        ACCOUNT_IS_FORBIDDEN(101,"账号被禁用");
        /*验证权限相关异常*/

        private Integer code;
        private String msg;

        ExceptionEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        ExceptionEnum(String msg){
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
            return "[异常码：" + this.code + "异常信息：" + this.msg + "]";
        }
    }
