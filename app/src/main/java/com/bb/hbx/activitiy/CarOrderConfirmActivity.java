package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;

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
