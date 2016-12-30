package com.bb.hbx.activitiy;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.activitiy.login.LoginActivity;
import com.bb.hbx.activitiy.login.LoginContract;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.RegistModel;
import com.bb.hbx.base.p.RegistPresenter;
import com.bb.hbx.base.v.RegistContract;
import com.bb.hbx.interfaces.LoginTextWatcher;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.CountDownTextView;
import com.bb.hbx.widget.LoginPswEdit;
import com.bb.hbx.widget.LoginTelEdit;

import butterknife.BindView;


/**
 * Created by fancl on 2016/12/20.
 */

public class RegisteActivity extends BaseActivity<RegistPresenter, RegistModel> implements LoginContract.View, View.OnClickListener {


    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.et_phone)
    LoginTelEdit et_phone;

    @BindView(R.id.et_yzm)
    EditText et_yzm;

    @BindView(R.id.tv_getcode)
    CountDownTextView tv_getcode;

    @BindView(R.id.et_psw)
    LoginPswEdit et_psw;

    @BindView(R.id.ck_agree)
    CheckBox ck_agree;

    @BindView(R.id.tv_regist)
    TextView tv_regist;

    @BindView(R.id.tv_passwordlogin)
    TextView tv_passwordlogin;


    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        tv_getcode.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        tv_passwordlogin.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        et_phone.addTextChangedListener(new LoginTextWatcher(tv_regist, this));
        et_yzm.addTextChangedListener(new LoginTextWatcher(tv_regist, this));
        et_psw.addTextChangedListener(new LoginTextWatcher(tv_regist, this));
        ck_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isverTel() && isverCode() && isverpassword() && isChecked) {
                    tv_regist.setBackgroundResource(R.drawable.shape_btn_a1);
                } else {
                    tv_regist.setBackgroundResource(R.drawable.shape_btn_a6);
                }
            }
        });
    }

    @Override
    public void initdata() {

    }

    @Override
    public void showMsg(String msg) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getcode:
                if (isverTel()) {
                    tv_getcode.startTime();
                } else
                    showTip("请输入正确的手机号码");
                break;

            case R.id.tv_passwordlogin:
                AppManager.getInstance().showActivity(PwdLoginActivity.class, null);
                break;
            case R.id.tv_regist:
                if (!isverTel()) {
                    showTip("手机号码不正确");
                    return;
                }
                if (!isverCode()) {
                    showTip("验证码不正确");
                    return;
                }

                if (!isverpassword()) {
                    showTip("密码不正确");
                    return;
                }
                mPresenter.regist(et_phone.getText().toString().trim(), et_psw.getText().toString().trim(),
                        et_yzm.getText().toString().trim());
                break;

            case R.id.back_iv:
                finish();
                break;

        }
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public boolean isverTel() {
        if (!TextUtils.isEmpty(et_phone.getText()) && et_phone.getText().toString().trim().length() == 11) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isverCode() {
        if (!TextUtils.isEmpty(tv_getcode.getText())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isverpassword() {
        if (!TextUtils.isEmpty(et_psw.getText()) && et_psw.getText().toString().trim().length() >= 6
                && et_psw.getText().toString().trim().length() <= 20) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCheckbx() {
        return ck_agree.isChecked();
    }


}
