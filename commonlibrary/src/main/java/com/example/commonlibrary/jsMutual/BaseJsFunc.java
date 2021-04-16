package com.example.commonlibrary.jsMutual;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.commonlibrary.R;
import com.example.commonlibrary.bean.AddrItemBean;
import com.example.commonlibrary.bean.JsBean;
import com.example.commonlibrary.constant.ArouterUrl;
import com.example.commonlibrary.constant.JsConstants;
import com.example.commonlibrary.utils.HandleMsgUtil;
import com.example.commonlibrary.utils.IComUtils;
import com.example.commonlibrary.utils.JsfuncUtlis;
import com.example.commonlibrary.utils.JsonUils;
import com.example.commonlibrary.utils.MyPermissUtils;
import com.example.commonlibrary.utils.URLSchemeService;
import com.example.mvvmlibrary.lib_mvvm.utils.PermissionUtils;
import com.example.mvvmlibrary.lib_mvvm.utils.ToastUtil;
import com.tencent.smtt.sdk.WebView;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.commonlibrary.constant.JsConstants.CAMERA_H5;
import static com.example.commonlibrary.constant.PerssionCode.CAMERACODE;

/**
 * 供HTML5调用的原生态（基础）功能 baseFunc
 *
 * @author by jhy on 2019/06/04
 */


public class BaseJsFunc {
    private static final String TAG = "BaseJsFunc";
    //webview 0 不支持缩放  1 支持缩放
    public static final String DISENABLE_ZOOM = "0";
    private Context mContext;
    private Handler mHandler;
    private WebView mWebview;
    private FragmentActivity mActivity;
    private ArrayList<AddrItemBean> addrItemBeans = new ArrayList<>();
    private int SCAN_QR_CODE = 101;


    public BaseJsFunc(FragmentActivity mActivity, Handler handler, WebView webViews) {
        this.mContext = mActivity.getBaseContext();
        this.mHandler = handler;
        this.mWebview = webViews;
        this.mActivity = mActivity;
    }

    /**
     * 回调数据给HTML端
     * String
     *
     * @param name  (functionName)
     * @param value
     */
    public void callBackHtml(final String name, final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mWebview != null) {
                    if (TextUtils.isEmpty(value)) {
                        debugLoadUrl("javascript:" + name + "()");
                        mWebview.loadUrl("javascript:" + name + "()");
                    } else {
                        debugLoadUrl("javascript:" + name + "('" + value + "')");
                        mWebview.loadUrl("javascript:" + name + "('" + value + "')");
                    }
                }
            }
        });
    }

    public void alert(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mWebview != null) {
                    debugLoadUrl("javascript:alert" + "('" + value + "')");
                    mWebview.loadUrl("javascript:alert" + "('" + value + "')");
                }
            }
        });
    }

    /**
     * 回调数据给HTML端
     * Object  JSONString
     *
     * @param name  (functionName)
     * @param value
     */
    public void callBackHtmlVaule(final String name, final Object value) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mWebview != null) {
                    debugLoadUrl(name + "("
                            + value + ")");
                    mWebview.evaluateJavascript(name + "("
                            + value + ")", null);
//                        mWebview.loadUrl("javascript:" + name + "("
//                                + value + ")");
                }
            }
        });
    }

    public void loadUrl(final String url) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mWebview != null) {
                    mWebview.loadUrl(url);
                }
            }
        });
    }


    /**
     * 供HTML5调用的原生态（基础）功能 总方法
     *
     * @author by jhy on 2019/06/04
     */
    @JavascriptInterface
    public void android_js(String mo) {
        final JsBean jsBean = JsonUils.parseStringToGson(mo, JsBean.class);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                process(jsBean);
            }
        });
    }

    private void process(final JsBean jsBean) {
        Log.e(getClass().getSimpleName(), JSON.toJSONString(jsBean));
        try {
            switch (jsBean.getAction()) {
                case JsConstants.WEB_VIEW.BACK:
                    JsfuncUtlis.back(mActivity, mWebview);
                    break;
                case JsConstants.WEB_VIEW.CLOSE:
                    JsfuncUtlis.close(mActivity);
                    break;
                case JsConstants.DEVICE.GET_NETWORK_TYPE:
                    callBackHtml(jsBean.getCallback(), IComUtils.getCurrentNetworkType(mContext));
                    break;
                case JsConstants.WEB_VIEW.SHOW_OPTION_MENU:
                    mHandler.sendEmptyMessage(JsConstants.SHOW_OPTION_MENU);
                    break;
                case JsConstants.WEB_VIEW.HIDE_OPTION_MENU:
                    mHandler.sendEmptyMessage(JsConstants.HIDE_OPTION_MENU);
                    break;
                case JsConstants.WEB_VIEW.SHOW_WEB_VIEW_TOOLBAR:
                    mHandler.sendEmptyMessage(JsConstants.SHOW_WEB_VIEW_TOOLBAR);
                    break;
                case JsConstants.WEB_VIEW.HIDE_WEB_VIEW_TOOLBAR:
                    mHandler.sendEmptyMessage(JsConstants.HIDE_WEB_VIEW_TOOLBAR);
                    break;
                case JsConstants.WEB_VIEW.SHOW_CLOSE_BTN:
                    mHandler.sendEmptyMessage(JsConstants.SHOW_CLOSE_BTN);
                    break;
                case JsConstants.WEB_VIEW.HIDE_CLOSE_BTN:
                    mHandler.sendEmptyMessage(JsConstants.HIDE_CLOSE_BTN);
                    break;
                case JsConstants.WEB_VIEW.TITLE_DOWN_ICON:
                    Message mesg = new Message();
                    Bundle bun = new Bundle();
                    bun.putString("common_title", jsBean.getParams().getTitle());  //往Bundle中存放数据
                    mesg.setData(bun);//mes利用Bundle传递数据
                    mesg.what = JsConstants.SET_TITLE_MENU;
                    mHandler.sendMessage(mesg);
                    break;
                case JsConstants.WEB_VIEW.PENDING_CLOSE:
                    mHandler.sendEmptyMessage(JsConstants.SET_PENDING_CLOSE);
                    break;
                case JsConstants.WEB_VIEW.ENABLE_ZOOM:
                    String enabled = jsBean.getParams().getEnabled();
                    mWebview.getSettings().setSupportZoom(!DISENABLE_ZOOM.equals(enabled));
                    break;
                case JsConstants.WEB_VIEW.ANDROID_BACK:
                    if (jsBean != null && jsBean.getParams()!=null){
                        Message backMessage = Message.obtain();
                        backMessage.obj = jsBean.getParams().getAndrodH5();
                        backMessage.what = JsConstants.ANDROID_BACK;
                        mHandler.sendMessage(backMessage);
                    }
                    break;
                case JsConstants.WEB_VIEW.STATUS_BAR_HEIGHT:
                    JSONObject jo = new JSONObject();
                    try {
                        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
                        int statusBarHeight =  mContext.getResources().getDimensionPixelSize(resourceId);
                        jo.put("code", "1");
                        jo.put("desc", "成功");
                        JSONObject data = new JSONObject();
                        data.put("statusBarHeight",statusBarHeight+"");
                        jo.put("data",data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        jo.put("code", "0");
                        jo.put("desc", "失败");
                        JSONObject data = new JSONObject();
                        data.put("statusBarHeight","");
                        jo.put("data",data);
                    }
                    callBackHtmlVaule(jsBean.getCallback(), jo);
                    break;
                case JsConstants.DEVICE.SELECT_PHOTOS:
                    JsConstants.callBackHtmlName = jsBean.getCallback();
                    Message mes = new Message();
                    try {
                        if (TextUtils.isEmpty(jsBean.getParams().toString())) {
                            mes.arg1 = 1;

                        } else {
                            if (!TextUtils.isEmpty(jsBean.getParams().getAmount())) {
                                mes.arg1 = Integer.parseInt(jsBean.getParams().getAmount());
                            } else {
                                mes.arg1 = 1;
                            }
                            if (TextUtils.isEmpty(jsBean.getParams().getCameraDevFacing())) {
                                mes.obj = "0";
                            } else {
                                mes.obj = jsBean.getParams().getCameraDevFacing();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mes.what = JsConstants.SELECT_PHOTOS;
                    mHandler.sendMessage(mes);
                    break;
                case JsConstants.DEVICE.SELECT_PICTURES:
//                    int amount;
//                    try {
//                        if (TextUtils.isEmpty(jsBean.getParams().toString())) {
//                            amount = 1;
//                        } else {
//                            if (!TextUtils.isEmpty(jsBean.getParams().getAmount())) {
//                                amount = Integer.parseInt(jsBean.getParams().getAmount());
//                            } else {
//                                amount = 1;
//                            }
//
//                        }
//                        SelectImageVideoUtils.openImageVideo(mActivity, 1, amount);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    JsConstants.callBackHtmlName = jsBean.getCallback();
                    break;
                case JsConstants.DEVICE.CALL:
                    JsfuncUtlis.goCallPhone(mContext, jsBean.getParams().getTel());
                    break;
                case JsConstants.SYSTEM.TAKEPHONE:
                    //检测权限
                    JsConstants.callBackHtmlName = jsBean.getCallback();
                    final String json = JsonUils.jsonToStrong(jsBean.getParams());
                    getPerssion(json, jsBean);
                    break;
                case JsConstants.SYSTEM.BACK_EVENT_CLOSE:
                    Message closeMessage = new Message();
                    closeMessage.arg1 = jsBean.getParams().getClose();
                    closeMessage.what = JsConstants.BACK_EVENT_CLOAW;
                    mHandler.sendMessage(closeMessage);
                    break;
                case JsConstants.SYSTEM.SYSTEM_WEBVIEW:
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(jsBean.getParams().getUrl());
                    intent.setData(content_url);
                    mContext.startActivity(intent);
                    break;
                case JsConstants.VALUE_CHAIN.VALUECHAIN_COMMENT:
                    Message commentMessage = new Message();
                    commentMessage.obj = jsBean;
                    commentMessage.what = JsConstants.VALUE_CHAIN_COMMENT;
                    mHandler.sendMessage(commentMessage);
                    break;
                case JsConstants.VALUE_CHAIN.VALUECHAIN_SHARETOIM:
                    Message shareMessage = new Message();
                    shareMessage.obj = jsBean;
                    shareMessage.what = JsConstants.VALUE_CHAIN_SHARETOIM;
                    mHandler.sendMessage(shareMessage);
                    break;
                case JsConstants.VALUE_CHAIN.VALUECHAIN_PUSHTOENERGIZE:
                    mHandler.sendEmptyMessage(JsConstants.VALUE_CHAIN_PUSH_TO_ENERGIZE);
                    break;
                case JsConstants.VALUE_CHAIN.VALUECHAIN_GESTUREDELETE:
                    Message alertMessage = new Message();
                    alertMessage.obj = jsBean;
                    alertMessage.what = JsConstants.VALUE_CHAIN_ALERT_DIALOG;
                    mHandler.sendMessage(alertMessage);
                    break;
                case JsConstants.QUESTIONS.GESTUREPASSWORD_QUESTIONS:
                    if (mHandler != null) {
                        mHandler.sendEmptyMessage(JsConstants.GESTUREPASSWORD_QUESTIONS);
                    }
                    break;
                case JsConstants.SYSTEM.LOAD_URL_WITH_REFER:
                    Map<String, String> header = new HashMap<>();
                    header.put("Referer", jsBean.getParams().getRefer());
                    mWebview.loadUrl(jsBean.getParams().getUrl(), header);
                    break;
                case JsConstants.SYSTEM.SHOW_TOAST:
                    String toastTest = jsBean.getParams().getText();
                    if (!TextUtils.isEmpty(toastTest))
                        ToastUtil.showToast(mContext.getApplicationContext(),toastTest);
                    break;
                case JsConstants.WEB_VIEW.FULL_SCREEN:
                    Message fullMessage = new Message();
                    fullMessage.obj = jsBean.getParams().getFullScreen();
                    fullMessage.what = JsConstants.FULL_SCREEN;
                    mHandler.sendMessage(fullMessage);
                    break;
                case JsConstants.WEB_VIEW.STATUS_BAR_COLOR:
                    Message statusBarColor = new Message();
                    statusBarColor.what = JsConstants.STATUS_BAR_COLOR;
                    HandleMsgUtil.TOOBAR_COLOR = jsBean.getParams().getColor();//要修改的状态栏颜色
                    mHandler.sendMessage(statusBarColor);
                    break;
                case JsConstants.VALUE_CHAIN.VALUECHAIN_PERMISSION:
//                    int permission = SharedPreferencesUtils.getIntShareData(mContext, "hasPermisstoAccess", 0);
//                    callBackHtml(jsBean.getCallback(), String.valueOf(permission));
                    break;
                case JsConstants.AMAP.LOCATION:
                    if (jsBean !=null){
                        getLocation(jsBean);
                    }
                    break;
                case JsConstants.AMAP.OPENMAP:
                    //打开地图-选取位置
                    if (jsBean !=null){
                        JsConstants.callBackHtmlName = jsBean.getCallback();
                        openMap(jsBean);
                    }
                    break;
                case JsConstants.SYSTEM.SELECT_ICOMEORG:
                    JsConstants.callBackHtmlName = jsBean.getCallback();
                    String chooseElements="";
                    String disableElements="";
                    if (jsBean.getParams().getChooseElements()!=null){
                        chooseElements = com.alibaba.fastjson.JSONObject.toJSONString(jsBean.getParams().getChooseElements());
                    }
                    if (jsBean.getParams().getDisableElements()!=null){
                        disableElements = com.alibaba.fastjson.JSONObject.toJSONString(jsBean.getParams().getDisableElements());
                    }
                    String selectIcomeorgUrl = "icomek://addressbook/org?singleMode="+jsBean.getParams().getSingleMode()+"&chooseElements="+chooseElements+"&disableElements="+disableElements;
                    URLSchemeService.iComeURLSchemeServiceV1(mActivity,selectIcomeorgUrl,null);
                    break;
                case JsConstants.SYSTEM.OPEN_WEB_VIEW:
                    String url = jsBean.getParams().getUrl();
                    String title = jsBean.getParams().getTitle();
                    if (!TextUtils.isEmpty(url)) {
                        ARouter.getInstance().build(ArouterUrl.LIGHTWEBACURL)
                                .withString("common_uri",url)
                                .withString("common_title",title)
                                .navigation();
                    }

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    /**
     * 打开地图-选取位置
     *
     * @param jsBean
     */
    private void openMap(JsBean jsBean) {
//        Bundle bundle = new Bundle();
//        bundle.putBoolean("isSelectSearch",true);
//        bundle.putBoolean("isSearchByCity",true);
//        Router.getInstance().startActivityForResult(mActivity, SELECT_MAP, bundle, RouterPath.APP_SELECEMAP_ACTVIITY);
    }

    /**
     * 获取位置
     */
    private void getLocation(final JsBean jsBean) {
//        new RxPermissions(mActivity)
//                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            final LocationUtil instance = LocationUtil.getInstance(mContext);
//                            instance.setOnceLocation(true);
//                            instance.activate(new LocationFunc.OnLocationChangedListener() {
//                                @Override
//                                public void onLocationChanged(AMapLocation location) {
//                                    callBackHtmlVaule(jsBean.getCallback(), Location2JsonUtil.toJson(location));
//                                    instance.deactivate();
//                                }
//
//                                @Override
//                                public void onLocationError(String msg) {
//                                    callBackHtmlVaule(jsBean.getCallback(), Location2JsonUtil.toJson(null));
//                                    instance.deactivate();
//                                }
//                            });
//                        } else {
//                            ToastUtil.showBottomToast(mContext.getApplicationContext(),"请打开定位权限");
//                        }
//                    }
//                });
    }


    private void getPerssion(final String json, final JsBean jsBean) {
        final String []   permissions  =  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        PermissionUtils.checkMorePermissions(mContext, permissions, new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
                ARouter.getInstance().build(ArouterUrl.CAMERFORH5)
                        .withString("params",json)
                        .navigation(mActivity,CAMERA_H5);
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
                PermissionUtils.requestMorePermissions(mActivity, permissions, CAMERACODE);
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                //用户点击了不再询问
                PermissionUtils.requestMorePermissions(mActivity, permissions, CAMERACODE);
            }
        });

//        RxPermissions rxPermissions = new RxPermissions(mActivity);
//        Disposable param = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA
//        ).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                if (aBoolean) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("params", json);
//                    Router.getInstance().startActivityForResult(mActivity, CAMERA_H5, bundle, RouterPath.CAMERA_ACTIVITY_FOR_H5_ACTIVITY);
//                } else {
//                    ToastUtil.showToast(mActivity.getApplicationContext(), "请到设置-权限管理中开启");
//                }
//
//            }
//
//        });


    }

    public String debugLoadUrl(String jsFun) {
        boolean debug = true;
        if (debug) {
            Log.e(getClass().getSimpleName(), String.format("%s", jsFun));
        }
        return jsFun;
    }
}
