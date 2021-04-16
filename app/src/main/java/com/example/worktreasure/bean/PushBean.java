package com.example.worktreasure.bean;

import java.util.List;

public class PushBean {


    /**
     * code : 200
     * data : [{"msgContent":"需求ID：test1\r\n            需求名称：需求名称1\r\n            审核结果：1\r\n            驳回原因：无\r\n            审核时间：2021-04-12","createTime":"2021-04-12 10:50:23","msgTitle":"模板标题","bizCode":"system","extra":"{\"demandName\":\"需求名称1\",\"demandID\":\"test1\",\"rejectReason\":\"无\",\"verifyResult\":\"1\",\"verifyTime\":\"2021-04-12\"}","msgDescription":"模板描述","id":8,"msgStatus":1,"msgShowDuration":-1}]
     * success : true
     * totalPages : 1
     * pageSize : 20
     * message :
     * totalCount : 1
     * pageNum : 1
     */

    private String code;
    private boolean success;
    private int totalPages;
    private int pageSize;
    private String message;
    private int totalCount;
    private int pageNum;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msgContent : 需求ID：test1
         需求名称：需求名称1
         审核结果：1
         驳回原因：无
         审核时间：2021-04-12
         * createTime : 2021-04-12 10:50:23
         * msgTitle : 模板标题
         * bizCode : system
         * extra : {"demandName":"需求名称1","demandID":"test1","rejectReason":"无","verifyResult":"1","verifyTime":"2021-04-12"}
         * msgDescription : 模板描述
         * id : 8
         * msgStatus : 1
         * msgShowDuration : -1
         */

        private String msgContent;
        private String createTime;
        private String msgTitle;
        private String bizCode;
        private String extra;
        private String msgDescription;
        private int id;
        private int msgStatus;
        private int msgShowDuration;

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }

        public String getBizCode() {
            return bizCode;
        }

        public void setBizCode(String bizCode) {
            this.bizCode = bizCode;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getMsgDescription() {
            return msgDescription;
        }

        public void setMsgDescription(String msgDescription) {
            this.msgDescription = msgDescription;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMsgStatus() {
            return msgStatus;
        }

        public void setMsgStatus(int msgStatus) {
            this.msgStatus = msgStatus;
        }

        public int getMsgShowDuration() {
            return msgShowDuration;
        }

        public void setMsgShowDuration(int msgShowDuration) {
            this.msgShowDuration = msgShowDuration;
        }
    }
}
