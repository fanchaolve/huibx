package com.bb.hbx.base.p;

import com.bb.hbx.MyApplication;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.ConfimpaymentContract;
import com.bb.hbx.bean.PayDetail;
import com.bb.hbx.bean.PaySign;
import com.bb.hbx.interfaces.Pay;
import com.bb.hbx.pay.PayFactory;
import com.bb.hbx.pay.alipay.Alipay;
import com.bb.hbx.pay.llianlianpay.LianlianPay;
import com.bb.hbx.pay.llianlianpay.utils.PayOrder;
import com.bb.hbx.utils.Constants;

/**
 * Created by fancl.
 */

public class ConfimpaymentPresenter extends ConfimpaymentContract.Presenter {


    private PostCallback postCallback;

    private String payPassword = "";

    private PayFactory payFactory;

    private Pay pay;


    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPayPassword() {
        return payPassword;
    }

    @Override
    public void getPaymentInfo(String paymentId, String orderNo) {
       
    }

    @Override
    public void onAttached() {
        payFactory = new PayFactory();

        postCallback = new PostCallback<ConfimpaymentContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {

                if (api.getOutput() instanceof PaySign) {
                    PaySign paySign = (PaySign) api.getOutput();
                    pay = new Alipay();
                    payFactory.setPay(pay);
                    payFactory.factory(paySign);


                } else if (api.getOutput() instanceof PayOrder) {
                    PayOrder payorder = (PayOrder) api.getOutput();
                    pay = new LianlianPay();
                    payFactory.setPay(pay);
                    payFactory.factory(payorder);
                } else {//
                    mView.dissDialog();
                }


            }

            @Override
            public void failCallback() {

            }
        };


    }


    @Override
    public void getPaySign(PayDetail detail) {
        if (Integer.parseInt(detail.getPaymentId()) == Constants.LLPAY) {
            mModel.getPaymentInfo(detail, postCallback);
        } else if (Integer.parseInt(detail.getPaymentId()) == Constants.ZFBPAY) {
            mModel.getPaySign(detail, postCallback);
        }
    }

    @Override
    public boolean isdialogshowisdialogshow() {
        return payPassword.isEmpty() ? true : false;
    }

    @Override
    public void verifyPayPwd(String payPwd) {
        mModel.verifyPayPwd(MyApplication.user.getUserId(), payPwd, postCallback);
    }


}
