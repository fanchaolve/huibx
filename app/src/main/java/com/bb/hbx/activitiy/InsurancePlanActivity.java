package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.adapter.CardPagerAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.CarInsDetail;
import com.bb.hbx.bean.ComCarPropsBean;
import com.bb.hbx.bean.GetCarInsCalcBean;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.ShadowTransformer;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/15.
 */

public class InsurancePlanActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_tb)
    ViewPager vp_tb;
    @BindView(R.id.lin_add)
    LinearLayout lin_add;
    @BindView(R.id.commit_tv)
    TextView commit_tv;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    private List<Item> mlist = new ArrayList<>();
    ArrayList<View> dotList = new ArrayList<>();
    List<ComCarPropsBean.PlanListBean> planList=new ArrayList<>();
    List<GetCarInsCalcBean.BenefitListBean> benefitTotalList=new ArrayList<>();
    GetCarInsCalcBean getCarInsCalcBean;
    int prePosition=0;//2---------------

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
        //initState();
        return R.layout.activit_insuranceplan;

    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        serialId = bundle.getString("serialId");
        city = bundle.getString("city");
        licenseNo = bundle.getString("licenseNo");
        insureName = bundle.getString("insureName");
        driveName = bundle.getString("driveName");
        idNo = bundle.getString("idNo");
        mobile = bundle.getString("mobile");

        carExtras = bundle.getString("carExtras");
        carPrice = bundle.getString("carPrice");
        modelCode = bundle.getString("modelCode");
        int size = bundle.getInt("size", -1);
        for (int i = 0; i < size; i++) {
            //接受并填充商业险及其保额项
            List<ComCarPropsBean.PlanListBean.SyxListBean> syxList = bundle.getParcelableArrayList("syxList" + i);
            for (int j = 0; j < syxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.SyxListBean.AmountListBeanXXX> amountList=bundle.getParcelableArrayList("amountList" + i + j);
                syxList.get(j).setAmountList(amountList);
            }
            //接受并填充交强险及其保额项
            List<ComCarPropsBean.PlanListBean.JqxListBean> jqxList = bundle.getParcelableArrayList("jqxList" + i);
            for (int j = 0; j < jqxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.JqxListBean.AmountListBeanX> amountJqxList=bundle.getParcelableArrayList("amountJqxList" + i + j);
                jqxList.get(j).setAmountList(amountJqxList);
            }
            //接受并填充附加险及其保额
            List<ComCarPropsBean.PlanListBean.FjxListBean> fjxList = bundle.getParcelableArrayList("fjxList" + i);
            for (int j = 0; j < fjxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.FjxListBean.AmountListBean> amountFjxList=bundle.getParcelableArrayList("amountFjxList" + i + j);
                fjxList.get(j).setAmountList(amountFjxList);
            }
            //接受并填充其他附加险及其保额
            List<ComCarPropsBean.PlanListBean.QtxListBean> qtxList = bundle.getParcelableArrayList("qtxList" + i);
            for (int j = 0; j < qtxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.QtxListBean.AmountListBeanXX> amountQtxList=bundle.getParcelableArrayList("amountQtxList" + i + j);
                qtxList.get(j).setAmountList(amountQtxList);
            }

            ComCarPropsBean.PlanListBean planListBean = new ComCarPropsBean.PlanListBean();
            //填充商业险
            planListBean.setSyxList(syxList);
            planListBean.setFjxList(fjxList);
            planListBean.setQtxList(qtxList);
            planListBean.setJqxList(jqxList);
            planList.add(planListBean);
        }

        for (int i = 0; i < planList.size(); i++) {
            /*Item item = new BannerBean();
            mlist.add(item);*/

            ImageView dotView = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
            lp.leftMargin = 10;
            dotView.setLayoutParams(lp);
            dotView.setBackgroundResource(R.drawable.dot_selected);
            dotList.add(dotView);
            lin_add.addView(dotView);
            if (i == 0) {//---------2
                dotList.get(i).setAlpha(1);
            }
            else
            {
                dotList.get(i).setAlpha(0.38f);
            }
        }

        //mCardAdapter = new CardPagerAdapter(this, mlist);
        mCardAdapter = new CardPagerAdapter(this, planList);
        mCardShadowTransformer = new ShadowTransformer(vp_tb, mCardAdapter);
        mCardShadowTransformer.setCanAlpha(true);
        mCardAdapter.setTransformer(mCardShadowTransformer);


        vp_tb.setAdapter(mCardAdapter);
        mCardAdapter.setPager(vp_tb);
        vp_tb.setPageTransformer(false, mCardShadowTransformer);
        mCardShadowTransformer.setAlpha(0.6f, true);
        mCardShadowTransformer.setScale(0.1f,true);

        //vp_tb.setCurrentItem(2);---------------
        //vp_tb.setCurrentItem(10000 / 2 - 10000 / 2 % 3);

        if (planList.size()>0)
        {
            vp_tb.setCurrentItem(0);
            getPlanPrice(0);
        }
        vp_tb.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotList.get(prePosition).setAlpha(0.38f);
                /*dotList.get(position%3).setAlpha(1);
                prePosition=position%3;*/
                dotList.get(position).setAlpha(1);
                prePosition=position;

                //获得套餐价格
                getPlanPrice(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getPlanPrice(final int position) {
        List<CarInsDetail.BenefitInCarIns> list=new ArrayList<>();
        //添加商业险的
        List<ComCarPropsBean.PlanListBean.SyxListBean> syxList = planList.get(position).getSyxList();
        for (int i = 0; i < syxList.size(); i++) {
            String chooseAmount = TextUtils.isEmpty(syxList.get(i).getChooseAmount())?"":syxList.get(i).getChooseAmount();
            String itemCode = TextUtils.isEmpty(syxList.get(i).getItemCode())?"":syxList.get(i).getItemCode();
            String franchiseFlag = TextUtils.isEmpty(syxList.get(i).getFranchiseFlag())?"":syxList.get(i).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            list.add(benefitInCarIns);
        }
        //添加交强险的
        List<ComCarPropsBean.PlanListBean.JqxListBean> jqxList = planList.get(position).getJqxList();
        for (int i = 0; i < jqxList.size(); i++) {
            String chooseAmount = TextUtils.isEmpty(jqxList.get(i).getChooseAmount())?"":jqxList.get(i).getChooseAmount();
            String itemCode = TextUtils.isEmpty(jqxList.get(i).getItemCode())?"":jqxList.get(i).getItemCode();
            String franchiseFlag = TextUtils.isEmpty(jqxList.get(i).getFranchiseFlag())?"":jqxList.get(i).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            list.add(benefitInCarIns);
        }
        CarInsDetail carInsDetail = new CarInsDetail(serialId,modelCode,carPrice,carExtras,list);
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getCarInsCalc(carInsDetail);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body.isSuccess())
                {
                    getCarInsCalcBean = (GetCarInsCalcBean) body.getOutput();
                    mCardAdapter.getTextViewList().get(position).setText("¥"+getCarInsCalcBean.getTotalPreium());
                    benefitTotalList.clear();
                    benefitTotalList.addAll(getCarInsCalcBean.getBenefitList());
                }
                else
                {
                    showTip(body.getRespMsg());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showTip("fail");
            }
        });
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCardAdapter.setmOnItemChangePlanClickListener(new CardPagerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //showTip("position:投保方案"+position);
                Bundle bundle = new Bundle();
                //传递商业险及其保额项
                bundle.putParcelableArrayList("syxList", (ArrayList<? extends Parcelable>) planList.get(position).getSyxList());
                for (int i = 0; i < planList.get(position).getSyxList().size(); i++) {
                    bundle.putParcelableArrayList("amountList"+i, (ArrayList<? extends Parcelable>) planList.get(position).getSyxList().get(i).getAmountList());
                }
                //传递交强险及其保额项
                bundle.putParcelableArrayList("jqxList", (ArrayList<? extends Parcelable>) planList.get(position).getJqxList());
                for (int i = 0; i < planList.get(position).getJqxList().size(); i++) {
                    bundle.putParcelableArrayList("amountJqxList"+i, (ArrayList<? extends Parcelable>) planList.get(position).getJqxList().get(i).getAmountList());
                }
                //传递附加险及其保额项
                bundle.putParcelableArrayList("fjxList", (ArrayList<? extends Parcelable>) planList.get(position).getFjxList());
                for (int i = 0; i < planList.get(position).getFjxList().size(); i++) {
                    bundle.putParcelableArrayList("amountFjxList"+i, (ArrayList<? extends Parcelable>) planList.get(position).getFjxList().get(i).getAmountList());
                }
                //传递其他附加险及其保额项
                bundle.putParcelableArrayList("qtxList", (ArrayList<? extends Parcelable>) planList.get(position).getQtxList());
                for (int i = 0; i < planList.get(position).getQtxList().size(); i++) {
                    bundle.putParcelableArrayList("amountQtxList"+i, (ArrayList<? extends Parcelable>) planList.get(position).getQtxList().get(i).getAmountList());
                }
                //serialId,modelCode,carPrice,carExtras
                bundle.putString("serialId",serialId);
                bundle.putString("modelCode",modelCode);
                bundle.putString("carPrice",carPrice);
                bundle.putString("carExtras",carExtras);
                AppManager.getInstance().showActivity(UpdateInsurancePlanActivity.class, bundle);
            }
        });
        mCardAdapter.setOnItemClickListener(new CardPagerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                showTip("position:"+position);
            }
        });
        commit_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.commit_tv:
                Bundle bundle = new Bundle();
                bundle.putParcelable("GetCarInsCalcBean",getCarInsCalcBean);
                bundle.putParcelableArrayList("benefitList", (ArrayList<? extends Parcelable>) benefitTotalList);
                if (getCarInsCalcBean==null)
                {
                    showTip("正在准备套餐信息");
                    return;
                }
                bundle.putString("licenseNo",licenseNo);
                bundle.putString("driveName",driveName);
                bundle.putString("idNo",idNo);
                bundle.putString("mobile",mobile);
                bundle.putString("insureName",insureName);
                bundle.putString("city",city);
                bundle.putString("carPrice",getCarInsCalcBean.getTotalPreium());
                AppManager.getInstance().showActivity(CarOrderConfirmActivity.class, bundle);
                break;
            default:
                break;
        }
    }
}
