package com.bb.hbx.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class CarInsDetail implements Parcelable{

    private String serialId;
    private String modelCode;
    private String carPrice;
    private String carExtras;
    private List<BenefitInCarIns> benefitList;

    public CarInsDetail() {
    }

    public CarInsDetail(String serialId, String modelCode, String carPrice, String carExtras, List<BenefitInCarIns> list) {
        this.serialId = serialId;
        this.modelCode = modelCode;
        this.carPrice = carPrice;
        this.carExtras = carExtras;
        this.benefitList = list;
    }

    protected CarInsDetail(Parcel in) {
        serialId = in.readString();
        modelCode = in.readString();
        carPrice = in.readString();
        carExtras = in.readString();
    }

    public static final Creator<CarInsDetail> CREATOR = new Creator<CarInsDetail>() {
        @Override
        public CarInsDetail createFromParcel(Parcel in) {
            return new CarInsDetail(in);
        }

        @Override
        public CarInsDetail[] newArray(int size) {
            return new CarInsDetail[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(serialId);
        dest.writeString(modelCode);
        dest.writeString(carPrice);
        dest.writeString(carExtras);
    }

    public static class BenefitInCarIns implements Parcelable{

        private String itemCode;
        private String franchiseFlag;
        private String chooseAmount;

        public BenefitInCarIns(String itemCode, String franchiseFlag, String chooseAmount) {
            this.itemCode = itemCode;
            this.franchiseFlag = franchiseFlag;
            this.chooseAmount = chooseAmount;
        }

        protected BenefitInCarIns(Parcel in) {
            itemCode = in.readString();
            franchiseFlag = in.readString();
            chooseAmount = in.readString();
        }

        public static final Creator<BenefitInCarIns> CREATOR = new Creator<BenefitInCarIns>() {
            @Override
            public BenefitInCarIns createFromParcel(Parcel in) {
                return new BenefitInCarIns(in);
            }

            @Override
            public BenefitInCarIns[] newArray(int size) {
                return new BenefitInCarIns[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(itemCode);
            dest.writeString(franchiseFlag);
            dest.writeString(chooseAmount);
        }
    }
}
