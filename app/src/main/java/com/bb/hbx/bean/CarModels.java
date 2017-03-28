package com.bb.hbx.bean;

import com.bb.hbx.widget.multitype.data.Item;

/**
 * Created by Administrator on 2017/3/1.
 */

public class CarModels implements Item {

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

    public CarModels(String carExtras, String carPrice, String configName, String displacement, String modelClass, String modelCode, String modelType, String releaseYear, String seats, String stalls) {
        this.carExtras = carExtras;
        this.carPrice = carPrice;
        this.configName = configName;
        this.displacement = displacement;
        this.modelClass = modelClass;
        this.modelCode = modelCode;
        this.modelType = modelType;
        this.releaseYear = releaseYear;
        this.seats = seats;
        this.stalls = stalls;
    }

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
}
