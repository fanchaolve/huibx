package com.bb.hbx.activitiy;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.login.LoginContract;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.MessageCodeBean;
import com.bb.hbx.interfaces.LoginTextWatcher;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.CountDownTextView;
import com.bb.hbx.widget.LoginPswEdit;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//修改登录密码
public class FixPwdActivity extends BaseActivity implements View.OnClickListener,LoginContract.View{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.checkCode_et)
    EditText checkCode_et;
    @BindView(R.id.getcode_tv)
    CountDownTextView getcode_tv;
    @BindView(R.id.pwd_et)
    LoginPswEdit pwd_et;
    @BindView(R.id.warn_tv)
    TextView warn_tv;
    @BindView(R.id.verify_tv)
    TextView verify_tv;

    String mobile;
    String smsCode;
    @Override
    public int getLayoutId() {
        return R.layout.activity_fix_pwd;
    }

    @Override
    public void initView() {
        pwd_et.setHintString("新密码(6-20位数字、字母组合)");
        //pwd_et.invalidate();
        mobile = MyApplication.user.getMobile();

        //使用正则匹配将电话号码的4-7位替换成*
        String displayPhoneNum = mobile.trim().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        warn_tv.setText("验证码将发送已绑定手机号"+displayPhoneNum);
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        getcode_tv.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
        checkCode_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        pwd_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
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
            case R.id.getcode_tv:
                getcode_tv.startTime();
                ApiService serviceCode = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call callCode=serviceCode.getVerifyCode("1",mobile,"12");
                callCode.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api body = (Result_Api) response.body();
                        if (body!=null)
                        {
                            MessageCodeBean beanCode = (MessageCodeBean) body.getOutput();
                            smsCode = beanCode.getSmsCode();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            case R.id.verify_tv:
                //showTip("确认");
                String code = checkCode_et.getText().toString();
                String pwd = pwd_et.getText().toString();
                //!TextUtils.isEmpty(code)&&!TextUtils.isEmpty(pwd)
                if (isverTel()&&isverpassword())
                {
                    if (code.equals(smsCode))
                    {
                        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                        Call call=service.resetLoginPwd(MyApplication.user.getUserId(),pwd,"2",code);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Result_Api body = (Result_Api) response.body();
                                if (body!=null)
                                {
                                    showTip(body.getRespMsg());
                                    if (body.isSuccess())
                                    {
                                        finish();
                                        AppManager.getInstance().finishParticularActivity(CountSecurityActivity.class);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                    }
                    else
                    {
                        showTip("输入验证码有误");
                    }
                }
                else
                {
                    showTip("请核对输入信息");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public boolean isverTel() {
        if (!TextUtils.isEmpty(checkCode_et.getText()) && checkCode_et.getText().toString().trim().length() == 4) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isverCode() {
        return true;
    }

    @Override
    public boolean isverpassword() {
        if (!TextUtils.isEmpty(pwd_et.getText()) && pwd_et.getText().toString().trim().length() >= 6
                && pwd_et.getText().toString().trim().length() <= 20) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCheckbx() {
        return true;
    }
}
