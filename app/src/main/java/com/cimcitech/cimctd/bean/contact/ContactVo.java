package com.cimcitech.cimctd.bean.contact;

import com.cimcitech.cimctd.bean.file_search.Data;

/**
 * Created by lyw on 2018/4/12.
 */

public class ContactVo {
    private int code;
    private String msg;
    private boolean success;
    private ContactData data;

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

    public ContactData getData() {
        return data;
    }

    public void setData(ContactData data) {
        this.data = data;
    }
}
