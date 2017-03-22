package com.bb.hbx.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.activitiy.EditInsuredInfoActivity;
import com.bb.hbx.activitiy.MyCustomActivity;
import com.bb.hbx.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/2/12.
 */

public class CustomInfoInMyCustomFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.sex_tv)
    TextView sex_tv;
    @BindView(R.id.birth_tv)
    TextView birth_tv;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.idType_tv)
    TextView idType_tv;
    @BindView(R.id.idNumber_tv)
    TextView idNumber_tv;
    @BindView(R.id.area_tv)
    TextView area_tv;
    @BindView(R.id.address_tv)
    TextView address_tv;
    @BindView(R.id.email_tv)
    TextView email_tv;
    @BindView(R.id.more_tv)
    TextView more_tv;
    @BindView(R.id.edit_tv)
    TextView edit_tv;

    Context mContext;
    private static CustomInfoInMyCustomFragment fragment;

    public static CustomInfoInMyCustomFragment getInstance() {
        if (fragment == null) {
            fragment = new CustomInfoInMyCustomFragment();
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
        return R.layout.fragment_custominfo_inmycustom_layout;
    }

    @Override
    public void initView() {
        edit_tv.setOnClickListener(this);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_tv:
                //Toast.makeText(mContext,"编辑资料",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, EditInsuredInfoActivity.class);

                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showInsuredInfo();
    }

    private void showInsuredInfo() {
        name_tv.setText(MyCustomActivity.insuredInfolBean.getInsuredName());
        sex_tv.setText(MyCustomActivity.insuredInfolBean.getGender());
        birth_tv.setText(MyCustomActivity.insuredInfolBean.getBirthday());
        phone_tv.setText(MyCustomActivity.insuredInfolBean.getMobile());
        idNumber_tv.setText(MyCustomActivity.insuredInfolBean.getIdNo());
        area_tv.setText(MyCustomActivity.insuredInfolBean.getInsuredAddress());
        address_tv.setText(MyCustomActivity.insuredInfolBean.getInsuredAddress());
        email_tv.setText(MyCustomActivity.insuredInfolBean.getEmail());
        more_tv.setText(MyCustomActivity.insuredInfolBean.getInsurantDesc());
        switch (MyCustomActivity.insuredInfolBean.getIdType()) {
            case "1":
                idType_tv.setText("身份证");
                break;
            case "2":
                idType_tv.setText("军官证");
                break;
            case "3":
                idType_tv.setText("护照");
                break;
            case "4":
                idType_tv.setText("驾驶证");
                break;
            case "5":
                idType_tv.setText("港澳台通行证");
                break;
            case "6":
                idType_tv.setText("回乡证");
                break;
            default:
                break;
        }
    }
}
