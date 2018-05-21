package com.cimcitech.cimctd.bean.faulthandle;

/**
 * Created by lyw on 2018/5/2.
 */

public class FaultHandleImageReq {
    private String iconUrl;

    public FaultHandleImageReq(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
