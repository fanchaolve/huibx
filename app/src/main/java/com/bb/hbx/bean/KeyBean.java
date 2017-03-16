package com.bb.hbx.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/16.
 */

public class KeyBean extends ArrayList<String> {


    public KeyBean() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!this.isEmpty()) {

            if (this.size() == 1) {
                builder.append(this.get(0) + ";");
            } else if (this.size() == 2) {
                builder.append(this.get(0) + ";");
                if (this.get(1).indexOf("_") > -1) {
                    builder.append(this.get(1).substring(0, this.get(1).indexOf("_")) + ";");
                } else
                    builder.append(this.get(1) + ";");
            } else {
                for (int i = 0; i < this.size(); i++) {
                    if (i == 0) {
                        builder.append(this.get(i) + ";");
                    } else if (i >= this.size() - 2) {
                        builder.append(this.get(i) + ";");
                    } else {
                        builder.append(this.get(i) + ",");
                    }
                }
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}
