package com.bb.hbx.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.widget.ItemLayout2;
import com.bb.hbx.widget.ItemLayout3;
import com.bb.hbx.widget.PickerDialogOneWheel;

import java.util.Arrays;

import butterknife.BindView;


/**
 * Created by fancl.
 * 调整投保方案
 */

public class UpdateInsurancePlanActivity extends BaseActivity implements
        View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.il_jqx)
    ItemLayout3 il_jqx;

    public static final String[] idTypes = {"投保", "不投保"};

    private PickerDialogOneWheel.OnTextListener textListener = new PickerDialogOneWheel.OnTextListener() {
        @Override
        public void onClick(View v, String value, int index) {
            if (v instanceof ItemLayout3) {
                if ((int) (v.getTag()) == 12)
                ((ItemLayout3) v).setText(value);
            }

        }

        @Override
        public void dissmiss(View v) {
            if (v instanceof ItemLayout2) {
                ((ItemLayout2) v).setdownImageResource();
            } else if (v instanceof ItemLayout3) {
                ((ItemLayout3) v).setdownImageResource();
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.update_insuranceplan;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //il_jqx.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        il_jqx.setTag(12);
        il_jqx.setListener(new ItemLayout3.OnUpListener() {
            @Override
            public void onClick() {
                if (idTypes != null && idTypes.length > 1) {
                    PickerDialogOneWheel wheel_data = new PickerDialogOneWheel(mContext, Arrays.asList(idTypes), il_jqx);
                    wheel_data.setListener(textListener);
                    wheel_data.setDialogMode(PickerDialogOneWheel.DIALOG_MODE_BOTTOM);
                    wheel_data.show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.il_jqx:
                break;
            default:
                break;
        }*/
    }
}
