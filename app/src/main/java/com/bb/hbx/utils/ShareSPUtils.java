package com.bb.hbx.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public static void initShareSP(Context context)
    {
        mContext=context;
        sp = context.getSharedPreferences("usersinfo", Context.MODE_PRIVATE);
        edit = sp.edit();
    }
    public static void readShareSP(ViewGroup notLogin, ImageView userIcon, TextView hasLogin,Context context)
    {
        if (sp!=null)
        {
            Can.hasLogined=sp.getBoolean("hasLogined",false);
            if (!Can.hasLogined)//用户未登录执行的逻辑
            {
                String parentPath = Can.getDefaultUsersIconFile();
                File file = new File(parentPath);
                String usersIconPath = new File(file, Can.userIconDefault.substring(Can.userIconDefault.lastIndexOf("/") + 1)).getAbsolutePath();
                if (notLogin.getVisibility()== View.GONE)
                {
                    notLogin.setVisibility(View.VISIBLE);
                    hasLogin.setVisibility(View.GONE);
                }
                userIcon.setImageBitmap(BitmapFactory.decodeFile(usersIconPath));
            }
            else //用户已登录执行的逻辑
            {
                Can.userName=sp.getString("userName",null);
                Can.userPwd=sp.getString("userPwd",null);
                Can.userIcon= sp.getString("userIcon", null);
                if (hasLogin.getVisibility()==View.GONE)
                {
                    hasLogin.setVisibility(View.VISIBLE);
                    notLogin.setVisibility(View.GONE);
                }
                //从本地文件加载图片
                userIcon.setImageBitmap(BitmapFactory.decodeFile(Can.userIcon));
                hasLogin.setText(Can.userName);
            }
        }
        else
        {
            Toast.makeText(context,"sharePreference建立失败",Toast.LENGTH_SHORT).show();

        }
    }
    public static String writeShareSp(boolean loginFlag,String userName,String phone,String pwd)
    {
        String parentPath = Can.getDefaultUsersIconFile();
        File file = new File(parentPath);
        String usersIconPath = new File(file, Can.userIconDefault.substring(Can.userIconDefault.lastIndexOf("/") + 1)).getAbsolutePath();
        edit.putBoolean("hasLogined",loginFlag);
        edit.putString("userName",userName);
        edit.putString("userPhone",phone);
        edit.putString("userPwd",pwd);
        edit.putString("userIcon",usersIconPath);
        edit.commit();
        return usersIconPath;
    }
}
