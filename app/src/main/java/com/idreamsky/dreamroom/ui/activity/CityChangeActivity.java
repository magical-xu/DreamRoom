package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.adapter.SortAdapter;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.callback.LetterOnTouchCallBack;
import com.idreamsky.dreamroom.model.SortModel;
import com.idreamsky.dreamroom.ui.custum.LetterView;
import com.idreamsky.dreamroom.util.CharacterParser;
import com.idreamsky.dreamroom.util.PinyinComparator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by magcial on 2016/4/22.
 * 切换城市选择
 */
@ContentView(R.layout.activity_city_change)
public class CityChangeActivity extends BaseActivity implements LetterOnTouchCallBack,
        AdapterView.OnItemClickListener {

    private static final String TAG = CityChangeActivity.class.getSimpleName();

    public static final int RESULT_CODE = 5;
    public static final String CITY_NAME = "city_name";

    @ViewInject(R.id.letter_group)
    private LetterView letterView;

    @ViewInject(R.id.letter)
    private TextView mTextView;

    @ViewInject(R.id.listview)
    private ListView listview;

    private List<SortModel> sourceData;

    private SortAdapter adapter;

    @Override
    public void init() {
        initView();
    }

    private void initView() {
        letterView.setOnLetterCallBack(this);
        letterView.setTextView(mTextView);

        sourceData = getSourceData();
        // 根据a-z进行排序源数据
        Collections.sort(sourceData, new PinyinComparator());
        adapter = new SortAdapter(this, sourceData);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }


    //汉字转成拼音
    private CharacterParser characterParser;

    private List<SortModel> getSourceData() {
        characterParser = CharacterParser.getInstance();
        List<SortModel> mSortList = new ArrayList<SortModel>();
        String[] data = getResources().getStringArray(R.array.city_list);
        int length = data.length;
        for (int i = 0; i < length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(data[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(data[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }

    //LetterView滑动监听
    @Override
    public void onTouchingLetterChanged(String s) {
        Log.e(TAG, s);
        int position = adapter.getPositionForSection(s.charAt(0));
        if (position != -1) {
            listview.setSelection(position);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position < adapter.getCount()) {
            SortModel model = (SortModel) adapter.getItem(position);

            if (null != model) {
                String select_city = model.getName();

                if (!TextUtils.isEmpty(select_city)) {
                    Intent intent = new Intent();
                    intent.putExtra(CITY_NAME, select_city);
                    setResult(RESULT_CODE, intent);
                    finish();
                }
            }

        }

    }
}
