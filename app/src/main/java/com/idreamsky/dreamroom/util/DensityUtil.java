package com.idreamsky.dreamroom.util;

import android.content.res.Resources;

/**
 * Created by magical on 2016/4/24.
 */
public class DensityUtil {

    public static final float getHeightInPx() {
        final float height = Resources.getSystem().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final float getWidthInPx() {
        final float width = Resources.getSystem().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp() {
        final float height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(height);
        return heightInDp;
    }

    public static final int getWidthInDp() {
        final float height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int widthInDp = px2dip(height);
        return widthInDp;
    }

    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }
}
