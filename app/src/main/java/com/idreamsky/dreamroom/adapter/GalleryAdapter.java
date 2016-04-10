package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.util.UniversalUtil;

/**
 * Created by magical on 2016/3/8.
 */
public class GalleryAdapter extends AbsRecyclerAdapter<GalleryEntity.GalleryModel> {

    public GalleryAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(MyViewHolder holder, GalleryEntity.GalleryModel data, int position) {

        ImageView imageView = (ImageView) holder.getView(R.id.id_item_img);
        UniversalUtil.displayImage(data.pic3, imageView, null);
    }
}
