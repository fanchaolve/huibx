package com.bb.hbx.activitiy;


import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.base.m.SearchHistoryModel;
import com.bb.hbx.base.p.SearchHistoryPresenter;
import com.bb.hbx.base.v.SearchHistoryContract;
import com.bb.hbx.bean.HotSearchBean;
import com.bb.hbx.bean.LishiSearchBean;
import com.bb.hbx.bean.SearchTitleBean;
import com.bb.hbx.bean.SearchTitleBean2;
import com.bb.hbx.provide.HotSearchProvide;
import com.bb.hbx.provide.LishiSearchProvide;
import com.bb.hbx.provide.SearchTitleProvide;
import com.bb.hbx.provide.SearchTitleProvide2;
import com.bb.hbx.widget.LoginTelEdit;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/12/26.
 */

public class SearchActivity extends BaseActivity<SearchHistoryPresenter, SearchHistoryModel> implements View.OnClickListener
        , SearchHistoryContract.View {

//
    private final String TAG = SearchActivity.class.getSimpleName();

    @BindView(R.id.tv_back)
    TextView tv_back;

    @BindView(R.id.rl_view)
    RecyclerView rl_view;

    @BindView(R.id.le_search)
    LoginTelEdit le_search;

    private MultiTypeAdapter adapter;


    private SearchTitleProvide2 searchTitleProvide2;
    private LishiSearchProvide lishiSearchProvide;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Item item = mPresenter.getList().get(position);

                if (item instanceof SearchTitleBean) {
                    return 4;
                } else if (item instanceof HotSearchBean) {
                    return 1;
                }
                return 4;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        rl_view.setLayoutManager(layoutManager);


        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();

        lishiSearchProvide = new LishiSearchProvide(adapter);
        searchTitleProvide2 = new SearchTitleProvide2();
        adapter.register(SearchTitleBean.class, new SearchTitleProvide());
        adapter.register(HotSearchBean.class, new HotSearchProvide());
        adapter.register(SearchTitleBean2.class, searchTitleProvide2);
        adapter.register(LishiSearchBean.class, lishiSearchProvide);
        rl_view.setAdapter(adapter);
        rl_view.addItemDecoration(new SpaceItemDecoration());


    }

    @Override
    public void initListener() {

        tv_back.setOnClickListener(this);
        le_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!TextUtils.isEmpty(le_search.getText())) {
                        mPresenter.addHistoryBean(new LishiSearchBean(le_search.getText().toString().trim()));
                        showTip("添加成功....");


                    }
                }
                return true;
            }
        });

        lishiSearchProvide.setListener(new LishiSearchProvide.LishiSearchListener() {
            @Override
            public void selectItem(LishiSearchBean bean) {

            }

            @Override
            public void deleteItem(LishiSearchBean bean) {
                mPresenter.deleteBean(bean);
            }
        });


        searchTitleProvide2.setListener(new SearchTitleProvide2.DeleteAllSearchListener() {
            @Override
            public void deleteAll() {
                mPresenter.deleteAll();
            }
        });
    }

    @Override
    public void initdata() {
        adapter.setItems(mPresenter.getList());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }

    }

    @Override
    public void getHistoryList(List<LishiSearchBean> lists) {
        adapter.addItems(lists);

    }

    @Override
    public void notfiy() {
        adapter.notifyDataSetChanged();
    }


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


            int pos = parent.getChildAdapterPosition(view);
            if (pos == -1)
                return;
            if (mPresenter.getList().get(pos) instanceof HotSearchBean) {
                outRect.top = 0;
                outRect.bottom = 0;

                outRect.right = 0;


                if (pos > 0 && (pos - 1) / 4 == 0) {
                    outRect.top = getResources().getDimensionPixelOffset(R.dimen.y36);
                }

                if (pos > 0 && (pos - 1) / 4 == 1) {
                    outRect.top = getResources().getDimensionPixelOffset(R.dimen.y26);
                    outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.y36);
                }

                if (pos > 0 && (pos - 1) % 4 == 0) {
                    outRect.left = getResources().getDimensionPixelOffset(R.dimen.x36);

                } else {
                    outRect.left = getResources().getDimensionPixelOffset(R.dimen.x26);
                }

                if (pos > 0 && (pos - 1) % 4 == 3) {
                    outRect.right = getResources().getDimensionPixelOffset(R.dimen.x36);
                } else {

                }

                Log.i(TAG, "left:" + outRect.left);
                Log.i(TAG, "right:" + outRect.right);

            }


        }


    }
}
