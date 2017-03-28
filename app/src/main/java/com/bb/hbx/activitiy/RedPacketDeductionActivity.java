package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;

import com.bb.hbx.R;
import com.bb.hbx.adapter.MyRedPDeductionAdapter;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.GetUserCouponsListBean;
import com.bb.hbx.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RedPacketDeductionActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<GetUserCouponsListBean.CouponListBean> list=new ArrayList<>();
    MyRedPDeductionAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_red_packet_deduction;
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

    }

    @Override
    public void initdata() {
        GridLayoutManager manager = new GridLayoutManager(this, 1){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyRedPDeductionAdapter(this, list);
        recyclerView.setAdapter(adapter);
        if (ConfirmpaymentActivity.couponListBuff!=null)
        {
            list.addAll(ConfirmpaymentActivity.couponListBuff);
        }
        adapter.notifyDataSetChanged();
        adapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                showTip("position:"+position);
                Intent intent = new Intent();
                intent.putExtra("price",list.get(position).getCouponValue());
                intent.putExtra("name",list.get(position).getCouponName());
                setResult(110,intent);
                finish();
            }
        });
    }
}
