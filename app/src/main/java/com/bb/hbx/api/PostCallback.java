package com.bb.hbx.api;

import android.util.Log;


import com.bb.hbx.base.v.BaseView;
import com.bb.hbx.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2016/12/2.
 */

public abstract class PostCallback<V extends BaseView> implements Callback<Result_Api> {


    private final static String TAG = PostCallback.class.getSimpleName();

    private final static String ERROR_DATA = "获取数据异常";
    private final static String NET_ERROR = "请检查网络是否正常";

    private V view;

//    private Class<T> clazz;
//
    public PostCallback(V view) {
          this.view = view;
//        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
//            clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//
//        }

    }

    public abstract void successCallback(Result_Api api);

    public abstract void failCallback();

    @Override
    public void onResponse(Call<Result_Api> call, Response<Result_Api> response) {
        Log.i(TAG, "response.code():" + response.code() + "%%%%%%%" + response.body());

        view.dissmissLoading();
        Result_Api api = response.body();
        if (api != null) {
            if (Constants.SUCCESS.equalsIgnoreCase(api.getRespCode())) {
//                if (api.getOutput() != null) {
//
//                    JSONObject jsonObject= null;
//                    try {
//                        jsonObject = new JSONObject(api.getOutput().toString());
//                        T t = new Gson().fromJson(jsonObject.toString(), clazz);
//                        api.setOutput(t);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
                successCallback(api);
            } else
                view.showMsg(api.getRespMsg());
        } else

            view.showMsg(ERROR_DATA);


    }

    @Override
    public void onFailure(Call<Result_Api> call, Throwable t) {
        view.dissmissLoading();
        view.showMsg(NET_ERROR);
        failCallback();
    }
}