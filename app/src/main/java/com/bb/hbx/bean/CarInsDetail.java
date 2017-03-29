package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class CarInsDetail {

    private String serialId;
    private String modelCode;
    private String carPrice;
    private String carExtras;
    private List<BenefitInCarIns> benefitList;

    public CarInsDetail(String serialId, String modelCode, String carPrice, String carExtras, List<BenefitInCarIns> list) {
        this.serialId = serialId;
        this.modelCode = modelCode;
        this.carPrice = carPrice;
        this.carExtras = carExtras;
        this.benefitList = list;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarExtras() {
        return carExtras;
    }

    public void setCarExtras(String carExtras) {
        this.carExtras = carExtras;
    }

    public List<BenefitInCarIns> getList() {
        return benefitList;
    }

    public void setList(List<BenefitInCarIns> list) {
        this.benefitList = list;
    }

    public static class BenefitInCarIns {

        private String itemCode;
        private String franchiseFlag;
        private String chooseAmount;

        public BenefitInCarIns(String itemCode, String franchiseFlag, String chooseAmount) {
            this.itemCode = itemCode;
            this.franchiseFlag = franchiseFlag;
            this.chooseAmount = chooseAmount;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getFranchiseFlag() {
            return franchiseFlag;
        }

        public void setFranchiseFlag(String franchiseFlag) {
            this.franchiseFlag = franchiseFlag;
        }

        public String getChooseAmount() {
            return chooseAmount;
        }

        public void setChooseAmount(String chooseAmount) {
            this.chooseAmount = chooseAmount;
        }
    }
}
