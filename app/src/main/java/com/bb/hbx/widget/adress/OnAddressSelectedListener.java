package com.bb.hbx.widget.adress;

import com.bb.hbx.bean.address.AddressBean.AreasListBean;
import com.bb.hbx.bean.address.AddressBean.AreasListBean.ChildrenBeanX;
import com.bb.hbx.bean.address.AddressBean.AreasListBean.ChildrenBeanX.ChildrenBean;

public interface OnAddressSelectedListener {
//    void onAddressSelected(Province province, City city, County county, Street street);
void onAddressSelected(AreasListBean province, ChildrenBeanX city, ChildrenBean county);
}
