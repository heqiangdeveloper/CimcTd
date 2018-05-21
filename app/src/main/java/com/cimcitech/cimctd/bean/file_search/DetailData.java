package com.cimcitech.cimctd.bean.file_search;

import java.util.List;

/**
 * Created by lyw on 2018/4/12.
 */

public class DetailData {
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
        "contractId": 501,
        "contractNo": "WHJC-15-CG-06",
        "createTime": 1512109547994,
        "creater": 13464,
        "custId": null,
        "custName": "武汉机场有限责任公司",
        "deliveryDate": 1453996800000,
        "detailId": 340,
        "fileId": null,
        "isScrap": "1",
        "jobNum": "7461",
        "maDate": null,
        "machineNum": "2756",
        "machineNumber": "",
        "mainDate": 1453996800000,
        "odDate": null,
        "plcName": "西门子",
        "plcType": "0",
        "productTypeDesc": "10-轮式-混合登机桥",
        "productionDate": 1451577600000,
        "remarks": "",
        "serialNum": "2756",
        "signDate": 1446307200000,
        "udDate": null,
        "updateTime": null,
        "updater": null
      },
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
    "pages": 272,
    "prePage": 0,
    "size": 10,
    "startRow": 1,
    "total": 2716
     */

    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private List<FileDetail> list;
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

    public List<FileDetail> getList() {
        return list;
    }

    public void setList(List<FileDetail> list) {
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
