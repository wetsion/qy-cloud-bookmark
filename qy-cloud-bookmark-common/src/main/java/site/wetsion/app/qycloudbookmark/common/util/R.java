package site.wetsion.app.qycloudbookmark.common.util;

import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.constant.ResponseConstant;

import java.io.Serializable;

/**
 * rest 接口响应封装
 *
 * Created by wetsion on 2020/3/19.
 */
@Data
public class R<T> implements Serializable{

    private int code;

    private String msg;

    private boolean success;

    private T data;

    private R(T data, int code, String msg, boolean success) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public static R ok() {
        return new R<>(null,
                ResponseConstant.CODE_SUCCESS,
                ResponseConstant.MSG_SUCCESS,
                ResponseConstant.BOOLEAN_SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(data,
                ResponseConstant.CODE_SUCCESS,
                ResponseConstant.MSG_SUCCESS,
                ResponseConstant.BOOLEAN_SUCCESS);
    }

    public static <T> R<T> ok(T data, String msg) {
        return new R<T>(data,
                ResponseConstant.CODE_SUCCESS,
                msg,
                ResponseConstant.BOOLEAN_SUCCESS);
    }

    public static R fail() {
        return new R<>(null,
                ResponseConstant.CODE_SERVER_FAIL,
                ResponseConstant.MSG_FAIL,
                ResponseConstant.BOOLEAN_FAIL);
    }

    public static <T> R<T> fail(T data) {
        return new R<T>(data,
                ResponseConstant.CODE_SUCCESS,
                ResponseConstant.MSG_SUCCESS,
                ResponseConstant.BOOLEAN_SUCCESS);
    }

    public static <T> R<T> fail(String msg) {
        return new R<T>(null ,
                ResponseConstant.CODE_SUCCESS,
                msg,
                ResponseConstant.BOOLEAN_SUCCESS);
    }

    public static <T> R<T> fail(T data, String msg) {
        return new R<T>(data,
                ResponseConstant.CODE_SERVER_FAIL,
                msg,
                ResponseConstant.BOOLEAN_FAIL);
    }

    public static <T> R<T> fail(T data, String msg, int code) {
        return new R<T>(data,
                code,
                msg,
                ResponseConstant.BOOLEAN_FAIL);
    }
}
