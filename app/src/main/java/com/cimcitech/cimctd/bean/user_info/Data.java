package com.cimcitech.cimctd.bean.user_info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyw on 2018/4/12.
 */

public class Data {
    /*
    *"data": {
    "endRow": 10,
    "firstPage": 1,
    "hasNextPage": true,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": false,
    "lastPage": 8,
    "list": [
      {
        "address": null,
        "companyId": null,
        "createTime": null,
        "creater": null,
        "email": null,
        "empNo": "",
        "flowUserId": null,
        "isActived": null,
        "isState": 0,
        "lastLogin": null,
        "mobile": "17665206238",
        "password": null,
        "position": null,
        "positionName": "",
        "realname": null,
        "remark": null,
        "roleId": null,
        "sex": null,
        "superId": null,
        "superName": "",
        "tel": "",
        "token": null,
        "unitId": null,
        "unitName": null,
        "updateTime": null,
        "updater": null,
        "userId": 33336,
        "userName": "17665206238"
      },
      {
        "address": null,
        "companyId": null,
        "createTime": null,
        "creater": null,
        "email": null,
        "empNo": "",
        "flowUserId": null,
        "isActived": null,
        "isState": 0,
        "lastLogin": null,
        "mobile": "15814482675",
        "password": null,
        "position": null,
        "positionName": "",
        "realname": null,
        "remark": null,
        "roleId": null,
        "sex": null,
        "superId": null,
        "superName": "",
        "tel": "",
        "token": null,
        "unitId": null,
        "unitName": null,
        "updateTime": null,
        "updater": null,
        "userId": 33335,
        "userName": "15814482675"
      }
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 8,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8
    ],
    "nextPage": 2,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 405,
    "prePage": 0,
    "size": 10,
    "startRow": 1,
    "total": 4050
  }
     */

    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private List<User> list;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int navigatePages;
    private List<Integer> navigatepageNums;
    private int nextPage;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
