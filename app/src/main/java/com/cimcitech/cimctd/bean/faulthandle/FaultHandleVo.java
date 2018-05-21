package com.cimcitech.cimctd.bean.faulthandle;

/**
 * Created by lyw on 2018/4/26.
 */

public class FaultHandleVo {
    /*
    *   "code": 0,
  "data": {
    "endRow": 3,
    "firstPage": 1,
    "hasNextPage": false,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": true,
    "lastPage": 1,
    "list": [
      {
        "approach": "临时",
        "approachIcon": null,
        "createTime": 1510715974466,
        "creater": null,
        "drive": "测试",
        "faultHandleId": 52,
        "faultType": "1",
        "iconName": null,
        "iconUrl": null,
        "partId": 13,
        "partName": "升降",
        "placeId": 12,
        "placeName": "测试",
        "productId": 5,
        "productName": "11-轮式-液压登机桥",
        "status": 0,
        "updateTime": 1512283591997,
        "updater": 13464
      },
      {
        "approach": "111",
        "approachIcon": null,
        "createTime": 1510887796911,
        "creater": null,
        "drive": "4",
        "faultHandleId": 53,
        "faultType": "3",
        "iconName": null,
        "iconUrl": null,
        "partId": 31,
        "partName": "行走",
        "placeId": 30,
        "placeName": "位置1",
        "productId": 3,
        "productName": "12-轮式-机电登机桥",
        "status": 1,
        "updateTime": 1510902168678,
        "updater": 13464
      },
      {
        "approach": "12121",
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
      }
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 1,
    "navigatePages": 8,
    "navigatepageNums": [
      1
    ],
    "nextPage": 0,
    "pageNum": 1,
    "pageSize": 3,
    "pages": 1,
    "prePage": 0,
    "size": 3,
    "startRow": 1,
    "total": 3
  },
  "msg": "",
  "success": true
     */
    private int code;
    private String msg;
    private boolean success;
    private FaultHandleData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public FaultHandleData getData() {
        return data;
    }

    public void setData(FaultHandleData data) {
        this.data = data;
    }
}
