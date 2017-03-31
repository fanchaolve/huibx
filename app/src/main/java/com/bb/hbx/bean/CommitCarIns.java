package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CommitCarIns {

    /**
     * output : {"benefitList":[{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"2170.28","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"2170.28","type":"商业险"},{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"591.35","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"591.35","type":"商业险"},{"amountList":[],"chooseAmount":"50000","chooseAmountName":"5万","franchiseFlag":"1","franchisePremium":"639.35","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"639.35","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"38.95","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"38.95","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"98.8","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"98.8","type":"商业险"},{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"0","franchisePremium":"","itemCode":"J1","itemDesc":"","itemName":"交强险","pcode":"","pfcode":"","premium":"","type":"交强险"}],"ccsTax":"180.0","jqAmount":"122000.0","jqPreium":"950.0","msg":"","state":"0","syAmount":"345112.2","syPreium":"4099.11","totalAmount":"","totalPreium":"5229.11","tradeId":"20170331105217229652"}
     * respCode : 000000
     * respMsg :
     * success : true
     */

        /**
         * benefitList : [{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"2170.28","itemCode":"Z1","itemDesc":"","itemName":"机动车车损险","pcode":"","pfcode":"","premium":"2170.28","type":"商业险"},{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"1","franchisePremium":"591.35","itemCode":"Z2","itemDesc":"","itemName":"盗抢险","pcode":"","pfcode":"","premium":"591.35","type":"商业险"},{"amountList":[],"chooseAmount":"50000","chooseAmountName":"5万","franchiseFlag":"1","franchisePremium":"639.35","itemCode":"Z3","itemDesc":"","itemName":"第三者责任险","pcode":"","pfcode":"","premium":"639.35","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"38.95","itemCode":"Z4","itemDesc":"","itemName":"司机责任险","pcode":"","pfcode":"","premium":"38.95","type":"商业险"},{"amountList":[],"chooseAmount":"10000","chooseAmountName":"1万","franchiseFlag":"1","franchisePremium":"98.8","itemCode":"Z5","itemDesc":"","itemName":"乘客责任险","pcode":"","pfcode":"","premium":"98.8","type":"商业险"},{"amountList":[],"chooseAmount":"1","chooseAmountName":"投保","franchiseFlag":"0","franchisePremium":"","itemCode":"J1","itemDesc":"","itemName":"交强险","pcode":"","pfcode":"","premium":"","type":"交强险"}]
         * ccsTax : 180.0
         * jqAmount : 122000.0
         * jqPreium : 950.0
         * msg :
         * state : 0
         * syAmount : 345112.2
         * syPreium : 4099.11
         * totalAmount :
         * totalPreium : 5229.11
         * tradeId : 20170331105217229652
         */

        private String ccsTax;
        private String jqAmount;
        private String jqPreium;
        private String msg;
        private String state;
        private String syAmount;
        private String syPreium;
        private String totalAmount;
        private String totalPreium;
        private String tradeId;
        private List<BenefitListBean> benefitList;

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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

        public String getTradeId() {
            return tradeId;
        }

        public void setTradeId(String tradeId) {
            this.tradeId = tradeId;
        }

        public List<BenefitListBean> getBenefitList() {
            return benefitList;
        }

        public void setBenefitList(List<BenefitListBean> benefitList) {
            this.benefitList = benefitList;
        }

        public static class BenefitListBean {
            /**
             * amountList : []
             * chooseAmount : 1
             * chooseAmountName : 投保
             * franchiseFlag : 1
             * franchisePremium : 2170.28
             * itemCode : Z1
             * itemDesc :
             * itemName : 机动车车损险
             * pcode :
             * pfcode :
             * premium : 2170.28
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
        }

}
