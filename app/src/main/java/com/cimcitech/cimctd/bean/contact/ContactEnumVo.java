package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/12.
 */

public class ContactEnumVo {
    private int code;
    private String msg;
    private boolean success;
    private ContactEnumData data;

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

    public ContactEnumData getData() {
        return data;
    }

    public void setData(ContactEnumData data) {
        this.data = data;
    }
}
