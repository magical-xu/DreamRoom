package com.idreamsky.dreamroom.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.GeomancyAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.GeomancyEntity;
import com.idreamsky.dreamroom.ui.activity.GeomancyDetailActivity;
import com.idreamsky.dreamroom.ui.custum.DividerItemDecoration;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.L;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/4.
 * 装修风水
 */
@ContentView(R.layout.frag_geomancy)
public class GeomancyFragment extends BaseFragment implements AbsRecyclerAdapter
        .OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.frag_common_srl)
    private SwipeRefreshLayout srlayout;

    @ViewInject(R.id.frag_common_list)
    private RecyclerView recyclerView;

    private int mCurrentPage = 1;
    private List<GeomancyEntity.GeomancyBean> mDataList;
    private GeomancyAdapter mAdapter;
    private Context mContext;

    private int lastVisibleItem = 0;
    private boolean isPrepared;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPage++;
            loadData(ConstantString.STATE_LOAD_MORE);
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        //lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        mCurrentPage = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    @Override
    protected void init(View view) {

        mContext = getActivity();

        srlayout.setColorSchemeResources(R.color.refresh_first_color, R.color
                .refresh_second_color);
        srlayout.setOnRefreshListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration
                .VERTICAL_LIST));

        mAdapter = new GeomancyAdapter(mContext, R.layout.item_adapter_geomancy);
        recyclerView.setAdapter(mAdapter);

        initEvent(manager);
    }

    private void initEvent(final LinearLayoutManager manager) {

        mAdapter.setOnItemClickListener(this);
        srlayout.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                        recyclerView.getAdapter().getItemCount()) {
                    srlayout.setRefreshing(true);
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void loadDatas() {

//        mCurrentPage = 1;
//        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    private void loadData(int state) {

        final int i_state = state;

        showProcee(mContext);
        VolleyUtil.requestString(Constants.Geomancy.GEOMANCY_BASE_URL + mCurrentPage, new
                VolleyUtil.OnRequest() {

                    @Override
                    public void response(String url, String response) {
                        L.d(url);
                        if (null != response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (null != jsonObject.optJSONObject("data")) {
                                    GeomancyEntity entity = new Gson().fromJson(response,
                                            GeomancyEntity
                                                    .class);
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
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dismissProcess();
                    }

                    @Override
                    public void errorResponse(String url, VolleyError error) {
                        srlayout.setRefreshing(false);
                        dismissProcess();
                    }
                });
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    @Override
    public void onItemClick(View v, int position) {
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
}
