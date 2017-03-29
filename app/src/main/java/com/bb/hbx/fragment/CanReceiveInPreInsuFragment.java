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
import com.bb.hbx.adapter.MyCanReceiveInPresInsuAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.PresentInsuBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CanReceiveInPreInsuFragment extends BaseFragment {

    public static final String TAG = "CanReceiveInPreInsu";
    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context mContext;
    private GridLayoutManager manager;
    private int pageIndex = 1;


    //    ArrayList<String> list = new ArrayList<>();
    List<PresentInsuBean.PresentProductsRspBean> list = new ArrayList<>();
    MyCanReceiveInPresInsuAdapter adapter;
    private static CanReceiveInPreInsuFragment fragment;

    public static CanReceiveInPreInsuFragment getInstance() {
        if (fragment == null) {
            fragment = new CanReceiveInPreInsuFragment();
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
        return R.layout.fragment_canreceive_inpres_layout;
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
        adapter = new MyCanReceiveInPresInsuAdapter(list, mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        loadData(pageIndex);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

        adapter.setOnPresentClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Toast.makeText(mContext, "赠送客户:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnBuyClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Toast.makeText(mContext, "立即投保:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 从网络加载数据
     *
     * @param pageIndex
     */
    private void loadData(final int pageIndex) {
        final ApiService apiService = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = apiService.getPresentProduct(MyApplication.user.getUserId(), "1", pageIndex + "", "10");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
//                Log.d("ttttt","-------come-------");
                if (api.getOutput() != null && api.getOutput() instanceof PresentInsuBean) {
                    PresentInsuBean presentInsuBean = (PresentInsuBean) api.getOutput();
                    List<PresentInsuBean.PresentProductsRspBean> out_list = presentInsuBean.getPresentProductsRsp();
                    if (out_list != null && !out_list.isEmpty()) {
                        if (pageIndex == 1) {
                            list.clear();
                        }
                        list.addAll(out_list);
                        adapter.notifyDataSetChanged();
                    }
                }
                if (scrollView.isRefreshing()) {
                    scrollView.onRefreshComplete();
                }
            }

            @Override
            public void failCallback() {
                Toast.makeText(MyApplication.getAppContext(), "网络请求失败，请重新尝试！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
