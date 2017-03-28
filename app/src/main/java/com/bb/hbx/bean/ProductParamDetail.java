package com.bb.hbx.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */

public class ProductParamDetail {


    /**
     * benNum :
     * claimGuide :
     * classId : 310022
     * clause :
     * defaultInsurant :
     * endtInsureTime : 99990101000000
     * flashSale : 0
     * genderLimit : 0
     * guaranteePeriod : 183天;1天至7天;8天至10天;11天至14天;15天至17天;18天至21天;22天至24天;25天至30天;31天至60天;61天至90天;91天至180天
     * insureWindow : 0
     * insurerId : 5
     * insurerLogo : http://ebao.seaway.net.cn:18100http://img.51hbx.com/resource/images/product/1486538972745.jpeg
     * insurerName : 安联保险
     * lastInsureTime : 99990101000000
     * maxAge : 90_4;
     * maxInsurant : 2
     * maxQuantity : 0
     * minAge : 60_1;
     * monthAmount : 0
     * occupation : 9
     * planList : []
     * policyType : 1
     * priceElements :
     * priceList : []
     * productId : 1049
     * productIntro :
     * productLogo : http://ebao.seaway.net.cn:18100http://img.51hbx.com/resource/images/product/1486881687588.png
     * productName : 安联国际旅行保险
     * productProp : 1
     * productTagUrls : http://ebao.seaway.net.cn:18100http://img.51hbx.com/resource/images/product/1486881685682.jpeg
     * prompt : 安联保险好，理赔找不到！
     * quota : 1
     * refundFlag : 1
     * startInsureTime : 20170212145213
     * suitable : 全部
     * totalAmount : 0
     * totalQuantity : 0
     */

    private List<Entry> entries = new ArrayList<>();
    private String benNum;
    private String claimGuide;
    private String classId;
    private String clause;
    private String defaultInsurant;
    private String endtInsureTime;
    private String flashSale;
    private String genderLimit;
    private String guaranteePeriod;
    private String insureWindow;
    private String insurerId;
    private String insurerLogo;
    private String insurerName;
    private String lastInsureTime;
    private String maxAge;
    private String maxInsurant;
    private int maxQuantity;
    private String minAge;
    private String monthAmount;
    private String occupation;
    private String policyType;
    private String priceElements;
    private String productId;
    private String productIntro;
    private String productLogo;
    private String productName;
    private String productProp;
    private String productTagUrls;
    private String prompt;
    private int quota;
    private String refundFlag;
    private String startInsureTime;
    private String suitable;
    private String totalAmount;
    private int totalQuantity;
    private String productFeature;
    private String perferWords;
    private String commisionType;
    private String commisionValue1;
    private String commisionValue2;
    private String effectDate;
    private String relationship;
    private String promotionCommisionValue1="";
    private String promotionCommisionValue2="";
    private String promotionVehicleCommisionValue1="";
    private String promotionVehicleCommisionValue2="";
    private int effectiveType;//1：即刻生效；2：次日生效；3：次月生效；10：指定天数后生效，天数存放在insure_windows字段中；99：指定日期生效；

    private List<Plan> planList;
    private List<PriceTag> priceList;

    public String getPromotionCommisionValue1() {
        return promotionCommisionValue1;
    }

    public void setPromotionCommisionValue1(String promotionCommisionValue1) {
        this.promotionCommisionValue1 = promotionCommisionValue1;
    }

    public String getPromotionCommisionValue2() {
        return promotionCommisionValue2;
    }

    public void setPromotionCommisionValue2(String promotionCommisionValue2) {
        this.promotionCommisionValue2 = promotionCommisionValue2;
    }

    public String getPromotionVehicleCommisionValue1() {
        return promotionVehicleCommisionValue1;
    }

    public void setPromotionVehicleCommisionValue1(String promotionVehicleCommisionValue1) {
        this.promotionVehicleCommisionValue1 = promotionVehicleCommisionValue1;
    }

    public String getPromotionVehicleCommisionValue2() {
        return promotionVehicleCommisionValue2;
    }

    public void setPromotionVehicleCommisionValue2(String promotionVehicleCommisionValue2) {
        this.promotionVehicleCommisionValue2 = promotionVehicleCommisionValue2;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBenNum() {
        return benNum;
    }

    public void setBenNum(String benNum) {
        this.benNum = benNum;
    }

    public String getClaimGuide() {
        return claimGuide;
    }

    public void setClaimGuide(String claimGuide) {
        this.claimGuide = claimGuide;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String getDefaultInsurant() {
        return defaultInsurant;
    }

    public void setDefaultInsurant(String defaultInsurant) {
        this.defaultInsurant = defaultInsurant;
    }

    public String getEndtInsureTime() {
        return endtInsureTime;
    }

    public void setEndtInsureTime(String endtInsureTime) {
        this.endtInsureTime = endtInsureTime;
    }

    public String getFlashSale() {
        return flashSale;
    }

    public void setFlashSale(String flashSale) {
        this.flashSale = flashSale;
    }

    public String getGenderLimit() {
        return genderLimit;
    }

    public void setGenderLimit(String genderLimit) {
        this.genderLimit = genderLimit;
    }

    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

    public String getInsureWindow() {
        return insureWindow;
    }

    public void setInsureWindow(String insureWindow) {
        this.insureWindow = insureWindow;
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

    public String getInsurerName() {
        return insurerName;
    }

    public void setInsurerName(String insurerName) {
        this.insurerName = insurerName;
    }

    public String getLastInsureTime() {
        return lastInsureTime;
    }

    public void setLastInsureTime(String lastInsureTime) {
        this.lastInsureTime = lastInsureTime;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getMaxInsurant() {
        return maxInsurant;
    }

    public void setMaxInsurant(String maxInsurant) {
        this.maxInsurant = maxInsurant;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(String monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPriceElements() {
        return priceElements;
    }

    public void setPriceElements(String priceElements) {
        this.priceElements = priceElements;
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

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getRefundFlag() {
        return refundFlag;
    }

    public void setRefundFlag(String refundFlag) {
        this.refundFlag = refundFlag;
    }

    public String getStartInsureTime() {
        return startInsureTime;
    }

    public void setStartInsureTime(String startInsureTime) {
        this.startInsureTime = startInsureTime;
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public List<PriceTag> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceTag> priceList) {
        this.priceList = priceList;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    public String getPerferWords() {
        return perferWords;
    }

    public void setPerferWords(String perferWords) {
        this.perferWords = perferWords;
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

    public String getCommisionValue2() {
        return commisionValue2;
    }

    public void setCommisionValue2(String commisionValue2) {
        this.commisionValue2 = commisionValue2;
    }

    public int getEffectiveType() {
        return effectiveType;
    }

    public void setEffectiveType(int effectiveType) {
        this.effectiveType = effectiveType;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

}
