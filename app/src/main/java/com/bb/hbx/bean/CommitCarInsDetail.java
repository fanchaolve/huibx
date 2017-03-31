package com.bb.hbx.bean;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CommitCarInsDetail {

    String userId;
    String serialId;
    String applicantName;
    String applicantIdNo;
    String applicantMobile;
    String insuredName;
    String insuredIdNo;
    String insuredMobile;
    String expressName;
    String expressMobile;
    String expressAddress;
    String expressProvince;
    String expressCity;
    String expressDistrict;
    String carExtras;

    public CommitCarInsDetail(String userId, String serialId, String applicantName, String applicantIdNo, String applicantMobile,
                              String insuredName, String insuredIdNo, String insuredMobile, String expressName, String expressMobile,
                              String expressAddress, String expressProvince, String expressCity, String expressDistrict,String carExtras) {
        this.userId = userId;
        this.serialId = serialId;
        this.applicantName = applicantName;
        this.applicantIdNo = applicantIdNo;
        this.applicantMobile = applicantMobile;
        this.insuredName = insuredName;
        this.insuredIdNo = insuredIdNo;
        this.insuredMobile = insuredMobile;
        this.expressName = expressName;
        this.expressMobile = expressMobile;
        this.expressAddress = expressAddress;
        this.expressProvince = expressProvince;
        this.expressCity = expressCity;
        this.expressDistrict = expressDistrict;
        this.carExtras = carExtras;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantIdNo() {
        return applicantIdNo;
    }

    public void setApplicantIdNo(String applicantIdNo) {
        this.applicantIdNo = applicantIdNo;
    }

    public String getApplicantMobile() {
        return applicantMobile;
    }

    public void setApplicantMobile(String applicantMobile) {
        this.applicantMobile = applicantMobile;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredIdNo() {
        return insuredIdNo;
    }

    public void setInsuredIdNo(String insuredIdNo) {
        this.insuredIdNo = insuredIdNo;
    }

    public String getInsuredMobile() {
        return insuredMobile;
    }

    public void setInsuredMobile(String insuredMobile) {
        this.insuredMobile = insuredMobile;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressMobile() {
        return expressMobile;
    }

    public void setExpressMobile(String expressMobile) {
        this.expressMobile = expressMobile;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    public String getExpressProvince() {
        return expressProvince;
    }

    public void setExpressProvince(String expressProvince) {
        this.expressProvince = expressProvince;
    }

    public String getExpressCity() {
        return expressCity;
    }

    public void setExpressCity(String expressCity) {
        this.expressCity = expressCity;
    }

    public String getExpressDistrict() {
        return expressDistrict;
    }

    public void setExpressDistrict(String expressDistrict) {
        this.expressDistrict = expressDistrict;
    }

    public String getCarExtras() {
        return carExtras;
    }

    public void setCarExtras(String carExtras) {
        this.carExtras = carExtras;
    }
}
