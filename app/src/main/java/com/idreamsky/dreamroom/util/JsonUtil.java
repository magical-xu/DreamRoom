package com.idreamsky.dreamroom.util;

import com.google.gson.Gson;
import com.idreamsky.dreamroom.model.GalleryEntity;

/**
 * 解析JSON工具类
 */
public class JsonUtil {

    /**
     * 拼接Json，处理不规则Json形式
     */
    public static String handleIrregularJson(String json){

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
        return new Gson().fromJson(json,GalleryEntity.class);
    }

//    /**
//     * 解析推荐页数据
//     * @param json
//     * @return
//     */
//    public static RecmentEntity getRDataByJSON(String json){
//        RecmentEntity recmentEntity = null;
//        if(json != null){
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                int code = jsonObject.getInt("code");
//                if(code == 1){//获取数据成功
//                    jsonObject = jsonObject.getJSONObject("obj");
//                    recmentEntity = new Gson().fromJson(jsonObject.toString(), RecmentEntity.class);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return recmentEntity;
//    }
//
//
//    /**
//     * 解析推荐页 猜你喜欢 数据
//     * @param json
//     * @return
//     */
//    public static RecomentCustomEntity getRCDataByJSON(String json){
//        RecomentCustomEntity recomentCustomEntity = new RecomentCustomEntity();
//        List<List<RecomentCustomEntity.Datas>> alldatas = null;
//        if(json != null){
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                int code = jsonObject.getInt("code");
//                if(code == 1){//获取数据成功
//                    alldatas = new ArrayList<>();
//                    jsonObject = jsonObject.getJSONObject("obj");
//                    jsonObject = jsonObject.getJSONObject("customized");
//                    recomentCustomEntity.setTitle(jsonObject.getString("title"));
//
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    List<RecomentCustomEntity.Datas> datasList = null;
//                    for(int i = 0; i < jsonArray.length(); i++){
//                        if(i % 2 == 0){
//                            if(datasList != null){
//                                alldatas.add(datasList);
//                            }
//                            datasList = new ArrayList<>();
//                        }
//                        RecomentCustomEntity.Datas datas = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), RecomentCustomEntity.Datas.class);
//                        datasList.add(datas);
//                    }
//                    recomentCustomEntity.setDatas(alldatas);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return recomentCustomEntity;
//    }
}
