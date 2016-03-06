package com.idreamsky.dreamroom.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/6.
 */
@ContentView(R.layout.frag_test)
public class TestFragment extends BaseFragment {

    private String type;

    @ViewInject(R.id.id_frag_text)
    private TextView textView;

    @Override
    protected void init(View view) {
        super.init(view);
        textView.setText(type);
    }

    @Override
    protected void getDatas(Serializable ks) {
        super.getDatas(ks);
        type = ks.toString();
    }
}
