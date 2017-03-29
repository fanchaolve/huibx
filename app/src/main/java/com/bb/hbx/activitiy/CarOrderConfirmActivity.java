package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.CarOrderConfirmTableBean;
import com.bb.hbx.bean.CarOrderConfirmTableSYXBean;
import com.bb.hbx.bean.GetCarInsCalcBean;
import com.bb.hbx.provide.CarOrderConfirmTableProvider;
import com.bb.hbx.provide.CarOrderConfirmTableSYXProvider;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.RecycleViewDivider;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//车险订单确认
public class CarOrderConfirmActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.recyclerViewJQX)
    RecyclerView recyclerViewJQX;
    @BindView(R.id.recyclerViewSYX)
    RecyclerView recyclerViewSYX;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.tv_buy)
    TextView tv_buy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_order_confirm;
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
    }

    @Override
    public void initListener() {
        tv_buy.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        GetCarInsCalcBean getCarInsCalcBean = bundle.getParcelable("GetCarInsCalcBean");//其中的benefitList需要单独传,,为null
        ArrayList<GetCarInsCalcBean.BenefitListBean> benefitList = bundle.getParcelableArrayList("benefitList");
        for (int i = 0; i < benefitList.size(); i++) {
            benefitList.get(i).getChooseAmount();
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewJQX.setLayoutManager(manager);
        MultiTypeAdapter adapterJQX = new MultiTypeAdapter();
        adapterJQX.applyGlobalMultiTypePool();
        //adapterJQX.register(TradeDetailType.InsureListBean.class, new PolicyFormProvide());
        adapterJQX.register(CarOrderConfirmTableBean.class, new CarOrderConfirmTableProvider());
        recyclerViewJQX.setAdapter(adapterJQX);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
                AppManager.getInstance().dp2px(this, 0.5), R.color.A6);

        recyclerViewJQX.addItemDecoration(divider);
        recyclerViewJQX.setAdapter(adapterJQX);

        List<Item> itemsJQX = new ArrayList<>();
        CarOrderConfirmTableBean beanJQXTitle = new CarOrderConfirmTableBean();
        beanJQXTitle.setItemName(getString(R.string.bxx));
        beanJQXTitle.setItemPreium(getString(R.string.bf));
        itemsJQX.add(beanJQXTitle);
        CarOrderConfirmTableBean beanJQX1 = new CarOrderConfirmTableBean();
        beanJQX1.setItemName("交强险");
        beanJQX1.setItemPreium(getCarInsCalcBean.getJqPreium());
        itemsJQX.add(beanJQX1);
        CarOrderConfirmTableBean beanJQX2 = new CarOrderConfirmTableBean();
        beanJQX2.setItemName("车船税");
        beanJQX2.setItemPreium(getCarInsCalcBean.getCcsTax());
        itemsJQX.add(beanJQX2);
        adapterJQX.setItems(itemsJQX);

        LinearLayoutManager managerSYX = new LinearLayoutManager(this);
        recyclerViewSYX.setLayoutManager(managerSYX);
        MultiTypeAdapter adapterSYX = new MultiTypeAdapter();
        adapterSYX.applyGlobalMultiTypePool();
        adapterSYX.register(CarOrderConfirmTableSYXBean.class, new CarOrderConfirmTableSYXProvider());
        recyclerViewSYX.setAdapter(adapterSYX);
        RecycleViewDivider dividerSYX = new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
                AppManager.getInstance().dp2px(this, 0.5), R.color.A6);

        recyclerViewSYX.addItemDecoration(dividerSYX);
        recyclerViewSYX.setAdapter(adapterSYX);
        List<Item> itemsSYX = new ArrayList<>();
        CarOrderConfirmTableSYXBean beanSYXTitle = new CarOrderConfirmTableSYXBean();
        beanSYXTitle.setItemName("保险项");
        beanSYXTitle.setItemBaoE("保额");
        beanSYXTitle.setItemBaoF("保费");
        beanSYXTitle.setItemBuJiMiam("不计免赔");
        itemsSYX.add(beanSYXTitle);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_buy:
                Intent intent = new Intent(this, CarOrderReviewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
