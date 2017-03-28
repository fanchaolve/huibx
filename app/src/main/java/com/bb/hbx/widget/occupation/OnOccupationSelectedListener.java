package com.bb.hbx.widget.occupation;

import com.bb.hbx.bean.occupation.OccupationBean;

public interface OnOccupationSelectedListener {
//    void onAddressSelected(Province province, City city, County county, Street street);
void onOccupationSelected(OccupationBean.TypeListBean province,
                          OccupationBean.TypeListBean.SubTypeBeanX city,
                          OccupationBean.TypeListBean.SubTypeBeanX.SubTypeBean county);
}
