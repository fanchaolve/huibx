package com.bb.hbx.activitiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;
import com.bb.hbx.db.DatabaseImpl;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEmailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.save_tv)
    TextView save_tv;
    @BindView(R.id.email_et)
    EditText email_et;

    Intent intentFromPersonInfo;
    String email;
    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_email;
    }

    @Override
    public void initView() {
        intentFromPersonInfo = getIntent();
        email = intentFromPersonInfo.getStringExtra("email");
        if (!TextUtils.isEmpty(email))
        {
            email_et.setText(email);
        }
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        save_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.save_tv:
                final String email = email_et.getText().toString();
                if (!TextUtils.isEmpty(email))
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("是否确认更改邮箱地址");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String userId=null;
                            final SQLiteDatabase db= DatabaseImpl.getInstance().getReadableDatabase();
                            Cursor cursor = db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
                            if (cursor!=null)
                            {
                                if (cursor.moveToNext())
                                {
                                    userId = cursor.getString(cursor.getColumnIndex("userId"));
                                }
                            }
                            if (!TextUtils.isEmpty(userId))
                            {
                                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                                Call call=service.updateUserInfoEmail(userId,email);
                                call.enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        Result_Api body = (Result_Api) response.body();
                                        if (body!=null)
                                        {
                                            if (body.isSuccess())
                                            {
                                                //SQLiteDatabase db= DatabaseImpl.getInstance().getReadableDatabase();
                                                db.execSQL("update userstb set email=? where currentUser=currentUser ",
                                                        new String[]{email});
                                                //db.close();
                                                intentFromPersonInfo.putExtra("email",email);
                                                setResult(Can.RESULT_EMAIL,intentFromPersonInfo);
                                                finish();
                                            }
                                            showTip(body.getRespMsg());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {

                                    }
                                });
                            }
                            cursor.close();
                            db.close();
                        }
                    });
                    dialog.setNegativeButton("取消", null);
                    dialog.show();
                }
                else
                {
                    showTip("邮箱地址不能为空!");
                }
                break;
            default:
                break;
        }
    }
}
