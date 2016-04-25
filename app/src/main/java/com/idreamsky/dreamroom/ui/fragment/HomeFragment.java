package com.idreamsky.dreamroom.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.model.HomeBanner;
import com.idreamsky.dreamroom.model.HomeHot;
import com.idreamsky.dreamroom.ui.activity.BrandProductActivity;
import com.idreamsky.dreamroom.ui.activity.WebContentActivity;
import com.idreamsky.dreamroom.ui.custum.NetworkImageHolderView;
import com.idreamsky.dreamroom.ui.custum.TranslucentImageView;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/4/17.
 * 首页
 */
@ContentView(R.layout.frag_home)
public class HomeFragment extends BaseFragment implements OnItemClickListener, View
        .OnClickListener {

    @ViewInject(R.id.id_home_banner)
    private ConvenientBanner banner;

    @ViewInject(R.id.id_home_hot_container)
    private LinearLayout ll_container;

    @ViewInject(R.id.id_home_horizontal)
    private HorizontalScrollView horizontalScrollView;

    private List<String> imgUrls;
    private List<HomeBanner> dataList;
    private List<HomeHot> hotList;

    private Context mContext;
    public static final int TYPE = 1;

    private boolean isPrepared;

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible) {
            return;
        }
        requestBannerData();
        requestHotData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void init(View view) {

        mContext = getActivity();
    }

    @Override
    public void loadDatas() {


    }

    private void initBanner() {

        if (null == dataList || 0 == dataList.size()) {
            return;
        }

        imgUrls = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            imgUrls.add(dataList.get(i).getImagesrc());
        }
        banner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, imgUrls)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.icon_point_normal, R.drawable
                        .icon_point_select})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        banner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        banner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {
        //banner点击
        if (position >= 0 && position < dataList.size()) {
            HomeBanner banner = dataList.get(position);
            Intent intent = new Intent(mContext, WebContentActivity.class);
            intent.putExtra(WebContentActivity.TYPE_BANNER, TYPE);
            intent.putExtra(BrandProductActivity.LINK, banner.getBanner_url());
            startActivity(intent);
        }
    }

    private void requestBannerData() {
        VolleyUtil.requestString(Constants.Home.Banner, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    dataList = JsonUtil.resolveHomeBanner(response);
                    initBanner();
                }
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
            }
        });
    }

    private void requestHotData() {
        VolleyUtil.requestString(Constants.Home.HotTopic, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                if (null != response) {
                    hotList = JsonUtil.resolveHomeHot(response);
                    setUpHorizontalScrollView();
                }
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
            }
        });
    }

    private void setUpHorizontalScrollView() {

        if (null == hotList || 0 == hotList.size()) {
            return;
        }

        ll_container.removeAllViews();
        float width = getResources().getDimension(R.dimen.dr_space_one_eight_zero);

        for (int i = 0; i < hotList.size(); i++) {

            HomeHot one = hotList.get(i);

            TranslucentImageView img = new TranslucentImageView(mContext);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) width,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            img.setLayoutParams(params);

            img.setTitle(one.getName());
            UniversalUtil.displayImage(one.getImage_urls().get(0).getOrigin(), img.getImageView(),
                    null);

            img.setTag(one.getId());
            img.setOnClickListener(this);
            ll_container.addView(img);
        }


    }

    @Override
    public void onClick(View v) {


    }
}
