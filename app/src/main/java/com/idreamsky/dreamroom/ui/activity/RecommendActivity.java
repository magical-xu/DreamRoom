package com.idreamsky.dreamroom.ui.activity;

import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/23.
 * 精彩推荐
 */
@ContentView(R.layout.activity_recommend)
public class RecommendActivity extends BaseActivity{

    @ViewInject(R.id.id_test)
    private TextView textView;

    @Override
    public void init() {

        String msg = getIntent().getStringExtra("data");

        textView.setText(msg);
    }

}
