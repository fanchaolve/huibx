package com.bb.hbx.api;

import android.util.Log;


import com.bb.hbx.MyApplication;
import com.bb.hbx.utils.AppUtils;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.utils.MD5Util;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/12/1.
 */

public class BasicParamsInterceptor implements Interceptor {


    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader("height", MyApplication.heightPixels + "");
        requestBuilder.addHeader("width", MyApplication.widthPixels + "");
        requestBuilder.addHeader("osName", Constants.OSNAME);
        requestBuilder.addHeader("clientVersion", AppUtils.getAppVersionName(MyApplication.getAppContext()));
        requestBuilder.addHeader("clientType", "10");
        requestBuilder.addHeader("versionType", "2");
        requestBuilder.addHeader("deviceNo", MyApplication.DUID);
        requestBuilder.addHeader("deviceName", "10");

        FormBody oldFormBody = (FormBody) request.body();
        TreeMap<String, String> treeMap = new TreeMap<>();
        JSONObject jsonObject=new JSONObject();
        for (int i = 0; i < oldFormBody.size(); i++) {
            try {
                jsonObject.put(oldFormBody.name(i),oldFormBody.value(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String sign = MD5Util.MD5(jsonObject.toString() + GenApiHashUrl.md5_key).toLowerCase();
        treeMap.put("input", jsonObject.toString());
        treeMap.put("sign", sign);
        RequestBody body = generateMultipartRequestBody(MEDIA_TYPE_JSON, treeMap);
        requestBuilder.method(request.method(), body);
        request = requestBuilder.build();
        return chain.proceed(request);
    }

    /**
     * 生成类是表单的请求体
     */
    protected RequestBody generateMultipartRequestBody(MediaType type, Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            String key = it2.next();
            builder.append(key);
            builder.append("=");
            builder.append(map.get(key));
            builder.append("&");
        }

        builder.deleteCharAt(builder.length() - 1);
        Log.d("info", builder.toString());
        RequestBody body = RequestBody.create(type, builder.toString().getBytes());

        return body;
    }

}
