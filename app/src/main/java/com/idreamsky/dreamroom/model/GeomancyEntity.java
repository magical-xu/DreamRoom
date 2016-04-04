package com.idreamsky.dreamroom.model;

import java.util.List;

/**
 * Created by magical on 2016/4/4.
 * 装修风水首页
 */
public class GeomancyEntity {

    private GeomancyModel data;

    public GeomancyModel getData() {
        return data;
    }

    public void setData(GeomancyModel data) {
        this.data = data;
    }

    public class GeomancyModel {
        private List<GeomancyBean> list;

        public List<GeomancyBean> getList() {
            return list;
        }

        public void setList(List<GeomancyBean> list) {
            this.list = list;
        }
    }

    public class GeomancyBean {
        public String newsId;//跳转所需ID

        public String title;//标题

        public String viewCount;//浏览数

        public String replies;//回复数

        public String image;//图片

        public String favNums;//收藏数
    }


}
