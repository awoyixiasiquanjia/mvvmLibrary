package com.example.mvvmlibrary.lib_mvvm.base.livedata;

/**
 * @Name：xiaobu_android_pad
 * @Description:
 * @Author：hyr
 * @Date：2019/12/12 6:49 PM 修改人：hyr 修改时间：2019/12/12 6:49 PM 修改备注：
 * @version:
 */
public class EventCenter<T> {

    /**
     * reserved data
     */
    private T data;

    /**
     * this code distinguish between different events
     */
    private int eventCode = -1;

    public EventCenter(int eventCode) {
        this(eventCode, null);
    }


    public EventCenter() {
    }


    public EventCenter(T data) {
        this.data = data;
    }


    public EventCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    /**
     * get event code
     *
     * @return
     */
    public int getEventCode() {
        return this.eventCode;
    }

    /**
     * get event reserved data
     *
     * @return
     */
    public T getData() {
        return this.data;
    }
}

