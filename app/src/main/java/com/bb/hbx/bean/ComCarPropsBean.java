package com.bb.hbx.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.bb.hbx.widget.multitype.data.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class ComCarPropsBean implements Parcelable{

    /**
     * output : {"carInfoList":[{"carExtras":"","carPrice":"124800.0","configName":"","displacement":"2350.0","modelClass":"奇瑞","modelCode":"0","modelType":"奇瑞SQR7246轿车","releaseYear":"2005","seats":"5.0","stalls":"手动档 豪华Ⅱ型 四驱"},{"carExtras":"","carPrice":"111111.0","configName":"","displacement":"2350.0","modelClass":"奇瑞","modelCode":"1","modelType":"奇瑞SQR7246轿车","releaseYear":"2005","seats":"5.0","stalls":"手动档 标准II型 四驱"},{"carExtras":"","carPrice":"114800.0","configName":"","displacement":"2350.0","modelClass":"奇瑞","modelCode":"2","modelType":"奇瑞SQR7246轿车","releaseYear":"2005","seats":"5.0","stalls":"手动档 舒适Ⅱ型 四驱"}],"extraJson":"{\n  \"resultDTO\" : {\n    \"resultCode\" : \"SUCCESS\",\n    \"resultMess\" : \"统一平台调用成功\"\n  },\n  \"dealFlag\" : \"1\",\n  \"dealMessage\" : \"车型查询成功！\",\n  \"total\" : \"0\",\n  \"checkNo\" : null,\n  \"checkCode\" : null,\n  \"checkFlag\" : null,\n  \"vehicleModelList\" : [ {\n    \"brandName\" : \"奇瑞SQR7246轿车\",\n    \"vehicleStyleDesc\" : \"手动档 豪华Ⅱ型 四驱\",\n    \"seatCount\" : \"5.0\",\n    \"purchasePrice\" : \"124800.0\",\n    \"importFlag\" : \"国产车\",\n    \"marketDate\" : \"2005\",\n    \"vehicleTonnage\" : \"0.0\",\n    \"exhaustCapacity\" : \"2350.0\",\n    \"vehicleWeight\" : \"1475.0\",\n    \"rbcode\" : \"QRD1079SQQ\",\n    \"actualValue\" : \"122553.6\",\n    \"carName\" : \"奇瑞SQR7246 豪华Ⅱ型\",\n    \"hyModelCode\" : \"BQRARJUD0008\",\n    \"noticeType\" : \"SQR7246\",\n    \"ecdemicVehicleFlag\" : \"0\",\n    \"vehicleJingyouDto\" : {\n      \"vehicleCode\" : \"QRD1079SQQ\",\n      \"vehicleName\" : \"奇瑞SQR7246轿车\",\n      \"brandName\" : \"奇瑞\",\n      \"price\" : \"124800.0\",\n      \"familyName\" : \"瑞虎\",\n      \"priceType\" : \"01\"\n    }\n  }, {\n    \"brandName\" : \"奇瑞SQR7246轿车\",\n    \"vehicleStyleDesc\" : \"手动档 标准II型 四驱\",\n    \"seatCount\" : \"5.0\",\n    \"purchasePrice\" : \"111111.0\",\n    \"importFlag\" : \"国产车\",\n    \"marketDate\" : \"2005\",\n    \"vehicleTonnage\" : \"0.0\",\n    \"exhaustCapacity\" : \"2350.0\",\n    \"vehicleWeight\" : \"1475.0\",\n    \"rbcode\" : \"QRD1115SQQ\",\n    \"actualValue\" : \"109111.0\",\n    \"carName\" : \"奇瑞SQR7246 标准II型\",\n    \"hyModelCode\" : \"BQRARJUB0001\",\n    \"noticeType\" : \"SQR7246\",\n    \"ecdemicVehicleFlag\" : \"0\",\n    \"vehicleJingyouDto\" : {\n      \"vehicleCode\" : \"QRD1115SQQ\",\n      \"vehicleName\" : \"奇瑞SQR7246轿车\",\n      \"brandName\" : \"奇瑞\",\n      \"price\" : \"111111.0\",\n      \"familyName\" : \"瑞虎\",\n      \"priceType\" : \"01\"\n    }\n  }, {\n    \"brandName\" : \"奇瑞SQR7246轿车\",\n    \"vehicleStyleDesc\" : \"手动档 舒适Ⅱ型 四驱\",\n    \"seatCount\" : \"5.0\",\n    \"purchasePrice\" : \"114800.0\",\n    \"importFlag\" : \"国产车\",\n    \"marketDate\" : \"2005\",\n    \"vehicleTonnage\" : \"0.0\",\n    \"exhaustCapacity\" : \"2350.0\",\n    \"vehicleWeight\" : \"1475.0\",\n    \"rbcode\" : \"QRD1078SQQ\",\n    \"actualValue\" : \"112733.6\",\n    \"carName\" : \"奇瑞SQR7246 舒适Ⅱ型\",\n    \"hyModelCode\" : \"BQRARJUC0008\",\n    \"noticeType\" : \"SQR7246\",\n    \"ecdemicVehicleFlag\" : \"0\",\n    \"vehicleJingyouDto\" : {\n      \"vehicleCode\" : \"QRD1078SQQ\",\n      \"vehicleName\" : \"奇瑞SQR7246轿车\",\n      \"brandName\" : \"奇瑞\",\n      \"price\" : \"114800.0\",\n      \"familyName\" : \"瑞虎\",\n      \"priceType\" : \"01\"\n    }\n  } ],\n  \"tmbVehicleInfo\" : null\n}","extractCarProps":[],"planList":[{"fjxList":[{"amountList":[{"amountCode":"5000","amountName":"5000"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"},{"amountCode":"2000","amountName":"2000"}],"chooseAmount":"2000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F1","itemDesc":"","itemName":"划痕险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"2","amountName":"进口玻璃"},{"amountCode":"1","amountName":"国产玻璃"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"2","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F2","itemDesc":"","itemName":"玻碎险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F3","itemDesc":"","itemName":"自燃损失险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F4","itemDesc":"","itemName":"涉水险","pcode":"","pfcode":"","premium":"","type":"附加险"}],"jqxList":[{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"J1","itemDesc":"","itemName":"交强险","pcode":"","pfcode":"","premium":"","type":"交强险"}],"pkgDesc":"","pkgName":"全家桶1","qtxList":[{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F5","itemDesc":"","itemName":"指定专修厂险","pcode":"","pfcode":"","premium":"","type":"其它附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F6","itemDesc":"","itemName":"第三者逃逸险","pcode":"","pfcode":"","premium":"","type":"其它附加险"}],"syxList":[{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"50000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"30000","amountName":"3万"},{"amountCode":"40000","amountName":"4万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"10000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"30000","amountName":"3万"},{"amountCode":"40000","amountName":"4万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"10000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"","type":"商业险"}]}],"state":""}
     * respCode : 000000
     * respMsg :
     * success : true
     */

        /**
         * carInfoList : [{"carExtras":"","carPrice":"124800.0","configName":"","displacement":"2350.0","modelClass":"奇瑞","modelCode":"0","modelType":"奇瑞SQR7246轿车","releaseYear":"2005","seats":"5.0","stalls":"手动档 豪华Ⅱ型 四驱"},{"carExtras":"","carPrice":"111111.0","configName":"","displacement":"2350.0","modelClass":"奇瑞","modelCode":"1","modelType":"奇瑞SQR7246轿车","releaseYear":"2005","seats":"5.0","stalls":"手动档 标准II型 四驱"},{"carExtras":"","carPrice":"114800.0","configName":"","displacement":"2350.0","modelClass":"奇瑞","modelCode":"2","modelType":"奇瑞SQR7246轿车","releaseYear":"2005","seats":"5.0","stalls":"手动档 舒适Ⅱ型 四驱"}]
         * extraJson : {
         "resultDTO" : {
         "resultCode" : "SUCCESS",
         "resultMess" : "统一平台调用成功"
         },
         "dealFlag" : "1",
         "dealMessage" : "车型查询成功！",
         "total" : "0",
         "checkNo" : null,
         "checkCode" : null,
         "checkFlag" : null,
         "vehicleModelList" : [ {
         "brandName" : "奇瑞SQR7246轿车",
         "vehicleStyleDesc" : "手动档 豪华Ⅱ型 四驱",
         "seatCount" : "5.0",
         "purchasePrice" : "124800.0",
         "importFlag" : "国产车",
         "marketDate" : "2005",
         "vehicleTonnage" : "0.0",
         "exhaustCapacity" : "2350.0",
         "vehicleWeight" : "1475.0",
         "rbcode" : "QRD1079SQQ",
         "actualValue" : "122553.6",
         "carName" : "奇瑞SQR7246 豪华Ⅱ型",
         "hyModelCode" : "BQRARJUD0008",
         "noticeType" : "SQR7246",
         "ecdemicVehicleFlag" : "0",
         "vehicleJingyouDto" : {
         "vehicleCode" : "QRD1079SQQ",
         "vehicleName" : "奇瑞SQR7246轿车",
         "brandName" : "奇瑞",
         "price" : "124800.0",
         "familyName" : "瑞虎",
         "priceType" : "01"
         }
         }, {
         "brandName" : "奇瑞SQR7246轿车",
         "vehicleStyleDesc" : "手动档 标准II型 四驱",
         "seatCount" : "5.0",
         "purchasePrice" : "111111.0",
         "importFlag" : "国产车",
         "marketDate" : "2005",
         "vehicleTonnage" : "0.0",
         "exhaustCapacity" : "2350.0",
         "vehicleWeight" : "1475.0",
         "rbcode" : "QRD1115SQQ",
         "actualValue" : "109111.0",
         "carName" : "奇瑞SQR7246 标准II型",
         "hyModelCode" : "BQRARJUB0001",
         "noticeType" : "SQR7246",
         "ecdemicVehicleFlag" : "0",
         "vehicleJingyouDto" : {
         "vehicleCode" : "QRD1115SQQ",
         "vehicleName" : "奇瑞SQR7246轿车",
         "brandName" : "奇瑞",
         "price" : "111111.0",
         "familyName" : "瑞虎",
         "priceType" : "01"
         }
         }, {
         "brandName" : "奇瑞SQR7246轿车",
         "vehicleStyleDesc" : "手动档 舒适Ⅱ型 四驱",
         "seatCount" : "5.0",
         "purchasePrice" : "114800.0",
         "importFlag" : "国产车",
         "marketDate" : "2005",
         "vehicleTonnage" : "0.0",
         "exhaustCapacity" : "2350.0",
         "vehicleWeight" : "1475.0",
         "rbcode" : "QRD1078SQQ",
         "actualValue" : "112733.6",
         "carName" : "奇瑞SQR7246 舒适Ⅱ型",
         "hyModelCode" : "BQRARJUC0008",
         "noticeType" : "SQR7246",
         "ecdemicVehicleFlag" : "0",
         "vehicleJingyouDto" : {
         "vehicleCode" : "QRD1078SQQ",
         "vehicleName" : "奇瑞SQR7246轿车",
         "brandName" : "奇瑞",
         "price" : "114800.0",
         "familyName" : "瑞虎",
         "priceType" : "01"
         }
         } ],
         "tmbVehicleInfo" : null
         }
         * extractCarProps : []
         * planList : [{"fjxList":[{"amountList":[{"amountCode":"5000","amountName":"5000"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"},{"amountCode":"2000","amountName":"2000"}],"chooseAmount":"2000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F1","itemDesc":"","itemName":"划痕险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"2","amountName":"进口玻璃"},{"amountCode":"1","amountName":"国产玻璃"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"2","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F2","itemDesc":"","itemName":"玻碎险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F3","itemDesc":"","itemName":"自燃损失险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F4","itemDesc":"","itemName":"涉水险","pcode":"","pfcode":"","premium":"","type":"附加险"}],"jqxList":[{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"J1","itemDesc":"","itemName":"交强险","pcode":"","pfcode":"","premium":"","type":"交强险"}],"pkgDesc":"","pkgName":"全家桶1","qtxList":[{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F5","itemDesc":"","itemName":"指定专修厂险","pcode":"","pfcode":"","premium":"","type":"其它附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F6","itemDesc":"","itemName":"第三者逃逸险","pcode":"","pfcode":"","premium":"","type":"其它附加险"}],"syxList":[{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"50000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"30000","amountName":"3万"},{"amountCode":"40000","amountName":"4万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"10000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"30000","amountName":"3万"},{"amountCode":"40000","amountName":"4万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"10000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"","type":"商业险"}]}]
         * state :
         */

        private String extraJson;
        private String state;
        private List<CarInfoListBean> carInfoList;
        private List<?> extractCarProps;
        private List<PlanListBean> planList;

    protected ComCarPropsBean(Parcel in) {
        extraJson = in.readString();
        state = in.readString();
    }

    public static final Creator<ComCarPropsBean> CREATOR = new Creator<ComCarPropsBean>() {
        @Override
        public ComCarPropsBean createFromParcel(Parcel in) {
            return new ComCarPropsBean(in);
        }

        @Override
        public ComCarPropsBean[] newArray(int size) {
            return new ComCarPropsBean[size];
        }
    };

    public String getExtraJson() {
            return extraJson;
        }

        public void setExtraJson(String extraJson) {
            this.extraJson = extraJson;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<CarInfoListBean> getCarInfoList() {
            return carInfoList;
        }

        public void setCarInfoList(List<CarInfoListBean> carInfoList) {
            this.carInfoList = carInfoList;
        }

        public List<?> getExtractCarProps() {
            return extractCarProps;
        }

        public void setExtractCarProps(List<?> extractCarProps) {
            this.extractCarProps = extractCarProps;
        }

        public List<PlanListBean> getPlanList() {
            return planList;
        }

        public void setPlanList(List<PlanListBean> planList) {
            this.planList = planList;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(extraJson);
        dest.writeString(state);
    }

    public static class CarInfoListBean implements Parcelable,Item{
            /**
             * carExtras :
             * carPrice : 124800.0
             * configName :
             * displacement : 2350.0
             * modelClass : 奇瑞
             * modelCode : 0
             * modelType : 奇瑞SQR7246轿车
             * releaseYear : 2005
             * seats : 5.0
             * stalls : 手动档 豪华Ⅱ型 四驱
             */

            private String carExtras;
            private String carPrice;
            private String configName;
            private String displacement;
            private String modelClass;
            private String modelCode;
            private String modelType;
            private String releaseYear;
            private String seats;
            private String stalls;

        protected CarInfoListBean(Parcel in) {
            carExtras = in.readString();
            carPrice = in.readString();
            configName = in.readString();
            displacement = in.readString();
            modelClass = in.readString();
            modelCode = in.readString();
            modelType = in.readString();
            releaseYear = in.readString();
            seats = in.readString();
            stalls = in.readString();
        }

        public static final Creator<CarInfoListBean> CREATOR = new Creator<CarInfoListBean>() {
            @Override
            public CarInfoListBean createFromParcel(Parcel in) {
                return new CarInfoListBean(in);
            }

            @Override
            public CarInfoListBean[] newArray(int size) {
                return new CarInfoListBean[size];
            }
        };

        public String getCarExtras() {
                return carExtras;
            }

            public void setCarExtras(String carExtras) {
                this.carExtras = carExtras;
            }

            public String getCarPrice() {
                return carPrice;
            }

            public void setCarPrice(String carPrice) {
                this.carPrice = carPrice;
            }

            public String getConfigName() {
                return configName;
            }

            public void setConfigName(String configName) {
                this.configName = configName;
            }

            public String getDisplacement() {
                return displacement;
            }

            public void setDisplacement(String displacement) {
                this.displacement = displacement;
            }

            public String getModelClass() {
                return modelClass;
            }

            public void setModelClass(String modelClass) {
                this.modelClass = modelClass;
            }

            public String getModelCode() {
                return modelCode;
            }

            public void setModelCode(String modelCode) {
                this.modelCode = modelCode;
            }

            public String getModelType() {
                return modelType;
            }

            public void setModelType(String modelType) {
                this.modelType = modelType;
            }

            public String getReleaseYear() {
                return releaseYear;
            }

            public void setReleaseYear(String releaseYear) {
                this.releaseYear = releaseYear;
            }

            public String getSeats() {
                return seats;
            }

            public void setSeats(String seats) {
                this.seats = seats;
            }

            public String getStalls() {
                return stalls;
            }

            public void setStalls(String stalls) {
                this.stalls = stalls;
            }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(carExtras);
            dest.writeString(carPrice);
            dest.writeString(configName);
            dest.writeString(displacement);
            dest.writeString(modelClass);
            dest.writeString(modelCode);
            dest.writeString(modelType);
            dest.writeString(releaseYear);
            dest.writeString(seats);
            dest.writeString(stalls);
        }
    }

        public static class PlanListBean implements Parcelable,Item{
            public PlanListBean() {
            }

            /**
             * fjxList : [{"amountList":[{"amountCode":"5000","amountName":"5000"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"},{"amountCode":"2000","amountName":"2000"}],"chooseAmount":"2000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F1","itemDesc":"","itemName":"划痕险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"2","amountName":"进口玻璃"},{"amountCode":"1","amountName":"国产玻璃"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"2","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F2","itemDesc":"","itemName":"玻碎险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F3","itemDesc":"","itemName":"自燃损失险","pcode":"","pfcode":"","premium":"","type":"附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"F4","itemDesc":"","itemName":"涉水险","pcode":"","pfcode":"","premium":"","type":"附加险"}]
             * jqxList : [{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"J1","itemDesc":"","itemName":"交强险","pcode":"","pfcode":"","premium":"","type":"交强险"}]
             * pkgDesc :
             * pkgName : 全家桶1
             * qtxList : [{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F5","itemDesc":"","itemName":"指定专修厂险","pcode":"","pfcode":"","premium":"","type":"其它附加险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"-1","franchisePremium":"","itemCode":"F6","itemDesc":"","itemName":"第三者逃逸险","pcode":"","pfcode":"","premium":"","type":"其它附加险"}]
             * syxList : [{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"1","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"50000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"30000","amountName":"3万"},{"amountCode":"40000","amountName":"4万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"10000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[{"amountCode":"150000","amountName":"15万"},{"amountCode":"100000","amountName":"10万"},{"amountCode":"50000","amountName":"5万"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"500000","amountName":"50万"},{"amountCode":"30000","amountName":"3万"},{"amountCode":"40000","amountName":"4万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"}],"chooseAmount":"10000","chooseAmountName":"","franchiseFlag":"1","franchisePremium":"","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"","type":"商业险"}]
             */


            private String pkgDesc;
            private String pkgName;
            private List<FjxListBean> fjxList;
            private List<JqxListBean> jqxList;
            private List<QtxListBean> qtxList;
            private List<SyxListBean> syxList;

            protected PlanListBean(Parcel in) {
                pkgDesc = in.readString();
                pkgName = in.readString();
            }

            public static final Creator<PlanListBean> CREATOR = new Creator<PlanListBean>() {
                @Override
                public PlanListBean createFromParcel(Parcel in) {
                    return new PlanListBean(in);
                }

                @Override
                public PlanListBean[] newArray(int size) {
                    return new PlanListBean[size];
                }
            };

            public String getPkgDesc() {
                return pkgDesc;
            }

            public void setPkgDesc(String pkgDesc) {
                this.pkgDesc = pkgDesc;
            }

            public String getPkgName() {
                return pkgName;
            }

            public void setPkgName(String pkgName) {
                this.pkgName = pkgName;
            }

            public List<FjxListBean> getFjxList() {
                return fjxList;
            }

            public void setFjxList(List<FjxListBean> fjxList) {
                this.fjxList = fjxList;
            }

            public List<JqxListBean> getJqxList() {
                return jqxList;
            }

            public void setJqxList(List<JqxListBean> jqxList) {
                this.jqxList = jqxList;
            }

            public List<QtxListBean> getQtxList() {
                return qtxList;
            }

            public void setQtxList(List<QtxListBean> qtxList) {
                this.qtxList = qtxList;
            }

            public List<SyxListBean> getSyxList() {
                return syxList;
            }

            public void setSyxList(List<SyxListBean> syxList) {
                this.syxList = syxList;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(pkgDesc);
                dest.writeString(pkgName);
            }

            public static class FjxListBean implements Parcelable{
                /**
                 * amountList : [{"amountCode":"5000","amountName":"5000"},{"amountCode":"20000","amountName":"2万"},{"amountCode":"10000","amountName":"1万"},{"amountCode":"0","amountName":"不投保"},{"amountCode":"2000","amountName":"2000"}]
                 * chooseAmount : 2000
                 * chooseAmountName :
                 * franchiseFlag : 1
                 * franchisePremium :
                 * itemCode : F1
                 * itemDesc :
                 * itemName : 划痕险
                 * pcode :
                 * pfcode :
                 * premium :
                 * type : 附加险
                 */

                private String chooseAmount;
                private String chooseAmountName;
                private String franchiseFlag;
                private String franchisePremium;
                private String itemCode;
                private String itemDesc;
                private String itemName;
                private String pcode;
                private String pfcode;
                private String premium;
                private String type;
                private List<AmountListBean> amountList;

                protected FjxListBean(Parcel in) {
                    chooseAmount = in.readString();
                    chooseAmountName = in.readString();
                    franchiseFlag = in.readString();
                    franchisePremium = in.readString();
                    itemCode = in.readString();
                    itemDesc = in.readString();
                    itemName = in.readString();
                    pcode = in.readString();
                    pfcode = in.readString();
                    premium = in.readString();
                    type = in.readString();
                }

                public static final Creator<FjxListBean> CREATOR = new Creator<FjxListBean>() {
                    @Override
                    public FjxListBean createFromParcel(Parcel in) {
                        return new FjxListBean(in);
                    }

                    @Override
                    public FjxListBean[] newArray(int size) {
                        return new FjxListBean[size];
                    }
                };

                public String getChooseAmount() {
                    return chooseAmount;
                }

                public void setChooseAmount(String chooseAmount) {
                    this.chooseAmount = chooseAmount;
                }

                public String getChooseAmountName() {
                    return chooseAmountName;
                }

                public void setChooseAmountName(String chooseAmountName) {
                    this.chooseAmountName = chooseAmountName;
                }

                public String getFranchiseFlag() {
                    return franchiseFlag;
                }

                public void setFranchiseFlag(String franchiseFlag) {
                    this.franchiseFlag = franchiseFlag;
                }

                public String getFranchisePremium() {
                    return franchisePremium;
                }

                public void setFranchisePremium(String franchisePremium) {
                    this.franchisePremium = franchisePremium;
                }

                public String getItemCode() {
                    return itemCode;
                }

                public void setItemCode(String itemCode) {
                    this.itemCode = itemCode;
                }

                public String getItemDesc() {
                    return itemDesc;
                }

                public void setItemDesc(String itemDesc) {
                    this.itemDesc = itemDesc;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getPcode() {
                    return pcode;
                }

                public void setPcode(String pcode) {
                    this.pcode = pcode;
                }

                public String getPfcode() {
                    return pfcode;
                }

                public void setPfcode(String pfcode) {
                    this.pfcode = pfcode;
                }

                public String getPremium() {
                    return premium;
                }

                public void setPremium(String premium) {
                    this.premium = premium;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<AmountListBean> getAmountList() {
                    return amountList;
                }

                public void setAmountList(List<AmountListBean> amountList) {
                    this.amountList = amountList;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(chooseAmount);
                    dest.writeString(chooseAmountName);
                    dest.writeString(franchiseFlag);
                    dest.writeString(franchisePremium);
                    dest.writeString(itemCode);
                    dest.writeString(itemDesc);
                    dest.writeString(itemName);
                    dest.writeString(pcode);
                    dest.writeString(pfcode);
                    dest.writeString(premium);
                    dest.writeString(type);
                }

                public static class AmountListBean implements Parcelable{
                    /**
                     * amountCode : 5000
                     * amountName : 5000
                     */

                    private String amountCode;
                    private String amountName;

                    protected AmountListBean(Parcel in) {
                        amountCode = in.readString();
                        amountName = in.readString();
                    }

                    public static final Creator<AmountListBean> CREATOR = new Creator<AmountListBean>() {
                        @Override
                        public AmountListBean createFromParcel(Parcel in) {
                            return new AmountListBean(in);
                        }

                        @Override
                        public AmountListBean[] newArray(int size) {
                            return new AmountListBean[size];
                        }
                    };

                    public String getAmountCode() {
                        return amountCode;
                    }

                    public void setAmountCode(String amountCode) {
                        this.amountCode = amountCode;
                    }

                    public String getAmountName() {
                        return amountName;
                    }

                    public void setAmountName(String amountName) {
                        this.amountName = amountName;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(amountCode);
                        dest.writeString(amountName);
                    }
                }
            }

            public static class JqxListBean implements Parcelable{
                /**
                 * amountList : [{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}]
                 * chooseAmount : 1
                 * chooseAmountName :
                 * franchiseFlag : -1
                 * franchisePremium :
                 * itemCode : J1
                 * itemDesc :
                 * itemName : 交强险
                 * pcode :
                 * pfcode :
                 * premium :
                 * type : 交强险
                 */

                private String chooseAmount;
                private String chooseAmountName;
                private String franchiseFlag;
                private String franchisePremium;
                private String itemCode;
                private String itemDesc;
                private String itemName;
                private String pcode;
                private String pfcode;
                private String premium;
                private String type;
                private List<AmountListBeanX> amountList;

                protected JqxListBean(Parcel in) {
                    chooseAmount = in.readString();
                    chooseAmountName = in.readString();
                    franchiseFlag = in.readString();
                    franchisePremium = in.readString();
                    itemCode = in.readString();
                    itemDesc = in.readString();
                    itemName = in.readString();
                    pcode = in.readString();
                    pfcode = in.readString();
                    premium = in.readString();
                    type = in.readString();
                }

                public static final Creator<JqxListBean> CREATOR = new Creator<JqxListBean>() {
                    @Override
                    public JqxListBean createFromParcel(Parcel in) {
                        return new JqxListBean(in);
                    }

                    @Override
                    public JqxListBean[] newArray(int size) {
                        return new JqxListBean[size];
                    }
                };

                public String getChooseAmount() {
                    return chooseAmount;
                }

                public void setChooseAmount(String chooseAmount) {
                    this.chooseAmount = chooseAmount;
                }

                public String getChooseAmountName() {
                    return chooseAmountName;
                }

                public void setChooseAmountName(String chooseAmountName) {
                    this.chooseAmountName = chooseAmountName;
                }

                public String getFranchiseFlag() {
                    return franchiseFlag;
                }

                public void setFranchiseFlag(String franchiseFlag) {
                    this.franchiseFlag = franchiseFlag;
                }

                public String getFranchisePremium() {
                    return franchisePremium;
                }

                public void setFranchisePremium(String franchisePremium) {
                    this.franchisePremium = franchisePremium;
                }

                public String getItemCode() {
                    return itemCode;
                }

                public void setItemCode(String itemCode) {
                    this.itemCode = itemCode;
                }

                public String getItemDesc() {
                    return itemDesc;
                }

                public void setItemDesc(String itemDesc) {
                    this.itemDesc = itemDesc;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getPcode() {
                    return pcode;
                }

                public void setPcode(String pcode) {
                    this.pcode = pcode;
                }

                public String getPfcode() {
                    return pfcode;
                }

                public void setPfcode(String pfcode) {
                    this.pfcode = pfcode;
                }

                public String getPremium() {
                    return premium;
                }

                public void setPremium(String premium) {
                    this.premium = premium;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<AmountListBeanX> getAmountList() {
                    return amountList;
                }

                public void setAmountList(List<AmountListBeanX> amountList) {
                    this.amountList = amountList;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(chooseAmount);
                    dest.writeString(chooseAmountName);
                    dest.writeString(franchiseFlag);
                    dest.writeString(franchisePremium);
                    dest.writeString(itemCode);
                    dest.writeString(itemDesc);
                    dest.writeString(itemName);
                    dest.writeString(pcode);
                    dest.writeString(pfcode);
                    dest.writeString(premium);
                    dest.writeString(type);
                }

                public static class AmountListBeanX implements Parcelable{
                    /**
                     * amountCode : 1
                     * amountName : 投保
                     */

                    private String amountCode;
                    private String amountName;

                    protected AmountListBeanX(Parcel in) {
                        amountCode = in.readString();
                        amountName = in.readString();
                    }

                    public static final Creator<AmountListBeanX> CREATOR = new Creator<AmountListBeanX>() {
                        @Override
                        public AmountListBeanX createFromParcel(Parcel in) {
                            return new AmountListBeanX(in);
                        }

                        @Override
                        public AmountListBeanX[] newArray(int size) {
                            return new AmountListBeanX[size];
                        }
                    };

                    public String getAmountCode() {
                        return amountCode;
                    }

                    public void setAmountCode(String amountCode) {
                        this.amountCode = amountCode;
                    }

                    public String getAmountName() {
                        return amountName;
                    }

                    public void setAmountName(String amountName) {
                        this.amountName = amountName;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(amountCode);
                        dest.writeString(amountName);
                    }
                }
            }

            public static class QtxListBean implements Parcelable{
                /**
                 * amountList : [{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}]
                 * chooseAmount : 1
                 * chooseAmountName :
                 * franchiseFlag : -1
                 * franchisePremium :
                 * itemCode : F5
                 * itemDesc :
                 * itemName : 指定专修厂险
                 * pcode :
                 * pfcode :
                 * premium :
                 * type : 其它附加险
                 */

                private String chooseAmount;
                private String chooseAmountName;
                private String franchiseFlag;
                private String franchisePremium;
                private String itemCode;
                private String itemDesc;
                private String itemName;
                private String pcode;
                private String pfcode;
                private String premium;
                private String type;
                private List<AmountListBeanXX> amountList;

                protected QtxListBean(Parcel in) {
                    chooseAmount = in.readString();
                    chooseAmountName = in.readString();
                    franchiseFlag = in.readString();
                    franchisePremium = in.readString();
                    itemCode = in.readString();
                    itemDesc = in.readString();
                    itemName = in.readString();
                    pcode = in.readString();
                    pfcode = in.readString();
                    premium = in.readString();
                    type = in.readString();
                }

                public static final Creator<QtxListBean> CREATOR = new Creator<QtxListBean>() {
                    @Override
                    public QtxListBean createFromParcel(Parcel in) {
                        return new QtxListBean(in);
                    }

                    @Override
                    public QtxListBean[] newArray(int size) {
                        return new QtxListBean[size];
                    }
                };

                public String getChooseAmount() {
                    return chooseAmount;
                }

                public void setChooseAmount(String chooseAmount) {
                    this.chooseAmount = chooseAmount;
                }

                public String getChooseAmountName() {
                    return chooseAmountName;
                }

                public void setChooseAmountName(String chooseAmountName) {
                    this.chooseAmountName = chooseAmountName;
                }

                public String getFranchiseFlag() {
                    return franchiseFlag;
                }

                public void setFranchiseFlag(String franchiseFlag) {
                    this.franchiseFlag = franchiseFlag;
                }

                public String getFranchisePremium() {
                    return franchisePremium;
                }

                public void setFranchisePremium(String franchisePremium) {
                    this.franchisePremium = franchisePremium;
                }

                public String getItemCode() {
                    return itemCode;
                }

                public void setItemCode(String itemCode) {
                    this.itemCode = itemCode;
                }

                public String getItemDesc() {
                    return itemDesc;
                }

                public void setItemDesc(String itemDesc) {
                    this.itemDesc = itemDesc;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getPcode() {
                    return pcode;
                }

                public void setPcode(String pcode) {
                    this.pcode = pcode;
                }

                public String getPfcode() {
                    return pfcode;
                }

                public void setPfcode(String pfcode) {
                    this.pfcode = pfcode;
                }

                public String getPremium() {
                    return premium;
                }

                public void setPremium(String premium) {
                    this.premium = premium;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<AmountListBeanXX> getAmountList() {
                    return amountList;
                }

                public void setAmountList(List<AmountListBeanXX> amountList) {
                    this.amountList = amountList;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(chooseAmount);
                    dest.writeString(chooseAmountName);
                    dest.writeString(franchiseFlag);
                    dest.writeString(franchisePremium);
                    dest.writeString(itemCode);
                    dest.writeString(itemDesc);
                    dest.writeString(itemName);
                    dest.writeString(pcode);
                    dest.writeString(pfcode);
                    dest.writeString(premium);
                    dest.writeString(type);
                }

                public static class AmountListBeanXX implements Parcelable{
                    /**
                     * amountCode : 1
                     * amountName : 投保
                     */

                    private String amountCode;
                    private String amountName;

                    protected AmountListBeanXX(Parcel in) {
                        amountCode = in.readString();
                        amountName = in.readString();
                    }

                    public static final Creator<AmountListBeanXX> CREATOR = new Creator<AmountListBeanXX>() {
                        @Override
                        public AmountListBeanXX createFromParcel(Parcel in) {
                            return new AmountListBeanXX(in);
                        }

                        @Override
                        public AmountListBeanXX[] newArray(int size) {
                            return new AmountListBeanXX[size];
                        }
                    };

                    public String getAmountCode() {
                        return amountCode;
                    }

                    public void setAmountCode(String amountCode) {
                        this.amountCode = amountCode;
                    }

                    public String getAmountName() {
                        return amountName;
                    }

                    public void setAmountName(String amountName) {
                        this.amountName = amountName;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(amountCode);
                        dest.writeString(amountName);
                    }
                }
            }

            public static class SyxListBean implements Parcelable,Item{
                /**
                 * amountList : [{"amountCode":"1","amountName":"投保"},{"amountCode":"0","amountName":"不投保"}]
                 * chooseAmount : 1
                 * chooseAmountName :
                 * franchiseFlag : 1
                 * franchisePremium :
                 * itemCode : Z1
                 * itemDesc :
                 * itemName : 机动车车损险
                 * pcode :
                 * pfcode :
                 * premium :
                 * type : 商业险
                 */

                private String chooseAmount;
                private String chooseAmountName;
                private String franchiseFlag;
                private String franchisePremium;
                private String itemCode;
                private String itemDesc;
                private String itemName;
                private String pcode;
                private String pfcode;
                private String premium;
                private String type;
                private List<AmountListBeanXXX> amountList;

                protected SyxListBean(Parcel in) {
                    chooseAmount = in.readString();
                    chooseAmountName = in.readString();
                    franchiseFlag = in.readString();
                    franchisePremium = in.readString();
                    itemCode = in.readString();
                    itemDesc = in.readString();
                    itemName = in.readString();
                    pcode = in.readString();
                    pfcode = in.readString();
                    premium = in.readString();
                    type = in.readString();
                }

                public static final Creator<SyxListBean> CREATOR = new Creator<SyxListBean>() {
                    @Override
                    public SyxListBean createFromParcel(Parcel in) {
                        return new SyxListBean(in);
                    }

                    @Override
                    public SyxListBean[] newArray(int size) {
                        return new SyxListBean[size];
                    }
                };

                public String getChooseAmount() {
                    return chooseAmount;
                }

                public void setChooseAmount(String chooseAmount) {
                    this.chooseAmount = chooseAmount;
                }

                public String getChooseAmountName() {
                    return chooseAmountName;
                }

                public void setChooseAmountName(String chooseAmountName) {
                    this.chooseAmountName = chooseAmountName;
                }

                public String getFranchiseFlag() {
                    return franchiseFlag;
                }

                public void setFranchiseFlag(String franchiseFlag) {
                    this.franchiseFlag = franchiseFlag;
                }

                public String getFranchisePremium() {
                    return franchisePremium;
                }

                public void setFranchisePremium(String franchisePremium) {
                    this.franchisePremium = franchisePremium;
                }

                public String getItemCode() {
                    return itemCode;
                }

                public void setItemCode(String itemCode) {
                    this.itemCode = itemCode;
                }

                public String getItemDesc() {
                    return itemDesc;
                }

                public void setItemDesc(String itemDesc) {
                    this.itemDesc = itemDesc;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getPcode() {
                    return pcode;
                }

                public void setPcode(String pcode) {
                    this.pcode = pcode;
                }

                public String getPfcode() {
                    return pfcode;
                }

                public void setPfcode(String pfcode) {
                    this.pfcode = pfcode;
                }

                public String getPremium() {
                    return premium;
                }

                public void setPremium(String premium) {
                    this.premium = premium;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<AmountListBeanXXX> getAmountList() {
                    return amountList;
                }

                public void setAmountList(List<AmountListBeanXXX> amountList) {
                    this.amountList = amountList;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(chooseAmount);
                    dest.writeString(chooseAmountName);
                    dest.writeString(franchiseFlag);
                    dest.writeString(franchisePremium);
                    dest.writeString(itemCode);
                    dest.writeString(itemDesc);
                    dest.writeString(itemName);
                    dest.writeString(pcode);
                    dest.writeString(pfcode);
                    dest.writeString(premium);
                    dest.writeString(type);
                }

                public static class AmountListBeanXXX implements Parcelable{
                    /**
                     * amountCode : 1
                     * amountName : 投保
                     */

                    private String amountCode;
                    private String amountName;

                    protected AmountListBeanXXX(Parcel in) {
                        amountCode = in.readString();
                        amountName = in.readString();
                    }

                    public static final Creator<AmountListBeanXXX> CREATOR = new Creator<AmountListBeanXXX>() {
                        @Override
                        public AmountListBeanXXX createFromParcel(Parcel in) {
                            return new AmountListBeanXXX(in);
                        }

                        @Override
                        public AmountListBeanXXX[] newArray(int size) {
                            return new AmountListBeanXXX[size];
                        }
                    };

                    public String getAmountCode() {
                        return amountCode;
                    }

                    public void setAmountCode(String amountCode) {
                        this.amountCode = amountCode;
                    }

                    public String getAmountName() {
                        return amountName;
                    }

                    public void setAmountName(String amountName) {
                        this.amountName = amountName;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(amountCode);
                        dest.writeString(amountName);
                    }
                }
            }
        }
}
