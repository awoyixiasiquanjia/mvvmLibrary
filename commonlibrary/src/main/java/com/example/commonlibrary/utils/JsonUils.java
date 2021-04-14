package com.example.commonlibrary.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonUils {
    /**
     * 将对象转化字符串
     * @param o
     * @return
     */
    public static String jsonToStrong(Object o){
        Gson gson=new Gson();
        return gson.toJson(o);
    }

    /**
     * 将对象转化字符串
     * @param o
     * @return
     */
    public static String jsonToJSonString(Object o){
        Gson gson=new Gson();
        return gson.toJson(o);
    }

    /**
      * @explain 将list转jsonArray
      * @author MR.su.
      * @creat 2019/9/17
      **/
    public static JSONArray listToJSonArray(List o){
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(o));
        return array;
    }


    /**
     * 转化为实体类
     * @param jsonData
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseStringToGson(String jsonData, Class<T> type) {
        T result = null;
        if (!TextUtils.isEmpty(jsonData)) {
            Gson gson = new GsonBuilder().create();
            try {
                result = gson.fromJson(jsonData, type);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (gson != null) {
                    gson = null;
                }
            }
        }
        return result;
    }

    /**
     * 转化为List列表
     * @param jsonData   json字符串
     * @param type
     * @param <T> list数据
     * @return
     */
    public static <T> List<T> parseStrinToList(String jsonData, Class<T> type) {
        List<T> result = null;
        if (!TextUtils.isEmpty(jsonData)) {
            Gson gson = new GsonBuilder().create();
            try {
                JsonParser parser = new JsonParser();
                JsonArray Jarray = parser.parse(jsonData).getAsJsonArray();
                if (Jarray != null) {
                    result = new ArrayList<>();
                    for (JsonElement obj : Jarray) {
                        try {
                            T cse = gson.fromJson(obj, type);
                            result.add(cse);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (gson != null) {
                    gson = null;
                }
            }
        }
        return result;
    }

}
