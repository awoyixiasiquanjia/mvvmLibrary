package com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonConverter implements JsonConverter {

    private Gson gson = new Gson();

    @Override
    public String toJson(Object value) {
        return gson.toJson(value);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }
}
