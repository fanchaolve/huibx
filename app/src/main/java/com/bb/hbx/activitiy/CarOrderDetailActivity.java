package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyCarOrderDetailAdapter;
import com.bb.hbx.adapter.MyExtraCarOrderDetailAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.CarOrderBean;
import com.bb.hbx.bean.CarOrderBean.TypeListBean.InsureListBean;
import com.bb.hbx.bean.CarOrderBean.TypeListBean.InsureListBean.ExtraInsureBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bb.hbx.R.id.list;
import static com.bb.hbx.R.id.name_tv;
import static com.bb.hbx.R.id.signTime_tv;
import static com.bb.hbx.R.id.state_iv;
import static com.bb.hbx.R.id.title_tv;
import static com.bb.hbx.activitiy.RedPacketActivity.adapter;


public class CarOrderDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.call_layout)
    RelativeLayout call_layout;
    @BindView(R.id.order_layout)
    RelativeLayout order_layout;
    @BindView(R.id.askMoney_layout)
    RelativeLayout askMoney_layout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.extraRecyclerView)
    RecyclerView extraRecyclerView;

    @BindView(R.id.insureInfo_tv)
    TextView insureInfo_tv;
    @BindView(R.id.insureItem_tv)
    TextView insureItem_tv;

    @BindView(R.id.company_iv)
    ImageView company_iv;

//    @BindView(R.id.title_tv)
//    TextView title_tv;

    @BindView(R.id.number_tv)
    TextView number_tv;

//    @BindView(R.id.name_tv)
//    TextView name_tv;

    @BindView(R.id.phone_tv)
    TextView phone_tv;

    @BindView(R.id.licenseNumber_tv)
    TextView licenseNumber_tv;

    @BindView(R.id.trafficTime_tv)
    TextView trafficTime_tv;

    @BindView(R.id.businessTime_tv)
    TextView businessTime_tv;

    @BindView(R.id.trafficInsurance_tv)
    TextView trafficInsurance_tv;       //交强险

    @BindView(R.id.tax_tv)
    TextView tax_tv;                    //车船税

    @BindView(R.id.holdName_tv)
    TextView holdName_tv;

    @BindView(R.id.idType_tv)
    TextView idType_tv;

    @BindView(R.id.idNumber_tv)
    TextView idNumber_tv;

    @BindView(R.id.carType_tv)
    TextView carType_tv;

    @BindView(R.id.frameNumber_tv)
    TextView frameNumber_tv;

    @BindView(R.id.engineNumber_tv)
    TextView engineNumber_tv;

    @BindView(R.id.signTime_tv)
    TextView signTime_tv;

    @BindView(R.id.isUsedCar_tv)
    TextView isUsedCar_tv;

    @BindView(R.id.address_tv)
    TextView address_tv;

    @BindView(R.id.tv_space)
    TextView tv_space;

    @BindView(R.id.state_iv)
    ImageView state_iv;

    private GridLayoutManager manager;
    private GridLayoutManager extraManager;
    private List<InsureListBean> list = new ArrayList<>();                 //商业保险的recycleview
    private List<ExtraInsureBean> extraList = new ArrayList<>();
    private List<ExtraInsureBean> extraTotalList = new ArrayList<>();       //不计免赔的recycleview
    private MyCarOrderDetailAdapter myCarOrderDetailAdapter;            //商业保险的适配器
    private MyExtraCarOrderDetailAdapter myExtraCarOrderDetailAdapter;  //不计免赔的适配器

    boolean moreOrLess = true;

    private String tradeId = "";
    private String detailId = "";
    private String policySts = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_order_detail;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        tradeId = intent.getStringExtra("tradeId");
        detailId = intent.getStringExtra("detailId");
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        insureInfo_tv.setOnClickListener(this);
        insureItem_tv.setOnClickListener(this);
        call_layout.setOnClickListener(this);
        order_layout.setOnClickListener(this);
        askMoney_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        getOrderDetail();
        manager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        extraManager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        extraRecyclerView.setLayoutManager(extraManager);
        recyclerView.setLayoutManager(this.manager);
        myExtraCarOrderDetailAdapter = new MyExtraCarOrderDetailAdapter(extraTotalList, this);
        extraRecyclerView.setAdapter(myExtraCarOrderDetailAdapter);
        myCarOrderDetailAdapter = new MyCarOrderDetailAdapter(list, this);

        recyclerView.setAdapter(myCarOrderDetailAdapter);
        myCarOrderDetailAdapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                //showTip("position:"+position);
                if (moreOrLess) {
                    tv_space.setVisibility(View.VISIBLE);
                    extraTotalList.addAll(extraList);
                } else {
                    tv_space.setVisibility(View.GONE);
                    extraTotalList.clear();
                }
                moreOrLess = !moreOrLess;
                myExtraCarOrderDetailAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.insureInfo_tv:
                showTip("投保须知");
                break;
            case R.id.insureItem_tv:
                showTip("保险条款");
                break;
            case R.id.call_layout:
                showTip("联系客服");
                break;
            case R.id.order_layout:
                showTip("电子保单");
                break;
            case R.id.askMoney_layout:
                showTip("我要理赔");
                break;
            default:
                break;
        }
    }

    /**
     * 获取保单详情
     */
    private void getOrderDetail() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getCarTradeDetail(MyApplication.user.getUserId(), tradeId, detailId, "1");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api api = (Result_Api) response.body();
                if (api.getOutput() != null && api.getOutput() instanceof CarOrderBean) {
                    CarOrderBean carOrderBean = (CarOrderBean) api.getOutput();
                    policySts = carOrderBean.getPolicySts();
                    switch (policySts) {
                        case "10":          //核保中
                            state_iv.setImageResource(R.drawable.baodan_daichudan);
                            break;
                        case "20":          //已承保
                            state_iv.setImageResource(R.drawable.baodan_baozhangzhong);
                            break;
                        case "40":         //已失效
                            state_iv.setImageResource(R.drawable.baodan_yiguoqi);
                            break;
                    }
                    GlideUtil.getInstance().loadImage(mContext,company_iv,carOrderBean.getInsurerLogo(),false);
//                    title_tv.setText(carOrderBean.getProductName());
                    number_tv.setText("保单号：" + carOrderBean.getPolicyId());
//                    name_tv.setText(carOrderBean.getApplicant());
                    phone_tv.setText(carOrderBean.getApplicantMobile());
                    licenseNumber_tv.setText(carOrderBean.getInsuredList().get(0).getCarInfo().getLicenseNo());
                    trafficTime_tv.setText(carOrderBean.getJqxStartTime() + "~" + carOrderBean.getJqxEndTime());
                    businessTime_tv.setText(carOrderBean.getStartTime() + "~" + carOrderBean.getEndTime());
                    String jqxCount = carOrderBean.getTypeList().get(0).getInsureList().get(0).getInsureAmount();   //交强险金额
                    trafficInsurance_tv.setText(TextUtils.isEmpty(jqxCount) ? "未投保" : "投保");
                    String taxCount = carOrderBean.getTypeList().get(0).getInsureList().get(1).getInsureAmount();     //车船税金额
                    tax_tv.setText(TextUtils.isEmpty(taxCount) ? "未投保" : "投保");
                    holdName_tv.setText(carOrderBean.getInsuredList().get(0).getInsuredName());
                    String idType = "1";
                    idType = carOrderBean.getInsuredList().get(0).getIdType();
                    switch (idType) {
                        case "1":
                            idType = "身份证";
                            break;
                        case "2":
                            idType = "军官证";
                            break;
                        case "3":
                            idType = "护照";
                            break;
                        case "4":
                            idType = "驾驶证";
                            break;
                        case "5":
                            idType = "港澳台通行证/回乡证";
                            break;
                        case "0":
                            idType = "其他";
                            break;
                    }
                    idType_tv.setText(idType);
                    idNumber_tv.setText(carOrderBean.getInsuredList().get(0).getIdNo());
                    carType_tv.setText(carOrderBean.getInsuredList().get(0).getCarInfo().getVehicleModel());
                    frameNumber_tv.setText(carOrderBean.getInsuredList().get(0).getCarInfo().getVehicleFrameNo());
                    engineNumber_tv.setText(carOrderBean.getInsuredList().get(0).getCarInfo().getEngineNo());
                    signTime_tv.setText(carOrderBean.getInsuredList().get(0).getCarInfo().getRegisterDate());
                    String isUsedCar = carOrderBean.getInsuredList().get(0).getCarInfo().getSpecialCarFlag() + "";
                    switch (isUsedCar) {
                        case "0":
                            isUsedCar = "否";
                            break;
                        case "1":
                            isUsedCar = "是";
                            break;
                    }
                    isUsedCar_tv.setText(isUsedCar);
                    address_tv.setText(carOrderBean.getShippingAddress());
                    List<InsureListBean> insureList = new ArrayList<>();
                    insureList = carOrderBean.getTypeList().get(1).getInsureList();
                    if (list.size() > 0) {
                        list.clear();
                    }
                    Log.d("ttttt","----------insurelist----------" + insureList.size());
                    list.addAll(insureList);
                    extraList = list.get(list.size() - 1).getExtraInsureList();
                    myCarOrderDetailAdapter.notifyDataSetChanged();
//                    for (InsureListBean insureListBean : insureList) {
//                        if (insureListBean.getExtraInsureList().size() > 0) {
//                            extraList.addAll(insureListBean.getExtraInsureList());
//                        } else {
//                            list.add(insureListBean);
//                        }
//                    }
////                    extraList = carOrderBean.getInsuredList().get(0).
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
//        adapter.notifyDataSetChanged();
    }
}
