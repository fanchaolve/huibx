package com.bb.hbx.base.v;

import com.bb.hbx.base.m.BaseModel;
import com.bb.hbx.base.p.BasePresenter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.List;

import retrofit2.Callback;


/**
 * Created by Administrator on 2017/1/9.
 */

public interface TopicListContract {


    interface Model extends BaseModel {

        void getSpecials(int pageIndex,Callback callback);

    }


    interface View extends BaseView {


        void stopRefresh();

        void stopLoadMore();
        void stopLoading();
        void notfiy();

    }

    abstract class Presenter extends BasePresenter<TopicListContract.Model, TopicListContract.View> {

        public abstract List<Item> getList();

        public abstract void getSpecials(int type);

    }
}
