package com.bb.hbx.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.InfoActivity;
import com.bb.hbx.adapter.MySystemInfoAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.Message;
import com.bb.hbx.bean.MsgInfo;
import com.bb.hbx.db.MyDBManagerSystemInfo;
import com.bb.hbx.interfaces.OnItemChangeStateClickListener;
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

    Context mContext;
    List<Message> totalList = new ArrayList<>();
    GridLayoutManager manager;
    MySystemInfoAdapter adapter;
    SystemInfoReceiver systemInfoReceiver;
    int pageIndex = 1;
    int unReadCount;
    int totalCount;
    int realsystemInfoCount;
    int haveReadCount;
    private MyDBManagerSystemInfo myDBManagerSystemInfo;
    private int unReadSysMsgNum = 0;

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
        myDBManagerSystemInfo = new MyDBManagerSystemInfo(mContext);

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
        adapter = new MySystemInfoAdapter(mContext, totalList, myDBManagerSystemInfo);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        showMsgList(pageIndex);
        adapter.setOnMyItemClickListener(new OnItemChangeStateClickListener() {
            @Override
            public void onMyItemChangeStateClickListener(int position, View view) {
                if (((Integer) position) == view.getTag()) {

                    Message mess = totalList.get(position);
                    if (!isReadMsg(mess.getMsgId())) {
                        if (unReadSysMsgNum > 0) {
                            unReadSysMsgNum--;
                        }
                        mess.setSts(2);
                        myDBManagerSystemInfo.insertOrReplaceObject(mess);
                        InfoActivity.resetLabSystem(unReadSysMsgNum);
                        view.setBackgroundResource(R.drawable.shape_circle_white);
                        totalList.get(position).setSts(2);
                        adapter.notifyItemChanged(position);
                    }
                }
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
                    haveReadCount = myDBManagerSystemInfo.queryAll();
                    //List<Message> msgList = bean.getMsgList();
                    if (pageIndex == 1) {
                        totalList.clear();
                    }
                    unReadCount = totalCount - haveReadCount;
                    InfoActivity.resetLabSystem(unReadSysMsgNum);
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

    /**
     * 请求系统消息
     */
//    private void loadSysMsgData(final int pageIndex) {
//        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
//        Call call = service.getMsgsUser(MyApplication.user.getUserId(), "1", "0", pageIndex + "");
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Result_Api body = (Result_Api) response.body();
//                MsgInfo bean = (MsgInfo) body.getOutput();
//                if (bean != null) {
//                    List<Message> msgList = bean.getMsgList();
//                    for (int i = 0; i < msgList.size() - 1; i++) {
//                        Message msg = msgList.get(i);
//                        List<Message> list = myDBManagerSystemInfo.queryOne(msg.getMsgId());
//                        if (list != null) {
//                            if (list.isEmpty()) {
//                                msg.setSts(1);        //未读
//                                myDBManagerSystemInfo.insertObject(msg);
//                                unReadSysMsgNum ++;
//                            } else {
//                                if (list.get(0).getSts() == 1) {
//                                    unReadSysMsgNum ++;
//                                }
//                            }
//                        }
//                    }
//                    if (pageIndex == 1) {
//                        totalList.clear();
//                    }
//                    Log.d("hhh","----------db-----------" + myDBManagerSystemInfo.queryAllObject().size());
//                    totalList.addAll(myDBManagerSystemInfo.queryAllObject());
//                    adapter.notifyDataSetChanged();
//                    InfoActivity.resetLabSystem(unReadSysMsgNum);
//                }
//                if (scrollView.isRefreshing()) {
//                    scrollView.onRefreshComplete();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//
//            }
//        });
//    }

    /**
     * 判断一个消息是否是已读消息
     * @param msgId
     * @return true已读
     */
    private boolean isReadMsg(String msgId) {
        List<Message> list = myDBManagerSystemInfo.queryOne(msgId);
        if (list != null) {
            if (list.isEmpty()) {
//                Log.d("ddd","-----------list1-----------" + list.toString());
                return false;
            } else {
                Message msg = myDBManagerSystemInfo.queryOne(msgId).get(0);
//                Log.d("ddd","-----------list2-----------" + list.toString());
//                Log.d("ddd","-----------isRead-----------" + (msg.getSts() == 2));

                return msg.getSts() == 2;
            }
        } else {
//            Log.d("ddd","-----------list-----------" + list.toString());
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContext.unregisterReceiver(systemInfoReceiver);
        myDBManagerSystemInfo.closeDaoSession();
    }

    class SystemInfoReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            for (int i = 0; i < totalList.size(); i++) {
                //totalList.get(i).setSts(2);
                Message mess = totalList.get(i);
                mess.setSts(2);
                myDBManagerSystemInfo.insertObject(mess);
            }
            adapter.notifyDataSetChanged();
            unReadSysMsgNum = 0;
            InfoActivity.resetLabSystem(unReadSysMsgNum);
        }
    }
}
