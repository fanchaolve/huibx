package com.bb.hbx.bean.occupation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class OccupationBean implements Serializable {


        private List<TypeListBean> typeList;

        public List<TypeListBean> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<TypeListBean> typeList) {
            this.typeList = typeList;
        }

        public static class TypeListBean implements Serializable{
            /**
             * insurerCode :
             * insurerId : 5
             * subType : [{"insurerCode":"","insurerId":5,"subType":[{"insurerCode":"A01","insurerId":5,"subType":[],"typeId":"1000001A01","typeName":"内勤人员","typeValue":"1"},{"insurerCode":"A02","insurerId":5,"subType":[],"typeId":"1000001A02","typeName":"外勤人员(从事+联系工作)","typeValue":"2"}],"typeId":"1000001","typeName":"机关团体公司行号","typeValue":"0"}]
             * typeId : 1000
             * typeName : 一般
             * typeValue : 0
             */

            private String insurerCode;
            private int insurerId;
            private String typeId;
            private String typeName;
            private String typeValue;
            private List<SubTypeBeanX> subType;

            public String getInsurerCode() {
                return insurerCode;
            }

            public void setInsurerCode(String insurerCode) {
                this.insurerCode = insurerCode;
            }

            public int getInsurerId() {
                return insurerId;
            }

            public void setInsurerId(int insurerId) {
                this.insurerId = insurerId;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getTypeValue() {
                return typeValue;
            }

            public void setTypeValue(String typeValue) {
                this.typeValue = typeValue;
            }

            public List<SubTypeBeanX> getSubType() {
                return subType;
            }

            public void setSubType(List<SubTypeBeanX> subType) {
                this.subType = subType;
            }

            public static class SubTypeBeanX implements Serializable{
                /**
                 * insurerCode :
                 * insurerId : 5
                 * subType : [{"insurerCode":"A01","insurerId":5,"subType":[],"typeId":"1000001A01","typeName":"内勤人员","typeValue":"1"},{"insurerCode":"A02","insurerId":5,"subType":[],"typeId":"1000001A02","typeName":"外勤人员(从事+联系工作)","typeValue":"2"}]
                 * typeId : 1000001
                 * typeName : 机关团体公司行号
                 * typeValue : 0
                 */

                private String insurerCode;
                private int insurerId;
                private String typeId;
                private String typeName;
                private String typeValue;
                private List<SubTypeBean> subType;

                public String getInsurerCode() {
                    return insurerCode;
                }

                public void setInsurerCode(String insurerCode) {
                    this.insurerCode = insurerCode;
                }

                public int getInsurerId() {
                    return insurerId;
                }

                public void setInsurerId(int insurerId) {
                    this.insurerId = insurerId;
                }

                public String getTypeId() {
                    return typeId;
                }

                public void setTypeId(String typeId) {
                    this.typeId = typeId;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }

                public String getTypeValue() {
                    return typeValue;
                }

                public void setTypeValue(String typeValue) {
                    this.typeValue = typeValue;
                }

                public List<SubTypeBean> getSubType() {
                    return subType;
                }

                public void setSubType(List<SubTypeBean> subType) {
                    this.subType = subType;
                }

                public static class SubTypeBean implements Serializable{
                    /**
                     * insurerCode : A01
                     * insurerId : 5
                     * subType : []
                     * typeId : 1000001A01
                     * typeName : 内勤人员
                     * typeValue : 1
                     */

                    private String insurerCode;
                    private int insurerId;
                    private String typeId;
                    private String typeName;
                    private String typeValue;
                    private List<?> subType;
                    private int myId;

                    public int getMyId() {
                        return Integer.parseInt(typeId.substring(typeId.length()-2,typeId.length()));
                    }

                    public void setMyId(int myId) {
                        this.myId = myId;
                    }

                    public String getInsurerCode() {
                        return insurerCode;
                    }

                    public void setInsurerCode(String insurerCode) {
                        this.insurerCode = insurerCode;
                    }

                    public int getInsurerId() {
                        return insurerId;
                    }

                    public void setInsurerId(int insurerId) {
                        this.insurerId = insurerId;
                    }

                    public String getTypeId() {
                        return typeId;
                    }

                    public void setTypeId(String typeId) {
                        this.typeId = typeId;
                    }

                    public String getTypeName() {
                        return typeName;
                    }

                    public void setTypeName(String typeName) {
                        this.typeName = typeName;
                    }

                    public String getTypeValue() {
                        return typeValue;
                    }

                    public void setTypeValue(String typeValue) {
                        this.typeValue = typeValue;
                    }

                    public List<?> getSubType() {
                        return subType;
                    }

                    public void setSubType(List<?> subType) {
                        this.subType = subType;
                    }
                }
            }
        }
}
