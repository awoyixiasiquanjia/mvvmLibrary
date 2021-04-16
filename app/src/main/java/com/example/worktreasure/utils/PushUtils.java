package com.example.worktreasure.utils;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.commonlibrary.utils.NotificationUtils;
import com.example.commonlibrary.view.activity.LightWebActivity;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.LiveBus;
import com.example.mvvmlibrary.lib_mvvm.base.repository.BaseRepository;
import com.example.worktreasure.MyAppliction;
import com.example.worktreasure.R;

public class PushUtils  extends Thread{
    static PushUtils sSingleInstance;
    static PushRepository pushRepository;
//    private static final long TIMEOUT_MILLS = 5 * 60 * 1000L;
   private static final long TIMEOUT_MILLS = 4000L;
    private Handler mhandler;
    private PushUtils() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mhandler = new Handler(Looper.myLooper());
                Looper.loop();
            }
        }).start();
    }
    public static PushUtils getIntance(Application application) {
        if (sSingleInstance == null) {
            synchronized (PushUtils.class) {
                if (sSingleInstance == null) {
                    sSingleInstance = new PushUtils();
                     pushRepository = new PushRepository(application);
                }
            }
        }
        return sSingleInstance;
    }

    @Override
    public void run() {
        super.run();
//        Log.e("thread","thread=="+Thread.currentThread().getName());
//        pushRepository.getPushMsg();
        starLoopMsg();
        Intent intent = new Intent(MyAppliction.context, LightWebActivity.class);
        //   Intent intent = MyAppliction.context.getPackageManager().getLaunchIntentForPackage("com.example.worktreasure");
        NotificationUtils.createNotif(MyAppliction.context,R.mipmap.ic_launcher,"打开app","点击打开",intent,(int) (Math.random() * 100));
    }

    private void starLoopMsg() {
          mhandler.postDelayed(this,TIMEOUT_MILLS);
    }

}
