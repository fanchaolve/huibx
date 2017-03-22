package com.bb.hbx.activitiy;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.DatePickerDialog;
import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.login.LoginContract;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.SingleCustomEditBean;
import com.bb.hbx.bean.address.AddressBean;
import com.bb.hbx.interfaces.LoginTextWatcher;
import com.bb.hbx.utils.LogUtil;
import com.bb.hbx.widget.adress.AddressSelector;
import com.bb.hbx.widget.adress.BottomDialog;
import com.bb.hbx.widget.adress.OnAddressSelectedListener;
import com.bb.hbx.widget.dialog.IdTypePickerDialog;
import com.bb.hbx.widget.dialog.SexPickerDialog;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInsuredInfoActivity extends BaseActivity implements View.OnClickListener,
        LoginContract.View, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.sex_layout)
    RelativeLayout sex_layout;
    @BindView(R.id.birth_layout)
    RelativeLayout birth_layout;
    @BindView(R.id.idType_layout)
    RelativeLayout idType_layout;
    @BindView(R.id.area_layout)
    RelativeLayout area_layout;
    @BindView(R.id.name_et)
    EditText name_et;
    @BindView(R.id.sex_et)
    EditText sex_et;
    @BindView(R.id.birth_et)
    EditText birth_et;
    @BindView(R.id.phone_et)
    EditText phone_et;
    @BindView(R.id.idType_et)
    EditText idType_et;
    @BindView(R.id.idNumber_et)
    EditText idNumber_et;
    @BindView(R.id.area_et)
    EditText area_et;
    @BindView(R.id.address_et)
    EditText address_et;
    @BindView(R.id.email_et)
    EditText email_et;
    @BindView(R.id.more_et)
    EditText more_et;
    @BindView(R.id.verify_tv)
    TextView verify_tv;

    String[] itemBuf = new String[4];
    String[] infoBuf = new String[4];

    String name;
    String sex;
    String birth;
    String mobile;
    String idNo;
    String area;
    String email;
    String idType;

    private BottomDialog dialogAddress;
    private String provinceId;
    private String cityId;
    private String countyId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_insured_info;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        sex_layout.setOnClickListener(this);
        birth_layout.setOnClickListener(this);
        idType_layout.setOnClickListener(this);
        area_layout.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
        name_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        //idType_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        idNumber_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        phone_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
    }

    @Override
    public void initdata() {
        name_et.setText(MyCustomActivity.insuredInfolBean.getInsuredName());
        sex_et.setText(MyCustomActivity.insuredInfolBean.getGender());
        birth_et.setText(MyCustomActivity.insuredInfolBean.getBirthday());
        phone_et.setText(MyCustomActivity.insuredInfolBean.getMobile());
        idNumber_et.setText(MyCustomActivity.insuredInfolBean.getIdNo());
        area_et.setText(MyCustomActivity.insuredInfolBean.getInsuredAddress());
        address_et.setText("没有字段");
        email_et.setText(MyCustomActivity.insuredInfolBean.getEmail());
        more_et.setText(MyCustomActivity.insuredInfolBean.getInsurantDesc());
        switch (MyCustomActivity.insuredInfolBean.getIdType()) {
            case "1":
                idType_et.setText("身份证");
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.sex_layout:
                SexPickerDialog sexDialog = new SexPickerDialog(mContext);
                sexDialog.setDialogMode(SexPickerDialog.DIALOG_MODE_BOTTOM);
                sexDialog.show();
                sexDialog.setOnSexPickListener(new SexPickerDialog.OnSexPickListener() {
                    @Override
                    public void onClick(String sex) {
                        sex_et.setText(sex);
//                        mSex = sex;
                    }

                    @Override
                    public void ondissmiss() {

                    }
                });
                break;
            case R.id.birth_layout:
                DatePickerDialog dialog = new DatePickerDialog(mContext);
                dialog.setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                dialog.show();
                dialog.setDatePickListener(new DatePickerDialog.OnDatePickListener() {
                    @Override
                    public void onClick(String year, String month, String day) {
                        birth_et.setText(year + "-" + month + "-" + day);
//                        birthday = year + month + day;
                    }

                    @Override
                    public void ondissmiss() {
                        //il_up3.setdownImageResource();
                    }
                });
                break;
            case R.id.idType_layout:
                IdTypePickerDialog idTypeDialog = new IdTypePickerDialog(mContext);
                idTypeDialog.setDialogMode(IdTypePickerDialog.DIALOG_MODE_BOTTOM);
                idTypeDialog.show();
                idTypeDialog.setOnIdTypePickListener(new IdTypePickerDialog.OnIdTypePickListener() {
                    @Override
                    public void onClick(String idType) {
                        idType_et.setText(idType);
//                        mIdType = idType;
                    }

                    @Override
                    public void ondissmiss() {

                    }
                });
                break;
            case R.id.area_layout:
                if (dialogAddress != null) {
                    dialogAddress.show();
                } else {
                    dialogAddress = new BottomDialog(this);
                    dialogAddress.setOnAddressSelectedListener(this);
                    dialogAddress.setDialogDismisListener(this);
                    dialogAddress.setTextSize(14);//设置字体的大小
                    dialogAddress.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
                    dialogAddress.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
                    dialogAddress.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
                    dialogAddress.show();
                }
                break;
            case R.id.verify_tv:
                //showTip("保存");
                name = name_et.getText().toString();
                sex = "男".equals(sex_et.getText().toString().trim()) ? "1" : "2";
                birth = birth_et.getText().toString();
                mobile = phone_et.getText().toString().trim();
                idNo = idNumber_et.getText().toString().trim();
                area = area_et.getText().toString().trim();
                final String areaId = countyId;
                final String street = address_et.getText().toString();
                email = email_et.getText().toString().trim();
                final String descr = more_et.getText().toString().trim();
                idType = idType_et.getText().toString().trim();
                final String insuredId = MyCustomActivity.insuredInfolBean.getInsuredId();
                switch (idType) {
                    case "身份证":
                        idType = "1";
                        break;
                    case "军官证":
                        idType = "2";
                        break;
                    case "护照":
                        idType = "3";
                        break;
                    case "驾驶证":
                        idType = "4";
                        break;
                    case "港澳台通行证":
                        idType = "5";
                        break;
                    case "回乡证":
                        idType = "6";
                        break;
                    default:
                        break;
                }

                itemBuf[0] = name;
                itemBuf[1] = mobile;
                itemBuf[2] = idType;
                itemBuf[3] = idNo;
                infoBuf[0] = "姓名";
                infoBuf[1] = "手机号";
                infoBuf[2] = "证件类型";
                infoBuf[3] = "证件号码";
                for (int i = 0; i < itemBuf.length; i++) {
                    if (TextUtils.isEmpty(itemBuf[i])) {
                        showTip(infoBuf[i] + "不能为空!");
                        return;
                    }
                }
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
               /* Call call=service.updateInsured(MyApplication.user.getUserId(),MyCustomActivity.insuredInfolBean.getInsuredId(),name,
                                    mobile,"1",idNo);*/
                SingleCustomEditBean editBean = new SingleCustomEditBean(MyApplication.user.getUserId(),
                        insuredId, name, sex, birth, mobile, idType, idNo, areaId, street, email, null, null, descr);
                Call call = service.updateInsured(editBean);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        MyCustomActivity.insuredInfolBean.setInsuredName(name);
                        MyCustomActivity.insuredInfolBean.setGender(sex);
                        MyCustomActivity.insuredInfolBean.setBirthday(birth);
                        MyCustomActivity.insuredInfolBean.setMobile(mobile);
                        MyCustomActivity.insuredInfolBean.setIdNo(idNo);
                        MyCustomActivity.insuredInfolBean.setInsuredAddress(areaId);
                        MyCustomActivity.insuredInfolBean.setEmail(email);
                        MyCustomActivity.insuredInfolBean.setIdType(idType);
                        MyCustomActivity.insuredInfolBean.setInsuredId(insuredId);
                        MyCustomActivity.insuredInfolBean.setInsuredAddress(street);
                        MyCustomActivity.insuredInfolBean.setInsurantDesc(descr);

                        showTip("修改信息成功!");
                        finish();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        showTip("更新信息失败");
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess() {

    }

    //判断姓名
    @Override
    public boolean isverTel() {
        if (!TextUtils.isEmpty(name_et.getText())) {
            return true;
        }
        return false;
    }

    //判断证件类型
    @Override
    public boolean isverCode() {
        return true;
    }

    //判断证件号码
    @Override
    public boolean isverpassword() {
        if (!TextUtils.isEmpty(idNumber_et.getText())) {
            return true;
        }
        return false;
    }

    //判断手机号码
    @Override
    public boolean isCheckbx() {
        if (!TextUtils.isEmpty(phone_et.getText())) {
            return true;
        }
        return false;
    }

    @Override
    public void dialogclose() {
        if (dialogAddress != null) {
            dialogAddress.dismiss();
        }
    }

    @Override
    public void onAddressSelected(AddressBean.AreasListBean province, AddressBean.AreasListBean.ChildrenBeanX city, AddressBean.AreasListBean.ChildrenBeanX.ChildrenBean county) {
        provinceId = (province == null ? "" : province.getAreaId() + "");
        cityId = (city == null ? "" : city.getAreaId() + "");
        countyId = (county == null ? "" : county.getAreaId() + "");
        LogUtil.d("数据——id", "省份id=" + provinceId);
        LogUtil.d("数据——id", "城市id=" + cityId);
        LogUtil.d("数据——id", "乡镇id=" + countyId);

        String str = (province == null ? "" : province.getAreaName())
                + (city == null ? "" : city.getAreaName())
                + (county == null ? "" : county.getAreaName());
        area_et.setText(str);
        if (dialogAddress != null) {
            dialogAddress.dismiss();
        }
    }
}
