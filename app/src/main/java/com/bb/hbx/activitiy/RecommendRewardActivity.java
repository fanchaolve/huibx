package com.bb.hbx.activitiy;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.InviteFriendsListviewAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.IniviteFriendsBean;
import com.bb.hbx.bean.IniviteFriendsBean.UserAccountDetailRecordBean;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class RecommendRewardActivity extends BaseActivity implements View.OnClickListener {

    private InviteFriendsListviewAdapter mAdapter;

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.lv_invitefriends)
    ListView lv_invitefriends;

    @BindView(R.id.bt_checkerule)
    Button bt_checkerule;

    @BindView(R.id.btn_gotoinvitefriend)
    Button btn_gotoinvitefriend;

    @BindView(R.id.civ_person1)
    CircleImageView civ_person1;

    @BindView(R.id.civ_person2)
    CircleImageView civ_person2;

    @BindView(R.id.civ_person3)
    CircleImageView civ_person3;

    @BindView(R.id.tv_name_01)
    TextView tv_name_01;

    @BindView(R.id.tv_name_02)
    TextView tv_name_02;

    @BindView(R.id.tv_name_03)
    TextView tv_name_03;

    @BindView(R.id.tv_money01)
    TextView tv_money01;

    @BindView(R.id.tv_money02)
    TextView tv_money02;

    @BindView(R.id.tv_money03)
    TextView tv_money03;

    @BindView(R.id.tv_inviteNum)
    TextView tv_inviteNum;

    @BindView(R.id.tv_money)
    TextView tv_money;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        bt_checkerule.setOnClickListener(this);
        btn_gotoinvitefriend.setOnClickListener(this);
    }

    @Override
    public void initdata() {

        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getInviteFriend(MyApplication.user.getUserId());

        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getOutput() != null && api.getOutput() instanceof IniviteFriendsBean) {
                    IniviteFriendsBean bean = (IniviteFriendsBean) api.getOutput();
                    if (bean != null) {
                        setView(bean);
                    }
                }
            }

            @Override
            public void failCallback() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"网络不可用，请检查网络！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 设置显示
     *
     * @param bean
     */
    public void setView(IniviteFriendsBean bean) {
        List<UserAccountDetailRecordBean> list = bean.getUserAccountDetailRecord();
        tv_inviteNum.setText(bean.getCount() + "位");
        tv_money.setText(bean.getAcctSum() + "元");

        if (list.size() <= 3) {
            setTopThreeView(list);
        } else {
            //先将前三名显示出来，再将后面的存入新的集合传给listview显示
            setTopThreeView(list);
            List<UserAccountDetailRecordBean> listViewList = new ArrayList<UserAccountDetailRecordBean>();
            for (int i = 0; i < list.size(); i++) {
                if (i > 2) {
                    listViewList.add(list.get(i));
                }
            }
            mAdapter = new InviteFriendsListviewAdapter(mContext, listViewList);
            lv_invitefriends.setAdapter(mAdapter);
            setListViewHeightByChildren(lv_invitefriends);
        }
    }

    /**
     * 设置前三名的显示
     * @param list
     */
    public void setTopThreeView(List<UserAccountDetailRecordBean> list) {
        GlideUtil glideUtil = GlideUtil.getInstance();
        switch(list.size()) {
            case 1:
                if (list.get(0) != null) {
                    glideUtil.loadImage(mContext, civ_person1, list.get(0).getUserLogo(), true);
                    tv_name_01.setText(list.get(0).getUserNickname());
                    tv_money01.setText(list.get(0).getAcctSum() + "");
                }
                break;
            case 2:
                if (list.get(0) != null) {
                    glideUtil.loadImage(mContext, civ_person1, list.get(0).getUserLogo(), true);
                    tv_name_01.setText(list.get(0).getUserNickname());
                    tv_money01.setText(list.get(0).getAcctSum() + "");
                }
                if (list.get(1) != null) {
                    glideUtil.loadImage(mContext, civ_person2, list.get(1).getUserLogo(), true);
                    tv_name_02.setText(list.get(1).getUserNickname());
                    tv_money02.setText(list.get(1).getAcctSum() + "");
                }
                break;
            default:
                if (list.get(0) != null) {
                    glideUtil.loadImage(mContext, civ_person1, list.get(0).getUserLogo(), true);
                    tv_name_01.setText(list.get(0).getUserNickname());
                    tv_money01.setText(list.get(0).getAcctSum() + "");
                }
                if (list.get(1) != null) {
                    glideUtil.loadImage(mContext, civ_person2, list.get(1).getUserLogo(), true);
                    tv_name_02.setText(list.get(1).getUserNickname());
                    tv_money02.setText(list.get(1).getAcctSum() + "");
                }
                if (list.get(2) != null) {
                    glideUtil.loadImage(mContext, civ_person3, list.get(2).getUserLogo(), true);
                    tv_name_03.setText(list.get(2).getUserNickname());
                    tv_money03.setText(list.get(2).getAcctSum() + "");
                }
                break;
        }
        if (list.get(0) != null) {
            glideUtil.loadImage(mContext, civ_person1, list.get(0).getUserLogo(), true);
            tv_name_01.setText(list.get(0).getUserNickname());
            tv_money01.setText(list.get(0).getAcctSum() + "");
        }
        if (list.get(1) != null) {
            glideUtil.loadImage(mContext, civ_person2, list.get(1).getUserLogo(), true);
            tv_name_02.setText(list.get(1).getUserNickname());
            tv_money02.setText(list.get(1).getAcctSum() + "");
        }
        if (list.get(2) != null) {
            glideUtil.loadImage(mContext, civ_person3, list.get(2).getUserLogo(), true);
            tv_name_03.setText(list.get(2).getUserNickname());
            tv_money03.setText(list.get(2).getAcctSum() + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.bt_checkerule:
                startActivity(new Intent(mContext, CheckRuleActivity.class));
                break;
            case R.id.btn_gotoinvitefriend:
                break;
            default:
                break;
        }
    }

    /**
     * 根据listview的条目高度计算listview的高度，解决scrollview嵌套listview的冲突
     *
     * @param listView
     */
    public void setListViewHeightByChildren(ListView listView) {
        mAdapter = (InviteFriendsListviewAdapter) listView.getAdapter();
        if (mAdapter == null) {
            return;
        }

        int toalHeight = 0;
        for (int i = 0, len = mAdapter.getCount(); i < len; i++) {
            View listItem = mAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            toalHeight += AppManager.sp2px(mContext, 68);
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        params.height = toalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
