package com.idreamsky.dreamroom.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.GalleryAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/3/7.
 */
@ContentView(R.layout.activity_gallery)
public class GalleryActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.id_common_title)
    private TextView tv_commonTitle;

    @ViewInject(R.id.id_srl_gallery)
    private SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.id_rc_gallery)
    private RecyclerView recyclerView;

    private List<GalleryEntity.GalleryModel> data;
    private GalleryAdapter mAdapter;
    private static int SPAN_COUNT = 2;

    @Override
    public void init() {

        tv_commonTitle.setText("家居图库");

        // 刷新时，指示器旋转后变化的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new GalleryAdapter(this, R.layout.item_card_img);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);


        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadDatas() {

        VolleyUtil.requestString(Constants.URL.GALLERY_ALL_URL, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                String json = JsonUtil.handleIrregularJson(response);
                GalleryEntity entity = JsonUtil.getGDataByJSON(json);
                data = entity.getData();
                mAdapter.setDatas(data);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
            }
        });
    }

    @Override
    public void onRefresh() {

    }
}
