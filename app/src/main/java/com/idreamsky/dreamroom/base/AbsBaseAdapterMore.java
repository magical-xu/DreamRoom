package com.idreamsky.dreamroom.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装自定义Adapter--多布局
 */
public abstract class AbsBaseAdapterMore<T> extends BaseAdapter{

    private Context context;
    private List<T> datas;
    private int[] resId;

    public AbsBaseAdapterMore(Context context, int ...resId){
        this.context = context;
        datas = new ArrayList();
        this.resId = resId;
    }

    public void setDatas(List<T> datas){
        this.datas.clear();
        addDatas(datas);
    }

    public void addDatas(List<T> datas){
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(context, resId[getItemViewType(position)], null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        //从数据源中获得数据 放入viewhodler的对象中控件里
        bindDatas(viewHolder, datas.get(position));
        return convertView;
    }

    public abstract void bindDatas(ViewHolder viewHolder, T data);

    //作用-->将布局中的控件缓存，以便下次使用时不再findViewById
    public class ViewHolder{
        private Map<Integer, View> mapCache = new HashMap();
        private View layoutView;
        public ViewHolder(View layoutView){
            this.layoutView = layoutView;
        }

        public View getView(int id){
            View view = null;
            if(mapCache.containsKey(id)){
                view = mapCache.get(id);
            } else {
                view = layoutView.findViewById(id);
                mapCache.put(id, view);
            }

            return view;
        }
    }

    /**
     * 强制T这个实体类中 必须有一个名字叫做type的属性
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        T data = datas.get(position);
        Class c = data.getClass();
        int ty = 0;
        try {
            Field type = c.getDeclaredField("type");
            type.setAccessible(true);
            ty = type.getInt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ty;
    }

    @Override
    public int getViewTypeCount() {
        return resId.length;
    }
}
