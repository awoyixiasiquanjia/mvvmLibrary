package com.example.commonlibrary.event;
import android.net.Uri;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RouterBean
 * Created by dovsnier on 2020/7/24.
 */
public class RouterBean  implements IRouterUri {

    protected String url;

    public RouterBean() {
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean isRn() {
        if (!TextUtils.isEmpty(getUrl())) {
            if (getUrl().contains(ICOME_RN)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRnCallback() {
        if (isRn()) {
            Pattern pattern = Pattern.compile(ICOME_REGEX);
            Matcher matcher = pattern.matcher(getUrl());
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return null;
    }

    @Override
    public boolean isClip() {
        if (isRn()){
            Uri uri = Uri.parse(getUrl());
            String amount = uri.getQueryParameter("amount");//图片数量 默认1
            String clipping = uri.getQueryParameter("clipping");//剪裁类型 0 默认 不裁剪 1 正方形
            if (!TextUtils.isEmpty(amount) && !TextUtils.isEmpty(clipping)){
                return amount.equals("1")&& clipping.equals("1");//当图片数量为1 并且裁剪类型为1时 裁剪
            }
        }
        return false;
    }
}
