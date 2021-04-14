package com.example.commonlibrary.bean;

import java.io.Serializable;
import java.util.List;

public class JsBean implements Serializable {
    private String target;
    private String callback;
    private String action;
    private Params params;

    public void setParams(Params params) {
        this.params = params;
    }

    public Params getParams() {
        return params;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public class Params implements Serializable {
        private String amount;
        private String appId;
        private String timestamp;
        private String nonceStr;
        private String singleMode;
        private String signature;
        private String iconId;
        private String onClick;
        private String jsApiList;
        private String tel;
        private String groupId;
        private String appletId;
        private String refer;
        private List<ParamBean> param;
        private String eId;//组织id
        private String oId;
        private String eventId;
        private String objcType;
        private String objcIndex;
        private List<Elements> chooseElements;
        private List<String> disableElements;
        private String openType;
        private List<Object> result;//选择工作组
        private String imgName;
        private String imgUrl;

        public String getImgName() {
            return imgName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getImgBase64() {
            return imgBase64;
        }

        private String imgBase64;

        public List<Object> getResult() {
            return result;
        }

        public void setResult(List<Object> result) {
            this.result = result;
        }

        public String getOpenType() {
            return openType;
        }

        public List<Elements> getChooseElements() {
            return chooseElements;
        }

        public void setChooseElements(List<Elements> chooseElements) {
            this.chooseElements = chooseElements;
        }

        public List<String> getDisableElements() {
            return disableElements;
        }

        public void setDisableElements(List<String> disableElements) {
            this.disableElements = disableElements;
        }

        public String getSingleMode() {
            return singleMode;
        }

        public void setSingleMode(String singleMode) {
            this.singleMode = singleMode;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        private int index;
        private String objcName;
        private String hidden;
        private String groupName;
        private String cover;
        private String code;
        private List<String> eIds;
        private List<String> eNames;
        private Ext ext;
        private String serverUrl;
        private String title;
        private int close;
        private String enabled;
        //客户详情
        private String customerId;
        private int clueType;
        private String customerPainPointId;
        private String name;
        private String color;
        private String deviceId;
        private String serviceId;
        private String characteristicId;
        private String value;
        private List<String> serviceIds;
        private List<String> characteristicIds;
        private String cameraDevFacing;
        //新闻
        private String videoUrl;
        private String snapshotUrl;
        private String videoTitle;
        //新闻评论
        private int articleId;
        private int commentId;
        private String userName;
        private int commentType;
        public int getCommentType() {
            return commentType;
        }
        public void setCommentType(int commentType) {
            this.commentType = commentType; }

        //通讯录已选人
        private List<SelectedPerson> selected;
        //跳转“用车服务”app的参数
        private String token;
        private String orderId;
        private String endLocation;
        private String endLatitude;
        private String endLongitude;
        //图片查看器，是否显示下载图标 0 不显示 1 显示
        private int showDownLoad;
        //物理返回回调方法
        private String androdH5;

        private String fullScreen;//0：非全屏  1：隐藏状态栏导航栏
        public String getAndrodH5() {
            return androdH5;
        }

        public void setAndrodH5(String androdH5) {
            this.androdH5 = androdH5;
        }

        public int getShowDownLoad() {
            return showDownLoad;
        }

        public String getFullScreen() {
            return fullScreen;
        }

        public void setFullScreen(String fullScreen) {
            this.fullScreen = fullScreen;
        }

        public void setShowDownLoad(int showDownLoad) {
            this.showDownLoad = showDownLoad;
        }
        public class SelectedPerson{
            private String userId;
            private String userName;
            private String photoId;
            private String jobTitle;

            public String getJobTitle() {
                return jobTitle;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPhotoId() {
                return photoId;
            }

            public void setPhotoId(String photoId) {
                this.photoId = photoId;
            }
        }

        public List<SelectedPerson> getSelected() {
            return selected;
        }

        public void setSelected(List<SelectedPerson> selected) {
            this.selected = selected;

        }
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(String endLocation) {
            this.endLocation = endLocation;
        }

        public String getEndLatitude() {
            return endLatitude;
        }

        public void setEndLatitude(String endLatitude) {
            this.endLatitude = endLatitude;
        }

        private String mobile;
        private String email;
        private String jobTitle;

        private String type;
        private String desc;
        private Object data;

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEndLongitude() {
            return endLongitude;
        }

        public void setEndLongitude(String endLongitude) {
            this.endLongitude = endLongitude;
        }
        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public void setAppletId(String appletId) {
            this.appletId = appletId;
        }

        public void setRefer(String refer) {
            this.refer = refer;
        }

        public void setHidden(String hidden) {
            this.hidden = hidden;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void seteIds(List<String> eIds) {
            this.eIds = eIds;
        }

        public void seteNames(List<String> eNames) {
            this.eNames = eNames;
        }

        public void setExt(Ext ext) {
            this.ext = ext;
        }

        public void setServerUrl(String serverUrl) {
            this.serverUrl = serverUrl;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public void setCharacteristicId(String characteristicId) {
            this.characteristicId = characteristicId;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setServiceIds(List<String> serviceIds) {
            this.serviceIds = serviceIds;
        }

        public void setCharacteristicIds(List<String> characteristicIds) {
            this.characteristicIds = characteristicIds;
        }

        public void setCameraDevFacing(String cameraDevFacing) {
            this.cameraDevFacing = cameraDevFacing;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getSnapshotUrl() {
            return snapshotUrl;
        }

        public void setSnapshotUrl(String snapshotUrl) {
            this.snapshotUrl = snapshotUrl;
        }

        public void setImageArr(String[] imageArr) {
            this.imageArr = imageArr;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getCameraDevFacing() {
            return cameraDevFacing;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public String getCharacteristicId() {
            return characteristicId;
        }

        public String getValue() {
            return value;
        }

        public List<String> getServiceIds() {
            return serviceIds;
        }

        public List<String> getCharacteristicIds() {
            return characteristicIds;
        }

        private String[] imageArr;

        public String[] getImageArr() {
            return imageArr;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



        public int getClose() {
            return close;
        }

        public void setClose(int close) {
            this.close = close;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getClueType() {
            return clueType;
        }

        public void setClueType(int clueType) {
            this.clueType = clueType;
        }

        public String getCustomerPainPointId() {
            return customerPainPointId;
        }

        public void setCustomerPainPointId(String customerPainPointId) {
            this.customerPainPointId = customerPainPointId;
        }

        public Ext getExt() {
            return ext;
        }

        public class Ext {
            private String fileName;
            private int fileSize;
            private String fileFormat;

            public String getFileName() {
                return fileName;
            }

            public int getFileSize() {
                return fileSize;
            }

            public String getFileFormat() {
                return fileFormat;
            }
        }

        public List<String> geteIds() {
            return eIds;
        }

        public List<String> geteNames() {
            return eNames;
        }

        public String getCover() {
            return cover;
        }

        public String getCode() {
            return code;
        }

        public String getGroupName() {
            return groupName;
        }


        public List<ParamBean> getParam() {
            return param;
        }

        public void setParam(List<ParamBean> param) {
            this.param = param;
        }

        public class ParamBean {
            /**
             * cover : http://10.39.35.11:8079/admin/homepage/image/getOgl?fileKey=5d77045dd309c158700f9fd0
             * code : FACE
             */

            private String cover;
            private String code;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
        public class Elements{
            private String oId;
            private String oName;
            public String getoId() {
                return oId;
            }

            public void setoId(String oId) {
                this.oId = oId;
            }

            public String getoName() {
                return oName;
            }

            public void setoName(String oName) {
                this.oName = oName;
            }
        }
        public String getHidden() {
            return hidden;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getObjcType() {
            return objcType;
        }

        public void setObjcType(String objcType) {
            this.objcType = objcType;
        }


        public String getObjcIndex() {
            return objcIndex;
        }

        public void setObjcIndex(String objcIndex) {
            this.objcIndex = objcIndex;
        }

        public String getPath() {
            return path;
        }

        private String path;

        public String getObjcName() {
            return objcName;
        }


        public void setObjcName(String objcName) {
            this.objcName = objcName;
        }

        public String geteId() {
            return eId;
        }

        public void seteId(String eId) {
            this.eId = eId;
        }

        public String getoId() {
            return oId;
        }

        public void setoId(String oId) {
            this.oId = oId;
        }

        public String getRefer() {
            return refer;
        }

        public String getAppletId() {
            return appletId;
        }

        public String getGroupId() {
            return groupId;
        }

        private String productId;
        private String screenshotUrl;

        public String getScreenshotUrl() {
            return screenshotUrl;
        }

        public void setScreenshotUrl(String screenshotUrl) {
            this.screenshotUrl = screenshotUrl;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        private String text;//创值链弹框内容

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setIconId(String iconId) {
            this.iconId = iconId;
        }

        public void setOnClick(String onClick) {
            this.onClick = onClick;
        }

        public void setJsApiList(String jsApiList) {
            this.jsApiList = jsApiList;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTel() {
            return tel;
        }

        public String getUrl() {
            return url;
        }

        private String userId;
        private String url;

        public String getUserId() {
            return userId;
        }


        public String getIconId() {
            return iconId;
        }

        public String getOnClick() {
            return onClick;
        }

        public String getAppId() {
            return appId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public String getSignature() {
            return signature;
        }

        public String getJsApiList() {
            return jsApiList;
        }

        public String getServerUrl() {
            return serverUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
