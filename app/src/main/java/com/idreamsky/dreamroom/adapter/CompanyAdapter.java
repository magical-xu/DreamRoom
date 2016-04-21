package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.CompanyEntity;
import com.idreamsky.dreamroom.ui.custum.RoundedImageView;
import com.idreamsky.dreamroom.util.UniversalUtil;

/**
 * Created by magical on 2016/4/19.
 */
public class CompanyAdapter extends AbsRecyclerAdapter<CompanyEntity> {

    public CompanyAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public void bindDatas(MyViewHolder holder, CompanyEntity data, int position) {

        ImageView iv_big = (ImageView) holder.getView(R.id.id_item_company_img);
        RoundedImageView iv_logo = (RoundedImageView) holder.getView(R.id.id_item_company_logo);

        TextView tv_name = (TextView) holder.getView(R.id.id_item_company_name);
        TextView tv_style = (TextView) holder.getView(R.id.id_item_company_style);

        if (null != data) {

            UniversalUtil.displayImage(data.getCover(), iv_big, null);
            UniversalUtil.displayImage(data.getLogo(), iv_logo, null);

            tv_name.setText(data.getCompanyName());
            tv_style.setText(data.getGoodHouseStyle());
        }
    }

}
