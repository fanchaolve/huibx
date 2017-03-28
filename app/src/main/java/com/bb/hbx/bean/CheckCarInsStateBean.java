package com.bb.hbx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class CheckCarInsStateBean {

    /**
     * output : {"extractCarProps":[],"serialId":"bbkj00000000001429145113","state":"0"}
     * respCode : 000000
     * respMsg :
     * success : true
     */

        /**
         * extractCarProps : []
         * serialId : bbkj00000000001429145113
         * state : 0
         */

        private String serialId;
        private String state;
        private List<?> extractCarProps;

        public String getSerialId() {
            return serialId;
        }

        public void setSerialId(String serialId) {
            this.serialId = serialId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<?> getExtractCarProps() {
            return extractCarProps;
        }

        public void setExtractCarProps(List<?> extractCarProps) {
            this.extractCarProps = extractCarProps;
        }
}
