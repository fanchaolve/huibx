package com.bb.hbx.activitiy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.MessageCodeBean;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.widget.CountDownTextView;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 设置支付密码 页面*/
public class SetPayPwdActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.pwd_et)
    EditText pwd_et;
    @BindView(R.id.checkPwd_et)
    EditText checkPwd_et;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.checkCode_et)
    EditText checkCode_et;
    @BindView(R.id.getCode_tv)
    CountDownTextView getCode_tv;
    @BindView(R.id.verify_tv)
    TextView verify_tv;

    String smsCode="";
    String mobile="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_set_pay_pwd;
    }

    @Override
    public void initView() {
        mobile = MyApplication.user.getMobile();
        //使用正则匹配将电话号码的4-7位替换成*
        String displayPhoneNum = mobile.trim().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        phone_tv.setText(displayPhoneNum);
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        getCode_tv.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.getCode_tv:
                //Toast.makeText(this,"获取验证码",Toast.LENGTH_SHORT).show();
                getCode_tv.startTime();
//                String phone = phone_tv.getText().toString().trim();
//                if (!TextUtils.isEmpty(phone)&&phone.length()==11)
//                {
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(mContext,"输入的手机号码错误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                    ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                    Call call=service.getVerifyCode("1",mobile,"14");//14
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Result_Api body = (Result_Api) response.body();
                            if (body!=null)
                            {
                                MessageCodeBean bean = (MessageCodeBean) body.getOutput();
                                if (bean!=null)
                                {
                                    smsCode = bean.getSmsCode();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            showTip("获取验证码失败");
                        }
                    });
                /*}
                else
                {
                    showTip("请输入正确的手机号");
                }*/
                break;
            case R.id.verify_tv:
                //Toast.makeText(this,"确认!",Toast.LENGTH_SHORT).show();
                String pwd = pwd_et.getText().toString();
                String checkPwd = checkPwd_et.getText().toString();
                String checkCode = checkCode_et.getText().toString();
                //String mobile = phone_tv.getText().toString();
                if (!TextUtils.isEmpty(pwd)&&!TextUtils.isEmpty(checkPwd)&&!TextUtils.isEmpty(checkCode)/*&&!TextUtils.isEmpty(mobile)*/)
                {
                    if (pwd.equals(checkPwd))
                    {
                        /*if (checkCode.equals(smsCode))
                        {*/
                            ApiService serviceSetPayPwd = RetrofitFactory.getINSTANCE().create(ApiService.class);
                            Call callSetPayPwd=serviceSetPayPwd.setPayPwd(MyApplication.user.getUserId(),pwd,checkCode);
                            callSetPayPwd.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Result_Api body = (Result_Api) response.body();
                                    if (body!=null)
                                    {
                                        if (body.isSuccess())
                                        {
                                            //更新表数据
                                            SQLiteDatabase db = DatabaseImpl.getInstance().getReadableDatabase();
                                            db.execSQL("update userstb set paymentPwd=? where currentUser=currentUser ",
                                                    new String[]{"1"});
                                            db.close();
                                            MyApplication.user.setPaymentPwd("1");
//                                            Intent intent = new Intent(SetPayPwdActivity.this, WithdrawActivity.class);
//                                            startActivity(intent);
                                            finish();
                                        }
                                        showTip(body.getRespMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    showTip("设置密码失败");
                                }
                            });
                        /*}
                        else
                        {
                            showTip("输入验证码有误"+smsCode);
                        }*/
                    }
                    else
                    {
                        showTip("两次密码输入不一致,请重新输入");
                    }
                }
                else
                {
                    showTip("请填写信息");
                }
                break;
            default:
                break;
        }
    }
}
