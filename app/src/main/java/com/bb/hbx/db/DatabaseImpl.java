package com.bb.hbx.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bb.hbx.MyApplication;
import com.bb.hbx.bean.AdBean;
import com.bb.hbx.bean.HomePageInfo;
import com.bb.hbx.bean.LishiSearchBean;
import com.bb.hbx.bean.ProductItem;
import com.bb.hbx.bean.Special;
import com.bb.hbx.bean.User;
import com.bb.hbx.bean.XhbMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class DatabaseImpl extends SQLiteOpenHelper implements Database {


    private static DatabaseImpl helper;

    private static final String DB_NAME = "db_huibx.db";

    private static final String TABLE_SEARCH_HISTORY = "db_search_history";

    private static final String TABLE_ADBEAN = "db_AdBean";
    private static final String TABLE_PRODUCTITEM = "db_ProductItem";
    private static final String TABLE_XHBMSG = "db_XhbMsg";
    private static final String TABLE_SPECIAL = "db_Special";
    private static final String TABLE_PRODUCT_LISTBEAN = "db_productlistbean";

    private static final String TABLE_HOMEPAGEINFO = "db_HomePageInfo";


    private static final int version = 1;

    public static SQLiteDatabase db;

    private DatabaseImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static synchronized DatabaseImpl getInstance() {
        if (helper == null) {
            helper = new DatabaseImpl(MyApplication.getAppContext(), DB_NAME, null, version);//数据库名称为create_db。
        }
        return helper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_SEARCH_HISTORY +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR" + ");");

        //广告
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_ADBEAN +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "adLink VARCHAR,adURL VARCHAR" + ");");

        //消息
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_XHBMSG +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "msgContent VARCHAR,msgId VARCHAR," +
                "msgLink VARCHAR,msgTime VARCHAR," +
                "msgTitle VARCHAR,msgType VARCHAR," +
                "sts INTEGER" + ");");


        //分类
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_PRODUCTITEM +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "insurerTypeId VARCHAR,insurerTypeLogo VARCHAR,insurerTypeName VARCHAR," +
                "insurerTypeUrl VARCHAR" + ");");

        //首页产品
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_PRODUCT_LISTBEAN +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ageDesc VARCHAR,classId VARCHAR,commisionType VARCHAR," +
                "commisionValue1 VARCHAR,guarantee VARCHAR,insurerId VARCHAR," +
                "insurerLogo VARCHAR,insurerName VARCHAR,minPremium VARCHAR," +
                "monthAmount VARCHAR,productId VARCHAR,productIntro VARCHAR," +
                "productLogo VARCHAR,productName VARCHAR,productPrice VARCHAR," +
                "productProp VARCHAR,productTagUrls VARCHAR,specialPrice VARCHAR" +
                "suitable VARCHAR,totalAmount VARCHAR,perferWords VARCHAR" + ");");

        //专题列表
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_SPECIAL +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "endTime VARCHAR," +
                "productCount VARCHAR,specialContent VARCHAR," +
                "specialId VARCHAR,specialIntro VARCHAR,specialLogo VARCHAR," +
                "specialName VARCHAR,specialType INTEGER,specialUrl VARCHAR,startTime VARCHAR" + ");");

        //首页信息
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_HOMEPAGEINFO +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "loop VARCHAR,showTime VARCHAR," +
                "iataCodeVersion VARCHAR,areaVersion VARCHAR" +
                ");");


        //name表示nickName
        String sql = "create table if not exists userstb(_id integer primary key autoincrement,currentUser text,hasLogined text,authority text,userId text,sessionId text," +
                "isBClient text,name text,gender text,email text,phone text,pwd text,paymentPwd text,usericon text,userQrcode text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public void addHistory(LishiSearchBean bean) {

        db = getWritableDatabase();
        if (db == null) return;
        ContentValues values = new ContentValues();
        values.put("name", bean.getName());
        String[] whereArgs = {bean.getName()};
        int row_count = db.update(TABLE_SEARCH_HISTORY, values, "name=?", whereArgs);
        if (row_count == 0) {
            db.insert(TABLE_SEARCH_HISTORY, null, values);
        }

        db.close();
    }

    @Override
    public void deleteHistory(LishiSearchBean bean) {
        db = getWritableDatabase();
        if (db == null) return;
        String[] whereArgs = {bean.getName()};
        db.delete(TABLE_SEARCH_HISTORY, "name=?", whereArgs);
        db.close();
    }

    @Override
    public void deleteAllHis() {
        db = getWritableDatabase();
        if (db == null) return;
        db.delete(TABLE_SEARCH_HISTORY, null, null);
        db.close();
    }

    @Override
    public List<LishiSearchBean> getListHis() {
        List<LishiSearchBean> infos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) return infos;
        Cursor cursor = db.query(TABLE_SEARCH_HISTORY, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                LishiSearchBean bean = new LishiSearchBean(cursor.getString(cursor.getColumnIndex("name")));
                infos.add(bean);

            }
        }
        cursor.close();
        db.close();
        return infos;
    }

    @Override
    public void addHomepageInfo(HomePageInfo info) {
        db = getWritableDatabase();
        if (db == null) return;
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.putAll(new HomePageInfoDatabaseBuilder().deconstruct(info));

        String[] whereArgs = {"1"};
        int row_count = db.update(TABLE_HOMEPAGEINFO, values, "id=?", whereArgs);

        if (row_count == 0) {
            db.insert(TABLE_HOMEPAGEINFO, null, values);
        }

        db.execSQL("DELETE FROM " + TABLE_ADBEAN);
        for (AdBean adBean : info.getAds()) {
            ContentValues value = new ContentValues();
            value.putAll(new AdbeanDatabaseBuilder().deconstruct(adBean));
            db.insert(TABLE_ADBEAN, null, value);
        }

        db.execSQL("DELETE FROM " + TABLE_PRODUCTITEM);
        for (ProductItem item : info.getProductType()) {
            ContentValues value = new ContentValues();
            value.putAll(new ProductItemDatabaseBuilder().deconstruct(item));
            db.insert(TABLE_PRODUCTITEM, null, value);
        }

        db.execSQL("DELETE FROM " + TABLE_XHBMSG);
        for (XhbMsg msg : info.getXhbMsgList()) {
            ContentValues value = new ContentValues();
            value.putAll(new XhbMsgDatabaseBuilder().deconstruct(msg));
            db.insert(TABLE_XHBMSG, null, value);
        }

        db.execSQL("DELETE FROM " + TABLE_SPECIAL);
        for (Special special : info.getSpecialList()) {
            ContentValues value = new ContentValues();
            value.putAll(new SpecialDatabaseBuilder().deconstruct(special));
            db.insert(TABLE_SPECIAL, null, value);
        }


        db.close();
    }

    @Override
    public boolean updateUser(String authority) {
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) return false;

        ContentValues values = new ContentValues();
        values.put("authority", "1");
        int row_count = db.update("userstb", values, null, null);
        if (row_count != 0) {
            return true;
        } else
            return false;
    }

    @Override
    public User getUser() {
        User user = null;
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) return user;


        Cursor query = db.query("userstb", null, null, null, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                user = new User();
                String authority = query.getString(query.getColumnIndex("authority"));
                user.setAuthority(authority);
                
            }
        }

        query.close();
        db.close();
        return user;
    }
}
