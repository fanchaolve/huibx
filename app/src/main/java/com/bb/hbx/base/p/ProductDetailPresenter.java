package com.bb.hbx.base.p;

import android.os.Bundle;

import com.bb.hbx.MyApplication;
import com.bb.hbx.activitiy.ConfirmpaymentActivity;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.ProductDetailContract;
import com.bb.hbx.bean.Benefit;
import com.bb.hbx.bean.Entry;
import com.bb.hbx.bean.Insured;
import com.bb.hbx.bean.KeyBean;
import com.bb.hbx.bean.PayDetail;
import com.bb.hbx.bean.Plan;
import com.bb.hbx.bean.PriceTag;
import com.bb.hbx.bean.ProdectDetalRequest;
import com.bb.hbx.bean.ProductParamDetail;
import com.bb.hbx.observable.KeyBeanObservable;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.bb.hbx.utils.Constants.beinsurer1_listkey;
import static com.bb.hbx.utils.Constants.beinsurer1_listvalue;
import static com.bb.hbx.utils.Constants.idType_keys;
import static com.bb.hbx.utils.Constants.idTypes;


/**
 * Created by fancl.
 * 详细页的P
 */

public class ProductDetailPresenter extends ProductDetailContract.Presenter implements Observer {


    private PostCallback postCallback;

    private int count = 1;//当前的投保份数

    private int max_Quto;//投保最大份数

    private KeyBeanObservable observable;//计价因子观察者

    private String selectPerids;//当前的选中的保障期限

    private String[] perids = new String[12];//保障期限的列表

    private int insureridType = 1;//投保人的idtype

    private int beinsureridType = 1;//被保人的idtype;

    private int beinsurer1key = 1;//被保人关系key

    private double singlePrice;//记价因子的求出的保额价格

    private ProductParamDetail detail;
    private Plan cruentPlan = new Plan();


    @Override
    public void onAttached() {

        mView.tv_quantity_setText(count);
        observable = new KeyBeanObservable();
        observable.setBean(new KeyBean());
        observable.addObserver(this);
        observable.add(" ");

        beinsurer1key = beinsurer1_listkey[0];
        mView.setReationShipValue(beinsurer1_listvalue[0]);


        mView.setInsurerType(idTypes[0]);
        insureridType = idType_keys[0];

        mView.setInsuredIdType(idTypes[0]);
        beinsureridType = idType_keys[0];


        postCallback = new PostCallback<ProductDetailContract.View>(mView) {
            @Override
            public void successCallback(Result_Api api) {

                if (api.getOutput() instanceof ProductParamDetail) {

                    detail = (ProductParamDetail) api.getOutput();

                    if (detail == null)
                        return;
                    mView.loadimage(detail.getProductLogo());
                    mView.setText_insurname_totalmount(detail.getInsurerName(), detail.getTotalAmount());
                    mView.setText_Feature(detail.getProductFeature(), detail.getPerferWords());
                    mView.setText_ProductName(detail.getProductName());

                    //---------购买份数的操作
                    if (detail.getQuota() == 0) {
                        max_Quto = Integer.MAX_VALUE;
                    } else if (detail.getQuota() == 1) {
                        max_Quto = 1;
                        mView.iv_addInVisibility();
                    } else {
                        max_Quto = detail.getQuota();
                    }
                    if (max_Quto == 1) {//购买份数一份 隐藏
                        mView.setlin_countInvisiblity();
                    }

                    //-----显示日期 不能点击
                    if (detail.getEffectiveType() == 1) {
                        mView.setEffectiveTypewithButon(detail.getEffectDate());
                    }

                    if (max_Quto == 1) {//隐藏横线

                        mView.setlineGone();
                    }

                    mView.IsBClientView(MyApplication.user.getIsBClient(), detail.getCommisionValue1());


                    //计划列表
                    if (detail.getPlanList() != null) {
                        mView.setPlanView(detail.getPlanList());
                    }


                    String perid = detail.getGuaranteePeriod();

                    if (perid != null) {
                        if (perid.indexOf(";") > -1) {
                            perids = perid.split(";");
                        } else {
                            perids = new String[]{perid};
                            mView.setil_up1ckickenable(false);
                            mView.setil_up1ckInvisible();
                        }
                    } else {
                        perid = "";
                        perids = new String[]{perid};
                        mView.setil_up1ckickenable(false);
                        mView.setil_up1ckInvisible();
                    }
                    if (perids[0].length() > 2 && perids[0].indexOf("_") > 1) {
                        mView.setil_up1Textvalue(perids[0].substring(0, perids[0].indexOf("_")));
                    } else {
                        mView.setil_up1Textvalue(perids[0]);
                    }
                    selectPerids = perids[0];

                    List<Entry> entries = StringUtils.getJsonOpt(detail.getPriceElements());
                    int i = 0;
                    for (final Entry entry : entries) {
                        observable.add(entry.getOption().get(0));
                        mView.setEntryView(entry, i);
                        i++;
                    }

                    detail.setEntries(entries);
                    observable.add(perids[0]);


                } else if (api.getOutput() instanceof PayDetail) {

                    PayDetail detail = (PayDetail) api.getOutput();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("content", detail);
                    AppManager.getInstance().showActivity(ConfirmpaymentActivity.class, bundle);
                }

            }

            @Override
            public void failCallback() {

            }
        };


    }


    @Override
    public void getProductDetail(String productId) {
        mModel.getProductDetail(productId, postCallback);
    }

    @Override
    public void applyTrade(ProdectDetalRequest request) {
        mModel.applyTrade(request, postCallback);
    }

    @Override
    public void addCount() {
        if (count == 1) {
            mView.iv_subVisibility();
        } else if (count == max_Quto - 1) {
            mView.iv_addInVisibility();
        }
        count++;

        mView.tv_quantity_setText(count);

        observable.setCount(count);
    }

    @Override
    public void subCount() {
        if (count <= max_Quto) {
            mView.iv_addVisibility();
        }
        if (count <= 2) {
            mView.iv_subInVisibility();
        }

        count--;
        mView.tv_quantity_setText(count);
        observable.setCount(count);
    }

    @Override
    public void GetProdectDetalRequest() {
        if (singlePrice <= 0) {
            mView.showMsg("没有获取到价格,请选择该选的内容");
            return;
        }
        if (detail == null) {
            mView.showMsg("没有获取服务器数据");
            return;
        }
//
//        if (mView.getTBRIDEtValue() == null || mView.getTBRIDEtValue().length() == 0) {
//            mView.showMsg("投保人的证件号码不能为空");
//            return;
//        }
//
//        if (mView.getBBRIDEtValue() == null || mView.getBBRIDEtValue().length() == 0) {
//            mView.showMsg("被保人的证件号码不能为空");
//            return;
//        }
//
//        if (mView.getTBRNameEtValue() == null || mView.getTBRNameEtValue().isEmpty()) {
//            mView.showMsg("投保人的名字不能为空");
//            return;
//        }
//
//        if (mView.getTBRMobileEtValue() == null || mView.getTBRMobileEtValue().isEmpty()) {
//            mView.showMsg("投保人的电话不能为空");
//            return;
//        }
//
//        if (mView.getBBRNameEtValue() == null || mView.getBBRNameEtValue().isEmpty()) {
//            mView.showMsg("被保人的名字不能为空");
//            return;
//        }

        ProdectDetalRequest request = new ProdectDetalRequest();
        request.setUserId(MyApplication.user.getUserId());
        request.setProductId(detail.getProductId());
        request.setPriceKeyword(observable.toString());
        request.setPlanId(cruentPlan.getPlanId());
        request.setPeriod(selectPerids);
        request.setIsExpress("0");
        List<Insured> insuredList = new ArrayList<>();
        Insured insured = new Insured();
        insured.setNum(count);
        insured.setOccupation(detail.getOccupation());
        insured.setRelationType(beinsurer1key + "");

        //------------------------------------------------------------

        //insured.setIdNo(mView.getBBRIDEtValue());
        insured.setIdNo("330621201203134673");
        insured.setIdType(beinsureridType);
        //insured.setInsuredName(mView.getBBRNameEtValue());
        insured.setInsuredName("范冰冰");
        insuredList.add(insured);
        request.setInsuredList(insuredList);
        //request.setMobile(mView.getTBRMobileEtValue());
        request.setMobile("13656714459");
        //request.setIdNo(mView.getTBRIDEtValue());
        request.setIdNo("330621198903134673");
        request.setIdType(insureridType);
        //request.setApplicant(mView.getTBRNameEtValue());
        request.setApplicant("范超略");
        request.setClassType("2");
        //调用接口
        applyTrade(request);

    }

    @Override
    public void TraversalPlans(int index) {
        if (detail != null && !detail.getPlanList().isEmpty()) {
            //index>=1;
            cruentPlan = detail.getPlanList().get(index - 1);
            if (cruentPlan.getClassNameList() != null && !cruentPlan.getClassNameList().isEmpty()) {
                List<Benefit> benefits = cruentPlan.getClassNameList().get(0).getBenefitList();
                observable.set(0, cruentPlan.getPlanName().trim());
                mView.removeLinView();
                if (benefits != null && benefits.size() > 0) {
                    for (int i = 0; i < benefits.size(); i++) {
                        if (i > 3) {
                            break;
                        }
                        Benefit benefit = benefits.get(i);
                        mView.addLinView(benefit);
                    }
                }
            }
        }
    }

    @Override
    public void setSelectPerids(int index) {
        selectPerids = perids[index];
        observable.set(observable.size() - 1, perids[index]);
    }

    @Override
    public void setKey(int index) {
        beinsurer1key = beinsurer1_listkey[index];
    }

    @Override
    public void setInsureridType(int index) {
        insureridType = idType_keys[index];
    }

    @Override
    public void setBeinsureridType(int index) {
        beinsureridType = idType_keys[index];
    }

    @Override
    public String[] substringperids() {
        String[] ps = null;
        if (perids != null && perids.length > 1) {
            ps = new String[perids.length];
            for (int i = 0; i < ps.length; i++) {
                if (perids[i].indexOf("_") > 1) {
                    ps[i] = perids[i].substring(0, perids[i].indexOf("_"));

                } else {
                    ps[i] = perids[i];
                }
            }

        }
        return ps;
    }


    @Override
    public void setkeybeanOther(int tag, String value, int index) {
        observable.set(tag, value);
    }

    //遍历计价因子,,得出价格
    public double ischeckPrice() {
        if (detail != null && detail.getPriceList() != null && !detail.getPriceList().isEmpty()) {
            for (PriceTag tag : detail.getPriceList()) {
                if (observable.toString().trim().equalsIgnoreCase(tag.getPriceKeyword().trim())) {
                    return Double.valueOf(tag.getProductPremium());
                }
            }
        }
        return 0;
    }

    //
    @Override
    public void update(Observable o, Object arg) {
        singlePrice = ischeckPrice();
        double price = singlePrice * observable.getCount();
        mView.setPrice("" + price);

    }
}
