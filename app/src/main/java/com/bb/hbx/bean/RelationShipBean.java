package com.bb.hbx.bean;

/**
 * Created by Administrator on 2017/3/27.
 */

public class RelationShipBean {

    String code;
    String name;

    public RelationShipBean(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
