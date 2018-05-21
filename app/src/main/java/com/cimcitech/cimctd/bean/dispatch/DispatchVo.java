package com.cimcitech.cimctd.bean.dispatch;

/**
 * Created by lyw on 2018/4/23.
 */

public class DispatchVo {
    /*
    *   "code": 0,
  "data": {
    "endRow": 10,
    "firstPage": 1,
    "hasNextPage": true,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": false,
    "lastPage": 2,
    "list": [
      {
        "actualEndDate": null,
        "actualStartDate": null,
        "airportName": "",
        "bridgeNum": "2441-5641",
        "chargeId": null,
        "chargeName": "",
        "contractId": 7,
        "contractNo": "SS-WB20160011",
        "contractType": null,
        "createTime": 1512543150000,
        "creater": null,
        "createrName": null,
        "custId": null,
        "custName": "唐山机场有限公司",
        "endDate": null,
        "fseq": 1,
        "fstatus": null,
        "isEnd": null,
        "isReceive": null,
        "isSendJobs": null,
        "latitude": null,
        "longitude": null,
        "maintenanceType": "WT01",
        "maintenanceTypeValue": "季检",
        "otherPeople": null,
        "otherPeopleValue": "",
        "planId": 41,
        "proEndDate": null,
        "proMaintenanceDate": 1514736000000,
        "productNo": "5641",
        "recComfirmDate": null,
        "regionManagerId": null,
        "regionManagerName": "",
        "remark": null,
        "scheduleStartDate": null,
        "startDate": null,
        "updateTime": null,
        "updater": null
      },
      "navigateFirstPage": 1,
        "navigateLastPage": 2,
        "navigatePages": 8,
        "navigatepageNums": [
          1,
          2
        ],
        "nextPage": 2,
        "pageNum": 1,
        "pageSize": 10,
        "pages": 2,
        "prePage": 0,
        "size": 10,
        "startRow": 1,
        "total": 20
      },
      "msg": "",
      "success": true
     */

    private int code;
    private String msg;
    private boolean success;
    private DispatchData data;

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

    public DispatchData getData() {
        return data;
    }

    public void setData(DispatchData data) {
        this.data = data;
    }
}
