package com.bb.hbx.activitiy.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.R;
import com.bb.hbx.activitiy.HomeActivity;
import com.bb.hbx.activitiy.PwdLoginActivity;
import com.bb.hbx.activitiy.RegisteActivity;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.LoginTelEdit;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 登录
 * Created by fancl
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View, View.OnClickListener,View.OnLayoutChangeListener {

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @BindView(R.id.phone_layout)
    RelativeLayout phone_layout;
    @BindView(R.id.pwd_layout)
    RelativeLayout pwd_layout;
    @BindView(R.id.agree_layout)
    LinearLayout agree_layout;
    @BindView(R.id.login_bt)
    Button login_bt;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.logoIcon_iv)
    ImageView logoIcon_iv;
    @BindView(R.id.logoText_iv)
    ImageView logoText_iv;
    @BindView(R.id.phone_et)
    LoginTelEdit phone_et;
    @BindView(R.id.pwd_et)
    EditText pwd_et;
    @BindView(R.id.tv_getcode)
    TextView tv_getcode;
    @BindView(R.id.agree_iv)
    ImageView agree_iv;
    @BindView(R.id.tv_regist)
    TextView tv_regist;
    @BindView(R.id.tv_passwordlogin)
    TextView tv_passwordlogin;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        activityRootView = findViewById(R.id.activity_smss_login);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom,
                               int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

            Toast.makeText(LoginActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
            startAnimator();

        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){

            Toast.makeText(LoginActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            resetAnimator();
        }
    }

    @Override
    public void initListener() {

        //agree_iv.setSelected(true);
        tv_getcode.setOnClickListener(this);
        login_bt.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        tv_passwordlogin.setOnClickListener(this);
        tv_regist.setOnClickListener(this);


        phone_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    phone_et.setCompoundDrawables(null, null, null, null);
                } else {
                    phone_et.setCompoundDrawables(null, null, phone_et.getEdit_endDrawable(), null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void initdata() {

    }


    @Override
    public void loginSuccess() {

    }

    public void startTime() {
        tv_getcode.setEnabled(false);


    }


    @Override
    public void processTime(int time) {
        if (tv_getcode != null) {
            tv_getcode.setText(getString(R.string.processTime, time + ""));
        }
    }

    @Override
    public void endTime() {
        if (tv_getcode != null) {
            tv_getcode.setEnabled(true);
            tv_getcode.setText(getString(R.string.getVerificationCode));
        }
    }


    @Override
    public void showMsg(String msg) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_bt:
                if (isverTel() && isverCode()) {
                    mPresenter.login(phone_et.getText().toString().trim(), pwd_et.getText().toString().trim());
                    AppManager.getInstance().showActivity(HomeActivity.class,null);
                }
                else
                    showTip("手机号码或验证码有误");
                break;
            case R.id.tv_getcode:
                if (isverTel()) {
                    startTime();
                    mPresenter.startTime();
                } else
                    showTip("请输入正确的手机号码");
                break;

            case R.id.tv_regist:
                AppManager.getInstance().showActivity(RegisteActivity.class, null);
                break;

            case R.id.tv_passwordlogin:
                AppManager.getInstance().showActivity(PwdLoginActivity.class, null);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.cancelTime();
    }


    private boolean isverTel() {
        if (!TextUtils.isEmpty(phone_et.getText()) && phone_et.getText().toString().trim().length() == 11) {
            return true;
        }
        return false;
    }

    private boolean isverCode() {
        if (!TextUtils.isEmpty(pwd_et.getText()) && pwd_et.getText().toString().trim().length() == 6) {
            return true;
        }
        return false;
    }
    private void startAnimator() {
        logoIcon_iv.setImageResource(R.mipmap.logo);
        logoText_iv.setImageResource(R.mipmap.huibaoxian);
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator translationYScroll = ObjectAnimator.ofFloat(scrollView, "translationY", 0, -30);
        ObjectAnimator translationYLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "translationY", 0, -40);
        Log.e("==========","======translationYLogoIcon======");
        ObjectAnimator translationYLogoText = ObjectAnimator.ofFloat(logoText_iv, "translationY", 0, -90);
        ObjectAnimator translationYPhoneLayout = ObjectAnimator.ofFloat(phone_layout, "translationY", 0, -110);
        ObjectAnimator translationYPwdLayout = ObjectAnimator.ofFloat(pwd_layout, "translationY", 0, -110);
        ObjectAnimator translationYForgetPwdLayout = ObjectAnimator.ofFloat(agree_layout, "translationY", 0, -110);
        ObjectAnimator translationYBt = ObjectAnimator.ofFloat(login_bt, "translationY", 0, -110);
        ObjectAnimator scaleXLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "scaleX", 1, 0.5f);
        ObjectAnimator scaleYLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "scaleY", 1, 0.5f);
        ObjectAnimator scaleXLogoText = ObjectAnimator.ofFloat(logoText_iv, "scaleX", 1, 0.5f);
        ObjectAnimator scaleYLogoText = ObjectAnimator.ofFloat(logoText_iv, "scaleY", 1, 0.5f);
        ObjectAnimator rotationLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "rotation", 0, 180);


        ArrayList<Animator> list=new ArrayList<>();
        list.add(translationYScroll);
        list.add(translationYLogoIcon);
        list.add(translationYLogoText);
        list.add(translationYPhoneLayout);
        list.add(translationYPwdLayout);
        list.add(translationYForgetPwdLayout);
        list.add(translationYBt);
        list.add(scaleXLogoIcon);
        list.add(scaleYLogoIcon);
        list.add(scaleXLogoText);
        list.add(scaleYLogoText);
        list.add(rotationLogoIcon);
        set.playTogether(list);
        set.setDuration(300);
        set.start();
    }

    private void resetAnimator() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator translationYScroll = ObjectAnimator.ofFloat(scrollView, "translationY", -30,0);
        ObjectAnimator translationYLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "translationY", -40,0);
        ObjectAnimator translationYLogoText = ObjectAnimator.ofFloat(logoText_iv, "translationY",-90,0);
        ObjectAnimator translationYPhoneLayout = ObjectAnimator.ofFloat(phone_layout, "translationY",-110, 0);
        ObjectAnimator translationYPwdLayout = ObjectAnimator.ofFloat(pwd_layout, "translationY",-110,0);
        ObjectAnimator translationYForgetPwdLayout = ObjectAnimator.ofFloat(agree_layout, "translationY",-110, 0);
        ObjectAnimator translationYBt = ObjectAnimator.ofFloat(login_bt, "translationY",-110,0);
        ObjectAnimator scaleXLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "scaleX",0.5f, 1);
        ObjectAnimator scaleYLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "scaleY",0.5f, 1);
        ObjectAnimator scaleXLogoText = ObjectAnimator.ofFloat(logoText_iv, "scaleX",0.5f, 1);
        ObjectAnimator scaleYLogoText = ObjectAnimator.ofFloat(logoText_iv, "scaleY",0.5f, 1);
        ObjectAnimator rotationLogoIcon = ObjectAnimator.ofFloat(logoIcon_iv, "rotation",-180, 0);
        ArrayList<Animator> list=new ArrayList<>();
        list.add(translationYScroll);
        list.add(translationYLogoIcon);
        list.add(translationYLogoText);
        list.add(translationYPhoneLayout);
        list.add(translationYPwdLayout);
        list.add(translationYForgetPwdLayout);
        list.add(translationYBt);
        list.add(scaleXLogoIcon);
        list.add(scaleYLogoIcon);
        list.add(scaleXLogoText);
        list.add(scaleYLogoText);
        list.add(rotationLogoIcon);
        set.playTogether(list);
        set.setDuration(300);
        set.start();
    }
}
