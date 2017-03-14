package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 * 已经使用过的赠险产品信息类
 */

public class HasUsedPreInsuBean {

    /**
     * pageSize : 1
     * presentProductsRsp : [{"ageDesc":"","buyCount":0,"endTime":"2017-01-31 20:17:55","expTime":"","guaranteePeriod":"","insuredName":"","insurerLogo":"http://img.51hbx.com/resource/images/product/1486538972745.jpeg","maxAge":"","maxPremium":0,"minAge":"","minPremium":0,"policyNo":"","policyholderName":"唐僧","productDesc":"","productLogo":"","productName":"延乐保（境外版）-安联境外航延险计划zz","productTagUrls":"","startTime":"2017-01-12 20:17:52","sumInsured":0}]
     * totalCount : 1
     */

    private String pageSize;
    private String totalCount;
    private List<PresentProductsRspBean> presentProductsRsp;

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

    public List<PresentProductsRspBean> getPresentProductsRsp() {
        return presentProductsRsp;
    }

    public void setPresentProductsRsp(List<PresentProductsRspBean> presentProductsRsp) {
        this.presentProductsRsp = presentProductsRsp;
    }

    public static class PresentProductsRspBean {
        /**
         * ageDesc :
         * buyCount : 0
         * endTime : 2017-01-31 20:17:55
         * expTime :
         * guaranteePeriod :
         * insuredName :
         * insurerLogo : http://img.51hbx.com/resource/images/product/1486538972745.jpeg
         * maxAge :
         * maxPremium : 0
         * minAge :
         * minPremium : 0
         * policyNo :
         * policyholderName : 唐僧
         * productDesc :
         * productLogo :
         * productName : 延乐保（境外版）-安联境外航延险计划zz
         * productTagUrls :
         * startTime : 2017-01-12 20:17:52
         * sumInsured : 0
         */

        private String ageDesc;
        private int buyCount;
        private String endTime;
        private String expTime;
        private String guaranteePeriod;
        private String insuredName;
        private String insurerLogo;
        private String maxAge;
        private int maxPremium;
        private String minAge;
        private int minPremium;
        private String policyNo;
        private String policyholderName;
        private String productDesc;
        private String productLogo;
        private String productName;
        private String productTagUrls;
        private String startTime;
        private int sumInsured;

        public String getAgeDesc() {
            return ageDesc;
        }

        public void setAgeDesc(String ageDesc) {
            this.ageDesc = ageDesc;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getExpTime() {
            return expTime;
        }

        public void setExpTime(String expTime) {
            this.expTime = expTime;
        }

        public String getGuaranteePeriod() {
            return guaranteePeriod;
        }

        public void setGuaranteePeriod(String guaranteePeriod) {
            this.guaranteePeriod = guaranteePeriod;
        }

        public String getInsuredName() {
            return insuredName;
        }

        public void setInsuredName(String insuredName) {
            this.insuredName = insuredName;
        }

        public String getInsurerLogo() {
            return insurerLogo;
        }

        public void setInsurerLogo(String insurerLogo) {
            this.insurerLogo = insurerLogo;
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

        public String getPolicyNo() {
            return policyNo;
        }

        public void setPolicyNo(String policyNo) {
            this.policyNo = policyNo;
        }

        public String getPolicyholderName() {
            return policyholderName;
        }

        public void setPolicyholderName(String policyholderName) {
            this.policyholderName = policyholderName;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getSumInsured() {
            return sumInsured;
        }

        public void setSumInsured(int sumInsured) {
            this.sumInsured = sumInsured;
        }
    }
}
