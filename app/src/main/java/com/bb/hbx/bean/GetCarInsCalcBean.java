package com.bb.hbx.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class GetCarInsCalcBean implements Parcelable{

    /**
     * output : {"benefitList":[{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"50000","chooseAmountName":"5万","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"","type":"商业险"}],"carExtras":"","ccsTax":"180.0","jqAmount":"122000.0","jqPreium":"950.0","syAmount":"345112.2","syPreium":"4099.11","totalAmount":"","totalPreium":"5229.11"}
     * respCode : 000000
     * respMsg :
     * success : true
     */

        /**
         * benefitList : [{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"50000","chooseAmountName":"5万","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"0","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"","type":"商业险"}]
         * carExtras :
         * ccsTax : 180.0
         * jqAmount : 122000.0
         * jqPreium : 950.0
         * syAmount : 345112.2
         * syPreium : 4099.11
         * totalAmount :
         * totalPreium : 5229.11
         */

        private String carExtras;
        private String ccsTax;
        private String jqAmount;
        private String jqPreium;
        private String syAmount;
        private String syPreium;
        private String totalAmount;
        private String totalPreium;
        private List<BenefitListBean> benefitList;

    protected GetCarInsCalcBean(Parcel in) {
        carExtras = in.readString();
        ccsTax = in.readString();
        jqAmount = in.readString();
        jqPreium = in.readString();
        syAmount = in.readString();
        syPreium = in.readString();
        totalAmount = in.readString();
        totalPreium = in.readString();
    }

    public static final Creator<GetCarInsCalcBean> CREATOR = new Creator<GetCarInsCalcBean>() {
        @Override
        public GetCarInsCalcBean createFromParcel(Parcel in) {
            return new GetCarInsCalcBean(in);
        }

        @Override
        public GetCarInsCalcBean[] newArray(int size) {
            return new GetCarInsCalcBean[size];
        }
    };

    public String getCarExtras() {
            return carExtras;
        }

        public void setCarExtras(String carExtras) {
            this.carExtras = carExtras;
        }

        public String getCcsTax() {
            return ccsTax;
        }

        public void setCcsTax(String ccsTax) {
            this.ccsTax = ccsTax;
        }

        public String getJqAmount() {
            return jqAmount;
        }

        public void setJqAmount(String jqAmount) {
            this.jqAmount = jqAmount;
        }

        public String getJqPreium() {
            return jqPreium;
        }

        public void setJqPreium(String jqPreium) {
            this.jqPreium = jqPreium;
        }

        public String getSyAmount() {
            return syAmount;
        }

        public void setSyAmount(String syAmount) {
            this.syAmount = syAmount;
        }

        public String getSyPreium() {
            return syPreium;
        }

        public void setSyPreium(String syPreium) {
            this.syPreium = syPreium;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getTotalPreium() {
            return totalPreium;
        }

        public void setTotalPreium(String totalPreium) {
            this.totalPreium = totalPreium;
        }

        public List<BenefitListBean> getBenefitList() {
            return benefitList;
        }

        public void setBenefitList(List<BenefitListBean> benefitList) {
            this.benefitList = benefitList;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carExtras);
        dest.writeString(ccsTax);
        dest.writeString(jqAmount);
        dest.writeString(jqPreium);
        dest.writeString(syAmount);
        dest.writeString(syPreium);
        dest.writeString(totalAmount);
        dest.writeString(totalPreium);
    }

    public static class BenefitListBean implements Parcelable{
            /**
             * amountList : []
             * chooseAmount : 1
             * chooseAmountName : 投保
             * franchiseFlag : 1
             * franchisePremium : 0
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
            private List<?> amountList;

        protected BenefitListBean(Parcel in) {
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

        public static final Creator<BenefitListBean> CREATOR = new Creator<BenefitListBean>() {
            @Override
            public BenefitListBean createFromParcel(Parcel in) {
                return new BenefitListBean(in);
            }

            @Override
            public BenefitListBean[] newArray(int size) {
                return new BenefitListBean[size];
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

            public List<?> getAmountList() {
                return amountList;
            }

            public void setAmountList(List<?> amountList) {
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
    }
}
