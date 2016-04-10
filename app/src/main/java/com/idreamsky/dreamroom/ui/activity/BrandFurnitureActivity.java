package com.idreamsky.dreamroom.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * Created by magical on 2016/4/10.
 */
@ContentView(R.layout.activity_brand_furniture)
public class BrandFurnitureActivity extends BaseActivity {

    public static final String FURNITURE_NAME = "furniture_name";
    public static final String BRAND_NAME = "brand_name";
    public static final String PARAM_BRAND = "brand";
    public static final String PARAM_PRODUCT = "productClass";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    private String productClassName;
    private String brandName;
    private String mUrl;

    //&brand=&productClass=%E6%A1%8C%E5%AD%90  &pn=1

    @Override
    public void init() {

        productClassName = getIntent().getStringExtra(FURNITURE_NAME);
        brandName = getIntent().getStringExtra(BRAND_NAME);
        if (null != productClassName) {
            tv_title.setText(productClassName);
        }
        HashMap<String, String> param = new HashMap<>();
        param.put(PARAM_BRAND, brandName);
        param.put(PARAM_PRODUCT, productClassName);

    }

    @Override
    public void loadDatas() {

    }

    public void goBack(View view) {
        finish();
    }
}
