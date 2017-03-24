package com.bb.hbx.base.v;

import com.bb.hbx.base.m.BaseModel;
import com.bb.hbx.base.p.BasePresenter;
import com.bb.hbx.bean.Benefit;
import com.bb.hbx.bean.Entry;
import com.bb.hbx.bean.Plan;
import com.bb.hbx.bean.ProdectDetalRequest;
import com.bb.hbx.bean.ProductParamDetail;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface ProductDetailContract {


    interface Model extends BaseModel {

        void getProductDetail(String productId, Callback callback);

        void applyTrade(ProdectDetalRequest request, Callback callback);

    }


    interface View extends BaseView {

        void setProductDetail(ProductParamDetail detail);

        void iv_subVisibility();//减按钮显示

        void iv_subInVisibility();//减按钮隐藏

        void iv_addVisibility();//加按钮显示

        void iv_addInVisibility();//加按钮隐藏

        void tv_quantity_setText(int count);//投保数量

        void loadimage(String logo);//加载图片

        void setText_insurname_totalmount(String insurerName, String totalAmount);//显示保险公司名称和数量

        void setText_ProductName(String productName);//显示保险产品

        void setText_Feature(String productFeature, String perferWords);//产品特征

        void setlin_countInvisiblity();//购买整行隐藏

        void setEffectiveTypewithButon(String effectDate);//layout2的显示或隐藏

        void setlineGone();//隐藏横线

        void setOccupationGone();//隐藏职业类别条目

        void IsBClientView(boolean isClient, String CommisionValue1);//是否显示推广费

        void setPlanView(List<Plan> list);//放入计划列表

        void setil_up1ckickenable(boolean enable);//是否可点击

        void setil_up1ckInvisible();//隐藏


        void setil_up1Textvalue(String value);//设置值

        void setEntryView(Entry entry, int index);//取得entry 动态加载布局

        void setReationShipValue(String value);

        void setInsuredIdType(String idTypeValue);//被保险人的id类型

        void setInsurerType(String idTypeValue);//投保人的id类型

        void setPrice(String price);//价格赋值

        void removeLinView();//删除里面的view

        void addLinView(Benefit benefit);//删除里面的view

        String getTBRIDEtValue();//投保人

        String getTBRMobileEtValue();//投保人

        String getTBRNameEtValue();//投保人

        String getBBRIDEtValue();//被保人

        String getBBRNameEtValue();//被保人
    }

    abstract class Presenter extends BasePresenter<ProductDetailContract.Model, ProductDetailContract.View> {

        public abstract void getProductDetail(String productId);

        public abstract void applyTrade(ProdectDetalRequest request);

        public abstract void addCount();

        public abstract void subCount();

        public abstract void GetProdectDetalRequest();

        public abstract void TraversalPlans(int index);

        public abstract void setSelectPerids(int index);//设置选中的perids

        public abstract void setKey(int index);//

        public abstract void setInsureridType(int index);

        public abstract void setBeinsureridType(int index);

        public abstract String[] substringperids();//切割perids

        public abstract void setkeybeanOther(int tag, String value, int index);//添加到keybean的计价因子里面（其他参数）


    }
}
