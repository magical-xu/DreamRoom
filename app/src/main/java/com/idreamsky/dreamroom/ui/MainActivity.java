package com.idreamsky.dreamroom.ui;

import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.text)
    private TextView textView;

    @Override
    public void init() {

    }

    @Override
    public void loadDatas() {

        textView.setText("xUtil真是太好用了");
    }
}
