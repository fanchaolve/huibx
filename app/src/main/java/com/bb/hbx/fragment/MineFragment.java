package com.bb.hbx.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.bb.hbx.R;
import com.bb.hbx.activitiy.CarInsuOrderActivity;
import com.bb.hbx.activitiy.CustomServiceActivity;
import com.bb.hbx.activitiy.CustomerManagerActivity;
import com.bb.hbx.activitiy.InfoActivity;
import com.bb.hbx.activitiy.MyAssertActivity;
import com.bb.hbx.activitiy.MyAssertDetailActivity;
import com.bb.hbx.activitiy.MyBankCardActivity;
import com.bb.hbx.activitiy.MyCollectionActivity;
import com.bb.hbx.activitiy.MyOrderActivity;
import com.bb.hbx.activitiy.PerInsuOrderActivity;
import com.bb.hbx.activitiy.PersonInfoSettingActivity;
import com.bb.hbx.activitiy.PresentInsuActivity;
import com.bb.hbx.activitiy.RecommendRewardActivity;
import com.bb.hbx.activitiy.RedPacketActivity;
import com.bb.hbx.activitiy.ScoreActivity;
import com.bb.hbx.activitiy.SettingsActivity;
import com.bb.hbx.activitiy.login.LoginActivity;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.GetMyPageInfoBean;
import com.bb.hbx.bean.UserInfo;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.ShareSPUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

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

    @BindView(R.id.canCash_layout)
    RelativeLayout canCash_layout;

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

    @BindView(R.id.cCount_tv)
    TextView cCount_tv;

    @BindView(R.id.purchase_layout)
    RelativeLayout purchase_layout;

    @BindView(R.id.customers_layout)
    RelativeLayout customers_layout;

    @BindView(R.id.collect_layout)
    RelativeLayout collect_layout;

    @BindView(R.id.invite_layout)
    RelativeLayout invite_layout;

    @BindView(R.id.service_layout)
    RelativeLayout service_layout;

    @BindView(R.id.topbar_layout)
    RelativeLayout topbar_layout;

    @BindView(R.id.leftMoney_layout)
    RelativeLayout leftMoney_layout;

    Context mContext;

    boolean isOnce = true;
    TextView hasLogin_tv;

    //记录提现
    int acctBalanceInt;
    //记录积分
    int accountScoreInt;
    //记录本月收入
    int acctMonthSumInt;
    //记录结算中
    int acctSettSumInt;
    //记录累计收入
    int acctSumInt;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_host;
    }

    @Override
    public void initView() {
        initListener();
        if (!ShareSPUtils.sp.getBoolean("hasLogined", false)) {
            canCash_tv.setText("0.00");
            leftMoney_tv.setText("0.00");
            score_tv.setText("0");
            redPacket_tv.setText("0");
        }
        ShareSPUtils.readShareSP(notLogin_layout, userIcon_civ,/*,hasLogin_tv,*/mContext);
        hasLoginShow();
        //updateMyAccount();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        setting_iv.setOnClickListener(this);
        message_iv.setOnClickListener(this);
        userIcon_civ.setOnClickListener(this);
        myOrder_layout.setOnClickListener(this);
        pInsurance_layout.setOnClickListener(this);
        cInsurance_layout.setOnClickListener(this);
        myAsset_tv.setOnClickListener(this);
        canCash_layout.setOnClickListener(this);
        score_layout.setOnClickListener(this);
        redPacket_layout.setOnClickListener(this);
        notLogin_layout.setOnClickListener(this);
        leftMoney_layout.setOnClickListener(this);

        purchase_layout.setOnClickListener(this);
        customers_layout.setOnClickListener(this);
        collect_layout.setOnClickListener(this);
        invite_layout.setOnClickListener(this);
        service_layout.setOnClickListener(this);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        ShareSPUtils.readShareSP(notLogin_layout, userIcon_civ,/*hasLogin_tv,*/mContext);
        hasLoginShow();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.message_iv:
                //Toast.makeText(mContext,"消息",Toast.LENGTH_SHORT).show();
                intent.setClass(mContext, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_iv:
                //Toast.makeText(mContext,"设置",Toast.LENGTH_SHORT).show();
                intent.setClass(mContext, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.myOrder_layout:
                intent.setClass(mContext, MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.pInsurance_layout:
                intent.setClass(mContext, PerInsuOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.cInsurance_layout:
                intent.setClass(mContext, CarInsuOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.userIcon_civ:
                boolean hasLogined = ShareSPUtils.sp.getBoolean("hasLogined", false);
                if (hasLogined) {
                    intent.setClass(mContext, PersonInfoSettingActivity.class);
                } else {
                    intent.setClass(mContext, LoginActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.myAsset_tv:
                intent.putExtra("acctBalanceInt", acctBalanceInt);
                intent.putExtra("acctMonthSumInt", acctMonthSumInt);
                intent.putExtra("acctSettSumInt", acctSettSumInt);
                intent.putExtra("acctSumInt", acctSumInt);
                intent.setClass(mContext, MyAssertActivity.class);
                startActivity(intent);
                break;
            case R.id.canCash_layout:
                if (ShareSPUtils.sp.getBoolean("hasLogined",false)) {
                    startActivity(new Intent(mContext,MyAssertActivity.class));
                } else {
                    startActivity(new Intent(mContext,LoginActivity.class));
                }
                break;
            case R.id.leftMoney_layout:
                if (ShareSPUtils.sp.getBoolean("hasLogined",false)) {
                    startActivity(new Intent(mContext, MyAssertDetailActivity.class));
                } else {
                    startActivity(new Intent(mContext,LoginActivity.class));
                }
                break;
            case R.id.score_layout:
                if (ShareSPUtils.sp.getBoolean("hasLogined",false)) {
                    intent.putExtra("accountScoreInt", accountScoreInt);
                    intent.setClass(mContext, ScoreActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(mContext,LoginActivity.class));
                }
                break;
            case R.id.redPacket_layout:
                if (ShareSPUtils.sp.getBoolean("hasLogined",false)) {
                    intent.setClass(mContext, RedPacketActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(mContext,LoginActivity.class));
                }
                break;
            case R.id.notLogin_layout:
                intent.setClass(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.activity_stay);
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
            case R.id.collect_layout:
                intent.setClass(mContext, MyCollectionActivity.class);
                startActivity(intent);

                break;
            case R.id.invite_layout:
                intent.setClass(mContext, RecommendRewardActivity.class);
                startActivity(intent);

               /* HomeActivity activity = (HomeActivity) getActivity();
                if (activity != null) {
                    activity.updateViewWithCToB();
                }*/

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
    public void hasLoginShow() {
        boolean hasLogined = ShareSPUtils.sp.getBoolean("hasLogined", false);
        if (hasLogined) {
            identify_tv.setVisibility(View.VISIBLE);
            String userName = null;
            SQLiteDatabase db = DatabaseImpl.getInstance().getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    final String userId = cursor.getString(cursor.getColumnIndex("userId"));
                    final String sessionId = cursor.getString(cursor.getColumnIndex("sessionId"));
                    userName = cursor.getString(cursor.getColumnIndex("name"));
                    if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(sessionId)) {
                        //调用更新账户方法
                        updateMyAccount(userId);
                        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                        Call call = service.getUserInfo(sessionId, userId);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                //Toast.makeText(mContext,"userId:"+userId+"  sessionId:"+sessionId,Toast.LENGTH_SHORT);
                                //正常,,不吐司
                                Result_Api body = (Result_Api) response.body();
                                if (body != null) {
                                    UserInfo userInfo = (UserInfo) body.getOutput();
                                    if (userInfo != null) {
                                        String userName1 = userInfo.getUserName();
                                    }
                                } else {
                                    Toast.makeText(mContext, "服务器异常!", Toast.LENGTH_SHORT);
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT);
                            }
                        });
                    }
                } else {
                    Toast.makeText(mContext, "cursor下一条不存在", Toast.LENGTH_SHORT);
                }
                db.close();
            } else {
                Toast.makeText(mContext, "cursor为空", Toast.LENGTH_SHORT);
            }
            if (isOnce) {
                hasLogin_tv = new TextView(mContext);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                hasLogin_tv.setLayoutParams(params);
                identify_layout.addView(hasLogin_tv);
                isOnce = false;
                hasLogin_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "点了我", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if (!TextUtils.isEmpty(userName)) {
                hasLogin_tv.setText(userName);
            } else {
                Toast.makeText(mContext, "用户名为空", Toast.LENGTH_SHORT);
            }
        }
        else
        {
            identify_tv.setVisibility(View.GONE);
            if (hasLogin_tv!=null)
            {
                identify_layout.removeView(hasLogin_tv);
            }
            isOnce = true;
        }
    }

    //更新我的资产
    private void updateMyAccount(String userId) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getMyPageInfo(userId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body != null) {
                    GetMyPageInfoBean bean = (GetMyPageInfoBean) body.getOutput();
                    if (bean != null) {
                        int acctBalance = bean.getAcctBalance();
                        int acctSum = bean.getAcctSum();
                        int score = bean.getScore();
                        int couponCount = bean.getCouponCount();
                        int realNameStatus = bean.getRealNameStatus();//0表示未认证,1表示已认证
                        int policyCount = bean.getPolicyCount();
                        int carPolicyCount = bean.getCarPolicyCount();
                        pCount_tv.setText(policyCount + "份保单");
                        cCount_tv.setText(carPolicyCount + "份保单");
                        canCash_tv.setText((acctBalance / 100) + "." + (acctBalance / 10 % 10) + (acctBalance % 10));
                        leftMoney_tv.setText((acctSum / 100) + "." + (acctSum / 10 % 10) + (acctSum % 10));
                        score_tv.setText((score / 100) + "." + (score / 10 % 10) + (score % 10));
                        redPacket_tv.setText(couponCount + "");
                        identify_tv.setText(0 == realNameStatus ? "未认证" : "已认证");
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
        /*ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
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
                        String acctBalance = account.getAcctBalance();//可提现
                        String acctSum = account.getAcctSum();//余额????收入,累计收入
                        String accountScore = account.getAccountScore();//积分
                        String bonusCount = account.getBonusCount();//红包个数
                        String acctMonthSum = account.getAcctMonthSum();//本月收入
                        String acctSettSum = account.getAcctSettSum();//结算中
                        acctBalanceInt = Integer.parseInt(acctBalance);
                        acctSumInt = Integer.parseInt(acctSum);
                        accountScoreInt = Integer.parseInt(accountScore);
                        acctMonthSumInt = Integer.parseInt(acctMonthSum);
                        acctSettSumInt = Integer.parseInt(acctSettSum);
                        canCash_tv.setText(TextUtils.isEmpty(acctBalance)?"0":(acctBalanceInt/100)+"."+(acctBalanceInt/10%10)+(acctBalanceInt%10));
                        leftMoney_tv.setText(TextUtils.isEmpty(acctSum)?"0":(acctSumInt/100)+"."+(acctSumInt/10%10)+(acctSumInt%10));
                        score_tv.setText(TextUtils.isEmpty(accountScore)?"0":(accountScoreInt/100)+"."+(accountScoreInt/10%10)+(accountScoreInt%10));
                        redPacket_tv.setText(TextUtils.isEmpty(bonusCount)?"0":bonusCount);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showTip("服务器返回出错啦!");
            }
        });*/
    }
}
