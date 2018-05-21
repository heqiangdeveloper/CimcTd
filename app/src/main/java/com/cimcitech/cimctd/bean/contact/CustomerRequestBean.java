package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/20.
 */

public class CustomerRequestBean {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private CustomerBean customer;

    public CustomerRequestBean(int pageNum, int pageSize, String orderBy, CustomerBean customer) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.customer = customer;
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

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }
}
