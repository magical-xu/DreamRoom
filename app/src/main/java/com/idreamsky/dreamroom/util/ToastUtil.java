package com.idreamsky.dreamroom.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by magical on 2016/3/5.
 */
public class ToastUtil {

    public static Toast gToast = null;

    /**
     * 弹出Toast提示，Toast.LENGTH_SHORT
     *
     * @param context
     *            context对象
     * @param msg
     *            提示内容
     */
    public static void ToastShort(Context context, String msg) {
        cleanToast();
        gToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        gToast.show();
    }

    public static void ToastShort(Context context, int msgTxtId) {
        if (null != context) {
            ToastShort(context, context.getResources().getString(msgTxtId));
        }
    }

    /**
     * 弹出Toast提示，Toast.LENGTH_LONG
     *
     * @param context
     *            context对象
     * @param msg
     *            提示内容
     */
    public static void ToastLong(Context context, String msg) {
        cleanToast();
        gToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        gToast.show();
    }

    public static void ToastLong(Context context, int msgTxtId) {
        if (null != context) {
            ToastLong(context, context.getResources().getString(msgTxtId));
        }
    }

    /**
     * 清除显示的TOAST
     */
    public static void cleanToast() {
        if (null != gToast) {
            gToast.cancel();
            gToast = null;
        }
    }
}
