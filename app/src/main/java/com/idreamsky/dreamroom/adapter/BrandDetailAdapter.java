package com.idreamsky.dreamroom.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.BrandFurnitureDetail;
import com.idreamsky.dreamroom.ui.custum.AutoScrollTextView;
import com.idreamsky.dreamroom.util.UniversalUtil;

/**
 * Created by Administrator on 2016/4/16.
 * 品牌展厅 - 具体家具
 */
public class BrandDetailAdapter extends AbsRecyclerAdapter<BrandFurnitureDetail> {

    private Context mContext;

    public BrandDetailAdapter(Context context, int resId) {
        super(context, resId);
        this.mContext = context;
    }

    @Override
    public void bindDatas(MyViewHolder holder, BrandFurnitureDetail data, int position) {
        ImageView imageView = (ImageView) holder.getView(R.id.id_item_brand_detail_iv);
        TextView tv_price = (TextView) holder.getView(R.id.id_item_brand_detail_price);
        AutoScrollTextView tv_name = (AutoScrollTextView) holder.getView(R.id.id_item_brand_detail_name);

        if (null != data) {
            UniversalUtil.displayImage(data.getPic(), imageView, null);
            tv_price.setText(null != data.getPrice() ? data.getPrice() : "");
            tv_name.setText(null != data.getProductName() ? data.getProductName() : "");
            tv_name.init(((Activity)mContext).getWindowManager());
            tv_name.startScroll();
        }
    }
}
