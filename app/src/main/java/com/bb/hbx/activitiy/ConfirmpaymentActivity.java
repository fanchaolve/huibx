package com.bb.hbx.activitiy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.ConfimpaymentlModel;
import com.bb.hbx.base.p.ConfimpaymentPresenter;
import com.bb.hbx.base.v.ConfimpaymentContract;
import com.bb.hbx.bean.PayDetail;
import com.bb.hbx.observable.PriceObservable;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.utils.TimeUtils;
import com.bb.hbx.utils.Utils;
import com.bb.hbx.widget.BlanceDailog;
import com.bb.hbx.widget.FancyCountDownTextview;
import com.bb.hbx.widget.PasswordDailog;
import com.bb.hbx.widget.TextviewObserver;


import butterknife.BindView;

import static com.bb.hbx.R.drawable.on;


/**
 * 确认支付
 */

public class ConfirmpaymentActivity extends BaseActivity<ConfimpaymentPresenter, ConfimpaymentlModel>
        implements View.OnClickListener, ConfimpaymentContract.View {


    @BindView(R.id.tv_timedetail)
    FancyCountDownTextview tv_timedetail;//自定义时间倒计时

    @BindView(R.id.tv_price)
    TextView tv_price;//总支付的金额

    @BindView(R.id.tv_insuranceName)
    TextView tv_insuranceName;//险种名字

    @BindView(R.id.tv_redenvelopes)
    TextView tv_redenvelopes;//红包抵扣

    @BindView(R.id.tv_needprice)
    TextviewObserver tv_needprice;//还需支付的金额
    //
    @BindView(R.id.tv_confim)
    TextView tv_confim;//确认支付

    @BindView(R.id.tv_tip)
    TextView tv_tip;//确认支付
    //
    @BindView(R.id.tv_jf)
    TextView tv_jf;//积分

    @BindView(R.id.tv_yhq)
    TextView tv_yhq;//优惠券
    //

    @BindView(R.id.tv_ye)
    TextView tv_ye;//积分

    @BindView(R.id.iv_back)
    ImageView iv_back;//积分

    @BindView(R.id.tv_pay)
    TextviewObserver tv_pay;//合计

    @BindView(R.id.lin_hb)
    LinearLayout lin_hb;//合计
    //

    @BindView(R.id.ck_jf)
    CheckBox ck_jf;//积分
    //

    @BindView(R.id.ck_ye)
    CheckBox ck_ye;//余额

    @BindView(R.id.ck_yhq)
    CheckBox ck_yhq;//优惠券

    //
    @BindView(R.id.tv_save)
    TextviewObserver tv_save;//节省

    @BindView(R.id.rg_select)
    RadioGroup rg_select;//险种

    @BindView(R.id.lin_Insurance)
    LinearLayout lin_Insurance;//险种

    @BindView(R.id.lin_yhq)
    LinearLayout lin_yhq;

    private PayDetail detail;
    private PriceObservable priceObservable;

    private final static int REU_A = 0x111;

    private long timeoutLast;

    private PasswordDailog dailog;


    @Override
    public int getLayoutId() {
        return R.layout.activit_confirmpayment;
    }

    @Override
    public void initView() {

//        tv_timedetail.startTime();
    }

    @Override
    public void initListener() {
        lin_Insurance.setOnClickListener(this);
        tv_confim.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        lin_hb.setOnClickListener(this);
        ck_jf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    priceObservable.setPrice_jf(detail.getDeductible());
                } else {
                    priceObservable.setPrice_jf("0");
                }
            }
        });
        ck_ye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if ("1".equalsIgnoreCase(MyApplication.user.getPaymentPwd())) {
                        if (mPresenter.isdialogshowisdialogshow()) {
                            showDialog();
                        } else {
                            priceObservable.setPrice_ye(detail.getAcctBalanceYE());
                        }

                    } else {
                        BlanceDailog dailog = new BlanceDailog(mContext);
                        dailog.show();
                    }

                } else {
                    priceObservable.setPrice_ye("0");
                }
            }
        });


        ck_yhq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    priceObservable.setPrice_yhq(detail.getPayPrice());
                } else {
                    priceObservable.setPrice_yhq("0");
                }
            }
        });

        dailog = new PasswordDailog(mContext, ConfirmpaymentActivity.this);
        dailog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mPresenter.getPayPassword().isEmpty()) {
                    ck_ye.setChecked(false);
                }
            }
        });
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey("content")) {
                detail = (PayDetail) bundle.getSerializable("content");
            }
        }
        if (detail == null)
            return;

        priceObservable = new PriceObservable();
        priceObservable.addObserver(tv_needprice);
        priceObservable.addObserver(tv_pay);
        priceObservable.addObserver(tv_save);

        priceObservable.setAllPrice(detail.getPayPrice());

        tv_price.setText(getString(R.string.howPrice, Utils.fromFenToYuan(detail.getPayPrice())));
        tv_insuranceName.setText(detail.getProductName());
        tv_tip.setText(detail.getTips());
        tv_jf.setText(getString(R.string.format_jf, detail.getAcctBalanceJF(), Utils.fromFenToYuan(detail.getDeductible())));
        timeoutLast = TimeUtils.divlong(detail.getPayDeadline());//与当前时间差的长整形
        if (timeoutLast <= 0) {
            tv_timedetail.setText("订单已经失效");
        } else {
            tv_timedetail.setTime(timeoutLast);
            tv_timedetail.startTime();
        }

        tv_ye.setText(getString(R.string.format_ye, Utils.fromFenToYuan(detail.getAcctBalanceYE())));


        if (!detail.getCouponCode().isEmpty()) {
            lin_yhq.setVisibility(View.VISIBLE);
            tv_yhq.setText(detail.getCouponCode());
        } else {
            lin_yhq.setVisibility(View.GONE);
        }

        addPayLayout();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_Insurance:
                Bundle bundle = new Bundle();
                bundle.putString("tradeId", detail.getTradeId());
                AppManager.getInstance().showActivity(PolicydetailsActivity.class, bundle);
                break;
            case R.id.tv_confim:
                int id = rg_select.getCheckedRadioButtonId();
                PayDetail detail2 = new PayDetail();
                detail2.setAcctBalanceJF(ck_jf.isChecked() ? detail.getAcctBalanceJF() : 0);
                detail2.setUserId(MyApplication.user.getUserId());
                detail2.setAcctBalanceYE(ck_ye.isChecked() ? detail.getAcctBalanceYE() : "0");
                detail2.setCouponCode(detail.getCouponCode());
                detail2.setDeductible(detail.getDeductible());
                detail2.setPayDeadline(detail.getPayDeadline());
                detail2.setCouponList(detail.getCouponList());
                detail2.setPayments(detail.getPayments());
                detail2.setProductName(detail.getProductName());
                detail2.setTradeId(detail.getTradeId());
                detail2.setPayPrice(detail.getPayPrice());
                if (id == R.id.rg_zfbid) {
                    detail2.setPaymentId(Constants.ZFBPAY + "");
                    mPresenter.getPaySign(detail2);
                } else if (id == R.id.rg_wxid) {
                    detail2.setPaymentId(Constants.WXPAY + "");
                    mPresenter.getPaySign(detail2);
                } else if (id == R.id.rg_llid) {
                    detail2.setPaymentId(Constants.LLPAY + "");
                    mPresenter.getPaySign(detail2);
                } else {
                    showMsg("抱歉没有支付方式可以支付");
                }
                //mPresenter.getPaymentInfo("90","20170225132939065004");

                break;
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.lin_hb:
                AppManager.getInstance().showActivityForResult(RedPacketActivity.class, null, REU_A);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REU_A && resultCode == RESULT_OK) {

        }
    }

    //动态加载支付列表
    private void addPayLayout() {
        if (detail.getPayments() != null && detail.getPayments().size() > 0) {
            for (int i = 0; i < detail.getPayments().size(); i++) {
                RadioButton button = new RadioButton(mContext);
                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,
                        AppManager.getInstance().dp2px(mContext, 56));
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                button.setLayoutParams(layoutParams);
                button.setBackgroundResource(R.color.white);
                button.setChecked(false);
                button.setPadding((int) getResources().getDimension(R.dimen.x30), 0, 0, 0);
                button.setTextColor(getResources().getColor(R.color.A3));
                button.setTextSize(14);
                button.setButtonDrawable(R.drawable.radiobutton_bg);
                if (detail.getPayments().get(i).getPaymentId() == Constants.ZFBPAY) {
                    button.setText(getString(R.string.zfbzf));
                    button.setId(R.id.rg_zfbid);

                } else if (detail.getPayments().get(i).getPaymentId() == Constants.WXPAY) {
                    button.setText(getString(R.string.wxzf));
                    button.setId(R.id.rg_wxid);
                } else if (detail.getPayments().get(i).getPaymentId() == Constants.LLPAY) {
                    button.setText(getString(R.string.llzf));
                    button.setId(R.id.rg_llid);
                }

                rg_select.addView(button);
//                if (i != detail.getPayments().size() - 1) {
//                    View view = LayoutInflater.from(mContext).inflate(R.layout.line_e, null);
//                    rg_select.addView(view);
//                }

            }
        }
    }

    @Override
    public void getverifyPayPwd(String payPwd) {
        mPresenter.verifyPayPwd(payPwd);
    }

    @Override
    public void showDialog() {
        if (!dailog.isShowing()) {
            dailog.show();
        }
    }

    @Override
    public void dissDialog() {
        if (dailog == null) {
        } else if (dailog.isShowing()) {
            mPresenter.setPayPassword(dailog.getPassword());
            dailog.dismiss();
        }
    }


}
