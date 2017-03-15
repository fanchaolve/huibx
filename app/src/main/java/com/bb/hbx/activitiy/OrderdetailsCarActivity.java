package com.bb.hbx.activitiy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.OrderdetaiCarlModel;
import com.bb.hbx.base.p.OrderdetailCarPresenter;
import com.bb.hbx.base.v.OrderdetailsCarContract;
import com.bb.hbx.bean.TradeDetail;
import com.bb.hbx.bean.TradeDetailType;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.provide.OrderCarFormProvide;
import com.bb.hbx.provide.PolicyFormProvide;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.TimeUtils;
import com.bb.hbx.utils.Utils;
import com.bb.hbx.widget.RecycleViewDivider;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 车险订单
 */

public class OrderdetailsCarActivity extends BaseActivity<OrderdetailCarPresenter, OrderdetaiCarlModel>
        implements OrderdetailsCarContract.View, View.OnClickListener {

    @BindView(R.id.recyclerViewJQX)
    RecyclerView recyclerViewJQX;
    @BindView(R.id.recyclerViewSYX)
    RecyclerView recyclerViewSYX;

    @BindView(R.id.tv_paytype)
    TextView tv_paytype;
    @BindView(R.id.tv_tradeid)
    TextView tv_tradeid;

    @BindView(R.id.tv_startTime)
    TextView tv_startTime;

    @BindView(R.id.tv_endTime)
    TextView tv_endTime;
    @BindView(R.id.tv_receiver)
    TextView tv_receiver;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.cardLicense_tv)
    TextView cardLicense_tv;
    @BindView(R.id.tv_carHolder)
    TextView tv_carHolder;
    @BindView(R.id.tv_cardNo)
    TextView tv_cardNo;
    @BindView(R.id.tv_carMobile)
    TextView tv_carMobile;
    @BindView(R.id.tv_cpxq)
    TextView tv_cpxq;
    @BindView(R.id.tv_tbcs)
    TextView tv_tbcs;
    @BindView(R.id.tv_tip)
    TextView tv_tip;
    @BindView(R.id.tv_confim)
    TextView tv_confim;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_realprice)
    TextView tv_realprice;

    @BindView(R.id.lin_jqx)
    LinearLayout lin_jqx;
    @BindView(R.id.lin_syx)
    LinearLayout lin_syx;
    @BindView(R.id.lin_pay)
    LinearLayout lin_pay;
    @BindView(R.id.lin_payments)
    LinearLayout lin_payments;

    private List<Item> items;
    private List<Item> itemsSYX;
    private String tradeId = "";//订单号
    private MultiTypeAdapter adapterJQX;
    private MultiTypeAdapter adapterSYX;

    private int paysts = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_orderdetail_car;
    }

    @Override
    public void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewJQX.setLayoutManager(manager);
        adapterJQX = new MultiTypeAdapter();
        adapterJQX.applyGlobalMultiTypePool();
        adapterJQX.register(TradeDetailType.InsureListBean.class, new PolicyFormProvide());
        recyclerViewJQX.setAdapter(adapterJQX);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
                AppManager.getInstance().dp2px(this, 0.5), R.color.A6);

        recyclerViewJQX.addItemDecoration(divider);
        recyclerViewJQX.setAdapter(adapterJQX);

        LinearLayoutManager managerSYX = new LinearLayoutManager(this);
        recyclerViewSYX.setLayoutManager(managerSYX);
        adapterSYX = new MultiTypeAdapter();
        adapterSYX.applyGlobalMultiTypePool();
        adapterSYX.register(TradeDetailType.InsureListBean.class, new OrderCarFormProvide());
        recyclerViewSYX.setAdapter(adapterSYX);
        RecycleViewDivider dividerSYX = new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
                AppManager.getInstance().dp2px(this, 0.5), R.color.A6);

        recyclerViewSYX.addItemDecoration(dividerSYX);
        recyclerViewSYX.setAdapter(adapterSYX);
    }

    @Override
    public void initListener() {

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
            mPresenter.getTradeDetail(tradeId, "", "1");
        }

        items = new ArrayList<>();
        TradeDetailType.InsureListBean bean = new TradeDetailType.InsureListBean();
        bean.setInsureName(getString(R.string.bxx));
        bean.setInsureAmount(getString(R.string.bf));
        items.add(bean);
        adapterJQX.setItems(items);

        itemsSYX = new ArrayList<>();



    }

    @Override
    public void onClick(View v) {

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

        tv_startTime.setText(TimeUtils.formatDate(detail.getStartTime()));
        tv_endTime.setText(TimeUtils.formatDate(detail.getEndTime()));
        tv_receiver.setText(detail.getApplicant());//-------------收件人?
        //tv_idtype.setText(StringUtils.idTostring(detail.getApplicantType()));------------无证件类型
        //tv_idNo.setText(detail.getApplicantIdNo());
        tv_phone.setText(detail.getApplicantMobile());//邮寄电话
        cardLicense_tv.setText(detail.getInsuredList().get(0).getCarInfo().getLicenseNo());
        tv_tbcs.setText(detail.getInsuredList().get(0).getCarInfo().getCityName());
        tv_cpxq.setText(detail.getProductName());
        tv_price.setText(getString(R.string.howPrice, Utils.fromFenToYuan(detail.getSumPremium())));
        tv_realprice.setText(getString(R.string.howPrice, Utils.fromFenToYuan(detail.getPayAmount())));
        if (detail.getTypeList() != null && detail.getTypeList().size() > 0) {
            items.addAll(detail.getTypeList().get(0).getInsureList());
            adapterJQX.notifyDataSetChanged();
        }
        if (detail.getInsuredList() != null && detail.getInsuredList().size() > 0) {
            //tv_reation.setText(StringUtils.reationTostring(detail.getInsuredList().get(0).getRelation()));
        }

        tv_tip.setText(getString(R.string.tip2, detail.getInsurerName(), detail.getInsurerTels()));


        TradeDetailType.InsureListBean beanSYX = new TradeDetailType.InsureListBean();
        TradeDetailType.ExtraInsureListBean extraInsureListBean = new TradeDetailType.ExtraInsureListBean();
        beanSYX.setInsureName("保险项");
        beanSYX.setInsureAmount("保额");
        extraInsureListBean.setExtraInsureAmount("保费");
        extraInsureListBean.setExtraInsureName("不计免赔");
        List<TradeDetailType.ExtraInsureListBean> exlist=new ArrayList<>();
        exlist.add(extraInsureListBean);
        beanSYX.setExtraInsureList(exlist);
        /*itemsSYX.add(extraInsureListBean);
        itemsSYX.add(beanSYX);*/
        itemsSYX.add(beanSYX);
        List<TradeDetailType.InsureListBean> listBeen = detail.getTypeList().get(1).getInsureList();
        itemsSYX.addAll(listBeen);
        adapterSYX.setItems(itemsSYX);
    }

}
