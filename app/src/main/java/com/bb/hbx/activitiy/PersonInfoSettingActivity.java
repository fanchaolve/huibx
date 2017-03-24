package com.bb.hbx.activitiy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.GetApplyCertificationInfoBean;
import com.bb.hbx.cans.Can;
import com.bb.hbx.db.DatabaseImpl;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.CompressBitmap;
import com.bb.hbx.utils.GlideUtil;
import com.bb.hbx.utils.STSGetter;
import com.bb.hbx.utils.ShareSPUtils;
import com.bb.hbx.widget.ChangeIconDailog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonInfoSettingActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.userIcon_civ)
    CircleImageView userIcon_civ;

    @BindView(R.id.userIcon_layout)
    RelativeLayout userIcon_layout;
    @BindView(R.id.name_layout)
    RelativeLayout name_layout;
    @BindView(R.id.sex_layout)
    RelativeLayout sex_layout;
    @BindView(R.id.email_layout)
    RelativeLayout email_layout;
    @BindView(R.id.address_layout)
    RelativeLayout address_layout;
    @BindView(R.id.realNameIdentify_layout)
    RelativeLayout realNameIdentify_layout;
    @BindView(R.id.countSafe_layout)
    RelativeLayout countSafe_layout;

    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.sex_tv)
    TextView sex_tv;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.email_tv)
    TextView email_tv;

    TextView camera_tv;
    TextView mapstorage_tv;

    String userId;
    String name;
    String email;
    File picFile;
    String picPath;

    OSSFederationToken token;
    String callbackAddress="http://ebao.seaway.net.cn:9003/api/ossCallback.do";
    @Override
    public int getLayoutId() {
        return R.layout.activity_person_info_setting;
    }

    @Override
    public void initView() {
        //ShareSPUtils.readShareSP();
        if (ShareSPUtils.sp!=null)
        {
            Can.hasLogined=ShareSPUtils.sp.getBoolean("hasLogined",false);
            Can.userName=ShareSPUtils.sp.getString("userName",null);
            Can.userPhone=ShareSPUtils.sp.getString("userPhone",null);
            Can.userPwd=ShareSPUtils.sp.getString("userPwd",null);
            Can.userIcon= ShareSPUtils.sp.getString("userIcon", null);
            //Log.d("========activity","=========="+Can.userIcon);
            //userIcon_civ.setImageBitmap(BitmapFactory.decodeFile(Can.userIcon));---------------
            //Glide.with(this).load(Can.userIcon).placeholder(R.mipmap.ic_launcher).into(userIcon_civ);
            //ImageCatchUtil.getInstance().clearImageAllCache();
            GlideUtil.getInstance().loadImageWithNoCache(this,userIcon_civ,Can.userIcon);
        }
        /*if (ShareSPUtils.sp.getBoolean("isChangeUserIcon",false))
        {
            GlideUtil.getInstance().loadImageWithCache(this,userIcon_civ,Can.userIcon,false);
        }
        else
        {
            GlideUtil.getInstance().loadImageWithCache(this,userIcon_civ,Can.userIcon,true);
        }*/
        //MyOssUtils myOssUtils = new MyOssUtils(getApplicationContext(),Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg","logo","logo","");
        //MyOssUtils myOssUtils = new MyOssUtils(getApplicationContext(),ShareSPUtils.sp.getString("userIcon",null),"logo","logo","");
        /*STSGetter getter=new STSGetter();
        OSS oss = new OSSClient(getApplicationContext(),"http://img-cn-hangzhou.aliyuncs.com",getter);

        String s = Can.getDefaultUsersIconFile() + "/20245617_095937129615_2.jpg";
        String s1 = ShareSPUtils.sp.getString("userIcon", null);
        // 构造上传请求
        //PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg");
        PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", ShareSPUtils.sp.getString("userIcon",null));
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
                    put("callbackBody", "uploadType=logo&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+".jpg");

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
        });*/

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        userIcon_layout.setOnClickListener(this);
        name_layout.setOnClickListener(this);
        sex_layout.setOnClickListener(this);
        email_layout.setOnClickListener(this);
        address_layout.setOnClickListener(this);
        realNameIdentify_layout.setOnClickListener(this);
        countSafe_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        updateUserInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserInfo();
    }

    private void updateUserInfo() {
        SQLiteDatabase db= DatabaseImpl.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from userstb where currentUser = ?", new String[]{"currentUser"});
        if (cursor!=null)
        {
            if (cursor.moveToNext())
            {
                /*String userId = cursor.getString(cursor.getColumnIndex("userId"));
                String sessionId = cursor.getString(cursor.getColumnIndex("sessionId"));*/
                userId = cursor.getString(cursor.getColumnIndex("userId"));
                name = cursor.getString(cursor.getColumnIndex("name"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                email = cursor.getString(cursor.getColumnIndex("email"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                //使用正则匹配将电话号码的4-7位替换成*
                String displayPhoneNum = phone.trim().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                name_tv.setText(name);
                if (TextUtils.isEmpty(email))
                {
                    email_tv.setText("请设置邮箱地址");
                }
                else
                {
                    email_tv.setText(email);
                }
                if (TextUtils.isEmpty(phone))//用户可能是通过短信的方式登录
                {
                    phone_tv.setText("请绑定手机号");
                }
                else
                {
                    phone_tv.setText(displayPhoneNum);
                }
                if (gender.equals("0"))
                {
                    sex_tv.setText("男");
                }
                else if (gender.equals("1"))
                {
                    sex_tv.setText("女");
                }
                else
                {
                    sex_tv.setText("性别未知");
                }
            }
            else
            {
                Toast.makeText(mContext,"cursor下一条不存在",Toast.LENGTH_SHORT);
            }
        }
        else
        {
            Toast.makeText(mContext,"cursor为空",Toast.LENGTH_SHORT);
        }
        cursor.close();
        db.close();
    }


    @Override
    public void onClick(View v) {
        final Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.userIcon_layout:
                final ChangeIconDailog iconDailog = new ChangeIconDailog(this);
                iconDailog.show();
                iconDailog.setmItemCameralickListener(new OnItemClickListener() {
                    @Override
                    public void onMyItemClickListener(int position) {
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            //以下为了获得原图
                            String cameraPath= Can.getDefaultUsersIconFile();
                            File file = new File(cameraPath);
                            picFile = new File(file, System.currentTimeMillis() + ".jpg");
                            picPath=picFile.getAbsolutePath();
                            //Uri uri = Uri.fromFile(picFile);
                            //为拍摄的图片指定一个存储的路径
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, picFile);
                            //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                            startActivityForResult(intent,101);
                        }
                        else
                        {
                            Toast.makeText(PersonInfoSettingActivity.this,"请检查您的sdk",Toast.LENGTH_SHORT).show();
                        }
                        iconDailog.dismiss();
                    }
                });
                iconDailog.setmItemMapClickListener(new OnItemClickListener() {
                    @Override
                    public void onMyItemClickListener(int position) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(intent,102);
                        iconDailog.dismiss();
                    }
                });
                break;
            case R.id.name_layout:
                intent.setClass(PersonInfoSettingActivity.this,EditNameActivity.class);
                intent.putExtra("name",name);
                startActivityForResult(intent,103);
                break;
            case R.id.sex_layout:
                intent.setClass(PersonInfoSettingActivity.this,SexActivity.class);
                startActivity(intent);
                break;
            case R.id.email_layout:
                intent.setClass(PersonInfoSettingActivity.this,EditEmailActivity.class);
                intent.putExtra("email",email);
                startActivityForResult(intent,104);
                break;
            case R.id.address_layout:
                intent.setClass(PersonInfoSettingActivity.this,AddressManagerActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                break;
            case R.id.realNameIdentify_layout:
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call=service.getApplyCertificationInfo(MyApplication.user.getUserId());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api body = (Result_Api) response.body();
                        GetApplyCertificationInfoBean bean = (GetApplyCertificationInfoBean) body.getOutput();
                        if (body.isSuccess())
                        {
                            intent.setClass(PersonInfoSettingActivity.this,HadRealNameIdentifyActivity.class);
                            intent.putExtra("audit_sts",bean.getAudit_sts());
                            intent.putExtra("audit_comment",bean.getAudit_sts());
                            startActivity(intent);
                        }
                        else
                        {
                            intent.setClass(PersonInfoSettingActivity.this,RealNameIdentifyActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });

                break;
            case R.id.countSafe_layout:
                //Toast.makeText(PersonInfoSettingActivity.this,"点击",Toast.LENGTH_SHORT).show();
                intent.setClass(PersonInfoSettingActivity.this,CountSecurityActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开系统图库请求
        if (requestCode==102)
        {
            if (resultCode==RESULT_OK)
            {
            if (data!=null)
                {
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            String mapPath = Can.getDefaultUsersIconFile();
                            File file = new File(mapPath);
                            mapPath = new File(file, System.currentTimeMillis() + ".jpg").getAbsolutePath();
                            FileOutputStream fos = null;
                            BufferedOutputStream bos = null;
                            try {
                                fos = new FileOutputStream(mapPath);
                                bos = new BufferedOutputStream(fos);
                                //compressBitmap(mapPath, fos);
                                bitmap.compress(Bitmap.CompressFormat.JPEG,80,bos);
                                bos.close();
                                //fos在压缩完毕后关闭
                                Bitmap compressBitmap = CompressBitmap.compressBitmap(mapPath, fos,3);

                                /*ShareSPUtils.edit.putString("userIcon", mapPath);
                                ShareSPUtils.edit.commit();*/
                                if (compressBitmap!=null)
                                {
                                    userIcon_civ.setImageBitmap(compressBitmap);
                                }

                                STSGetter getter=new STSGetter();
                                OSS oss = new OSSClient(getApplicationContext(),"http://img-cn-hangzhou.aliyuncs.com",getter);

                        /*String s = Can.getDefaultUsersIconFile() + "/20245617_095937129615_2.jpg";
                        String s1 = ShareSPUtils.sp.getString("userIcon", null);*/
                                // 构造上传请求
                                //PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg");
                                PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", mapPath);
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
                                            put("callbackBody", "uploadType=logo&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+".jpg");

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
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(PersonInfoSettingActivity.this, "请检查您的sdk", Toast.LENGTH_SHORT).show();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if (requestCode==101)
        {
            if (data!=null)
            {
                Bundle bundle = data.getExtras();
                if (bundle!=null)
                {
                    try {
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        userIcon_civ.setImageBitmap(bitmap);
                        ShareSPUtils.edit.putBoolean("isChangeUserIcon", true);
                        ShareSPUtils.edit.commit();
                        /*ShareSPUtils.edit.putString("userIcon", picPath);
                        ShareSPUtils.edit.commit();*/
                        if (picFile.exists())
                        {
                            picFile.delete();
                        }
                        picFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(picFile);//fos 为毛是null
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
                        fos.flush();
                        fos.close();

                        //MyOssUtils myOssUtils = new MyOssUtils(getApplicationContext(),picPath,"logo","logo","");

                        STSGetter getter=new STSGetter();
                        OSS oss = new OSSClient(getApplicationContext(),"http://img-cn-hangzhou.aliyuncs.com",getter);

                        /*String s = Can.getDefaultUsersIconFile() + "/20245617_095937129615_2.jpg";
                        String s1 = ShareSPUtils.sp.getString("userIcon", null);*/
                        // 构造上传请求
                        //PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", Can.getDefaultUsersIconFile()+"/20245617_095937129615_2.jpg");
                        PutObjectRequest put = new PutObjectRequest("hbx-image", "resource/images/user/logo/"+ MyApplication.user.getUserId()+".jpg", picPath);
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
                                    put("callbackBody", "uploadType=logo&content="+MyApplication.user.getUserId()+"&filename="+MyApplication.user.getUserId()+".jpg");

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

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Glide.with(PersonInfoSettingActivity.this).load(new File(picPath)).into(userIcon_civ);
                }
            }
            /*if (resultCode!=0)
            {
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                    File file = new File(Can.getDefaultUsersIconFile());
                    picPath=new File(file,System.currentTimeMillis()+".jpg").getAbsolutePath();
                    FileOutputStream fos = new FileOutputStream(picPath);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
                    bos.close();
                    Bitmap compressBitmap = CompressBitmap.compressBitmap(picPath,fos,3);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        else if (requestCode==103)
        {
            if (resultCode==Can.RESULT_NAME)
            {
                if (data!=null)
                {
                    String name = data.getStringExtra("name");
                    if (!TextUtils.isEmpty(name))
                    {
                        name_tv.setText(name);
                    }
                }
            }
        }
        else if (requestCode==104)
        {
            if (resultCode==Can.RESULT_EMAIL)
            {
                if (data!=null)
                {
                    String email = data.getStringExtra("email");
                    if (!TextUtils.isEmpty(email))
                    {
                        email_tv.setText(email);
                    }
                }
            }
        }
    }
}
