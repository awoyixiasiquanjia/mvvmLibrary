package com.example.mvvmlibrary.lib_mvvm.net;
import com.example.mvvmlibrary.lib_mvvm.net.Interceptor.InterceptorUtil;

import java.util.concurrent.TimeUnit;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    //超时时间
    public static final int TIMEOUT = 10;

    private static class RetrofitBuilderHolder {
        private final static RetrofitBuilder INSTANCE = new RetrofitBuilder();
    }
    private RetrofitBuilder() {
    }


    public static Retrofit.Builder getInstance(String baseUrl) {
        return RetrofitBuilderHolder.INSTANCE.get(baseUrl);
    }


    public Retrofit.Builder get(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
             .addInterceptor(InterceptorUtil.LogInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);
        OkHttpClient okHttpClient = builder.build();
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create());
        return retrofit;
    }

}
