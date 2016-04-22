package com.idreamsky.dreamroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.model.SortModel;

import java.util.List;

/**
 * Created by magical on 2016/4/22.
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {

    private List<SortModel> list;

    private LayoutInflater inflater;

    public SortAdapter(Context context, List<SortModel> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int paramInt) {
        return list.get(paramInt);
    }

    @Override
    public long getItemId(int paramInt) {
        return paramInt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup paramViewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_city_change_list, paramViewGroup, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 根据position获取分类字母的Char的ASCII码
        int section = getSectionForPosition(position);

        // 根据当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(list.get(position).getSortLetters());
        } else {
            holder.title.setVisibility(View.GONE);
        }

        holder.content.setText(list.get(position).getName());
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView content;
    }


    //----------------------SectionIndex的回调函数----------------------

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */

    @Override
    public int getPositionForSection(int position) {
        // TODO 遍历数据集
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == position) {
                return i;
            }
        }

        return -1;

    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */

    @Override
    public int getSectionForPosition(int position) {
        // TODO 首字母Char自动转型int
        return list.get(position).getSortLetters().charAt(0);
    }
}
