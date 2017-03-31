package com.bb.hbx.widget.occupation;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.db.DBOpenHelper;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.utils.GlideUtil;
import com.bb.hbx.utils.MyUsersSqlite;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyErweimaDialog extends Dialog {

    private TextView tv_name;
    private ImageView iv_erweima;
    private String name;
    private String imgUrl;

    private Context context;
    public MyErweimaDialog(Context context, String name, String imgUrl) {
        super(context);
        this.context = context;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public MyErweimaDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.popupwindow);
        this.tv_name = (TextView) findViewById(R.id.tv_name);
        this.iv_erweima = (ImageView) findViewById(R.id.iv_erweima);
        this.tv_name.setText(name);

        GlideUtil.getInstance().loadImage(context,this.iv_erweima,imgUrl,false);

    }
}
