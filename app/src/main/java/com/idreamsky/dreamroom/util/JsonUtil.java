package com.idreamsky.dreamroom.util;

import com.google.gson.Gson;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.model.GeomancyDetail;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析JSON工具类
 */
public class JsonUtil {

    /**
     * 拼接Json，处理不规则Json形式
     */
    public static String handleIrregularJson(String json) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"");
        sb.append("data");
        sb.append("\"");
        sb.append(":");
        sb.append(json);
        sb.append("}");
        return sb.toString();
    }

    /**
     * 解析家居图库数据
     */
    public static GalleryEntity getGDataByJSON(String json) {
        return new Gson().fromJson(json, GalleryEntity.class);
    }

    public static GeomancyDetail resolveGeomancyDetail(String json) {
        GeomancyDetail geomancyDetail = null;
        try {
            JSONObject dataObject = new JSONObject(json).optJSONObject("data");

            JSONObject currentNews = dataObject.optJSONObject("currentNews");
            geomancyDetail = new Gson().fromJson(currentNews.toString(), GeomancyDetail.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return geomancyDetail;
    }

}
