package com.example.commonlibrary.event;

/**
 * IRouterUri
 * Created by dovsnier on 2020/7/24.
 */
public interface IRouterUri  {

    String ICOME_RN = "icomeRN";
    String ICOME_REGEX = "(?<=icomeRN=)[a-zA-Z0-9]+";

    /**
     * 路由全链
     *
     * @return
     */
    String getUrl();

    /**
     * 是否是RN 标识
     *
     * @return
     */
    boolean isRn();

    /**
     * 返回RN 回调
     *
     * @return
     */
    String getRnCallback();

    /**
     * 判断是否裁剪
     * @return
     */
    boolean  isClip();
}
