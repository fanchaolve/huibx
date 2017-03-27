package com.bb.hbx.bean;

/**
 * Created by Administrator on 2017/3/27.
 * 积分签到状态的bean
 */

public class ScoreResultBean {

    /**
     * output : 再连续签到6次可额外获得50积分
     * respCode : 100000
     * respMsg : 当天只能签到一次
     * success : false
     */

    private String output;
    private String respCode;
    private String respMsg;
    private boolean success;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
