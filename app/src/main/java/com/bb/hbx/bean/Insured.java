package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Insured {

    private String insuredId;//被保险人id

    private String relationType;//与投保人关系

    private int num;//购买份数

    private int idType;

    private String idNo;//

    private String mobile;//

    private String insuredName;//

    private String occupation="";//从事职业

    private List<Ben> benList;//受益人列表


    public String getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }

    public List<Ben> getBenList() {
        return benList;
    }

    public void setBenList(List<Ben> benList) {
        this.benList = benList;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
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

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }
}
