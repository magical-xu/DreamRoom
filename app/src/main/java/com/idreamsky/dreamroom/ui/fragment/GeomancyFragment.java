package com.idreamsky.dreamroom.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.GeomancyAdapter;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.GeomancyEntity;
import com.idreamsky.dreamroom.ui.activity.GeomancyDetailActivity;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/4.
 * 装修风水
 */
@ContentView(R.layout.frag_geomancy)
public class GeomancyFragment extends BaseFragment implements AdapterView.OnItemClickListener,
        AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.frag_geomancy_srl)
    private SwipeRefreshLayout srlayout;

    @ViewInject(R.id.frag_geomancy_list)
    private ListView listView;

    private int mCurrentPage = 1;
    private List<GeomancyEntity.GeomancyBean> mDataList;
    private GeomancyAdapter mAdapter;
    private Context mContext;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPage++;
            loadData(ConstantString.STATE_LOAD_MORE);
        }
    };

    @Override
    protected void init(View view) {

        mContext = getActivity();
        mAdapter = new GeomancyAdapter(mContext, R.layout.item_adapter_geomancy);
        listView.setAdapter(mAdapter);

        initEvent();
    }

    private void initEvent() {

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        srlayout.setOnRefreshListener(this);
    }

    @Override
    public void loadDatas() {

        mCurrentPage = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    private void loadData(int state) {

        final int i_state = state;

        VolleyUtil.requestString(Constants.Geomancy.GEOMANCY_BASE_URL + mCurrentPage, new
                VolleyUtil.OnRequest() {

                    @Override
                    public void response(String url, String response) {
                        GeomancyEntity entity = new Gson().fromJson(response, GeomancyEntity.class);
                        mDataList = entity.getData().getList();
                        if (mDataList != null) {
                            if (i_state == ConstantString.STATE_LOAD_REFRESH) {
                                mAdapter.setDatas(mDataList);
                            } else if (i_state == ConstantString.STATE_LOAD_MORE) {
                                mAdapter.addDatas(mDataList);
                            }
                            srlayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void errorResponse(String url, VolleyError error) {
                        srlayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String newsId = mAdapter.getDatas().get(position).newsId;
        String browse = mAdapter.getDatas().get(position).viewCount;
        String favor = mAdapter.getDatas().get(position).favNums;
        String comment = mAdapter.getDatas().get(position).replies;
        Intent intent = new Intent(mContext, GeomancyDetailActivity.class);
        intent.putExtra(GeomancyDetailActivity.NEWSID, newsId);
        intent.putExtra(GeomancyDetailActivity.BROWSE, browse);
        intent.putExtra(GeomancyDetailActivity.FAVORATE, favor);
        intent.putExtra(GeomancyDetailActivity.COMMENT, comment);
        startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
            totalItemCount) {
        // TODO: 2016/4/17 这么判断是有问题的
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            srlayout.setRefreshing(true);
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }
}
