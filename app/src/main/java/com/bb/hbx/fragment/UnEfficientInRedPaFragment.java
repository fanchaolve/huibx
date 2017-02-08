package com.bb.hbx.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bb.hbx.R;
import com.bb.hbx.adapter.MyUnEfficientInRedPAdapter;
import com.bb.hbx.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**点击 我的--红包 后显示的 已过期 页面
 * Created by Administrator on 2016/12/27.
 */

public class UnEfficientInRedPaFragment extends BaseFragment{

    Context mContext;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    String path="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    ArrayList<String> list=new ArrayList<>();
    GridLayoutManager manager;
    MyUnEfficientInRedPAdapter adapter;
    private static UnEfficientInRedPaFragment fragment;
    public static UnEfficientInRedPaFragment getInstance()
    {
        if (fragment==null)
        {
            fragment=new UnEfficientInRedPaFragment();
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
        return R.layout.fragment_unefficient_inredp_layout;
    }

    @Override
    public void initView() {

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
        if (list!=null&&list.size()>0)
        {
            list.clear();
        }
        for (int i = 0; i < 5; i++) {
            list.add(path);
        }
        adapter = new MyUnEfficientInRedPAdapter(mContext, list);
        recyclerView.setAdapter(adapter);
    }
}
