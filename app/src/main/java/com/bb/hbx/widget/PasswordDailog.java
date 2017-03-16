package com.bb.hbx.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.BaseDialog;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.CheckIdentifyInForgerActivity;
import com.bb.hbx.base.v.ConfimpaymentContract;
import com.bb.hbx.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;




/**
 * Created by fancl.
 */

public class PasswordDailog extends BaseDialog implements
        View.OnClickListener {


    private Unbinder unbinder;

    private Context mContext;


    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.et_psd)
    EditText et_psd;


    @BindView(R.id.tv_forgetpsd)
    TextView tv_forgetpsd;

    @BindView(R.id.tv_verify)
    TextView tv_verify;

    private ConfimpaymentContract.View view;

    public String password = "";

    public String getPassword() {
        return password;
    }


    public interface GetPasswordListener {

        void getPassword(String password);
    }

    private GetPasswordListener listener;

    public void setListener(GetPasswordListener listener) {
        this.listener = listener;
    }

    /**
     * @param context 上下文
     */
    public PasswordDailog(Context context) {
        super(context, R.layout.passworddialog);
        mContext = context;


    }

    /**
     * @param context 上下文
     */
    public PasswordDailog(Context context, ConfimpaymentContract.View view) {
        super(context, R.layout.passworddialog);
        mContext = context;
        this.view = view;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        init();


    }

    private void init() {
        setAnimation(R.style.Animation_Bottom_Dialog);
        setGravity(Gravity.CENTER);
        iv_back.setOnClickListener(this);
        tv_forgetpsd.setOnClickListener(this);
        /*et_psd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!TextUtils.isEmpty(et_psd.getText())) {
                        password = et_psd.getText().toString().trim();
                        if (view != null) {
                            view.getverifyPayPwd(password);
                        }

                        if (listener != null) {
                            listener.getPassword(password);
                        }
                    }
                }
                return true;
            }
        });*/
        tv_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_psd.getText())) {
                    password = et_psd.getText().toString().trim();
                    if (view != null) {
                        view.getverifyPayPwd(password);
                    }

                    if (listener != null) {
                        listener.getPassword(password);
                    }
                }
            }
        });
    }

    @Override
    protected int dialogWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        return metric.widthPixels - 2 * mContext.getResources().getDimensionPixelOffset(R.dimen.x46);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.dismiss();
                break;
            case R.id.tv_forgetpsd:
                AppManager.getInstance().showActivity(CheckIdentifyInForgerActivity.class, null);
                break;

        }
    }


}
