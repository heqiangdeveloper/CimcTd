package com.cimcitech.cimctd.bean.file_search;

/**
 * Created by lyw on 2018/4/12.
 */

public class FileSearchDetailReq {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private filesVoBean filesVo;

    public FileSearchDetailReq(int pageNum, int pageSize, String orderBy, filesVoBean filesVo){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.filesVo = filesVo;
    }

    public filesVoBean getFilesVo() {
        return filesVo;
    }

    public void setFilesVo(filesVoBean filesVo) {
        this.filesVo = filesVo;
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

    public static class filesVoBean{
        private int detailId;
        private String newFileName;

        public filesVoBean(int detailId,String newFileName){
            this.detailId = detailId;
            this.newFileName = newFileName;
        }

        public int getDetailId() {
            return detailId;
        }

        public void setDetailId(int detailId) {
            this.detailId = detailId;
        }

        public String getNewFileName() {
            return newFileName;
        }

        public void setNewFileName(String newFileName) {
            this.newFileName = newFileName;
        }
    }
}
