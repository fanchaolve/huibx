package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.CommitCarIns;
import com.bb.hbx.bean.CommitCarInsDetail;
import com.bb.hbx.bean.FilterBean;
import com.bb.hbx.bean.Insurer;
import com.bb.hbx.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//车险 订单审核 页面
public class CarOrderReviewActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_confim)
    TextView tv_confim;
    @BindView(R.id.originPrice_tv)
    TextView originPrice_tv;
    @BindView(R.id.dealPrice_tv)
    TextView dealPrice_tv;
    @BindView(R.id.info_tv)
    TextView info_tv;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.chuchiDetail_tv)
    TextView chuchiDetail_tv;
    @BindView(R.id.tongguo_tv)
    TextView tongguo_tv;

    @BindView(R.id.chuchi_iv)
    ImageView chuchi_iv;
    @BindView(R.id.tongguo_iv)
    ImageView tongguo_iv;
    @BindView(R.id.zhifu_iv)
    ImageView zhifu_iv;

    @BindView(R.id.end_rl)
    RelativeLayout end_rl;

    String carPrice="";
    String insurerId="";
    String licenseNo="";

    String serialId="";
    String applicantName="";
    String applicantIdNo="";
    String applicantMobile="";
    String insuredName="";
    String insuredIdNo="";
    String insuredMobile="";
    String expressName="";
    String expressMobile="";
    String expressAddress="";
    String expressProvince="";
    String expressCity="";
    String expressDistrict="";
    String carExtras="";
    String tradeId="";
    String state="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_car_order_review;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        carPrice=intent.getStringExtra("carPrice");
        insurerId=intent.getStringExtra("insurerId");
        licenseNo=intent.getStringExtra("licenseNo");
        serialId=intent.getStringExtra("serialId");
        applicantName=intent.getStringExtra("driveName");
        applicantIdNo=intent.getStringExtra("idNo");
        applicantMobile=intent.getStringExtra("mobile");
        insuredName=applicantName;
        insuredIdNo=applicantIdNo;
        insuredMobile=applicantMobile;
        expressName="我";
        expressMobile="15735926343";
        expressAddress="浙江省温州市永嘉县沿江路125号";
        expressProvince="330000";
        expressCity="330300";
        expressDistrict="330324";
        carExtras=intent.getStringExtra("carExtras");
        //tv_title.setText(licenseNo);
    }

    @Override
    public void initListener() {
        tv_confim.setOnClickListener(this);
    }

    @Override
    public void initdata() {


        getInsurecompanys();

        //getInfoFromCompany();
    }

    private void getInsurecompanys() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getInsurers("0",insurerId,"20");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body.isSuccess())
                {
                    FilterBean bean = (FilterBean) body.getOutput();
                    List<Insurer> insurerList = bean.getInsurerList();
                    String insurerName="保险公司";
                    String insurerTels="400-";
                    if (insurerList.size()>0)
                    {
                        insurerName = insurerList.get(0).getInsurerName();
                        insurerTels = insurerList.get(0).getInsurerTels();
                    }
                    tv_title.setText(insurerName+"-"+licenseNo);
                    phone_tv.setText("联系电话: "+insurerTels);
                    chuchiDetail_tv.setText(TimeUtils.getCurrentHour()+"提交"+insurerName+"审核");
                    getInfoFromCompany();
                }
                else
                {
                    tongguo_iv.setImageResource(R.drawable.smrz_shenhe_shibai);
                    tongguo_tv.setText("核保失败");
                    showTip(body.getRespMsg());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    //
    private void getInfoFromCompany() {

        CommitCarInsDetail detail = new CommitCarInsDetail(MyApplication.user.getUserId(), serialId, applicantName, applicantIdNo, applicantMobile,
                insuredName, insuredIdNo, insuredMobile, expressName, expressMobile,
                expressAddress, expressProvince, expressCity, expressDistrict,carExtras);
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.commitCarIns(detail);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body.isSuccess())
                {
                    CommitCarIns bean = (CommitCarIns) body.getOutput();
                    tradeId = bean.getTradeId();
                    state = bean.getState();
                    //tongguo_iv.setImageResource(R.drawable.smrz_shenhe_tongguoing);
                    if ("1".equals(state))
                    {
                        tongguo_tv.setText("核保通过");
                        tongguo_iv.setImageResource(R.drawable.smrz_shenhe_tongguoing);
                        end_rl.setVisibility(View.VISIBLE);
                        originPrice_tv.setText(carPrice);
                        dealPrice_tv.setText("¥"+bean.getTotalPreium());
                    }
                    else
                    {
                        tongguo_iv.setImageResource(R.drawable.smrz_shenhe_shibai);
                        tongguo_tv.setText("核保失败");
                    }
                    showTip(bean.getMsg());
                }
                else
                {
                    tongguo_iv.setImageResource(R.drawable.smrz_shenhe_shibai);
                    showTip(body.getRespMsg());
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
            case R.id.tv_confim:
                if (!("1".equals(state)))
                {
                    showTip("订单审核失败!");
                    return;
                }
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call = service.reqCarInsPay(MyApplication.user.getUserId(),tradeId);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api body = (Result_Api) response.body();
                        if (body.isSuccess())
                        {
                            Intent intent = new Intent(CarOrderReviewActivity.this, CarConfirmPayActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            showTip(body.getRespMsg());
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
