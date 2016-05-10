package com.idreamsky.dreamroom.util;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by magical on 2016/4/28.
 * 数据库管理类
 */
public class DBUtil {

    private DbManager.DaoConfig geoMancyConfig;
    private DbManager.DaoConfig eventConfig;
    private DbManager.DaoConfig companyConfig;
    private DbManager.DaoConfig brandConfig;
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

        companyConfig = new DbManager.DaoConfig()
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbName("company.db")
                .setDbDir(new File(cacheDir))
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    }
                });

        eventConfig = new DbManager.DaoConfig()
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbName("event.db")
                .setDbDir(new File(cacheDir))
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    }
                });

        brandConfig = new DbManager.DaoConfig()
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbName("brand.db")
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

    public DbManager.DaoConfig getCompanyDB() {
        return companyConfig;
    }

    public DbManager.DaoConfig getEventDB() {
        return eventConfig;
    }

    public DbManager.DaoConfig getBrandDB() {
        return brandConfig;
    }
}
