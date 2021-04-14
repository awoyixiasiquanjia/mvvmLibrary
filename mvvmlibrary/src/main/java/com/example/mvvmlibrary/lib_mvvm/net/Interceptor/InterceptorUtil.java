package com.example.mvvmlibrary.lib_mvvm.net.Interceptor;
import android.util.Log;
import java.nio.charset.Charset;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author yemao
 * @date 2017/4/9
 * @description 拦截器工具类!
 */

public class InterceptorUtil {
    public static String TAG = "----";
    public final static Charset UTF8 = Charset.forName("UTF-8");
    //日志拦截器
        public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

}
