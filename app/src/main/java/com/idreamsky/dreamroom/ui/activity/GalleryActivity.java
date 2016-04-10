package com.idreamsky.dreamroom.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.GalleryAdapter;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.ui.custum.ImageDialog;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.L;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/3/7.
 */
@ContentView(R.layout.activity_gallery)
public class GalleryActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener, AbsRecyclerAdapter
        .OnItemClickListener, View.OnClickListener {

    private static final int TYPE_SPACE = 0;
    private static final int TYPE_COLOR = 1;

    @ViewInject(R.id.id_common_title)
    private TextView tv_commonTitle;

    @ViewInject(R.id.id_srl_gallery)
    private SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.id_rc_gallery)
    private RecyclerView recyclerView;

    @ViewInject(R.id.id_sp_space)
    private TextView btn_space;

    @ViewInject(R.id.id_sp_color)
    private TextView btn_color;

    private List<GalleryEntity.GalleryModel> data;
    private List<GalleryEntity.GalleryModel> allDatas;

    private GalleryAdapter mAdapter;
    private static int SPAN_COUNT = 2;
    private static int STATE_LOAD_REFRESH = 0;
    private static int STATE_LOAD_MORE = 1;
    private int mCurrentNum = 0;
    private int lastVisibleItem;
    private View convertView;
    private PopupWindow mWindow;
    private String mCurrentUrl;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                mCurrentNum++;
                loadData(mCurrentUrl, STATE_LOAD_MORE);
            }
        }
    };

    @Override
    public void init() {

        tv_commonTitle.setText("家居图库");

        // 刷新时，指示器旋转后变化的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_first_color, R.color
                .refresh_second_color);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new GalleryAdapter(this, R.layout.item_card_img);

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
        btn_space.setOnClickListener(this);
        btn_color.setOnClickListener(this);

    }

    @Override
    public void loadDatas() {

        mCurrentNum = 1;
        mCurrentUrl = Constants.Gallery.ALL_SPACE_URL;
        loadData(mCurrentUrl, STATE_LOAD_REFRESH);

    }

    private void loadData(String url, int state) {

        String newUrl = url + mCurrentNum;
        L.d(newUrl);
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
                shortToast(ConstantString.LOAD_FAILED);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        mCurrentNum = 1;
        loadData(mCurrentUrl, STATE_LOAD_REFRESH);
    }

    @Override
    public void onItemClick(View v, int position) {
        allDatas = mAdapter.getDatas();
        new ImageDialog(this, allDatas, position).show();
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        if (clickId == R.id.id_sp_space) {
            showPopupWindow(v, TYPE_SPACE);
        } else if (clickId == R.id.id_sp_color) {
            showPopupWindow(v, TYPE_COLOR);
        } else {
            String url = (String) v.getTag();
            mCurrentNum = 1;
            mCurrentUrl = url;
            loadData(mCurrentUrl, STATE_LOAD_REFRESH);
            mWindow.dismiss();
        }

    }

    private void showPopupWindow(View view, int type) {

        switch (type) {
            case TYPE_SPACE:
                convertView = LayoutInflater.from(this).inflate(R.layout.pop_space, null);
                initSpace(convertView);
                break;
            case TYPE_COLOR:
                convertView = LayoutInflater.from(this).inflate(R.layout.pop_color, null);
                initColor(convertView);
                break;
        }

        mWindow = new PopupWindow(convertView, view.getWidth(),
                ViewGroup.LayoutParams
                        .WRAP_CONTENT, true);

        mWindow.setTouchable(true);
        mWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mWindow.setBackgroundDrawable(new ColorDrawable());
        mWindow.setAnimationStyle(R.style.popwin_anim_style);

        mWindow.showAsDropDown(view);
    }

    private void initColor(View convertView) {
        TextView cl_all = (TextView) convertView.findViewById(R.id.id_color_all);
        TextView green = (TextView) convertView.findViewById(R.id.id_green);
        TextView pink = (TextView) convertView.findViewById(R.id.id_pink);
        TextView red = (TextView) convertView.findViewById(R.id.id_red);
        TextView yellow = (TextView) convertView.findViewById(R.id.id_yellow);

        cl_all.setTag(Constants.Gallery.ALL_COLOR_URL);
        green.setTag(Constants.Gallery.GREEN_URL);
        pink.setTag(Constants.Gallery.PINK_URL);
        red.setTag(Constants.Gallery.RED_URL);
        yellow.setTag(Constants.Gallery.YELLOW_URL);

        cl_all.setOnClickListener(this);
        green.setOnClickListener(this);
        pink.setOnClickListener(this);
        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
    }

    private void initSpace(View convertView) {
        TextView sp_all = (TextView) convertView.findViewById(R.id.id_space_all);
        TextView bedroom = (TextView) convertView.findViewById(R.id.id_bedroom);
        TextView livingroom = (TextView) convertView.findViewById(R.id.id_livingroom);
        TextView diningroom = (TextView) convertView.findViewById(R.id.id_diningroom);
        TextView bathroom = (TextView) convertView.findViewById(R.id.id_bathroom);

        sp_all.setTag(Constants.Gallery.ALL_SPACE_URL);
        bedroom.setTag(Constants.Gallery.BEDROOM_URL);
        livingroom.setTag(Constants.Gallery.LIVING_URL);
        diningroom.setTag(Constants.Gallery.DINING_URL);
        bathroom.setTag(Constants.Gallery.BATH_URL);

        sp_all.setOnClickListener(this);
        bedroom.setOnClickListener(this);
        livingroom.setOnClickListener(this);
        diningroom.setOnClickListener(this);
        bathroom.setOnClickListener(this);
    }
}
