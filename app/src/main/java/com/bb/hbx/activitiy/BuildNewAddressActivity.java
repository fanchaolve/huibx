package com.bb.hbx.activitiy;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.AddConsignee;
import com.bb.hbx.utils.GetPhoneContactsUtil;
import com.bb.hbx.utils.LogUtil;
import com.bb.hbx.widget.adress.AddressSelector;
import com.bb.hbx.widget.adress.BottomDialog;
import com.bb.hbx.widget.adress.OnAddressSelectedListener;
import com.bb.hbx.bean.address.AddressBean.AreasListBean;
import com.bb.hbx.bean.address.AddressBean.AreasListBean.ChildrenBeanX;
import com.bb.hbx.bean.address.AddressBean.AreasListBean.ChildrenBeanX.ChildrenBean;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildNewAddressActivity extends BaseActivity implements View.OnClickListener,OnAddressSelectedListener,AddressSelector.OnDialogCloseListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.contacts_layout)
    RelativeLayout contacts_layout;
    @BindView(R.id.address_layout)
    RelativeLayout address_layout;
    @BindView(R.id.isNormalAddress_iv)
    ImageView isNormalAddress_iv;
    @BindView(R.id.isNormalAddress_layout)
    RelativeLayout isNormalAddress_layout;
    @BindView(R.id.name_et)
    EditText name_et;
    @BindView(R.id.phone_et)
    EditText phone_et;
    @BindView(R.id.post_et)
    EditText post_et;
    @BindView(R.id.street_et)
    EditText street_et;
    @BindView(R.id.address_tv)
    TextView address_tv;
    @BindView(R.id.save_tv)
    TextView save_tv;

    boolean isNormalAddress;

    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
//    private String streetCode;

    String userId;
    String areaId;
    String defaultFlag="0";
    boolean syncUser;
    String address;
    @Override
    public int getLayoutId() {
        return R.layout.activity_build_new_address;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        contacts_layout.setOnClickListener(this);
        address_layout.setOnClickListener(this);
        isNormalAddress_layout.setOnClickListener(this);
        save_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        syncUser = intent.getBooleanExtra("syncUser",false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.contacts_layout:
                //showTip("klajsfsjf");
                Uri uri = ContactsContract.Contacts.CONTENT_URI;
                Intent intent = new Intent(Intent.ACTION_PICK,uri);
                startActivityForResult(intent,0);
                break;
            case R.id.address_layout:
                if (dialog != null) {
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this);
                    dialog.setOnAddressSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.setTextSize(14);//设置字体的大小
                    dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
                    dialog.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
                    dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
                    dialog.show();
                }
                /*ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call=service.getAreaList(true);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });*/
                break;
            case R.id.isNormalAddress_layout:
                if (isNormalAddress)
                {
                    isNormalAddress_iv.setSelected(false);
                    defaultFlag="0";
                    isNormalAddress=false;
                }
                else
                {
                    isNormalAddress_iv.setSelected(true);
                    defaultFlag="1";
                    isNormalAddress=true;
                }
                break;
            case R.id.save_tv:
                String name = name_et.getText().toString();
                String phone = phone_et.getText().toString();
                String post = post_et.getText().toString();
                String street = street_et.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||
                        TextUtils.isEmpty(post)||TextUtils.isEmpty(areaId)||
                        TextUtils.isEmpty(address) ||TextUtils.isEmpty(street))
                {
                    showTip("请完善收货人信息");
                }
                else
                {
                    ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                    Call call=service.addConsignee(userId,name,phone,post,areaId,address+street,syncUser?"1":"0",defaultFlag);
                    showTip("=====defaultFlag======"+defaultFlag+":!!!!!!"+post);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Result_Api body = (Result_Api) response.body();
                            AddConsignee addConsignee = (AddConsignee) body.getOutput();
                            int cneeId = addConsignee.getCneeId();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            showTip("请检查填写信息");
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(data==null)
                {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri=data.getData();
                String[] contacts= GetPhoneContactsUtil.getPhoneContacts(this,uri);
                name_et.setText(contacts[0]);
                phone_et.setText(contacts[1]);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void dialogclose() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void onAddressSelected(AreasListBean province, ChildrenBeanX city, ChildrenBean county) {
        provinceCode = (province == null ? "" : province.getAreaId() + "");
        cityCode = (city == null ? "" : city.getAreaId() + "");
        countyCode = (county == null ? "" : county.getAreaId() + "");
//        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
//        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.getAreaName())
                + (city == null ? "" : city.getAreaName())
                + (county == null ? "" : county.getAreaName());
        address=s;
        areaId=city.getAreaId()+"";
        address_tv.setText(s);
        address_tv.setTextColor(getResources().getColor(R.color.A3));
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
