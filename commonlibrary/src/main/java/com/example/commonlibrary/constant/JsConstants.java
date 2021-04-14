package com.example.commonlibrary.constant;

public class JsConstants {
    //显示右上角的按钮
    public static final int SHOW_OPTION_MENU = 1;
    //隐藏右上角的按钮
    public static final int HIDE_OPTION_MENU = 2;
    //显示页面标题栏
    public static final int SHOW_WEB_VIEW_TOOLBAR = 3;
    //隐藏页面标题栏
    public static final int HIDE_WEB_VIEW_TOOLBAR = 4;
    //显示左上角按钮
    public static final int SHOW_CLOSE_BTN = 5;
    //显示左上角按钮
    public static final int HIDE_CLOSE_BTN = 6;
    //设置右上角的按钮
    public static final int SET_OPTION_MENU = 7;
    //设置标题
    public static final int SET_TITLE_MENU = 8;
    //关闭页面标识
    public static final int SET_PENDING_CLOSE = 9;
    //关闭页面标识
    public static final int SELECT_PHOTOS = 10;

    //创值链弹出dialog
    public static final int VALUE_CHAIN_ALERT_DIALOG = 100;
    //创值链分享到IM
    public static final int VALUE_CHAIN_SHARETOIM = 101;
    //跳转到赋能站
    public static final int VALUE_CHAIN_PUSH_TO_ENERGIZE = 102;
    //创值链弹出评论框
    public static final int VALUE_CHAIN_COMMENT = 103;
    //问答点击跳过
    public static final int GESTUREPASSWORD_QUESTIONS = 200;

    //选择联系人
    public static final int SYSTEM_SELECT_USER = 10;

    //h5拍照回调
    public static final int CAMERA_H5 = 12;

    public static String callBackHtmlName = "";
    //设置导航栏显示隐藏
    public static final int FULL_SCREEN = 11;

    //修改导航栏颜色
    public static final int STATUS_BAR_COLOR = 12;

    //关闭页面（在无法直接关闭的情况下，web端告诉我们直接关闭）
    public static final int BACK_EVENT_CLOAW = 13;

    //选择客户
    public static final int SYSTEM_SELECT_CUSTOMER = 18;
    //回复评论
    public static final int REPLY_COMMENT = 14;
    //视频播放
    public static final int VIDEO_DISPLAY = 15;
    //回复二级评论
    public static final int REPLY_COMMENT_SECOND=16;
    //物理返回键相关事件
    public static final int ANDROID_BACK=17;

    //跳转SelectMapLocaActivity的requestCode,
    public static final int SELECT_MAP = 30;
    //选择系统通讯录
    public static final int SELECT_CONTACT = 40;
    //手势密码
    public static final int GESTURE_PWD = 22;
    public interface OBTAIN {
        //校验身份和注册jsapi接口
        String VALID_DATA = "validData";//轻应用权限认证
    }

    public interface WEB_VIEW {
        //获取当前客户端的用户代理
        String GET_USER_AGENT = "webview.getUserAgent";
        //返回上一页
        String BACK = "webview.back";
        //关闭当前webview
        String CLOSE = "webview.close";
        //显示右上角的按钮
        String SHOW_OPTION_MENU = "webview.showOptionMenu";
        //隐藏右上角的按钮
        String HIDE_OPTION_MENU = "webview.hideOptionMenu";
        //显示页面标题栏
        String SHOW_WEB_VIEW_TOOLBAR = "webview.showWebViewToolbar";
        //隐藏页面标题栏
        String HIDE_WEB_VIEW_TOOLBAR = "webview.hideWebViewToolbar";
        //设置右上角的按钮
        String SET_OPTION_MENU = "webview.setOptionMenu";
        //显示左上角按钮
        String SHOW_CLOSE_BTN = "webview.showCloseBtn";
        //显示左上角按钮
        String HIDE_CLOSE_BTN = "webview.hideCloseBtn";
        //设置标题
        String TITLE_DOWN_ICON = "webview.setTitle";
        //唤起/关闭键盘
        String ALLOW_DISPLAYING_KEYBOARD = "webview.allowDisplayingKeyboard";
        //关闭当前页面(如果此页面调用物理返回键无法退出页面)
        String PENDING_CLOSE = "webview.pendingclose";
        //导航栏显示隐藏
        String FULL_SCREEN = "webview.fullScreen";
        //修改导航栏颜色
        String STATUS_BAR_COLOR = "webview.statusBarColor";
        //是否支持缩放
        String ENABLE_ZOOM = "webview.zoom";
        //点击物理返回按键时，做相应的处理
        String ANDROID_BACK = "webview.androidBack";
        //获取状态栏高度
        String STATUS_BAR_HEIGHT = "webview.statusBarHeight";


    }

    public interface DEVICE {
        //获取用户网络状态
        String GET_NETWORK_TYPE = "device.getNetworkType";
        //获取图片（从相机或本地，取一张或多张图片）
        String SELECT_PHOTOS = "device.selectPhotos";
        //扫一扫，返回二维码对于的字符串
        String SCAN_QR_CODE = "device.scanQRCode";
        //拍照
        String TAKE_PHOTOS = "device.takePhotos";
        //拨打电话
        String CALL = "device.call";
        //获取位置信息
        String GET_LOCATION = "device.getLocation";
        //使用APP内置地图查看位置
        String CHECK_LOCATION = "device.checkLocation";
        //从Icom文件目录中选择单个文件上传到指定服务器上，返回服务器文件的Id
        String UPLOAD_FILE = "device.uploadFile";
        //预览文件
        String PREVIEW_FILE = "device.previewFile";
        String SNAP_SHOT = "device.snapshot"; //人脸识别

        String UPLOAD_FILE_FULL = "device.uploadFileToServer";

        //选择原图
        String NO_COMPRESS_PHOTOS = "device.noCompressPhotos";
        //仅相册选择图片
        String SELECT_PICTURES = "device.selectPictures";
        //开启录音
        String START_RECORD = "device.startRecord";
        //关闭录音
        String STOP_RECORD = "device.stopRecord";
        //保存图片
        String SAVE_IMG = "device.saveImg";
    }

    public interface SYSTEM {
        //获取当前用户身份信息
        String GET_USER_INFO = "system.getUserInfo";
        //调用选择组织界面（单选）
        String SELECT_ORG = "system.selectOrg";
        //选择用户（多选）
        String SELECT_USER = "system.selectUser";
        //双人会话存在时直接进入双人会话页面，如果双人会话不存在，创建双人会话后进入双人会话页面。
        String OPEN_CHAT = "system.openChat";
        //打开会话（双人、多人）列表，选择某个会话并返回该会话的信息
        String SELECT_GROUP = "system.selectGroup";
        //打开会话界面
        String OPEN_GROUP_CHAT = "system.openGroupChat";
        //这里说的人员详情是指调出iCom APP上面的人员详情页，而不是返回人员详细的内容。
        String SHOW_USER_INFO = "system.showUserInfo";
        //实现分享
        String SHARE_TO_ICOM = "system.shareToICOM";
        String OPEN_APP = "system.openApp";   //打开其他的轻应用
        String SHARE_TO_WECHAT = "system.shareToWechat";   //分享到微信
        //创建多人会话
        String CREATE_MULT_CHAT = "system.createMultChat";
        //打开视频会议
        String OPEN_VIDEOCONF = "system.openVideoConf";
        //打开指定url并使用指定域名替换refer
        String LOAD_URL_WITH_REFER = "system.loadUrlWithRefer";
        //打开sdk外部的东西
        String OPEN_OUT_SDK = "system.openNativeMethod";
        //打开微信小程序
        String OPEN_WE_CHATAPPLET = "system.openWeChatApplet";
        //打开通讯录
        String OPEN_MAIL_LIST = "system.icomeAddressList";
        //打开新web页
        String OPEN_WEB_VIEW = "system.openWebView";
        //获取token
        String GET_TOKEN = "system.token";
        //获取ticket
        String GET_TICKET = "system.ticket";
        //查看指定用户对组织
        String SELECT_SHOW_ORG = "system.showOrg";
        //APP内部断链
        String SELECT_URL_SCHEME = "system.UrlScheme";

        //打开组织
        String OPEN_ORG = "system.showOrg";
        //弹出Toast
        String SHOW_TOAST = "system.showToast";
        //功能埋点
        String ANALYTICS = "system.analytics";

        //h5调用拍照
        String TAKEPHONE = "system.takePhotos";

        //获取图片地址
        String MONGOLAYS = "system.getPhotos";
        //打开网盘
        String OPENWEBDISC = "system.openWebNetdisc";
        //关闭当前页面(无法直接关闭的情况，需要web端告诉你)
        String BACK_EVENT_CLOSE = "system.backEventClose";
        //打开操作系统的浏览器
        String SYSTEM_WEBVIEW = "system.systemWebView";

        //播放视频
        String VIDEO_DISPLAY = "system.videoDisplay";//视频播放
        //复制操作系统剪切板信息
        String COPY_PASTEBOARD = "system.copyPasteboard";
        //是否在iCome内
        String ICOME = "system.iCome";
        //通讯录选组织
        String SELECT_ICOMEORG ="system.selectiComeOrg";

        //保存人员到系统通讯录
        String SYSTEM_SAVE_ADDRESS_BOOK = "system.saveAddressBook";
        //从系统通讯录里选人
        String SYSTEM_SELECTEDADDRESSBOOK = "system.selectedAddressBook";
        //调用手势密码
        String SYSTEM_GESTUREPWD="system.getuserpwd";
        //选择工作组
        String WORKTEAM_EXPORTRESULTS="workTeam.exportResults";
    }

    public interface BLE {
        //初始化蓝牙模块
        String BT_INIT_BLUETOOTH = "bt.initBluetooth";
        //向低功耗蓝牙设备特征值中写入二进制数据
        String BT_WRIT_BLE_CHARACTERISTIVALUE = "bt.writeBLECharacteristicValue";
        //关闭蓝牙模块
        String BT_CLOSE_BLUETOOTH = "bt.closeBluetoothAdapter";
    }
    public interface ERROR {
        //接口没有在接口列表中，无权限访问
        String NO_ACCESS = "error.noAccess";
        //URL跳出轻应用的根目录拦截所有接口请求
        String OUT_ROOT = "error.outRoot";
    }

    public interface METHOD_PARAM {
        String SERVER_URL = "serverUrl";
    }

    /* 创值链 */
    public interface VALUE_CHAIN{
        //点击评论
        String VALUECHAIN_COMMENT = "valueChain.comment";
        //创值链分享到IM
        String VALUECHAIN_SHARETOIM = "valueChain.shareToIM";
        //跳转到赋能站
        String VALUECHAIN_PUSHTOENERGIZE= "valueChain.pushToEnergize";
        //创值链 创值链弹出dialog
        String VALUECHAIN_GESTUREDELETE = "valueChain.gestureDelete";
        //创值链 进入详情页
        String VALUECHAIN_OPENDETAIL = "valueChain.openDetail";
        //创值链获取权限 0无权限 ，1有权限
        String VALUECHAIN_PERMISSION = "valueChain.permission";

    }
    /* 问答 */
    public interface QUESTIONS{
        //问答点击跳过事件
        String GESTUREPASSWORD_QUESTIONS = "gesturePassword.questions";
    }
    /* 客户 */
    public interface CUSTOMER{
        //查看客户详情
        String CUSTOMER_DETAILS = "customer.goToCustomerDetails";
        // 客户认知-刷新指定页面
        String CUSTOMER_RELOADDATA = "customer.reloadData";

        //客户认知选择客户
        String CUSTOMER_SELECT_CLENT = "customer.searchInfo";
    }

    /**
     * 网盘
     */
    public interface SKY_DRIVE {
        String SKY_SEND = "dbank.send";//网盘发送数据
        String SKY_PREVIEW = "dbank.preview";//网盘预览
        String SKY_LOAD = "dbank.download";//网盘下载
    }

    /**
     * 工作台
     */
    public interface WORKBENCH {
        //工作台首页刷新
        String WORKBENCH_RELOAD_DATA = "workbench.reloadData";
    }
    //新闻
    public interface NEWS {
        //预览图片
        String SHOW_IMGES = "news.showImages";

        String REPLY_COMMENT = "news.replyComment";//回复评论

        String REPLY_COMMENT_SECOND = "news.replyCommentSecond";

    }

    /**
     * 高德地图
     */
    public interface AMAP {
        //定位
        String LOCATION = "amap.location";
        //打开地图-选取位置
        String OPENMAP = "amap.openMap";
        //打开导航app(carservice 用车服务)
        String JUMP_TO_NAVI_APP = "amap.jumpToNavigationAPP";
    }

    /**
     * IM相关
     */
    public interface IM{
        String IM_SHARE_CARD = "im.shareCard";//分享卡片
    }
}
