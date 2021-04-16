package com.example.worktreasure.view.activity;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonlibrary.constant.ArouterUrl;
import com.example.mvvmlibrary.lib_mvvm.view.BaseMvvmActivity;
import com.example.mvvmlibrary.lib_mvvm.viewmodel.BaseViewModel;
import com.example.worktreasure.R;
import com.example.worktreasure.databinding.ActivityStartupBinding;
public class StartUpActivity extends BaseMvvmActivity<ActivityStartupBinding, BaseViewModel> {
   private  int countTime =3;
   private   Handler handler;
   private static final  int SPLASHCODDE =3;
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initView() {
         handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what==SPLASHCODDE){
                    if (countTime==0){
                        ARouter.getInstance().build(ArouterUrl.LIGHTWEBACURL)
                                .withString("common_uri","http://dgb-user-h5.dev.ennewi.cn")
                                .withString("common_title","测试页面")
                                .navigation();
                        finish();
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
        };
        mBinding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ArouterUrl.LIGHTWEBACURL)
                        .withString("common_uri","http://dgb-user-h5.dev.ennewi.cn")
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
}
