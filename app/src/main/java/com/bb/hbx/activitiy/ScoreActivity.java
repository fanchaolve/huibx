package com.bb.hbx.activitiy;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyScoreAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.Account;
import com.bb.hbx.bean.GetAccountDetailBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 积分页面
 */
public class ScoreActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.scrollView)
    PullToRefreshScrollView scrollView;
    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.menu_iv)
    ImageView menu_iv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.score_tv)
    TextView score_tv;
    @BindView(R.id.state_tv)
    TextView state_tv;
    @BindView(R.id.info_tv)
    TextView info_tv;
    GridLayoutManager manager;
    //ArrayList<MyScoreBean> totalList=new ArrayList<>();
    List<GetAccountDetailBean.AccountDetailListBean> totalList=new ArrayList<>();
    MyScoreAdapter adapter;

    int pageIndex=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_score;
    }

    @Override
    public void initView() {
        /*Intent intent = getIntent();
        int accountScoreInt = intent.getIntExtra("accountScoreInt", 0);
        score_tv.setText((accountScoreInt/100)+"."+(accountScoreInt/10%10)+(accountScoreInt%10));*/
        showScore();

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        menu_iv.setOnClickListener(this);
        state_tv.setOnClickListener(this);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex=1;
                showScoreDetail(pageIndex);
                showScore();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                showScoreDetail(pageIndex);
            }
        });
    }

    @Override
    public void initdata() {
        manager = new GridLayoutManager(this, 1){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new MyScoreAdapter(totalList, this);
        recyclerView.setAdapter(adapter);
        showScoreDetail(pageIndex);

    }

    private void showScoreDetail(final int pageIndex) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getAccountDetail(MyApplication.user.getUserId(),"10",pageIndex+"","0");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body!=null)
                {
                    GetAccountDetailBean detailBean = (GetAccountDetailBean) body.getOutput();
                    if (detailBean!=null)
                    {
                        if (pageIndex==1)
                        {
                            totalList.clear();
                        }
                        totalList.addAll(detailBean.getAccountDetailList());
                        adapter.notifyDataSetChanged();

                    }
                }
                if (scrollView.isRefreshing())
                {
                    scrollView.onRefreshComplete();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    //显示头部积分
    private void showScore() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call=service.getAccount(MyApplication.user.getUserId(),"10");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                if (body!=null)
                {
                    Account account = (Account) body.getOutput();
                    if (account!=null)
                    {
                        String accountScore = account.getAccountScore();
                        int accountScoreInt=0;
                        if (!TextUtils.isEmpty(accountScore))
                        {
                            accountScoreInt = Integer.parseInt(accountScore);
                        }
                        score_tv.setText(TextUtils.isEmpty(accountScore)?"0.00":(accountScoreInt/100)+"."+(accountScoreInt/10%10)+(accountScoreInt%10));
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.menu_iv:
                Toast.makeText(this,"菜单",Toast.LENGTH_SHORT).show();
                break;
            case R.id.state_tv:
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call=service.checkIn(MyApplication.user.getUserId(),"10");
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            default:
                break;
        }
    }

}
