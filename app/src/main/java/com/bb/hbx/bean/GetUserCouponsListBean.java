package com.bb.hbx.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class GetUserCouponsListBean {

    /**
     * output : {"couponList":[{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-20","expTime":"2017-02-22","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-20","expTime":"2017-02-22","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"}],"pageSize":"7","totalCount":"7"}
     * respCode : 000000
     * respMsg :
     * success : true
     */

        /**
         * couponList : [{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-20","expTime":"2017-02-22","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-20","expTime":"2017-02-22","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"},{"couponDesc":"","couponId":"1","couponName":"新用户红包","couponType":"2","couponUrl":"","couponValue":"500","effTime":"2017-02-17","expTime":"2017-02-28","offUpto":"0"}]
         * pageSize : 7
         * totalCount : 7
         */

        private String pageSize;
        private String totalCount;
        private List<CouponListBean> couponList;

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

        public List<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean implements Serializable {
            /**
             * couponDesc :
             * couponId : 1
             * couponName : 新用户红包
             * couponType : 2
             * couponUrl :
             * couponValue : 500
             * effTime : 2017-02-17
             * expTime : 2017-02-28
             * offUpto : 0
             */

            private String couponDesc;
            private String couponCode;
            private String couponId;
            private String couponName;
            private String couponType;
            private String couponUrl;
            private String couponValue;
            private String effTime;
            private String expTime;
            private String offUpto;

            public String getCouponCode() {
                return couponCode;
            }

            public void setCouponCode(String couponCode) {
                this.couponCode = couponCode;
            }

            public String getCouponDesc() {
                return couponDesc;
            }

            public void setCouponDesc(String couponDesc) {
                this.couponDesc = couponDesc;
            }

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public String getCouponType() {
                return couponType;
            }

            public void setCouponType(String couponType) {
                this.couponType = couponType;
            }

            public String getCouponUrl() {
                return couponUrl;
            }

            public void setCouponUrl(String couponUrl) {
                this.couponUrl = couponUrl;
            }

            public String getCouponValue() {
                return couponValue;
            }

            public void setCouponValue(String couponValue) {
                this.couponValue = couponValue;
            }

            public String getEffTime() {
                return effTime;
            }

            public void setEffTime(String effTime) {
                this.effTime = effTime;
            }

            public String getExpTime() {
                return expTime;
            }

            public void setExpTime(String expTime) {
                this.expTime = expTime;
            }

            public String getOffUpto() {
                return offUpto;
            }

            public void setOffUpto(String offUpto) {
                this.offUpto = offUpto;
            }
        }
}
