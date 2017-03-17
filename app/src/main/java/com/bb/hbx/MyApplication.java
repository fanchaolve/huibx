package com.bb.hbx;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.bb.hbx.bean.User;
import com.bb.hbx.bean.VersionInfo;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Administrator on 2016/12/6.
 */

public class MyApplication extends Application {


    //public LocationService locationService;
    private static MyApplication INSTANCE = null;
    private static Context context;

    public static String latitude;// 纬度

    public static String longitude;//经度

    public static String city;//城市


    public static String areaVersion = "";

    public static VersionInfo versionInfo;


    public static String DUID = "";// 设备唯一码
    public static int widthPixels = 321;// 屏幕宽度
    public static int heightPixels = 481;//屏幕高度

    public static User user;// 用户信息

    public static final Context getAppContext() {
        return context;
    }

    ;

    public static MyApplication getInstance() {
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        INSTANCE = this;
        //初始化realm数据库
        Realm.init(this);
        RealmConfiguration config = new  RealmConfiguration.Builder()
                .name("systemmsg.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        //       locationService = new LocationService(context);
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(context);

    }
}
