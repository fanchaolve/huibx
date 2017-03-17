package com.bb.hbx.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.bb.hbx.widget.multitype.data.Item;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class Product implements Item,/*Serializable*/Parcelable {


    /**
     * ageDesc : 不限
     * benefitList :
     * classId : 103249
     * commisionType : 1
     * commisionValue1 : 50
     * guarantee :
     * insurerId : 2
     * insurerLogo : http://ebao.seaway.net.cn:181002
     * insurerName : 太平洋保险
     * minPremium : 30
     * monthAmount : 20
     * perferWords :
     * productId : 1002
     * productIntro : 全面保障您的婚姻
     * productLogo : http://ebao.seaway.net.cn:18100http://article.joyme.com/article/uploads/allimg/140617/18-14061G600290-L.jpg
     * productName : 单身狗保障险
     * productPrice : 30
     * productProp :
     * productTagUrls :
     * specialPrice : 30
     * suitable :
     * totalAmount : 20
     */

    private String ageDesc;

    private List<Benefit>benefitList;
    private String classId;
    private String commisionType;
    private String commisionValue1;
    private String guarantee;
    private String insurerId;
    private String insurerLogo;
    private String areaDesc;
    private String businessDesc;
    private String insurerName;
    private String minPremium;
    private String monthAmount;
    private String perferWords;
    private String productId;
    private String productIntro;
    private String productLogo;
    private String productName;
    private String productPrice;
    private String productProp;
    private String productTagUrls;
    private String specialPrice;
    private String suitable;
    private String totalAmount;

    protected Product(Parcel in) {
        ageDesc = in.readString();
        classId = in.readString();
        commisionType = in.readString();
        commisionValue1 = in.readString();
        guarantee = in.readString();
        insurerId = in.readString();
        insurerLogo = in.readString();
        areaDesc = in.readString();
        businessDesc = in.readString();
        insurerName = in.readString();
        minPremium = in.readString();
        monthAmount = in.readString();
        perferWords = in.readString();
        productId = in.readString();
        productIntro = in.readString();
        productLogo = in.readString();
        productName = in.readString();
        productPrice = in.readString();
        productProp = in.readString();
        productTagUrls = in.readString();
        specialPrice = in.readString();
        suitable = in.readString();
        totalAmount = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getAgeDesc() {
        return ageDesc;
    }

    public void setAgeDesc(String ageDesc) {
        this.ageDesc = ageDesc;
    }

    public List<Benefit> getBenefitList() {
        return benefitList;
    }

    public void setBenefitList(List<Benefit> benefitList) {
        this.benefitList = benefitList;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCommisionType() {
        return commisionType;
    }

    public void setCommisionType(String commisionType) {
        this.commisionType = commisionType;
    }

    public String getCommisionValue1() {
        return commisionValue1;
    }

    public void setCommisionValue1(String commisionValue1) {
        this.commisionValue1 = commisionValue1;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(String insurerId) {
        this.insurerId = insurerId;
    }

    public String getInsurerLogo() {
        return insurerLogo;
    }

    public void setInsurerLogo(String insurerLogo) {
        this.insurerLogo = insurerLogo;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getInsurerName() {
        return insurerName;
    }

    public void setInsurerName(String insurerName) {
        this.insurerName = insurerName;
    }

    public String getMinPremium() {
        return minPremium;
    }

    public void setMinPremium(String minPremium) {
        this.minPremium = minPremium;
    }

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(String monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getPerferWords() {
        return perferWords;
    }

    public void setPerferWords(String perferWords) {
        this.perferWords = perferWords;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductProp() {
        return productProp;
    }

    public void setProductProp(String productProp) {
        this.productProp = productProp;
    }

    public String getProductTagUrls() {
        return productTagUrls;
    }

    public void setProductTagUrls(String productTagUrls) {
        this.productTagUrls = productTagUrls;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getSuitable() {
        return suitable;
    }

    public void setSuitable(String suitable) {
        this.suitable = suitable;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ageDesc);
        dest.writeString(classId);
        dest.writeString(commisionType);
        dest.writeString(commisionValue1);
        dest.writeString(guarantee);
        dest.writeString(insurerId);
        dest.writeString(insurerLogo);
        dest.writeString(areaDesc);
        dest.writeString(businessDesc);
        dest.writeString(insurerName);
        dest.writeString(minPremium);
        dest.writeString(monthAmount);
        dest.writeString(perferWords);
        dest.writeString(productId);
        dest.writeString(productIntro);
        dest.writeString(productLogo);
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeString(productProp);
        dest.writeString(productTagUrls);
        dest.writeString(specialPrice);
        dest.writeString(suitable);
        dest.writeString(totalAmount);
    }
}
