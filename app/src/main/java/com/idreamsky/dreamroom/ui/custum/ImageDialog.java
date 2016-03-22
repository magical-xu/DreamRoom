package com.idreamsky.dreamroom.ui.custum;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.DialogImageAdapter;
import com.idreamsky.dreamroom.model.GalleryEntity.GalleryModel;

import java.util.List;

/**
 * Created by magical on 2016/3/22.
 */
public class ImageDialog extends Dialog {

    private Context mContext;
    private int mCurrentPos;//当前页
    private List<GalleryModel> mDatas;//数据
    private View rootView;//根布局

    private TextView tv_page;//页数
    private ImageView iv_back;//返回键
    private ViewPager mViewPager;
    private DialogImageAdapter mAdapter;

    private String mTitle;//标题

    public ImageDialog(Context context, List<GalleryModel> data, int position) {
        super(context, android.R.style.Theme_NoTitleBar_Fullscreen);//全屏的
        setProperty();
        this.mContext = context;
        this.mDatas = data;
        this.mCurrentPos = position;

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

        //返回监听
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //划动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPos = position;
                mTitle = (mCurrentPos + 1) + " / " + mDatas.size();
                tv_page.setText(mTitle);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mTitle = (mCurrentPos + 1) + " / " + mDatas.size();
        tv_page.setText(mTitle);

        mAdapter = new DialogImageAdapter(mContext, mDatas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrentPos);
        mViewPager.setOffscreenPageLimit(5);
    }

    private void initView() {

        rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_image_pager, null);
        tv_page = (TextView) rootView.findViewById(R.id.id_dialog_title_page);
        iv_back = (ImageView) rootView.findViewById(R.id.id_dialog_title_back);
        mViewPager = (ViewPager) rootView.findViewById(R.id.id_dialog_viewpager);

        setContentView(rootView);
    }

    private void setProperty() {
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
