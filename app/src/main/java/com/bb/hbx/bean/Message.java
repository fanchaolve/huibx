package com.bb.hbx.bean;

//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Keep;
//import org.greenrobot.greendao.annotation.Generated;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/1/23.
 */

//@Entity
public class Message extends RealmObject {


    /**
     * msgContent : 恭喜周伟亮下单成功获得100万元返利
     * msgId : 4
     * msgLink :
     * msgTime : 20170121173832
     * msgTitle : 系统消息
     * msgType : 1
     * sts : 1
     */

//    @Id(autoincrement = true)
    long id;
    private String msgContent;
    @PrimaryKey
    private String msgId;
    private String msgLink;
    private String msgTime;
    private String msgTitle;
    private String msgType;
    private int sts;            //1:未读   2:已读
    private boolean isRead;         //已读 未读

    //    @Keep
//    public Message(long id, String msgContent, String msgId, String msgLink, String msgTime, String msgTitle, String msgType, int sts, boolean isRead) {
//        this.id = id;
//        this.msgContent = msgContent;
//        this.msgId = msgId;
//        this.msgLink = msgLink;
//        this.msgTime = msgTime;
//        this.msgTitle = msgTitle;
//        this.msgType = msgType;
////        this.sts = sts;
//        this.isRead = isRead;
//    }

    //    @Generated(hash = 637306882)
    public Message() {
    }

    //    @Generated(hash = 1171299826)
    public Message(long id, String msgContent, String msgId, String msgLink, String msgTime, String msgTitle, String msgType, int sts, boolean isRead) {
        this.id = id;
        this.msgContent = msgContent;
        this.msgId = msgId;
        this.msgLink = msgLink;
        this.msgTime = msgTime;
        this.msgTitle = msgTitle;
        this.msgType = msgType;
        this.sts = sts;
        this.isRead = isRead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgLink() {
        return msgLink;
    }

    public void setMsgLink(String msgLink) {
        this.msgLink = msgLink;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public int getSts() {
        return sts;
    }

    public void setSts(int sts) {
        this.sts = sts;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
