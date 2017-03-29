package com.bb.hbx.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyOutOfDatePreInsuAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.PresentInsuBean;
import com.bb.hbx.bean.PresentInsuBean.PresentProductsRspBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/2/9.
 */

public class OutofDateInPreInsuFragment extends BaseFragment {
    @BindView(R.id.scrollview)
    PullToRefreshScrollView scrollview;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context mContext;
    private GridLayoutManager manager;
    private int pageIndex = 1;
    private MyOutOfDatePreInsuAdapter adapter;
    private List<PresentProductsRspBean> list = new ArrayList<>();

    private static OutofDateInPreInsuFragment fragment;

    public static OutofDateInPreInsuFragment getInstance() {
        if (fragment == null) {
            fragment = new OutofDateInPreInsuFragment();
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_outofdate_inpres_layout;
    }

    @Override
    public void initView() {
        manager = new GridLayoutManager(mContext, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyOutOfDatePreInsuAdapter(mContext, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        loadData(pageIndex);
        scrollview.setMode(PullToRefreshBase.Mode.BOTH);
        scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex = 1;
                loadData(pageIndex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                loadData(pageIndex);
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData(final int pageIndex) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getPresentProduct(MyApplication.user.getUserId(), "-1", pageIndex + "", "10");
//        Log.d("ttttt","---------------coming111---------------");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
//                Log.d("ttttt","---------------coming222---------------");
                if (api.getOutput() != null && api.getOutput() instanceof PresentInsuBean) {
                    PresentInsuBean rspBean = (PresentInsuBean) api.getOutput();
                    List<PresentProductsRspBean> beanList = rspBean.getPresentProductsRsp();
                    if (beanList != null && !beanList.isEmpty()) {
                        if (pageIndex == 1) {
                            list.clear();
                        }
                        list.addAll(beanList);
                        adapter.notifyDataSetChanged();
                    }
                }
                if (scrollview.isRefreshing()) {
                    scrollview.onRefreshComplete();
                }
            }

            @Override
            public void failCallback() {
                Toast.makeText(MyApplication.getAppContext(), "网络请求失败，请重新尝试！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
