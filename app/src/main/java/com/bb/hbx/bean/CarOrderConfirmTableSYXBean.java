package com.bb.hbx.bean;

import com.bb.hbx.widget.multitype.data.Item;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CarOrderConfirmTableSYXBean implements Item{

    public String itemName;
    public String itemBaoE;
    public String itemBaoF;
    public String itemBuJiMiam;

    public CarOrderConfirmTableSYXBean() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBaoE() {
        return itemBaoE;
    }

    public void setItemBaoE(String itemBaoE) {
        this.itemBaoE = itemBaoE;
    }

    public String getItemBaoF() {
        return itemBaoF;
    }

    public void setItemBaoF(String itemBaoF) {
        this.itemBaoF = itemBaoF;
    }

    public String getItemBuJiMiam() {
        return itemBuJiMiam;
    }

    public void setItemBuJiMiam(String itemBuJiMiam) {
        this.itemBuJiMiam = itemBuJiMiam;
    }
}
