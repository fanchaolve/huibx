package com.bb.hbx.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**当点击客户管理中的某一联系人是,,需要传递本Bean至新的页面
 * Created by Administrator on 2017/2/14.
 */

public class InsuredInfolBean implements Parcelable{

    private String birthday;
    private String email;
    private String gender;
    private String idNo;
    private String idType;
    private String insurantDesc;
    private String insuredAbbr;
    private String insuredAddress;
    private String insuredEname;
    private String insuredId;
    private String insuredName;
    private String mobile;
    private String occupation;
    private String relation;
    private String areaId;
    private String area;

    public InsuredInfolBean(String birthday, String email, String gender, String idNo, String idType, String insurantDesc, String insuredAbbr,
                            String insuredAddress, String insuredEname, String insuredId, String insuredName, String mobile, String occupation,
                            String relation, String areaId, String area) {
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.idNo = idNo;
        this.idType = idType;
        this.insurantDesc = insurantDesc;
        this.insuredAbbr = insuredAbbr;
        this.insuredAddress = insuredAddress;
        this.insuredEname = insuredEname;
        this.insuredId = insuredId;
        this.insuredName = insuredName;
        this.mobile = mobile;
        this.occupation = occupation;
        this.relation = relation;
        this.area = area;
        this.areaId = areaId;
    }

    protected InsuredInfolBean(Parcel in) {
        birthday = in.readString();
        email = in.readString();
        gender = in.readString();
        idNo = in.readString();
        idType = in.readString();
        insurantDesc = in.readString();
        insuredAbbr = in.readString();
        insuredAddress = in.readString();
        insuredEname = in.readString();
        insuredId = in.readString();
        insuredName = in.readString();
        mobile = in.readString();
        occupation = in.readString();
        relation = in.readString();
        area = in.readString();
        areaId = in.readString();
    }

    public static final Creator<InsuredInfolBean> CREATOR = new Creator<InsuredInfolBean>() {
        @Override
        public InsuredInfolBean createFromParcel(Parcel in) {
            return new InsuredInfolBean(in);
        }

        @Override
        public InsuredInfolBean[] newArray(int size) {
            return new InsuredInfolBean[size];
        }
    };

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getInsurantDesc() {
        return insurantDesc;
    }

    public void setInsurantDesc(String insurantDesc) {
        this.insurantDesc = insurantDesc;
    }

    public String getInsuredAbbr() {
        return insuredAbbr;
    }

    public void setInsuredAbbr(String insuredAbbr) {
        this.insuredAbbr = insuredAbbr;
    }

    public String getInsuredAddress() {
        return insuredAddress;
    }

    public void setInsuredAddress(String insuredAddress) {
        this.insuredAddress = insuredAddress;
    }

    public String getInsuredEname() {
        return insuredEname;
    }

    public void setInsuredEname(String insuredEname) {
        this.insuredEname = insuredEname;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(birthday);
        dest.writeString(email);
        dest.writeString(gender);
        dest.writeString(idNo);
        dest.writeString(idType);
        dest.writeString(insurantDesc);
        dest.writeString(insuredAbbr);
        dest.writeString(insuredAddress);
        dest.writeString(insuredEname);
        dest.writeString(insuredId);
        dest.writeString(insuredName);
        dest.writeString(mobile);
        dest.writeString(occupation);
        dest.writeString(relation);
        dest.writeString(area);
        dest.writeString(areaId);
    }
}
