package com.example.commonlibrary.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.tencent.smtt.sdk.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/***
 *
 * webview 调用原始方法
 * **/

public class JsfuncUtlis {

    public static void back(final Activity mac, final WebView mWebView){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (!mWebView.canGoBack()) {
                    mac.finish();
                } else {
                    mWebView.goBack();
                }
            }
        });

    }

    public static void close(Activity mac){
        mac.finish();
    }

    public static void goCallPhone(Context context, String phone) {
        if (!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                    + phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    } //获取手机版本型号信息
    public static String getDevInfo() {
        return "Android " + Build.VERSION.RELEASE + ";" + Build.BRAND + ";" + Build.MODEL;
    }

    /**
     * 获取剪切板内容
     *
     * @param context
     * @return
     */
    public static String getClipboardContentTest(Context context) {
        try {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (cm != null) {
                ClipData data = cm.getPrimaryClip();
                if (data != null) {
                    ClipData.Item item = data.getItemAt(0);
                    if (item != null && item.getText() != null) {
                        //部分手机可能会在剪切板没有相关的文本内容返回null
                        return item.getText().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 是否在iCome内
     *
     * @return
     */
    public static JSONObject isInICome() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", "1");
            jsonObject.put("desc", "成功");
            JSONObject data = new JSONObject();
            data.put("isCome", "1");
            jsonObject.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
