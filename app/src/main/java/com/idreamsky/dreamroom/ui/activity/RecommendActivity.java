package com.idreamsky.dreamroom.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.RecommendAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.Recommend;
import com.idreamsky.dreamroom.ui.custum.DividerItemDecoration;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/23.
 * 精彩推荐
 */
@ContentView(R.layout.activity_recommend)
public class RecommendActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener {

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.frag_common_srl)
    private SwipeRefreshLayout srl;

    @ViewInject(R.id.frag_common_list)
    private RecyclerView recyclerView;

    private int mCurrentNum = 0;
    private int lastVisibleItem;
    private List<Recommend> mDataList;
    private RecommendAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentNum++;
            loadData(ConstantString.STATE_LOAD_MORE);
        }
    };


    @Override
    public void init() {

        tv_title.setText(getResources().getString(R.string.nav_recommend));

        srl.setColorSchemeResources(R.color.refresh_first_color, R.color
                .refresh_second_color);
        srl.setOnRefreshListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL_LIST));

        mAdapter = new RecommendAdapter(this, R.layout.item_adapter_recommend);
        recyclerView.setAdapter(mAdapter);

        //mAdapter.setOnItemClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                        recyclerView.getAdapter().getItemCount()) {
                    srl.setRefreshing(true);
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

        mCurrentNum = 1;
        loadData(STATE_LOAD_REFRESH);
    }

    private void loadData(int state) {

        int stateCode = state;
        String url = Constants.Recommand.Home + mCurrentNum;
        VolleyUtil.requestString(url, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    mDataList = JsonUtil.resolveRecommend(response);
                    if (null != mDataList && 0 != mDataList.size()) {
                        mAdapter.setDatas(mDataList);
                    }

                }
                srl.setRefreshing(false);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                ToastUtil.ToastShort(RecommendActivity.this, ConstantString.LOAD_FAILED);
                srl.setRefreshing(false);
            }
        });
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onRefresh() {
        mCurrentNum = 1;
        loadData(STATE_LOAD_REFRESH);
    }
}
