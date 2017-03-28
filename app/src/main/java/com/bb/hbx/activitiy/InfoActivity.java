package com.bb.hbx.activitiy;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.Message;
import com.bb.hbx.fragment.MyInfoFragment;
import com.bb.hbx.fragment.SystemInfoFragment;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.RealmUtilsForMessage;
import com.bb.hbx.widget.MsgEditDialog;


import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.content_layout)
    RelativeLayout content_layout;
    @BindView(R.id.editAll_tv)
    TextView editAll_tv;
    @BindView(R.id.myInfo_tv)
    TextView myInfo_tv;
    @BindView(R.id.systemInfo_tv)
    TextView systemInfo_tv;

    static TextView myInfoCount_tv;
    static TextView systemInfoCount_tv;

    private FragmentManager fragmentManager;
    private MyInfoFragment myInfoFragment;
    private SystemInfoFragment systemInfoFragment;

    private int framentFlag = 0;            //0:个人消息页面  1：系统消息页面

    @Override
    public int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    public void initView() {
        myInfoCount_tv= (TextView) findViewById(R.id.myInfoCount_tv);
        systemInfoCount_tv= (TextView) findViewById(R.id.systemInfoCount_tv);
        fragmentManager = getSupportFragmentManager();
        myInfoFragment=new MyInfoFragment();
        systemInfoFragment=new SystemInfoFragment();

    }

    public static void resetLabMine(int count)
    {
        if (count==0)
        {
            myInfoCount_tv.setVisibility(View.GONE);
        }
        else
        {
            myInfoCount_tv.setVisibility(View.VISIBLE);
            myInfoCount_tv.setText(count+"");
        }
    }

    public static void resetLabSystem(int count)
    {
        if (count==0)
        {
            systemInfoCount_tv.setVisibility(View.GONE);
        }
        else
        {
            systemInfoCount_tv.setVisibility(View.VISIBLE);
            systemInfoCount_tv.setText(count+"");
        }
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        editAll_tv.setOnClickListener(this);
        myInfo_tv.setOnClickListener(this);
        systemInfo_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        fragmentManager.beginTransaction().add(R.id.content_layout,myInfoFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.editAll_tv:
                final MsgEditDialog msgEditDialog = new MsgEditDialog(this);
                msgEditDialog.show();
                switch (framentFlag) {
                    case 0:                     //个人消息页面
                        msgEditDialog.setItemFlagAllClickListener(new OnItemClickListener() {
                            @Override
                            public void onMyItemClickListener(int position) {
                                readAllPerSonInfos();
                                msgEditDialog.dismiss();
                            }
                        });

                        msgEditDialog.setItemClearAllListener(new OnItemClickListener() {
                            @Override
                            public void onMyItemClickListener(int position) {
                                delAllPerSonInfo();
                                msgEditDialog.dismiss();
                            }
                        });
                        break;
                    case 1:                     //系统消息页面
                        msgEditDialog.setItemFlagAllClickListener(new OnItemClickListener() {
                            @Override
                            public void onMyItemClickListener(int position) {
                                readAllSysInfos();
                                msgEditDialog.dismiss();
                            }
                        });

                        msgEditDialog.setItemClearAllListener(new OnItemClickListener() {
                            @Override
                            public void onMyItemClickListener(int position) {
                                delAllSysInfos();
                                msgEditDialog.dismiss();
                            }
                        });
                        break;
                }
                break;
            case R.id.myInfo_tv:
                framentFlag = 0;
                //showTip("我的消息");
                myInfo_tv.setTextColor(getResources().getColor(R.color.white));
                myInfo_tv.setBackgroundResource(R.drawable.shape_select_left_custom);
                systemInfo_tv.setTextColor(getResources().getColor(R.color.A1));
                systemInfo_tv.setBackgroundResource(R.drawable.shape_unselect_right_custom);
                if (myInfoFragment.isAdded())
                {
                    fragmentManager.beginTransaction().hide(systemInfoFragment).show(myInfoFragment).commit();
                }
                else
                {
                    fragmentManager.beginTransaction().add(R.id.content_layout,myInfoFragment).commit();
                }
                break;
            case R.id.systemInfo_tv:
                framentFlag = 1;
                //showTip("系统消息");
                systemInfo_tv.setTextColor(getResources().getColor(R.color.white));
                systemInfo_tv.setBackgroundResource(R.drawable.shape_select_right_custom);
                myInfo_tv.setTextColor(getResources().getColor(R.color.A1));
                myInfo_tv.setBackgroundResource(R.drawable.shape_unselect_left_custom);
                if (systemInfoFragment.isAdded())
                {
                    fragmentManager.beginTransaction().hide(myInfoFragment).show(systemInfoFragment).commit();
                }
                else
                {
                    fragmentManager.beginTransaction().add(R.id.content_layout,systemInfoFragment).commit();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 删除全部个人消息
     */
    private void delAllPerSonInfo() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.delMsg(MyApplication.user.getUserId(), "2", "0", "0");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                //直接清空adapter的集合
                myInfoFragment.totalList.clear();
                myInfoFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void failCallback() {

            }
        });
    }

    /**
     *将个人消息全部标记为已读
     */
    private void readAllPerSonInfos() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.readMsg(MyApplication.user.getUserId(), "2", "1", "0");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                myInfoFragment.showMsgList(1);
            }

            @Override
            public void failCallback() {

            }
        });
    }

    /**
     * 删除全部系统消息
     */
    private void delAllSysInfos() {
        List<Message> delList = systemInfoFragment.totalList;
        if (delList.size() == 0) {
            systemInfoFragment.adapter.notifyDataSetChanged();
            return;
        }
        Message msg;
        for (int i = 0; i < delList.size(); i++) {
            msg = delList.get(i);
            if (RealmUtilsForMessage.queryMessageById(msg.getMsgId()) != null) {        //已读消息
                RealmUtilsForMessage.update(msg.getMsgId(),true);
            } else {                                                                    //未读消息
                msg.setDelete(true);
                RealmUtilsForMessage.add(msg);
            }
        }
        systemInfoFragment.totalList.clear();
        resetLabSystem(0);
        systemInfoFragment.adapter.notifyDataSetChanged();
    }

    /**
     * 将系统消息全部标为已读
     */
    private void readAllSysInfos() {
        List<Message> delList = systemInfoFragment.totalList;
        if (delList.size() == 0) {
            systemInfoFragment.adapter.notifyDataSetChanged();
            return;
        }

        Message msg;
        for (int i = 0; i < delList.size(); i++) {
            msg = delList.get(i);
            if (RealmUtilsForMessage.queryMessageById(msg.getMsgId()) == null) {        //未读消息
                RealmUtilsForMessage.add(msg);
            }
        }
        resetLabSystem(0);
        systemInfoFragment.adapter.notifyDataSetChanged();
    }
}
