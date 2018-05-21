package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/20.
 */

public class CustomerBean {
    private String custName;

    public CustomerBean(String custName) {
        this.custName = custName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
