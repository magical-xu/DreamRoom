package com.idreamsky.dreamroom.model;

/**
 * Created by magical on 2016/4/23.
 * 灵感专题
 */
public class InspirationTheme {

    /**
     * id : 605144
     * title : 靠垫有的不只是高颜值
     * like_num : 0
     * comment_count : 0
     * url : http://static-xiaoguotu.17house.com/web/xiaoguotu/201604/21/201604211046573256.png
     * desc :
     * 之所以大家都爱靠垫，决不仅仅是因为靠垫有着高颜值，更多的是它和我们的家很般配，也许靠垫从来都不是一个主角，但是它们默默把配角的戏演好，尽心尽力，这样我们怎么能不爱呢？最最关键的是，身边没人的时候抱抱它还能减少孤寂感。
     */

    private String id;
    private String title;
    private String like_num;
    private String comment_count;
    private String url;
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
