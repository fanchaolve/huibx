package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.fragment.MyInfoFragment;
import com.bb.hbx.fragment.SystemInfoFragment;

import butterknife.BindView;

public class InfoActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.content_layout)
    RelativeLayout content_layout;
    @BindView(R.id.realAll_tv)
    TextView realAll_tv;
    @BindView(R.id.myInfo_tv)
    TextView myInfo_tv;
    @BindView(R.id.systemInfo_tv)
    TextView systemInfo_tv;
    @BindView(R.id.myInfoCount_tv)
    TextView myInfoCount_tv;
    @BindView(R.id.systemInfoCount_tv)
    TextView systemInfoCount_tv;

    FragmentManager fragmentManager;
    MyInfoFragment myInfoFragment;
    SystemInfoFragment systemInfoFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        myInfoFragment=new MyInfoFragment();
        systemInfoFragment=new SystemInfoFragment();
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        realAll_tv.setOnClickListener(this);
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
            case R.id.realAll_tv:
                showTip("全标已读");
                Intent intent = new Intent();
                intent.setAction("com.myinfo");
                sendBroadcast(intent);
                break;
            case R.id.myInfo_tv:
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
}
