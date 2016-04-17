package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.EventEntity;
import com.idreamsky.dreamroom.util.UniversalUtil;

/**
 * Created by magical on 2016/4/17.
 * 优选活动适配器
 */
public class EventAdapter extends AbsRecyclerAdapter<EventEntity> {

    public EventAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(MyViewHolder holder, EventEntity data, int position) {

        ImageView iv = (ImageView) holder.getView(R.id.id_item_event_img);
        TextView tv_title = (TextView) holder.getView(R.id.item_event_title);
        TextView tv_time = (TextView) holder.getView(R.id.item_event_time);
        TextView tv_address = (TextView) holder.getView(R.id.item_event_address);

        if (null != data) {

            tv_title.setText(data.getCity() + " | " + data.getName());
            tv_time.setText(data.getTime());
            tv_address.setText("地址: " + data.getAreaDetail());

            UniversalUtil.displayImage(data.getLogo(), iv, null);
        }
    }
}
