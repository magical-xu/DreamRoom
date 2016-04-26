package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AppContext;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.ui.MainActivity;
import com.idreamsky.dreamroom.util.ShareUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/25.
 * 欢迎页
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    private int[] imgs = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.page_guide1};

    @ViewInject(R.id.id_splash_iv)
    private ImageView iv_splash;

    @ViewInject(R.id.id_splash_tiy)
    private TextView tv_tiy;

    private Animation animation;
    private int count = 0;
    private String pageController;
    private boolean flag = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            iv_splash.setImageResource(imgs[count]);
            iv_splash.startAnimation(animation);
        }
    };

    @Override
    public void init() {

        pageController = ShareUtil.getString(AppContext.KEY_SPLASH_SHOW);
        if (TextUtils.isEmpty(pageController)) {
            //第一次进入
            flag = true;
            animation = AnimationUtils.loadAnimation(this, R.anim.near_to_far);
            animation.setAnimationListener(this);
        } else if (null != pageController && ConstantString.VALUE_SPLASH_SHOW.equals
                (pageController)) {
            //不是第一次
            flag = false;
            jumpToMain();
        }


    }

    @Override
    public void loadDatas() {

        if (flag) {
            iv_splash.setImageResource(imgs[count]);
            iv_splash.setAnimation(animation);
            iv_splash.startAnimation(animation);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        count++;
        if (3 == count) {
            iv_splash.setImageResource(R.drawable.welcome);
            tv_tiy.setVisibility(View.VISIBLE);
            return;
        }
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    public void goMain(View view) {
        ShareUtil.putString(AppContext.KEY_SPLASH_SHOW, ConstantString.VALUE_SPLASH_SHOW);
        jumpToMain();
    }

    public void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, R.anim.fade, R.anim.hold);
        finish();
    }
}
