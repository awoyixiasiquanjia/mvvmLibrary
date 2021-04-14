package com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.json;

public interface JsonConverter {

    String toJson(Object value);

    <T> T fromJson(String json, Class<T> classOfT);
}
