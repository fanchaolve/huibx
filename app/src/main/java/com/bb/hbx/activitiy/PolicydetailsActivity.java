package com.bb.hbx.activitiy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.PolicydetailModel;
import com.bb.hbx.base.p.PolicydetailPresenter;
import com.bb.hbx.base.v.PolicydetailsContract;
import com.bb.hbx.bean.TradeDetail;
import com.bb.hbx.bean.TradeDetailType;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.provide.PolicyFormProvide;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.StringUtils;
import com.bb.hbx.utils.Utils;
import com.bb.hbx.widget.RecycleViewDivider;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fancl
 * 订单(保单)详情
 * （个险）
 */

public class PolicydetailsActivity extends BaseActivity<PolicydetailPresenter, PolicydetailModel>
        implements PolicydetailsContract.View, View.OnClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_tradeid)
    TextView tv_tradeid;

    @BindView(R.id.tv_startTime)
    TextView tv_startTime;

    @BindView(R.id.tv_endTime)
    TextView tv_endTime;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_idtype)
    TextView tv_idtype;

    @BindView(R.id.tv_idNo)
    TextView tv_idNo;

    @BindView(R.id.tv_tel)
    TextView tv_tel;

    @BindView(R.id.tv_productName)
    TextView tv_productName;


    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.tv_realprice)
    TextView tv_realprice;

    @BindView(R.id.tv_tip)
    TextView tv_tip;

    @BindView(R.id.iv_back)
    ImageView iv_back;
    //

    @BindView(R.id.tv_paytype)
    TextView tv_paytype;

    @BindView(R.id.tv_reation)
    TextView tv_reation;
    //

    @BindView(R.id.lin_pay)
    LinearLayout lin_pay;

    @BindView(R.id.lin_payments)
    LinearLayout lin_payments;


    @BindView(R.id.tv_confim)
    TextView tv_confim;


    private MultiTypeAdapter adapter;

    private List<Item> items;


    private String tradeId = "";//订单号

    private int paysts = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_policydetails;
    }

    @Override
    public void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();
        adapter.register(TradeDetailType.InsureListBean.class, new PolicyFormProvide());
        recyclerView.setAdapter(adapter);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
                AppManager.getInstance().dp2px(this, 0.5), R.color.A6);

        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        iv_back.setOnClickListener(this);
        tv_confim.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("tradeId")) {
                tradeId = bundle.getString("tradeId");
            }
        }

        if (!tradeId.isEmpty()) {
            mPresenter.getTradeDetail(tradeId, "","2");
        }

        items = new ArrayList<>();
        TradeDetailType.InsureListBean bean = new TradeDetailType.InsureListBean();
        bean.setInsureName(getString(R.string.bxzr));
        bean.setInsureAmount(getString(R.string.be));
        items.add(bean);
        adapter.setItems(items);


    }

    @Override
    public void getTradeDetail(TradeDetail detail) {
        tv_tradeid.setText(tradeId);
        paysts = detail.getSts();
        if (paysts == 10) {
            tv_paytype.setText("待支付");
            lin_pay.setVisibility(View.GONE);
            tv_confim.setText("去支付");
        } else if (paysts == 20) {
            tv_paytype.setText("已支付");
            lin_pay.setVisibility(View.VISIBLE);
            tv_confim.setText("再次购买");
            DatabaseImpl.getInstance().updateUser("1");
            if (detail.getPaymentList() != null && !detail.getPaymentList().isEmpty()) {
                for (TradeDetail.InsuredListBean.Payment payment : detail.getPaymentList()) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_payment, null);
                    TextView tv_paykey = (TextView) view.findViewById(R.id.tv_paykey);
                    TextView tv_payvalue = (TextView) view.findViewById(R.id.tv_payvalue);
                    tv_paykey.setText(getString(R.string.dk, payment.getPaymentName()));
                    tv_payvalue.setText(getString(R.string.howPrice, payment.getPayAmount()));
                    lin_payments.addView(view);
                }
            }

        } else if (paysts == -11) {
            tv_paytype.setText("已失效");
            lin_pay.setVisibility(View.GONE);
            tv_confim.setText("去购买");
        }

        /*tv_startTime.setText(TimeUtils.formatDate(detail.getStartTime()));
        tv_endTime.setText(TimeUtils.formatDate(detail.getEndTime()));*/
        tv_startTime.setText(detail.getStartTime());
        tv_endTime.setText(detail.getEndTime());
        tv_name.setText(detail.getApplicant());
        tv_idtype.setText(StringUtils.idTostring(detail.getApplicantType()));
        tv_idNo.setText(detail.getApplicantIdNo());
        tv_tel.setText(detail.getApplicantMobile());
        tv_productName.setText(detail.getProductName());
        tv_price.setText(getString(R.string.howPrice, Utils.fromFenToYuan(detail.getSumPremium())));
        tv_realprice.setText(getString(R.string.howPrice, Utils.fromFenToYuan(detail.getPayAmount())));
        if (detail.getTypeList() != null && detail.getTypeList().size() > 0) {
            items.addAll(detail.getTypeList().get(0).getInsureList());
            adapter.notifyDataSetChanged();
        }
        if (detail.getInsuredList() != null && detail.getInsuredList().size() > 0) {
            tv_reation.setText(StringUtils.reationTostring(detail.getInsuredList().get(0).getRelation()));
        }

        tv_tip.setText(getString(R.string.tip2, detail.getInsurerName(), detail.getInsurerTels()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_confim:
                if (paysts == 10) {
                    this.finish();
                } else if (paysts == 20) {
                    this.finish();
                    AppManager.getInstance().finishParticularActivity(ConfirmpaymentActivity.class);
                    AppManager.getInstance().finishParticularActivity(ProductDetailActivity.class);
                    AppManager.getInstance().showActivity(HomeActivity.class, null);
                } else if (paysts == -11) {
                    this.finish();
                    AppManager.getInstance().finishActivity(ConfirmpaymentActivity.class);
                }
                break;
        }
    }
}
