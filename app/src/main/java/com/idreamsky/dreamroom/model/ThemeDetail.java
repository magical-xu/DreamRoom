package com.idreamsky.dreamroom.model;

/**
 * Created by magical on 2016/4/24.
 */
public class ThemeDetail {
    /**
     * id : 856718
     * path : http://static-xiaoguotu.17house.com/web/xiaoguotu/201604/21/201604211046573256.png
     * like_desc :
     * 之所以大家都爱靠垫，决不仅仅是因为靠垫有着高颜值，更多的是它和我们的家很般配，也许靠垫从来都不是一个主角，但是它们默默把配角的戏演好，尽心尽力，这样我们怎么能不爱呢？最最关键的是，身边没人的时候抱抱它还能减少孤寂感。
     * title : 清新卧室
     */

    private String id;
    private String path;
    private String like_desc;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLike_desc() {
        return like_desc;
    }

    public void setLike_desc(String like_desc) {
        this.like_desc = like_desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
