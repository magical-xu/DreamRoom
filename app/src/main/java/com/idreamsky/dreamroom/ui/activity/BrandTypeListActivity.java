package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.BrandClazzAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.BrandClazz;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/10.
 * 品牌展厅 - 具体分类
 */
@ContentView(R.layout.activity_brand_type)
public class BrandTypeListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    public static final String BRAND_NAME = "brand_name";
    public static final String JUMP_URL = "jump_url";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_brand_type_list)
    private ListView lv;

    private String mUrl;
    private BrandClazzAdapter mAdapter;
    private List<BrandClazz> mDataList;

    @Override
    public void init() {
        String title = getIntent().getStringExtra(BRAND_NAME);
        mUrl = getIntent().getStringExtra(JUMP_URL);
        if (null != title) {
            tv_title.setText(title);
        }

        mAdapter = new BrandClazzAdapter(this, R.layout.item_adapter_brand_clazz);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void loadDatas() {

        if (null != mUrl) {
            VolleyUtil.requestString(mUrl, new VolleyUtil.OnRequest() {
                @Override
                public void response(String url, String response) {
                    mDataList = JsonUtil.resolveBrandClazz(response);
                    if (null != mDataList) {
                        mAdapter.setDatas(mDataList);
                    }
                }

                @Override
                public void errorResponse(String url, VolleyError error) {
                    ToastUtil.ToastShort(BrandTypeListActivity.this, ConstantString.LOAD_FAILED);
                }
            });
        }

    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mDataList && mDataList.size() != 0) {
            Intent intent = new Intent(this, BrandFurnitureActivity.class);
            intent.putExtra(BrandFurnitureActivity.FURNITURE_NAME, mDataList.get(position)
                    .getProductClass());
            intent.putExtra(BrandFurnitureActivity.BRAND_NAME, mDataList.get(position).getBrand());

            startActivity(intent, R.anim.fade, R.anim.hold);
        }
    }
}
