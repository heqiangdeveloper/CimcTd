package com.cimcitech.cimctd.bean;

/**
 * Created by cimcitech on 2017/7/31.
 */

public class RegisterVo {

    private int code;
    private String msg;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
