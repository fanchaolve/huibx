package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.CarInfomationModel;
import com.bb.hbx.base.p.CarInfomationPresenter;
import com.bb.hbx.base.v.CarInfomationContract;
import com.bb.hbx.bean.Area;
import com.bb.hbx.bean.Product;
import com.bb.hbx.interfaces.OnAreaSelectedListener;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.AddressSelectorSelf;
import com.bb.hbx.widget.BottomDialogSelfData;


import java.util.List;

import butterknife.BindView;

/**
 * Created by fancl
 * 车险 选择城市 和车牌号码
 */

public class CarInformationActivity extends BaseActivity<CarInfomationPresenter, CarInfomationModel> implements
        CarInfomationContract.View, View.OnClickListener,
        OnAreaSelectedListener, AddressSelectorSelf.OnDialogCloseListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.tv_city)
    TextView tv_city;


    @BindView(R.id.et_carid)
    EditText et_carid;

    @BindView(R.id.cb_wei)
    CheckBox cb_wei;

    @BindView(R.id.tv_lishi)
    TextView tv_lishi;

    @BindView(R.id.tv_search)
    TextView tv_search;

    @BindView(R.id.iv_banner)
    ImageView iv_banner;

    private Product product;

    private BottomDialogSelfData dialog;


    @Override
    public int getLayoutId() {
        return R.layout.activit_carinfo;
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
        tv_search.setOnClickListener(this);
        tv_lishi.setOnClickListener(this);
        tv_city.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey("product")) {
                product = (Product) bundle.getParcelable("product");
                if (product!=null)
                {
                    tv_title.setText(product.getInsurerName());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                //AppManager.getInstance().showActivity(SelectCarActivity.class, null);
//                AppManager.getInstance().showActivity(UpdateInsurancePlanActivity.class, null);
                AppManager.getInstance().showActivity(CarInformationFillInActivity.class, null);
                break;
            case R.id.tv_city:
                if (dialog == null) {
                    dialog = new BottomDialogSelfData(this);
                    dialog.setOnAreaSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.setTextSize(14);//设置字体的大小
                    dialog.setTextSelectedColor(R.color.A1);
                    dialog.setTextUnSelectedColor(R.color.A3);
                    dialog.setIndicatorBackgroundColor(R.color.A1);
                    mPresenter.getProvicesCarAreas("110");
                    //mPresenter.getProvicesCarAreas(product.getInsurerId());
                }
                dialog.show();
                break;

        }
    }

    @Override
    public void onAreaProvinceSelected(Area province) {
        if (province != null)
            mPresenter.getcitiesCarAreas("110", province.getAreaCode());

    }

    @Override
    public void onAreaCitySelected(Area city) {
        tv_city.setText(city.getAreaName());
        et_carid.setText(city.getLicensePreff());
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void getProvices(List<Area> prvoinces) {
        if (dialog != null)
            dialog.getProvinces(prvoinces);
    }

    @Override
    public void getCities(List<Area> cities) {
        if (dialog != null)
            dialog.getCitylist(cities);
    }
}
