package com.bb.hbx.activitiy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.utils.AppManager;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixPayPwdActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.pwd_et)
    EditText pwd_et;
    @BindView(R.id.checkPwd_et)
    EditText checkPwd_et;
    @BindView(R.id.verify_tv)
    TextView verify_tv;

    String oldPwd = "";
    int flag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fix_pay_pwd;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        oldPwd = intent.getStringExtra("oldPwd");
        flag = intent.getIntExtra("flag", -1);
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
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
            case R.id.verify_tv:
                String pwd = pwd_et.getText().toString().trim();
                String checkPwd = checkPwd_et.getText().toString().trim();
                if (!TextUtils.isEmpty(pwd)) {
                    if (pwd.equals(checkPwd)) {
                        if (Can.FORGET_PWD == flag) {
                            ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                            Call call = service.forgetPayPwd(MyApplication.user.getUserId(), pwd);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Result_Api body = (Result_Api) response.body();
                                    if (body != null) {
                                        if (body.isSuccess()) {
                                            //更新表数据
                                            SQLiteDatabase db = DatabaseImpl.getInstance().getReadableDatabase();
                                            db.execSQL("update userstb set paymentPwd=? where currentUser=currentUser ",
                                                    new String[]{"1"});
                                            MyApplication.user.setPaymentPwd("1");
                                            db.close();
                                            AppManager.getInstance().finishParticularActivity(CheckIdentifyInForgerActivity.class);
                                            finish();
                                        }
                                        showTip(body.getRespMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {

                                }
                            });
                        } else if (Can.FIX_PWD == flag) {
                            ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                            Call call = service.modPayPwd(MyApplication.user.getUserId(), oldPwd, pwd);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Result_Api body = (Result_Api) response.body();
                                    if (body != null) {
                                        if (body.isSuccess()) {
                                            SQLiteDatabase db = DatabaseImpl.getInstance().getReadableDatabase();
                                            //更新表数据
                                            db.execSQL("update userstb set paymentPwd=? where currentUser=currentUser ",
                                                    new String[]{"1"});
                                            db.close();
                                            MyApplication.user.setPaymentPwd("1");
                                            AppManager.getInstance().finishParticularActivity(CheckIdentifyUnderPwdActivity.class);
                                            finish();
                                        }
                                        showTip(body.getRespMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {

                                }
                            });
                        }
                    } else {
                        showTip("密码不一致,请重新输入");
                    }
                } else {
                    showTip("密码不能为空");
                }
                break;
            default:
                break;
        }
    }
}
