package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class Entry {

    private String name;
    private String code;

    private List<String> option;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }
}
