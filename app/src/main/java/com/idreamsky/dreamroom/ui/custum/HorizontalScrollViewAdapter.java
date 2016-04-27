package com.idreamsky.dreamroom.ui.custum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.util.UniversalUtil;

import java.util.List;

/**
 * Created by magical on 2016/4/25.
 */
public class HorizontalScrollViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mDatas;

    public HorizontalScrollViewAdapter(Context context, List<String> mDatas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.horizontal_item, parent, false);
            viewHolder.mImg = (ImageView) convertView
                    .findViewById(R.id.id_horizontal_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.mImg.setImageResource(mDatas.get(position));
        UniversalUtil.displayImage(mDatas.get(position), viewHolder.mImg, null);

        return convertView;
    }

    private class ViewHolder {
        ImageView mImg;
    }

}
