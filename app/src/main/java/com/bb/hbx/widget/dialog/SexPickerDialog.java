package com.bb.hbx.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.BaseDialog;
import com.bb.hbx.R;
import com.bb.hbx.adapter.AbstractWheelTextAdapter;
import com.bb.hbx.views.OnWheelChangedListener;
import com.bb.hbx.views.OnWheelScrollListener;
import com.bb.hbx.views.WheelView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/15.
 * 性别选择的dialog
 */

public class SexPickerDialog extends BaseDialog implements
        View.OnClickListener,DialogInterface.OnDismissListener {

    private Context mContext;
    private ViewGroup vSexDialogPicker;
    private WheelView wvSex;
    private View vDialog;
    private View vDialogChild;
    private TextView btnSure;
    private TextView btnCancel;
    private TextView tvTittle;
    private ArrayList<String> sexList = new ArrayList<>();
    private CalendarTextAdapter mSexAdapter;
    private OnSexPickListener onSexPickListener;

    private int maxTextSize = 24;
    private int minTextSize = 18;

    private boolean issetdata = false;

    public static final int DIALOG_MODE_CENTER = 0;
    public static final int DIALOG_MODE_BOTTOM = 1;

    /**
     * @param context    上下文
     *
     */
    public SexPickerDialog(Context context) {
        super(context, R.layout.dialog_picker_center);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void setOnSexPickListener(OnSexPickListener onSexPickListener) {
        this.onSexPickListener = onSexPickListener;
    }

    /**
     * 初始化
     */
    public void init() {
        vSexDialogPicker = (ViewGroup) findViewById(R.id.ly_dialog_picker);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        // 此处相当于布局文件中的Android:layout_gravity属性
        lp.gravity = Gravity.CENTER_VERTICAL;

        wvSex = new WheelView(mContext);
        wvSex.setLayoutParams(lp);
        vSexDialogPicker.addView(wvSex);

        vDialog = findViewById(R.id.ly_dialog);
        vDialogChild = findViewById(R.id.ly_dialog_child);
        btnSure = (TextView) findViewById(R.id.btn_dialog_sure);
        btnCancel = (TextView) findViewById(R.id.btn_dialog_cancel);
        tvTittle = (TextView) findViewById(R.id.tv_dialog_title);

        tvTittle.setText("性别选择");
        vDialog.setOnClickListener(this);
        vDialogChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        this.setOnDismissListener(this);
        if (null != btnCancel) {
            btnCancel.setOnClickListener(this);
        }

        if (!issetdata) {
            initData();
        }

        mSexAdapter = new CalendarTextAdapter(mContext,sexList,0,maxTextSize,minTextSize);
        wvSex.setVisibleItems(2);
        wvSex.setViewAdapter(mSexAdapter);
        wvSex.setCurrentItem(0);
        wvSex.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mSexAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText,mSexAdapter);

            }
        });

        wvSex.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mSexAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, mSexAdapter);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        issetdata = true;
        sexList.add("男");
        sexList.add("女");
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (onSexPickListener != null) {
            onSexPickListener.ondissmiss();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSure) {
            if (onSexPickListener != null) {
                onSexPickListener.onClick((String) mSexAdapter.getItemText(
                        wvSex.getCurrentItem()));
            }
        } else if (v == btnCancel) {

        } else if (v == vDialogChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();
    }

    /**
     * 设置dialog弹出框模式
     *
     * @param dialogMode DIALOG_MODE_CENTER
     *                   从屏幕中间弹出
     *                   DIALOG_MODE_BOTTOM
     *                   从屏幕底部弹出
     */
    public void setDialogMode(int dialogMode) {
        if (dialogMode == DIALOG_MODE_BOTTOM) {
            resetContent(R.layout.dialog_picker_bottom);
            setAnimation(R.style.AnimationBottomDialog);
            setGravity(Gravity.BOTTOM);
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText,
                                CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

    public class CalendarTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list,
                                      int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
                    maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    public interface OnSexPickListener {
        public void onClick(String sex);
        void ondissmiss();
    }
}
