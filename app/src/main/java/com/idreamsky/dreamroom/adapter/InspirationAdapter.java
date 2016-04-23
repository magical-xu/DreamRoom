package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsBaseAdapter;
import com.idreamsky.dreamroom.model.InspirationTheme;
import com.idreamsky.dreamroom.util.UniversalUtil;

/**
 * Created by magical on 2016/4/23.
 */
public class InspirationAdapter extends AbsBaseAdapter<InspirationTheme> {

    public InspirationAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, InspirationTheme data) {

        TextView tv_title = (TextView) viewHolder.getView(R.id.id_item_title);
        TextView tv_eye = (TextView) viewHolder.getView(R.id.id_item_eye);
        TextView tv_fav = (TextView) viewHolder.getView(R.id.id_item_like);
        TextView tv_des = (TextView) viewHolder.getView(R.id.id_item_detail);
        ImageView iv = (ImageView) viewHolder.getView(R.id.id_item_iv);

        if (null != data) {

            UniversalUtil.displayImage(data.getUrl(), iv, null);
            tv_title.setText(data.getTitle());
            tv_eye.setText(data.getComment_count());
            tv_fav.setText(data.getLike_num());
            tv_des.setText(data.getDesc());
        }

    }
}
