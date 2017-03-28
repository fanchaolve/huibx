package com.bb.hbx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/20.
 */

public class ItemLayout2 extends LinearLayout {


    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.iv_last)
    ImageView iv_last;


    @BindView(R.id.tv_comm)
    TextView tv_comm;


    private String left_name = "";//左边的标题


    private String text = "";


    private int right_icon;

    private int right_butIcon;

    private int textAppearance;


    private Context mContext;

    //-------------
    private boolean isOpen = true;

    OnUpListener listener;


    private boolean isEnable = true;


    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public void setListener(OnUpListener listener) {
        this.listener = listener;
    }

    public ItemLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ItemLayout2, defStyleAttr, 0);

        left_name = a.getString(R.styleable.ItemLayout2_left_name2);
        text = a.getString(R.styleable.ItemLayout2_text2);
        right_icon = a.getResourceId(R.styleable.ItemLayout2_right_icon2, -1);
        right_butIcon = a.getResourceId(R.styleable.ItemLayout2_right_butIcon2, -1);

        textAppearance = a.getResourceId(R.styleable.ItemLayout2_textAppearance, R.style.TextAppear_Theme_A3_Size13);
        a.recycle();
        init();

    }

    private void init() {
        this.setOrientation(VERTICAL);
        View view = LayoutInflater.from(mContext).inflate(R.layout.line_item2, this, false);
        addView(view);
        ButterKnife.bind(this);
        tv_comm.setTextAppearance(mContext, textAppearance);


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
        iv_last.setImageResource(right_icon);

    }


    public void setLeft_name(String left_name) {
        this.left_name = left_name;
        tv_type.setText(left_name);
    }


    public void setText(String text) {
        this.text = text;
        tv_comm.setText(text);
    }


    public String getTextValue() {
        return tv_comm.getText().toString().trim();
    }

    public void setdownImageResource() {
        iv_last.setImageResource(right_icon);
    }

    public void setMyDownImageResource() {
        iv_last.setImageResource(right_butIcon);
    }

    public void setButtonGone() {
        iv_last.setVisibility(View.INVISIBLE);
        isEnable = false;
    }

    public interface OnUpListener {

        void onClick();
    }


}
