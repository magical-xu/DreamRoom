package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/3/8.
 */
public class GalleryAdapter extends AbsRecyclerAdapter<GalleryEntity.GalleryModel> {

    private List<Integer> mHeights;

    public GalleryAdapter(Context context, int resId) {
        super(context, resId);
        mHeights = new ArrayList<>();
    }

//    public void setRandomItemHeight(List<Integer> heights){
//        if (heights != null) {
//            mHeights.addAll(heights);
//        }
//    }

    @Override
    public void bindDatas(MyViewHolder holder, GalleryEntity.GalleryModel data, int position) {

        ImageView imageView = (ImageView) holder.getView(R.id.id_item_img);

//        ViewGroup.LayoutParams mLayoutParams = imageView.getLayoutParams();
//        mLayoutParams.height = mHeights.get(position);
//        imageView.setLayoutParams(mLayoutParams);




        UniversalUtil.displayImage(data.pic3, imageView, new ImageLoadingListener() {
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
    }
}
