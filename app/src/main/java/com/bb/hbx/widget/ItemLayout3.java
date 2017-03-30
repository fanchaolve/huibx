package com.bb.hbx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/20.
 */

public class ItemLayout3 extends LinearLayout {


    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.iv_last)
    ImageView iv_last;


    @BindView(R.id.tv_comm)
    TextView tv_comm;

    @BindView(R.id.cb_ceter)
    CheckBox cb_ceter;

    @BindView(R.id.lin_line)
    LinearLayout lin_line;


    private String left_name = "";//左边的标题


    private String text = "";

    private String center_text3 = "";


    private int right_icon;

    private int right_butIcon;

    private int textAppearance;


    private Context mContext;

    //-------------
    private boolean isOpen = true;

    OnUpListener listener;


    private boolean isEnable = true;

    private boolean isLine;

    private boolean isCenter_visible = true;


    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public void setListener(OnUpListener listener) {
        this.listener = listener;
    }


    public ItemLayout3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemLayout3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ItemLayout3, defStyleAttr, 0);

        left_name = a.getString(R.styleable.ItemLayout3_left_name3);
        text = a.getString(R.styleable.ItemLayout3_text3);
        center_text3 = a.getString(R.styleable.ItemLayout3_center_text3);
        right_icon = a.getResourceId(R.styleable.ItemLayout3_right_icon3, -1);
        right_butIcon = a.getResourceId(R.styleable.ItemLayout3_right_butIcon3, -1);
        isLine = a.getBoolean(R.styleable.ItemLayout3_isline, true);
        isCenter_visible = a.getBoolean(R.styleable.ItemLayout3_center_visible, true);
        textAppearance = a.getResourceId(R.styleable.ItemLayout3_textAppearance3, R.style.TextAppear_Theme_A3_Size14);
        a.recycle();
        init();

    }

    public boolean isCenter_visible() {
        return isCenter_visible;
    }

    public void setCenter_visible(boolean center_visible) {
        isCenter_visible = center_visible;
        setCkVisibly();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        View view = LayoutInflater.from(mContext).inflate(R.layout.line_item3, this, false);
        addView(view);
        ButterKnife.bind(this);
        tv_comm.setTextAppearance(mContext, textAppearance);
        setCkVisibly();

        iv_last.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnable) {
                    iv_last.setImageResource(right_butIcon);
                    if (listener != null) {
                        listener.onClick();
                    }
                }


            }
        });

        tv_comm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnable) {
                    iv_last.setImageResource(right_butIcon);
                    if (listener != null) {
                        listener.onClick();
                    }
                }


            }
        });

        initData();
    }

    private void initData() {
        tv_type.setText(left_name);
        tv_comm.setText(text);
        cb_ceter.setText(center_text3);
        iv_last.setImageResource(right_icon);
        if (isLine)
            lin_line.setVisibility(View.VISIBLE);
        else
            lin_line.setVisibility(View.GONE);


    }

    public boolean isCb_ceter() {
        return cb_ceter.isChecked();
    }

    public void setLeft_name(String left_name) {
        this.left_name = left_name;
        tv_type.setText(left_name);
    }

    //设置是否为选中状态
    public void setCb_ceterChecked(boolean flag)
    {
        if (flag)
        {
            cb_ceter.setChecked(true);
        }
        else
        {
            cb_ceter.setChecked(false);
        }
    }

    public void setText(String text) {
        this.text = text;
        tv_comm.setText(text);
    }

    public void setCkVisibly() {
        if (isCenter_visible)
            cb_ceter.setVisibility(View.VISIBLE);
        else
            cb_ceter.setVisibility(View.GONE);
    }


    public void setLine(boolean line) {
        isLine = line;
        initData();
    }

    public String getTextValue() {
        return tv_comm.getText().toString().trim();
    }

    public void setdownImageResource() {
        iv_last.setImageResource(right_icon);
    }

    public void setButtonGone() {
        iv_last.setVisibility(View.INVISIBLE);
        isEnable = false;
    }

    public interface OnUpListener {

        void onClick();
    }


}
