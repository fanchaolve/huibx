package com.bb.hbx.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.AddContactActivity;
import com.bb.hbx.activitiy.CustomerManagerActivity;
import com.bb.hbx.activitiy.MyCustomActivity;
import com.bb.hbx.adapter.ContactAdapter;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.bean.ContactBean;
import com.bb.hbx.bean.GetInsured;
import com.bb.hbx.bean.InsuredInfolBean;
import com.bb.hbx.decoration.TitleItemDecoration;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.utils.GetPhoneContactsUtil;
import com.bb.hbx.widget.IndexBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bb.hbx.activitiy.MyCustomActivity.insuredInfolBean;

/**
 * Created by Administrator on 2017/2/8.
 */

public class CustomersManagerFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "zxt";
    @BindView(R.id.rv)
    RecyclerView mRv;

    @BindView(R.id.ll_grey)
    LinearLayout ll_grey;

    @BindView(R.id.ll_contact)
    LinearLayout ll_contact;

    @BindView(R.id.lv_contact)
    ListView lv_contact;

    private ContactAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<ContactBean> mDatas = new ArrayList<>();

    private TitleItemDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    @BindView(R.id.indexBar)
    IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    @BindView(R.id.fromContacts_tv)
    TextView fromContacts_tv;
    @BindView(R.id.fromManual_tv)
    TextView fromManual_tv;
    private Context mContext;

    int scrollY;
    int pageIndex = 1;
    int pageSize;
    int totalCount;
    List<GetInsured.InsuredListBean> insuredList;
    //String [] nameTotalData;
    public List<String> nameTotalList = new ArrayList<>();

    //int count=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public LinearLayout getLl_grey() {
        return ll_grey;
    }

    public LinearLayout getLl_contact() {
        return ll_contact;
    }

    public ListView getLv_contact() {
        return lv_contact;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_custommanager_layout;
    }

    @Override
    public void initView() {
        fromContacts_tv.setOnClickListener(this);
        fromManual_tv.setOnClickListener(this);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        mRv.setLayoutManager(mManager = new LinearLayoutManager(mContext));
        //initDatas();
        //initDatas(getResources().getStringArray(R.array.provinces));注了这
        mRv.setAdapter(mAdapter = new ContactAdapter(mContext, mDatas));
        //mRv.addItemDecoration(mDecoration = new TitleItemDecoration(mContext, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        //mRv.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        //mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        //mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar
        //改了这
        /*mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mDatas);//设置数据源*/

        //recycler实现分页加载
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Log.e("===dy===","======"+dy);
                scrollY = dy;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //得到当前显示的最后一个item的view
                    View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                    if (lastChildView != null) {
                        //得到lastChildView的bottom的坐标值
                        int lastChildBottom = lastChildView.getBottom();
                        //得到recyclerview的底部坐标减去底部padding值,也就是显示内容最底部的坐标
                        int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                        //通过lastChildView得到这个view当前的position值
                        int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);
                        //判断lastChildView的bottom值跟recyclerview的bottom,判断lastPosition是不是最后一个position
                        //如果条件都满足则说明真正滑动到了最底部
                    /*if (lastChildBottom==recyclerBottom)//列表到底了也不相等
                    {
                        Toast.makeText(mContext,lastChildBottom+"true"+recyclerBottom,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(mContext,lastChildBottom+"false"+recyclerBottom,Toast.LENGTH_SHORT).show();
                    }*/
                        if (scrollY > 0 &&/*lastChildBottom==recyclerBottom&&*/lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                            pageIndex++;
                            if (pageIndex > ((totalCount / 10) + 1)) {
                                showTip("已经到底啦!");
                                pageIndex--;
                            } else {
                                expandInsuredList();
                            }
                        }
                    }
                }
            }
        });
        mAdapter.setOnMyItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                if (mDatas != null && mDatas.size() > 0) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(position).getCity().equals(insuredList.get(i).getInsuredName())) {
                            position = i;
                            break;
                        }
                    }
                    Intent intent = new Intent(mContext, MyCustomActivity.class);
                    String birthday = insuredList.get(position).getBirthday();
                    String email = insuredList.get(position).getEmail();
                    String gender = "1".equals(insuredList.get(position).getGender()) ? "男" : "女";
                    String idNo = insuredList.get(position).getIdNo();
                    String idType = insuredList.get(position).getIdType();
                    String insurantDesc = insuredList.get(position).getInsurantDesc();
                    String insuredAbbr = insuredList.get(position).getInsuredAbbr();
                    String insuredAddress = insuredList.get(position).getInsuredAddress();
                    String insuredEname = insuredList.get(position).getInsuredEname();
                    String insuredId = insuredList.get(position).getInsuredId();
                    String insuredName = insuredList.get(position).getInsuredName();
                    String mobile = insuredList.get(position).getMobile();
                    String occupation = insuredList.get(position).getOccupation();
                    String relation = insuredList.get(position).getRelation();

                    String areaId = "";
                    String area = "";

                    InsuredInfolBean insuredInfolBean = new InsuredInfolBean(birthday, email, gender, idNo, idType, insurantDesc, insuredAbbr, insuredAddress,
                            insuredEname, insuredId, insuredName, mobile, occupation, relation, areaId, area);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("insuredInfolBean", insuredInfolBean);
                    intent.putExtra("insuredInfolBean", bundle);
                    CustomerManagerActivity.intentFromProDetail.putExtra(Constants.TRANSTATION, insuredInfolBean);
                    //CustomerManagerActivity.intentFromProDetail.putExtra("insuredInfolBean",bundle);
                    if (CustomerManagerActivity.typeFromProDetail == Constants.GETSURED) {
                        getActivity().setResult(Activity.RESULT_OK, CustomerManagerActivity.intentFromProDetail);
                        getActivity().finish();
                    } else {
                        startActivity(intent);
                    }

                }
            }
        });
    }

    private void expandInsuredList() {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getInsured(MyApplication.user.getUserId(), MyApplication.user.getMobile(), pageIndex + "", "10");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                GetInsured bean = (GetInsured) body.getOutput();
                if (bean != null) {
                    String pageSizeString = bean.getPageSize();
                    String totalCountString = bean.getTotalCount();
                    pageSize = Integer.parseInt(pageSizeString);
                    totalCount = Integer.parseInt(totalCountString);
                    List<GetInsured.InsuredListBean> insuredBufList = bean.getInsuredList();
                    int listSize = insuredBufList.size();
                    String[] nameData = new String[listSize];
                    for (int i = 0; i < listSize; i++) {
                        nameData[i] = insuredBufList.get(i).getInsuredName();
                        //nameTotalData[count-1]= insuredBufList.get(i).getInsuredName();
                        nameTotalList.add(insuredBufList.get(i).getInsuredName());
                        //count++;
                    }
                    if (listSize > 0) {
                        insuredList.addAll(insuredBufList);
                        //initDatas(nameTotalData);
                        initDataInOtherWay(nameTotalList);
                        mAdapter.notifyDataSetChanged();
                        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                                .setNeedRealIndex(true)//设置需要真实的索引
                                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                                .setmSourceDatas(mDatas);//设置数据源
                        mIndexBar.invalidate();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void initDataInOtherWay(List<String> list) {
        mDatas.clear();
        //注了这
        //mDatas = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ContactBean contactBean = new ContactBean();
            contactBean.setCity(list.get(i));//设置城市名称
            mDatas.add(contactBean);
        }
    }

    private void initDatas(String[] data) {
        mDatas.clear();
        //注了这
        //mDatas = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            ContactBean contactBean = new ContactBean();
            contactBean.setCity(data[i]);//设置城市名称
            mDatas.add(contactBean);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getInsuredList();
    }

    private void getInsuredList() {
        pageIndex = 1;
        //count=0;
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getInsured(MyApplication.user.getUserId(), MyApplication.user.getMobile(), pageIndex + "", "10");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Result_Api body = (Result_Api) response.body();
                GetInsured bean = (GetInsured) body.getOutput();
                if (bean != null) {
                    String pageSizeString = bean.getPageSize();
                    String totalCountString = bean.getTotalCount();
                    pageSize = Integer.parseInt(pageSizeString);
                    totalCount = Integer.parseInt(totalCountString);
                    insuredList = bean.getInsuredList();
                    int listSize = insuredList.size();
                    String[] nameData = new String[listSize];
                    //nameTotalData=new String[totalCount];
                    nameTotalList.clear();
                    for (int i = 0; i < listSize; i++) {
                        nameData[i] = insuredList.get(i).getInsuredName();
                        //nameTotalData[i]=insuredList.get(i).getInsuredName();
                        nameTotalList.add(insuredList.get(i).getInsuredName());
                        //count++;
                    }
                    if (listSize > 0) {
                        initDatas(nameData);
                        mAdapter.notifyDataSetChanged();
                        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                                .setNeedRealIndex(true)//设置需要真实的索引
                                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                                .setmSourceDatas(mDatas);//设置数据源
                        mIndexBar.invalidate();
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

        switch (v.getId()) {
            case R.id.fromContacts_tv:
                //showTip("通讯录导入");
                //Toast.makeText(mContext,"通讯录导入", Toast.LENGTH_SHORT).show();
                Uri uri = ContactsContract.Contacts.CONTENT_URI;
                Intent intentBuf = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intentBuf, 0);
                break;
            case R.id.fromManual_tv:
                //showTip("手动添加");
                //Toast.makeText(mContext,"手动添加",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(mContext, AddContactActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri = data.getData();
                String[] contacts = GetPhoneContactsUtil.getPhoneContacts(mContext, uri);
                Intent intent = new Intent(mContext, AddContactActivity.class);
                intent.putExtra("name", contacts[0]);
                intent.putExtra("phone", contacts[1]);
                startActivity(intent);
                //name_et.setText(contacts[0]);
                //phone_et.setText(contacts[1]);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
