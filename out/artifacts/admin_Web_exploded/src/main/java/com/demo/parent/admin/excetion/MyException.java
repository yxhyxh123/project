package com.demo.parent.admin.excetion;

import com.demo.parent.admin.enums.ExceptionEnum;

/**
 * projectName demo
 * className MyException

 *
 * @author yzh
 * @date 2020/3/19 8:27 上午
 */
public class MyException extends RuntimeException{

    private int code;
    private String msg;

    public MyException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.toString());
        this.code = exceptionEnum.getCode();
        this.msg  = exceptionEnum.getMsg();
    }

    public MyException(String msg){
        super(msg);
    }
}
