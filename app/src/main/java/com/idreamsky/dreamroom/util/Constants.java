package com.idreamsky.dreamroom.util;

/**
 * 常量接口
 */
public interface Constants {

    /**
     * 家居图库
     */
    interface Gallery {

        //颜色系列
        String GALLERY_ALL_COLOR_URL = "http://ihome.cmfmobile" +
                ".com:8080/sp/custom/searchpicByColour" +
                ".do?__client__=android&__umeng_channel__=myapp&tags=&pn=";
        String GALLERY_BEIGE_URL = "http://ihome.cmfmobile.com:8080/sp/custom/searchpicByColour" +
                ".do?__client__=android&__umeng_channel__=myapp&tags=%E7%B1%B3%E8%89%B2&pn=";
        String GALLERY_PINK_URL = "http://ihome.cmfmobile.com:8080/sp/custom/searchpicByColour" +
                ".do?__client__=android&__umeng_channel__=myapp&tags=%E7%B2%89%E8%89%B2&pn=";

        //空间系列
        String GALLERY_ALL_SPACE_URL = "";
    }

    /**
     * 装修风水
     */
    interface Geomancy {

        //风水首页 不加page
        String GEOMANCY_BASE_URL = "http://appapi.17house.com/NewsApi" +
                ".php?version=1&pageSize=20&action=getNewsByTagId&tagIds=136&model=android&page=";
        //风水文章详情 不加id
        String GEOMANCY_DETAIL_URL = "http://appapi.17house.com/NewsApi" +
                ".php?version=1&relatedNum=3&action=newsDetail&page=1&model=android&newsId=";
    }
}
