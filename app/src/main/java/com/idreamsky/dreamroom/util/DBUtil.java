package com.idreamsky.dreamroom.util;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by magical on 2016/4/28.
 * 数据库管理类
 */
public class DBUtil {

    private DbManager.DaoConfig geoMancyConfig;
    private static final String cacheDir = "/sdcard";

    private DBUtil() {
    }

    private static DBUtil instance;

    public static DBUtil getInstance() {
        if (null == instance) {
            synchronized (DBUtil.class) {
                if (null == instance) {
                    instance = new DBUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 用这个类 必须先初始化
     */
    public void initDB() {

        geoMancyConfig = new DbManager.DaoConfig()
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbName("geomancy.db")
                .setDbDir(new File(cacheDir))
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });

    }

    /**
     * 获取装修风水的数据库
     *
     * @return
     */
    public DbManager.DaoConfig getGeoDB() {
        return geoMancyConfig;
    }


}
