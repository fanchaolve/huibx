package com.bb.hbx.activitiy;

import android.app.Dialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyAssertDetailAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.GetAccountDetailBean;
import com.bb.hbx.bean.GetAccountDetailBean.AccountDetailListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 账户资金详情页面
 */
public class MyAssertDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.topbar_layout)
    RelativeLayout topbar_layout;

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.more_layout)
    RelativeLayout more_layout;

    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.title_tv)
    TextView title_tv;

    private List<AccountDetailListBean> list = new ArrayList<>();
    private List<AccountDetailListBean> outList = new ArrayList<>();//支出
    private List<AccountDetailListBean> incomeList = new ArrayList<>();//收入

    //tradeType交易类型，"0":全部,"10":充值,"20":冻结,"21":解冻,"30":消费,"31":收入,"40":退款,"50":提现
    private String tradeType = "0";

    private GridLayoutManager manager;
    private MyAssertDetailAdapter adapter;
    int pageIndex = 1;
    int index = 0;              //0表示全部,1表示支出,2表示收入
    ArrayList<ImageView> imageList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_assert_detail;
    }

    @Override
    public void initView() {
        manager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyAssertDetailAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        more_layout.setOnClickListener(this);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex = 1;
                showAssertDetail(pageIndex,getTradeType(),getList());
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                showAssertDetail(pageIndex,getTradeType(),getList());
            }
        });
    }

    @Override
    public void initdata() {
        tradeType = "0";
        pageIndex = 1;
        index = 0;
        showAssertDetail(pageIndex,getTradeType(),getList());
    }

    /**
     * 请求数据
     * @param pageIndex
     * @param tradeType
     */
    private void showAssertDetail(final int pageIndex, final String tradeType, final List<AccountDetailListBean> list) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getAccountDetail(MyApplication.user.getUserId(), "20", pageIndex + "", tradeType);
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
//                Log.i("mytag","--------------------" + tradeType + "----------------" + pageIndex);
                if (api.getOutput() != null && api.getOutput() instanceof GetAccountDetailBean) {
                    GetAccountDetailBean accountDetailBean = (GetAccountDetailBean) api.getOutput();
                    List<AccountDetailListBean> mList = accountDetailBean.getAccountDetailList();
                    if (mList != null && !mList.isEmpty()) {
                        if (pageIndex == 1) {
                            list.clear();
                        }
                        list.addAll(mList);
                        adapter.notifyDataSetChanged();
                    }
                }
                if (scrollView.isRefreshing()) {
                    scrollView.onRefreshComplete();
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.more_layout:
                //showTip("下拉");
                final Dialog dialog = new Dialog(this, R.style.myDialogTheme);
                View view = View.inflate(this, R.layout.dialog_myassert_layout, null);
                RelativeLayout back_layout = (RelativeLayout) view.findViewById(R.id.back_layout);
                RelativeLayout more_layout = (RelativeLayout) view.findViewById(R.id.more_layout);
                RelativeLayout all_layout = (RelativeLayout) view.findViewById(R.id.all_layout);
                RelativeLayout out_layout = (RelativeLayout) view.findViewById(R.id.out_layout);
                RelativeLayout income_layout = (RelativeLayout) view.findViewById(R.id.income_layout);
                final TextView dialogTitle_tv = (TextView) view.findViewById(R.id.title_tv);
                final ImageView stateAll_iv = (ImageView) view.findViewById(R.id.stateAll_iv);
                final ImageView stateOut_iv = (ImageView) view.findViewById(R.id.stateOut_iv);
                final ImageView stateIncome_iv = (ImageView) view.findViewById(R.id.stateIncome_iv);

                dialog.setContentView(view);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.TOP);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = (int) (MyApplication.widthPixels); //设置宽度
                window.setAttributes(lp);
                window.setWindowAnimations(R.style.AnimationBottomDialog);//添加动画
                dialog.show();
                imageList.clear();
                imageList.add(stateAll_iv);
                imageList.add(stateOut_iv);
                imageList.add(stateIncome_iv);

                for (int i = 0; i < imageList.size(); i++) {
                    if (i == index) {
                        imageList.get(i).setImageResource(R.drawable.check);
                    } else {
                        imageList.get(i).setImageBitmap(null);
                    }
                }

                if (index == 0) {
                    dialogTitle_tv.setText("全部明细");
                } else if (index == 1) {
                    dialogTitle_tv.setText("支出明细");
                } else {
                    dialogTitle_tv.setText("收入明细");
                }

                more_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                back_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                all_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        title_tv.setText("全部明细");
                        dialogTitle_tv.setText("全部明细");
                        stateAll_iv.setImageResource(R.drawable.check);
                        stateOut_iv.setImageBitmap(null);
                        stateIncome_iv.setImageBitmap(null);

//                        adapter.setList(list);
//                        adapter.notifyDataSetChanged();
                        index = 0;
                        showAssertDetail(pageIndex,getTradeType(),getList());
                        dialog.dismiss();
                    }
                });
                out_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        title_tv.setText("支出明细");
                        dialogTitle_tv.setText("支出明细");
                        stateOut_iv.setImageResource(R.drawable.check);
                        stateAll_iv.setImageBitmap(null);
                        stateIncome_iv.setImageBitmap(null);

//                        outList.clear();
//                        for (int i = 0; i < list.size(); i++) {
//                            int tradeType = list.get(i).getTradeType();
//                            if (tradeType == 20 || tradeType == 30 || tradeType == 40 || tradeType == 50) {
//                                outList.add(list.get(i));
//                            }
//                        }
//                        adapter.setList(outList);
//                        adapter.notifyDataSetChanged();
                        index = 1;
                        showAssertDetail(pageIndex,getTradeType(),getList());
                        dialog.dismiss();
                    }
                });
                income_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        title_tv.setText("收入明细");
                        dialogTitle_tv.setText("收入明细");
                        stateIncome_iv.setImageResource(R.drawable.check);
                        stateOut_iv.setImageBitmap(null);
                        stateAll_iv.setImageBitmap(null);

                        //dialog.dismiss();
//                        incomeList.clear();
//                        for (int i = 0; i < list.size(); i++) {
//                            int tradeType = list.get(i).getTradeType();
//                            if (tradeType == 10 || tradeType == 21 || tradeType == 31) {
//                                incomeList.add(list.get(i));
//                            }
//                        }
//                        adapter.setList(incomeList);
//                        adapter.notifyDataSetChanged();
                        index = 2;
                        showAssertDetail(pageIndex,getTradeType(),getList());
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 获取页面的 tradeType
     * @return
     */
    private String getTradeType() {
        String type = "0";
        switch (index) {
            case 0:
                type = "0";
                break;
            case 1:
                type = "30";
                break;
            case 2:
                type = "31";
                break;
        }
        return type;
    }

    private List<AccountDetailListBean> getList() {
        List<AccountDetailListBean> mList = new ArrayList<>();
        switch (index) {
            case 0:
                mList = list;
                break;
            case 1:
                mList = outList;
                break;
            case 2:
                mList = incomeList;
                break;
        }
        return mList;
    }
}
