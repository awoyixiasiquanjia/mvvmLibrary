package com.example.mvvmlibrary.lib_mvvm.base.livedata;

import com.example.mvvmlibrary.lib_mvvm.base.livedata.core.Config;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.core.LiveEventBusCore;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.core.Observable;

public final class LiveBus {

    private static volatile LiveBus instance;


    public static LiveBus getDefault() {
        if (instance == null) {
            synchronized (LiveBus.class) {
                if (instance == null) {
                    instance = new LiveBus();
                }
            }
        }
        return instance;
    }


    /**
     * get observable by key with type
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public  <T> Observable<T> get(String key, Class<T> type) {
        return LiveEventBusCore.get().with(key, "", type);
    }


    public  <T> Observable<T> get(String key, String tag, Class<T> type) {
        return LiveEventBusCore.get().with(key, tag, type);
    }


    /**
     * get observable by key
     *
     * @param key
     * @return
     */
    public Observable<Object> get(String key) {
        return get(key, Object.class);
    }

    public Observable<Object> get(String key,String tag) {
        return get(key,tag, Object.class);
    }

    /**
     * use the inner class Config to set params first of all, call config to get the Config instance
     * then, call the method of Config to config LiveEventBus call this method in
     * Application.onCreate
     */
    public Config config() {
        return LiveEventBusCore.get().config();
    }


    public <T> void postEvent(String key, T value) {

        LiveBus.getDefault().get(key).post(value);
    }

    public <T> void postEvent(String key,String tag, T value) {

        LiveBus.getDefault().get(key,tag).post(value);
    }
}
