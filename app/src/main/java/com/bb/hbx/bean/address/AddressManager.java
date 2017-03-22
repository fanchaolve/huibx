package com.bb.hbx.bean.address;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.bean.address.AddressBean.AreasListBean;
import com.bb.hbx.bean.address.AddressBean.AreasListBean.ChildrenBeanX;
import com.bb.hbx.bean.address.AddressBean.AreasListBean.ChildrenBeanX.ChildrenBean;
import com.bb.hbx.utils.AddressUtils;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/21.
 * 对地址txt文件读取查询
 */

public class AddressManager {

//    private AddressBean addressBean = new AddressBean();
    private Context context;
    private String fileName = "address.txt";
    private String addressStr = "";
    private AddressBean addressBean;
    private List<AreasListBean> provinceList = new ArrayList<>();
    private List<ChildrenBeanX> cityList = new ArrayList<>();
    private List<ChildrenBean> townList = new ArrayList<>();

    public AddressManager(Context context) {
        this.context = context;
    }

    /**
     * 获取省份的集合
     * @return
     */
    public List<AreasListBean> getProvinceList() {
        addressBean = (AddressBean) AddressUtils.readObject(context,"addressBean");
        provinceList = addressBean.getAreasList();
        return provinceList;
    }

    /**
     * 获取市的集合
     * @param provinceId
     * @return
     */
    public List<ChildrenBeanX> getCityList(int provinceId) {
        for (AreasListBean areasListBean : provinceList) {
            if (areasListBean.getAreaId() == provinceId)
                cityList = areasListBean.getChildren();
        }
        return cityList;
    }

    /**
     * 获取城镇/乡的集合
     * @param cityId
     * @return
     */
    public List<ChildrenBean> getTownList(int cityId) {
        for (ChildrenBeanX childrenBeanX : cityList) {
            if (childrenBeanX.getAreaId() == cityId)
                townList = childrenBeanX.getChildren();
        }
        return townList;
    }
}










