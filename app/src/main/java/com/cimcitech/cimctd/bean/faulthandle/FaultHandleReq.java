package com.cimcitech.cimctd.bean.faulthandle;

/**
 * Created by lyw on 2018/4/26.
 */

public class FaultHandleReq {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private FaultHandleBean faultHandle;

    public FaultHandleReq(int pageNum, int pageSize, String orderBy, FaultHandleBean faultHandle) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.faultHandle = faultHandle;
    }

    public static class FaultHandleBean{
        private Long productId;
        private Long partId;
        private Long placeId;
        private String faultType;
        private String drive;
        private String approach;

        public FaultHandleBean(Long productId, Long partId, Long placeId, String faultType, String drive, String approach) {
            this.productId = productId;
            this.partId = partId;
            this.placeId = placeId;
            this.faultType = faultType;
            this.drive = drive;
            this.approach = approach;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getPartId() {
            return partId;
        }

        public void setPartId(Long partId) {
            this.partId = partId;
        }

        public Long getPlaceId() {
            return placeId;
        }

        public void setPlaceId(Long placeId) {
            this.placeId = placeId;
        }

        public String getFaultType() {
            return faultType;
        }

        public void setFaultType(String faultType) {
            this.faultType = faultType;
        }

        public String getDrive() {
            return drive;
        }

        public void setDrive(String drive) {
            this.drive = drive;
        }

        public String getApproach() {
            return approach;
        }

        public void setApproach(String approach) {
            this.approach = approach;
        }
    }



}
