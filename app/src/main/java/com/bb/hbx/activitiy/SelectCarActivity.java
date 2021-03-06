package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.ComCarPropsBean;
import com.bb.hbx.bean.NomatchCarModels;
import com.bb.hbx.provide.NomatchCarProvide;
import com.bb.hbx.provide.SelectCarProvide;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fancl
 */

public class SelectCarActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.rl_view)
    RecyclerView rl_view;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_confim)
    TextView tv_confim;

    private List<Item> items;


    private MultiTypeAdapter adapter;

    private SelectCarProvide provide;

    List<ComCarPropsBean.PlanListBean> planList=new ArrayList<>();
    int size;
    Bundle bundleFromCarInfo;
    String insurerId="";
    String serialId="";
    String city;
    String licenseNo;
    String insureName;
    String driveName="";
    String idNo="";
    String mobile="";
    String carExtras="";
    String carPrice="";
    String modelCode="";

    @Override
    public int getLayoutId() {
        return R.layout.activit_select;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rl_view.setLayoutManager(manager);
        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();
        provide = new SelectCarProvide(this);
        provide.setmOnItemClickListener(new SelectCarProvide.OnitemClick() {
            @Override
            public void onItemClick(ComCarPropsBean.CarInfoListBean data, int position) {
                adapter.notifyDataSetChanged();
                carPrice = data.getCarPrice();
                modelCode = data.getModelCode();
                showTip("carPrice:"+carPrice+"----modelCode:"+modelCode);
            }
        });
        adapter.register(ComCarPropsBean.CarInfoListBean.class, provide);
        adapter.register(NomatchCarModels.class, new NomatchCarProvide(this));
        rl_view.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_confim.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        bundleFromCarInfo = intent.getExtras();
        serialId = bundleFromCarInfo.getString("serialId");
        insurerId = bundleFromCarInfo.getString("insurerId");
        city = bundleFromCarInfo.getString("city");
        licenseNo = bundleFromCarInfo.getString("licenseNo");
        insureName = bundleFromCarInfo.getString("insureName");
        driveName = bundleFromCarInfo.getString("driveName");
        idNo = bundleFromCarInfo.getString("idNo");
        mobile = bundleFromCarInfo.getString("mobile");

        carExtras = bundleFromCarInfo.getString("carExtras");
        size = bundleFromCarInfo.getInt("size", -1);
        List<ComCarPropsBean.CarInfoListBean> carInfoList = bundleFromCarInfo.getParcelableArrayList("carinfoList");
        items = new ArrayList<>();
        /*for (int i = 0; i < 30; i++) {
            items.add(new CarModels());
        }*/
        for (int i = 0; i < carInfoList.size(); i++) {
            items.add(carInfoList.get(i));
        }
        items.add(new NomatchCarModels());
        adapter.setItems(items);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_confim:
                Bundle bundle = new Bundle();
                bundle.putString("insurerId",insurerId);
                bundle.putString("serialId",serialId);
                bundle.putString("licenseNo",licenseNo);
                bundle.putString("driveName",driveName);
                bundle.putString("idNo",idNo);
                bundle.putString("mobile",mobile);
                bundle.putString("insureName",insureName);
                bundle.putString("city",city);

                bundle.putString("carExtras",carExtras);
                bundle.putString("carPrice",carPrice);
                bundle.putString("modelCode",modelCode);
                bundle.putInt("size",size);
                if (TextUtils.isEmpty(carPrice)||TextUtils.isEmpty(modelCode))
                {
                    showTip("请选择车型");
                    return;
                }
                for (int i = 0; i < size; i++) {
                    //接受并下发商业险及其保额项
                    List<ComCarPropsBean.PlanListBean.SyxListBean> syxList = bundleFromCarInfo.getParcelableArrayList("syxList" + i);
                    bundle.putParcelableArrayList("syxList"+i, (ArrayList<? extends Parcelable>) syxList);
                    for (int j = 0; j < syxList.size(); j++) {
                        List<ComCarPropsBean.PlanListBean.SyxListBean.AmountListBeanXXX> amountList =
                                bundleFromCarInfo.getParcelableArrayList("amountList" + i + j);
                        bundle.putParcelableArrayList("amountList"+i+j, (ArrayList<? extends Parcelable>) amountList);
                    }
                    //接受并下发交强险及其保额项
                    List<ComCarPropsBean.PlanListBean.JqxListBean> jqxList = bundleFromCarInfo.getParcelableArrayList("jqxList" + i);
                    bundle.putParcelableArrayList("jqxList"+i, (ArrayList<? extends Parcelable>) jqxList);
                    for (int j = 0; j < jqxList.size(); j++) {
                        List<ComCarPropsBean.PlanListBean.JqxListBean.AmountListBeanX> amountJqxList =
                                bundleFromCarInfo.getParcelableArrayList("amountJqxList" + i + j);
                        bundle.putParcelableArrayList("amountJqxList"+i+j, (ArrayList<? extends Parcelable>) amountJqxList);
                    }
                    //接受并下发附加险及其保额项
                    List<ComCarPropsBean.PlanListBean.FjxListBean> fjxList = bundleFromCarInfo.getParcelableArrayList("fjxList" + i);
                    bundle.putParcelableArrayList("fjxList"+i, (ArrayList<? extends Parcelable>) fjxList);
                    for (int j = 0; j < fjxList.size(); j++) {
                        List<ComCarPropsBean.PlanListBean.FjxListBean.AmountListBean> amountFjxList =
                                bundleFromCarInfo.getParcelableArrayList("amountFjxList" + i + j);
                        bundle.putParcelableArrayList("amountFjxList"+i+j, (ArrayList<? extends Parcelable>) amountFjxList);
                    }
                    //接受并下发其他附加险及其保额项
                    List<ComCarPropsBean.PlanListBean.QtxListBean> qtxList = bundleFromCarInfo.getParcelableArrayList("qtxList" + i);
                    bundle.putParcelableArrayList("qtxList"+i, (ArrayList<? extends Parcelable>) qtxList);
                    for (int j = 0; j < qtxList.size(); j++) {
                        List<ComCarPropsBean.PlanListBean.QtxListBean.AmountListBeanXX> amountQtxList =
                                bundleFromCarInfo.getParcelableArrayList("amountQtxList" + i + j);
                        bundle.putParcelableArrayList("amountQtxList"+i+j, (ArrayList<? extends Parcelable>) amountQtxList);
                    }
                }

                AppManager.getInstance().showActivity(InsurancePlanActivity.class, bundle);
                break;
            default:
                break;
        }
    }
}
