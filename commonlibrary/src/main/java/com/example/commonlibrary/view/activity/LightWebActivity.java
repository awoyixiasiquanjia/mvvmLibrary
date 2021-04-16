package com.example.commonlibrary.view.activity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.commonlibrary.R;
import com.example.commonlibrary.bean.ParamBean;
import com.example.commonlibrary.constant.ArouterUrl;
import com.example.commonlibrary.constant.JsConstants;
import com.example.commonlibrary.databinding.ActivityLightwebviewBinding;
import com.example.commonlibrary.jsMutual.BaseJsFunc;
import com.example.commonlibrary.utils.BitmapUtil;
import com.example.commonlibrary.utils.HandleMsgUtil;
import com.example.commonlibrary.utils.JsonUils;
import com.example.commonlibrary.utils.URLSchemeService;
import com.example.commonlibrary.utils.UiUtils;
import com.example.mvvmlibrary.lib_mvvm.utils.ToastUtil;
import com.example.mvvmlibrary.lib_mvvm.view.BaseMvvmActivity;
import com.example.mvvmlibrary.lib_mvvm.viewmodel.BaseViewModel;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.commonlibrary.constant.JsConstants.CAMERA_H5;
import static com.example.commonlibrary.constant.PerssionCode.CAMERACODE;

@Route(path = ArouterUrl.LIGHTWEBACURL)
public class LightWebActivity extends BaseMvvmActivity<ActivityLightwebviewBinding, BaseViewModel> implements View.OnClickListener, View.OnLongClickListener {
    private static final String HTTP = "http://";
    private static final String WWW = "www.";
    private static final String SELECTORIMG = "image";
    private static final String VIDEO = "video";
    public static final String TITLE_BAR_GONE = "1";
    private BaseJsFunc baseJsFunc;
    private WebView mWebView;
    private LinearLayout llRightClose;
    private ArrayList<String> pathList = new ArrayList<>();
    protected ImageView mIvTitleRightBtn;
    private TextView TVTopTitle;
    private static final String RIGHT_BAR_ADD = "1";
    private static final String RIGHT_BAR_MENU = "2";
    private static final String RIGHT_BAR_SEARCH = "3";
    private static final String RIGHT_BAR_LOCAL = "4";
    private static final int REQUEST_FILE_CHOOSER = 6;
    public static final int COPY_LINK = 1;//复制链接
    public static final int OPEN_IN_BROWSER = 2;//浏览器中打开
    public static final String WEB_URL = "common_uri";
    private String mUrl;
    private String mTitle;
    private WebSettings webSetting;
    public Toolbar LLTitleBar;
    private List<String> encodeList = new ArrayList<>();
    private StringBuffer stringBuffer = new StringBuffer();
    private  int SCAN_QR_CODE =101;
    private ArrayList<String> selectStirng;
    private  int action;
    private String cameraDevFacing;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private boolean isClose;//关闭当前页面(如果此页面调用物理返回键无法退出页面)
    public static String android_back = "";
    private boolean mIsDestroy;
    private View mCustomView;
    private FrameLayout mFullscreenContainer;
    private IX5WebChromeClient.CustomViewCallback mCustomViewCallback;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case 3:
                    hideTitleBar(VISIBLE);
                    break;
                case 4:
                    hideTitleBar(GONE);
                    break;
                case 5:
                    setClosetVisibility(VISIBLE);
                    break;
                case 6:
                    setClosetVisibility(GONE);
                    break;
                case JsConstants.STATUS_BAR_COLOR:
                    ll.setBackgroundColor(Color.parseColor(HandleMsgUtil.TOOBAR_COLOR));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.DRAG_FLAG_GLOBAL_URI_WRITE);
                    }
                    break;
                case JsConstants.BACK_EVENT_CLOAW:
                    if (msg.arg1 == 1){
                        isClose = true;
                    }else{
                        isClose = false;
                    }
                    break;
            }

        }
    };

    private LinearLayout ll;
    private String isHeader;
    private String isLandScape;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.left_btn_bg) {
            if (!isClose){
                if (mWebView.canGoBack()) {
                    //当WebView不是处于第一页面时，返回上一个页面
                    webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    mWebView.goBack();
                } else {
                    //当WebView处于第一页面时,直接退出
                    finish();
                }
            }else{
                finish();
            }
        } else if (v.getId() == R.id.ll_right_close) {
            LightWebActivity.this.finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == CAMERA_H5 && (data != null)) {
            Disposable subscribe = Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    //回调h5参数
                    String param = data.getStringExtra("param");
                    List<ParamBean> params = JsonUils.parseStrinToList(param, ParamBean.class);
                    for (ParamBean str : params) {
                        Bitmap bitmap = BitmapFactory.decodeFile(str.getImage());
                        String base64 = "data:image/jpeg;base64," + BitmapUtil.bitmapToBase64(bitmap);
                        str.setImage(base64);
                    }
                    JSONArray objects = JsonUils.listToJSonArray(params);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("param", objects);
                    String jsonString = jsonObject.toJSONString();
                    e.onNext(jsonString);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String param) throws Exception {
                            baseJsFunc.callBackHtmlVaule(JsConstants.callBackHtmlName, param);
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERACODE){
            ToastUtil.showToast(this.getApplicationContext(),"请打开相关权限！");
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        UiUtils.setStatusBar(this);
        isHeader = extras.getString("isHeader");
        isLandScape=extras.getString("isLandScape");
        if(TextUtils.equals(isLandScape,TITLE_BAR_GONE)){//横屏下不展示title
            isHeader="1";
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        mUrl = URLSchemeService.buildurl(extras.getString("common_uri"));
        if (!TextUtils.isEmpty(mUrl) && mUrl.startsWith(WWW)) {
            mUrl = HTTP + mUrl;
        }
        mTitle = extras.getString("common_title");
    }

    private void initWebViewSettings() {
        webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(false);//支持缩放
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(false);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setUserAgentString(webSetting.getUserAgentString()+" icomesdkv2");
        webSetting.setBlockNetworkImage(false);//解决图片不显示
        webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * 初始化js调用本地的方法 basefunc.goNative
     */
    @SuppressLint("JavascriptInterface")
    private void initJSExeLocaMethod() {
        // --------html调用我们本地方法-----------
        baseJsFunc = new BaseJsFunc(LightWebActivity.this, mHandler, mWebView);
        mWebView.addJavascriptInterface(baseJsFunc, "baseJsFunc");
    }

    private void initWebviewClient() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView webView, String s) {
                super.onLoadResource(webView, s);
                if (!TextUtils.isEmpty(mTitle)) {
                    TVTopTitle.setText(mTitle);
                } else {
                    LightWebActivity.this.TVTopTitle.setText(mTitle != null && !TextUtils.isEmpty(mTitle) ? mTitle : webView.getTitle());
                }

            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                mUrl = s;
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return;
                }
                Log.e("TAG", "H5加载失败");
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (webResourceRequest.isForMainFrame()) {
                    Log.e("TAG", "H5加载失败,请重试");
                }
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                WebView.HitTestResult hitTestResult = webView.getHitTestResult();
                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                    webView.loadUrl(url);
                    return false;
                }
                try {
                    if (url.startsWith("weixin://wap/pay")) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        webView.getContext().startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.shouldOverrideUrlLoading(webView, url);

            }

            @Override
            public void onPageFinished(WebView webView, String s) {
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String s, GeolocationPermissionsCallback geolocationPermissionsCallback) {
                super.onGeolocationPermissionsShowPrompt(s, geolocationPermissionsCallback);
            }

            @Override
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> valueCallback,
                                             FileChooserParams fileChooserParams) {
                mUploadCallbackAboveL = valueCallback;
                String[] acceptTypes = fileChooserParams.getAcceptTypes(); //只取一个
                String acceptType=  acceptTypes[0];
                if (acceptType.contains(SELECTORIMG)){
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "image Browser"), REQUEST_FILE_CHOOSER);
                }else if (acceptType.contains(VIDEO)){
                   // startVideo();
                }else{
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    startActivityForResult(Intent.createChooser(i, "File Browser"), REQUEST_FILE_CHOOSER);
                }
                return true;

            }

            @Override
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                  super.onShowCustomView(view, customViewCallback);
                  showCustomView(view, customViewCallback);
            }

            @Override
            public void onHideCustomView() {
                 hideCustomView();
            }

        }
        );

    }
    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
        if (mIsDestroy) {
            return;
        }
        // if a view already exists then immediately terminate the new one
        if (mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        mFullscreenContainer = new FullscreenHolder(this);
        mFullscreenContainer.addView(view, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        decor.addView(mFullscreenContainer, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mCustomView = view;
        setFullScreen(true, this);
        mCustomViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (mCustomView == null || mIsDestroy) {
            return;
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setFullScreen(false, this);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(mFullscreenContainer);
        mFullscreenContainer = null;
        mCustomView = null;
        mCustomViewCallback.onCustomViewHidden();
    }

    private void setFullScreen(boolean fullScreen, Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        if (fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(attrs);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attrs);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }


    private void initHardwareAccelerate() {
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    private void setClosetVisibility(int visibility) {
        llRightClose.setVisibility(visibility);
    }

    public void hideTitleBar(int visibility) {
        LLTitleBar.setVisibility(visibility);
    }

    @Override
    protected void initView() {
        //EventBus.getDefault().register(this);
        ll = findViewById(R.id.ll);
        mWebView = findViewById(R.id.webView);
        llRightClose = findViewById(R.id.ll_right_close);
        LLTitleBar = findViewById(R.id.toolbar);
        TVTopTitle = findViewById(R.id.title_text);
        findViewById(R.id.left_btn_bg).setOnClickListener(this);
        findViewById(R.id.ll_right_close).setOnClickListener(this);
        if (!TextUtils.isEmpty(isHeader) && TITLE_BAR_GONE.equals(isHeader)){
            hideTitleBar(GONE);
        }
        initWebViewSettings();
        initWebviewClient();
       // mWebView.setDownloadListener(new FileDownloadListener(this));
        initHardwareAccelerate();
        initJSExeLocaMethod();
        mWebView.loadUrl(mUrl);
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return null;
    }

    @Override
    protected void dataObserver() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_lightwebview;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android_back = null;
        mIsDestroy = true;
       // EventBus.getDefault().unregister(this);
    }

//    /**
//     * 拍照的返回
//     *
//     * @param cameraPathBean
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getCameraPath(CameraPathBean cameraPathBean) {
//        encodeList.clear();
//        ArrayList<String> list = new ArrayList<>();
//        list.add(cameraPathBean.getPathString());
//        if (list != null && list.size() > 0) {
//            CompressImageUtils.getLuBan(LightWebActivity.this, list, new GetLuBanFile() {
//                @Override
//                public void getImageFile(File file) {
//                    if (!JsConstants.callBackHtmlName.equals("")) {
//                        byte[] bytes = BitmapUtil.compressImageReturnBytes(BitmapFactory.decodeFile(file.getPath()), 1024, 100, 10);
//                        if (bytes != null) {
//                            String encode = Base64.encodeToString(bytes, Base64.NO_WRAP);
//                            encodeList.add("data:image/jpeg;base64," + encode);
//                            String[] toBeStored = encodeList.toArray(new String[encodeList.size()]);
//                            String jsonString2 = JSON.toJSONString(toBeStored);
//                            baseJsFunc.callBackHtmlVaule(JsConstants.callBackHtmlName, jsonString2);
//                        }
//
//                    }
//                }
//            });
//        }else {
//            String[] toBeStored = encodeList.toArray(new String[encodeList.size()]);
//            String jsonString2 = JSON.toJSONString(toBeStored);
//            baseJsFunc.callBackHtmlVaule(JsConstants.callBackHtmlName, jsonString2);
//        }
//    }

    private void setRightBtnListener(final String jsFunction) {
        mIvTitleRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsClick(jsFunction);
            }
        });
    }

    public void jsClick(final String jsClickFunction) {
        mWebView.post(new Runnable() {//开启线程进行回传
            @Override
            public void run() {
                String ret = String.format("javascript:" + jsClickFunction + "();");
                mWebView.loadUrl(ret);// 将新的url加载到js当中
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isClose){
                if (mWebView.canGoBack()) {
                    if (!TextUtils.isEmpty(android_back)){
                        baseJsFunc.callBackHtml(android_back, "");
                        return true;
                    }
                    //当WebView不是处于第一页面时，返回上一个页面
                    webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    mWebView.goBack();
                    return true;
                } else {
                    //当WebView处于第一页面时,直接退出
                    finish();
                }
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}