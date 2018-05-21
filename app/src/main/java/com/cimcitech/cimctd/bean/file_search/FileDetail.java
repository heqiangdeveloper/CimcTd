package com.cimcitech.cimctd.bean.file_search;

public class FileDetail {
    /*
    *   "contractId": null,
        "detailId": null,
        "detailIds": null,
        "fileId": 1,
        "fileType": "FT00",
        "fileTypeName": "合同",
        "fileUrl": "C:\\Fileupload/7461 武汉天河机场三期T3航站楼登机桥合同_1512283973041.pdf",
        "folder": null,
        "newFileName": "7461 武汉天河机场三期T3航站楼登机桥合同_1512283973041.pdf",
        "originalFileName": "7461 武汉天河机场三期T3航站楼登机桥合同.pdf",
        "remark": null,
        "size": "",
        "tableName": null,
        "uploadName": "系统管理员",
        "uploadPerson": 13464,
        "uploadTime": 1512283973041
     */
    private String contractId;
    private String detailId;
    private String detailIds;
    private int fileId;
    private String fileType;
    private String fileTypeName;
    private String fileUrl;
    private String folder;
    private String newFileName;
    private String originalFileName;
    private String remark;
    private String tableName;
    private String uploadName;
    private int uploadPerson;
    private Long uploadTime;
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getDetailIds() {
        return detailIds;
    }

    public void setDetailIds(String detailIds) {
        this.detailIds = detailIds;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public int getUploadPerson() {
        return uploadPerson;
    }

    public void setUploadPerson(int uploadPerson) {
        this.uploadPerson = uploadPerson;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }
}