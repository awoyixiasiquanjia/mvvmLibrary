package com.example.mvvmlibrary.lib_mvvm.content;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class ApplicationContextProvider{
    @SuppressLint("StaticFieldLeak")
    static Context mContext;
    public static  void setmContext(Context context){
        mContext = context;
    }


    public static Application getApplication() {
        if (mContext==null){
            new RuntimeException("请初始化");
        }
         return (Application) mContext;
    }
}
