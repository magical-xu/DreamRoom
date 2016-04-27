package com.idreamsky.dreamroom.util;

/**
 * 网络请求URL
 */
public interface Constants {

    /**
     * 精彩推荐
     */
    interface Recommand {

        String Home = "http://ihome.cmfmobile" +
                ".com:8080/sp/custom/getIndexProjectList" +
                ".do?__client__=android&__umeng_channel__=myapp&__read_loc_data__=false&city" +
                "=%E5%8C%97%E4%BA%AC%E5%B8%82&pn=";
    }

    /**
     * 家居图库
     */
    interface Gallery {

        String BASE_SPACE = "http://ihome.cmfmobile.com:8080/sp/custom/searchpic" +
                ".do?__client__=android&__umeng_channel__=myapp";

        String BASE_COLOR = "http://ihome.cmfmobile.com:8080/sp/custom/searchpicByColour" +
                ".do?__client__=android&__umeng_channel__=myapp";

        //颜色系列
        String ALL_COLOR_URL = BASE_COLOR + "&tags=&pn=";
        String GREEN_URL = BASE_COLOR + "&tags=%E7%BB%BF%E8%89%B2&pn=";//绿色
        String PINK_URL = BASE_COLOR + "&tags=%E7%B2%89%E8%89%B2&pn=";//粉色
        String RED_URL = BASE_COLOR + "&tags=%E7%BA%A2%E8%89%B2&pn=";//红色
        String YELLOW_URL = BASE_COLOR + "&tags=%E9%BB%84%E8%89%B2&pn=";//黄色

        //空间系列
        String ALL_SPACE_URL = BASE_SPACE + "&type=all&pn=";//全部
        String BEDROOM_URL = BASE_SPACE + "&type=bedroom&pn=";//卧室
        String LIVING_URL = BASE_SPACE + "&type=living&pn=";//客厅
        String DINING_URL = BASE_SPACE + "&type=dining&pn=";//餐厅
        String BATH_URL = BASE_SPACE + "&type=bath&pn=";//浴室
    }

    /**
     * 灵感专题
     */
    interface Inspiration {

        String THEME_LIST = "http://appapi.17house.com/xiaoguotuApi" +
                ".php?version=1&pageSize=10&action=albumList&page=1&tagid=1&model=android";
        String THEME_DETAIL = "http://appapi.17house.com/xiaoguotuApi" +
                ".php?version=1&action=albumDetail&model=android&id=";
    }

    /**
     * 首页
     */
    interface Home {

        String Banner = "http://appapi.17house.com/AppManagerApi" +
                ".php?version=1&action=getownerinfo&model=android";

        String HotTopic = "http://api.wsq.umeng" +
                ".com/v2/user/topics?dt=1459870296518&de=Redmi+Note+2&os=Android&en=Wi-Fi&sdkv=2" +
                ".3.0&imei=867515021875389&ak=5603a02467e58e6ad00014ab&md5imei" +
                "=fb6802e163fe0a1874b031eb7872d195&mac=fc%3A64%3Aba%3A16%3Ad4%3A10&count=20&fuid" +
                "=5668d916ee78501a18a1e58a";
    }

    /**
     * 家装公司
     */
    interface Company {

        String HOME_LIST_BASE = "http://ihome.cmfmobile.com:8080/sp/custom/getCommunityList" +
                ".do?__client__=android&__umeng_channel__=myapp&__read_loc_data__=false";
        String DETAIL_RELEVANT = "http://ihome.cmfmobile.com:8080/sp/custom/getCommunityModelList" +
                ".do?__client__=android&__umeng_channel__=myapp&id=";
    }

    /**
     * 优选活动
     */
    interface Event {

        String EVENT_HOME = "http://ihome.cmfmobile.com:8080/sp/custom/getGoodActivity" +
                ".do?__client__=android&__umeng_channel__=myapp&__read_loc_data__=false&pn=";
    }

    /**
     * 品牌展厅
     */
    interface Brand {

        //品牌展厅首页
        String BRAND_HOME_URL = "http://ihome.cmfmobile.com:8080/sp/custom/showBrand" +
                ".do?__client__=android&__umeng_channel__=myapp";

        String BRAND_BASE_URL = "http://ihome.cmfmobile" +
                ".com:8080/sp/custom/showProductClassByBrand" +
                ".do?__client__=android&__umeng_channel__=myapp&brand=";

        String BRAND_FURNITURE_BASE_URL = "http://ihome.cmfmobile.com:8080/sp/custom/getProduct" +
                ".do?__client__=android&__umeng_channel__=myapp&__read_loc_data__=false";
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
