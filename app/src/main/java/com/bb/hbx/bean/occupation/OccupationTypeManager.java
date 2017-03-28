package com.bb.hbx.bean.occupation;

import android.content.Context;

import com.bb.hbx.utils.OccupationTypeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 * 对职业类型txt文件读取查询
 */

public class OccupationTypeManager {

//    private AddressBean addressBean = new AddressBean();
    private Context context;
    private String fileName = "occupationtype.txt";
    private String addressStr = "";
    private OccupationBean occupationBean;
    private List<OccupationBean.TypeListBean> provinceList = new ArrayList<>();
    private List<OccupationBean.TypeListBean.SubTypeBeanX> cityList = new ArrayList<>();
    private List<OccupationBean.TypeListBean.SubTypeBeanX.SubTypeBean> townList = new ArrayList<>();

    public OccupationTypeManager(Context context) {
        this.context = context;
    }

    /**
     * 获取省份的集合
     * @return
     */
    public List<OccupationBean.TypeListBean> getProvinceList() {
        occupationBean = (OccupationBean) OccupationTypeUtils.readObject(context,"occupationBean");
        provinceList = occupationBean.getTypeList();
        return provinceList;
    }

    /**
     * 获取市的集合
     * @param provinceId
     * @return
     */
    public List<OccupationBean.TypeListBean.SubTypeBeanX> getCityList(String provinceId) {
        for (OccupationBean.TypeListBean areasListBean : provinceList) {
            if (provinceId.equals(areasListBean.getTypeId()))
                cityList = areasListBean.getSubType();
        }
        return cityList;
    }

    /**
     * 获取城镇/乡的集合
     * @param cityId
     * @return
     */
    public List<OccupationBean.TypeListBean.SubTypeBeanX.SubTypeBean> getTownList(String cityId) {
        for (OccupationBean.TypeListBean.SubTypeBeanX childrenBeanX : cityList) {
            if (cityId.equals(childrenBeanX.getTypeId()))
                townList = childrenBeanX.getSubType();
        }
        return townList;
    }
}










