package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/17.
 */
@ContentView(R.layout.activity_web_content)
public class WebContentActivity extends BaseActivity {

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_web_content)
    private WebView webView;

    private String mLoadUrl;

    @Override
    public void init() {

        tv_title.setText("淘宝");
        Intent intent = getIntent();
        if (null != intent) {
            mLoadUrl = intent.getStringExtra(BrandProductActivity.LINK);
        }
        initWebView();

    }

    private void initWebView() {
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void loadDatas() {

        if (!TextUtils.isEmpty(mLoadUrl)) {
            webView.loadUrl(mLoadUrl);
        }

    }

    public void goBack(View view) {
        finish();
    }
}
