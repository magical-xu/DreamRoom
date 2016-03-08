package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsRecyclerAdapter;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by magical on 2016/3/8.
 */
public class GalleryAdapter extends AbsRecyclerAdapter<GalleryEntity.GalleryModel> {

    public GalleryAdapter(Context context, int resId) {
        super(context, resId);
    }

//    private void getRandomHeight(List<String> lists){//得到随机item的高度
//        heights = new ArrayList<>();
//        for (int i = 0; i < lists.size(); i++) {
//            heights.add((int)(200+Math.random()*400));
//        }
//    }

    @Override
    public void bindDatas(MyViewHolder holder, GalleryEntity.GalleryModel data) {

        ImageView imageView = (ImageView) holder.getView(R.id.id_item_img);

        ViewGroup.LayoutParams mLayoutParams = imageView.getLayoutParams();
        //mLayoutParams.height = mHeights.get(position);
        imageView.setLayoutParams(mLayoutParams);


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
