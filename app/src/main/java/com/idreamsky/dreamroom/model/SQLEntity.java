package com.idreamsky.dreamroom.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by magical on 2016/4/28.
 * 标准数据库 JavaBean   - 装修风水
 */
@Table(name = "SQLEntity")
public class SQLEntity {

    @Column(name = "newsId", isId = true)
    public int newsId;//跳转所需ID

    @Column(name = "title")
    public String title;//标题

    @Column(name = "viewCount")
    public String viewCount;//浏览数

    @Column(name = "replies")
    public String replies;//回复数

    @Column(name = "image")
    public String image;//图片

    @Column(name = "favNums")
    public String favNums;//收藏数

    @Override
    public String toString() {
        return "SQLEntity{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", replies='" + replies + '\'' +
                ", image='" + image + '\'' +
                ", favNums='" + favNums + '\'' +
                '}';
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFavNums() {
        return favNums;
    }

    public void setFavNums(String favNums) {
        this.favNums = favNums;
    }
}
