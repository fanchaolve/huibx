package com.bb.hbx.activitiy;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckIdentifyActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.pwd_et)
    EditText pwd_et;
    @BindView(R.id.nextStep_tv)
    TextView nextStep_tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_identify;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        nextStep_tv.setOnClickListener(this);
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
            case R.id.nextStep_tv:
                //showTip("下一步");
                final String pwd = pwd_et.getText().toString().trim();
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call = service.verifyPayPwd(MyApplication.user.getUserId(), pwd);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api result_api = (Result_Api) response.body();
                        if (result_api != null) {
                            if (result_api.isSuccess()) {
                                Intent intent = new Intent(mContext, FixPayPwdActivity.class);
                                intent.putExtra("flag", Can.FIX_PWD);
                                intent.putExtra("oldPwd", pwd);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(mContext,"密码输入错误，请重新输入！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(mContext,"网络异常！",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}
