package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsBaseAdapter;
import com.idreamsky.dreamroom.model.GeomancyEntity;
import com.idreamsky.dreamroom.util.UniversalUtil;

/**
 * Created by magical on 2016/4/4.
 * 装修风水适配器
 */
public class GeomancyAdapter extends AbsBaseAdapter<GeomancyEntity.GeomancyBean> {

    public GeomancyAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, GeomancyEntity.GeomancyBean data) {

        TextView tv_title = (TextView) viewHolder.getView(R.id.id_item_geomancy_title);
        TextView tv_browse = (TextView) viewHolder.getView(R.id.id_item_geomancy_browse);
        TextView tv_far = (TextView) viewHolder.getView(R.id.id_item_geomancy_far);
        TextView tv_comment = (TextView) viewHolder.getView(R.id.id_item_geomancy_comment);
        ImageView iv_item = (ImageView) viewHolder.getView(R.id.id_item_geomancy_img);

        tv_title.setText(data.title);
        tv_browse.setText(data.viewCount);
        tv_far.setText(data.favNums);
        tv_comment.setText(data.replies);
        UniversalUtil.displayImage(data.image, iv_item, null);

    }
}