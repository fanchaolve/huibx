package com.bb.hbx.base.v;

import com.bb.hbx.base.m.BaseModel;
import com.bb.hbx.base.p.BasePresenter;
import com.bb.hbx.bean.RequestProduct;
import com.bb.hbx.bean.TypeModel;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface Mall_ItemContract {


    interface Model extends BaseModel {

        void getProducts(RequestProduct product, Callback callback);

    }


    interface View extends BaseView {


        void notfiy();

        TypeModel getTypeModel();

        void stopRefresh();

        void stopLoadMore();
        void stopLoading();

    }

    abstract class Presenter extends BasePresenter<Mall_ItemContract.Model, Mall_ItemContract.View> {


        public abstract List<Item> getList();

        public abstract void getProducts(int type);

        public abstract void setSortCode(int SortCode);
    }
}
