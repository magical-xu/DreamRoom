package com.idreamsky.dreamroom.base;

import android.app.Application;

import com.idreamsky.dreamroom.util.ShareUtil;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.x;

/**
 * Created by Administrator on 2016/3/5.
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 初始化第三方库
         */
        VolleyUtil.initVolley(this);
        UniversalUtil.initUniversal(this);
        ShareUtil.init(this);
        x.Ext.init(this);
    }
}
