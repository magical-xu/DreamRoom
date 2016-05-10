package com.idreamsky.dreamroom.ui.activity;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.EventEntity;
import com.idreamsky.dreamroom.util.DBUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.UniversalUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by magical on 2016/4/17.
 * 活动详情界面
 */
@ContentView(R.layout.activity_event_detail)
public class EventDetailActivity extends BaseActivity {

    public static final String DATA = "data";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_event_detail_img)
    private ImageView iv_big;

    @ViewInject(R.id.id_item_event_img)
    private ImageView iv_logo;

    @ViewInject(R.id.item_event_title)
    private TextView tv_item_title;

    @ViewInject(R.id.item_event_time)
    private TextView tv_time;

    @ViewInject(R.id.item_event_address)
    private TextView tv_address;

    @ViewInject(R.id.id_event_detail_des)
    private TextView tv_des;

    private EventEntity mData;

    @Override
    public void init() {

        tv_title.setText("活动详情");
        try {
            mData = (EventEntity) getIntent().getSerializableExtra(DATA);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void loadDatas() {
        if (null != mData) {
            UniversalUtil.displayImage(mData.getBackground(), iv_big, null);
            UniversalUtil.displayImage(mData.getLogo(), iv_logo, null);

            tv_item_title.setText(mData.getName());
            tv_time.setText(mData.getTime());
            tv_address.setText(mData.getAreaDetail());
            tv_des.setText(Html.fromHtml(mData.getContent()));
            tv_des.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    public void goBack(View view) {
        finish();
    }

    public void collect(View view) {

        if (null == mData) {
            return;
        }
        DbManager db = x.getDb(DBUtil.getInstance().getEventDB());
        try {
            List<EventEntity> sqlList = db.selector(EventEntity.class).findAll();
            if (null != sqlList && sqlList.size() > 0) {
                for (int i = 0; i < sqlList.size(); i++) {
                    EventEntity one = sqlList.get(i);
                    if (mData.getName().equals(one.getName())) {
                        ToastUtil.ToastShort(EventDetailActivity.this, ConstantString.HAD_COLLECT);
                        return;
                    }
                }
            }

            db.save(mData);
            ToastUtil.ToastShort(EventDetailActivity.this, ConstantString.COLLECT_SUCCESS);
        } catch (DbException e) {
            e.printStackTrace();
            ToastUtil.ToastShort(EventDetailActivity.this, ConstantString.COLLECT_FAILED);
        }
    }
}
