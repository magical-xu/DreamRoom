package com.idreamsky.dreamroom.util;

import com.google.gson.Gson;
import com.idreamsky.dreamroom.model.BrandClazz;
import com.idreamsky.dreamroom.model.BrandHomeEntity;
import com.idreamsky.dreamroom.model.BrandHomeEntity.BrandHomeModel;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.model.GeomancyDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 解析装修风水
     *
     * @param json
     * @return
     */
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

    /**
     * 解析品牌展厅
     *
     * @param json
     * @return
     */
    public static List<BrandHomeModel> resolveBrandHomeEntity(String json) {
        BrandHomeEntity entity = new Gson().fromJson(json, BrandHomeEntity.class);
        return null != entity ? entity.getResult() : null;
    }

    /**
     * 解析品牌中心，家具分类
     *
     * @param json
     * @return
     */
    public static List<BrandClazz> resolveBrandClazz(String json) {
        List<BrandClazz> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                BrandClazz one = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),
                        BrandClazz.class);
                list.add(one);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
