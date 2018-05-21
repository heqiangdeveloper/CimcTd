package com.cimcitech.cimctd.bean.contact;

import java.util.List;

/**
 * Created by lyw on 2018/4/12.
 */

public class ContactData {
    /*
    *   "data": {
    "endRow": 10,
    "firstPage": 1,
    "hasNextPage": true,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": false,
    "lastPage": 8,
    "list": [
      {
        "addr1": "西安机场候机楼",
        "addr2": "",
        "birthday": 1512230400000,
        "contactId": 1,
        "contactName": "李小光",
        "createName": "系统管理员",
        "createTime": 1512287152000,
        "creater": 13464,
        "custId": 70,
        "custName": "西安咸阳机场股份有限公司",
        "deptName": "机电公司",
        "duties": "副总",
        "email": "",
        "fax": "",
        "hobbies": null,
        "internalRela": "NG01",
        "internalRelaValue": "管理层",
        "isPass": 1,
        "isState": 1,
        "relationLevel": "RL01",
        "relationLevelValue": "很好",
        "remark": "",
        "sex": "1",
        "tel": "13891081203",
        "updateName": "系统管理员",
        "updateTime": 1512287262000,
        "updater": 13464
      },
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
    "pages": 9,
    "prePage": 0,
    "size": 10,
    "startRow": 1,
    "total": 87
     */

    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private List<Contact> list;
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

    public List<Contact> getList() {
        return list;
    }

    public void setList(List<Contact> list) {
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
