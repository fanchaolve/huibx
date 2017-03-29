package com.bb.hbx.activitiy;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.GetBankCardList;
import com.bb.hbx.interfaces.PointTextWatcher;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.LoginTelEdit;
import com.bb.hbx.widget.PasswordDailog;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by fancl on 2017/1/9.
 * 提现
 */

public class WithdrawActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.menu_iv)
    ImageView menu_iv;

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.iv_bankicon)
    ImageView iv_bankicon;

    @BindView(R.id.tv_banktitle)
    TextView tv_banktitle;

    @BindView(R.id.tv_bankstatus)
    TextView tv_bankstatus;

    @BindView(R.id.tv_cardType)
    TextView tv_cardType;
    @BindView(R.id.tv_cardNo)
    TextView tv_cardNo;

    @BindView(R.id.tv_withdraw)
    TextView tv_withdraw;

    @BindView(R.id.et_price)
    LoginTelEdit et_price;

    @BindView(R.id.tv_getAllMoney)
    TextView tv_getAllMoney;

    @BindView(R.id.tv_other_detail)
    TextView tv_other_detail;

    private int acctBalanceInt = 0;
    String bankName = "";
    String lastDigits = "";
    String cardType = "";

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.menu_iv:

                break;
            case R.id.tv_bankstatus:
                Intent intent = new Intent(this, MyBankCardActivity.class);
                /*intent.putExtra("bankName",bankName);
                intent.putExtra("lastDigits",lastDigits);
                intent.putExtra("cardType",cardType);*/
                startActivity(intent);
                break;
            case R.id.tv_withdraw:
                final String price = et_price.getText().toString().trim();
                float getCashNum = Float.parseFloat(price);
                float canGetMaxCash = (float) acctBalanceInt / 100;
//                Log.d("tttttt", "-------------------------------" + canGetMaxCash);
                if (!TextUtils.isEmpty(price)) {
                    if (getCashNum > canGetMaxCash) {
                        showTip("您的余额不足！");
                        return;
                    }
                    final PasswordDailog dialog = new PasswordDailog(this);
                    dialog.setListener(new PasswordDailog.GetPasswordListener() {
                        @Override
                        public void getPassword(String password) {
                            ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                            Call call = service.verifyPayPwd(MyApplication.user.getUserId(), password);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Result_Api body = (Result_Api) response.body();

                                    if (body.isSuccess()) {
                                        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                                        Call callCash = service.applyCash(MyApplication.user.getUserId(), price);
                                        callCash.enqueue(new Callback() {
                                            @Override
                                            public void onResponse(Call call, Response response) {
                                                Result_Api body = (Result_Api) response.body();
                                                if (body != null) {
                                                    if (body.isSuccess()) {
                                                        showTip("提现成功,3-5个工作日到账");
                                                        AppManager.getInstance().finishParticularActivity(AddBankCardActivity.class);
                                                    } else {
                                                        showTip(body.getRespMsg());
                                                    }
                                                }
                                                dialog.dismiss();
                                            }

                                            @Override
                                            public void onFailure(Call call, Throwable t) {
                                                showTip("提现失败");
                                            }
                                        });
                                    } else {
                                        Toast.makeText(mContext, "支付密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {

                                }
                            });
                        }
                    });
                    dialog.show();
                }
                break;
            case R.id.tv_getAllMoney:
                et_price.setText((acctBalanceInt / 100) + "." + (acctBalanceInt / 10 % 10) + (acctBalanceInt % 10));
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        acctBalanceInt = intent.getIntExtra("acctBalanceInt", 0);
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getBankCardList(MyApplication.user.getUserId());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body != null) {
                    GetBankCardList cardBean = (GetBankCardList) body.getOutput();
                    if (cardBean != null) {
                        String bankName = cardBean.getBankName();
                        String lastDigits = cardBean.getLastDigits();
                        String cardType = cardBean.getCardType();
                        tv_banktitle.setText(bankName);
                        tv_cardType.setText(cardType);
                        tv_cardNo.setText(lastDigits);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
        /*tv_banktitle.setText(bankName);
        tv_cardNo.setText(lastDigits);
        tv_cardType.setText(cardType);*/

    }

    @Override
    public void initListener() {
        menu_iv.setOnClickListener(this);
        tv_withdraw.setOnClickListener(this);
        back_layout.setOnClickListener(this);
        tv_bankstatus.setOnClickListener(this);
        tv_getAllMoney.setOnClickListener(this);
        et_price.addTextChangedListener(new PointTextWatcher(et_price, tv_withdraw));
    }

    @Override
    public void initdata() {
        setWithdrawCount();
    }

    /**
     * 设置可提现次数
     */
    private void setWithdrawCount() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getWithdrawCount(MyApplication.user.getUserId());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api api = (Result_Api) response.body();
                if (api != null) {
                    String count = (String) api.getOutput();
                    tv_other_detail.setText("可提现" + ((acctBalanceInt / 100) + "." + (acctBalanceInt / 10 % 10) + (acctBalanceInt % 10))
                            + "元      " + "本月还可以免费提现" + count + "次");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
