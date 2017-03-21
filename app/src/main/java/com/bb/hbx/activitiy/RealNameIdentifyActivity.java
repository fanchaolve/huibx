package com.bb.hbx.activitiy;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;
import com.bb.hbx.utils.CompressBitmap;
import com.bb.hbx.utils.STSGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;

public class RealNameIdentifyActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.front_layout)
    RelativeLayout front_layout;
    @BindView(R.id.reverse_layout)
    RelativeLayout reverse_layout;
    @BindView(R.id.front_iv)
    ImageView front_iv;
    @BindView(R.id.reverse_iv)
    ImageView reverse_iv;
    @BindView(R.id.frontFlag_iv)
    ImageView frontFlag_iv;
    @BindView(R.id.reverseFlag_iv)
    ImageView reverseFlag_iv;
    @BindView(R.id.next_tv)
    TextView next_tv;

    File picFileFront;
    String picPathFront="";
    File picFileReverse;
    String picPathReverse="";
    Bitmap bitmapFront;
    Bitmap bitmapReverse;

    //true: 成功 false:失败
    boolean isFlag = true;
    OSSFederationToken token;
    String callbackAddress="http://ebao.seaway.net.cn:9003/api/ossCallback.do";
    @Override
    public int getLayoutId() {
        return R.layout.activity_real_name_identify;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        front_layout.setOnClickListener(this);
        reverse_layout.setOnClickListener(this);
        next_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        int code = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int code2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int code3 = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (code == -1||code2 == -1||code3 == -1) {
            //授权失败
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE
            }, 201);
        } else {
            //授权成功
            isFlag = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.front_layout:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //以下为了获得原图
                    String cameraPath= Can.getDefaultUsersIconFile();
                    File file = new File(cameraPath);
                    picFileFront = new File(file, System.currentTimeMillis() + ".jpg");
                    picPathFront=picFileFront.getAbsolutePath();
                    Uri uri = Uri.fromFile(picFileFront);
                    //为拍摄的图片指定一个存储的路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                    startActivityForResult(intent,101);
                }
                else
                {
                    Toast.makeText(RealNameIdentifyActivity.this,"请检查您的sdk",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reverse_layout:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //以下为了获得原图
                    String cameraPath= Can.getDefaultUsersIconFile();
                    File file = new File(cameraPath);
                    picFileReverse = new File(file, System.currentTimeMillis() + ".jpg");
                    picPathReverse=picFileReverse.getAbsolutePath();
                    Uri uri = Uri.fromFile(picFileReverse);
                    //为拍摄的图片指定一个存储的路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                    startActivityForResult(intent,102);
                }
                else
                {
                    Toast.makeText(RealNameIdentifyActivity.this,"请检查您的sdk",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.next_tv:
                Intent intent = new Intent(this, RealNameCommitActivity.class);
                intent.putExtra("front",picPathFront);
                intent.putExtra("reverse",picPathReverse);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101)
        {

            bitmapFront = CompressBitmap.compressBitmapOnly(picPathFront, 4);
            if (bitmapFront==null)//bitmapFront==null
            {
                front_iv.setImageResource(R.drawable.shenfenzhengzhengmian);
                frontFlag_iv.setVisibility(View.VISIBLE);
            }
            else
            {
                //Bitmap getbitmap=null;
                if (picFileFront.exists())
                {
                    picFileFront.delete();
                }
                try {
                    picFileFront.createNewFile();
                    FileOutputStream fos = new FileOutputStream(picFileFront);//fos 为毛是null
                    bitmapFront.compress(Bitmap.CompressFormat.PNG,100,fos);
                    /*getbitmap = getbitmap(bitmapFront);
                    getbitmap.compress(Bitmap.CompressFormat.PNG,100,fos);*/
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                front_iv.setImageBitmap(bitmapFront);//bitmapFront
                frontFlag_iv.setVisibility(View.GONE);
                //new MyOssUtils(getApplicationContext(),picPathFront,"idcard","idCard_F","_F");
                STSGetter getter=new STSGetter();
                OSS oss = new OSSClient(getApplicationContext(),"http://img-cn-hangzhou.aliyuncs.com",getter);

                        /*String s = Can.getDefaultUsersIconFile() + "/20245617_095937129615_2.jpg";
                        String s1 = ShareSPUtils.sp.getString("userIcon", null);*/
                // 构造上传请求
                //PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg");
                PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/idcard/"+ MyApplication.user.getUserId()+"_F.jpg", picPathFront);
                // 异步上传时可以设置进度回调
                put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                    @Override
                    public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                        Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                    }
                });

                if (callbackAddress != null) {
                    // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
                    put.setCallbackParam(new HashMap<String, String>() {
                        {
                            put("callbackUrl", callbackAddress);
                            //callbackBody可以自定义传入的信息
                            put("callbackBody", "uploadType=idCard_F&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+"_F.jpg");

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
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(string);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JSONObject output = jsonObject.getJSONObject("output");
                            String userLogo = output.getString("userLogo");
                            showTip("userLogo"+userLogo);
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
        else if (requestCode==102)
        {
            bitmapReverse = CompressBitmap.compressBitmapOnly(picPathReverse, 4);

            if (bitmapReverse==null)
            {
                reverse_iv.setImageResource(R.drawable.shenfenzhengfanmian);
                reverseFlag_iv.setVisibility(View.VISIBLE);
            }
            else
            {
                if (picFileReverse.exists())
                {
                    picFileReverse.delete();
                }
                try {
                    picFileReverse.createNewFile();
                    FileOutputStream fos = new FileOutputStream(picFileReverse);//fos 为毛是null
                    bitmapReverse.compress(Bitmap.CompressFormat.PNG,100,fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                reverse_iv.setImageBitmap(bitmapReverse);
                reverseFlag_iv.setVisibility(View.GONE);
                //new MyOssUtils(getApplicationContext(),picPathReverse,"idcard","idCard_B","_B");
                STSGetter getter=new STSGetter();
                OSS oss = new OSSClient(getApplicationContext(),"http://img-cn-hangzhou.aliyuncs.com",getter);

                        /*String s = Can.getDefaultUsersIconFile() + "/20245617_095937129615_2.jpg";
                        String s1 = ShareSPUtils.sp.getString("userIcon", null);*/
                // 构造上传请求
                //PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg");
                PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/idcard/"+ MyApplication.user.getUserId()+"_B.jpg", picPathReverse);
                // 异步上传时可以设置进度回调
                put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                    @Override
                    public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                        Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                    }
                });

                if (callbackAddress != null) {
                    // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
                    put.setCallbackParam(new HashMap<String, String>() {
                        {
                            put("callbackUrl", callbackAddress);
                            //callbackBody可以自定义传入的信息
                            put("callbackBody", "uploadType=idCard_B&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+"_B.jpg");

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
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(string);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JSONObject output = jsonObject.getJSONObject("output");
                            String userLogo = output.getString("userLogo");
                            showTip("userLogo"+userLogo);
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
            //bitmap.recycle();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201) {
            if (grantResults[0] == 0&&grantResults[1] == 0&&grantResults[2] == 0) {
                //授权成功
                isFlag = true;
            } else {
                //授权失败
                isFlag = false;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmapFront!=null)
        {
            bitmapFront.recycle();
        }
        if (bitmapReverse!=null)
        {
            bitmapReverse.recycle();
        }
    }

    public Bitmap getbitmap(Bitmap bitmap){
        int buff=0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //放大為屏幕的1/2大小
        float screenWidth  = getWindowManager().getDefaultDisplay().getHeight();     // 屏幕宽（像素，如：480px）
        float screenHeight = getWindowManager().getDefaultDisplay().getWidth();        // 屏幕高（像素，如：800p）
        Log.d("screen",screenWidth+"");
        float scaleWidth = screenWidth/2/width;
        float scaleHeight = screenWidth/2/width;
        if (width<height)
        {
            scaleWidth=2;
            scaleHeight=0.2f;
        }

        // 取得想要缩放的matrix參數
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的圖片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
        return newbm;
    }
}
