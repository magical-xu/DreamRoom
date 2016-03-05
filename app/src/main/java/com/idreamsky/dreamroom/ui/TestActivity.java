package com.idreamsky.dreamroom.ui;

import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/5.
 */
@ContentView(R.layout.activity_test)
public class TestActivity extends BaseActivity {

    @ViewInject(R.id.id_test)
    private TextView textView;

    @Override
    public void init() {

        String msg = getIntent().getStringExtra("data");

        textView.setText(msg);
    }
}
