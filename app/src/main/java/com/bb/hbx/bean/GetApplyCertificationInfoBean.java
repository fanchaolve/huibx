package com.bb.hbx.bean;

/**
 * Created by Administrator on 2017/3/24.
 */

public class GetApplyCertificationInfoBean {

    /**
     * output : {"audit_comment":null,"audit_sts":0}
     * respCode : 000000
     * respMsg : 实名认证审核信息取得成功！
     * success : true
     */

        /**
         * audit_comment : null
         * audit_sts : 0
         */

        private Object audit_comment;
        private int audit_sts;

        public Object getAudit_comment() {
            return audit_comment;
        }

        public void setAudit_comment(Object audit_comment) {
            this.audit_comment = audit_comment;
        }

        public int getAudit_sts() {
            return audit_sts;
        }

        public void setAudit_sts(int audit_sts) {
            this.audit_sts = audit_sts;
        }
}
