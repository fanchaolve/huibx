package com.bb.hbx.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bb.hbx.R;
import com.bb.hbx.cans.Can;

import java.io.File;

/**
 * Created by Administrator on 2017/1/5.
 */

public class ShareSPUtils {

    //记录用户登录状态
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;
    public static Context mContext;

    public static void initShareSP(Context context) {
        mContext = context;
        sp = context.getSharedPreferences("usersinfo", Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    public static void readShareSP(ViewGroup notLogin, ImageView userIcon, /*TextView hasLogin,*/Context context) {
        if (sp != null) {
            Can.hasLogined = sp.getBoolean("hasLogined", false);
            if (!Can.hasLogined)//用户未登录执行的逻辑
            {
                String parentPath = Can.getDefaultUsersIconFile();
                File file = new File(parentPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String usersIconPath = new File(file, Can.userIconDefault.substring(Can.userIconDefault.lastIndexOf("/") + 1)).getAbsolutePath();
                if (notLogin.getVisibility() == View.GONE) {
                    notLogin.setVisibility(View.VISIBLE);
                    //hasLogin.setVisibility(View.GONE);
                }
                userIcon.setImageResource(R.drawable.defult_icon);
                //userIcon.setImageBitmap(BitmapFactory.decodeFile(usersIconPath));//默认头像的下载是在downloadService中实现,此时可能未下载完成,,所以先用glide吧
                //Glide.with(mContext).load(Can.userIconDefault).into(userIcon);
            } else //用户已登录执行的逻辑
            {
                Can.userName = sp.getString("userName", null);
                Can.userPwd = sp.getString("userPwd", null);
                Can.userIcon = sp.getString("userIcon", null);
                /*if (hasLogin.getVisibility()==View.GONE)
                {*/
                notLogin.setVisibility(View.GONE);
                   /* hasLogin.setVisibility(View.VISIBLE);
                }*/
                //Bitmap bitmap = BitmapFactory.decodeFile(Can.userIcon);------------------
//                int byteCount = bitmap.getByteCount();
                //从本地文件加载图片
                //userIcon.setImageBitmap(bitmap);-----------------
                //hasLogin.setText(Can.userName);

            }
        } else {
            Toast.makeText(context, "sharePreference建立失败", Toast.LENGTH_SHORT).show();

        }
    }

    public static String writeShareSp(boolean loginFlag, String userId, String sessionId, String userName, String phone, String pwd) {
        String parentPath = Can.getDefaultUsersIconFile();
        File file = new File(parentPath);
        String usersIconPath = new File(file, Can.userIconDefault.substring(Can.userIconDefault.lastIndexOf("/") + 1)).getAbsolutePath();
        edit.putBoolean("hasLogined", loginFlag);
        edit.putString("userId", userId);
        edit.putString("sessionId", sessionId);
        edit.putString("userName", userName);
        edit.putString("userPhone", phone);
        edit.putString("userPwd", pwd);
        edit.putString("userIcon", usersIconPath);
        edit.commit();
        return usersIconPath;
    }

    /**
     * 记录签到后返回的信息
     *
     * @param content
     */
    public static void writeContent(String content) {
        edit.putString("content", content);
        edit.commit();
    }

    /**
     * 读取签到后返回的信息
     *
     * @return
     */
    public static String readContent() {
        return sp.getString("content", "");
    }
}
