package com.bb.hbx.activitiy.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.activitiy.PwdLoginActivity;
import com.bb.hbx.activitiy.RegisteActivity;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.MessageCodeBean;
import com.bb.hbx.bean.User;
import com.bb.hbx.cans.Can;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.interfaces.LoginTextWatcher;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.LoginAnimHelp;
import com.bb.hbx.utils.ShareSPUtils;
import com.bb.hbx.widget.CountDownTextView;
import com.bb.hbx.widget.LoginTelEdit;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 登录
 * Created by fancl
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View,
        View.OnClickListener, View.OnLayoutChangeListener {


    private final  String TAG=LoginActivity.class.getSimpleName();

    @BindView(R.id.activity_smss_login)
    LinearLayout activity_smss_login;

    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.scrollView)
    ScrollView scrollView;


    @BindView(R.id.logoIcon_iv)
    ImageView logoIcon_iv;

    @BindView(R.id.logoText_iv)
    ImageView logoText_iv;

    @BindView(R.id.et_phone)
    LoginTelEdit et_phone;

    @BindView(R.id.et_pwd)
    EditText et_pwd;

    @BindView(R.id.tv_getcode)
    CountDownTextView tv_getcode;

    @BindView(R.id.ck_agree)
    CheckBox ck_agree;

    @BindView(R.id.tv_agree)
    TextView tv_agree;

    @BindView(R.id.tv_login)
    TextView tv_login;

    @BindView(R.id.tv_passwordlogin)
    TextView tv_passwordlogin;

    @BindView(R.id.tv_regist)
    TextView tv_regist;

    @BindView(R.id.iv_wechat)
    ImageView iv_wechat;

    @BindView(R.id.lin_ed)
    LinearLayout lin_ed;


    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    //接收验证码
    String smsCode;

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        activity_smss_login.addOnLayoutChangeListener(this);
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            startAnimator();

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            resetAnimator();
        }
    }


    @Override
    public void loginSuccess() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    @Override
    public void initListener() {
        tv_getcode.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        tv_passwordlogin.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        et_phone.addTextChangedListener(new LoginTextWatcher(tv_login, this));
        et_pwd.addTextChangedListener(new LoginTextWatcher(tv_login, this));

        ck_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isverTel() && isverCode() && ck_agree.isChecked()) {
                    tv_login.setBackgroundResource(R.drawable.shape_btn_a1);
                } else {
                    tv_login.setBackgroundResource(R.drawable.shape_btn_a6);
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
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                overridePendingTransition(0, R.anim.slide_out_to_bottom);
                break;
            case R.id.tv_regist:
                AppManager.getInstance().showActivity(RegisteActivity.class, null);
                finish();
                break;

            case R.id.tv_passwordlogin:
                //AppManager.getInstance().showActivity(PwdLoginActivity.class, null);
                intent=new Intent(this, PwdLoginActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.tv_login:
                if (isverTel() && isverCode()) {
                    mPresenter.login(et_phone.getText().toString().trim(), et_pwd.getText().toString().trim());
                    loginMethod();
                    //AppManager.getInstance().showActivity(HomeActivity.class, null);
                } else
                    showTip("手机号码或验证码有误");
                break;
            case R.id.tv_getcode:
                if (isverTel()) {
                    tv_getcode.startTime();
                    getCheckCode();
                } else
                    showTip("请输入正确的手机号码");
                break;

        }
    }

    public boolean isverTel() {
        if (!TextUtils.isEmpty(et_phone.getText()) && et_phone.getText().toString().trim().length() == 11) {
            return true;
        }
        return false;
    }

    public boolean isverCode() {
        if (!TextUtils.isEmpty(et_pwd.getText())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isverpassword() {
        return true;
    }

    @Override
    public boolean isCheckbx() {
        return ck_agree.isChecked();
    }

    private void startAnimator() {
        LoginAnimHelp helpstart = new LoginAnimHelp();

        helpstart.addTranslationY(lin_ed, 0, -60);
        //helpstart.addTranslationY(scrollView, 0, -60);
        helpstart.addTranslationY(logoIcon_iv, 0, -40);
        helpstart.addTranslationY(logoText_iv, 0, -120);
        helpstart.addScaleX(logoIcon_iv, 1, 0.5f);
        helpstart.addScaleX(logoText_iv, 1, 0.5f);
        helpstart.addScaleY(logoIcon_iv, 1, 0.5f);
        helpstart.addScaleY(logoText_iv, 1, 0.5f);
        helpstart.addRotation(logoIcon_iv, 0, 180);
        helpstart.startAnim(300);
    }

    private void resetAnimator() {
        LoginAnimHelp helpstart = new LoginAnimHelp();
        helpstart.addTranslationY(lin_ed, -60, 0);
        //helpstart.addTranslationY(scrollView, -60, 0);
        helpstart.addTranslationY(logoIcon_iv, -40, 0);
        helpstart.addTranslationY(logoText_iv, -120, 0);
        helpstart.addScaleX(logoIcon_iv, 0.5f, 1);
        helpstart.addScaleX(logoText_iv, 0.5f, 1);
        helpstart.addScaleY(logoIcon_iv, 0.5f, 1);
        helpstart.addScaleY(logoText_iv, 0.5f, 1);
        helpstart.addRotation(logoIcon_iv, 180, 0);
        helpstart.startAnim(300);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_to_bottom);
    }

    //获取短信验证码
    public String getCheckCode() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getVerifyCode("1",et_phone.getText().toString().trim(),"11");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body.getOutput() != null) {
                    MessageCodeBean bean = (MessageCodeBean) body.getOutput();
                    smsCode= bean.getSmsCode();
                }

                //Toast.makeText(LoginActivity.this,"smsCode:"+smsCode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showTip("获取验证码失败");
            }
        });
        return null;
    }

    public void loginMethod()
    {
        final String phone = et_phone.getText().toString();
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.loginSms(et_phone.getText().toString().trim(),et_pwd.getText().toString().trim(),"2");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body.isSuccess())
                {
                    User user = (User) body.getOutput();
                    if (user!=null)
                    {
                        String userId = user.getUserId();
                        String sessionId = user.getSessionId();
                        String isBClient = user.getIsBClient()+"";
                        String loginPwd = user.getLoginPwd();
                        String paymentPwd = user.getPaymentPwd();
                        String gender = user.getGender();
                        String userName = user.getNickName();
                        String email = user.getEmail();
                        String logo = user.getLogo();
                        String userQrcode = user.getUserQrcode();
                        if (TextUtils.isEmpty(gender))
                        {
                            gender="0";
                        }
                        if (TextUtils.isEmpty(userName))
                        {
                            userName=phone;
                        }
                        ShareSPUtils.writeShareSp(true,userId,sessionId,"默认用户名",phone, null);
                        //更新表数据
                        //MyUsersSqlite.db
                        SQLiteDatabase db= DatabaseImpl.getInstance().getReadableDatabase();
                        db.execSQL("update userstb set hasLogined=?,userId=?,sessionId=?,isBClient=?,name=?,gender=?,email=?,phone=?,pwd=?,paymentPwd=?,usericon=? ,userQrcode=? where currentUser=currentUser ",
                                new String[]{"true",userId,sessionId,isBClient,userName,gender,email,phone,loginPwd,paymentPwd,logo,userQrcode});
                        db.close();
                        showTip(body.getRespMsg());

                        user.setUserQrcode(userQrcode);
                        user.setUserId(userId);
                        user.setMobile(phone);
                        user.setGender(gender);
                        user.setLoginPwd(loginPwd);
                        user.setPaymentPwd(paymentPwd);
                        user.setSessionId(sessionId);
                        user.setIsBClient(isBClient.equals("true")?true:false);

                        //AppManager.getInstance().showActivity(HomeActivity.class, null);
                        finish();
                    }
                    else
                    {
                        showTip(body.getRespMsg());
                    }
                }
                else
                {
                    showTip(body.getRespMsg());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showTip("登录失败");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100)
        {
            if (resultCode== Can.FINISH_LOGIN)
            {
                finish();
            }
        }
    }
}
