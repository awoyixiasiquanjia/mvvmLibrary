package com.example.commonlibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * H5传递过来的，为了与ios一样
 *
 * @auther 张腾
 * @since 2018/2/2.
 */
public class AddrItemBean implements Parcelable {
    /**
     * 部门下的人数
     */
    private String myCount;
    /**
     * oId
     */
    private String oId;
    /**
     * oName 公司/部门 名
     */
    private String oName;
    /**
     * pId
     */
    private String pId;
    /**
     * eId  员工ID
     */
    private String eId;
    /**
     * eName  员工名
     */
    private String eName;
    /**
     * email  员工邮箱
     */
    private String email;
    /**
     * jobTitle
     */
    private String jobTitle;
    /**
     * photoId 头像Id
     */
    private String photoId;
    /**
     * 人员状态
     */
    private int invitedStatus;
    private boolean isChecked;
    private boolean isBanUnCheck;
    private Object next;
    /**
     * 头像url
     */
    private String userIconUrl;


    // FIXME: 2018/9/17  曹壮壮
    private int userCount;//每一个自驱组织下对应的人数
    private String account;//itcode

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }


    public AddrItemBean() {
    }

    public int getInvitedStatus() {
        return invitedStatus;
    }

    public void setInvitedStatus(int invitedStatus) {
        this.invitedStatus = invitedStatus;
    }

    public String getMyCount() {
        return myCount;
    }

    public void setMyCount(String myCount) {
        this.myCount = myCount;
    }

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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isBanUnCheck() {
        return isBanUnCheck;
    }

    public void setBanUnCheck(boolean banUnCheck) {
        isBanUnCheck = banUnCheck;
    }

    public String getUserIconUrl() {
        return userIconUrl;
    }

    public void setUserIconUrl(String userIconUrl) {
        this.userIconUrl = userIconUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.myCount);
        dest.writeString(this.oId);
        dest.writeString(this.oName);
        dest.writeString(this.pId);
        dest.writeString(this.eId);
        dest.writeString(this.eName);
        dest.writeString(this.email);
        dest.writeString(this.jobTitle);
        dest.writeString(this.photoId);
        dest.writeInt(this.invitedStatus);
        dest.writeInt(this.userCount);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isBanUnCheck ? (byte) 1 : (byte) 0);
        dest.writeString(this.userIconUrl);
    }

    protected AddrItemBean(Parcel in) {
        this.myCount = in.readString();
        this.oId = in.readString();
        this.oName = in.readString();
        this.pId = in.readString();
        this.eId = in.readString();
        this.eName = in.readString();
        this.email = in.readString();
        this.jobTitle = in.readString();
        this.photoId = in.readString();
        this.invitedStatus = in.readInt();
        this.userCount = in.readInt();
        this.isChecked = in.readByte() != 0;
        this.isBanUnCheck = in.readByte() != 0;
        this.userIconUrl = in.readString();
    }

    public static final Creator<AddrItemBean> CREATOR = new Creator<AddrItemBean>() {
        @Override
        public AddrItemBean createFromParcel(Parcel source) {
            return new AddrItemBean(source);
        }

        @Override
        public AddrItemBean[] newArray(int size) {
            return new AddrItemBean[size];
        }
    };

    public void setNext(Object next) {
        this.next = next;
    }

    public int isEdit = 1;
}
