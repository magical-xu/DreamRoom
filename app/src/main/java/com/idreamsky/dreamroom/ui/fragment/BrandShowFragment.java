package com.idreamsky.dreamroom.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.BrandHomeAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.model.BrandHomeEntity.BrandHomeModel;
import com.idreamsky.dreamroom.ui.activity.BrandTypeListActivity;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/4/10.
 * 品牌展厅
 */
@ContentView(R.layout.frag_brandshow)
public class BrandShowFragment extends BaseFragment implements SwipeRefreshLayout
        .OnRefreshListener, AbsRecyclerAdapter.OnItemClickListener {

    @ViewInject(R.id.frag_brand_srl)
    private SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.frag_brand_list)
    private RecyclerView recyclerView;

    private Context mContext;
    private List<BrandHomeModel> dataList;
    private BrandHomeAdapter mAdapter;
    private static final int SPAN_COUNT = 2;

    @Override
    protected void init(View view) {

        mContext = getActivity();

        // 刷新时，指示器旋转后变化的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_first_color, R.color
                .refresh_second_color);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new BrandHomeAdapter(mContext, R.layout.item_card_img);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(SPAN_COUNT,
                StaggeredGridLayoutManager
                        .VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void loadDatas() {

        loadData();
    }

    private void loadData() {
        VolleyUtil.requestString(Constants.Brand.BRAND_HOME_URL, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    dataList = JsonUtil.resolveBrandHomeEntity(response);
                    if (null != dataList && dataList.size() != 0) {
                        mAdapter.setRandomItemHeight(generateRandomHeight(dataList.size()));
                        mAdapter.setDatas(dataList);
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                ((BaseActivity) mContext).shortToast("加载失败");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onItemClick(View v, int position) {
        String brandName = mAdapter.getDatas().get(position).getBrand();
        Intent intent = new Intent(mContext, BrandTypeListActivity.class);
        try {
            String jumpUrl = Constants.Brand.BRAND_BASE_URL + URLEncoder.encode(brandName, "utf-8");
            intent.putExtra(BrandTypeListActivity.BRAND_NAME, brandName);
            intent.putExtra(BrandTypeListActivity.JUMP_URL, jumpUrl);
            ((BaseActivity) mContext).startActivity(intent, R.anim.zoomin, R.anim.zoomout);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
}
