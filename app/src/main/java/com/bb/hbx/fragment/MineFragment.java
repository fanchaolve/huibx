package com.bb.hbx.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.CarInsuOrderActivity;
import com.bb.hbx.activitiy.CustomServiceActivity;
import com.bb.hbx.activitiy.CustomerManagerActivity;
import com.bb.hbx.activitiy.InfoActivity;
import com.bb.hbx.activitiy.MyAssertActivity;
import com.bb.hbx.activitiy.MyOrderActivity;
import com.bb.hbx.activitiy.PerInsuOrderActivity;
import com.bb.hbx.activitiy.PersonInfoSettingActivity;
import com.bb.hbx.activitiy.PresentInsuActivity;
import com.bb.hbx.activitiy.RedPacketActivity;
import com.bb.hbx.activitiy.ScoreActivity;
import com.bb.hbx.activitiy.login.LoginActivity;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.Account;
import com.bb.hbx.bean.UserInfo;
import com.bb.hbx.utils.MyUsersSqlite;
import com.bb.hbx.utils.ShareSPUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.setting_iv)
    ImageView setting_iv;
    @BindView(R.id.message_iv)
    ImageView message_iv;
    @BindView(R.id.userIcon_civ)
    CircleImageView userIcon_civ;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.myOrder_layout)
    RelativeLayout myOrder_layout;
    @BindView(R.id.pInsurance_layout)
    RelativeLayout pInsurance_layout;
    @BindView(R.id.cInsurance_layout)
    RelativeLayout cInsurance_layout;
    @BindView(R.id.myAsset_tv)
    TextView myAsset_tv;
    @BindView(R.id.score_layout)
    RelativeLayout score_layout;
    @BindView(R.id.canCash_tv)
    TextView canCash_tv;
    @BindView(R.id.leftMoney_tv)
    TextView leftMoney_tv;
    @BindView(R.id.score_tv)
    TextView score_tv;
    @BindView(R.id.redPacket_tv)
    TextView redPacket_tv;
    @BindView(R.id.identify_layout)
    FrameLayout identify_layout;
    @BindView(R.id.redPacket_layout)
    RelativeLayout redPacket_layout;
    @BindView(R.id.notLogin_layout)
    RelativeLayout notLogin_layout;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.identify_tv)
    TextView identify_tv;
    /*@BindView(R.id.hasLogin_tv)
    TextView hasLogin_tv;*/
    @BindView(R.id.pCount_tv)
    TextView pCount_tv;

    @BindView(R.id.purchase_layout)
    RelativeLayout purchase_layout;
    @BindView(R.id.customers_layout)
    RelativeLayout customers_layout;
    @BindView(R.id.service_layout)
    RelativeLayout service_layout;
    Context mContext;

    boolean isOnce=true;
    TextView hasLogin_tv;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_host;
    }

    @Override
    public void initView() {

        setting_iv.setOnClickListener(this);
        message_iv.setOnClickListener(this);
        userIcon_civ.setOnClickListener(this);
        myOrder_layout.setOnClickListener(this);
        pInsurance_layout.setOnClickListener(this);
        cInsurance_layout.setOnClickListener(this);
        myAsset_tv.setOnClickListener(this);
        score_layout.setOnClickListener(this);
        redPacket_layout.setOnClickListener(this);
        notLogin_layout.setOnClickListener(this);

        purchase_layout.setOnClickListener(this);
        customers_layout.setOnClickListener(this);
        service_layout.setOnClickListener(this);

        ShareSPUtils.readShareSP(notLogin_layout,userIcon_civ,/*,hasLogin_tv,*/mContext);
        hasLoginShow();
        //updateMyAccount();
    }



    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        ShareSPUtils.readShareSP(notLogin_layout,userIcon_civ,/*hasLogin_tv,*/mContext);
        hasLoginShow();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
           case R.id.message_iv:
                //Toast.makeText(mContext,"消息",Toast.LENGTH_SHORT).show();
               intent.setClass(mContext,InfoActivity.class);
               startActivity(intent);
                break;
            case R.id.setting_iv:
                //Toast.makeText(mContext,"设置",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("确认退出登录?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(MyApplication.user.getUserId()))
                        {
                            Toast.makeText(mContext,"请先登录",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                        Call call=service.logout(MyApplication.user.getUserId());
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Cursor cursor = MyUsersSqlite.db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
                                if (cursor!=null)
                                {
                                    if (cursor.moveToNext())
                                    {
                                        ContentValues values = new ContentValues();
                                        values.put("hasLogined", "false");//默认false,未登录
                                        values.put("isBClient", false);//默认false
                                        values.put("sessionId", "");
                                        values.put("userId", "");
                                        values.put("phone", "");
                                        values.put("gender", "0");//默认为0
                                        long flag = MyUsersSqlite.db.insert("userstb", null, values);
                                        //Toast.makeText(this,"插入新用户成功:"+flag,Toast.LENGTH_SHORT).show();
                                        values.clear();

                                        MyApplication.user.setUserId("");
                                        MyApplication.user.setMobile("");
                                        MyApplication.user.setLoginPwd("0");
                                        MyApplication.user.setSessionId("");
                                        MyApplication.user.setIsBClient(false);
                                        ShareSPUtils.writeShareSp(false,"","","默认用户名","", null);
                                        ShareSPUtils.readShareSP(notLogin_layout,userIcon_civ,/*hasLogin_tv,*/mContext);
                                        identify_tv.setVisibility(View.GONE);
                                        identify_layout.removeView(hasLogin_tv);
                                        isOnce=true;
                                    }
                                }
                                cursor.close();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
                break;
            case R.id.myOrder_layout:
                intent.setClass(mContext,MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.pInsurance_layout:
                intent.setClass(mContext,PerInsuOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.cInsurance_layout:
                intent.setClass(mContext,CarInsuOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.userIcon_civ:
                boolean hasLogined = ShareSPUtils.sp.getBoolean("hasLogined", false);
                if (hasLogined)
                {
                    intent.setClass(mContext,PersonInfoSettingActivity.class);
                }
                else
                {
                    intent.setClass(mContext, LoginActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.myAsset_tv:
                intent.setClass(mContext,MyAssertActivity.class);
                startActivity(intent);
                break;
            case R.id.score_layout:
                intent.setClass(mContext, ScoreActivity.class);
                startActivity(intent);
                break;
            case R.id.redPacket_layout:
                intent.setClass(mContext, RedPacketActivity.class);
                startActivity(intent);
                break;
            case R.id.notLogin_layout:
                intent.setClass(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_bottom,R.anim.activity_stay);
                break;
           /* case hasLogin_tv:
                Toast.makeText(mContext,"点了我",Toast.LENGTH_SHORT).show();

                break;*/
            case R.id.purchase_layout:
                //Toast.makeText(mContext,"赠险产品",Toast.LENGTH_SHORT).show();
                intent.setClass(mContext, PresentInsuActivity.class);
                startActivity(intent);
                break;
            case R.id.customers_layout:
                intent.setClass(mContext, CustomerManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.service_layout:
                intent.setClass(mContext, CustomServiceActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    //用户已经登录后,更改显示的页面
    public void hasLoginShow()
    {
        boolean hasLogined = ShareSPUtils.sp.getBoolean("hasLogined", false);
        if (hasLogined)
        {
            identify_tv.setVisibility(View.VISIBLE);
            String userName=null;
            Cursor cursor = MyUsersSqlite.db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
            if (cursor!=null)
            {
                if (cursor.moveToNext())
                {
                    final String userId = cursor.getString(cursor.getColumnIndex("userId"));
                    final String sessionId = cursor.getString(cursor.getColumnIndex("sessionId"));
                    userName = cursor.getString(cursor.getColumnIndex("name"));
                    if (!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId))
                    {
                        //调用更新账户方法
                        updateMyAccount(userId);
                        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                        Call call=service.getUserInfo(sessionId,userId);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                //Toast.makeText(mContext,"userId:"+userId+"  sessionId:"+sessionId,Toast.LENGTH_SHORT);
                                //正常,,不吐司
                                Result_Api body = (Result_Api) response.body();
                                if (body!=null)
                                {
                                    UserInfo userInfo = (UserInfo) body.getOutput();
                                    if (userInfo!=null)
                                    {
                                        String userName1 = userInfo.getUserName();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(mContext,"服务器异常!",Toast.LENGTH_SHORT);
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(mContext,"error",Toast.LENGTH_SHORT);
                            }
                        });
                    }
                }
                else
                {
                    Toast.makeText(mContext,"cursor下一条不存在",Toast.LENGTH_SHORT);
                }
            }
            else
            {
                Toast.makeText(mContext,"cursor为空",Toast.LENGTH_SHORT);
            }
            if (isOnce)
            {
                hasLogin_tv = new TextView(mContext);
                FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity= Gravity.CENTER;
                hasLogin_tv.setLayoutParams(params);
                identify_layout.addView(hasLogin_tv);
                isOnce=false;
                hasLogin_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"点了我",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if (!TextUtils.isEmpty(userName))
            {
                hasLogin_tv.setText(userName);
            }
            else
            {
                Toast.makeText(mContext,"用户名为空",Toast.LENGTH_SHORT);
            }
        }
    }
    //更新我的资产
    private void updateMyAccount(String userId) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getAccount(userId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body!=null)
                {
                    Account account = (Account) body.getOutput();
                    if (account!=null)
                    {
                        String acctBalance = account.getAcctBalance();//可提现????
                        String acctSum = account.getAcctSum();//余额????
                        String accountScore = account.getAccountScore();
                        String bonusCount = account.getBonusCount();
                        canCash_tv.setText(TextUtils.isEmpty(acctBalance)?"0":acctBalance);
                        leftMoney_tv.setText(TextUtils.isEmpty(acctSum)?"0":acctSum);
                        score_tv.setText(TextUtils.isEmpty(accountScore)?"0":accountScore);
                        redPacket_tv.setText(TextUtils.isEmpty(bonusCount)?"0":bonusCount);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showTip("服务器返回出错啦!");
            }
        });
    }
}
