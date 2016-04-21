package com.idreamsky.dreamroom.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.CompanyAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.CompanyEntity;
import com.idreamsky.dreamroom.ui.activity.CompanyDetailActivity;
import com.idreamsky.dreamroom.ui.custum.DividerItemDecoration;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.UrlUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by magical on 2016/4/17.
 * 家装公司
 */
@ContentView(R.layout.frag_decoration)
public class DecorationFragment extends BaseFragment implements SwipeRefreshLayout
        .OnRefreshListener, AbsRecyclerAdapter.OnItemClickListener {

    @ViewInject(R.id.frag_common_srl)
    private SwipeRefreshLayout srl;

    @ViewInject(R.id.frag_common_list)
    private RecyclerView recyclerView;

    @ViewInject(R.id.id_company_float)
    private FloatingActionButton floatButton;

    private Context mContext;
    private CompanyAdapter mAdapter;
    private int lastVisibleItem;
    private int mCurrentNum = 0;
    private String mCurrentCity = "深圳";
    private List<CompanyEntity> mDataList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            mCurrentNum++;
            loadData(ConstantString.STATE_LOAD_MORE, mCurrentCity);
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

        mAdapter = new CompanyAdapter(getActivity(), R.layout.item_adapter_company);
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

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "跳转城市列表", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData(int state, String cityName) {

        showProcee(mContext);

        HashMap hm = new HashMap();
        hm.put("city", mCurrentCity);
        hm.put("pn", String.valueOf(mCurrentNum));
        String needParams = UrlUtil.getMapString(hm);
        String url = Constants.Company.HOME_LIST_BASE + needParams;
        final int stateCode = state;
        VolleyUtil.requestString(url, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    mDataList = JsonUtil.resolveCompanyEntity(response);
                    if (null != mDataList && 0 != mDataList.size()) {
                        if (ConstantString.STATE_LOAD_REFRESH == stateCode) {
                            mAdapter.setDatas(mDataList);
                        } else if (ConstantString.STATE_LOAD_MORE == stateCode) {
                            mAdapter.addDatas(mDataList);
                        }
                    } else {
                        ToastUtil.ToastShort(mContext, ConstantString.NO_MORE_DATA);
                    }
                }
                dismissProcess();
                srl.setRefreshing(false);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                srl.setRefreshing(false);
                dismissProcess();
                ToastUtil.ToastShort(mContext, ConstantString.LOAD_FAILED);
            }
        });
    }

    @Override
    public void loadDatas() {
        mCurrentNum = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH, mCurrentCity);
    }

    @Override
    public void onRefresh() {
        mCurrentNum = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH, mCurrentCity);
    }

    @Override
    public void onItemClick(View v, int position) {

        if (position >= 0 && position < mAdapter.getItemCount()) {
            CompanyEntity one = mAdapter.getDatas().get(position);

            if (null != one) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(CompanyDetailActivity.BUNDLE_DATA, one);
                Intent intent = new Intent(mContext, CompanyDetailActivity.class);
                intent.putExtra(CompanyDetailActivity.BUNDLE_DATA, bundle);
                startActivity(intent);
            }
        }
    }


}
