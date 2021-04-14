package com.example.commonlibrary.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.example.commonlibrary.constant.JsConstants;

import java.util.ArrayList;
/**
 * 处理handleMessage工具类
 */
public class HandleMsgUtil {
    public static String TOOBAR_COLOR = "";//修改的状态栏颜色
    private static String cameraDevFacing;
    public static String back_callback = "";

    /**
     * 处理handleMessage
     *
     * @param activity
     * @param msg
     */
    public static void handleMsg(final Activity activity, Message msg) {
//        if (activity != null && activity instanceof WebViewActivity) {
//            final WebViewActivity webViewActivity = (WebViewActivity) activity;
//            switch (msg.what) {
//                case JsConstants.SHOW_OPTION_MENU:
//                    webViewActivity.setRightBtnVisibility(View.VISIBLE);
//                    break;
//                case JsConstants.HIDE_OPTION_MENU:
//                    webViewActivity.setRightBtnVisibility(View.GONE);
//                    break;
//                case JsConstants.SHOW_WEB_VIEW_TOOLBAR:
//                    webViewActivity.hideTitleBar(View.VISIBLE);
//                    break;
//                case JsConstants.HIDE_WEB_VIEW_TOOLBAR:
//                    webViewActivity.hideTitleBar(View.GONE);
//                    break;
//                case JsConstants.SHOW_CLOSE_BTN:
//                    webViewActivity.setClosetVisibility(View.VISIBLE);
//                    break;
//                case JsConstants.HIDE_CLOSE_BTN:
//                    webViewActivity.setClosetVisibility(View.GONE);
//                    break;
//                case JsConstants.SET_OPTION_MENU:
//                    webViewActivity.setOptionMenu(msg.getData().getString("iconId"), msg.getData().getString("onclick"));
//                    break;
//                case JsConstants.SET_TITLE_MENU:
//                    webViewActivity.mTitle = msg.getData().getString("common_title");
//                    webViewActivity.getTVTopTitle().setText(webViewActivity.mTitle);
//                    break;
//                case JsConstants.GESTUREPASSWORD_QUESTIONS:
//                    webViewActivity.finish();
//                    String url = ShareDataUtil.getValueShareUrl(webViewActivity);
//                    IComeURLSchemeService.iComeURLSchemeServiceV1(webViewActivity, url, "");
//                    break;
//                case JsConstants.VALUE_CHAIN_PUSH_TO_ENERGIZE:
//                    webViewActivity.finish();
//                    EventBus.getDefault().post(new SwitchCardBean(0));
//                    break;
//                case JsConstants.STATUS_BAR_COLOR:
//                    webViewActivity.getCommonCl().setBackgroundColor(Color.parseColor(HandleMsgUtil.TOOBAR_COLOR));
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
//                        webViewActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.DRAG_FLAG_GLOBAL_URI_WRITE);
//                    }
//                    break;
//                case SYSTEM_SELECT_USER:
//                    final int action = msg.arg1;
//                    cameraDevFacing = (String) msg.obj;
//                    MyNewDialog myNewDialog = new MyNewDialog();
//                    ArrayList selectStirng = new ArrayList<>();
//                    selectStirng.add("拍照");
//                    selectStirng.add("手机相册");
//                    myNewDialog.showListDialog(webViewActivity, selectStirng, new MyNewDialog.DialotListViewListene() {
//                        @Override
//                        public void listViewListen(int position, String content) {
//                            if (position == 0) {
//                                //去拍照
//                                if (!MyPermissUtils.requestVideo(webViewActivity)) {
//                                    return;
//                                }
//                                Bundle bundle = new Bundle();
//                                bundle.putBoolean("isVideo", true);
//                                bundle.putString("cameraDevFacing", cameraDevFacing);
//                                Router.getInstance().startActivity(webViewActivity, bundle, RouterPath.APP_MYCAMER_ACTIVITY);
//                            } else if (position == 1) {
//                                //去图库
//                                SelectImageVideoUtils.openImageVideo(webViewActivity, 1, action);
//
//                            } else {
//
//                            }
//                        }
//                    });
//                    break;
//                case ANDROID_BACK:
//                    Object obj = msg.obj;
//                    if (obj != null && obj instanceof String) {
//                        back_callback = (String) msg.obj;
//                    }
//                    break;
//                case JsConstants.FULL_SCREEN:
//                    String fullScreen = (String) msg.obj;
//                    if (TextUtils.equals(fullScreen, "1")) {//全屏显示 隐藏状态栏+导航栏
//                        webViewActivity.hideActionBar();
//                        webViewActivity.hideTitleBar(View.GONE);
//                        webViewActivity.hideDivider(View.GONE);
//                        ImmersionBar.with(webViewActivity).init();
//
//                    } else {
//                        ImmersionBar.with(webViewActivity).destroy();
//                        webViewActivity.hideDivider(View.VISIBLE);
//                        webViewActivity.hideTitleBar(View.VISIBLE);
//                    }
//                default:
//
//                    break;
//            }
  //      }
    }
}
