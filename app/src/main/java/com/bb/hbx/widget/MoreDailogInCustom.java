package com.bb.hbx.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bb.hbx.BaseDialog;
import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.EditInsuredInfoActivity;
import com.bb.hbx.activitiy.MyCustomActivity;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.utils.AppManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by fancl.
 */

public class MoreDailogInCustom extends BaseDialog implements
        View.OnClickListener{


    private LinearLayout lin_heka;

    private LinearLayout lin_bianji;

    private LinearLayout lin_shanchu;

    private LinearLayout lin_cancl;

    private Context mContext;
    /**
     * @param context    上下文
     *
     */
    public MoreDailogInCustom(Context context) {
        super(context, R.layout.more_incustom);
        mContext=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


    }

    private void init() {
        setAnimation(R.style.AnimationBottomDialog);
        setGravity(Gravity.BOTTOM);
        lin_heka= (LinearLayout) findViewById(R.id.lin_heka);
        lin_bianji= (LinearLayout) findViewById(R.id.lin_bianji);
        lin_shanchu= (LinearLayout) findViewById(R.id.lin_shanchu);
        lin_cancl= (LinearLayout) findViewById(R.id.lin_cancl);
        lin_cancl.setOnClickListener(this);
        lin_heka.setOnClickListener(this);
        lin_bianji.setOnClickListener(this);
        lin_shanchu.setOnClickListener(this);
    }

    @Override
    protected int dialogWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        return metric.widthPixels;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_heka:
                Toast.makeText(mContext,"发送贺卡",Toast.LENGTH_LONG).show();
                break;
            case R.id.lin_bianji:
                mContext.startActivity(new Intent(mContext, EditInsuredInfoActivity.class));
                break;
            case R.id.lin_shanchu:
                //Toast.makeText(mContext,"删除客户",Toast.LENGTH_LONG).show();
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call=service.delInsured(MyApplication.user.getUserId(), MyCustomActivity.insuredInfolBean.getInsuredId());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Toast.makeText(mContext,"删除客户成功",Toast.LENGTH_LONG).show();
                        AppManager.getInstance().finishParticularActivity(MyCustomActivity.class);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(mContext,"删除客户失败",Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.lin_cancl:
                this.dismiss();
                break;
        }
        dismiss();
    }
}
