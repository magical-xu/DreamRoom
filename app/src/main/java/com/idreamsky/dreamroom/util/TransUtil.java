package com.idreamsky.dreamroom.util;

import com.idreamsky.dreamroom.model.GeomancyBean;
import com.idreamsky.dreamroom.model.SQLEntity;

/**
 * Created by magical on 2016/4/28.
 * 将不规范的数据集 转成规定的Bean
 */
public class TransUtil {

    public static SQLEntity transGeo2SQLEntity(GeomancyBean bean) {

        SQLEntity entity = null;
        if (null != bean) {
            entity = new SQLEntity();
            entity.setNewsId(Integer.parseInt(bean.getNewsId()));
            entity.setTitle(bean.getTitle());
            entity.setViewCount(bean.getViewCount());
            entity.setReplies(bean.getReplies());
            entity.setFavNums(bean.getFavNums());
            entity.setImage(bean.getImage());
        }
        return entity;
    }

    public static GeomancyBean transSQLEntity2Geo(SQLEntity entity) {

        GeomancyBean bean = null;
        if (null != entity) {
            bean = new GeomancyBean();
            bean.setNewsId(String.valueOf(entity.getNewsId()));
            bean.setTitle(entity.getTitle());
            bean.setViewCount(entity.getViewCount());
            bean.setReplies(entity.getReplies());
            bean.setFavNums(entity.getFavNums());
            bean.setImage(entity.getImage());
        }
        return bean;
    }
}
