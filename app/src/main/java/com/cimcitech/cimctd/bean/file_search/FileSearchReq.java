package com.cimcitech.cimctd.bean.file_search;

/**
 * Created by lyw on 2018/4/12.
 */

public class FileSearchReq {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private SaleContDetBean saleContDet;

    public FileSearchReq(int pageNum, int pageSize, String orderBy, SaleContDetBean saleContDet){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.saleContDet = saleContDet;
    }

    public SaleContDetBean getSaleContDet() {
        return saleContDet;
    }

    public void setSaleContDet(SaleContDetBean saleContDet) {
        this.saleContDet = saleContDet;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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

    public static class SaleContDetBean{
        private long contractId;
        private String serialNum;
        private long custId;

        public SaleContDetBean(String serialNum){
            this.serialNum = serialNum;
        }

        public SaleContDetBean(long contractId,String serialNum,long custId){
            this.contractId = contractId;
            this.serialNum = serialNum;
            this.custId = custId;
        }

        public long getContractId() {
            return contractId;
        }

        public void setContractId(long contractId) {
            this.contractId = contractId;
        }

        public String getSerialNum() {
            return serialNum;
        }

        public void setSerialNum(String serialNum) {
            this.serialNum = serialNum;
        }

        public long getCustId() {
            return custId;
        }

        public void setCustId(long custId) {
            this.custId = custId;
        }
    }
}
