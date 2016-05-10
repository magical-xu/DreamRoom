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
import com.idreamsky.dreamroom.model.EventEntity;
import com.idreamsky.dreamroom.util.DBUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by magical on 2016/5/10.
 * 优选活动-收藏
 */
@ContentView(R.layout.activity_collect_event)
public class ColEventActivity extends BaseActivity {

    @ViewInject(R.id.id_company_collect_list)
    private ListView listView;

    private ColCompanyAdapter mAdapter;

    @Override
    public void init() {

        mAdapter = new ColCompanyAdapter(this, R.layout.item_adapter_company_collect);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void loadDatas() {

        //查询数据库
        DbManager db = x.getDb(DBUtil.getInstance().getEventDB());
        try {

            List<EventEntity> sqlList = db.selector(EventEntity.class).findAll();
            if (null != sqlList && sqlList.size() > 0) {
                mAdapter.setDatas(sqlList);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EventEntity one = (EventEntity) mAdapter.getItem(position);
                if (null != one) {
                    Intent intent = new Intent(ColEventActivity.this, EventDetailActivity
                            .class);
                    intent.putExtra(EventDetailActivity.DATA, one);
                    startActivity(intent);
                }
            }
        });
    }

    public class ColCompanyAdapter extends AbsBaseAdapter<EventEntity> {

        public ColCompanyAdapter(Context context, int resId) {
            super(context, resId);
        }

        @Override
        public void bindDatas(ViewHolder viewHolder, EventEntity data) {

            TextView textView = (TextView) viewHolder.getView(R.id.id_item_company_collect_name);
            if (null != data) {
                textView.setText(data.getName());
            }
        }
    }


}
