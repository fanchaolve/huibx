package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 * 收藏详情的Bean类
 */

public class CollectionBean {

    /**
     * favoritesProductsRecord : [{"ageDesc":"23","areaDesc":"","benefitPlanList":[],"businessDesc":"","classType":"1","commisionValue1":"55","commisionValue2":"15","guaranteePeriod":"1个月;1年","insurerLogo":"","insurerName":"人保财险","maxAge":"17周岁","maxPremium":1,"minAge":"60天","minPremium":50,"planList":[],"productDesc":"","productLogo":"","productName":"航空意外保障","productTagUrls":"","sumInsured":90,"totalQuantity":"111"},{"ageDesc":"","areaDesc":"","benefitPlanList":[],"businessDesc":"","classType":"2","commisionValue1":"9","commisionValue2":"12","guaranteePeriod":"1天_1_1;3天_3_1","insurerLogo":"http://img.51hbx.com/resource/images/product/1486538972745.jpeg","insurerName":"安联保险","maxAge":"70周岁","maxPremium":3500,"minAge":"18周岁","minPremium":1000,"planList":[],"productDesc":"","productLogo":"","productName":"畅享自驾 - 安联境内自驾旅行保险计划","productTagUrls":"http://img.51hbx.com/resource/images/product/1489026798590.jpg","sumInsured":0,"totalQuantity":"405"}]
     * pageSize :
     * totalCount :
     */

    private String pageSize;
    private String totalCount;
    private List<FavoritesProductsRecordBean> favoritesProductsRecord;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<FavoritesProductsRecordBean> getFavoritesProductsRecord() {
        return favoritesProductsRecord;
    }

    public void setFavoritesProductsRecord(List<FavoritesProductsRecordBean> favoritesProductsRecord) {
        this.favoritesProductsRecord = favoritesProductsRecord;
    }

    public static class FavoritesProductsRecordBean {
        /**
         * ageDesc : 23
         * areaDesc :
         * benefitPlanList : []
         * businessDesc :
         * classType : 1
         * commisionValue1 : 55
         * commisionValue2 : 15
         * guaranteePeriod : 1个月;1年
         * insurerLogo :
         * insurerName : 人保财险
         * maxAge : 17周岁
         * maxPremium : 1
         * minAge : 60天
         * minPremium : 50
         * planList : []
         * productDesc :
         * productLogo :
         * productName : 航空意外保障
         * productTagUrls :
         * sumInsured : 90
         * totalQuantity : 111
         */

        private String ageDesc;
        private String areaDesc;
        private String businessDesc;
        private String classType;
        private String commisionValue1;
        private String commisionValue2;
        private String guaranteePeriod;
        private String insurerLogo;
        private String insurerName;
        private String maxAge;
        private int maxPremium;
        private String minAge;
        private int minPremium;
        private String productDesc;
        private String productLogo;
        private String productName;
        private String productTagUrls;
        private int sumInsured;
        private String totalQuantity;
        private List<?> benefitPlanList;
        private List<?> planList;

        public String getAgeDesc() {
            return ageDesc;
        }

        public void setAgeDesc(String ageDesc) {
            this.ageDesc = ageDesc;
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

        public String getClassType() {
            return classType;
        }

        public void setClassType(String classType) {
            this.classType = classType;
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

        public String getGuaranteePeriod() {
            return guaranteePeriod;
        }

        public void setGuaranteePeriod(String guaranteePeriod) {
            this.guaranteePeriod = guaranteePeriod;
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

        public String getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(String maxAge) {
            this.maxAge = maxAge;
        }

        public int getMaxPremium() {
            return maxPremium;
        }

        public void setMaxPremium(int maxPremium) {
            this.maxPremium = maxPremium;
        }

        public String getMinAge() {
            return minAge;
        }

        public void setMinAge(String minAge) {
            this.minAge = minAge;
        }

        public int getMinPremium() {
            return minPremium;
        }

        public void setMinPremium(int minPremium) {
            this.minPremium = minPremium;
        }

        public String getProductDesc() {
            return productDesc;
        }

        public void setProductDesc(String productDesc) {
            this.productDesc = productDesc;
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

        public String getProductTagUrls() {
            return productTagUrls;
        }

        public void setProductTagUrls(String productTagUrls) {
            this.productTagUrls = productTagUrls;
        }

        public int getSumInsured() {
            return sumInsured;
        }

        public void setSumInsured(int sumInsured) {
            this.sumInsured = sumInsured;
        }

        public String getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(String totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public List<?> getBenefitPlanList() {
            return benefitPlanList;
        }

        public void setBenefitPlanList(List<?> benefitPlanList) {
            this.benefitPlanList = benefitPlanList;
        }

        public List<?> getPlanList() {
            return planList;
        }

        public void setPlanList(List<?> planList) {
            this.planList = planList;
        }
    }
}
