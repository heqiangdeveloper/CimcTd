package com.cimcitech.cimctd.bean.contact;

import java.io.Serializable;

public class Contact implements Serializable{
    /*
    *   ""addr1": "青岛市城阳区民航区",
        "addr2": "",
        "birthday": 1512230400000,
        "contactId": 10,
        "contactName": "张忠林",
        "createName": "系统管理员",
        "createTime": 1512287586000,
        "creater": 13464,
        "custId": 55,
        "custName": "青岛国际机场集团有限公司  ",
        "deptName": "机电维修科",
        "duties": "副科长",
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
        "tel": "13589277091",
        "updateName": null,
        "updateTime": null,
        "updater": null
     */
     private  String addr1;
    private  String  addr2;
    private  Long  birthday;
    private  Long contactId;
    private  String  contactName;
    private  String  createName;
    private  Long  createTime;
    private  Long  creater;
    private  Long   custId;
    private  String  custName;
    private  String  deptName;
    private  String duties;
    private  String  email;
    private  String  fax;
    private  String  hobbies;
    private  String   internalRela;
    private  String   internalRelaValue;
    private  int  isPass;
    private  int  isState;
    private  String relationLevel;
    private  String  relationLevelValue;
    private  String  remark;
    private  String  sex;
    private  String  tel;
    private  String  updateName;
    private  Long  updateTime;
    private  String  updater;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getInternalRela() {
        return internalRela;
    }

    public void setInternalRela(String internalRela) {
        this.internalRela = internalRela;
    }

    public String getInternalRelaValue() {
        return internalRelaValue;
    }

    public void setInternalRelaValue(String internalRelaValue) {
        this.internalRelaValue = internalRelaValue;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    public int getIsState() {
        return isState;
    }

    public void setIsState(int isState) {
        this.isState = isState;
    }

    public String getRelationLevel() {
        return relationLevel;
    }

    public void setRelationLevel(String relationLevel) {
        this.relationLevel = relationLevel;
    }

    public String getRelationLevelValue() {
        return relationLevelValue;
    }

    public void setRelationLevelValue(String relationLevelValue) {
        this.relationLevelValue = relationLevelValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}