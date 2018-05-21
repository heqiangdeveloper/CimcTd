package com.cimcitech.cimctd.bean.dispatch;

/**
 * Created by lyw on 2018/4/23.
 */

public class MainDispatchReq {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private MaintenancePlanVo maintenancePlanVo;

    public MainDispatchReq(int pageNum, int pageSize, String orderBy, MaintenancePlanVo maintenancePlanVo) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.maintenancePlanVo = maintenancePlanVo;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public MaintenancePlanVo getMaintenancePlanVo() {
        return maintenancePlanVo;
    }

    public void setMaintenancePlanVo(MaintenancePlanVo maintenancePlanVo) {
        this.maintenancePlanVo = maintenancePlanVo;
    }
}
