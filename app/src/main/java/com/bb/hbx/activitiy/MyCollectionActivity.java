package com.bb.hbx.activitiy;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyCollectionAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.CollectionBean;
import com.bb.hbx.bean.CollectionBean.FavoritesProductsRecordBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

import static com.bb.hbx.R.id.list;
import static com.bb.hbx.R.id.refresh;

/**
 * Created by Administrator on 2017/3/15.
 * 个人收藏界面
 */

public class MyCollectionActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;

    private List<FavoritesProductsRecordBean> list = new ArrayList<>();
    private GridLayoutManager manager;
    private MyCollectionAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mycollection;
    }

    @Override
    public void initView() {
        manager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyCollectionAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        toolbar.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        if (!list.isEmpty()) {
            list.clear();
        }

        loadData();
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadData();
            }
        });
        adapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
        }
    }

    /**
     * 请求数据
     */
    private void loadData() {
        ApiService apiService = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = apiService.getUserFavoritesDetail(MyApplication.user.getUserId(), "10", "0");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getOutput() != null && api.getOutput() instanceof CollectionBean) {
                    CollectionBean collectionBean = (CollectionBean) api.getOutput();
                    List<FavoritesProductsRecordBean> beanList = collectionBean.getFavoritesProductsRecord();
                    if (beanList != null && beanList.size() > 0) {
                        if (list.size() > 0) {
                            list.clear();
                        }
                        list.addAll(beanList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }
}
