package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.BrandDetailAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.BrandFurnitureDetail;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.UrlUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by magical on 2016/4/10.
 * 品牌展厅- 具体家具界面
 */
@ContentView(R.layout.activity_brand_furniture)
public class BrandFurnitureActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener, AbsRecyclerAdapter
        .OnItemClickListener {

    public static final String FURNITURE_NAME = "furniture_name";
    public static final String BRAND_NAME = "brand_name";
    public static final String PARAM_BRAND = "brand";
    public static final String PARAM_PRODUCT = "productClass";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.frag_furniture_list)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.frag_furniture_srl)
    private SwipeRefreshLayout srl;

    private String productClassName;
    private String brandName;
    private String mUrl;
    private BrandDetailAdapter mAdapter;
    private List<BrandFurnitureDetail> mDataList;

    private int mCurrentNum = 0;
    private final static int SPAN_COUNT = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentNum++;
            loadData(ConstantString.STATE_LOAD_MORE);
        }
    };
    private int lastVisibleItem;

    @Override
    public void init() {

        productClassName = getIntent().getStringExtra(FURNITURE_NAME);
        brandName = getIntent().getStringExtra(BRAND_NAME);
        if (null != productClassName) {
            tv_title.setText(productClassName);
        }
        HashMap<String, String> param = new HashMap<>();
        param.put(PARAM_BRAND, brandName);
        param.put(PARAM_PRODUCT, productClassName);
        mUrl = Constants.Brand.BRAND_FURNITURE_BASE_URL + UrlUtil.getMapString(param) + "&pn=";

        mAdapter = new BrandDetailAdapter(this, R.layout.item_adapter_brand_detail);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    srl.setRefreshing(true);
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            }
        });


        mAdapter.setOnItemClickListener(this);
        srl.setOnRefreshListener(this);
    }

    @Override
    public void loadDatas() {

        mCurrentNum = 1;
        loadData(ConstantString.STATE_LOAD_REFRESH);
    }

    public void goBack(View view) {
        finish();
    }

    private void loadData(int state) {

        final int stateCode = state;
        String url = mUrl + mCurrentNum;
        VolleyUtil.requestString(url, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    String jsonString = JsonUtil.handleIrregularJson(response);
                    List<BrandFurnitureDetail> tmpList = JsonUtil.resolveBrandDetail(jsonString);
                    if (null != tmpList && 0 != tmpList.size()) {
                        //坑爹啊这里 ，没数据返回的JSON不对强行加了一个
                        if (null == tmpList.get(0).getId()) {
                            shortToast(ConstantString.NO_MORE_DATA);
                            srl.setRefreshing(false);
                            return;
                        }
                        if (ConstantString.STATE_LOAD_REFRESH == stateCode) {
                            mAdapter.setDatas(tmpList);
                        } else if (ConstantString.STATE_LOAD_MORE == stateCode) {
                            mAdapter.addDatas(tmpList);
                        }
                    } else {
                        shortToast(ConstantString.LOAD_FAILED);
                    }
                }
                srl.setRefreshing(false);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
                srl.setRefreshing(false);
                shortToast(ConstantString.LOAD_FAILED);
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
        BrandFurnitureDetail one = mAdapter.getDatas().get(position);
        if (null != one) {
            Intent intent = new Intent(this, BrandProductActivity.class);
            intent.putExtra(BrandProductActivity.BRAND, one.getBrand());
            intent.putExtra(BrandProductActivity.NAME, one.getProductName());
            intent.putExtra(BrandProductActivity.PRICE, one.getPrice());
            intent.putExtra(BrandProductActivity.DES, one.getDescription());
            intent.putExtra(BrandProductActivity.IMG, one.getPic());
            intent.putExtra(BrandProductActivity.LINK, one.getTaobaolinks());

            startActivity(intent, R.anim.hyperspace_in, R.anim.hyperspace_out);
        }
    }
}
