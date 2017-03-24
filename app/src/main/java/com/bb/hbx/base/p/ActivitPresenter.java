package com.bb.hbx.base.p;

import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.ActivitContract;
import com.bb.hbx.bean.ActivitInfo;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl.
 */

public class ActivitPresenter extends ActivitContract.Presenter {

    private List<Item> items = new ArrayList<>();
    private static final int PAGE_SIZE = 10;//
    private int PAGE_INDEX = 1;//
    private int loadType = DataLoadDirection.Refresh;
    private boolean isloadmore = true;//是否还有更多
    private PostCallback postCallback;


    @Override
    public void onAttached() {
        postCallback = new PostCallback<ActivitContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {

                if (api.getOutput() instanceof ActivitInfo) {
                    ActivitInfo bean = (ActivitInfo) api.getOutput();
                    if (loadType == DataLoadDirection.Refresh) {
                        mView.stopRefresh();
                        items.clear();
                        isloadmore = true;
                    } else {
                        mView.stopLoadMore();
                    }
                    if (bean.getAdsList() == null || bean.getAdsList().isEmpty()) {
                        mView.showMsg("没有数据啦...");
                    } else {
                        mView.stopLoading();
                        items.addAll(bean.getAdsList());
                        mView.notfiy();
                    }
                    if (items.size() >= bean.getTotalCount())
                        isloadmore = false;

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
    public void getAdsList(int type) {
        loadType = type;
        if (loadType == DataLoadDirection.Refresh) {
            PAGE_INDEX = 1;
            mModel.getAdsList(PAGE_INDEX, PAGE_SIZE, postCallback);
        } else {
            if (isloadmore) {
                PAGE_INDEX++;
                mModel.getAdsList(PAGE_INDEX, PAGE_SIZE, postCallback);
            } else {
                mView.showMsg("没有数据啦...");
                mView.stopLoadMore();

            }

        }
    }
}
