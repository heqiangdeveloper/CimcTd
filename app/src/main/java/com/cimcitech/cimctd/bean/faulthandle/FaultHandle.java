package com.cimcitech.cimctd.bean.faulthandle;

import java.io.Serializable;

/**
 * Created by lyw on 2018/4/26.
 */

public class FaultHandle implements Serializable{
    /*
    *   "approach": "12121",
        "approachIcon": null,
        "createTime": 1510905095412,
        "creater": 13464,
        "drive": "2",
        "faultHandleId": 57,
        "faultType": "2",
        "iconName": null,
        "iconUrl": null,
        "partId": 15,
        "partName": "转台（含转台立柱）",
        "placeId": 0,
        "placeName": "",
        "productId": 5,
        "productName": "11-轮式-液压登机桥",
        "status": 0,
        "updateTime": null,
        "updater": null
     */

    private String approach;
    private String approachIcon;
    private Long createTime;
    private Long creater;
    private String  drive;
    private Long  faultHandleId;
    private String  faultType;
    private String iconName;
    private String  iconUrl;
    private Long  partId;
    private String  partName;
    private Long  placeId;
    private String placeName;
    private Long  productId;
    private String productName;
    private int status;
    private Long   updateTime;
    private Long   updater;

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getApproachIcon() {
        return approachIcon;
    }

    public void setApproachIcon(String approachIcon) {
        this.approachIcon = approachIcon;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public Long getFaultHandleId() {
        return faultHandleId;
    }

    public void setFaultHandleId(Long faultHandleId) {
        this.faultHandleId = faultHandleId;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }
}
