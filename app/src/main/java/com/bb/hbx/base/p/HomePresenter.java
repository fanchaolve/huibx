package com.bb.hbx.base.p;


import android.support.v7.widget.GridLayoutManager;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.HomeContract;
import com.bb.hbx.bean.BKItem;
import com.bb.hbx.bean.BannerBean;
import com.bb.hbx.bean.BobaoItem;
import com.bb.hbx.bean.MsgInfo;
import com.bb.hbx.bean.ProductItem;
import com.bb.hbx.bean.ProductListBean;
import com.bb.hbx.bean.Special;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl.
 */

public class HomePresenter extends HomeContract.Presenter {


    private List<Item> items;

    private BannerBean bannerBean;

    private int topicListPageIndex = 1;

    private int topicListPageSize = 10;

    private int item_PageSize = 5;

    private PostCallback postCallback;


    @Override
    public void onAttached() {
        items = new ArrayList<>();
        postCallback = new PostCallback<HomeContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {
                mView.stopRefresh();
                if (api.getOutput() instanceof BannerBean) {
                    BannerBean bannerBean = (BannerBean) api.getOutput();
                    items.set(0, bannerBean);
                    mView.notfiy();

                } else if (api.getOutput() instanceof ProductItem) {
                    ProductItem productItem = (ProductItem) api.getOutput();
                    if (productItem.getProductType() != null && !productItem.getProductType().isEmpty()) {
                        int i = 1;
                        for (ProductItem productItem1 : productItem.getProductType()) {
                            items.set(i, productItem1);
                            i++;
                        }
                        mView.notfiy();
                    }

                } else if (api.getOutput() instanceof BobaoItem) {
//                    //小汇报
                    BobaoItem bobaoItem = (BobaoItem) api.getOutput();
                    items.set(item_PageSize + 1, bobaoItem);
                    mView.notfiy();

                } else if (api.getOutput() instanceof Special) {
                    Special parent_Special = (Special) api.getOutput();
                    //爆款推荐专题
                    items.add(new BKItem(R.drawable.baokuantuijian));
                    //爆款推荐的产品
                    List<Special> specialList = parent_Special.getSpecialList();
                    List<Special> jxLists = new ArrayList<>();
                    for (Special special : specialList) {
                        if (special.getSpecialType() == Constants.BKTJ) {
                            List<ProductListBean> beanList = special.getProductList();
                            if (beanList == null || beanList.size() == 0)
                                return;
                            beanList.get(beanList.size() - 1).setLine(false);
                            items.addAll(beanList);
                        } else {
                            jxLists.add(special);
                        }
                    }

                    //精选专题
                    items.add(new BKItem(R.drawable.jingxuanzhuanti));
                    items.addAll(jxLists);
                    mView.notfiy();

                } else if (api.getOutput() instanceof MsgInfo) {
                    MsgInfo info = (MsgInfo) api.getOutput();
                    mView.setMsgCount(info.getTotalCount());
                }
            }

            @Override
            public void failCallback() {
                mView.stopRefresh();
            }
        };

    }




    @Override
    public void getHomePageInfo() {
        items.clear();
        items.add(new BannerBean());
        for (int i = 0; i < item_PageSize; i++) {
            items.add(new ProductItem());
        }
        if (MyApplication.user.getIsBClient()) {
            items.add(new BobaoItem());
        }
        //接口
        mModel.getBannerInfo(postCallback);
        mModel.getHomePageProductType(postCallback);
        if (MyApplication.user.getIsBClient()) {
            mModel.getXhbMessageInfo(postCallback);
        }
        mModel.getHomePageProductList(postCallback);
    }

    @Override
    public void getMsgs() {

        mModel.getUnReadMessageCount(MyApplication.user.getUserId(), postCallback);
    }

    @Override
    public List<Item> getListItems() {
        return items;
    }


    @Override
    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position<items.size())//-------------------这层判断我新加
                {
                    Item item = items.get(position);
                    if (item instanceof ProductItem) {
                        return 1;
                    }
                }
                return item_PageSize;
            }
        };
        return spanSizeLookup;
    }


}
