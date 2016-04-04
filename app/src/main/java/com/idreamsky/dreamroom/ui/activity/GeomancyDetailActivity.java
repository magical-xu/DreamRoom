package com.idreamsky.dreamroom.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.model.GeomancyDetail;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/4.
 * 装修风水文章详情页
 */
@ContentView(R.layout.activity_geomancy_detail)
public class GeomancyDetailActivity extends BaseActivity {

    public static final String NEWSID = "newsId";
    public static final String BROWSE = "browse";
    public static final String FAVORATE = "favorate";
    public static final String COMMENT = "comment";

    private String newsId, browse, favor, comment;

    @ViewInject(R.id.id_common_title)
    public TextView tv_common_title;

    @ViewInject(R.id.id_geomancy_detail_title)
    public TextView tv_detail_title;

    @ViewInject(R.id.id_item_geomancy_browse)
    public TextView tv_browse;

    @ViewInject(R.id.id_item_geomancy_far)
    public TextView tv_far;

    @ViewInject(R.id.id_item_geomancy_comment)
    public TextView tv_comment;

    @ViewInject(R.id.id_geomancy_detail_ll_container)
    public LinearLayout container;

    private GeomancyDetail mData;

    @Override
    public void init() {
        newsId = getIntent().getStringExtra(NEWSID);
        browse = getIntent().getStringExtra(BROWSE);
        favor = getIntent().getStringExtra(FAVORATE);
        comment = getIntent().getStringExtra(COMMENT);
        tv_common_title.setText("文章详情");
        tv_browse.setText(browse);
        tv_far.setText(favor);
        tv_comment.setText(comment);
    }

    @Override
    public void loadDatas() {

        VolleyUtil.requestString(Constants.Geomancy.GEOMANCY_DETAIL_URL + newsId, new VolleyUtil
                .OnRequest() {

            @Override
            public void response(String url, String response) {
                mData = JsonUtil.resolveGeomancyDetail(response);
                configViews(mData);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {

            }
        });
    }

    public void configViews(GeomancyDetail detail) {

        tv_detail_title.setText(detail.title);
        String[] array = detail.content;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < array.length; i++) {
            if (array[i].startsWith("http")) {
                params = new LinearLayout.LayoutParams(LinearLayout
                        .LayoutParams.MATCH_PARENT, 500);
                params.topMargin = 30;
                ImageView iv = new ImageView(this);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                UniversalUtil.displayImage(array[i], iv, null);
                container.addView(iv, params);
            } else {
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.topMargin = 30;
                TextView tv = new TextView(this);
                tv.setText(array[i]);
                tv.setTextSize(17);
                tv.setTextColor(Color.parseColor("#6D6D6D"));
                container.addView(tv, params);
            }
        }
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
