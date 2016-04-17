package com.idreamsky.dreamroom.model;

import java.io.Serializable;

/**
 * Created by magical on 2016/4/17.
 * 优选活动
 */
public class EventEntity implements Serializable {

    /**
     * area : 上海
     * areaDetail : 浦东新区国展路1099号（上海世博展览馆）
     * background : http://jzzn.qiniudn.com/1415172214921.jpg
     * brand : 科隆
     * city : 上海
     * content : 嵌入式
     * id : 90
     * logo : http://jzzn.qiniudn.com/1415172188859.jpg
     * name : （上篇）科隆国际厨房展媒体见面会在上海召开
     * time : 2014年11月5日至2014年11月7日
     * type : 3
     */

    private String area;
    private String areaDetail;
    private String background;
    private String brand;
    private String city;
    private String content;
    private String id;
    private String logo;
    private String name;
    private String time;
    private String type;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
