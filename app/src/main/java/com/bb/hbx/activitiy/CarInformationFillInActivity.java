package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.DatePickerDialog;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.ComCarPropsBean;
import com.bb.hbx.utils.AppManager;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fancl
 * 车险 信息填写
 */

public class CarInformationFillInActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.businessCar_tv)
    TextView businessCar_tv;

    @BindView(R.id.modelType_et)
    EditText modelType_et;
    @BindView(R.id.vinNo_et)
    EditText vinNo_et;
    @BindView(R.id.engineNo_et)
    EditText engineNo_et;
    @BindView(R.id.seats_et)
    EditText seats_et;
    @BindView(R.id.driveName_et)
    EditText driveName_et;
    @BindView(R.id.idNo_et)
    EditText idNo_et;
    @BindView(R.id.mobile_et)
    EditText mobile_et;

    @BindView(R.id.registTime_ib)
    ImageView registTime_ib;
    @BindView(R.id.jqxTime_ib)
    ImageView jqxTime_ib;
    @BindView(R.id.syxTime_ib)
    ImageView syxTime_ib;
    @BindView(R.id.transferDate_ib)
    ImageView transferDate_ib;
    @BindView(R.id.registTime_et)
    EditText registTime_et;
    @BindView(R.id.jqxTime_et)
    EditText jqxTime_et;
    @BindView(R.id.syxTime_et)
    EditText syxTime_et;

    @BindView(R.id.transferCar_cb)
    CheckBox transferCar_cb;
    @BindView(R.id.transferDate_rl)
    RelativeLayout transferDate_rl;
    @BindView(R.id.transferDate_et)
    EditText transferDate_et;

    String serialId;
    String licenseNo;
    String registTime;
    String jqxTime;
    String syxTime;
    String transferDate="";
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
        businessCar_tv.setOnClickListener(this);
        transferDate_ib.setOnClickListener(this);

        transferCar_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    transferDate_rl.setVisibility(View.VISIBLE);
                }
                else
                {
                    transferDate_rl.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        serialId = bundle.getString("serialId");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
            case R.id.tv_submit:
                String modelType = modelType_et.getText().toString().trim();
                String vinNo = vinNo_et.getText().toString().trim();
                String engineNo = engineNo_et.getText().toString().trim();
                String seats = seats_et.getText().toString().trim();
                String driveName = driveName_et.getText().toString().trim();
                String idNo = idNo_et.getText().toString().trim();
                String mobile = mobile_et.getText().toString().trim();
                if (TextUtils.isEmpty(modelType))
                {
                    showTip("请填写品牌型号");
                    return;
                }
                if (TextUtils.isEmpty(vinNo))
                {
                    showTip("请填写车辆识别代码");
                    return;
                }
                if (TextUtils.isEmpty(engineNo))
                {
                    showTip("请填写发动机号");
                    return;
                }
                if (TextUtils.isEmpty(registTime))
                {
                    showTip("请填写注册时间");
                    return;
                }
                if (TextUtils.isEmpty(jqxTime))
                {
                    showTip("请填写交强险起保时间");
                    return;
                }
                if (TextUtils.isEmpty(syxTime))
                {
                    showTip("请填写商业险起保时间");
                    return;
                }
                if (TextUtils.isEmpty(seats))
                {
                    showTip("请填写荷载人数");
                    return;
                }
                if (transferDate_rl.getVisibility()==View.VISIBLE)
                {
                    transferDate = transferDate_et.getText().toString().trim();
                    if (TextUtils.isEmpty(transferDate))
                    {
                        showTip("请填写过户日期");
                        return;
                    }
                }
                if (TextUtils.isEmpty(driveName))
                {
                    showTip("请填写车主姓名");
                    return;
                }
                if (TextUtils.isEmpty(idNo))
                {
                    showTip("请填写身份证");
                    return;
                }
                if (TextUtils.isEmpty(mobile))
                {
                    showTip("请填写手机号");
                    return;
                }
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call=service.compCarProps(serialId,"0",modelType,vinNo,engineNo,registTime,seats,syxTime,jqxTime,transferCar_cb.isChecked()?"1":"0",
                        transferDate,driveName,idNo,mobile,new String[2]);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api body = (Result_Api) response.body();
                        ComCarPropsBean bean = (ComCarPropsBean) body.getOutput();
                        if (body.isSuccess())
                        {
                            if (bean!=null)
                            {
                                Bundle bundle = new Bundle();
                                //bundle.putParcelable("comCarPropsBean",bean);
                                bundle.putParcelableArrayList("carinfoList", (ArrayList<? extends Parcelable>) bean.getCarInfoList());
                                int size = bean.getPlanList().size();
                                bundle.putInt("size",size);
                                for (int i = 0; i < size; i++) {
                                    bundle.putParcelableArrayList("syxList"+i, (ArrayList<? extends Parcelable>) bean.getPlanList().get(i).getSyxList());
                                }
                                //bundle.putParcelable("carBean",bean);
                                bundle.putString("serialId",serialId);
                                bundle.putString("carExtras",bean.getExtraJson());
                                AppManager.getInstance().showActivity(SelectCarActivity.class, bundle);
                            }
                        }
                        else
                        {
                            showTip(body.getRespMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            case R.id.registTime_ib:
                registTime_ib.setImageResource(R.drawable.xialaicon_xiangshang);
                DatePickerDialog dialog = new DatePickerDialog(mContext);
                dialog.setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                dialog.show();
                dialog.setDatePickListener(new DatePickerDialog.OnDatePickListener() {
                    @Override
                    public void onClick(String year, String month, String day) {
                        int monthInt = Integer.parseInt(month);
                        int dayInt = Integer.parseInt(day);
                        registTime=year + monthInt/10+monthInt%10 +  dayInt/10+dayInt%10;
                        registTime_et.setText(year + "-" + monthInt/10+monthInt%10 + "-" + dayInt/10+dayInt%10);
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
                        int monthInt = Integer.parseInt(month);
                        int dayInt = Integer.parseInt(day);
                        jqxTime=year + monthInt/10+monthInt%10 + dayInt/10+dayInt%10;
                        jqxTime_et.setText(year + "-" + monthInt/10+monthInt%10 + "-" + dayInt/10+dayInt%10);
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
                        int monthInt = Integer.parseInt(month);
                        int dayInt = Integer.parseInt(day);
                        syxTime=year + monthInt/10+monthInt%10 + dayInt/10+dayInt%10;
                        syxTime_et.setText(year + "-" + monthInt/10+monthInt%10 + "-" + dayInt/10+dayInt%10);
                    }

                    @Override
                    public void ondissmiss() {
                        syxTime_ib.setImageResource(R.drawable.xialaicon);
                    }
                });
                break;
            case R.id.transferDate_ib:
                transferDate_ib.setImageResource(R.drawable.xialaicon_xiangshang);
                DatePickerDialog dialogGHSJ = new DatePickerDialog(mContext);
                dialogGHSJ.setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                dialogGHSJ.show();
                dialogGHSJ.setDatePickListener(new DatePickerDialog.OnDatePickListener() {
                    @Override
                    public void onClick(String year, String month, String day) {
                        int monthInt = Integer.parseInt(month);
                        int dayInt = Integer.parseInt(day);
                        transferDate=year + monthInt/10+monthInt%10 + dayInt/10+dayInt%10;
                        transferDate_et.setText(year + "-" + monthInt/10+monthInt%10 + "-" + dayInt/10+dayInt%10);
                    }

                    @Override
                    public void ondissmiss() {
                        transferDate_ib.setImageResource(R.drawable.xialaicon);
                    }
                });
                break;
            case R.id.businessCar_tv:
                showTip("企业车辆");
                break;
            default:
                break;
        }
    }
}
