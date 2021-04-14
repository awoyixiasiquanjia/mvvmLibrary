package com.example.commonlibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.commonlibrary.BuildConfig;
import com.example.commonlibrary.event.EventBase;

import java.util.ArrayList;
import java.util.List;

public class URLSchemeService {
    protected static Context context;
    protected static String url;
    /**
     * 是否隐藏头原生头-默认显示, 1 隐藏
     */
    private static String isHeader;

    /**
     * 是否强制横屏
     */
    private static String isLandScape;

    /**
     * 根据url类型跳转不同的业务场景,对WebView进行了重构,目前只对事业开放,测试完成后,再统一替换
     *
     * @param context
     * @param url
     * @param title
     */
    public static void iComeURLSchemeServiceV1(Context context, final String url, String title) {
        setContext(context);
        setUrl(url);
        if (!TextUtils.isEmpty(url)) {
           if (url.contains("work-webview")) {
               //跳转网页


            }
        }
    }


    /**
     * 拼接ticket并跳转
     *
     * @param context
     * @param url
     * @param title
     * @param web
     */
    private static void reorganizationUrlAndJump(Context context, String url, String title, String web) {
        if (!TextUtils.isEmpty(url)) {
            Bundle bundle = new Bundle();
            bundle.putString("isHeader", isHeader);
            bundle.putString("isLandScape", isLandScape);
            bundle.putString("common_title", title);
            //跳转到webView页面


           // Router.getInstance().startActivity(context, bundle, web);
            isHeader = null;
            isLandScape = null;
        }
    }




    public static String buildurl(String url) {
        if (TextUtils.isEmpty(url)){
            return url;
        }
        if (url.contains("icome-webview=v2")) {
            if (url.contains("icome-webview=v2&")) {
                return url.replace("icome-webview=v2&", ""); //得到新的字符串
            } else if (url.contains("icome-webview=v2?")) {
                return url.replace("icome-webview=v2?", ""); //得到新的字符串
            } else {
                return url.replace("icome-webview=v2&", ""); //得到新的字符串
            }
        } else if (url.contains("icome-webview=v1")) {
            if (url.contains("icome-webview=v1&")) {
                return url.replace("icome-webview=v1&", ""); //得到新的字符串
            } else if (url.contains("icome-webview=v1?")) {
                return url.replace("icome-webview=v1?", ""); //得到新的字符串
            } else {
                return url.replace("icome-webview=v1&", ""); //得到新的字符串
            }
        } else {
            return url;
        }
    }

    public static void onCallback(EventBase bean) {
//        if (null != bean && null != getContext() && null != getUrl()) {
//            bean.setUrl(getUrl());
//            int what = bean.getWhat();
//            int arg1 = bean.getArg1();
//            int arg2 = bean.getArg2();
//            String msg = bean.getMsg();
//            Object obj = bean.getObj();
//            String path = bean.getPath();
//            String videoUrl = bean.getVideoUrl();
//            if (what == RouterPath.PICKER_ACTIVITY.hashCode()) {
//                List<String> imgList = new ArrayList<>();
//                //noinspection ConditionCoveredByFurtherCondition
//                if (null != obj && obj instanceof List) {
//                    //noinspection unchecked
//                    imgList = (List<String>) obj;
//                }
//                if (bean.isClip()){
//                    //跳转到裁剪页面
//                    jumpClipActivity(imgList.get(0));
//                }else{
//                    if (bean.isRn()) {// RN
//                        JumpUtil.jumpCallback(bean.getRnCallback(), RNCallBackUtil.getCallBackPhotoMap(imgList));
//                    } else {
//                        // Native
//                    }
//                    reset();
//                }
//            } else if (what  == RouterPath.APP_MYCAMER_ACTIVITY.hashCode()) {
//                if (bean.isClip()){
//                    //跳转到裁剪页面
//                    jumpClipActivity(path);
//                }else{
//                    if (bean.isRn()) {// RN
//                        JumpUtil.jumpCallback(bean.getRnCallback(), RNCallBackUtil.getCallBackPhotoMap(path));
//                    } else {
//                        // Native
//                    }
//                    reset();
//                }
//            }else if(what  == RouterPath.CLIP_IMAGE_ACTIVITY.hashCode()){
//                //裁剪图片返回
//                if (bean.isRn()) {// RN
//                    JumpUtil.jumpCallback(bean.getRnCallback(),  RNCallBackUtil.getCallBackPhotoMap(path));
//                } else {
//                    // Native
//                }
//                reset();
//            } else if (what == RouterPath.COMMON_WORKING_TEAM_ACTIVITY.hashCode()) {
//                // 工作项
//                if (bean.isRn()) { // RN
//                    JumpUtil.jumpCallback(bean.getRnCallback(), RNCallBackUtil.getCallbackWithObject(obj));
//                } else {
//                    // Native
//                }
//                reset();
//            }else {
//                // nothing to do
//            }
//        }

    }

    private static void reset() {
        setContext(null);
        setUrl(null);
    }


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
      URLSchemeService.context = context;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        URLSchemeService.url = url;
    }


}
