package com.bb.hbx.base.p;


import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.base.v.Mall_ItemContract;
import com.bb.hbx.bean.ProductBean;
import com.bb.hbx.bean.RequestProduct;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl.
 */

public class Mall_ItemPresenter extends Mall_ItemContract.Presenter {


    private List<Item> items = new ArrayList<>();

    private static final int PAGE_SIZE = 10;//

    private int PAGE_INDEX = 1;//

    private RequestProduct rp;

    private PostCallback postCallback;


    private int loadType = DataLoadDirection.Refresh;


    private boolean isloadmore = true;

    private int sortCode;


    @Override
    public void onAttached() {
        postCallback = new PostCallback<Mall_ItemContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {


                if (api.getOutput() instanceof ProductBean) {
                    ProductBean bean = (ProductBean) api.getOutput();
                    if (loadType == DataLoadDirection.Refresh) {
                        items.clear();
                        isloadmore = true;
                    }

                    if (bean.getPageSize() < PAGE_SIZE) {
                        isloadmore = false;
                    }
                    if (bean.getProductList() == null || bean.getProductList().isEmpty()) {
                        mView.showMsg("没有数据啦...");
                    } else {
                        mView.stopLoading();
                        items.addAll(bean.getProductList());
                        mView.notfiy();
                    }
                }

                if (loadType == DataLoadDirection.Refresh)
                    mView.stopRefresh();
                else {
                    mView.stopLoadMore();
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
        };
        rp = new RequestProduct();
        rp.setPageIndex(PAGE_INDEX);
        rp.setPageSize(PAGE_SIZE);
        rp.setProductType(Integer.valueOf(mView.getTypeModel().getTypeId()));

    }


    @Override
    public List<Item> getList() {
        return items;
    }

    @Override
    public void getProducts(int type) {
        loadType = type;
        if (loadType == DataLoadDirection.Refresh) {
            PAGE_INDEX = 1;
            rp.setPageIndex(PAGE_INDEX);
            rp.setSortCode(sortCode);
            mModel.getProducts(rp, postCallback);
        } else {
            if (isloadmore) {
                PAGE_INDEX++;
                rp.setPageIndex(PAGE_INDEX);
                mModel.getProducts(rp, postCallback);
            } else {
                mView.showMsg("没有数据啦...");
                mView.stopLoadMore();

            }

        }


    }

    @Override
    public void setSortCode(int SortCode) {
        sortCode = SortCode;
    }


}
