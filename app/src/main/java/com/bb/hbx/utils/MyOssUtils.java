package com.bb.hbx.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bb.hbx.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/21.
 */

public class MyOssUtils {

    Context mContext;
    String callbackAddress="http://ebao.seaway.net.cn:9003/api/ossCallback.do";
    String imgPath;

    public MyOssUtils(Context mContext,String imgPath,String type,String content,String dire) {
        this.mContext = mContext;
        this.imgPath=imgPath;
        updataLogo(type,content,dire);
    }

    private void updataLogo(String type, final String content, final String dire) {
        STSGetter getter=new STSGetter();
        OSS oss = new OSSClient(mContext,"http://img-cn-hangzhoaliyuncs.com",getter);

        //logo-----idcard   _F  _B
        // 构造上传请求
        //PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", /*Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg"*/imgPath);
        PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/"+type+"/"+ MyApplication.user.getUserId()+dire+".jpg", /*Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg"*/imgPath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        //logo---
        if (callbackAddress != null) {
            // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
            put.setCallbackParam(new HashMap<String, String>() {
                {
                    put("callbackUrl", callbackAddress);
                    //callbackBody可以自定义传入的信息
                    //put("callbackBody", "uploadType=logo&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+".jpg");
                    put("callbackBody", "uploadType="+content+"&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+".jpg");

                }
            });
        }

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                String string = result.getServerCallbackReturnBody().toString();
                Log.d("callbackAddress",string);
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    JSONObject output = jsonObject.getJSONObject("output");
                    String userLogo = output.getString("userLogo");
                    //showTip("userLogo"+userLogo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }
}
