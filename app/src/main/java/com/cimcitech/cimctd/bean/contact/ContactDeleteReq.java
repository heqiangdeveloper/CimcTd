package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/18.
 */

public class ContactDeleteReq {
    private Long contactId;

    public ContactDeleteReq(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
