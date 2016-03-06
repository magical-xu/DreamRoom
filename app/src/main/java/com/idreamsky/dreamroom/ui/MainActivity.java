package com.idreamsky.dreamroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.base.BaseFragment;
import com.idreamsky.dreamroom.ui.fragment.TestFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @ViewInject(R.id.id_drawer_layout)
    private DrawerLayout mDrawerLayout;

    @ViewInject(R.id.id_cdntLayout)
    private CoordinatorLayout mCoordinatorLayout;

    @ViewInject(R.id.id_appBarLayout)
    private AppBarLayout mAppBarLayout;

    @ViewInject(R.id.id_toorBar)
    private Toolbar mToolbar;

    @ViewInject(R.id.id_tabLayout)
    private TabLayout mTabLayout;

    @ViewInject(R.id.id_viewPager)
    private ViewPager mViewPager;

    @ViewInject(R.id.id_nav_menu)
    private NavigationView mNavigationView;

    private long exitTime;//记录退出时间
    private Menu nav_menu;//navigation菜单
    private String[] tab_titles;//TabLayout中的标题
    private List<Fragment> fragmentList;//ViewPager中的Fragment


    @Override
    public void init() {

        //获取tab标题
        tab_titles = getResources().getStringArray(R.array.tab_titles);

        //初始化Fragment集合
        fragmentList = new ArrayList<>();
        for (int i = 0; i < tab_titles.length; i++) {
            Bundle bundle = new Bundle();
            String type = tab_titles[i];
            BaseFragment bf = BaseFragment.getInstance(TestFragment.class);
            bf.bindDatas(type);
            fragmentList.add(i, bf);
        }

        //设置DrawerLayout开关指示器，即左边Icon
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mToggle.syncState();

        //设置NavigationView顶部布局
        mNavigationView.inflateHeaderView(R.layout.nav_header);
        //设置NavigationView菜单布局
        mNavigationView.inflateMenu(R.menu.nav_menu);
        //获取nav_menu
        nav_menu = mNavigationView.getMenu();
        //设置NavigationView菜单点击监听
        onNavigationViewMenuItemSelected(mNavigationView);


    }

    @Override
    public void loadDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            closeDrawer();
            return true;
        }

        //防止误碰Back键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                shortToast("再按一次，才能退出哦！");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    /**
     * NavigationView菜单点击监听
     *
     * @param nav
     */
    public void onNavigationViewMenuItemSelected(final NavigationView nav) {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                String msg = "";
                switch (item.getItemId()) {

                    // TODO: 2016/3/5 点击跳转Activity，别忘了过场动画
                    case R.id.nav_recommend:
                        msg = "精彩推荐";
                        break;
                    case R.id.nav_gallery:
                        msg = "家居图库";
                        break;
                    case R.id.nav_inspiration:
                        msg = "灵感专题";
                        break;
                    case R.id.nav_collection:
                        msg = "我的收藏";
                        break;
                    case R.id.nav_about_author:
                        msg = "关于作者";
                        break;
                    case R.id.nav_setting:
                        msg = "设置";
                        break;
                }
                intent.putExtra("data", msg);
                startActivity(intent);

                unCheckMenu();
                item.setChecked(true);

                return true;
            }
        });
    }

    /**
     * 关闭菜单
     */
    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    /**
     * 设置菜单为未选中状态
     */
    public void unCheckMenu() {
        for (int i = 0; i < nav_menu.size(); i++) {
            nav_menu.getItem(i).setChecked(false);
        }
        nav_menu.findItem(R.id.nav_about_author).setChecked(false);
        nav_menu.findItem(R.id.nav_setting).setChecked(false);
    }

}
