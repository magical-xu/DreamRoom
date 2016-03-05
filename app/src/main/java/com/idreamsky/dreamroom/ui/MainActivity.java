package com.idreamsky.dreamroom.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @ViewInject(R.id.id_drawer_layout)
    private DrawerLayout mDrawerLayout;

    @ViewInject(R.id.id_nav_menu)
    private NavigationView mNavigationView;

    private long exitTime;//记录退出时间
    private boolean isItemChecked = false;
    private Menu nav_menu;

    @Override
    public void init() {

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
