package com.idreamsky.dreamroom.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.GalleryAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.ui.custum.ImageDialog;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/3/7.
 */
@ContentView(R.layout.activity_gallery)
public class GalleryActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, AbsRecyclerAdapter.OnItemClickListener {

    @ViewInject(R.id.id_common_title)
    private TextView tv_commonTitle;

    @ViewInject(R.id.id_srl_gallery)
    private SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.id_rc_gallery)
    private RecyclerView recyclerView;

    private List<GalleryEntity.GalleryModel> data;
    private List<GalleryEntity.GalleryModel> allDatas;

    private GalleryAdapter mAdapter;
    private static int SPAN_COUNT = 2;
    private static int STATE_LOAD_REFRESH = 0;
    private static int STATE_LOAD_MORE = 1;
    private int mCurrentNum = 0;
    private int lastVisibleItem;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                mCurrentNum++;
                loadData(Constants.Gallery.GALLERY_ALL_COLOR_URL, STATE_LOAD_MORE);
            }
        }
    };

    @Override
    public void init() {

        tv_commonTitle.setText("家居图库");

        // 刷新时，指示器旋转后变化的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_first_color, R.color.refresh_second_color);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new GalleryAdapter(this, R.layout.item_card_img);

        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    swipeRefreshLayout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    handler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            }
        });

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void loadDatas() {

        mCurrentNum = 1;
        loadData(Constants.Gallery.GALLERY_ALL_COLOR_URL, STATE_LOAD_REFRESH);

    }

    private void loadData(String url, int state) {
        String newUrl = url + mCurrentNum;
        final int mode = state;

        VolleyUtil.requestString(newUrl, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                String json = JsonUtil.handleIrregularJson(response);
                GalleryEntity entity = JsonUtil.getGDataByJSON(json);
                data = entity.getData();
                if (mode == STATE_LOAD_REFRESH) {
                    mAdapter.setDatas(data);
                } else if (mode == STATE_LOAD_MORE) {
                    mAdapter.addDatas(data);
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                shortToast("加载失败");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        mCurrentNum = 1;
        loadData(Constants.Gallery.GALLERY_ALL_COLOR_URL, STATE_LOAD_REFRESH);
    }

    /**
     * 生成随机高度
     */
    private List<Integer> generateRandomHeight(int size) {
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            randomList.add((int) (Math.random() * 300) + 200);
        }
        return randomList;
    }

    @Override
    public void onItemClick(View v, int position) {
        // TODO: 2016/3/20 大图查看模式
        allDatas = mAdapter.getDatas();
        new ImageDialog(this, allDatas, position).show();
    }
}
