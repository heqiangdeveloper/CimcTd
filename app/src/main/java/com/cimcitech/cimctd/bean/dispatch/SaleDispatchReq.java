package com.cimcitech.cimctd.bean.dispatch;

/**
 * Created by lyw on 2018/4/23.
 */

public class SaleDispatchReq {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private saleMaintenPlanVo saleMaintenPlanVo;

    public SaleDispatchReq(int pageNum, int pageSize, String orderBy, com.cimcitech.cimctd.bean.dispatch.saleMaintenPlanVo saleMaintenPlanVo) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.saleMaintenPlanVo = saleMaintenPlanVo;
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

    public com.cimcitech.cimctd.bean.dispatch.saleMaintenPlanVo getSaleMaintenPlanVo() {
        return saleMaintenPlanVo;
    }

    public void setSaleMaintenPlanVo(com.cimcitech.cimctd.bean.dispatch.saleMaintenPlanVo saleMaintenPlanVo) {
        this.saleMaintenPlanVo = saleMaintenPlanVo;
    }
}
