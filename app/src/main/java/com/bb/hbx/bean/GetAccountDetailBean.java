package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class GetAccountDetailBean {

    /**
     * accountDetailList : [{"acctBalance":0,"acctFreezing":0,"acctId":223,"acctName":"","acctSum":0,"logId":129,"logTime":"2017-03-25 17:28:07","tradeAddr":"积分签到","tradeAmount":100,"tradeDesc":"积分签到","tradeId":"0","tradeType":10,"userLogo":"","userNickname":""}]
     * acctBalance : 100
     * pageIndex : 1
     * pageSize : 10
     * totalCount : 89
     */

    private int acctBalance;                //账户余额
    private String pageIndex;               //当前页记录数
    private String pageSize;                //当前页数
    private String totalCount;              //总记录数
    private List<AccountDetailListBean> accountDetailList;

    public int getAcctBalance() {
        return acctBalance;
    }

    public void setAcctBalance(int acctBalance) {
        this.acctBalance = acctBalance;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<AccountDetailListBean> getAccountDetailList() {
        return accountDetailList;
    }

    public void setAccountDetailList(List<AccountDetailListBean> accountDetailList) {
        this.accountDetailList = accountDetailList;
    }

    public static class AccountDetailListBean {
        /**
         * acctBalance : 0
         * acctFreezing : 0
         * acctId : 223
         * acctName :
         * acctSum : 0
         * logId : 129
         * logTime : 2017-03-25 17:28:07
         * tradeAddr : 积分签到
         * tradeAmount : 100
         * tradeDesc : 积分签到
         * tradeId : 0
         * tradeType : 10
         * userLogo :
         * userNickname :
         */

        private int acctBalance;
        private int acctFreezing;
        private int acctId;
        private String acctName;
        private int acctSum;
        private int logId;
        private String logTime;
        private String tradeAddr;
        private int tradeAmount;
        private String tradeDesc;
        private String tradeId;
        private int tradeType;
        private String userLogo;
        private String userNickname;

        public int getAcctBalance() {
            return acctBalance;
        }

        public void setAcctBalance(int acctBalance) {
            this.acctBalance = acctBalance;
        }

        public int getAcctFreezing() {
            return acctFreezing;
        }

        public void setAcctFreezing(int acctFreezing) {
            this.acctFreezing = acctFreezing;
        }

        public int getAcctId() {
            return acctId;
        }

        public void setAcctId(int acctId) {
            this.acctId = acctId;
        }

        public String getAcctName() {
            return acctName;
        }

        public void setAcctName(String acctName) {
            this.acctName = acctName;
        }

        public int getAcctSum() {
            return acctSum;
        }

        public void setAcctSum(int acctSum) {
            this.acctSum = acctSum;
        }

        public int getLogId() {
            return logId;
        }

        public void setLogId(int logId) {
            this.logId = logId;
        }

        public String getLogTime() {
            return logTime;
        }

        public void setLogTime(String logTime) {
            this.logTime = logTime;
        }

        public String getTradeAddr() {
            return tradeAddr;
        }

        public void setTradeAddr(String tradeAddr) {
            this.tradeAddr = tradeAddr;
        }

        public int getTradeAmount() {
            return tradeAmount;
        }

        public void setTradeAmount(int tradeAmount) {
            this.tradeAmount = tradeAmount;
        }

        public String getTradeDesc() {
            return tradeDesc;
        }

        public void setTradeDesc(String tradeDesc) {
            this.tradeDesc = tradeDesc;
        }

        public String getTradeId() {
            return tradeId;
        }

        public void setTradeId(String tradeId) {
            this.tradeId = tradeId;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
            this.tradeType = tradeType;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }
    }
}
