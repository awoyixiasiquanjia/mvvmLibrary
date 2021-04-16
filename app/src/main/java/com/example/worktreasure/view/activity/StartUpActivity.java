package com.example.worktreasure.view.activity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.provider.Settings;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonlibrary.constant.ArouterUrl;
import com.example.mvvmlibrary.lib_mvvm.view.BaseMvvmActivity;
import com.example.mvvmlibrary.lib_mvvm.viewmodel.BaseViewModel;
import com.example.worktreasure.R;
import com.example.worktreasure.databinding.ActivityStartupBinding;
public class StartUpActivity extends BaseMvvmActivity<ActivityStartupBinding, BaseViewModel> {
   private  int countTime =3;
   @SuppressLint("HandlerLeak")
   private final  Handler handler = new Handler(){
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
           if (msg.what==SPLASHCODDE){
               if (countTime==0){
                   requestPermission();
               }else{
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           handler.sendEmptyMessage(3);
                           mBinding.tvSkip.setVisibility(View.VISIBLE);
                           mBinding.tvSkip.setText("跳过"+countTime+"S");
                           countTime --;
                       }
                   },1000);
               }
           }
       }
   };;
   private static final  int SPLASHCODDE =3;
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initView() {
        mBinding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ArouterUrl.LIGHTWEBACURL)
//                .withString("common_uri","http://dgb-user-h5.dev.ennewi.cn")
                        .withString("common_uri","http://10.2.153.37:18081/")
                        .withString("common_title","测试页面")
                        .navigation();
                finish();
            }
        });
        handler.sendEmptyMessage(SPLASHCODDE);
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return null;
    }

    @Override
    protected void dataObserver() {

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_startup;
    }

    public void requestPermission() {
        //权限检查
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }else{
            jumpLightWeb();
        }
    }
    private AlertDialog mDialog;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                //已授权
                if (grantResults[i] == 0) {
                    jumpLightWeb();
                    continue;
                }

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                    //选择禁止
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("授权");
                    builder.setMessage("需要允许授权才可使用");
                    builder.setPositiveButton("去允许", (dialog, id) -> {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                        ActivityCompat.requestPermissions(StartUpActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    });
                    mDialog = builder.create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                } else {
                    //选择禁止并勾选禁止后不再询问
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("授权");
                    builder.setMessage("需要允许授权才可使用");
                    builder.setPositiveButton("去授权", (dialog, id) -> {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        //调起应用设置页面
                        startActivityForResult(intent, 2);
                    });
                    mDialog = builder.create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
            }
    }
    }

    private void jumpLightWeb() {
        ARouter.getInstance().build(ArouterUrl.LIGHTWEBACURL)
//                .withString("common_uri","http://dgb-user-h5.dev.ennewi.cn")
                .withString("common_uri","http://10.2.153.37:18081/")
                .withString("common_title","测试页面")
                .navigation();
        finish();
    }
}
