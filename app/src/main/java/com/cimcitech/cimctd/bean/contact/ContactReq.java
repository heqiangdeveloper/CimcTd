package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/12.
 */

public class ContactReq {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private ContactBean contact;

    public ContactReq(int pageNum, int pageSize, String orderBy, ContactBean contact){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.contact = contact;
    }

    public ContactBean getContact() {
        return contact;
    }

    public void setContact(ContactBean contact) {
        this.contact = contact;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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

    public static class ContactBean {
        private String contactName;

        public ContactBean(String contactName) {
            this.contactName = contactName;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }
    }
}
