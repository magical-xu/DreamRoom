package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.model.GalleryEntity.GalleryModel;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class DialogImageAdapter extends PagerAdapter {

    private List<GalleryModel> mDatas;
    private Context mContext;

    public DialogImageAdapter(Context context, List<GalleryModel> data) {
        this.mContext = context;
        this.mDatas = data;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.item_dialog_framlayout, container, false);

        ViewHolder holder = new ViewHolder();

        holder.iv_item = (ImageView) convertView.findViewById(R.id.id_item_dialog_iv);
        String item_url = mDatas.get(position).pic;

        UniversalUtil.displayImage(item_url, holder.iv_item, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
        container.addView(convertView);

        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    class ViewHolder {
        public ImageView iv_item;
    }
}
