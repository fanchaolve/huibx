package com.bb.hbx.activitiy;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.Account;
import com.bb.hbx.bean.GetBankCardList;
import com.bb.hbx.bean.GetTotalIncomeBean;
import com.bb.hbx.utils.TimeUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 点击 我的--我的资产 显示的 我的资产 页面*/
public class MyAssertActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.detail_tv)
    TextView detail_tv;
    @BindView(R.id.cash_layout)
    RelativeLayout cash_layout;
    @BindView(R.id.income_layout)
    RelativeLayout income_layout;
    @BindView(R.id.settlement_layout)
    RelativeLayout settlement_layout;
    @BindView(R.id.allIncome_layout)
    RelativeLayout allIncome_layout;
    @BindView(R.id.income_tv)
    TextView income_tv;
    @BindView(R.id.cash_tv)
    TextView cash_tv;
    @BindView(R.id.settlement_tv)
    TextView settlement_tv;
    @BindView(R.id.allIncome_tv)
    TextView allIncome_tv;
    /*@BindView(R.id.income_itv)
    IncomesTableView income_itv;
    //设置横坐标显示的月份
    String [] xValues=new String[7];
    //设置纵坐标显示的金额
    int [] yValues=new int[7];*/
    @BindView(R.id.chart_lc)
    LineChart chart_lc;

    ArrayList<GetTotalIncomeBean.TotalIncomeListBean> totalList = new ArrayList<>();
    String currentTime = "";
    String startTime = "";
    private int acctBalanceInt = 0;

    int pageIndex = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_assert;
    }

    @Override
    public void initView() {
        /*Intent intent = getIntent();
        int acctBalanceInt = intent.getIntExtra("acctBalanceInt", 0);
        int acctMonthSumInt = intent.getIntExtra("acctMonthSumInt", 0);
        int acctSettSumInt = intent.getIntExtra("acctSettSumInt", 0);
        int acctSumInt = intent.getIntExtra("acctSumInt", 0);
        income_tv.setText((acctBalanceInt/100)+"."+(acctBalanceInt/10%10)+(acctBalanceInt%10));
        cash_tv.setText((acctMonthSumInt/100)+"."+(acctMonthSumInt/10%10)+(acctMonthSumInt%10));
        settlement_tv.setText((acctSettSumInt/100)+"."+(acctSettSumInt/10%10)+(acctSettSumInt%10));
        allIncome_tv.setText((acctSumInt/100)+"."+(acctSumInt/10%10)+(acctSumInt%10));*/

        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getAccount(MyApplication.user.getUserId(), "20");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body != null) {
                    Account account = (Account) body.getOutput();
                    if (account != null) {
                        String acctBalance = account.getAcctBalance();//可提现
                        String acctSum = account.getAcctSum();//余额????收入,累计收入
                        String acctMonthSum = account.getAcctMonthSum();//本月收入
                        String acctSettSum = account.getAcctSettSum();//结算中
                        acctBalanceInt = Integer.parseInt(acctBalance);
                        int acctSumInt = Integer.parseInt(acctSum);
                        int acctMonthSumInt = Integer.parseInt(acctMonthSum);
                        int acctSettSumInt = Integer.parseInt(acctSettSum);
                        income_tv.setText((acctBalanceInt / 100) + "." + (acctBalanceInt / 10 % 10) + (acctBalanceInt % 10));
                        cash_tv.setText((acctMonthSumInt / 100) + "." + (acctMonthSumInt / 10 % 10) + (acctMonthSumInt % 10));
                        settlement_tv.setText((acctSettSumInt / 100) + "." + (acctSettSumInt / 10 % 10) + (acctSettSumInt % 10));
                        allIncome_tv.setText((acctSumInt / 100) + "." + (acctSumInt / 10 % 10) + (acctSumInt % 10));
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
        /*for (int i = 0; i < xValues.length; i++) {
            xValues[i]= i+"";
        }
        income_itv.setXValues(xValues);
        for (int i = 0; i < yValues.length; i++) {
            yValues[i]= 1+i*100;
        }
        income_itv.setYValues(yValues);*/

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        detail_tv.setOnClickListener(this);
        cash_layout.setOnClickListener(this);
        income_layout.setOnClickListener(this);
        settlement_layout.setOnClickListener(this);
        allIncome_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        showTableInfo();
    }

    private void showTableInfo() {
        //chart_lc= (CandleStickChart) findViewById(R.id.chart_lc);
        /*chart_lc.setDescription("最近六个月收入走势");
        chart_lc.setDescriptionTextSize(10f);//像素为单位,6f最小
        chart_lc.setDescriptionColor(getResources().getColor(R.color.A3));*/
        chart_lc.setDescription("");
        chart_lc.setNoDataTextDescription("设置当chart为空时显示描述的文字");
        chart_lc.setDrawGridBackground(true);//如果启用,chart绘图区后面的背景矩形将绘制
        chart_lc.setGridBackgroundColor(getResources().getColor(R.color.white));//设置网格背景应与绘制的颜色,若上一个属性为false,,则此属性无效
        //chart_lc.setDrawBorders(true);
        //chart_lc.setDrawBorders(false);//启用或禁用绘制图表边框,即chart周围的线
        //chart_lc.setBorderColor(Color.RED);
        //chart_lc.setBorderWidth(2);//单位dp
        //chart_lc.setMaxVisibleValueCount(5);//设置最大可见绘制的chart count数量,,,,,需配合方法使用

        YAxis axisLeft = chart_lc.getAxisLeft();
        axisLeft.enableGridDashedLine(2, 2, 0);//设置为虚线模式,线长,线间宽,起始点
        axisLeft.setTextColor(getResources().getColor(R.color.A4));
        axisLeft.setAxisLineColor(getResources().getColor(R.color.white));
        YAxis axisRight = chart_lc.getAxisRight();
        axisRight.setEnabled(false);//设置右侧y轴是否显示
       /* axisRight.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat mFormat = new DecimalFormat("#0.00%");
                return mFormat.format(value);
            }
        });*/
        //-------------axisRight.setValueFormatter(new MyYAxisValueFormatter());//设置数据格式器

        XAxis xAxis = chart_lc.getXAxis();
        //xAxis.enableGridDashedLine(4,2,-90);
        //xAxis.setAxisLineColor(getResources().getColor(R.color.white));
        xAxis.setGridColor(getResources().getColor(R.color.white));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.A4));
        xAxis.setValueFormatter(new XAxisValueFormatter() {//设置数据格式器
            @Override
            public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
                return original + "月";
            }
        });

        //---------------------------------------------------------------add
        currentTime = TimeUtils.getCurrentTime();//2017-02-27
        String[] timeBuf = currentTime.split("-");
        String singleYearCurrent = timeBuf[0];//2017
        String singleMonthCurrent = timeBuf[1];//02
        String singleDayCurrent = timeBuf[2];//24
        int singleYearCurrentInt = Integer.parseInt(singleYearCurrent);//2017
        int singleMonthCurrentInt = Integer.parseInt(singleMonthCurrent);//2
        int singleDayCurrentInt = Integer.parseInt(singleDayCurrent);//24
        int singleYearStartInt = singleYearCurrentInt;
        int singleMonthStartInt = 0;
        for (int i = 1; i <= 6; i++) {
            if (singleMonthCurrentInt == 1) {
                singleMonthStartInt = 12;
                singleMonthCurrentInt = 12;
                singleYearStartInt = singleYearCurrentInt - 1;
            } else {
                singleMonthStartInt = singleMonthCurrentInt--;
            }
        }
        startTime = singleYearStartInt + "-" + (singleMonthStartInt / 10) + (singleMonthStartInt % 10) + "-" + "01";
        showTotalIncomeList(pageIndex, startTime, currentTime, singleMonthCurrentInt);

///*
//        //.......................以下为添加数据到图表
//        ArrayList<Entry> valsComp1=new ArrayList<>();
//        ArrayList<Entry> valsComp2=new ArrayList<>();
//        Entry c1e1 = new Entry(100, 0);
//        Entry c1e2 = new Entry(200, 1);
//        Entry c1e3 = new Entry(400, 2);
//        Entry c1e4 = new Entry(700, 3);
//        valsComp1.add(c1e1);
//        valsComp1.add(c1e2);
//        valsComp1.add(c1e3);
//        valsComp1.add(c1e4);
//        Entry c2e1 = new Entry(200, 0);
//        Entry c2e2 = new Entry(400, 1);
//        Entry c2e3 = new Entry(700, 2);
//        Entry c2e4 = new Entry(900, 3);
//        valsComp2.add(c2e1);
//        valsComp2.add(c2e2);
//        valsComp2.add(c2e3);
//        valsComp2.add(c2e4);
//
//        //LineDataSet setComp1 = new LineDataSet(valsComp1, "我的资产");
//        LineDataSet setComp1 = new LineDataSet(valsComp1, "");
//        setComp1.setHighLightColor(Color.RED);//设置手指滑动到某个点时,横竖两条线的颜色
//        setComp1.setColor(getResources().getColor(R.color.A1));//设置本条折线的颜色
//        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);//使dataset对应指定轴,进行绘制
//        setComp1.setFillFormatter(new DefaultFillFormatter());//效果未知
//        //LineDataSet setComp2 = new LineDataSet(valsComp2, "平台平均值");
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "");
//        setComp2.setColors(new int[]{R.color.A2},this);//设置本条折线的颜色
//        //setComp2.setHighlightEnabled(true);
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
//        //List<ILineDataSet> dataSets = new ArrayList<>();
//        List<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(setComp1);
//        dataSets.add(setComp2);
//
//        ArrayList<String> xVals=new ArrayList<>();
//        xVals.add("1.Q");
//        xVals.add("2.Q");
//        xVals.add("3.Q");
//        xVals.add("4.Q");
//
//        LineData data = new LineData(xVals,dataSets);
//        chart_lc.setData(data);
//        //chart_lc.invalidate();
//        //设置动画
//        //chart_lc.animateX(4000);
//        chart_lc.animateY(4000);
//
//        //修改图例
//        Legend legend = chart_lc.getLegend();
//        legend.setEnabled(false);
//        legend.setTextColor(getResources().getColor(R.color.A3));
//        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
//
//        //chart_lc.setVisibleXRangeMinimum(1);//超过一定值,不能进一步沿x轴放大视图
//        //chart_lc.setVisibleXRangeMaximum(1);//x轴超过一的值将不可见,这是可滑动chart
//        //chart_lc.setVisibleYRangeMaximum(1, YAxis.AxisDependency.LEFT);
//
//        //chart_lc.setExtraOffsets(0,50,0,0);//设置额外偏移量
//        //chart_lc.moveViewToX(2);//不明显
//        //chart_lc.moveViewToY(800, YAxis.AxisDependency.LEFT);//效果不明显*/
    }

    private void showTotalIncomeList(final int pageIndex, String startTime, String currentTime, final int curentMonth) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getTotalIncome(MyApplication.user.getUserId(), "20", startTime, currentTime, pageIndex + "", "10");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body != null) {
                    GetTotalIncomeBean incomeBean = (GetTotalIncomeBean) body.getOutput();
                    if (incomeBean != null) {
                        if (pageIndex == 1) {
                            totalList.clear();
                        }
                        totalList.addAll(incomeBean.getTotalIncomeList());
                        addDateToTable(totalList, curentMonth);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void addDateToTable(ArrayList<GetTotalIncomeBean.TotalIncomeListBean> dataList, int curentMonth) {
        //.......................以下为添加数据到图表
        ArrayList<Entry> valsComp1 = new ArrayList<>();
        ArrayList<Entry> valsComp2 = new ArrayList<>();
        int indexMax = dataList.size() - 1;
        for (int i = 0; i < 6; i++) {
            //6-i
            int monthTotalAmountInt = 0;
            //5-i>indexMax
            if ((5 - i) <= indexMax) {
                String monthTotalAmount = dataList.get(5 - i).getMonthTotalAmount();
                monthTotalAmountInt = Integer.parseInt(monthTotalAmount);
            } else {
                monthTotalAmountInt = 0;
            }
            String income = (monthTotalAmountInt / 100) + "." + (monthTotalAmountInt / 10 % 10) + (monthTotalAmountInt % 10);
            Entry entry = new Entry(Float.parseFloat(income), i);
            //Entry entry = new Entry(monthTotalAmountInt, i);
            valsComp1.add(entry);
        }
        /*Entry c1e1 = new Entry(100, 0);
        Entry c1e2 = new Entry(200, 1);
        Entry c1e3 = new Entry(400, 2);
        Entry c1e4 = new Entry(700, 3);
        valsComp1.add(c1e1);
        valsComp1.add(c1e2);
        valsComp1.add(c1e3);
        valsComp1.add(c1e4);*/
        Entry c2e1 = new Entry(200, 0);
        Entry c2e2 = new Entry(400, 1);
        Entry c2e3 = new Entry(700, 2);
        Entry c2e4 = new Entry(900, 3);
        valsComp2.add(c2e1);
        valsComp2.add(c2e2);
        valsComp2.add(c2e3);
        valsComp2.add(c2e4);

        //LineDataSet setComp1 = new LineDataSet(valsComp1, "我的资产");
        LineDataSet setComp1 = new LineDataSet(valsComp1, "");
        setComp1.setHighLightColor(Color.RED);//设置手指滑动到某个点时,横竖两条线的颜色
        setComp1.setColor(getResources().getColor(R.color.A1));//设置本条折线的颜色
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);//使dataset对应指定轴,进行绘制
        setComp1.setFillFormatter(new DefaultFillFormatter());//效果未知
        //LineDataSet setComp2 = new LineDataSet(valsComp2, "平台平均值");
        LineDataSet setComp2 = new LineDataSet(valsComp2, "");
        setComp2.setColors(new int[]{R.color.A2}, this);//设置本条折线的颜色
        //setComp2.setHighlightEnabled(true);
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        //List<ILineDataSet> dataSets = new ArrayList<>();
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);


        ArrayList<String> xVals = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < 6; i++) {

                //String tradeTime=""+curentMonth;
                int month = curentMonth;
                if ((5 - i) <= indexMax) {
                    String tradeTime = dataList.get(5 - i).getTradeTime();
                    String[] dataBuf = tradeTime.split("[年月]");
                    month = Integer.parseInt(dataBuf[1]);
                    if (curentMonth == 1) {
                        curentMonth = 12;
                    } else {
                        curentMonth--;
                    }
                } else {
                    if (curentMonth == 1) {
                        curentMonth = 12;
                    } else {
                        curentMonth--;
                    }
                    month = curentMonth;
                }
                xVals.add(month + "");
            }
        } else {
            for (int i = 0; i < 6; i++) {
                xVals.add((12 - curentMonth) + "");
                curentMonth--;
            }
        }
       /* xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");*/

        LineData data = new LineData(xVals, dataSets);
        chart_lc.setData(data);
        //chart_lc.invalidate();
        //设置动画
        //chart_lc.animateX(4000);
        chart_lc.animateY(4000);

        //修改图例
        Legend legend = chart_lc.getLegend();
        legend.setEnabled(false);
        legend.setTextColor(getResources().getColor(R.color.A3));
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);

        //chart_lc.setVisibleXRangeMinimum(1);//超过一定值,不能进一步沿x轴放大视图
        //chart_lc.setVisibleXRangeMaximum(1);//x轴超过一的值将不可见,这是可滑动chart
        //chart_lc.setVisibleYRangeMaximum(1, YAxis.AxisDependency.LEFT);

        //chart_lc.setExtraOffsets(0,50,0,0);//设置额外偏移量
        //chart_lc.moveViewToX(2);//不明显
        //chart_lc.moveViewToY(800, YAxis.AxisDependency.LEFT);//效果不明显
    }


    @Override
    public void onClick(View view) {
        final Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.detail_tv:
                //Toast.makeText(this,"明细",Toast.LENGTH_SHORT).show();
                intent.setClass(this, MyAssertDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.cash_layout:
                //intent.setClass(this,CashActivity.class);
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call = service.getBankCardList(MyApplication.user.getUserId());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api body = (Result_Api) response.body();
                        if (body != null) {
                            GetBankCardList cardBean = (GetBankCardList) body.getOutput();
                            if (cardBean != null && !TextUtils.isEmpty(cardBean.getLastDigits())) {
                                String setPayPwd = "0";
                                setPayPwd = MyApplication.user.getPaymentPwd();
                                switch (setPayPwd) {
                                    case "0":           //未设置支付密码
                                        startActivity(new Intent(mContext, SetPayPwdActivity.class));
                                        finish();
                                        break;
                                    case "1":
                                        intent.setClass(MyAssertActivity.this, WithdrawActivity.class);
                                        intent.putExtra("acctBalanceInt", acctBalanceInt);
                                        startActivity(intent);
                                        finish();
                                        break;
                                }
//                                if (!TextUtils.isEmpty(cardBean.getLastDigits()))
//                                {
//                                    intent.setClass(MyAssertActivity.this,WithdrawActivity.class);
////                                    String bankName = cardBean.getBankName();
////                                    String lastDigits = cardBean.getLastDigits();
////                                    String cardType = cardBean.getCardType();
//                                    intent.putExtra("acctBalanceInt", acctBalanceInt);
////                                    intent.putExtra("bankName",bankName);
////                                    intent.putExtra("lastDigits",lastDigits);
////                                    intent.putExtra("cardType",cardType);
//                                    startActivity(intent);
//                                }
//                                else
//                                {
//                                    intent.setClass(MyAssertActivity.this,AddBankCardActivity.class);
//                                    startActivity(intent);
//                                }
                            } else {
                                intent.setClass(MyAssertActivity.this, AddBankCardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            case R.id.settlement_layout:
                intent.setClass(this, SettlementActivity.class);
                startActivity(intent);
                break;
            case R.id.allIncome_layout:
                intent.setClass(this, AllIncomeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    class MyYAxisValueFormatter implements YAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyYAxisValueFormatter() {
            mFormat = new DecimalFormat("#0.0元");
        }

        @Override
        public String getFormattedValue(float value, YAxis yAxis) {
            return mFormat.format(value);
        }
    }
}
