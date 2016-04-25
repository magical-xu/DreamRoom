package com.idreamsky.dreamroom.util;

import com.google.gson.Gson;
import com.idreamsky.dreamroom.model.BrandClazz;
import com.idreamsky.dreamroom.model.BrandFurnitureDetail;
import com.idreamsky.dreamroom.model.BrandHomeEntity;
import com.idreamsky.dreamroom.model.BrandHomeEntity.BrandHomeModel;
import com.idreamsky.dreamroom.model.CompanyEntity;
import com.idreamsky.dreamroom.model.EventEntity;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.model.GeomancyDetail;
import com.idreamsky.dreamroom.model.HomeBanner;
import com.idreamsky.dreamroom.model.HomeHot;
import com.idreamsky.dreamroom.model.InspirationTheme;
import com.idreamsky.dreamroom.model.ThemeDetail;

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

    /**
     * 解析品牌展厅 - 具体家具
     *
     * @return
     */
    public static List<BrandFurnitureDetail> resolveBrandDetail(String json) {
        List<BrandFurnitureDetail> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                BrandFurnitureDetail one = new Gson().fromJson(jsonArray.getJSONObject(i)
                        .toString(), BrandFurnitureDetail.class);
                list.add(one);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析 优选活动
     *
     * @param json
     * @return
     */
    public static List<EventEntity> resolveEventEntity(String json) {
        List<EventEntity> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (null != jsonArray && 0 != jsonArray.length()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    EventEntity one = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),
                            EventEntity.class);
                    if (null != one.getLogo()) {
                        list.add(one);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 解析   家装公司首页List
     *
     * @param json
     * @return
     */
    public static List<CompanyEntity> resolveCompanyEntity(String json) {
        List<CompanyEntity> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (null != jsonArray && 0 != jsonArray.length()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    CompanyEntity one = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),
                            CompanyEntity.class);
                    if (null != one.getLogo()) {
                        list.add(one);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 解析灵感专题
     *
     * @param json
     * @return
     */
    public static List<InspirationTheme> resolveInspirationTheme(String json) {
        List<InspirationTheme> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            if (null != jsonArray && 0 != jsonArray.length()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    InspirationTheme one = new Gson().fromJson(jsonArray.getJSONObject(i)
                                    .toString(),
                            InspirationTheme.class);
                    if (null != one.getId()) {
                        list.add(one);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 解析专题详情
     *
     * @param json
     * @return
     */
    public static List<ThemeDetail> resolveThemeDetail(String json) {
        List<ThemeDetail> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = jsonObject.optJSONObject("data");
            JSONArray jsonArray = dataObject.optJSONArray("images");
            if (null != jsonArray && 0 != jsonArray.length()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    ThemeDetail one = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),
                            ThemeDetail.class);
                    if (null != one.getId()) {
                        list.add(one);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 解析 首页Banner
     *
     * @param json
     * @return
     */
    public static List<HomeBanner> resolveHomeBanner(String json) {
        List<HomeBanner> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            if (null != jsonArray && 0 != jsonArray.length()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    HomeBanner one = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),
                            HomeBanner.class);
                    if (null != one.getBanner_url()) {
                        list.add(one);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 解析 首页 热门话题
     *
     * @return
     */
    public static List<HomeHot> resolveHomeHot(String json) {
        List<HomeHot> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("items");
            if (null != jsonArray && 0 != jsonArray.length()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    HomeHot one = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),
                            HomeHot.class);
                    if (null != one.getName()) {
                        list.add(one);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
