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

public class RealNameCommitActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_iv)
    ImageView back_iv;
    @BindView(R.id.name_layout)
    RelativeLayout name_layout;
    @BindView(R.id.realName_et)
    EditText realName_et;
    @BindView(R.id.idCard_et)
    EditText idCard_et;
    @BindView(R.id.commit_tv)
    TextView commit_tv;

    String head = "http://img.51hbx.com/resource/images/user/";

    @Override
    public int getLayoutId() {
        return R.layout.activity_real_name_commit;
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        String frontPath = intent.getStringExtra("front");
        String reversePath = intent.getStringExtra("reverse");
        if (!TextUtils.isEmpty(frontPath)) {
            //MyOssUtils myOssUtils = new MyOssUtils(getApplicationContext(), frontPath);
        }
    }

    @Override
    public void initListener() {
        back_iv.setOnClickListener(this);
        name_layout.setOnClickListener(this);
        commit_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.name_layout:
                Toast.makeText(this, "修改个人信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.commit_tv:
                /*Intent intent = new Intent(this, RealNameFinishActivity.class);
                startActivity(intent);*/
                String realName = realName_et.getText().toString();
                String idCard1 = idCard_et.getText().toString();
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call = service.applyCertification(MyApplication.user.getUserId(), head + "idcard/" + MyApplication.user.getUserId() + "_F" + ".jpg",
                        head + "idcard/" + MyApplication.user.getUserId() + "_B" + ".jpg", idCard1, "1", realName);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api body = (Result_Api) response.body();
                        if (body != null) {
                            if (body.isSuccess()) {
                                Intent intent = new Intent(RealNameCommitActivity.this, RealNameAuthenticatingActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                showTip(body.getRespMsg());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
