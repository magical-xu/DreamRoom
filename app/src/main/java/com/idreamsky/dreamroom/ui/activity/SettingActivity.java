package com.idreamsky.dreamroom.ui.activity;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.util.CacheManagerHelper;
import com.idreamsky.dreamroom.util.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by magical on 2016/4/29.
 * 设置
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_setting_et)
    private EditText editText;

    @Override
    public void init() {

        tv_title.setText("设置");
    }

    @Override
    public void loadDatas() {

        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("让吐槽来的更猛烈写吧 (●'◡'●) !");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(14, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }

    public void goBack(View view) {
        finish();
    }

    public void submit(View view) {

        if (TextUtils.isEmpty(editText.getText().toString())) {
            ToastUtil.ToastShort(this, "建议不能为空！");
            return;
        }
        editText.setText("");
        ToastUtil.ToastShort(this, "提交成功！");
    }

    public void clearCache(View view) {

        CacheManagerHelper.clearApplicationCache(SettingActivity.this.getApplicationContext());
        ToastUtil.ToastShort(this, "清除成功");
    }
}
