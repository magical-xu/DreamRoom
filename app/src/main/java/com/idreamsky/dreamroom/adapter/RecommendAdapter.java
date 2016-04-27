package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.Recommend;
import com.idreamsky.dreamroom.ui.custum.GestureViewPager;
import com.idreamsky.dreamroom.ui.custum.MyHorizontalScrollView;
import com.idreamsky.dreamroom.util.UniversalUtil;

import java.util.List;

/**
 * Created by magical on 2016/4/27.
 */
public class RecommendAdapter extends AbsRecyclerAdapter<Recommend> {

    private final String INDEX_TYPE = "0";//控制 布局内容

    MyHorizontalScrollView scrollView;
    GestureViewPager vp;
    ImageView iv_heart;
    TextView tv_name;
    TextView tv_style;
    TextView tv_space;
    TextView tv_price;
    TextView tv_priceInclude;
    LinearLayout ll_frame;
    ImageView iv_hide;
    RelativeLayout rl_bottom;

    public RecommendAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(MyViewHolder holder, Recommend data, int position) {

        //scrollView = (MyHorizontalScrollView) holder.getView(R.id.item_recommend_hl);
        vp = (GestureViewPager) holder.getView(R.id.item_recommend_vp);
        iv_heart = (ImageView) holder.getView(R.id.item_recommend_heart);
        tv_name = (TextView) holder.getView(R.id.item_recommend_name);
        tv_style = (TextView) holder.getView(R.id.item_recommend_style);
        tv_space = (TextView) holder.getView(R.id.item_recommend_space);
        tv_price = (TextView) holder.getView(R.id.item_recommend_price);
        tv_priceInclude = (TextView) holder.getView(R.id.item_recommend_priceInclude);
        ll_frame = (LinearLayout) holder.getView(R.id.item_recommend_fl);
        iv_hide = (ImageView) holder.getView(R.id.item_recommend_hide);
        rl_bottom = (RelativeLayout) holder.getView(R.id.item_recommend_rl);

        if (null != data) {

            if (!INDEX_TYPE.equals(data.getIndexType())) {
                //只显示一张图片
                vp.setVisibility(View.GONE);
                //scrollView.setVisibility(View.GONE);
                iv_heart.setVisibility(View.GONE);
                rl_bottom.setVisibility(View.GONE);
                ll_frame.setVisibility(View.GONE);
                iv_hide.setVisibility(View.VISIBLE);

                UniversalUtil.displayImage(data.getImageUrl().get(0), iv_hide, null);
            } else {
                //正常显示
                iv_hide.setVisibility(View.GONE);
                showNormalContent(data);
            }

        }
    }

    private void showNormalContent(Recommend data) {

        tv_name.setText(data.getName());
        tv_style.setText(data.getStyle());
        tv_space.setText(data.getSpace());
        tv_price.setText(data.getPrice());
        tv_priceInclude.setText(data.getPriceInclude());

        final List<String> urls = data.getImageUrl();
//        HorizontalScrollViewAdapter adapter = new HorizontalScrollViewAdapter(context, urls);
//        scrollView.initDatas(adapter);

//        List<ImageView> imgList = new ArrayList<>();
//        for (int i = 0; i < urls.size(); i++) {
//            ImageView img = new ImageView(context);
//            UniversalUtil.displayImage(urls.get(i), img, null);
//            imgList.add(img);
//        }

        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return urls.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                UniversalUtil.displayImage(urls.get(position), imageView, null);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
                container.removeView((ImageView) object);
            }
        });
    }
}
