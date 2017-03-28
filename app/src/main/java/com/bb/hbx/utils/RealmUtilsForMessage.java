package com.bb.hbx.utils;

import com.bb.hbx.bean.Message;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.R.attr.id;

/**
 * Created by Administrator on 2017/3/17.
 * realm数据库操作工具类(针对message)
 */

public class RealmUtilsForMessage {

    /**
     * 增
     *
     * @param message
     */
    public static void add(Message message) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(message);
        realm.commitTransaction();
    }

    /**
     * 删
     */
    public static void delete() {
        Realm mRealm = Realm.getDefaultInstance();

        final RealmResults<Message> messages = mRealm.where(Message.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Message message = messages.get(5);
                message.deleteFromRealm();
                //删除第一个数据
                messages.deleteFirstFromRealm();
                //删除最后一个数据
                messages.deleteLastFromRealm();
                //删除位置为1的数据
                messages.deleteFromRealm(1);
                //删除所有数据
                messages.deleteAllFromRealm();
            }
        });
    }

    /**
     * 删除全部
     */
    public static void deleteAll() {
        Realm mRealm = Realm.getDefaultInstance();

        final RealmResults<Message> messages = mRealm.where(Message.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

//                Message message = messages.get(5);
//                message.deleteFromRealm();
//                //删除第一个数据
//                messages.deleteFirstFromRealm();
//                //删除最后一个数据
//                messages.deleteLastFromRealm();
//                //删除位置为1的数据
//                messages.deleteFromRealm(1);
                //删除所有数据
                messages.deleteAllFromRealm();
            }
        });
    }

    /**
     * 改    修改消息是否删除的状态
     */
    public static void update(String msgId, boolean isDelete) {
        Realm  mRealm=Realm.getDefaultInstance();

        Message message = mRealm.where(Message.class).equalTo("msgId", msgId).findFirst();
        mRealm.beginTransaction();
        message.setDelete(isDelete);
        mRealm.commitTransaction();
    }

    /**
     * 查询所有
     * @return
     */
    public static List<Message> queryAllMessages() {
        Realm  mRealm=Realm.getDefaultInstance();
        RealmResults<Message> messages = mRealm.where(Message.class).findAll();
        return mRealm.copyFromRealm(messages);
    }

    /**
     * 条件查找
     * @param msgId
     * @return
     */
    public static Message queryMessageById(String msgId) {
        Realm  mRealm=Realm.getDefaultInstance();
        Message message = mRealm.where(Message.class).equalTo("msgId", msgId).findFirst();
        return message;
    }
}
