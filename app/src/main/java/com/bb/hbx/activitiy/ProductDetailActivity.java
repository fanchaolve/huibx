package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.ProductDetailModle;
import com.bb.hbx.base.p.ProductDetailPresenter;
import com.bb.hbx.base.v.ProductDetailContract;
import com.bb.hbx.bean.Benefit;
import com.bb.hbx.bean.Entry;
import com.bb.hbx.bean.InsuredInfolBean;
import com.bb.hbx.bean.Plan;
import com.bb.hbx.bean.ProductParamDetail;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.utils.Utils;
import com.bb.hbx.widget.CardLayout;
import com.bb.hbx.widget.ClickAble;
import com.bb.hbx.widget.ItemLayout;
import com.bb.hbx.widget.ItemLayout2;
import com.bb.hbx.widget.PickerDialogOneWheel;
import com.bb.hbx.widget.ShareDailog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

import static com.bb.hbx.utils.Constants.beinsurer1_listvalue;
import static com.bb.hbx.utils.Constants.idTypes;


/**
 * Created by fancl.
 * 详情页
 */

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter, ProductDetailModle> implements ProductDetailContract.View,
        View.OnClickListener {


    @BindView(R.id.sl)
    ScrollView sl;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_pic)
    ImageView iv_pic;

    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.collect_iv)
    ImageView collect_iv;

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

    @BindView(R.id.il_beinsurer2)
    ItemLayout il_beinsurer2;


    @BindView(R.id.il_beinsurer4)
    ItemLayout il_beinsurer4;

    @BindView(R.id.il_insurer3)
    ItemLayout il_insurer3;

    @BindView(R.id.il_insurer4)
    ItemLayout il_insurer4;

    @BindView(R.id.il_beinsurer3)
    ItemLayout il_beinsurer3;


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

    @BindView(R.id.tv_buy)
    TextView tv_buy;

    @BindView(R.id.il_beinsurer1)
    ItemLayout il_beinsurer1;

    @BindView(R.id.layout_tab2)
    View layout_tab2;//起保时间

    @BindView(R.id.lin_count)
    LinearLayout lin_count;//购买的份数


    private ItemLayout2 layout2;

    private LinearLayout line;


    private String productId = "";


    private PickerDialogOneWheel wheel_data;

    private final static int REQUEST_GETTBR = 0x001;


    //传递接口需要的参数
    private PickerDialogOneWheel.OnTextListener textListener = new PickerDialogOneWheel.OnTextListener() {
        @Override
        public void onClick(View v, String value, int index) {
            if (v instanceof ItemLayout2) {
                ((ItemLayout2) v).setText(value);
                if ((int) (v.getTag()) == 0) {
                    mPresenter.setSelectPerids(index);
                } else {
                    //控制value的位置(tag)
                    mPresenter.setkeybeanOther((Integer) v.getTag(), value, index);
                }

            } else if (v instanceof ItemLayout) {
                if ((int) (v.getTag()) == 11) {
                    setReationShipValue(beinsurer1_listvalue[index]);
                    mPresenter.setKey(index);
                } else if ((int) (v.getTag()) == 12) {
                    setInsurerType(idTypes[index]);
                    mPresenter.setInsureridType(index);
                } else if ((int) v.getTag() == 13) {
                    setInsuredIdType(idTypes[index]);
                    mPresenter.setBeinsureridType(index);
                }
            }
        }

        @Override
        public void dissmiss(View v) {
            if (v instanceof ItemLayout2) {
                ((ItemLayout2) v).setdownImageResource();
            } else if (v instanceof ItemLayout) {
                ((ItemLayout) v).setdownImageResource();
            }
        }
    };

    @Override
    public int getLayoutId() {
        //沉浸状态栏
        initState();
        return R.layout.activity_productdetail;
    }

    @Override
    public void initView() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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
        hintSp.setSpan(new ClickAble(4, mContext), 4, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hintSp.setSpan(new ClickAble(10, mContext), 10, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hintSp.setSpan(new ClickAble(16, mContext), 16, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hintSp.setSpan(new TextAppearanceSpan(
                this, R.style.TextAppear_Theme_A3_Size12), 22, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_agree.setText(hintSp);
        tv_agree.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件


        //-------
        layout2 = (ItemLayout2) layout_tab2.findViewById(R.id.il_up1);
        line = (LinearLayout) layout_tab2.findViewById(R.id.lin_line);
        layout2.setLeft_name(getString(R.string.qbsj2));
    }

    @Override
    public void initListener() {
        tv_buy.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        collect_iv.setOnClickListener(this);
        tv_morefeature.setOnClickListener(this);
        lin_share.setOnClickListener(this);
        il_up1.setTag(0);
        il_up1.setListener(new ItemLayout2.OnUpListener() {
            @Override
            public void onClick() {
                final String[] subPerids = mPresenter.substringperids();
                if (subPerids != null && subPerids.length > 1) {
                    if (wheel_data == null) {
                        wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(subPerids), il_up1);
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
                //第几个
                mPresenter.TraversalPlans(index);
            }
        });


        il_beinsurer1.setListener(new ItemLayout.OnBtnListener() {

            @Override
            public void onClick() {
                il_beinsurer1.setTag(11);
                PickerDialogOneWheel wheel = new PickerDialogOneWheel(mContext, Arrays.asList(beinsurer1_listvalue), il_beinsurer1);
                wheel.setListener(textListener);
                wheel.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                wheel.show();
            }
        });


        il_insurer2.setTag(12);
        il_insurer2.setListener(new ItemLayout.OnBtnListener() {
            @Override
            public void onClick() {
                if (idTypes != null && idTypes.length > 1) {
                    PickerDialogOneWheel wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_insurer2);
                    wheel_data.setListener(textListener);
                    wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                    wheel_data.show();

                }
            }
        });

        il_beinsurer3.setTag(13);
        il_beinsurer3.setListener(new ItemLayout.OnBtnListener() {
            @Override
            public void onClick() {
                if (idTypes != null && idTypes.length > 1) {
                    PickerDialogOneWheel wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_beinsurer3);
                    wheel_data.setListener(textListener);
                    wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                    wheel_data.show();

                }
            }
        });

        il_insurer1.setListener(new ItemLayout.OnBtnListener() {
            @Override
            public void onClick() {

                Bundle b = new Bundle();
                b.putInt("type", Constants.GETSURED);
                AppManager.getInstance().showActivityForResult(CustomerManagerActivity.class, b, REQUEST_GETTBR);
            }
        });

    }

    @Override
    public void initdata() {
        mPresenter.getProductDetail(productId);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                mPresenter.addCount();
                break;
            case R.id.iv_sub:
                mPresenter.subCount();
                break;
            case R.id.lin_share:
                ShareDailog shareDailog = new ShareDailog(this);
                shareDailog.show();
                break;
            case R.id.tv_buy:
                //调用 提交订单 接口
                mPresenter.GetProdectDetalRequest();
                break;
            case R.id.back_iv:
                finish();
                break;
            case R.id.collect_iv://收藏
                collectProduct(productId);
                break;

        }
    }

    /**
     * 收藏商品的网络请求
     */
    private void collectProduct(String productId) {
        ApiService apiService = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = apiService.collectProduct(MyApplication.user.getUserId(),"10",productId,"add");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                if (api != null) {
                    if (api.isSuccess()) {
                        Toast.makeText(mContext,"收藏商品成功！",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void failCallback() {
                Toast.makeText(mContext,"收藏商品失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setProductDetail(ProductParamDetail detail) {

    }

    @Override
    public void iv_subVisibility() {
        iv_sub.setVisibility(View.VISIBLE);
    }

    @Override
    public void iv_subInVisibility() {
        iv_sub.setVisibility(View.INVISIBLE);
    }

    @Override
    public void iv_addVisibility() {
        iv_add.setVisibility(View.VISIBLE);
    }

    @Override
    public void iv_addInVisibility() {
        iv_add.setVisibility(View.INVISIBLE);
    }

    @Override
    public void tv_quantity_setText(int count) {
        tv_quantity.setText(count + "");
    }

    @Override
    public void loadimage(String logo) {
        //GlideUtil.getInstance().loadImage(this, iv_pic, logo, true);
    }

    @Override
    public void setText_insurname_totalmount(String insurerName, String totalAmount) {
        tv_matchall.setText(insurerName + " | 销量 " + totalAmount);
    }


    @Override
    public void setText_ProductName(String productName) {
        tv_name.setText(productName);
    }

    @Override
    public void setText_Feature(String productFeature, String perferWords) {
        tv_description1.setText(productFeature);
        tv_description2.setText(perferWords);
    }

    @Override
    public void setlin_countInvisiblity() {
        lin_count.setVisibility(View.GONE);
    }

    @Override
    public void setEffectiveTypewithButon(String effectDate) {
        layout2.setText(effectDate);
        layout2.setButtonGone();
    }

    @Override
    public void setlineGone() {
        line.setVisibility(View.GONE);
    }

    @Override
    public void IsBClientView(boolean isClient, String CommisionValue1) {
        if (isClient) {
            tv_pro.setVisibility(View.VISIBLE);
            tv_pro.setText(CommisionValue1 + "%推广费");
        } else {
            tv_pro.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPlanView(List<Plan> list) {
        clayout.setPlanList(list);
        clayout.setCount(list.size());
    }

    @Override
    public void setil_up1ckickenable(boolean enable) {
        il_up1.setEnable(false);
    }

    @Override
    public void setil_up1ckInvisible() {
        il_up1.setButtonGone();
    }


    @Override
    public void setil_up1Textvalue(String value) {
        il_up1.setText(value);
    }

    //查看保障详情部分,,,,,在此生成 保障时间 下的一系列保障条目
    @Override
    public void setEntryView(final Entry entry, int index) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_insured_up2, null);
        ll_add.addView(view);
        final ItemLayout2 up = (ItemLayout2) view.findViewById(R.id.il_up1);
        up.setTag(index + 1);
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

    @Override
    public void setReationShipValue(String value) {
        il_beinsurer1.setText(value);
    }

    @Override
    public void setInsuredIdType(String idTypeValue) {
        il_beinsurer3.setText(idTypeValue);
    }

    @Override
    public void setInsurerType(String idTypeValue) {
        il_insurer2.setText(idTypeValue);

    }

    @Override
    public void setPrice(String price) {

        tv_price.setText(getString(R.string.howPrice, Utils.fromFenToYuan(price)));
    }

    @Override
    public void removeLinView() {
        lin_additem.removeAllViews();
    }

    //动态生成介于 查看保障详情 之上,计划 之下的条目
    @Override
    public void addLinView(Benefit benefit) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardlayout_item, null, false);
        TextView tv_appendKey = (TextView) view.findViewById(R.id.tv_appendKey);
        TextView tv_appendValue = (TextView) view.findViewById(R.id.tv_appendValue);
        tv_appendKey.setText(benefit.getBenefitName());
        tv_appendValue.setText(benefit.getInsuredAmount());
        lin_additem.addView(view);
    }

    @Override
    public String getTBRIDEtValue() {
        return il_insurer3.getEtValue();
    }

    @Override
    public String getTBRMobileEtValue() {
        return il_insurer4.getEtValue();
    }

    @Override
    public String getTBRNameEtValue() {
        return il_insurer1.getEtValue();
    }

    @Override
    public String getBBRIDEtValue() {
        return il_beinsurer4.getEtValue();
    }

    @Override
    public String getBBRNameEtValue() {
        return il_beinsurer2.getEtValue();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_GETTBR) {
            InsuredInfolBean bean = data.getParcelableExtra(Constants.TRANSTATION);
            if (bean != null) {

            }
        }
    }
}
