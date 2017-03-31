package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.GetInsured;
import com.bb.hbx.bean.LishiSearchBean;
import com.bb.hbx.fragment.CustomersManagerFragment;
import com.bb.hbx.fragment.RemindingFragment;
import com.bb.hbx.widget.CustomerSearchEditText;
import com.bb.hbx.widget.LoginTelEdit;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bb.hbx.R.id.le_search;

public class CustomerManagerActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.complex_layout)
    RelativeLayout complex_layout;
    @BindView(R.id.content_layout)
    RelativeLayout content_layout;
    @BindView(R.id.null_layout)
    RelativeLayout null_layout;
    @BindView(R.id.search_iv)
    ImageView search_iv;
    @BindView(R.id.singleTitle_tv)
    TextView singleTitle_tv;
    @BindView(R.id.customers_tv)
    TextView customers_tv;
    @BindView(R.id.reminding_tv)
    TextView reminding_tv;
    @BindView(R.id.nullFromContact_tv)
    TextView nullFromContact_tv;
    @BindView(R.id.nullFromManual_tv)
    TextView nullFromManual_tv;

    @BindView(R.id.ll_grey)
    LinearLayout ll_grey;

    @BindView(R.id.ll_search)
    LinearLayout ll_search;

    @BindView(R.id.topbar_layout)
    RelativeLayout topbar_layout;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.iv_del)
    ImageView iv_del;

    FragmentManager fragmentManager;
    CustomersManagerFragment customersManagerFragment;
    RemindingFragment remindingFragment;

    boolean isEmpty=true;
    public static Intent intentFromProDetail;
    public static int typeFromProDetail;
    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_manager;
    }

    @Override
    public void initView() {
        intentFromProDetail = getIntent();
        typeFromProDetail = intentFromProDetail.getIntExtra("type", -1);
        fragmentManager = getSupportFragmentManager();
        customersManagerFragment = new CustomersManagerFragment();
        remindingFragment = new RemindingFragment();
//        et_search = new CustomerSearchEditText(mContext);
        uiDisplay();
        /*if (!isEmpty)
        {
            fragmentManager.beginTransaction().add(R.id.content_layout,customersManagerFragment).commit();
        }*/
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        search_iv.setOnClickListener(this);
        customers_tv.setOnClickListener(this);
        reminding_tv.setOnClickListener(this);
        nullFromContact_tv.setOnClickListener(this);
        nullFromManual_tv.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        iv_del.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        /*if (!isEmpty)
        {
            fragmentManager.beginTransaction().add(R.id.content_layout,customersManagerFragment).commit();
        }*/
    }
    //显示ui页面
    private void uiDisplay() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getInsured(MyApplication.user.getUserId(),MyApplication.user.getMobile(),"1","10");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                GetInsured bean = (GetInsured) body.getOutput();
                if (bean!=null)
                {
                    String totalCountString = bean.getTotalCount();
                    List<GetInsured.InsuredListBean> insuredList = bean.getInsuredList();
                    if (insuredList!=null&&insuredList.size()>0)
                    {
                        complex_layout.setVisibility(View.VISIBLE);
                        search_iv.setVisibility(View.VISIBLE);
                        customers_tv.setVisibility(View.VISIBLE);           //始终显示客户管理
                        singleTitle_tv.setVisibility(View.GONE);
                        null_layout.setVisibility(View.GONE);
                        reminding_tv.setVisibility(View.GONE);              //隐藏事件提醒按钮
                        isEmpty=false;
                    }
                    else
                    {
                        complex_layout.setVisibility(View.GONE);
                        search_iv.setVisibility(View.GONE);
                        singleTitle_tv.setVisibility(View.VISIBLE);
                        null_layout.setVisibility(View.VISIBLE);
                        isEmpty=true;
                    }
                }
                if (!isEmpty)
                {
                    if (!customersManagerFragment.isAdded())
                    {
                        fragmentManager.beginTransaction().add(R.id.content_layout,customersManagerFragment).commit();
                        search_iv.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.search_iv:
//                showTip("搜索");
                topbar_layout.setVisibility(View.INVISIBLE);
                ll_search.setVisibility(View.VISIBLE);
                customersManagerFragment.getLl_grey().setVisibility(View.VISIBLE);
                customersManagerFragment.getLl_grey().setBackgroundResource(R.color.A3);
                customersManagerFragment.getLl_grey().getBackground().setAlpha(51);
                break;
            case R.id.tv_cancel:
                ll_search.setVisibility(View.INVISIBLE);
                topbar_layout.setVisibility(View.VISIBLE);
                customersManagerFragment.getLl_grey().setVisibility(View.GONE);
                break;
            case R.id.iv_del:
                et_search.setText("");
                break;
            case R.id.customers_tv:
                //showTip("客户管理");
                if (!isEmpty)
                {
                    customers_tv.setTextColor(getResources().getColor(R.color.white));
                    customers_tv.setBackgroundResource(R.drawable.shape_select_left_custom);
                    reminding_tv.setTextColor(getResources().getColor(R.color.A1));
                    reminding_tv.setBackgroundResource(R.drawable.shape_unselect_right_custom);
                    if (customersManagerFragment.isAdded())
                    {
                        fragmentManager.beginTransaction().hide(remindingFragment).show(customersManagerFragment).commit();
                    }
                    else
                    {
                        fragmentManager.beginTransaction().add(R.id.content_layout,customersManagerFragment).commit();
                    }
                }
                break;
            case R.id.reminding_tv:
                //showTip("事件提醒");
                if (!isEmpty)
                {
                    reminding_tv.setTextColor(getResources().getColor(R.color.white));
                    reminding_tv.setBackgroundResource(R.drawable.shape_select_right_custom);
                    customers_tv.setTextColor(getResources().getColor(R.color.A1));
                    customers_tv.setBackgroundResource(R.drawable.shape_unselect_left_custom);
                    if (remindingFragment.isAdded())
                    {
                        fragmentManager.beginTransaction().hide(customersManagerFragment).show(remindingFragment).commit();
                    }
                    else
                    {
                        fragmentManager.beginTransaction().add(R.id.content_layout,remindingFragment).commit();
                    }
                }
                break;
            case R.id.nullFromContact_tv:
                showTip("空的通讯录");
                break;
            case R.id.nullFromManual_tv:
                //showTip("空的手动添加");
                Intent intent = new Intent();
                intent.setClass(this, AddContactActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        uiDisplay();
    }
}
