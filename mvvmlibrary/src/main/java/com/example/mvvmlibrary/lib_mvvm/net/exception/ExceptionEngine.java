package com.example.mvvmlibrary.lib_mvvm.net.exception;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import io.reactivex.rxjava3.exceptions.CompositeException;
import retrofit2.HttpException;



public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    public static Exception handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ErrorType.HTTP_ERROR, 0);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "当前请求需要用户验证";
                    break;
                case FORBIDDEN:
                    ex.message = "服务器已经理解请求，但是拒绝执行它";
                    break;
                case NOT_FOUND:
                    ex.message = "服务器异常，请稍候再试!404";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message =
                        "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP"
                            + "）或者辅助服务器（例如DNS）收到响应";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器去火星啦,请稍候再试!";
                    break;
                case BAD_GATEWAY:
                    ex.message = "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "由于临时的服务器维护或者过载，服务器当前无法处理请求";
                    break;
                default:
                    ex.message = "网络出错啦,请稍候再试!";  //其它均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex =
                new ApiException(resultException, resultException.code, resultException.message);
            return ex;
        } else if (e instanceof JsonParseException
            || e instanceof JSONException
            || e instanceof ParseException) {
            ex = new ApiException(e, ErrorType.PARSE_ERROR, 0);
            ex.message = "解析错误:  \r\n" + e.getMessage();            //均视为解析错误
            return ex;
        } else if (e instanceof ConnectException
            || e instanceof SocketTimeoutException
            || e instanceof ConnectTimeoutException) {
            ex = new ApiException(e, ErrorType.NETWORK_ERROR, 0);
            ex.message = "服务器连接超时";  //均视为网络错误
            ex.alert = e.toString();
            return ex;
        } else if (e instanceof IOException) {
            ex = new ApiException(e, ErrorType.NETWORK_ERROR, 0);
            ex.message = "内容为空";
            ex.alert = e.toString();
            return ex;
        } else if (e instanceof NullDataException) {

            NullDataException nullDataException = new NullDataException();

            return nullDataException;
        } else if (e instanceof CompositeException) {

            ex = new ApiException(e, ErrorType.PARSE_ERROR, 0);

            ex.alert = e.toString();

            StringBuffer messageBuffer = new StringBuffer();

            List<Throwable> exceptions = ((CompositeException) e).getExceptions();

            for (Throwable throwable : exceptions) {

                if (throwable instanceof JsonSyntaxException) {

                    messageBuffer.append("解析错误 :  \r\n" + throwable.getMessage() + "\r\n");
                } else {
                    messageBuffer.append(throwable.getMessage() + "\r\n");
                }
            }

            ex.message = messageBuffer.toString();

            return ex;
        } else {
            ex = new ApiException(e, ErrorType.UNKNOWN, 0);
            ex.message = "未知错误" + e.getMessage();          //未知错误
            return ex;
        }
    }
}

