package com.bb.hbx.widget.occupation;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.bb.hbx.R;
import com.bb.hbx.utils.Dev;


/**
 * Created by smartTop on 2016/10/19.
 */

public class BottomDialog extends Dialog {
    private OccupationSelector selector;

    public BottomDialog(Context context) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public BottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        selector = new OccupationSelector(context);
        setContentView(selector.getView());

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = Dev.dp2px(context, 256);
        window.setAttributes(params);

        window.setGravity(Gravity.BOTTOM);
    }

    public void setOnOccupationSelectedListener(OnOccupationSelectedListener listener) {
        this.selector.setOnOccupationSelectedListener(listener);
    }

//    public void setOnAddressSelectedListenerInContact(OnAddressSelectedListenerForAddContacts listener) {
//        this.selector.setOnAddressSelectedListenerInContact(listener);
//    }

    public static BottomDialog show(Context context) {
        return show(context, null);
    }

    public static BottomDialog show(Context context, OnOccupationSelectedListener listener) {
        BottomDialog dialog = new BottomDialog(context, R.style.bottom_dialog);
        dialog.selector.setOnOccupationSelectedListener(listener);
        dialog.show();

        return dialog;
    }
    public void setDialogDismisListener(OccupationSelector.OnDialogCloseListener listener){
        this.selector.setOnDialogCloseListener(listener);
    }
    /**
     *设置字体选中的颜色
     */
    public void setTextSelectedColor(int selectedColor){
        this.selector.setTextSelectedColor(selectedColor);
    }
    /**
     *设置字体没有选中的颜色
     */
    public void setTextUnSelectedColor(int unSelectedColor){
        this.selector.setTextUnSelectedColor(unSelectedColor);
    }
    /**
     * 设置字体的大小
     */
    public void setTextSize(float dp){
       this.selector.setTextSize(dp);
    }
    /**
     * 设置字体的背景
     */
    public void setBackgroundColor(int colorId){
       this.selector.setBackgroundColor(colorId);
    }
    /**
     * 设置指示器的背景
     */
    public void setIndicatorBackgroundColor(int colorId){
        this.selector.setIndicatorBackgroundColor(colorId);
    }
    /**
     * 设置指示器的背景
     */
    public void setIndicatorBackgroundColor(String color){
        this.selector.setIndicatorBackgroundColor(color);
    }
}
