package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsBaseAdapter;
import com.idreamsky.dreamroom.model.BrandClazz;

/**
 * Created by Administrator on 2016/4/10.
 */
public class BrandClazzAdapter extends AbsBaseAdapter<BrandClazz> {

    public BrandClazzAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, BrandClazz data) {

        TextView tv = (TextView) viewHolder.getView(R.id.id_brand_clazz_tv);
        if (null != data) {
            tv.setText(data.getProductClass());
        }
    }
}
