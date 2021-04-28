package com.example.commonlibrary.view.activity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.commonlibrary.R;
import com.example.commonlibrary.bean.JsBean;
import com.example.commonlibrary.bean.ParamBean;
import com.example.commonlibrary.constant.ArouterUrl;
import com.example.commonlibrary.databinding.ActivityCameraForH5Binding;
import com.example.commonlibrary.utils.BitmapUtil;
import com.example.commonlibrary.utils.FileUtil;
import com.example.commonlibrary.utils.JsonUils;
import com.example.mvvmlibrary.lib_mvvm.utils.ToastUtil;
import com.example.mvvmlibrary.lib_mvvm.view.BaseMvvmActivity;
import com.example.mvvmlibrary.lib_mvvm.viewmodel.BaseViewModel;
import com.laikang.jtcameraview.CameraStateListener;
import com.laikang.jtcameraview.JTCameraView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.commonlibrary.constant.JsConstants.CAMERA_H5;
import static com.laikang.jtcameraview.Constants.CAMERA_FACING_BACK;
import static com.laikang.jtcameraview.Constants.CAMERA_FACING_FRONT;

@Route(path = ArouterUrl.CAMERFORH5)
public class CameraActivityForH5 extends BaseMvvmActivity<ActivityCameraForH5Binding, BaseViewModel> implements CameraStateListener {
    private List<ParamBean> ts;
    private List<String> bitmaps = new ArrayList<>();
    String cameraDevFacing = "0";
    String   diskLruCache;
    @Override
    protected void getBundleExtras(Bundle extras) {
        String param = extras.getString("params");
        JSONObject jsonObject = JSON.parseObject(param);
        if (jsonObject.containsKey("cameraDevFacing")){
            cameraDevFacing = jsonObject.getString("cameraDevFacing");
        }
        ts = JsonUils.parseStrinToList(jsonObject.getString("param"), ParamBean.class);
    }

    @Override
    protected void initView() {
        diskLruCache  = FileUtil.getDisk(this);
        mBinding.ftdv.setListener(this);
        mBinding.setCameraClick(new CameraClick());

    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return null;
    }

    @Override
    protected void dataObserver() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutRes() {
        setScreen();
        return R.layout.activity_camera_for_h5;
    }

    /**
     * 设置全屏显示
     */
    private void setScreen() {
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.ftdv.registerSensorManager(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBinding.ftdv.unregisterSensorManager(this);
    }

    MediaPlayer mediaPlayer;

    public void shootSound() {
        AudioManager meng = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int volume = meng.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        if (volume != 0) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, Uri.parse("file:///system/media/audio/ui/camera_click.ogg"));
            }
            if (mediaPlayer != null)
                mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        mBinding.ftdv.releaseCamera();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    public void TakePhoneCallBack() {
        for (int i = 0; i < bitmaps.size(); i++) {
            ts.get(i).setImage(bitmaps.get(i));
        }
        Intent intent = new Intent();
        intent.putExtra("param", JsonUils.jsonToStrong(ts));
        setResult(CAMERA_H5, intent);
        finish();
    }




    @Override
    public void onBeforestartPreview() {
        mBinding.ftdv.setCameraFacing(cameraDevFacing.endsWith("1")?CAMERA_FACING_FRONT:CAMERA_FACING_BACK);
    }

    @Override
    public void onCameraOpend() {

    }

    @Override
    public void onPreviewStart() {
//        mJTCameraView.setCameraFacing(cameraDevFacing.endsWith("1")?CAMERA_FACING_FRONT:CAMERA_FACING_BACK);
    }

    @Override
    public void onPreviewStop() {

    }

    @Override
    public void onShutter() {

    }

    @Override
    public void onCupture(Bitmap bitmap) {
        String bitmapPath = BitmapUtil.saveBitmap(CameraActivityForH5.this, bitmap);
        bitmaps.add(bitmapPath);
        if (bitmaps.size() == ts.size()) {
            TakePhoneCallBack();
            return;
        }
        Glide.with(CameraActivityForH5.this).load(ts.get(bitmaps.size()).getCover()).diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(mBinding.imgCover);
        readPictureDegree(new File(diskLruCache).getParentFile().getAbsolutePath() + "/1.jpg");
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private static final int MIN_DELAY_TIME = 500;  // 两次点击间隔不能少于400ms 往大调也可以
    private static long lastClickTime;

    public static boolean isFastClick() { //这个方法可以放到公共类里
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    @Override
    public void onCut(File file) {

    }

    @Override
    public void onCameraClosed() {

    }

    private int mFacing = CAMERA_FACING_FRONT;

    public class CameraClick{
        public void takePhoto(){
            if (isFastClick()){
                    return;
                }
                shootSound();
                mBinding.ftdv.takePicture();
        }
        public void finish(){
            CameraActivityForH5.this.finish();
        }
        public void imageSwitch(){
            if (mFacing == CAMERA_FACING_FRONT) {
                    mFacing = CAMERA_FACING_BACK;
                } else {
                    mFacing = CAMERA_FACING_FRONT;
                }
            mBinding.ftdv.setCameraFacing(mFacing);
        }
    }

//    @OnClick({R.id.image_switch, R.id.img_photo,R.id.finish})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.image_switch:
//                if (mFacing == CAMERA_FACING_FRONT) {
//                    mFacing = CAMERA_FACING_BACK;
//                } else {
//                    mFacing = CAMERA_FACING_FRONT;
//                }
//                mJTCameraView.setCameraFacing(mFacing);
//                break;
//            case R.id.img_photo:
//                if (isFastClick()){
//                    return;
//                }
//                shootSound();
//                mJTCameraView.takePicture();
//                break;
//            case R.id.finish:
//                finish();
//                break;
//        }
//    }


}
