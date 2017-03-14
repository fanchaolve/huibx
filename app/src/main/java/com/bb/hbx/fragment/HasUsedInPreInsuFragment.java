package com.bb.hbx.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bb.hbx.R;
import com.bb.hbx.adapter.MyHasUsedInPresInsuAdapter;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HasUsedInPreInsuFragment extends BaseFragment {

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Context mContext;
    List<String> list = new ArrayList<>();
    GridLayoutManager manager;
    MyHasUsedInPresInsuAdapter adapter;
    private static HasUsedInPreInsuFragment fragment;

    public static HasUsedInPreInsuFragment getInstance() {
        if (fragment == null) {
            fragment = new HasUsedInPreInsuFragment();
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
        return R.layout.fragment_hasused_inpres_layout;
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
        adapter = new MyHasUsedInPresInsuAdapter(list, mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

        if (list != null && list.size() > 0) {
            list.clear();
        }
        for (int i = 0; i < 6; i++) {
            list.add("i:" + i);
        }
        adapter.notifyDataSetChanged();
        adapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
