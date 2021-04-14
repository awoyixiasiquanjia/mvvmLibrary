package com.example.mvvmlibrary.lib_mvvm.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmlibrary.R;

/**
 *弹框显示
 */
public class ToastUtil {

    public static void showToast(Context context, int resId) {
        showToast(context.getApplicationContext(), context.getString(resId));
    }

    protected static Toast centerToast = null;

    /**
     * 居中显示Toast
     *
     * @param context
     * @param resId
     * @param time    显示时长
     */
    public static View showCenterToast(Context context, int resId, long time) {
        View view = LayoutInflater.from(context).inflate(resId, null);
//        if (centerToast!= null) {
//            centerToast.cancel();
//
//        }else {
        centerToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        centerToast.setGravity(Gravity.CENTER, 0, 0);
//        }
        centerToast.setView(view);
        centerToast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                centerToast.cancel();
            }
        }, time);
        return view;
    }

    /**
     * 资源居中显示Toast
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_black_layout, null);
        TextView textView = view.findViewById(R.id.toast_black_text);

        textView.setText(text);

        centerToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        centerToast.setGravity(Gravity.CENTER, 0, 0);

        centerToast.setView(view);
        centerToast.show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                centerToast.cancel();
            }
        }, 1500);//显示时长
    }

    private static Toast sigleToast;

    /**
     * 单列的Toast,针对toke过期弹多次的问题
     *
     * @param context
     * @param text
     */
    public static void showSigleToast(Context context, String text) {
        if (sigleToast!=null){
            TextView textView = sigleToast.getView().findViewById(R.id.toast_black_text);
            textView.setText(text);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.toast_black_layout, null);
            TextView textView = view.findViewById(R.id.toast_black_text);
            textView.setText(text);
            sigleToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
            sigleToast.setGravity(Gravity.CENTER, 0, 0);
            sigleToast.setView(view);
        }
        sigleToast.show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                sigleToast.cancel();
            }
        }, 2000);//显示时长
    }





    /**
     * 底部显示Toast
     *
     * @param context
     * @param text
     */
    public static void showBottomToast(Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_black_layout, null);
        TextView textView = view.findViewById(R.id.toast_black_text);

        textView.setText(text);

        centerToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        centerToast.setGravity(Gravity.BOTTOM, 0, 150);

        centerToast.setView(view);
        centerToast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                centerToast.cancel();
            }
        }, 3000);//显示时长
    }
    /**
     * 资源居中显示Toast
     *
     * @param context
     * @param id
     */
    public static void showToastTow(final Context context, int id) {
        final View view = LayoutInflater.from(context).inflate(R.layout.toast_black_layout, null);
        TextView textView = view.findViewById(R.id.toast_black_text);

        textView.setText(context.getString(id));
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                centerToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
                centerToast.setGravity(Gravity.CENTER, 0, 0);

                centerToast.setView(view);
                centerToast.show();
            }
        });//显示时长

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                centerToast.cancel();
            }
        }, 1500);//显示时长
    }

}
