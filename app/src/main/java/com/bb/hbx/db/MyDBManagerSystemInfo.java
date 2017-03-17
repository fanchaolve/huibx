package com.bb.hbx.db;

import android.content.Context;

import com.bb.hbx.bean.DaoMaster;
import com.bb.hbx.bean.DaoSession;
import com.bb.hbx.bean.Message;
import com.bb.hbx.bean.MessageDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */

public class MyDBManagerSystemInfo {

    private MessageDao dao;
    DaoSession daoSession;
    public MyDBManagerSystemInfo(Context context) {
        //this.dao = dao;
        daoSession = DaoMaster.newDevSession(context, "systeminfo");
        dao=daoSession.getMessageDao();
    }

    //插入单个
    public void insertObject(Message bean)
    {
        dao.insert(bean);
    }

    //插入或替换单个
    public void insertOrReplaceObject(Message bean)
    {
        dao.insertOrReplace(bean);
    }

    //查询所有,返回个数
    public int queryAll()
    {
        Query<Message> build = dao.queryBuilder().orderAsc(MessageDao.Properties.Id).build();
        List<Message> list = build.list();
        if (list==null)
        {
            return 0;
        }
        else
        {
            return list.size();
        }
    }

    //查询所有,返回所有bean的集合
    public List<Message> queryAllObject()
    {
        Query<Message> build = dao.queryBuilder().orderAsc(MessageDao.Properties.Id).build();
        List<Message> list = build.list();
        return list;
    }

    //指定查询
    public ArrayList<Message> queryOne(String key)
    {
        QueryBuilder<Message> builder = dao.queryBuilder();
        QueryBuilder<Message> query = builder.where(MessageDao.Properties.MsgId.eq(key));
        List<Message> list = query.list();
        return (ArrayList<Message>) list;
    }

    //删除一条
    public void deleteObject(Message bean)
    {
        dao.delete(bean);
    }

    //删除所有
    public void deleteAll()
    {
        dao.deleteAll();
    }

    /**
     * 关闭数据库
     */
    public void CloseDataBase(){
        closeDaoSession();
    }

    public void closeDaoSession(){
        if (null != daoSession){
            daoSession.clear();
            daoSession = null;
        }
    }
}
