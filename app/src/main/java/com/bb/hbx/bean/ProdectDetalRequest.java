package com.bb.hbx.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class ProdectDetalRequest {

    private String userId = "";//用户ID


    private String productId = "";//产品编号

    private int planId;//计划编号

    private String period = "";//保障期限值

    private String priceKeyword = "";//价格关键字

    private String relateTrade = "";//渠道订单号

    private String startTime = "";//起保时间

    private String endTime = "";//终保时间

    private String applicantId = "";//投保人ID

    private String email = "";//电子邮件

    private String isExpress = "";//是否快递

    private String cneeName = "";//收货人姓名

    private String cneeMobile = "";// 收货人手机

    private String areaCode = "";//城市编号

    private String address = "";//详细地址

    private String referrer = "";//推荐人

    private String agent = "";//代理人

    private String extraInfos = "";//扩展信息

    private String idNo = "";//证件号

    private String mobile = "";//手机号码

    private String applicant="";//投保人姓名

    private String classType="";//类型

    private String selectedAge="";//code------------

    private int idType;

    private List<Insured> insuredList = new ArrayList<>();


    public String getSelectedAge() {
        return selectedAge;
    }

    public void setSelectedAge(String selectedAge) {
        this.selectedAge = selectedAge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCneeMobile() {
        return cneeMobile;
    }

    public void setCneeMobile(String cneeMobile) {
        this.cneeMobile = cneeMobile;
    }

    public String getCneeName() {
        return cneeName;
    }

    public void setCneeName(String cneeName) {
        this.cneeName = cneeName;
    }

    public String getIsExpress() {
        return isExpress;
    }

    public void setIsExpress(String isExpress) {
        this.isExpress = isExpress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRelateTrade() {
        return relateTrade;
    }

    public void setRelateTrade(String relateTrade) {
        this.relateTrade = relateTrade;
    }

    public String getPriceKeyword() {
        return priceKeyword;
    }

    public void setPriceKeyword(String priceKeyword) {
        this.priceKeyword = priceKeyword;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<Insured> getInsuredList() {
        return insuredList;
    }

    public void setInsuredList(List<Insured> insuredList) {
        this.insuredList = insuredList;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
