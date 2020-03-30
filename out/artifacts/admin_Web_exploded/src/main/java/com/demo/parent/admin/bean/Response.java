package com.demo.parent.admin.bean;

import com.demo.parent.admin.enums.ResponseEnum;

/**
 * projectName demo
 * className Response
 * web通用json返回
 * @author yzh
 * @date 2020/3/23 9:31 上午
 */
public class Response<T> {
    private int code;

    private String msg;

    private T data;

    public Response(){
    }

    private Response(ResponseEnum responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    private Response(ResponseEnum responseCode,T data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    private Response(ResponseEnum responseCode,String msg) {
        this.code = responseCode.getCode();
        this.msg = msg;
    }

    private Response(ResponseEnum responseCode,String msg,T data) {
        this.code = responseCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    private Response(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Response success() {
        return new Response(ResponseEnum.SUCCESS);
    }
    public static Response error() {
        return new Response(ResponseEnum.ERROR);
    }
    public static Response build(ResponseEnum responseCode) {
        return new Response(responseCode);
    }
    public static<T> Response<T> build(ResponseEnum responseCode,T data) {
        return new Response(responseCode,data);
    }
    public static Response build(ResponseEnum responseCode,String msg) {
        return new Response(responseCode,msg);
    }
    public static<T> Response<T> build(ResponseEnum responseCode,String msg,T data) {
        return new Response(responseCode,msg,data);
    }
    public static<T> Response<T> build(Integer code,String msg) {
        return new Response(code,msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
