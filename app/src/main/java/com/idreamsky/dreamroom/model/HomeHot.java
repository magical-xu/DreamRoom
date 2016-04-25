package com.idreamsky.dreamroom.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by magical on 2016/4/25.
 * HorizontalScrollView 热门话题
 */
public class HomeHot {

    /**
     * description : 征稿要求： 1、标题：【2016装修计划单项赛】您的标题 2、内容要求： 户型 图: 装修预算: 设计风格: 是否有装修经验: 计划开工时间:
     * 文字不少于300字，相关图片不少于3张的装修计划描述。 征稿时间：3月3日至6月1日
     * fan_count : 2
     * icon_url : null
     * image_urls : [{"60":"http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@60w_0e_1l_75Q",
     * "360":"http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@360w_360h_1e_1c_75Q",
     * "750":"http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@750w_0e_1l",
     * "900":"http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@900w_0e_1l_90Q",
     * "origin":"http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630"}]
     * custom :
     * create_time : 2016-04-21 11:54:29
     * icon_urls : {"80":null,"160":null,"origin":null}
     * feed_count : 3
     * id : 57184ef57019c920dc20f288
     * name : 装修计划PK赛
     */

    private String description;
    private int fan_count;
    private Object icon_url;
    private String custom;
    private String create_time;
    /**
     * 80 : null
     * 160 : null
     * origin : null
     */

    private IconUrlsEntity icon_urls;
    private int feed_count;
    private String id;
    private String name;
    /**
     * 60 : http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@60w_0e_1l_75Q
     * 360 : http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@360w_360h_1e_1c_75Q
     * 750 : http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@750w_0e_1l
     * 900 : http://c-56330cc9ee785049e075b793.image.alimmdn
     * .com/2016-04-21/900df42f0c6bf3dd5fef532b84dd4630@900w_0e_1l_90Q
     * origin : http://c-56330cc9ee785049e075b793.image.alimmdn.com/2016-04-21
     * /900df42f0c6bf3dd5fef532b84dd4630
     */

    private List<ImageUrlsEntity> image_urls;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFan_count() {
        return fan_count;
    }

    public void setFan_count(int fan_count) {
        this.fan_count = fan_count;
    }

    public Object getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(Object icon_url) {
        this.icon_url = icon_url;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public IconUrlsEntity getIcon_urls() {
        return icon_urls;
    }

    public void setIcon_urls(IconUrlsEntity icon_urls) {
        this.icon_urls = icon_urls;
    }

    public int getFeed_count() {
        return feed_count;
    }

    public void setFeed_count(int feed_count) {
        this.feed_count = feed_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageUrlsEntity> getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(List<ImageUrlsEntity> image_urls) {
        this.image_urls = image_urls;
    }

    public static class IconUrlsEntity {
        @SerializedName("80")
        private Object value80;
        @SerializedName("160")
        private Object value160;
        private Object origin;

        public Object getValue80() {
            return value80;
        }

        public void setValue80(Object value80) {
            this.value80 = value80;
        }

        public Object getValue160() {
            return value160;
        }

        public void setValue160(Object value160) {
            this.value160 = value160;
        }

        public Object getOrigin() {
            return origin;
        }

        public void setOrigin(Object origin) {
            this.origin = origin;
        }
    }

    public static class ImageUrlsEntity {
        @SerializedName("60")
        private String value60;
        @SerializedName("360")
        private String value360;
        @SerializedName("750")
        private String value750;
        @SerializedName("900")
        private String value900;
        private String origin;

        public String getValue60() {
            return value60;
        }

        public void setValue60(String value60) {
            this.value60 = value60;
        }

        public String getValue360() {
            return value360;
        }

        public void setValue360(String value360) {
            this.value360 = value360;
        }

        public String getValue750() {
            return value750;
        }

        public void setValue750(String value750) {
            this.value750 = value750;
        }

        public String getValue900() {
            return value900;
        }

        public void setValue900(String value900) {
            this.value900 = value900;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }
    }
}
