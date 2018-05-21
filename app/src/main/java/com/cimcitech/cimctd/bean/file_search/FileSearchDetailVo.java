package com.cimcitech.cimctd.bean.file_search;

/**
 * Created by lyw on 2018/4/12.
 */

public class FileSearchDetailVo {
    private int code;
    private String msg;
    private boolean success;
    private DetailData data;

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

    public DetailData getData() {
        return data;
    }

    public void setData(DetailData data) {
        this.data = data;
    }
}
