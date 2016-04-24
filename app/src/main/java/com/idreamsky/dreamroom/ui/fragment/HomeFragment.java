package com.idreamsky.dreamroom.ui.fragment;

import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.ui.custum.NetworkImageHolderView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/17.
 * 首页
 */
@ContentView(R.layout.frag_home)
public class HomeFragment extends BaseFragment implements OnItemClickListener {

    @ViewInject(R.id.id_home_banner)
    private ConvenientBanner banner;

    private List<String> bannerUrls;

    @Override
    protected void init(View view) {


    }

    @Override
    public void loadDatas() {

        initBanner();
    }

    private void initBanner() {

        banner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, bannerUrls)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable
//                        .ic_page_indicator_focused})
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
    }
}
