package com.cimcitech.cimctd.bean.contact;

/**
 * Created by lyw on 2018/4/20.
 */

public class CustomerVo {
    /*
    *  "code": 0,
  "data": {
    "endRow": 1,
    "firstPage": 1,
    "hasNextPage": false,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": true,
    "lastPage": 1,
    "list": [
      {
        "assignTime": null,
        "bankAccount": "",
        "bankDeposit": "",
        "cityId": 9413,
        "cityName": "重庆市",
        "companySize": "",
        "countryId": 0,
        "countryName": "中国",
        "createTime": 1511769291420,
        "creater": 13464,
        "createrName": "系统管理员",
        "custAddr": "重庆机场集团有限公司",
        "custCode": "0005",
        "custId": 9,
        "custLevel": "CL01",
        "custLevelValue": "A级",
        "custName": "重庆机场集团有限公司",
        "custTel": "",
        "email": "",
        "established": 1511712000000,
        "fax": "",
        "invoice": "重庆机场集团有限公司",
        "isState": 1,
        "juridicalPerson": "",
        "marketLeader": "",
        "provinceId": 99,
        "provinceName": "重庆",
        "regionId": 106,
        "regionName": "西南地区",
        "remark": "",
        "salesName": "",
        "shortName": "",
        "superName": "重庆机场集团有限公司",
        "taxNumber": "",
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
    "pageSize": 10,
    "pages": 1,
    "prePage": 0,
    "size": 1,
    "startRow": 1,
    "total": 1
  },
  "msg": "",
  "success": true
     */

    private int code;
    private String msg;
    private boolean success;
    private CustomerData data;

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

    public CustomerData getData() {
        return data;
    }

    public void setData(CustomerData data) {
        this.data = data;
    }
}
