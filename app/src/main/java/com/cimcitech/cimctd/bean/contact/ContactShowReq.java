package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/18.
 */

public class ContactShowReq {
    private CustomerRequestBean customerRequest;

    public ContactShowReq(CustomerRequestBean customerRequest) {
        this.customerRequest = customerRequest;
    }

    public CustomerRequestBean getCustomerRequest() {
        return customerRequest;
    }

    public void setCustomerRequest(CustomerRequestBean customerRequest) {
        this.customerRequest = customerRequest;
    }
}
