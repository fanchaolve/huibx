package com.bb.hbx.activitiy;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yintong.secure.c.ae.j.bo;

public class AddBankCardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.nameInfo_iv)
    ImageView nameInfo_iv;
    @BindView(R.id.name_et)
    EditText name_et;
    @BindView(R.id.idCard_et)
    EditText idCard_et;
    @BindView(R.id.bankCard_et)
    EditText bankCard_et;

    @BindView(R.id.verify_tv)
    TextView verify_tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        nameInfo_iv.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.nameInfo_iv:
                Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
                break;
            case R.id.verify_tv:

                String name = name_et.getText().toString();
                String idCard = idCard_et.getText().toString();
                String bankCard = bankCard_et.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(idCard) && !TextUtils.isEmpty(bankCard)) {

                    ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                    Call call = service.bindingBankCard(MyApplication.user.getUserId(), name, idCard, bankCard);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Result_Api body = (Result_Api) response.body();
                            boolean success = body.isSuccess();
                            String paymentPwd = MyApplication.user.getPaymentPwd();
                            if (success) {
                                if ("1".equals(paymentPwd))//已经设置过支付密码
                                {
                                    Intent intent = new Intent(AddBankCardActivity.this, WithdrawActivity.class);
                               /* BindingBankCard bindingBean = (BindingBankCard) body.getOutput();
                                String bankName = bindingBean.getBankName();
                                String lastDigits = bindingBean.getLastDigits();
                                String cardType = bindingBean.getCardType();
                                intent.putExtra("bankName",bankName);
                                intent.putExtra("lastDigits",lastDigits);
                                intent.putExtra("cardType",cardType);*/
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(AddBankCardActivity.this, SetPayPwdActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                showTip(body.getRespMsg() + "!");
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                } else {
                    showTip("请核对信息!");
                }
                break;
            default:
                break;
        }
    }

}
