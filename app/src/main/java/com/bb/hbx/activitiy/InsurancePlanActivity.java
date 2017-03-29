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
        carExtras = bundle.getString("carExtras");
        carPrice = bundle.getString("carPrice");
        modelCode = bundle.getString("modelCode");
        int size = bundle.getInt("size", -1);
        for (int i = 0; i < size; i++) {
            List<ComCarPropsBean.PlanListBean.SyxListBean> syxList = bundle.getParcelableArrayList("syxList" + i);
            ComCarPropsBean.PlanListBean planListBean = new ComCarPropsBean.PlanListBean();
            planListBean.setSyxList(syxList);
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
        List<ComCarPropsBean.PlanListBean.SyxListBean> syxList = planList.get(position).getSyxList();
        for (int i = 0; i < syxList.size(); i++) {
            String chooseAmount = TextUtils.isEmpty(syxList.get(i).getChooseAmount())?"":syxList.get(i).getChooseAmount();
            String itemCode = TextUtils.isEmpty(syxList.get(i).getItemCode())?"":syxList.get(i).getItemCode();
            String franchiseFlag = TextUtils.isEmpty(syxList.get(i).getFranchiseFlag())?"":syxList.get(i).getFranchiseFlag();
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
                AppManager.getInstance().showActivity(UpdateInsurancePlanActivity.class, null);
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
                AppManager.getInstance().showActivity(CarOrderConfirmActivity.class, bundle);
                break;
            default:
                break;
        }
    }
}
