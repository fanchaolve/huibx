package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 * 车险保单详情类
 */

public class CarOrderBean {

    /**
     * applicant : 1111
     * applicantIdNo : 411424199306122870
     * applicantMobile : 111111
     * applicantType : 1
     * claimsURL :
     * classId : 103249
     * classType : 1
     * discountAmount : 0
     * endTime : 2018-02-22 19:33:52
     * insureAmount : 0
     * insuredList : [{"benList":[],"birthday":"2017-02-28","carInfo":{"cityName":"杭州","engineNo":"123123","fuelType":"","licenseNo":"浙12345","noLicenseFlag":0,"registerDate":"2017-02-21","specialCarDate":"2017-02-28","specialCarFlag":1,"vehicleFrameNo":"12345","vehicleModel":"宝马"},"detailId":"156","email":"","gender":"男","idNo":"20170227192800743493","idType":"1","insureCount":1,"insuredId":"156","insuredName":"222","insuredNamePinyin":"222","mobile":"","policyURL":"","relation":1}]
     * insurerId : 1
     * insurerLogo :
     * insurerName : 人保财险
     * insurerTels : 010-88888888
     * jqxEndTime : 2017-03-06
     * jqxStartTime : 2017-03-06
     * orderURL : http://ebao.seaway.net.cn:18100/product_details.html#productDetails/1092
     * payAmount : 64700
     * paymentList : []
     * policyId :
     * policySts : 10
     * productId : 1092
     * productName : 车险测试产品
     * productProp : 528
     * sex : 1
     * shippingAddress : 杭州西湖区
     * startTime : 2017-02-27 19:33:52
     * sts : -11
     * sumAmount : 100000
     * sumPremium : 64700
     * tradeAmount : 1
     * tradeId : 20170227193252677649
     * tradePeriod : 1年_1_4
     * tradePremium : 64700
     * tradeTime : 2017-02-27 19:32:52
     * typeList : [{"insureList":[{"amountUnit":"","desc":"","extraInsureList":[],"insureAmount":"600,000","insureName":"交强险"},{"amountUnit":"","desc":"","extraInsureList":[],"insureAmount":"600,000","insureName":"车船险"}],"sumInsureAmount":"0","typeId":0,"typeName":"强制保险"},{"insureList":[{"amountUnit":"","desc":"","extraInsureList":[],"insureAmount":"100,000","insureName":"车辆保障"},{"amountUnit":"","desc":"","extraInsureList":[],"insureAmount":"600,000","insureName":"乘客座位险"},{"amountUnit":"","desc":"","extraInsureList":[{"extraDesc":"","extraInsureAmount":"600,000","extraInsureName":"车辆不计免赔"},{"extraDesc":"","extraInsureAmount":"600,000","extraInsureName":"司机座位不计免赔"}],"insureAmount":"","insureName":"附加不计免赔"}],"sumInsureAmount":"0","typeId":0,"typeName":"商业保险"}]
     * userId :
     */

    private String applicant;
    private String applicantIdNo;
    private String applicantMobile;
    private String applicantType;
    private String claimsURL;
    private String classId;
    private int classType;
    private String discountAmount;
    private String endTime;
    private String insureAmount;
    private String insurerId;
    private String insurerLogo;
    private String insurerName;
    private String insurerTels;
    private String jqxEndTime;
    private String jqxStartTime;
    private String orderURL;
    private String payAmount;
    private String policyId;
    private String policySts;
    private String productId;
    private String productName;
    private int productProp;
    private String sex;
    private String shippingAddress;
    private String startTime;
    private String sts;
    private String sumAmount;
    private String sumPremium;
    private String tradeAmount;
    private String tradeId;
    private String tradePeriod;
    private String tradePremium;
    private String tradeTime;
    private String userId;
    private List<InsuredListBean> insuredList;
    private List<?> paymentList;
    private List<TypeListBean> typeList;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
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

    public String getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType;
    }

    public String getClaimsURL() {
        return claimsURL;
    }

    public void setClaimsURL(String claimsURL) {
        this.claimsURL = claimsURL;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getInsureAmount() {
        return insureAmount;
    }

    public void setInsureAmount(String insureAmount) {
        this.insureAmount = insureAmount;
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

    public String getInsurerTels() {
        return insurerTels;
    }

    public void setInsurerTels(String insurerTels) {
        this.insurerTels = insurerTels;
    }

    public String getJqxEndTime() {
        return jqxEndTime;
    }

    public void setJqxEndTime(String jqxEndTime) {
        this.jqxEndTime = jqxEndTime;
    }

    public String getJqxStartTime() {
        return jqxStartTime;
    }

    public void setJqxStartTime(String jqxStartTime) {
        this.jqxStartTime = jqxStartTime;
    }

    public String getOrderURL() {
        return orderURL;
    }

    public void setOrderURL(String orderURL) {
        this.orderURL = orderURL;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicySts() {
        return policySts;
    }

    public void setPolicySts(String policySts) {
        this.policySts = policySts;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductProp() {
        return productProp;
    }

    public void setProductProp(int productProp) {
        this.productProp = productProp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

    public String getSumPremium() {
        return sumPremium;
    }

    public void setSumPremium(String sumPremium) {
        this.sumPremium = sumPremium;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradePeriod() {
        return tradePeriod;
    }

    public void setTradePeriod(String tradePeriod) {
        this.tradePeriod = tradePeriod;
    }

    public String getTradePremium() {
        return tradePremium;
    }

    public void setTradePremium(String tradePremium) {
        this.tradePremium = tradePremium;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<InsuredListBean> getInsuredList() {
        return insuredList;
    }

    public void setInsuredList(List<InsuredListBean> insuredList) {
        this.insuredList = insuredList;
    }

    public List<?> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<?> paymentList) {
        this.paymentList = paymentList;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public static class InsuredListBean {
        /**
         * benList : []
         * birthday : 2017-02-28
         * carInfo : {"cityName":"杭州","engineNo":"123123","fuelType":"","licenseNo":"浙12345","noLicenseFlag":0,"registerDate":"2017-02-21","specialCarDate":"2017-02-28","specialCarFlag":1,"vehicleFrameNo":"12345","vehicleModel":"宝马"}
         * detailId : 156
         * email :
         * gender : 男
         * idNo : 20170227192800743493
         * idType : 1
         * insureCount : 1
         * insuredId : 156
         * insuredName : 222
         * insuredNamePinyin : 222
         * mobile :
         * policyURL :
         * relation : 1
         */

        private String birthday;
        private CarInfoBean carInfo;
        private String detailId;
        private String email;
        private String gender;
        private String idNo;
        private String idType;
        private int insureCount;
        private String insuredId;
        private String insuredName;
        private String insuredNamePinyin;
        private String mobile;
        private String policyURL;
        private int relation;
        private List<?> benList;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public CarInfoBean getCarInfo() {
            return carInfo;
        }

        public void setCarInfo(CarInfoBean carInfo) {
            this.carInfo = carInfo;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
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

        public int getInsureCount() {
            return insureCount;
        }

        public void setInsureCount(int insureCount) {
            this.insureCount = insureCount;
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

        public String getInsuredNamePinyin() {
            return insuredNamePinyin;
        }

        public void setInsuredNamePinyin(String insuredNamePinyin) {
            this.insuredNamePinyin = insuredNamePinyin;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPolicyURL() {
            return policyURL;
        }

        public void setPolicyURL(String policyURL) {
            this.policyURL = policyURL;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public List<?> getBenList() {
            return benList;
        }

        public void setBenList(List<?> benList) {
            this.benList = benList;
        }

        public static class CarInfoBean {
            /**
             * cityName : 杭州
             * engineNo : 123123
             * fuelType :
             * licenseNo : 浙12345
             * noLicenseFlag : 0
             * registerDate : 2017-02-21
             * specialCarDate : 2017-02-28
             * specialCarFlag : 1
             * vehicleFrameNo : 12345
             * vehicleModel : 宝马
             */

            private String cityName;
            private String engineNo;
            private String fuelType;
            private String licenseNo;
            private int noLicenseFlag;
            private String registerDate;
            private String specialCarDate;
            private int specialCarFlag;
            private String vehicleFrameNo;
            private String vehicleModel;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getEngineNo() {
                return engineNo;
            }

            public void setEngineNo(String engineNo) {
                this.engineNo = engineNo;
            }

            public String getFuelType() {
                return fuelType;
            }

            public void setFuelType(String fuelType) {
                this.fuelType = fuelType;
            }

            public String getLicenseNo() {
                return licenseNo;
            }

            public void setLicenseNo(String licenseNo) {
                this.licenseNo = licenseNo;
            }

            public int getNoLicenseFlag() {
                return noLicenseFlag;
            }

            public void setNoLicenseFlag(int noLicenseFlag) {
                this.noLicenseFlag = noLicenseFlag;
            }

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getSpecialCarDate() {
                return specialCarDate;
            }

            public void setSpecialCarDate(String specialCarDate) {
                this.specialCarDate = specialCarDate;
            }

            public int getSpecialCarFlag() {
                return specialCarFlag;
            }

            public void setSpecialCarFlag(int specialCarFlag) {
                this.specialCarFlag = specialCarFlag;
            }

            public String getVehicleFrameNo() {
                return vehicleFrameNo;
            }

            public void setVehicleFrameNo(String vehicleFrameNo) {
                this.vehicleFrameNo = vehicleFrameNo;
            }

            public String getVehicleModel() {
                return vehicleModel;
            }

            public void setVehicleModel(String vehicleModel) {
                this.vehicleModel = vehicleModel;
            }
        }
    }

    public static class TypeListBean {
        /**
         * insureList : [{"amountUnit":"","desc":"","extraInsureList":[],"insureAmount":"600,000","insureName":"交强险"},{"amountUnit":"","desc":"","extraInsureList":[],"insureAmount":"600,000","insureName":"车船险"}]
         * sumInsureAmount : 0
         * typeId : 0
         * typeName : 强制保险
         */

        private String sumInsureAmount;
        private int typeId;
        private String typeName;
        private List<InsureListBean> insureList;

        public String getSumInsureAmount() {
            return sumInsureAmount;
        }

        public void setSumInsureAmount(String sumInsureAmount) {
            this.sumInsureAmount = sumInsureAmount;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public List<InsureListBean> getInsureList() {
            return insureList;
        }

        public void setInsureList(List<InsureListBean> insureList) {
            this.insureList = insureList;
        }

        public static class InsureListBean {
            /**
             * amountUnit :
             * desc :
             * extraInsureList : []
             * insureAmount : 600,000
             * insureName : 交强险
             */

            private String amountUnit;
            private String desc;
            private String insureAmount;
            private String insureName;
            private List<ExtraInsureBean> extraInsureList;

            public String getAmountUnit() {
                return amountUnit;
            }

            public void setAmountUnit(String amountUnit) {
                this.amountUnit = amountUnit;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getInsureAmount() {
                return insureAmount;
            }

            public void setInsureAmount(String insureAmount) {
                this.insureAmount = insureAmount;
            }

            public String getInsureName() {
                return insureName;
            }

            public void setInsureName(String insureName) {
                this.insureName = insureName;
            }

            public List<ExtraInsureBean> getExtraInsureList() {
                return extraInsureList;
            }

            public void setExtraInsureList(List<ExtraInsureBean> extraInsureList) {
                this.extraInsureList = extraInsureList;
            }

            public static class ExtraInsureBean {
//                "extraDesc": "",
//                        "extraInsureAmount": "600,000",
//                        "extraInsureName": "车辆不计免赔"

                private String extraDesc;
                private String extraInsureAmount;
                private String extraInsureName;

                public String getExtraDesc() {
                    return extraDesc;
                }

                public void setExtraDesc(String extraDesc) {
                    this.extraDesc = extraDesc;
                }

                public String getExtraInsureAmount() {
                    return extraInsureAmount;
                }

                public void setExtraInsureAmount(String extraInsureAmount) {
                    this.extraInsureAmount = extraInsureAmount;
                }

                public String getExtraInsureName() {
                    return extraInsureName;
                }

                public void setExtraInsureName(String extraInsureName) {
                    this.extraInsureName = extraInsureName;
                }
            }
        }
    }
}
