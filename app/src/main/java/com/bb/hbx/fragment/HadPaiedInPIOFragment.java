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
import com.bb.hbx.activitiy.PerOrderDetailActivity;
import com.bb.hbx.adapter.MyHadPaiedInPIOAdapter;
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

/**我的--个险订单--中的 有效fragment 模块
 * Created by Administrator on 2016/12/26.
 */

public class HadPaiedInPIOFragment extends BaseFragment{

    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    GridLayoutManager manager;
    List<GetPolicies.PolicyListBean> totalList=new ArrayList<>();
    Context mContext;
    MyHadPaiedInPIOAdapter myHadPaiedInPIOAdapter;

    int pageIndex=1;
    private static HadPaiedInPIOFragment fragment;
    public static HadPaiedInPIOFragment getInstance()
    {
        if (fragment==null)
        {
            fragment=new HadPaiedInPIOFragment();
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
        return R.layout.fragment_hadpay_inpio_layout;
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
        myHadPaiedInPIOAdapter = new MyHadPaiedInPIOAdapter(totalList, mContext);
        recyclerView.setAdapter(myHadPaiedInPIOAdapter);
        if (totalList!=null&&totalList.size()>0)
        {
            totalList.clear();
        }
        showPoliciesList(pageIndex);
        myHadPaiedInPIOAdapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Intent intent = new Intent(mContext, PerOrderDetailActivity.class);
                startActivity(intent);
            }
        });
        //listView.setAdapter(myHadPaiedInPIOAdapter);
    }
    private void showPoliciesList(final int pageIndex) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getPolicies(MyApplication.user.getUserId(),"10","2",pageIndex+"","10");
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
                        myHadPaiedInPIOAdapter.notifyDataSetChanged();
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
