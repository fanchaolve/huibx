package com.bb.hbx.base.v;

import android.app.Activity;

import com.bb.hbx.base.m.BaseModel;
import com.bb.hbx.base.p.BasePresenter;
import com.bb.hbx.bean.PayDetail;
import com.bb.hbx.bean.TradeDetail;


import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface ConfimpaymentContract {


    interface Model extends BaseModel {


        void getPaySign(PayDetail detail, Callback callback);

        void verifyPayPwd(String userId, String payPwd, Callback callback);

        void getPaymentInfo(PayDetail detail, Callback callback);

    }


    interface View extends BaseView {

        void getverifyPayPwd(String payPwd);

        void showDialog();

        void dissDialog();
    }

    abstract class Presenter extends BasePresenter<ConfimpaymentContract.Model, ConfimpaymentContract.View> {

        public abstract void getPaySign(PayDetail detail);

        public abstract boolean isdialogshowisdialogshow();

        public abstract void verifyPayPwd(String payPwd);

        public abstract void setPayPassword(String payPassword);

        public abstract String getPayPassword();
        public abstract  void getPaymentInfo(String paymentId,String orderNo);

    }
}
