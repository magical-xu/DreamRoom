package com.idreamsky.dreamroom.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.AbsBaseAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magical on 2016/4/28.
 * 我的收藏
 */
@ContentView(R.layout.activity_collect)
public class CollectActivity extends BaseActivity {

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_collect_list)
    private ListView listView;

    private List<String> collectList;
    private CollectAdapter mAdapter;

    @Override
    public void init() {

        tv_title.setText("我的收藏");
        collectList = new ArrayList<>();
        collectList.add("装修风水");
        collectList.add("装修公司");
        collectList.add("装修活动");
        collectList.add("品牌展厅");
        mAdapter = new CollectAdapter(this, R.layout.item_adapter_collect);
        mAdapter.setDatas(collectList);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void loadDatas() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(CollectActivity.this, ColGeoActivity.class);
                        break;
                    case 1:
                        intent.setClass(CollectActivity.this, ColCompanyActivity.class);
                        break;
                    case 2:
                        intent.setClass(CollectActivity.this, ColEventActivity.class);
                        break;
                    case 3:
                        intent.setClass(CollectActivity.this, ColBrandActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    public void goBack(View view) {
        finish();
    }

    public class CollectAdapter extends AbsBaseAdapter<String> {

        public CollectAdapter(Context context, int resId) {
            super(context, resId);
        }

        @Override
        public void bindDatas(ViewHolder viewHolder, String data) {

            TextView tv = (TextView) viewHolder.getView(R.id.id_item_collect);
            tv.setText(data);
        }
    }
}
