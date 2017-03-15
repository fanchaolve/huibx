package com.bb.hbx.activitiy;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
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
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.City;
import com.bb.hbx.base.County;
import com.bb.hbx.base.Province;
import com.bb.hbx.base.Street;
import com.bb.hbx.bean.AddInsured;
import com.bb.hbx.bean.SingleCustomBean;
import com.bb.hbx.interfaces.LoginTextWatcher;
import com.bb.hbx.utils.GetPhoneContactsUtil;
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

import static com.yintong.secure.c.ae.j.br;

/**
 * 添加客户界面
 */
public class AddContactActivity extends BaseActivity implements View.OnClickListener, OnAddressSelectedListener,
        AddressSelector.OnDialogCloseListener, LoginContract.View {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.birth_layout)
    RelativeLayout birth_layout;

    @BindView(R.id.area_layout)
    RelativeLayout area_layout;

    @BindView(R.id.sex_layout)
    RelativeLayout sex_layout;

    @BindView(R.id.idType_layout)
    RelativeLayout idType_layout;

    @BindView(R.id.fromContacts_tv)
    TextView fromContacts_tv;

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

    private BottomDialog dialogAddress;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private String mSex;
    private String mIdType;

    String birthday = "";
    String[] itemBuf = new String[4];
    String[] infoBuf = new String[4];

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_contact;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        if (!TextUtils.isEmpty(name)) {
            name_et.setText(name);
        }
        if (!TextUtils.isEmpty(phone)) {
            phone_et.setText(phone);
        }
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        birth_layout.setOnClickListener(this);
        sex_layout.setOnClickListener(this);
        idType_layout.setOnClickListener(this);
        fromContacts_tv.setOnClickListener(this);
        area_layout.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
        name_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        //idType_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        idNumber_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));
        phone_et.addTextChangedListener(new LoginTextWatcher(verify_tv, this));

    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.fromContacts_tv:
                //showTip("从联系人导入");
                Uri uri = ContactsContract.Contacts.CONTENT_URI;
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intent, 0);
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
                        birthday = year + month + day;
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
                String name = name_et.getText().toString().trim();
                String sex = sex_et.getText().toString().trim();
                String gender = "男".equals(sex) ? "1" : "2";
                String birthday = birth_et.getText().toString().trim();
                String phone = phone_et.getText().toString().trim();
                String idType = idType_et.getText().toString().trim();
                String idNumber = idNumber_et.getText().toString().trim();
                String address = area_et.getText().toString().trim();
                String street = address_et.getText().toString().trim();
                String email = email_et.getText().toString().trim();
                String descr = more_et.getText().toString().trim();
                itemBuf[0] = name;
                itemBuf[1] = phone;
                //1"身份证", 2"军官证", 3"护照", 4"驾驶证", 5"港澳台通行证", 6"回乡证"
                switch (idType) {
                    case "身份证":
                        itemBuf[2] = "1";
                        break;
                    case "军官证":
                        itemBuf[2] = "2";
                        break;
                    case "护照":
                        itemBuf[2] = "3";
                        break;
                    case "驾驶证":
                        itemBuf[2] = "4";
                        break;
                    case "港澳台通行证":
                        itemBuf[2] = "5";
                        break;
                    case "回乡证":
                        itemBuf[2] = "6";
                        break;
                    default:
                        break;
                }
//                itemBuf[2] = idType;
//                itemBuf[2] = "1";
                itemBuf[3] = idNumber;
                infoBuf[0] = "姓名";
                infoBuf[1] = "手机号";
                infoBuf[2] = "证件类型";
                infoBuf[3] = "证件号码";
                SingleCustomBean singleCustomBean = new SingleCustomBean(MyApplication.user.getUserId(), name, gender, birthday, phone, "1", idNumber, address, street, email, descr);
                //!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(phone)/*&&!TextUtils.isEmpty(idType)*/&&!TextUtils.isEmpty(idNumber)
                if (isverTel() && isverCode() && isverpassword() && isCheckbx()) {
                    ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                    //Call call=service.addInsured(MyApplication.user.getUserId(),name,phone,"1",idNumber);
                    Call call = service.addInsured(singleCustomBean);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Result_Api body = (Result_Api) response.body();
                            AddInsured addInsured = (AddInsured) body.getOutput();
                            showTip(body.getRespMsg());
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            showTip("新增联系人失败");
                        }
                    });
                } else {
                    for (int i = 0; i < itemBuf.length; i++) {
                        if (TextUtils.isEmpty(itemBuf[i])) {
                            showTip(infoBuf[i] + "不能为空!");
                            return;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri = data.getData();
                String[] contacts = GetPhoneContactsUtil.getPhoneContacts(this, uri);
                name_et.setText(contacts[0]);
                phone_et.setText(contacts[1]);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void dialogclose() {
        if (dialogAddress != null) {
            dialogAddress.dismiss();
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        //address=s;
        //areaId=city.id+"";
        /*address_tv.setText(s);
        address_tv.setTextColor(getResources().getColor(R.color.A3));*/
        area_et.setText(s);
        if (dialogAddress != null) {
            dialogAddress.dismiss();
        }
    }

    @Override
    public void loginSuccess() {

    }


    //判断姓名
    @Override
    public boolean isverTel() {
        if (!TextUtils.isEmpty(name_et.getText().toString().trim())) {
            return true;
        }
        return false;
    }

    //判断证件类型
    @Override
    public boolean isverCode() {
        if (!TextUtils.isEmpty(idType_et.getText().toString().trim())) {
            return true;
        }
        return false;
    }

    //判断证件号码
    @Override
    public boolean isverpassword() {
        if (!TextUtils.isEmpty(idNumber_et.getText().toString().trim())) {
            return true;
        }
        return false;
    }

    //判断手机号码
    @Override
    public boolean isCheckbx() {
        if (!TextUtils.isEmpty(phone_et.getText().toString().trim())) {
            return true;
        }
        return false;
    }
}
