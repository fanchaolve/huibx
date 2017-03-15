package com.bb.hbx.activitiy;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bb.hbx.R;
import com.bb.hbx.adapter.CardPagerAdapter;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.BannerBean;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.ShadowTransformer;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    private List<Item> mlist = new ArrayList<>();
    ArrayList<View> dotList = new ArrayList<>();

    int prePosition=2;
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

        for (int i = 0; i < 3; i++) {
            Item item = new BannerBean();
            mlist.add(item);

            ImageView dotView = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
            lp.leftMargin = 10;
            dotView.setLayoutParams(lp);
            dotView.setBackgroundResource(R.drawable.dot_selected);
            dotList.add(dotView);
            lin_add.addView(dotView);
            if (i == 2) {
                dotList.get(i).setAlpha(1);
            }
            else
            {
                dotList.get(i).setAlpha(0.38f);
            }
        }

        mCardAdapter = new CardPagerAdapter(this, mlist);
        mCardShadowTransformer = new ShadowTransformer(vp_tb, mCardAdapter);
        mCardShadowTransformer.setCanAlpha(true);
        mCardAdapter.setTransformer(mCardShadowTransformer);


        vp_tb.setAdapter(mCardAdapter);
        mCardAdapter.setPager(vp_tb);
        vp_tb.setPageTransformer(false, mCardShadowTransformer);
        mCardShadowTransformer.setAlpha(0.6f, true);
        mCardShadowTransformer.setScale(0.1f,true);

        vp_tb.setCurrentItem(2);
        //vp_tb.setCurrentItem(10000 / 2 - 10000 / 2 % 3);

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId())
        {
            case iv_back:
                finish();
                break;
            default:
                break;
        }*/
    }
}
