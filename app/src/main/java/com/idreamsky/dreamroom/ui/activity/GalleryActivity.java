package com.idreamsky.dreamroom.ui.activity;

import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.model.GalleryEntity;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
@ContentView(R.layout.activity_gallery)
public class GalleryActivity extends BaseActivity {

    @ViewInject(R.id.id_common_title)
    private TextView tv_commonTitle;

    private List<GalleryEntity.GalleryModel> data;

    @Override
    public void init() {

        tv_commonTitle.setText("家居图库");
    }

    @Override
    public void loadDatas() {

        VolleyUtil.requestString(Constants.URL.GALLERY_ALL_URL, new VolleyUtil.OnRequest() {
            @Override
            public void response(String url, String response) {
                String json = JsonUtil.handleIrregularJson(response);
                GalleryEntity entity = JsonUtil.getGDataByJSON(json);
                data = entity.getData();
                String tt = data.get(0).id;
                longToast(tt);
            }

            @Override
            public void errorResponse(String url, VolleyError error) {
            }
        });
    }
}
