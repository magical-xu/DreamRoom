package com.idreamsky.dreamroom.util;

/**
 * 常量接口
 */
public interface Constants {

    interface URL{
        /**
         * 推荐页主体
         */
        String RECOMENT_MAIN_URL = "http://api.meishi.cc/v5/index2.php?format=json&c_md5=958586a040c1db0420851cf2e13487fe";

        /**
         * 推荐页猜你喜欢
         */
        String RECOMENT_CUSTOMIZED_URL = "http://api.meishi.cc/v5/index2.php?format=json&c_md5=958586a040c1db0420851cf2e13487fe&page=2";

        /**
         * 食话模块
         */
        String FOOD_MAIN = "http://api.meishi.cc/v5/meishiquan_index2.php?format=json&source=android";
    }
}
