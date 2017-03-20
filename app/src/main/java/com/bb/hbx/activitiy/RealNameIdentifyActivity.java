package com.bb.hbx.activitiy;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;
import com.bb.hbx.utils.CompressBitmap;
import com.bb.hbx.utils.MyOssUtils;

import java.io.File;

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
            bitmapFront = CompressBitmap.compressBitmapOnly(picPathFront, 2);
            if (bitmapFront==null)
            {
                front_iv.setImageResource(R.drawable.shenfenzhengzhengmian);
                frontFlag_iv.setVisibility(View.VISIBLE);
            }
            else
            {
                front_iv.setImageBitmap(bitmapFront);
                frontFlag_iv.setVisibility(View.GONE);
                new MyOssUtils(getApplicationContext(),picPathFront,"idcard","idCard_F","_F");
            }
        }
        else if (requestCode==102)
        {
            bitmapReverse = CompressBitmap.compressBitmapOnly(picPathReverse, 2);
            if (bitmapReverse==null)
            {
                reverse_iv.setImageResource(R.drawable.shenfenzhengfanmian);
                reverseFlag_iv.setVisibility(View.VISIBLE);
            }
            else
            {
                reverse_iv.setImageBitmap(bitmapReverse);
                reverseFlag_iv.setVisibility(View.GONE);
                new MyOssUtils(getApplicationContext(),picPathReverse,"idcard","idCard_B","_B");
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
}
