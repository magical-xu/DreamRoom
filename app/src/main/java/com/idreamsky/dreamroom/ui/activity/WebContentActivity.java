package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.ui.fragment.HomeFragment;
import com.nostra13.universalimageloader.utils.L;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/17.
 */
@ContentView(R.layout.activity_web_content)
public class WebContentActivity extends BaseActivity {

    public static final String TYPE_BANNER = "banner";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_web_content)
    private WebView webView;

    @ViewInject(R.id.id_empty_data)
    private RelativeLayout rl_no_data;

    @ViewInject(R.id.id_no_data_anim)
    private ImageView iv_anim;

    private String mLoadUrl;
    private int mType = 0;
    private Animation animation;

    @Override
    public void init() {

        rl_no_data.setVisibility(View.GONE);
        Intent intent = getIntent();
        if (null != intent) {
            mLoadUrl = intent.getStringExtra(BrandProductActivity.LINK);
            mType = intent.getIntExtra(TYPE_BANNER, 0);
        }

        if (HomeFragment.TYPE == mType) {
            tv_title.setText(ConstantString.TOGETHER);
        } else {
            tv_title.setText(ConstantString.TAO_BAO);
        }
        initWebView();

        animation = AnimationUtils.loadAnimation(this, R.anim.far_to_near);
        iv_anim.setAnimation(animation);
    }

    private void initWebView() {
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void loadDatas() {

        L.d(mLoadUrl);
        if (!TextUtils.isEmpty(mLoadUrl)) {
            rl_no_data.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(mLoadUrl);
        } else {
            webView.setVisibility(View.GONE);
            rl_no_data.setVisibility(View.VISIBLE);
            animation.startNow();
        }

    }

    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animation.cancel();
    }
}
