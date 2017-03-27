package com.bb.hbx.activitiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.utils.ShareSPUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.personInfo_layout)
    RelativeLayout personInfo_layout;
    @BindView(R.id.income_layout)
    RelativeLayout income_layout;
    @BindView(R.id.clearCache_layout)
    RelativeLayout clearCache_layout;
    @BindView(R.id.evaluation_layout)
    RelativeLayout evaluation_layout;
    @BindView(R.id.feedback_layout)
    RelativeLayout feedback_layout;
    @BindView(R.id.aboutUs_layout)
    RelativeLayout aboutUs_layout;
    @BindView(R.id.exits_layout)
    RelativeLayout exits_layout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        personInfo_layout.setOnClickListener(this);
        income_layout.setOnClickListener(this);
        clearCache_layout.setOnClickListener(this);
        evaluation_layout.setOnClickListener(this);
        feedback_layout.setOnClickListener(this);
        aboutUs_layout.setOnClickListener(this);
        exits_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.personInfo_layout:
//                showTip("个人资料");
                if (ShareSPUtils.sp.getBoolean("hasLogined", false)) {
                    startActivity(new Intent(mContext,PersonInfoSettingActivity.class));
                } else {
                    showTip("请先登录！");
                }
                break;
            case R.id.income_layout:
                showTip("推广费显示");
                break;
            case R.id.clearCache_layout:
                showTip("清除缓存");
                break;
            case R.id.evaluation_layout:
                showTip("赐予好评");
                break;
            case R.id.feedback_layout:
                //showTip("意见反馈");
                intent.setClass(this, FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.aboutUs_layout:
                showTip("关于汇保险");
                break;
            case R.id.exits_layout:
//                if (TextUtils.isEmpty(MyApplication.user.getUserId())) {
//                    Toast.makeText(mContext, "您还未登录！", Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
                //showTip("退出登录");
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("确认退出登录?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                        Call call = service.logout(MyApplication.user.getUserId());
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                SQLiteDatabase db = DatabaseImpl.getInstance().getReadableDatabase();
                                Cursor cursor = db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
                                if (cursor != null) {
                                    if (cursor.moveToNext()) {
                                        //更新表数据
                                        db.execSQL("update userstb set hasLogined=?,userId=?,sessionId=?,isBClient=?,gender=?,phone=? where currentUser=currentUser ",
                                                new String[]{"false", "", "", "false", "0", ""});
                                        MyApplication.user.setUserId("");
                                        MyApplication.user.setMobile("");
                                        MyApplication.user.setLoginPwd("0");
                                        MyApplication.user.setSessionId("");
                                        MyApplication.user.setIsBClient(false);
                                        ShareSPUtils.writeShareSp(false, "", "", "默认用户名", "", null);

                                        finish();
                                        //ShareSPUtils.readShareSP(notLogin_layout, userIcon_civ,/*hasLogin_tv,*/mContext);
                                        /*identify_tv.setVisibility(View.GONE);
                                        identify_layout.removeView(hasLogin_tv);
                                        isOnce = true;*/
                                    }
                                }
                                cursor.close();
                                db.close();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            default:
                break;
        }
    }
}
