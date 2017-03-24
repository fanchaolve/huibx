package com.bb.hbx.activitiy;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.utils.CameraManager;
import com.bb.hbx.utils.FileUtils;

import java.io.File;

public class ACameraActivity extends BaseActivity implements SurfaceHolder.Callback{

    protected static final String tag = "ACameraActivity";
    private CameraManager mCameraManager;
    private SurfaceView mSurfaceView;
    private ProgressBar pb;
    private ImageButton mShutter;
    private SurfaceHolder mSurfaceHolder;
    private String flashModel = Camera.Parameters.FLASH_MODE_OFF;
    private byte[] jpegData = null;
    String filePathBuff="";

    private Handler mHandler=new Handler(){

        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getBaseContext(), "拍照失败", Toast.LENGTH_SHORT).show();
                    mCameraManager.initPreView();
                    break;
                case 1:
                    jpegData=(byte[]) msg.obj;
                    if(jpegData!=null && jpegData.length>0){
                        pb.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result= null;
                                //boolean isavilable= NetUtil.isNetworkConnectionActivity(ACameraActivity.this);

                                //result = Scan(MainActivity.action,jpegData,"jpg");

                                //mHandler.sendMessage(mHandler.obtainMessage(4, result));
                                mHandler.sendMessage(mHandler.obtainMessage(4, jpegData));

                            }
                        }).start();
                    }
                    break;
                case 3:
                    pb.setVisibility(View.GONE);
                    String str=msg.obj+"";
                    Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
                    mCameraManager.initPreView();
                    mShutter.setEnabled(true);
                    break;
                case 4:
                    mShutter.setEnabled(true);
                    pb.setVisibility(View.GONE);
                    //String result=msg.obj+"";
                    byte [] result= (byte[]) msg.obj;
                    Intent intent=new Intent();

                    intent.putExtra("result", result);
                    intent.putExtra("filePathBuff",filePathBuff);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                    break;
                case 5:
                    String filePath=msg.obj+"";//图片存储路径
                    filePathBuff=filePath;
                    byte[] data= FileUtils.getByteFromPath(filePath);
                    Log.d(tag, "data length:"+data.length);
                    if(data!=null && data.length>0){
                        mHandler.sendMessage(mHandler.obtainMessage(1,data));
                    }else{
                        mHandler.sendMessage(mHandler.obtainMessage(0));
                    }
                    break;
                case 6:
                    Toast.makeText(getBaseContext(), "请插入存储卡！", Toast.LENGTH_SHORT).show();
                    mCameraManager.initPreView();
                    break;
            }
        };
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_acamera;
    }

    @Override
    public void initView() {
        mCameraManager = new CameraManager(mHandler,this);
        initViews();

        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(getBaseContext(), "请插入存储卡", Toast.LENGTH_LONG).show();
            finish();
        }

        File dir = new File(CameraManager.strDir);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    private void initViews() {
        pb = (ProgressBar) findViewById(R.id.reco_recognize_bar);
        mSurfaceView = (SurfaceView) findViewById(R.id.camera_preview);
        mShutter = (ImageButton) findViewById(R.id.camera_shutter);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(ACameraActivity.this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mShutter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCameraManager.requestFocuse();
                mShutter.setEnabled(false);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraManager.openCamera(mSurfaceHolder);
        if(flashModel ==null || !mCameraManager.isSupportFlash(flashModel)){
            flashModel =mCameraManager.getDefaultFlashMode();
        }
        mCameraManager.setCameraFlashMode(flashModel);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(width>height){
            mCameraManager.setPreviewSize(width,height);
        }else{
            mCameraManager.setPreviewSize(height,width);
        }
        mCameraManager.initPreView();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraManager.closeCamera();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            setResult(Activity.RESULT_OK);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
