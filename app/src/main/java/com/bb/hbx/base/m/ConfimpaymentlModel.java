package com.bb.hbx.base.m;


import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.v.ConfimpaymentContract;
import com.bb.hbx.base.v.PolicydetailsContract;
import com.bb.hbx.bean.PayDetail;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/2/23.
 */

public class ConfimpaymentlModel implements ConfimpaymentContract.Model {


    private ApiService service;


    public ConfimpaymentlModel() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void getPaySign(PayDetail detail, Callback callback) {
        Call call = service.getPaySign(detail);
        call.enqueue(callback);
    }

    @Override
    public void verifyPayPwd(String userId, String payPwd, Callback callback) {
        Call call = service.verifyPayPwd(userId, payPwd);
        call.enqueue(callback);
    }

    @Override
    public void getPaymentInfo(PayDetail detail, Callback callback) {
        Call call = service.getPaymentInfo(detail);
        call.enqueue(callback);
    }
}
