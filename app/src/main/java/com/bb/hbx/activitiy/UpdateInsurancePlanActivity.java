package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.CarInsDetail;
import com.bb.hbx.bean.ComCarPropsBean;
import com.bb.hbx.widget.ItemLayout2;
import com.bb.hbx.widget.ItemLayout3;
import com.bb.hbx.widget.PickerDialogOneWheel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by fancl.
 * 调整投保方案
 */

public class UpdateInsurancePlanActivity extends BaseActivity implements
        View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.il_jqx)
    ItemLayout3 il_jqx;

    @BindView(R.id.ll_syx)
    LinearLayout ll_syx;
    @BindView(R.id.ll_fjx)
    LinearLayout ll_fjx;
    @BindView(R.id.ll_qtx)
    LinearLayout ll_qtx;

    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.tv_recover)
    TextView tv_recover;

    public static final String[] idTypes = {"投保", "不投保"};

    List<ComCarPropsBean.PlanListBean.SyxListBean> syxList;
    List<ItemLayout3> syxViewList=new ArrayList<>();
    List<String> itemList=new ArrayList<>();

    List<ComCarPropsBean.PlanListBean.FjxListBean> fjxList;
    List<ItemLayout3> fjxViewList=new ArrayList<>();
    List<String> itemFjxList=new ArrayList<>();

    List<ComCarPropsBean.PlanListBean.QtxListBean> qtxList;
    List<ItemLayout3> qtxViewList=new ArrayList<>();
    List<String> itemQtxList=new ArrayList<>();

    List<ComCarPropsBean.PlanListBean.JqxListBean> jqxList;

    //存放查询用的参数
    List<ComCarPropsBean.PlanListBean.SyxListBean> syxNewList=new ArrayList<>();
    List<ComCarPropsBean.PlanListBean.JqxListBean> jqxNewList=new ArrayList<>();
    List<ComCarPropsBean.PlanListBean.FjxListBean> fjxNewList=new ArrayList<>();
    List<ComCarPropsBean.PlanListBean.QtxListBean> qtxNewList=new ArrayList<>();

    List<CarInsDetail.BenefitInCarIns> benefitList=new ArrayList<>();
    //serialId,modelCode,carPrice,carExtras
    String serialId="";
    String modelCode="";
    String carPrice="";
    String carExtras="";

    private PickerDialogOneWheel.OnTextListener textListener = new PickerDialogOneWheel.OnTextListener() {
        @Override
        public void onClick(View v, String value, int index) {
            if (v instanceof ItemLayout3) {
                if ((int) (v.getTag()) == 12)
                ((ItemLayout3) v).setText(value);

                String itemCode = jqxList.get(0).getItemCode();
                String chooseAmount=value;
                String franchiseFlag="-1";
                if ("投保".equals(value))
                {
                    chooseAmount="1";
                }
                else if ("不投保".equals(value))
                {
                    chooseAmount="0";
                }
                if (!("-1".equals(jqxList.get(0).getFranchiseFlag())))
                {
                    franchiseFlag=il_jqx.isCb_ceter()?"1":"0";
                }
                CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
                removeRepeat(itemCode);
                benefitList.add(benefitInCarIns);
            }

        }

        @Override
        public void dissmiss(View v) {
            if (v instanceof ItemLayout2) {
                ((ItemLayout2) v).setdownImageResource();
            } else if (v instanceof ItemLayout3) {
                ((ItemLayout3) v).setdownImageResource();
            }
        }
    };

    private void changeSYXItem(int tag, String value) {
        for (int i = 0; i < syxViewList.size(); i++) {
            if (tag==i)
            {
                syxViewList.get(i).setText(value);
                String itemCode = syxList.get(tag).getItemCode();
                String chooseAmount=value;
                String franchiseFlag="-1";
                if ("投保".equals(value))
                {
                    chooseAmount="1";
                }
                else if ("不投保".equals(value))
                {
                    chooseAmount="0";
                }
                if (!("-1".equals(syxList.get(tag).getFranchiseFlag())))
                {
                    franchiseFlag=syxViewList.get(tag).isCb_ceter()?"1":"0";
                }
                CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
                removeRepeat(itemCode);
                benefitList.add(benefitInCarIns);
            }
        }
    }

    private void changeFJXItem(int tag, String value) {
        for (int i = 0; i < fjxViewList.size(); i++) {
            if (tag==i)
            {
                fjxViewList.get(i).setText(value);
                String itemCode = fjxList.get(tag).getItemCode();
                String chooseAmount=value;
                String franchiseFlag="-1";
                if ("投保".equals(value))
                {
                    chooseAmount="1";
                }
                else if ("不投保".equals(value))
                {
                    chooseAmount="0";
                }
                if (!("-1".equals(fjxList.get(tag).getFranchiseFlag())))
                {
                    franchiseFlag=fjxViewList.get(tag).isCb_ceter()?"1":"0";
                }
                CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
                removeRepeat(itemCode);
                benefitList.add(benefitInCarIns);
            }
        }
    }

    private void changeQTXItem(int tag, String value) {
        for (int i = 0; i < qtxViewList.size(); i++) {
            if (tag==i)
            {
                qtxViewList.get(i).setText(value);
                String itemCode = qtxList.get(tag).getItemCode();
                String chooseAmount=value;
                String franchiseFlag="-1";
                if ("投保".equals(value))
                {
                    chooseAmount="1";
                }
                else if ("不投保".equals(value))
                {
                    chooseAmount="0";
                }
                if (!("-1".equals(qtxList.get(tag).getFranchiseFlag())))
                {
                    franchiseFlag=qtxViewList.get(tag).isCb_ceter()?"1":"0";
                }
                CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
                removeRepeat(itemCode);
                benefitList.add(benefitInCarIns);
            }
        }
    }

    //去重
    private void removeRepeat(String itemCode) {
        if (benefitList.size()>0)
        {
            int index=-1;
            for (int i = 0; i < benefitList.size(); i++) {
                if (itemCode.equals(benefitList.get(i).getItemCode()))
                {
                    index=i;
                    break;
                }
            }
            if (-1!=index)
            {
                benefitList.remove(index);
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.update_insuranceplan;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //serialId,modelCode,carPrice,carExtras
        serialId=bundle.getString("serialId");
        modelCode=bundle.getString("modelCode");
        carPrice=bundle.getString("carPrice");
        carExtras=bundle.getString("carExtras");

        //显示交强险
        jqxList=bundle.getParcelableArrayList("jqxList");

        if (jqxList.size()>0)
        {
            //赋初值
            String itemCode = jqxList.get(0).getItemCode();
            String chooseAmount=jqxList.get(0).getChooseAmount();
            String franchiseFlag=jqxList.get(0).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            removeRepeat(itemCode);
            benefitList.add(benefitInCarIns);

            if ("-1".equals(jqxList.get(0).getFranchiseFlag()))//无不计免赔
            {
                il_jqx.setCenter_visible(false);
            }
            else if ("1".equals(jqxList.get(0).getFranchiseFlag()))
            {
                il_jqx.setCenter_visible(true);
                il_jqx.setCb_ceterChecked(true);
            }
            else
            {
                il_jqx.setCenter_visible(true);
                il_jqx.setCb_ceterChecked(false);
            }
            if ("1".equals(jqxList.get(0).getChooseAmount()))
            {
                il_jqx.setText("投保");
            }
            else if ("0".equals(jqxList.get(0).getChooseAmount()))
            {
                il_jqx.setText("未投保");
            }
            else
            {
                il_jqx.setText(jqxList.get(0).getChooseAmount());
            }
        }

        //动态生成商业险条目
        syxList=bundle.getParcelableArrayList("syxList");
        for (int i = 0; i < syxList.size(); i++) {
            for (int j = 0; j < syxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.SyxListBean.AmountListBeanXXX> amountList = bundle.getParcelableArrayList("amountList" + j);
                syxList.get(j).setAmountList(amountList);
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_undate_carinfo, null);
            final ItemLayout3 il_item = (ItemLayout3) view.findViewById(R.id.il_item);
            ll_syx.addView(view);
            syxViewList.add(il_item);
            il_item.setLeft_name(syxList.get(i).getItemName());

            //赋初值
            String itemCode = syxList.get(i).getItemCode();
            String chooseAmount=syxList.get(i).getChooseAmount();
            String franchiseFlag=syxList.get(i).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            removeRepeat(itemCode);
            benefitList.add(benefitInCarIns);

            if ("-1".equals(syxList.get(i).getFranchiseFlag()))//无不计免赔
            {
                il_item.setCenter_visible(false);
            }
            else if ("1".equals(syxList.get(i).getFranchiseFlag()))
            {
                il_item.setCenter_visible(true);
                il_item.setCb_ceterChecked(true);
            }
            else
            {
                il_item.setCenter_visible(true);
                il_item.setCb_ceterChecked(false);
            }
            if ("1".equals(syxList.get(i).getChooseAmount()))
            {
                il_item.setText("投保");
            }
            else if ("0".equals(syxList.get(i).getChooseAmount()))
            {
                il_item.setText("未投保");
            }
            else
            {
                il_item.setText(syxList.get(i).getChooseAmount());
            }
            il_item.setTag(i);
            il_item.setListener(new ItemLayout3.OnUpListener() {
                @Override
                public void onClick() {
                    if (idTypes != null && idTypes.length > 1) {
                        PickerDialogOneWheel wheel_data=null;
                        if ("1".equals(syxList.get((int)il_item.getTag()).getChooseAmount())||"0".equals(syxList.get((int)il_item.getTag()).getChooseAmount()))
                        {
                            wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_item);
                        }
                        else
                        {
                            itemList.clear();
                            for (int j = 0; j < syxList.get((int)il_item.getTag()).getAmountList().size(); j++) {
                                if ("1".equals(syxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode()))
                                {
                                    itemList.add("投保");
                                }
                                else if ("0".equals(syxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode()))
                                {
                                    itemList.add("不投保");
                                }
                                else
                                {
                                    itemList.add(syxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode());
                                }
                            }
                            wheel_data = new PickerDialogOneWheel(mContext, itemList, il_item);
                        }
                        wheel_data.setListener(new PickerDialogOneWheel.OnTextListener() {
                            @Override
                            public void onClick(View pv, String value, int index) {
                                changeSYXItem((int) (pv.getTag()),value);
                            }

                            @Override
                            public void dissmiss(View pv) {
                                ((ItemLayout3) pv).setdownImageResource();
                            }
                        });
                        wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                        wheel_data.show();

                    }
                }
            });
        }

        //动态生成附加险条目
        fjxList = bundle.getParcelableArrayList("fjxList");
        for (int i = 0; i < fjxList.size(); i++) {
            for (int j = 0; j < fjxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.FjxListBean.AmountListBean> amountFjxList = bundle.getParcelableArrayList("amountFjxList" + j);
                fjxList.get(j).setAmountList(amountFjxList);
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_undate_carinfo, null);
            final ItemLayout3 il_item = (ItemLayout3) view.findViewById(R.id.il_item);
            ll_fjx.addView(view);
            fjxViewList.add(il_item);
            il_item.setLeft_name(fjxList.get(i).getItemName());
            //il_item.setCenter_visible(false);
            if ("-1".equals(fjxList.get(i).getFranchiseFlag()))//无不计免赔
            {
                il_item.setCenter_visible(false);
            }
            else if ("1".equals(fjxList.get(i).getFranchiseFlag()))
            {
                il_item.setCenter_visible(true);
                il_item.setCb_ceterChecked(true);
            }
            else
            {
                il_item.setCenter_visible(true);
                il_item.setCb_ceterChecked(false);
            }
            if ("1".equals(fjxList.get(i).getChooseAmount()))
            {
                il_item.setText("投保");
            }
            else if ("0".equals(fjxList.get(i).getChooseAmount()))
            {
                il_item.setText("未投保");
            }
            else
            {
                il_item.setText(fjxList.get(i).getChooseAmount());
            }
            il_item.setTag(i);
            il_item.setListener(new ItemLayout3.OnUpListener() {
                @Override
                public void onClick() {
                    if (idTypes != null && idTypes.length > 1) {
                        PickerDialogOneWheel wheel_data=null;
                        if ("1".equals(fjxList.get((int)il_item.getTag()).getChooseAmount())||"0".equals(fjxList.get((int)il_item.getTag()).getChooseAmount()))
                        {
                            wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_item);
                        }
                        else
                        {
                            itemFjxList.clear();
                            for (int j = 0; j < fjxList.get((int)il_item.getTag()).getAmountList().size(); j++) {
                                if ("1".equals(fjxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode()))
                                {
                                    itemFjxList.add("投保");
                                }
                                else if ("0".equals(fjxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode()))
                                {
                                    itemFjxList.add("不投保");
                                }
                                else
                                {
                                    itemFjxList.add(fjxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode());
                                }
                            }
                            wheel_data = new PickerDialogOneWheel(mContext, itemFjxList, il_item);
                        }
                        wheel_data.setListener(new PickerDialogOneWheel.OnTextListener() {
                            @Override
                            public void onClick(View pv, String value, int index) {
                                changeFJXItem((int) (pv.getTag()),value);
                            }

                            @Override
                            public void dissmiss(View pv) {
                                ((ItemLayout3) pv).setdownImageResource();
                            }
                        });
                        wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                        wheel_data.show();

                    }
                }
            });
        }

        //动态生成其他附加险
        qtxList = bundle.getParcelableArrayList("qtxList");
        for (int i = 0; i < qtxList.size(); i++) {
            for (int j = 0; j < qtxList.size(); j++) {
                List<ComCarPropsBean.PlanListBean.QtxListBean.AmountListBeanXX> amountQtxList = bundle.getParcelableArrayList("amountQtxList" + j);
                qtxList.get(j).setAmountList(amountQtxList);
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_undate_carinfo, null);
            final ItemLayout3 il_item = (ItemLayout3) view.findViewById(R.id.il_item);
            ll_qtx.addView(view);
            qtxViewList.add(il_item);
            il_item.setLeft_name(qtxList.get(i).getItemName());
            //il_item.setCenter_visible(false);
            if ("-1".equals(qtxList.get(i).getFranchiseFlag()))//无不计免赔
            {
                il_item.setCenter_visible(false);
            }
            else if ("1".equals(qtxList.get(i).getFranchiseFlag()))
            {
                il_item.setCenter_visible(true);
                il_item.setCb_ceterChecked(true);
            }
            else
            {
                il_item.setCenter_visible(true);
                il_item.setCb_ceterChecked(false);
            }
            if ("1".equals(qtxList.get(i).getChooseAmount()))
            {
                il_item.setText("投保");
            }
            else if ("0".equals(qtxList.get(i).getChooseAmount()))
            {
                il_item.setText("未投保");
            }
            else
            {
                il_item.setText(qtxList.get(i).getChooseAmount());
            }
            il_item.setTag(i);
            il_item.setListener(new ItemLayout3.OnUpListener() {
                @Override
                public void onClick() {
                    if (idTypes != null && idTypes.length > 1) {
                        PickerDialogOneWheel wheel_data=null;
                        if ("1".equals(qtxList.get((int)il_item.getTag()).getChooseAmount())||"0".equals(qtxList.get((int)il_item.getTag()).getChooseAmount()))
                        {
                            wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_item);
                        }
                        else
                        {
                            itemQtxList.clear();
                            for (int j = 0; j < qtxList.get((int)il_item.getTag()).getAmountList().size(); j++) {
                                if ("1".equals(qtxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode()))
                                {
                                    itemQtxList.add("投保");
                                }
                                else if ("0".equals(qtxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode()))
                                {
                                    itemQtxList.add("不投保");
                                }
                                else {
                                    itemQtxList.add(qtxList.get((int)il_item.getTag()).getAmountList().get(j).getAmountCode());
                                }
                            }
                            wheel_data = new PickerDialogOneWheel(mContext, itemQtxList, il_item);
                        }
                        wheel_data.setListener(new PickerDialogOneWheel.OnTextListener() {
                            @Override
                            public void onClick(View pv, String value, int index) {
                                changeQTXItem((int) (pv.getTag()),value);
                            }

                            @Override
                            public void dissmiss(View pv) {
                                ((ItemLayout3) pv).setdownImageResource();
                            }
                        });
                        wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                        wheel_data.show();

                    }
                }
            });
        }
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("customSyxPlan", (ArrayList<? extends Parcelable>) syxList);
                intent.putParcelableArrayListExtra("customJqxPlan", (ArrayList<? extends Parcelable>) jqxList);
                intent.putParcelableArrayListExtra("customFjxPlan", (ArrayList<? extends Parcelable>) fjxList);
                intent.putParcelableArrayListExtra("customQtxPlan", (ArrayList<? extends Parcelable>) qtxList);
                setResult(200, intent);
                finish();
            }
        });
        tv_search.setOnClickListener(this);
        tv_recover.setOnClickListener(this);
        //il_jqx.setOnClickListener(this);
    }

    @Override
    public void initdata() {


        il_jqx.setTag(12);
        il_jqx.setListener(new ItemLayout3.OnUpListener() {
            @Override
            public void onClick() {
                if (idTypes != null && idTypes.length > 1) {
                    PickerDialogOneWheel wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_jqx);
                    wheel_data.setListener(textListener);
                    wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                    wheel_data.show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_search:
                searchForPrice();
                break;
            case R.id.tv_recover:
                repeatItem();
                break;
            default:
                break;
        }
    }

    //恢复为出厂设置
    private void repeatItem() {
        //恢复商业险部分为初始状态
        for (int i = 0; i < syxList.size(); i++) {
            //恢复为初值
            String itemCode = syxList.get(i).getItemCode();
            String chooseAmount=syxList.get(i).getChooseAmount();
            String franchiseFlag=syxList.get(i).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            removeRepeat(itemCode);
            benefitList.add(benefitInCarIns);

            if ("1".equals(syxList.get(i).getChooseAmount()))
            {
                syxViewList.get(i).setText("投保");
            }
            else if ("0".equals(syxList.get(i).getChooseAmount()))
            {
                syxViewList.get(i).setText("不投保");
            }
            else
            {
                syxViewList.get(i).setText(syxList.get(i).getChooseAmount());
            }
            if ("1".equals(syxList.get(i).getFranchiseFlag()))
            {
                syxViewList.get(i).setCb_ceterChecked(true);
            }
            else if ("0".equals(syxList.get(i).getFranchiseFlag()))
            {
                syxViewList.get(i).setCb_ceterChecked(false);
            }
        }
        //恢复交强险为初始状态
        if (jqxList.size()>0)
        {
            //恢复为初值
            String itemCode = jqxList.get(0).getItemCode();
            String chooseAmount=jqxList.get(0).getChooseAmount();
            String franchiseFlag=jqxList.get(0).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            removeRepeat(itemCode);
            benefitList.add(benefitInCarIns);

            if ("1".equals(jqxList.get(0).getChooseAmount()))
            {
                il_jqx.setText("投保");
            }
            else if ("0".equals(jqxList.get(0).getChooseAmount()))
            {
                il_jqx.setText("不投保");
            }
            else
            {
                il_jqx.setText(jqxList.get(0).getChooseAmount());
            }
            if ("1".equals(jqxList.get(0).getFranchiseFlag()))
            {
                il_jqx.setCb_ceterChecked(true);
            }
            else if ("0".equals(jqxList.get(0).getFranchiseFlag()))
            {
                il_jqx.setCb_ceterChecked(false);
            }
        }
        //恢复附加险为初始状态
        for (int i = 0; i < fjxList.size(); i++) {
            //恢复为初值
            String itemCode = fjxList.get(i).getItemCode();
            String chooseAmount=fjxList.get(i).getChooseAmount();
            String franchiseFlag=fjxList.get(i).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            removeRepeat(itemCode);
            benefitList.add(benefitInCarIns);

            if ("1".equals(fjxList.get(i).getChooseAmount()))
            {
                fjxViewList.get(i).setText("投保");
            }
            else if ("0".equals(fjxList.get(i).getChooseAmount()))
            {
                fjxViewList.get(i).setText("不投保");
            }
            else
            {
                fjxViewList.get(i).setText(fjxList.get(i).getChooseAmount());
            }
            if ("1".equals(fjxList.get(i).getFranchiseFlag()))
            {
                fjxViewList.get(i).setCb_ceterChecked(true);
            }
            else if ("0".equals(fjxList.get(i).getFranchiseFlag()))
            {
                fjxViewList.get(i).setCb_ceterChecked(false);
            }
        }
        //恢复其他附加险为初始状态
        for (int i = 0; i < qtxList.size(); i++) {
            //恢复为初值
            String itemCode = qtxList.get(i).getItemCode();
            String chooseAmount=qtxList.get(i).getChooseAmount();
            String franchiseFlag=qtxList.get(i).getFranchiseFlag();
            CarInsDetail.BenefitInCarIns benefitInCarIns = new CarInsDetail.BenefitInCarIns(itemCode, franchiseFlag, chooseAmount);
            removeRepeat(itemCode);
            benefitList.add(benefitInCarIns);

            if ("1".equals(qtxList.get(i).getChooseAmount()))
            {
                qtxViewList.get(i).setText("投保");
            }
            else if ("0".equals(qtxList.get(i).getChooseAmount()))
            {
                qtxViewList.get(i).setText("不投保");
            }
            else
            {
                qtxViewList.get(i).setText(qtxList.get(i).getChooseAmount());
            }
            if ("1".equals(qtxList.get(i).getFranchiseFlag()))
            {
                qtxViewList.get(i).setCb_ceterChecked(true);
            }
            else if ("0".equals(qtxList.get(i).getFranchiseFlag()))
            {
                qtxViewList.get(i).setCb_ceterChecked(false);
            }
        }
    }

    //查询价格
    private void searchForPrice() {
        CarInsDetail carInsDetail = new CarInsDetail(serialId,modelCode,carPrice,carExtras,benefitList);
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getCarInsCalc(carInsDetail);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body.isSuccess())
                {
                    showTip("success");
                    //finish();
                }
                else
                {
                    showTip(body.getRespMsg());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
