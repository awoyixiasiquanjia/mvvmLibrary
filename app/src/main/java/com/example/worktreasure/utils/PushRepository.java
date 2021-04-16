package com.example.worktreasure.utils;

import android.app.Application;
import com.example.mvvmlibrary.lib_mvvm.base.repository.BaseRepository;
import com.example.mvvmlibrary.lib_mvvm.net.model.HttpResult;
import com.example.mvvmlibrary.lib_mvvm.utils.ParamsBuilder;
import com.example.worktreasure.bean.PushBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class PushRepository extends BaseRepository<PushRepository.APIServer> {
    @Override
    protected Class<APIServer> getServerClass() {
        return APIServer.class;
    }

    public PushRepository(Application application) {
        super(application);
    }

    public interface APIServer {
        @FormUrlEncoded
        @POST("dgb-demand/innerMsg/list")
        Observable<PushBean> getPush( @FieldMap Map<String, Integer> map, @Header("ennUnifiedCsrfToken") String ennUnifiedCsrfToken ,@Header("ennUnifiedAuthorization") String ennUnifiedAuthorization);
    }

    public void getPushMsg(String ennUnifiedCsrfToken,String ennUnifiedAuthorizatio) {
        Map<String, Integer> map = new HashMap<>();
        map.put("status",1);
        map.put("pageNum",1);
        map.put("pageSize",1);
        apiServer.getPush(map,ennUnifiedCsrfToken,ennUnifiedAuthorizatio).subscribe(new Observer<PushBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PushBean pushBean) {


            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }




}
