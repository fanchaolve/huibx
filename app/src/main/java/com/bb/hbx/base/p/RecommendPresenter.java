package com.bb.hbx.base.p;

import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.RecommendContract;
import com.bb.hbx.bean.RecommendBean;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl.
 */

public class RecommendPresenter extends RecommendContract.Presenter {

    private List<Item> items = new ArrayList<>();
    private static final int PAGE_SIZE = 10;//
    private int PAGE_INDEX = 1;//
    private int loadType = DataLoadDirection.Refresh;

    private PostCallback postCallback;


    @Override
    public void onAttached() {
        postCallback = new PostCallback<RecommendContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {

                if (api.getOutput() instanceof RecommendBean) {
                    RecommendBean bean = (RecommendBean) api.getOutput();
                    if (loadType == DataLoadDirection.Refresh) {
                        mView.stopRefresh();
                        items.clear();
                    } else {
                        mView.stopLoadMore();
                    }
                    if (bean.getProductList() != null && bean.getProductList().size() > 0) {
                        items.addAll(bean.getProductList());
                        mView.stopLoading();
                        mView.notfiy();
                        PAGE_INDEX++;
                    }

                }


            }

            @Override
            public void failCallback() {
                if (loadType == DataLoadDirection.Refresh)
                    mView.stopRefresh();
                else {
                    mView.stopLoadMore();
                }
            }
        }

        ;

    }


    @Override
    public List<Item> getList() {
        return items;
    }


    @Override
    public void getSpecialProductList(int type) {
        loadType = type;
        if (loadType == DataLoadDirection.Refresh) {
            PAGE_INDEX = 1;
            mModel.getSpecialProductList(PAGE_INDEX, postCallback);
        } else {
            mModel.getSpecialProductList(PAGE_INDEX, postCallback);


        }
    }
}
