package com.bb.hbx.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.CarOrderDetailActivity;
import com.bb.hbx.adapter.MyHadStopInCarInsuAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.GetPolicies;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/1/23.
 * 已终止车险保单
 */

public class HadStopInCarInsuFragment extends BaseFragment{

    Context mContext;
    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<GetPolicies.PolicyListBean> totalList=new ArrayList<>();
    GridLayoutManager manager;
    MyHadStopInCarInsuAdapter adapter;
    int pageIndex=1;
    private static HadStopInCarInsuFragment fragment;
    public static HadStopInCarInsuFragment getInstance()
    {
        if (fragment==null)
        {
            fragment=new HadStopInCarInsuFragment();
        }
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hadstop_incarinsu_layout;
    }

    @Override
    public void initView() {
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex=1;
                showPoliciesList(pageIndex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                showPoliciesList(pageIndex);
            }
        });
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        manager = new GridLayoutManager(mContext, 1){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyHadStopInCarInsuAdapter(mContext, totalList);
        recyclerView.setAdapter(adapter);
        if (totalList!=null&&totalList.size()>0)
        {
            totalList.clear();
        }
        showPoliciesList(pageIndex);
        adapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Intent intent = new Intent(mContext, CarOrderDetailActivity.class);
                intent.putExtra("detailId", totalList.get(position).getDetailId());
                intent.putExtra("tradeId", totalList.get(position).getTradeId());
                startActivity(intent);
            }
        });
    }
    private void showPoliciesList(final int pageIndex) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getPolicies(MyApplication.user.getUserId(),"40","1",pageIndex+"","10");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body!=null)
                {
                    GetPolicies bean = (GetPolicies) body.getOutput();
                    if (bean!=null)
                    {
                        //List<GetPolicies.PolicyListBean> policyList = bean.getPolicyList();
                        if (pageIndex==1)
                        {
                            totalList.clear();
                        }
//                        Toast.makeText(mContext,"size:"+totalList.size(),Toast.LENGTH_SHORT).show();
                        totalList.addAll(bean.getPolicyList());
                        adapter.notifyDataSetChanged();
                    }
                }
                if (scrollView.isRefreshing())
                {
                    scrollView.onRefreshComplete();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(mContext,"走了这shibai",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
