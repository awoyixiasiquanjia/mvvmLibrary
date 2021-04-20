package com.example.worktreasure;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.worktreasure.utils.PushUtils;

public class MyAppliction extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        //开启调试模式（如果在InstantRun模式下运行，必须开启调试模式！ 不然点击不会跳转
        // 线上版本需要关闭，否则有安全风险）
        ARouter.openDebug();
        ARouter.init(this);
        //开始轮询
        PushUtils.getIntance(this).start();
        context = this;
    }


}
