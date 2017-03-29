package com.bb.hbx.bean;

import com.bb.hbx.widget.multitype.data.Item;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CarOrderConfirmTableBean implements Item{

    public String itemName;
    public String itemPreium;

    public CarOrderConfirmTableBean() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPreium() {
        return itemPreium;
    }

    public void setItemPreium(String itemPreium) {
        this.itemPreium = itemPreium;
    }
}
