package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.ProductDetailModle;
import com.bb.hbx.base.p.ProductDetailPresenter;
import com.bb.hbx.base.v.ProductDetailContract;
import com.bb.hbx.bean.Benefit;
import com.bb.hbx.bean.Entry;
import com.bb.hbx.bean.InsuredInfolBean;
import com.bb.hbx.bean.Plan;
import com.bb.hbx.bean.ProductParamDetail;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.utils.Utils;
import com.bb.hbx.widget.CardLayout;
import com.bb.hbx.widget.ClickAble;
import com.bb.hbx.widget.ItemLayout;
import com.bb.hbx.widget.ItemLayout2;
import com.bb.hbx.widget.PickerDialogOneWheel;
import com.bb.hbx.widget.ShareDailog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static com.bb.hbx.R.id.toolbar;
import static com.bb.hbx.utils.Constants.beinsurer1_listvalue;
import static com.bb.hbx.utils.Constants.idTypes;


/**
 * Created by fancl.
 * 调整投保方案
 */

public class UpdateInsurancePlanActivity extends BaseActivity implements
        View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.update_insuranceplan;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        toolbar.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }
}
