package com.idreamsky.dreamroom.util;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by magical on 2016/4/10.
 * 请求地址拼装
 */
public class UrlUtil {

    //中文编码一次utf-8
    public static String chineseEncode(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        String utf8String = null;
        try {
            utf8String = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf8String;
    }

    /**
     * 将map参数转成String参数 并转一次utf8
     *
     * @param param
     * @return
     */
    public static String getMapString(Map<String, String> param) {
        //拼接到 url后
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keys = param.keySet();
        //int i = 0;

        for (String key : keys) {
            //if (i > 0) {
            stringBuffer.append("&");
            //}
            //进行一次utf8
            String value = chineseEncode(param.get(key));
            stringBuffer.append(key + "=" + value);
            //i++;
        }
        return stringBuffer.toString();
    }
}
