package com.idreamsky.dreamroom.model;

/**
 * Created by magical on 2016/4/28.
 */
public class GeomancyBean {

    public String newsId;//跳转所需ID

    public String title;//标题

    public String viewCount;//浏览数

    public String replies;//回复数

    public String image;//图片

    public String favNums;//收藏数

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
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
