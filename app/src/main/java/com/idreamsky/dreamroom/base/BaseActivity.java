package com.idreamsky.dreamroom.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.util.ToastUtil;

import org.xutils.x;

/**
 * Created by magical on 2016/3/5.
 */
public class BaseActivity extends AppCompatActivity implements ConstantString, ToastUtil {

    private FragmentManager fragmentManager;
    private Fragment showFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        AppContext.mAMS.addActivity(this);
        fragmentManager = getSupportFragmentManager();

        init();
        loadDatas();
    }

    /**
     * 初始化
     */
    public void init() {

    }

    /**
     * 加载数据
     */
    public void loadDatas() {

    }

    /**
     * 带默认过程动画的启动Activity方式
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        this.startActivity(intent, R.anim.activity_in_rigth, R.anim.activity_out);
    }

    /**
     * 自定义过场动画的Activity启动方式
     *
     * @param intent
     * @param animin
     * @param animout
     */
    public void startActivity(Intent intent, int animin, int animout) {
        super.startActivity(intent);
        overridePendingTransition(animin, animout);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none, R.anim.trans_center_2_right);
    }

    /**
     * fragment管理 -- 不包含过程动画的方式
     */
    public void fragmentManager(int fl_resid, Class fclass) {
        fragmentManager(fl_resid, 0, 0, fclass);
    }

    /**
     * 包含动画的fragment管理
     *
     * @param fl_resid 给fragment占位的布局id
     * @param fclass   fragment进入的动画
     * @param inanim   fragment退出的动画
     * @param outanim  需要显示的fragment的Class对象
     */
    public void fragmentManager(int fl_resid, int inanim, int outanim, Class<BaseFragment> fclass) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (inanim != 0 || outanim != 0) {
            fragmentTransaction.setCustomAnimations(inanim, outanim);
        }

        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fclass.getName());
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }
        if (fragmentByTag != null) {
            fragmentTransaction.show(fragmentByTag);
            showFragment = fragmentByTag;
        } else {
            BaseFragment baseFragment = BaseFragment.getInstance(fclass);
            fragmentTransaction.add(fl_resid, baseFragment, fclass.getName());
            showFragment = baseFragment;
        }
        fragmentTransaction.commit();
    }


    @Override
    public void shortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
