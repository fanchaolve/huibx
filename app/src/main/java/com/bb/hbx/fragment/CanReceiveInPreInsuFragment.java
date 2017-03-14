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
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.PresentInsuBean;
import com.bb.hbx.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CanReceiveInPreInsuFragment extends BaseFragment {

    public static final String TAG = "CanReceiveInPreInsu";
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Context mContext;
    GridLayoutManager manager;
    //    ArrayList<String> list = new ArrayList<>();
    List<PresentInsuBean> list = new ArrayList<>();
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
        final ApiService apiService = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = apiService.getPresentProduct(MyApplication.user.getUserId(), "1");
        Log.d(TAG,"userId:" + MyApplication.user.getUserId());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.e(TAG, "onResponse:------------------------------- " + response.toString());
                Result_Api result_api = (Result_Api) response.body();
                List<PresentInsuBean> out_list= (List<PresentInsuBean>) result_api.getOutput();
                if (out_list != null && !out_list.isEmpty()) {
                    list.clear();
                    list.addAll(out_list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MyApplication.getAppContext(),"------------------",Toast.LENGTH_SHORT).show();
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

}
