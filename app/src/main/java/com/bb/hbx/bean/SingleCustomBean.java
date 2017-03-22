package com.bb.hbx.bean;

/**
 * Created by Administrator on 2017/2/25.
 * 手动添加通讯录好友的bean类
 */

public class SingleCustomBean {

    private String userId;                  //用户ID
    private String insuredName;             //保险联系人姓名
    private String idType;                  //证件类型
    private String idNo;                    //证件号码
    private String gender;                  //性别
    private String birthday;                //出生年月
    private String mobile;                  //手机号码
    private String email;                   //电子邮件
    private String occupation;              //职业
    private String relation;                //被保险人关系
    private String areaId;                  //所在地区
    private String insurantAddress;         //详细地址
    private String insurantDesc;            //备注

    public SingleCustomBean(String userId, String insuredName, String idType, String idNo,
                            String gender, String birthday, String mobile, String email,
                            String occupation, String relation, String areaId,
                            String insurantAddress, String insurantDesc) {
        this.idNo = idNo;
        this.gender = gender;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
        this.occupation = occupation;
        this.relation = relation;
        this.areaId = areaId;
        this.insurantAddress = insurantAddress;
        this.insurantDesc = insurantDesc;
        this.idType = idType;
        this.insuredName = insuredName;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getInsurantAddress() {
        return insurantAddress;
    }

    public void setInsurantAddress(String insurantAddress) {
        this.insurantAddress = insurantAddress;
    }

    public String getInsurantDesc() {
        return insurantDesc;
    }

    public void setInsurantDesc(String insurantDesc) {
        this.insurantDesc = insurantDesc;
    }
}
