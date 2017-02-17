package com.bb.hbx.activitiy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.ProductDetailModle;
import com.bb.hbx.base.p.ProductDetailPresenter;
import com.bb.hbx.base.v.ProductDetailContract;
import com.bb.hbx.bean.Benefit;
import com.bb.hbx.bean.Entry;
import com.bb.hbx.bean.KeyBean;
import com.bb.hbx.bean.Plan;
import com.bb.hbx.bean.PriceTag;
import com.bb.hbx.bean.ProductParamDetail;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.CardLayout;
import com.bb.hbx.widget.ItemLayout;
import com.bb.hbx.widget.ItemLayout2;
import com.bb.hbx.widget.PickerDialogOneWheel;
import com.bb.hbx.widget.ShareDailog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


/**
 * Created by fancl.
 * 详情页
 */

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter, ProductDetailModle> implements ProductDetailContract.View,
        View.OnClickListener {


    @BindView(R.id.sl)
    ScrollView sl;

    @BindView(R.id.iv_pic)
    ImageView iv_pic;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_description1)
    TextView tv_description1;

    @BindView(R.id.tv_description2)
    TextView tv_description2;

    @BindView(R.id.tv_pro)
    TextView tv_pro;

    @BindView(R.id.tv_matchall)
    TextView tv_matchall;


    @BindView(R.id.tv_morefeature)
    TextView tv_morefeature;

    @BindView(R.id.iv_sub)
    ImageView iv_sub;

    @BindView(R.id.iv_add)
    ImageView iv_add;

    @BindView(R.id.tv_quantity)
    TextView tv_quantity;

    @BindView(R.id.tv_agree)
    TextView tv_agree;

    @BindView(R.id.il_up1)
    ItemLayout2 il_up1;


    @BindView(R.id.il_insurer1)
    ItemLayout il_insurer1;

    @BindView(R.id.il_insurer2)
    ItemLayout il_insurer2;

    @BindView(R.id.il_insurer3)
    ItemLayout il_insurer3;

    @BindView(R.id.il_insurer4)
    ItemLayout il_insurer4;


    @BindView(R.id.lin_share)
    LinearLayout lin_share;

    @BindView(R.id.clayout)
    CardLayout clayout;


    @BindView(R.id.lin_additem)
    LinearLayout lin_additem;

    @BindView(R.id.ll_add)
    LinearLayout ll_add;

    @BindView(R.id.tv_price)
    TextView tv_price;

    //

    String[] perids;

    private String selectPerids;


    private String productId = "";

    private int count = 1;


    private KeyBean keyBean;

    private PickerDialogOneWheel wheel_data;

    private ProductParamDetail productParamDetail;

    private PickerDialogOneWheel.OnTextListener textListener = new PickerDialogOneWheel.OnTextListener() {
        @Override
        public void onClick(View v, String value) {
            if (v instanceof ItemLayout2) {
                ((ItemLayout2) v).setText(value);
                if ((int) (v.getTag()) == 0) {
                    keyBean.set(keyBean.size() - 1, value);
                } else {
                    keyBean.set((Integer) v.getTag(), value);
                }
                setTextPrice();
            }
        }

        @Override
        public void dissmiss(View v) {
            if (v instanceof ItemLayout2) {
                ((ItemLayout2) v).setdownImageResource();
            }
        }
    };

    @Override
    public int getLayoutId() {
        initState();
        return R.layout.activity_productdetail;
    }

    @Override
    public void initView() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey("productId")) {
                productId = bundle.getString("productId");
            }
        }
        SpannableString hintSp = new SpannableString(getResources().getString(R.string.agreement2));
        hintSp.setSpan(new TextAppearanceSpan(
                this, R.style.TextAppear_Theme_A3_Size12), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        hintSp.setSpan(new ClickAble(4), 4, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hintSp.setSpan(new ClickAble(10), 10, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hintSp.setSpan(new ClickAble(16), 16, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//        hintSp.setSpan(new TextAppearanceSpan(
//                this, R.style.TextAppear_Theme_A1_Size12), 4, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        hintSp.setSpan(new TextAppearanceSpan(
                this, R.style.TextAppear_Theme_A3_Size12), 22, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_agree.setText(hintSp);
        tv_agree.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
    }

    @Override
    public void initListener() {


        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
        tv_morefeature.setOnClickListener(this);
        lin_share.setOnClickListener(this);
        il_up1.setTag(0);
        il_up1.setListener(new ItemLayout2.OnUpListener() {
            @Override
            public void onClick() {
                if (perids != null && perids.length > 1) {
                    if (wheel_data == null) {
                        wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(perids), il_up1);
                        wheel_data.setListener(textListener);
                        wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                    }
                    wheel_data.show();

                }


            }
        });

        clayout.setListener(new CardLayout.CardListener() {
            @Override
            public void onclick(int index) {
                if (productParamDetail != null && !productParamDetail.getPlanList().isEmpty()) {
                    //index>=1;
                    Plan plan = productParamDetail.getPlanList().get(index - 1);
                    List<Benefit> benefits = plan.getClassNameList().get(0).getBenefitList();
                    keyBean.set(0, plan.getPlanName().trim());
                    setTextPrice();
                    //----------------------------------
                    lin_additem.removeAllViews();
                    if (benefits != null && benefits.size() > 0) {

                        for (int i = 0; i < benefits.size(); i++) {
                            if (i > 3) {
                                break;
                            }
                            Benefit benefit = benefits.get(i);
                            View view = LayoutInflater.from(mContext).inflate(R.layout.cardlayout_item, null, false);
                            lin_additem.addView(view);
                            TextView tv_appendKey = (TextView) view.findViewById(R.id.tv_appendKey);
                            TextView tv_appendValue = (TextView) view.findViewById(R.id.tv_appendValue);
                            tv_appendKey.setText(benefit.getBenefitName());
                            tv_appendValue.setText(benefit.getInsuredAmount());
                        }
                    }
                }
            }
        });

    }

    @Override
    public void initdata() {
        mPresenter.getProductDetail(productId);
        tv_quantity.setText(count + "");
        keyBean = new KeyBean();
        keyBean.add(" ");


        //clayout.setCount(3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                addCount();
                break;
            case R.id.iv_sub:
                subCount();
                break;
            case R.id.lin_share:
                ShareDailog shareDailog = new ShareDailog(this);
                shareDailog.show();
                break;

        }
    }

    public void addCount() {

        if (count == 1) {
            iv_sub.setVisibility(View.VISIBLE);
        } else if (count == 9) {
            iv_add.setVisibility(View.INVISIBLE);
        }
        count++;
        tv_quantity.setText(count + "");
        setTextPrice();
    }

    public void subCount() {
        if (count == 2) {
            iv_sub.setVisibility(View.INVISIBLE);
        } else if (count == 8) {
            iv_add.setVisibility(View.VISIBLE);
        }
        count--;
        tv_quantity.setText(count + "");
        setTextPrice();
    }

    @Override
    public void setProductDetail(ProductParamDetail detail) {
        productParamDetail = detail;
        //GlideUtil.getInstance().loadImage(this, iv_pic, detail.getProductLogo(), true);
        tv_matchall.setText(detail.getInsurerName() + " | 销量 " + detail.getTotalAmount());
        tv_name.setText(detail.getProductName());

        tv_description1.setText(detail.getProductFeature());
        tv_description2.setText(detail.getPerferWords());
        if (MyApplication.user.getIsBClient()) {
            tv_pro.setVisibility(View.VISIBLE);
        } else {
            tv_pro.setVisibility(View.GONE);
        }
        if (detail.getPlanList() != null) {
            clayout.setPlanList(detail.getPlanList());
            clayout.setCount(detail.getPlanList().size());
        }

        String perid = productParamDetail.getGuaranteePeriod();
        if (perid != null && perid.indexOf(";") > -1) {
            perids = perid.split(";");
            il_up1.setText(perids[0]);
        } else {
            perids = new String[]{perid};
            il_up1.setText(perids[0]);
            il_up1.setEnable(false);
        }


        if (detail.getPriceElements() != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(detail.getPriceElements());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray jaList = jsonObject.optJSONArray("price_element");
            List<Entry> list = new ArrayList<>();
            for (int i = 0; i < jaList.length(); i++) {
                JSONObject obj = jaList.optJSONObject(i);
                final Entry entry = new Entry();
                if (obj.has("name"))
                    entry.setName(obj.optString("name"));
                if (obj.has("option")) {
                    String opt = obj.optString("option");
                    if (opt.indexOf(",") > -1) {
                        String[] opts = opt.split(",");
                        entry.setOption(Arrays.asList(opts));
                    } else {
                        String[] opts = {opt};
                        entry.setOption(Arrays.asList(opts));
                    }


                }
                list.add(entry);
                keyBean.add(entry.getOption().get(0));
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_insured_up2, null);
                ll_add.addView(view);
                final ItemLayout2 up = (ItemLayout2) view.findViewById(R.id.il_up1);
                up.setTag(i + 1);
                up.setLeft_name(entry.getName());
                if (entry.getOption() != null && !entry.getOption().isEmpty()) {
                    if (entry.getOption().size() > 1) {
                        up.setText(entry.getOption().get(0));
                        up.setListener(new ItemLayout2.OnUpListener() {
                            @Override
                            public void onClick() {
                                PickerDialogOneWheel wheel = new PickerDialogOneWheel(mContext, entry.getOption(), up);
                                wheel.setListener(textListener);
                                wheel.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                                wheel.show();
                            }
                        });
                    } else {
                        up.setText(entry.getOption().get(0));
                        up.setEnabled(false);
                    }

                }

            }

            detail.setEntries(list);


        }

        keyBean.add(perids[0]);
        setTextPrice();

    }

    private void setTextPrice() {
        double singlePrice = ischeckPrice();
        tv_price.setText("￥" + singlePrice * count);
    }


    public double ischeckPrice() {
        if (productParamDetail.getPriceList() != null && !productParamDetail.getPriceList().isEmpty()) {

            for (PriceTag tag : productParamDetail.getPriceList()) {
                if (keyBean.toString().trim().equalsIgnoreCase(tag.getPriceKeyword().trim())) {
                    return Double.valueOf(tag.getProductPremium());
                }
            }
        }
        return 0;
    }

    //Span的点击事件
    class ClickAble extends ClickableSpan {

        public int index;

        ClickAble(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View widget) {
//            Bundle bundle=new Bundle();
//            bundle.putString("","");
//            AppManager.getInstance().showActivity();
            Log.i("fancl", index + "---");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#2dce8f"));
            ds.setTextSize(AppManager.getInstance().sp2px(ProductDetailActivity.this, 12));
            ds.setUnderlineText(false);

        }
    }


}
