package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.ThemeDetail;
import com.idreamsky.dreamroom.util.Constants;
import com.idreamsky.dreamroom.util.JsonUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.UniversalUtil;
import com.idreamsky.dreamroom.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by magical on 2016/4/24.
 */
@ContentView(R.layout.activity_theme_detail)
public class ThemeDetailActivity extends BaseActivity {

    public static final String INTENT_ID = "intent_id";
    public static final String INTENT_TITLE = "intent_title";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_theme_detail_title)
    private TextView tv_theme_title;

    @ViewInject(R.id.id_theme_detail_ll_container)
    private LinearLayout ll_container;

    private String themeID;
    private final int LINE_SPACE = 20;

    @Override
    public void init() {

        tv_title.setText(getResources().getString(R.string.act_theme_detail));
        Intent intent = getIntent();
        if (null != intent.getStringExtra(INTENT_ID)) {
            themeID = intent.getStringExtra(INTENT_ID);
        }
        if (null != intent.getStringExtra(INTENT_TITLE)) {
            tv_theme_title.setText(intent.getStringExtra(INTENT_TITLE));
        }
    }

    @Override
    public void loadDatas() {

        if (!TextUtils.isEmpty(themeID)) {

            VolleyUtil.requestString(Constants.Inspiration.THEME_DETAIL + themeID, new VolleyUtil
                    .OnRequest() {

                @Override
                public void response(String url, String response) {
                    if (null != response) {
                        List<ThemeDetail> tmpList = JsonUtil.resolveThemeDetail(response);
                        if (null != tmpList && 0 != tmpList.size()) {
                            addContent(tmpList);
                        }
                    }
                }

                @Override
                public void errorResponse(String url, VolleyError error) {
                    ToastUtil.ToastShort(ThemeDetailActivity.this, ConstantString.LOAD_FAILED);
                }
            });
        }
    }

    private void addContent(List<ThemeDetail> list) {

        for (int i = 0; i < list.size(); i++) {

            ThemeDetail one = list.get(i);

            if (!TextUtils.isEmpty(one.getLike_desc())) {
                TextView textView = new TextView(this);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout
                        .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.leftMargin = 20;
                params1.rightMargin = 20;
                params1.topMargin = 40;
                params1.bottomMargin = 40;

                textView.setText(one.getLike_desc());
                float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, Resources
                        .getSystem().getDisplayMetrics());
                textView.setTextSize(com.idreamsky.dreamroom.util.DensityUtil.px2sp(textSize));
                textView.setTextColor(getResources().getColor(R.color.main_black_grey));
                textView.setLineSpacing(LINE_SPACE, 1);
                ll_container.addView(textView, params1);
            }

            if (!TextUtils.isEmpty(one.getPath())) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout
                        .LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen
                        .dr_space_three_zero_zero));
                params2.topMargin = 20;
                params2.bottomMargin = 20;

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                UniversalUtil.displayImage(one.getPath(), imageView, null);
                ll_container.addView(imageView, params2);
            }
        }

    }

    public void goBack(View view) {
        finish();
    }
}
