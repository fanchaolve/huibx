package com.bb.hbx.base.p;

import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.TopicListContract;
import com.bb.hbx.bean.TopicBean;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl.
 */

public class ToplistPresenter extends TopicListContract.Presenter {

    private List<Item> items = new ArrayList<>();
    private int PAGE_INDEX = 1;//
    private int loadType = DataLoadDirection.Refresh;

    private PostCallback postCallback;


    @Override
    public void onAttached() {
        postCallback = new PostCallback<TopicListContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {
                if(api.getOutput() instanceof TopicBean){
                    TopicBean bean = (TopicBean) api.getOutput();
                    if (loadType == DataLoadDirection.Refresh) {
                        mView.stopRefresh();
                        items.clear();
                    } else {
                        mView.stopLoadMore();
                    }
                    if (bean.getSpecialList() != null && bean.getSpecialList().size() > 0) {
                        mView.stopLoading();
                        items.addAll(bean.getSpecialList());
                        mView.notfiy();
                        PAGE_INDEX++;
                    }
                }
            }

            @Override
            public void failCallback() {

            }
        };

    }


    @Override
    public List<Item> getList() {
        return items;
    }


    @Override
    public void getSpecials(int type) {
        loadType = type;
        if (loadType == DataLoadDirection.Refresh) {
            PAGE_INDEX = 1;
            mModel.getSpecials(PAGE_INDEX, postCallback);
        } else {
            mModel.getSpecials(PAGE_INDEX, postCallback);


        }
    }
}
