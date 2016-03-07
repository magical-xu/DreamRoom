package com.idreamsky.dreamroom.model;

import java.util.List;

/**
 * Created by magical on 2016/3/7.
 */
public class GalleryEntity {

    private List<GalleryModel> data;

    public List<GalleryModel> getData() {
        return data;
    }

    public void setData(List<GalleryModel> data) {
        this.data = data;
    }

    public class GalleryModel{
        public String id;

        public String title;

        public String pic;//点进去的具体图片

        public String pic3;// gallery中的视图Url
    }
}
