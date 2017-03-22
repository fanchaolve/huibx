package com.bb.hbx.bean;

/**
 * Created by Administrator on 2017/2/25.
 */

public class SingleCustomEditBean {

    private String userId;
    private String insuredId;
    private String insuredName;
    private String gender;
    private String birthday;
    private String mobile;
    private String idType;
    private String idNo;
    private String address;
    private String street;
    private String email;
    private String description;
    private String occupation;
    private String relation;

    public SingleCustomEditBean(String userId, String insuredId, String insuredName, String gender, String birthday, String mobile, String idType,
                                String idNo, String address, String street, String email, String occupation, String relation, String description) {
        this.userId = userId;
        this.insuredId = insuredId;
        this.insuredName = insuredName;
        this.gender = gender;
        this.birthday = birthday;
        this.mobile = mobile;
        this.idType = idType;
        this.idNo = idNo;
        this.address = address;
        this.street = street;
        this.email = email;
        this.description = description;
        this.occupation = occupation;
        this.relation = relation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
