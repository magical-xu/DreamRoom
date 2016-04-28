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

}
