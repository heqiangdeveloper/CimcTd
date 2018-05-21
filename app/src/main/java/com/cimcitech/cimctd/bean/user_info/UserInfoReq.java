package com.cimcitech.cimctd.bean.user_info;

/**
 * Created by lyw on 2018/4/12.
 */

public class UserInfoReq {
    private int pageNum;
    private int pageSize;

    public UserInfoReq(int pageNum,int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
