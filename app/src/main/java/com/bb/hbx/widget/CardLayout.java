package com.bb.hbx.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.Plan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/1/20.
 */

public class CardLayout extends LinearLayout implements View.OnClickListener {


    @BindView(R.id.lin_card1)
    LinearLayout lin_card1;

    @BindView(R.id.lin_card2)
    LinearLayout lin_card2;

    @BindView(R.id.lin_card3)
    LinearLayout lin_card3;


    @BindView(R.id.lin_item1)
    LinearLayout lin_item1;

    @BindView(R.id.lin_item2)
    LinearLayout lin_item2;

    @BindView(R.id.lin_item3)
    LinearLayout lin_item3;

    @BindView(R.id.tv_detail1)
    TextView tv_detail1;

    @BindView(R.id.tv_detail2)
    TextView tv_detail2;

    @BindView(R.id.tv_detail3)
    TextView tv_detail3;


    @BindView(R.id.tv_price1)
    TextView tv_price1;

    @BindView(R.id.tv_price2)
    TextView tv_price2;

    @BindView(R.id.tv_price3)
    TextView tv_price3;

    @BindView(R.id.tv_other1)
    TextView tv_other1;

    @BindView(R.id.tv_other2)
    TextView tv_other2;

    @BindView(R.id.tv_other3)
    TextView tv_other3;

    @BindView(R.id.rel_1)
    RelativeLayout rel_1;

    @BindView(R.id.rel)
    RelativeLayout rel;


    private int count = 0;
    public static final int CARD0 = 0;
    public static final int CARD1 = 1;
    public static final int CARD2 = 2;
    public static final int CARD3 = 3;


    private String price1;

    private String price2;

    private String price3;

    private String detail1;

    private String detali2;

    private String detail3;

    private Context mContext;

    private CardListener listener;

    private List<Plan> planList;

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public void setListener(CardListener listener) {
        this.listener = listener;
    }

    public CardLayout(Context context) {
        this(context, null, 0);
    }

    public CardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_item, this, false);
        addView(view);
        ButterKnife.bind(this);

        lin_card1.setOnClickListener(this);
        lin_card2.setOnClickListener(this);
        lin_card3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        restCardView(lin_card1);
        restCardView(lin_card2);
        restCardView(lin_card3);
        restItemView(lin_item1);
        restItemView(lin_item2);
        restItemView(lin_item3);
        restTvView();
        switch (v.getId()) {
            case R.id.lin_card1:
                clickView(lin_card1);
                clickItemView(lin_item1);
                tv_price1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size24);
                tv_detail1.setTextAppearance(mContext, R.style.TextAppear_Theme_A4_Size12);
                tv_other1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size12);


                if (listener != null) {
                    listener.onclick(CARD1);
                }
                break;
            case R.id.lin_card2:
                clickView(lin_card2);
                clickItemView(lin_item2);
                tv_price2.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size24);
                tv_detail2.setTextAppearance(mContext, R.style.TextAppear_Theme_A4_Size12);
                tv_other2.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size12);

                if (listener != null) {
                    listener.onclick(CARD2);
                }
                break;
            case R.id.lin_card3:
                clickView(lin_card3);
                clickItemView(lin_item3);
                tv_price3.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size24);
                tv_detail3.setTextAppearance(mContext, R.style.TextAppear_Theme_A4_Size12);
                tv_other3.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size12);

                if (listener != null) {
                    listener.onclick(CARD3);
                }
                break;
        }
    }


    private void restCardView(LinearLayout lin_card) {
        int margin_top = (int) mContext.getResources().getDimension(R.dimen.y156);
        ViewGroup.LayoutParams layoutParams = lin_card.getLayoutParams();
        layoutParams.height = margin_top;
        lin_card.setLayoutParams(layoutParams);
        lin_card.setBackgroundResource(R.drawable.shape_z49);

    }

    private void restItemView(LinearLayout lin_item) {
        lin_item.setVisibility(View.GONE);

    }

    private void restTvView() {
        tv_price1.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size24);
        tv_detail1.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size12);
        tv_other1.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size12);

        tv_price2.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size24);
        tv_detail2.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size12);
        tv_other2.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size12);

        tv_price3.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size24);
        tv_detail3.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size12);
        tv_other3.setTextAppearance(mContext, R.style.TextAppear_Theme_Z48_Size12);
    }

    private void clickView(LinearLayout lin_card) {
        int margin_top = (int) mContext.getResources().getDimension(R.dimen.y178);
        ViewGroup.LayoutParams layoutParams = lin_card.getLayoutParams();
        layoutParams.height = margin_top;
        lin_card.setLayoutParams(layoutParams);
        lin_card.setBackgroundResource(R.color.white);

    }

    private void clickItemView(LinearLayout lin_item) {
        lin_item.setVisibility(View.VISIBLE);

    }


    private void makeView(int count) {

        switch (count) {
            case CARD0:
            case CARD1:
                rel_1.setVisibility(View.VISIBLE);
                rel.setVisibility(View.GONE);
                if (listener != null) {
                    listener.onclick(CARD1);
                }
                break;
            case CARD2:
                rel_1.setVisibility(View.GONE);
                rel.setVisibility(View.VISIBLE);
                lin_card3.setVisibility(View.GONE);

                clickView(lin_card1);
                clickItemView(lin_item1);
                tv_price1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size24);
                tv_detail1.setTextAppearance(mContext, R.style.TextAppear_Theme_A4_Size12);
                tv_other1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size12);



                if (listener != null) {
                    listener.onclick(CARD1);
                }

                break;
            case CARD3:
                rel_1.setVisibility(View.GONE);
                rel.setVisibility(View.VISIBLE);

                clickView(lin_card1);
                clickItemView(lin_item1);
                tv_price1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size24);
                tv_detail1.setTextAppearance(mContext, R.style.TextAppear_Theme_A4_Size12);
                tv_other1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size12);
                if (listener != null) {
                    listener.onclick(CARD1);
                }
                break;
            default:
                rel_1.setVisibility(View.GONE);
                rel.setVisibility(View.VISIBLE);

                clickView(lin_card1);
                clickItemView(lin_item1);
                tv_price1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size24);
                tv_detail1.setTextAppearance(mContext, R.style.TextAppear_Theme_A4_Size12);
                tv_other1.setTextAppearance(mContext, R.style.TextAppear_Theme_A1_Size12);
                if (listener != null) {
                    listener.onclick(CARD1);
                }

        }

        if(planList==null)
            return;
        if(planList.size()>=3){
            String priceString1 = planList.get(0).getPlanPrice();
            int priceInt1 = TextUtils.isEmpty(priceString1)?0:Integer.parseInt(priceString1);
            //tv_price1.setText(planList.get(0).getPlanPrice());
            tv_price1.setText((priceInt1/100)+"."+(priceInt1/10%10)+(priceInt1%10));
            tv_detail1.setText(planList.get(0).getPlanName());

            String priceString2 = planList.get(1).getPlanPrice();
            int priceInt2 = TextUtils.isEmpty(priceString2)?0:Integer.parseInt(priceString2);
            //tv_price2.setText(planList.get(1).getPlanPrice());
            tv_price2.setText((priceInt2/100)+"."+(priceInt2/10%10)+(priceInt2%10));
            tv_detail2.setText(planList.get(1).getPlanName());

            String priceString3 = planList.get(2).getPlanPrice();
            int priceInt3 = TextUtils.isEmpty(priceString3)?0:Integer.parseInt(priceString3);
            //tv_price3.setText(planList.get(2).getPlanPrice());
            tv_price3.setText((priceInt3/100)+"."+(priceInt3/10%10)+(priceInt3%10));
            tv_detail3.setText(planList.get(2).getPlanName());
        }else if(planList.size()==2){
            String priceString1 = planList.get(0).getPlanPrice();
            int priceInt1 = TextUtils.isEmpty(priceString1)?0:Integer.parseInt(priceString1);
            //tv_price1.setText(planList.get(0).getPlanPrice());
            tv_price1.setText((priceInt1/100)+"."+(priceInt1/10%10)+(priceInt1%10));
            tv_detail1.setText(planList.get(0).getPlanName());

            String priceString2 = planList.get(1).getPlanPrice();
            int priceInt2 = TextUtils.isEmpty(priceString2)?0:Integer.parseInt(priceString2);
            //tv_price2.setText(planList.get(1).getPlanPrice());
            tv_price2.setText((priceInt2/100)+"."+(priceInt2/10%10)+(priceInt2%10));
            tv_detail2.setText(planList.get(1).getPlanName());
        }else if(planList.size()==1){
            String priceString1 = planList.get(0).getPlanPrice();
            int priceInt1 = TextUtils.isEmpty(priceString1)?0:Integer.parseInt(priceString1);
            tv_price1.setText((priceInt1/100)+"."+(priceInt1/10%10)+(priceInt1%10));
            tv_detail1.setText(planList.get(0).getPlanName());
        }


    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        makeView(count);

    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
        tv_price1.setText(price1);
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
        tv_price2.setText(price2);
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
        tv_price3.setText(price3);
    }

    public String getDetail1() {
        return detail1;
    }

    public void setDetail1(String detail1) {
        this.detail1 = detail1;
        tv_detail1.setText(detail1);
    }

    public String getDetali2() {
        return detali2;
    }

    public void setDetali2(String detali2) {
        this.detali2 = detali2;
        tv_detail2.setText(detali2);
    }

    public String getDetail3() {
        return detail3;
    }

    public void setDetail3(String detail3) {
        this.detail3 = detail3;
        tv_detail3.setText(detail3);
    }

    public interface CardListener {
        void onclick(int index);
    }


}
