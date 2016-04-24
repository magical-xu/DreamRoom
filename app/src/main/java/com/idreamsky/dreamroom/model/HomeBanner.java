package com.idreamsky.dreamroom.model;

/**
 * Created by magical on 2016/4/24.
 */
public class HomeBanner {


    /**
     * imagesrc : http://appmanager.17house.com/upload/20160413/570df93a05056_t.jpg
     * imagesrc2 : http://appmanager.17house.com/upload/20160413/570df93a0f458_t.jpg
     * tid :
     * type : 4
     * banner_url : http://m.17house.com/tuan/2444.html
     */

    private String imagesrc;
    private String imagesrc2;
    private String tid;
    private int type;
    private String banner_url;

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getImagesrc2() {
        return imagesrc2;
    }

    public void setImagesrc2(String imagesrc2) {
        this.imagesrc2 = imagesrc2;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }
}
