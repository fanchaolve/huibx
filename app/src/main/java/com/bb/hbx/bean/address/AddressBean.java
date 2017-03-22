package com.bb.hbx.bean.address;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class AddressBean implements Serializable {

    private List<AreasListBean> areasList;

    public List<AreasListBean> getAreasList() {
        return areasList;
    }

    public void setAreasList(List<AreasListBean> areasList) {
        this.areasList = areasList;
    }

    public static class AreasListBean implements Serializable {
        /**
         * areaId : 110000
         * areaName : 北京市
         * children : [{"areaId":110100,"areaName":"北京市","children":[{"areaId":110101,"areaName":"东城区","children":[]},{"areaId":110102,"areaName":"西城区","children":[]},{"areaId":110105,"areaName":"朝阳区","children":[]},{"areaId":110106,"areaName":"丰台区","children":[]},{"areaId":110107,"areaName":"石景山区","children":[]},{"areaId":110108,"areaName":"海淀区","children":[]},{"areaId":110109,"areaName":"门头沟区","children":[]},{"areaId":110111,"areaName":"房山区","children":[]},{"areaId":110112,"areaName":"通州区","children":[]},{"areaId":110113,"areaName":"顺义区","children":[]},{"areaId":110114,"areaName":"昌平区","children":[]},{"areaId":110115,"areaName":"大兴区","children":[]},{"areaId":110116,"areaName":"怀柔区","children":[]},{"areaId":110117,"areaName":"平谷区","children":[]},{"areaId":110228,"areaName":"密云县","children":[]},{"areaId":110229,"areaName":"延庆县","children":[]}]}]
         */

        private int areaId;
        private String areaName;
        private List<ChildrenBeanX> children;

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ChildrenBeanX implements Serializable {
            /**
             * areaId : 110100
             * areaName : 北京市
             * children : [{"areaId":110101,"areaName":"东城区","children":[]},{"areaId":110102,"areaName":"西城区","children":[]},{"areaId":110105,"areaName":"朝阳区","children":[]},{"areaId":110106,"areaName":"丰台区","children":[]},{"areaId":110107,"areaName":"石景山区","children":[]},{"areaId":110108,"areaName":"海淀区","children":[]},{"areaId":110109,"areaName":"门头沟区","children":[]},{"areaId":110111,"areaName":"房山区","children":[]},{"areaId":110112,"areaName":"通州区","children":[]},{"areaId":110113,"areaName":"顺义区","children":[]},{"areaId":110114,"areaName":"昌平区","children":[]},{"areaId":110115,"areaName":"大兴区","children":[]},{"areaId":110116,"areaName":"怀柔区","children":[]},{"areaId":110117,"areaName":"平谷区","children":[]},{"areaId":110228,"areaName":"密云县","children":[]},{"areaId":110229,"areaName":"延庆县","children":[]}]
             */

            private int areaId;
            private String areaName;
            private List<ChildrenBean> children;

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean implements Serializable {
                /**
                 * areaId : 110101
                 * areaName : 东城区
                 * children : []
                 */

                private int areaId;
                private String areaName;
                private List<?> children;

                public int getAreaId() {
                    return areaId;
                }

                public void setAreaId(int areaId) {
                    this.areaId = areaId;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }
            }
        }
    }
}
