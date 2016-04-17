package com.idreamsky.dreamroom.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.EventAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.EventEntity;
import com.idreamsky.dreamroom.ui.activity.EventDetailActivity;
import com.idreamsky.dreamroom.ui.custum.DividerItemDecoration;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/17.
 * 优选活动
 */
@ContentView(R.layout.frag_event)
public class EventFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        AbsRecyclerAdapter.OnItemClickListener {

    @ViewInject(R.id.frag_common_srl)
    private SwipeRefreshLayout srl;

    @ViewInject(R.id.frag_common_list)
    private RecyclerView recyclerView;

    private Context mContext;
    private int mCurrentNum = 0;
    private EventAdapter mAdapter;
    private int lastVisibleItem;
    private List<EventEntity> mDataList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentNum++;
            loadData(ConstantString.STATE_LOAD_MORE);
        }
    };

    @Override
    protected void init(View view) {

        mContext = getActivity();

        srl.setColorSchemeResources(R.color.refresh_first_color, R.color
                .refresh_second_color);
        srl.setOnRefreshListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration
                .VERTICAL_LIST));

        mAdapter = new EventAdapter(getActivity(), R.layout.item_adapter_event);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
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
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    private void loadData(int state) {

        String url = Constants.Event.EVENT_HOME + mCurrentNum;
        final int stateCode = state;
        VolleyUtil.requestString(url, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    mDataList = JsonUtil.resolveEventEntity(response);
                    if (null != mDataList && 0 != mDataList.size()) {
                        if (ConstantString.STATE_LOAD_REFRESH == stateCode) {
                            mAdapter.setDatas(mDataList);
                        } else if (ConstantString.STATE_LOAD_MORE == stateCode) {
                            mAdapter.addDatas(mDataList);
                        }
                    }
                }
                srl.setRefreshing(false);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                srl.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        mCurrentNum = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(mContext, EventDetailActivity.class);
        EventEntity one = mAdapter.getDatas().get(position);
        if (null != one) {
            intent.putExtra(EventDetailActivity.DATA, one);
            startActivity(intent);
        }
    }

}
