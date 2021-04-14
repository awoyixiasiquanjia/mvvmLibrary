package com.example.mvvmlibrary.lib_mvvm.viewmodel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import com.example.mvvmlibrary.lib_mvvm.base.repository.BaseRepository;
import com.example.mvvmlibrary.lib_mvvm.content.ApplicationContextProvider;

public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel implements
    LifecycleObserver {
    protected T repository;
//    public BaseViewModel(T repository) {
//        //如果没有穿appliction的话，就需要在初始话库的收传入appliction ，暂时不用
//        super(ApplicationContextProvider.getApplication());
//        this.repository = repository;
//    }

    public BaseViewModel(@NonNull Application application, T repository) {
        super(application);
        this.repository = repository;
    }

    public T getRepository() {

        if (null == repository) {

            throw new RuntimeException("请创建ViewModel和Repository的工厂类");
        }
        return repository;
    }


    @Override protected void onCleared() {
        super.onCleared();

        if (null != repository) {

            repository.onCleared();
        }
    }

}
