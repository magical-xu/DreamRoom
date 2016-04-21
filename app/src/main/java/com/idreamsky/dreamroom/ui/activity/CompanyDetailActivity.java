package com.idreamsky.dreamroom.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.model.CompanyEntity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/19.
 * 公司详情
 */
@ContentView(R.layout.activity_company_detail)
public class CompanyDetailActivity extends BaseActivity {

    public static final String BUNDLE_DATA = "data";
    public static final String INTENT_DATA = "intent";

    @ViewInject(R.id.id_common_title)
    private TextView tv_common_title;

    private CompanyEntity mData;

    @Override
    public void init() {

        tv_common_title.setText("公司详情");
        mData = (CompanyEntity) getIntent().getBundleExtra(BUNDLE_DATA).getSerializable
                (BUNDLE_DATA);
    }

    @Override
    public void loadDatas() {

    }

    public void goBack(View view) {
        finish();
    }
}
