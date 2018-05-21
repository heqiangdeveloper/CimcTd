package com.cimcitech.cimctd.bean.dispatch;

/**
 * Created by lyw on 2018/4/26.
 */

public class saleMaintenPlanVo {
    private String isSendJobs;
    private String isReceive;
    private String isEnd;
    private Long startDate;
    private Long endDate;
    private Long contractId;
    private Long custId;
    private Long chargeId;

    public saleMaintenPlanVo(String isSendJobs, String isReceive, String isEnd, Long startDate, Long endDate, Long contractId, Long custId, Long chargeId) {
        this.isSendJobs = isSendJobs;
        this.isReceive = isReceive;
        this.isEnd = isEnd;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractId = contractId;
        this.custId = custId;
        this.chargeId = chargeId;
    }

    public String getIsSendJobs() {
        return isSendJobs;
    }

    public void setIsSendJobs(String isSendJobs) {
        this.isSendJobs = isSendJobs;
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }
}
