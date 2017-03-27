package com.bb.hbx.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.InfoActivity;
import com.bb.hbx.activitiy.MsgDetailsActivity;
import com.bb.hbx.adapter.MyInfoAdapter;
import com.bb.hbx.adapter.MySystemInfoAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.Message;
import com.bb.hbx.bean.MsgInfo;
import com.bb.hbx.interfaces.OnDelBtnClickListener;
import com.bb.hbx.interfaces.OnItemChangeStateClickListener;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.RealmUtilsForMessage;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/17.
 */

public class SystemInfoFragment extends BaseFragment {

    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context mContext;
    private List<Message> totalList = new ArrayList<>();
    private GridLayoutManager manager;
    private MySystemInfoAdapter adapter;
    private SystemInfoReceiver systemInfoReceiver;
    private int pageIndex = 1;
    private int unReadCount = 0;
    private int totalCount;
    private int haveReadCount;
//    private int unReadSysMsgNum = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_systeminfo_layout;
    }

    @Override
    public void initView() {
        initReceiver();

        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex = 1;
                showMsgList(pageIndex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                showMsgList(pageIndex);
            }
        });
    }

    private void initReceiver() {
        systemInfoReceiver = new SystemInfoReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.myinfo");
        mContext.registerReceiver(systemInfoReceiver, filter);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        manager = new GridLayoutManager(mContext, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        adapter = new MySystemInfoAdapter(mContext, totalList);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        showMsgList(pageIndex);
        adapter.setOnMyItemClickListener(new OnItemChangeStateClickListener() {
            @Override
            public void onMyItemChangeStateClickListener(int position, View view) {
                if (((Integer) position) == view.getTag()) {

                    Message mess = totalList.get(position);
                    if (RealmUtilsForMessage.queryMessageById(mess.getMsgId()) == null) {
                        if (unReadCount > 0) {
                            unReadCount--;
                        }
                        InfoActivity.resetLabSystem(unReadCount);
                        RealmUtilsForMessage.add(mess);
                        view.setBackgroundResource(R.drawable.shape_circle_white);
                        totalList.get(position).setSts(2);
                        adapter.notifyItemChanged(position);
                    }
                    startActivity(new Intent(mContext, MsgDetailsActivity.class));
                }
            }
        });

        adapter.setOnDeleteItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(final int position) {
                //Toast.makeText(mContext,"长按删除:"+position,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("确认要删除本条信息吗");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(mContext,"删除:"+position,Toast.LENGTH_SHORT).show();
                        totalList.remove(position);
//                        delMsg(totalList.get(position).getMsgId());
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < totalList.size(); i++) {
                            Log.e("===AA===" + totalList.size(), "=========" + totalList.get(i).getSts());
                        }
                    }
                });
                dialog.setNegativeButton("取消", null);
                dialog.show();
            }
        });

        adapter.setOnDelBtnClickListener(new OnDelBtnClickListener() {
            @Override
            public void onDelBtnClick(View view, int position) {
//                delMsg(totalList.get(position).getMsgId());
                adapter.removeData(position);
                if (adapter.menuIsOpen()) {
                    adapter.closeMenu();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showMsgList(final int pageIndex) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getMsgsUser(MyApplication.user.getUserId(), "1", "0", pageIndex + "");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                MsgInfo bean = (MsgInfo) body.getOutput();
                if (bean != null) {
                    totalCount = bean.getTotalCount();
                    //List<Message> msgList = bean.getMsgList();
                    haveReadCount = RealmUtilsForMessage.queryAllMessages().size();

                    if (pageIndex == 1) {
                        totalList.clear();
                    }

                    unReadCount = totalCount - haveReadCount;
                    InfoActivity.resetLabSystem(unReadCount);
                    totalList.addAll(bean.getMsgList());
                    adapter.notifyDataSetChanged();
                }
                if (scrollView.isRefreshing()) {
                    scrollView.onRefreshComplete();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContext.unregisterReceiver(systemInfoReceiver);
    }

    class SystemInfoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            for (int i = 0; i < totalList.size(); i++) {
                Message mess = totalList.get(i);
                mess.setSts(2);
                RealmUtilsForMessage.add(mess);
            }
            adapter.notifyDataSetChanged();
            unReadCount = 0;
            InfoActivity.resetLabSystem(unReadCount);
        }
    }
}
