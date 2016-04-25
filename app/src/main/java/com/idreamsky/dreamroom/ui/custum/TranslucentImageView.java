package com.idreamsky.dreamroom.ui.custum;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;

/**
 * Created by magical on 2016/4/25.
 * 热门话题 半透明带文字Imageview
 */
public class TranslucentImageView extends RelativeLayout {

    private View mRootView;
    private Context mContext;

    private ImageView imageView;
    private TextView textView;

    public TranslucentImageView(Context context) {
        this(context, null);
    }

    public TranslucentImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {

        mRootView = LayoutInflater.from(mContext).inflate(R.layout.custum_layer_img, this, true);

        imageView = (ImageView) mRootView.findViewById(R.id.id_custum_img);
        textView = (TextView) mRootView.findViewById(R.id.id_custum_title);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setTitle(String title) {
        textView.setText(title);
    }
}
