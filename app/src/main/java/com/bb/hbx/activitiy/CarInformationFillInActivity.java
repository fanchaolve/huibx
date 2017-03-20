package com.bb.hbx.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.DatePickerDialog;
import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.utils.AppManager;

import butterknife.BindView;

/**
 * Created by fancl
 * 车险 信息填写
 */

public class CarInformationFillInActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.registTime_ib)
    ImageView registTime_ib;
    @BindView(R.id.jqxTime_ib)
    ImageView jqxTime_ib;
    @BindView(R.id.syxTime_ib)
    ImageView syxTime_ib;
    @BindView(R.id.registTime_tv)
    TextView registTime_tv;
    @BindView(R.id.jqxTime_tv)
    TextView jqxTime_tv;
    @BindView(R.id.syxTime_tv)
    TextView syxTime_tv;


    @Override
    public int getLayoutId() {
        return R.layout.carinfomationfillin;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        toolbar.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        registTime_ib.setOnClickListener(this);
        jqxTime_ib.setOnClickListener(this);
        syxTime_ib.setOnClickListener(this);

    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
            case R.id.tv_submit:
                AppManager.getInstance().showActivity(SelectCarActivity.class, null);
                break;
            case R.id.registTime_ib:
                registTime_ib.setImageResource(R.drawable.xialaicon_xiangshang);
                DatePickerDialog dialog = new DatePickerDialog(mContext);
                dialog.setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                dialog.show();
                dialog.setDatePickListener(new DatePickerDialog.OnDatePickListener() {
                    @Override
                    public void onClick(String year, String month, String day) {
                        registTime_tv.setText(year + "-" + month + "-" + day);
                    }

                    @Override
                    public void ondissmiss() {
                        registTime_ib.setImageResource(R.drawable.xialaicon);
                    }
                });
                break;
            case R.id.jqxTime_ib:
                jqxTime_ib.setImageResource(R.drawable.xialaicon_xiangshang);
                DatePickerDialog dialogJQX = new DatePickerDialog(mContext);
                dialogJQX.setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                dialogJQX.show();
                dialogJQX.setDatePickListener(new DatePickerDialog.OnDatePickListener() {
                    @Override
                    public void onClick(String year, String month, String day) {
                        jqxTime_tv.setText(year + "-" + month + "-" + day);
                    }

                    @Override
                    public void ondissmiss() {
                        jqxTime_ib.setImageResource(R.drawable.xialaicon);
                    }
                });
                break;
            case R.id.syxTime_ib:
                syxTime_ib.setImageResource(R.drawable.xialaicon_xiangshang);
                DatePickerDialog dialogSYX = new DatePickerDialog(mContext);
                dialogSYX.setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                dialogSYX.show();
                dialogSYX.setDatePickListener(new DatePickerDialog.OnDatePickListener() {
                    @Override
                    public void onClick(String year, String month, String day) {
                        syxTime_tv.setText(year + "-" + month + "-" + day);
                    }

                    @Override
                    public void ondissmiss() {
                        syxTime_ib.setImageResource(R.drawable.xialaicon);
                    }
                });
                break;
            default:
                break;
        }
    }
}
