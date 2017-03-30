package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyPerOrderDetailAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.MyPerOrderDetailBean;
import com.bb.hbx.bean.TradeDetail;
import com.bb.hbx.bean.TradeDetailType;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 保单详情页面  由点击 我的--个险订单 中的条目进入*/
public class PerOrderDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.buy_tv)
    TextView buy_tv;
    @BindView(R.id.insureInfo_tv)
    TextView insureInfo_tv;
    @BindView(R.id.insureItem_tv)
    TextView insureItem_tv;

    @BindView(R.id.call_layout)
    RelativeLayout call_layout;
    @BindView(R.id.order_layout)
    RelativeLayout order_layout;
    @BindView(R.id.askMoney_layout)
    RelativeLayout askMoney_layout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.company_iv)
    ImageView company_iv;

    @BindView(R.id.number_tv)
    TextView number_tv;

    @BindView(R.id.pay_tv)
    TextView pay_tv;

    @BindView(R.id.startTime_tv)
    TextView startTime_tv;

    @BindView(R.id.endTime_tv)
    TextView endTime_tv;

    @BindView(R.id.name_tv)
    TextView name_tv;

    @BindView(R.id.idType_tv)
    TextView idType_tv;

    @BindView(R.id.idNumber_tv)
    TextView idNumber_tv;

    @BindView(R.id.bornTime_tv)
    TextView bornTime_tv;

    @BindView(R.id.phone_tv)
    TextView phone_tv;

    @BindView(R.id.relationshap_tv)
    TextView relationshap_tv;

    @BindView(R.id.state_iv)
    ImageView state_iv;

    private GridLayoutManager manager;
    private MyPerOrderDetailAdapter adapter;

    private List<TradeDetailType.InsureListBean> totalList = new ArrayList<>();
    private String detailId = "";
    private String tradeId = "";
    private String policySts = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        detailId = intent.getStringExtra("detailId");
        tradeId = intent.getStringExtra("tradeId");
//        policySts = intent.getStringExtra("policySts");
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        buy_tv.setOnClickListener(this);
        insureInfo_tv.setOnClickListener(this);
        insureItem_tv.setOnClickListener(this);
        call_layout.setOnClickListener(this);
        order_layout.setOnClickListener(this);
        askMoney_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        loadData();

        manager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyPerOrderDetailAdapter(totalList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.buy_tv:
                showTip("再次购买");
                break;
            case R.id.insureInfo_tv:
                Toast.makeText(this, "投保须知", Toast.LENGTH_SHORT).show();
                break;
            case R.id.insureItem_tv:
                Toast.makeText(this, "保险条款", Toast.LENGTH_SHORT).show();
                break;
            case R.id.call_layout:
                Toast.makeText(this, "客服电话", Toast.LENGTH_SHORT).show();
                break;
            case R.id.order_layout:
                Toast.makeText(this, "电子保单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.askMoney_layout:
                Toast.makeText(this, "我要理赔", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 加载数据
     */
    private void loadData() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getTradeDetail(MyApplication.user.getUserId(), tradeId, detailId, "2");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api api = (Result_Api) response.body();
                if (api.getOutput() != null && api.getOutput() instanceof TradeDetail) {
                    TradeDetail tradeDetail = (TradeDetail) api.getOutput();
                    policySts = tradeDetail.getPolicySts();
                    Log.d("tttttt","-----------------------" + policySts);
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
                    GlideUtil.getInstance().loadImage(mContext,company_iv,tradeDetail.getInsurerLogo(),false);
                            title_tv.setText(tradeDetail.getProductName());
                    number_tv.setText("保单号：" + tradeDetail.getPolicyId());
                    String payMoney = tradeDetail.getTradePremium();
                    if (!TextUtils.isEmpty(payMoney)) {
                        float money = Float.parseFloat(payMoney);
                        payMoney = (int) (money / 100) + "元";
                    }
                    pay_tv.setText(payMoney);
                    startTime_tv.setText(tradeDetail.getStartTime());
                    endTime_tv.setText(tradeDetail.getEndTime());
                    name_tv.setText(tradeDetail.getApplicant());
                    String idType = "1";
                    idType = tradeDetail.getInsuredList().get(0).getIdType();
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
                    idNumber_tv.setText(tradeDetail.getInsuredList().get(0).getIdNo());
                    bornTime_tv.setText(tradeDetail.getInsuredList().get(0).getBirthday());
                    phone_tv.setText(tradeDetail.getInsuredList().get(0).getMobile());
                    relationshap_tv.setText(tradeDetail.getInsuredList().get(0).getInsuredName());
                    List<TradeDetailType.InsureListBean> list = tradeDetail.getTypeList().get(0).getInsureList();
                    if (totalList.size() > 0) {
                        totalList.clear();
                    }
                    totalList.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
