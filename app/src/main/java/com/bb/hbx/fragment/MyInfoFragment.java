package com.bb.hbx.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.InfoActivity;
import com.bb.hbx.activitiy.MsgDetailsActivity;
import com.bb.hbx.adapter.MyInfoAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.Message;
import com.bb.hbx.bean.MsgInfo;
import com.bb.hbx.db.MyDBManagerSystemInfo;
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

public class MyInfoFragment extends BaseFragment {

    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context mContext;
    public List<Message> totalList = new ArrayList<>();
    private GridLayoutManager manager;
    public MyInfoAdapter adapter;
    private MyInfoReceiver myInfoReceiver;
    private int pageIndex = 1;

    private int unReadCount;
    private int unReadSysMsgNum = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_myinfo_layout;
    }

    @Override
    public void initView() {
        scrollView.scrollTo(0, 0);
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

    @Override
    protected void initdate(Bundle savedInstanceState) {
        manager = new GridLayoutManager(mContext, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        adapter = new MyInfoAdapter(mContext, totalList);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        showMsgList(pageIndex);
        //adapter.notifyDataSetChanged();
        adapter.setOnMyItemClickListener(new OnItemChangeStateClickListener() {
            @Override
            public void onMyItemChangeStateClickListener(int position, View view) {
                if (((Integer) position) == view.getTag()) {
                    //view.setVisibility(View.GONE);
                    if (totalList.get(position).getSts() == 1) {
                        if (unReadCount > 0) {
                            unReadCount--;
                        }
                        InfoActivity.resetLabMine(unReadCount);
                        view.setBackgroundResource(R.drawable.shape_circle_white);
                        totalList.get(position).setSts(2);
                        adapter.notifyItemChanged(position);
                    }
                    startActivity(new Intent(mContext, MsgDetailsActivity.class));
                    uploadServices(totalList.get(position).getMsgId());
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
                        delMsg(totalList.get(position).getSts(), totalList.get(position).getMsgId());
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
                delMsg(totalList.get(position).getSts(), totalList.get(position).getMsgId());
                adapter.removeData(position);
                if (adapter.menuIsOpen()) {
                    adapter.closeMenu();
                }
                adapter.notifyDataSetChanged();
            }
        });

        loadSysMsgData();
    }

    /**
     * 读消息
     *
     * @param msgId
     */
    private void uploadServices(String msgId) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.readMsg(MyApplication.user.getUserId(), "2", "1", msgId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void showMsgList(final int pageIndex) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getMsgsUser(MyApplication.user.getUserId(), "2", "0", pageIndex + "");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                MsgInfo bean = (MsgInfo) body.getOutput();
                if (bean != null) {
                    //List<Message> msgList = bean.getMsgList();
                    if (pageIndex == 1) {
                        unReadCount = bean.getUnReadCount();
                        totalList.clear();
                    } else {
                        unReadCount += bean.getUnReadCount();
                    }
                    InfoActivity.resetLabMine(unReadCount);
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
     * 删除消息
     */
    private void delMsg(final int sts, String msgId) {
        ApiService apiService = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = apiService.delMsg(MyApplication.user.getUserId(), "2", sts + "", msgId);
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                if (sts == 1) {
                    unReadCount --;
                    InfoActivity.resetLabMine(unReadCount);
                }
                Toast.makeText(mContext, "消息删除成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failCallback() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContext.unregisterReceiver(myInfoReceiver);
//        myDBManagerSystemInfo.closeDaoSession();
    }

    class MyInfoReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            for (int i = 0; i < totalList.size(); i++) {
                totalList.get(i).setSts(2);
            }
            adapter.notifyDataSetChanged();
            unReadCount = 0;
            InfoActivity.resetLabMine(unReadCount);
            uploadServices("0");
        }
    }

    //注册广播接收者
    private void initReceiver() {
        myInfoReceiver = new MyInfoReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.myinfo");
        mContext.registerReceiver(myInfoReceiver, filter);
    }

    /**
     * 请求系统消息
     */
    private void loadSysMsgData() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getMsgsUser(MyApplication.user.getUserId(), "1", "0", pageIndex + "");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                MsgInfo bean = (MsgInfo) body.getOutput();
                if (bean != null) {
                    List<Message> msgList = bean.getMsgList();
//                    Log.d("ddd","=======================" + msgList.size());

                    for (int i = 0; i < msgList.size(); i++) {
                        Message msg = msgList.get(i);
                        if (RealmUtilsForMessage.queryMessageById(msg.getMsgId()) == null) {
                            unReadSysMsgNum++;
                        }
                    }
//                    Log.d("kkk","---------------------------------" + unReadSysMsgNum);
                    InfoActivity.resetLabSystem(unReadSysMsgNum);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
