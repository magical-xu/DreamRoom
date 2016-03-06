package com.idreamsky.dreamroom.base;

/**
 * Created by Administrator on 2016/3/5.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

import java.io.Serializable;

/**
 * Fragement基类 -- 泛型K 为需要传递到该fragment中的类型
 */
public class BaseFragment<K extends Serializable> extends Fragment {
    private boolean injected = false;
    protected static final String DATA_KEY = "datas";

    /**
     * 静态工厂方法
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends BaseFragment> T getInstance(Class<T> tClass){
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 初始化执行方法
     * @param view
     */
    protected void init(View view){

    }

    /**
     * 加载数据 -- 如果需要加载数据，重写该方法
     */
    public void loadDatas(){

    }

    /**
     * 需要传递实体参数给fragment时调用该方法
     * @param ks
     * @return
     */
    public BaseFragment bindDatas(K ks){
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_KEY, ks);
        this.setArguments(bundle);
        return this;
    }

    /**
     * 需要传递多个参数给fragment时调用该方法
     * @return
     */
    public BaseFragment bindDatas(Object... obj){
        Bundle bundle = setDatas(obj);
        this.setArguments(bundle);
        return this;
    }

    /**
     * 多数据的处理方法 -- 通过bindDatas(Object... obj)方式传值时需要重写这个方法
     * @param obj
     * @return
     */
    protected Bundle setDatas(Object... obj){
        return null;
    }

    /**
     * 获得数据方法 -- 通过bindDatas(Object... obj)方式传值时需要重写这个方法
     */
    protected void getDatas(Bundle bundle){
        if(bundle != null) {
            K ks = (K) bundle.getSerializable(DATA_KEY);
            if (ks != null) {
                getDatas(ks);
            }
        }
    }

    /**
     * 获得实体数据方法 -- 通过bindDatas(K ks)方式传值时需要重写这个方法
     */
    protected void getDatas(K ks){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        //注册xutils
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        getDatas(getArguments());
        init(view);
        loadDatas();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getDatas(getArguments());
    }


    /**
     * fragment管理方法
     */
    public void fragmentManager(int fl_resid, Class fclass){
        Activity activity = getActivity();
        if(activity instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity) activity;
            baseActivity.fragmentManager(fl_resid, fclass);
        }
    }
}