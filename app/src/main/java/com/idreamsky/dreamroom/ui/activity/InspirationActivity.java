package com.idreamsky.dreamroom.ui.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.InspirationAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.InspirationTheme;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/23.
 * 灵感专题
 */
@ContentView(R.layout.activity_inspiration)
public class InspirationActivity extends BaseActivity {

    @ViewInject(R.id.id_inspiration_list)
    private ListView listView;

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    private InspirationAdapter mAdapter;
    private List<InspirationTheme> mDataList;


    @Override
    public void init() {

        tv_title.setText(getResources().getString(R.string.nav_inspiration));

        mAdapter = new InspirationAdapter(this, R.layout.item_adapter_inspiration);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void loadDatas() {

        VolleyUtil.requestString(Constants.Inspiration.THEME_LIST, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {

                if (null != response) {
                    mDataList = JsonUtil.resolveInspirationTheme(response);
                    mAdapter.setDatas(mDataList);
                }
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                ToastUtil.ToastShort(InspirationActivity.this, ConstantString.LOAD_FAILED);
            }
        });
    }

    public void goBack(View view) {
        finish();
    }
}
