package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.BrandHomeEntity;
import com.idreamsky.dreamroom.util.UniversalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/4/10.
 * 品牌展厅首页适配器
 */
public class BrandHomeAdapter extends AbsRecyclerAdapter<BrandHomeEntity.BrandHomeModel> {

    private List<Integer> mHeights;

    public void setRandomItemHeight(List<Integer> heights) {
        if (heights != null) {
            mHeights.addAll(heights);
        }
    }

    public BrandHomeAdapter(Context context, int resId) {
        super(context, resId);
        mHeights = new ArrayList<>();
    }

    @Override
    public void bindDatas(MyViewHolder holder, BrandHomeEntity.BrandHomeModel data, int position) {

        ImageView imageView = (ImageView) holder.getView(R.id.id_item_img);
        if (null != mHeights && mHeights.size() != 0) {
            ViewGroup.LayoutParams mLayoutParams = imageView.getLayoutParams();
            mLayoutParams.height = mHeights.get(position);
            imageView.setLayoutParams(mLayoutParams);
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        UniversalUtil.displayImage(data.getLogo(), imageView, null);
    }
}
