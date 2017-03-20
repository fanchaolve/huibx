package com.bb.hbx.base.m;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bb.hbx.MyApplication;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.v.WelcomeContract;
import com.bb.hbx.bean.User;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.utils.MyUsersSqlite;

import retrofit2.Call;
import retrofit2.Callback;

import static com.bb.hbx.MyApplication.user;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelcomeModel implements WelcomeContract.Model {


    private ApiService service;


    public WelcomeModel() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void getClientCtlInfo(String userId, Callback callback) {

        Call call = service.getClientCtlInfo(userId);
        call.enqueue(callback);
    }

    @Override
    public User settingUser() {
        //User user=new User();
        //boolean hasLogined = ShareSPUtils.sp.getBoolean("hasLogined", false);
        //MyUsersSqlite.db
        SQLiteDatabase db= DatabaseImpl.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
        if (cursor != null) {
            if (!cursor.moveToNext())//没有数据,则首次使用,初始化部分数据
            {
                ContentValues values = new ContentValues();
                values.put("currentUser", "currentUser");
                values.put("hasLogined", "false");//默认false,未登录
                values.put("isBClient", "false");//默认false
                values.put("sessionId", "");
                values.put("userId", "");
                values.put("authority", "0");
                values.put("phone", "");
                values.put("gender", "0");//默认为0
                values.put("userQrcode", "");//默认为0
                long flag = MyUsersSqlite.db.insert("userstb", null, values);
                //Toast.makeText(this,"插入新用户成功:"+flag,Toast.LENGTH_SHORT).show();
                values.clear();

                MyApplication.user.setUserId("");
                MyApplication.user.setAuthority("0");
                MyApplication.user.setMobile("");
                MyApplication.user.setLoginPwd("0");
                MyApplication.user.setPaymentPwd("0");//是否设置过支付密码
                MyApplication.user.setSessionId("");
                MyApplication.user.setIsBClient(false);
                MyApplication.user.setUserQrcode("");
            } else {
                String hasLogined = cursor.getString(cursor.getColumnIndex("hasLogined"));
                if (hasLogined.equals("true"))//首次登陆后,数据会被刷新
                {
                    String userId = cursor.getString(cursor.getColumnIndex("userId"));
                    String authority = cursor.getString(cursor.getColumnIndex("authority"));
                    String phone = cursor.getString(cursor.getColumnIndex("phone"));
                    String sessionId = cursor.getString(cursor.getColumnIndex("sessionId"));
                    String isBClient = cursor.getString(cursor.getColumnIndex("isBClient"));
                    String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                    String paymentPwd = cursor.getString(cursor.getColumnIndex("paymentPwd"));
                    String userQrcode = cursor.getString(cursor.getColumnIndex("userQrcode"));
                    MyApplication.user.setUserQrcode(userQrcode);
                    MyApplication.user.setUserId(userId);
                    MyApplication.user.setAuthority(authority);
                    MyApplication.user.setMobile(phone);
                    //MyApplication.user.setLoginPwd(TextUtils.isEmpty(pwd)?"0":"1");
                    MyApplication.user.setLoginPwd(pwd);
                    MyApplication.user.setPaymentPwd(paymentPwd);
                    MyApplication.user.setSessionId(sessionId);
                    MyApplication.user.setIsBClient(isBClient.equals("true") ? true : false);
                } else {
                    MyApplication.user.setUserId("");
                    MyApplication.user.setAuthority("0");
                    MyApplication.user.setMobile("");
                    MyApplication.user.setLoginPwd("0");
                    MyApplication.user.setPaymentPwd("0");
                    MyApplication.user.setSessionId("");
                    MyApplication.user.setIsBClient(false);
                    MyApplication.user.setUserQrcode("");
                }
            }
            db.close();
        }
        return user;
    }
}
