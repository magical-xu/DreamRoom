package com.idreamsky.dreamroom.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.GeomancyAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.GeomancyBean;
import com.idreamsky.dreamroom.model.SQLEntity;
import com.idreamsky.dreamroom.ui.custum.DividerItemDecoration;
import com.idreamsky.dreamroom.util.DBUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.TransUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/5/10.
 * 装修风水收藏
 */
@ContentView(R.layout.activity_collect_geomancy)
public class ColGeoActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener, AbsRecyclerAdapter.OnItemClickListener, AbsRecyclerAdapter
        .OnItemLongClickListener {

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.frag_common_srl)
    private SwipeRefreshLayout srl;

    @ViewInject(R.id.frag_common_list)
    private RecyclerView recyclerView;

    private List<SQLEntity> mSQList;
    private List<GeomancyBean> mDataList;

    private GeomancyAdapter mAdapter;

    private int lastVisibleItem = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            loadData(ConstantString.STATE_LOAD_MORE);
        }
    };

    @Override
    public void init() {

        tv_title.setText("装修风水-收藏");

        srl.setColorSchemeResources(R.color.refresh_first_color, R.color
                .refresh_second_color);
        srl.setOnRefreshListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL_LIST));

        mAdapter = new GeomancyAdapter(this, R.layout.item_adapter_geomancy);
        recyclerView.setAdapter(mAdapter);

        initEvent(manager);
    }

    private void initEvent(final LinearLayoutManager manager) {

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        srl.setOnRefreshListener(this);
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

        loadData(STATE_LOAD_REFRESH);
    }

    private void loadData(int state) {

        mDataList = getDataFromDb();
        if (null != mDataList && 0 != mDataList.size()) {

            if (STATE_LOAD_REFRESH == state) {

                mAdapter.setDatas(mDataList);

            } else if (STATE_LOAD_MORE == state) {

                mAdapter.addDatas(mDataList);
            }
        } else {
            ToastUtil.ToastShort(this, ConstantString.LOAD_FAILED);
        }

        srl.setRefreshing(false);
    }

    /**
     * 查询数据库 获取数据
     *
     * @return
     */
    private List<GeomancyBean> getDataFromDb() {

        List<GeomancyBean> tmpData = null;
        DbManager db = x.getDb(DBUtil.getInstance().getGeoDB());
        try {
            mSQList = db.selector(SQLEntity.class).limit(10).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (null != mSQList && 0 != mSQList.size()) {
            tmpData = new ArrayList<>();
            for (int i = 0; i < mSQList.size(); i++) {
                GeomancyBean one = TransUtil.transSQLEntity2Geo(mSQList.get(i));
                tmpData.add(one);
            }
        }
        return tmpData;
    }

    @Override
    public void onRefresh() {
        loadData(STATE_LOAD_REFRESH);
    }

    @Override
    public void onItemClick(View v, int position) {
        String newsId = mAdapter.getDatas().get(position).newsId;
        String browse = mAdapter.getDatas().get(position).viewCount;
        String favor = mAdapter.getDatas().get(position).favNums;
        String comment = mAdapter.getDatas().get(position).replies;
        Intent intent = new Intent(this, GeomancyDetailActivity.class);
        intent.putExtra(GeomancyDetailActivity.NEWSID, newsId);
        intent.putExtra(GeomancyDetailActivity.BROWSE, browse);
        intent.putExtra(GeomancyDetailActivity.FAVORATE, favor);
        intent.putExtra(GeomancyDetailActivity.COMMENT, comment);
        startActivity(intent);
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onItemLongClick(View v, int position) {

        final int index = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("删除")
                .setIcon(R.drawable.dr_icon)
                .setMessage("确定么？？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GeomancyBean bean = mAdapter.getDatas().get(index);
                SQLEntity entity = TransUtil.transGeo2SQLEntity(bean);
                DbManager db = x.getDb(DBUtil.getInstance().getGeoDB());
                try {
                    db.delete(entity);
                    ToastUtil.ToastShort(ColGeoActivity.this, "删除成功");
                    loadData(STATE_LOAD_REFRESH);
                } catch (DbException e) {
                    e.printStackTrace();
                    ToastUtil.ToastShort(ColGeoActivity.this, "删除失败");
                }

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}

