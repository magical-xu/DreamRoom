package com.idreamsky.dreamroom.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.id_drawer_layout)
    private DrawerLayout mDrawerLayout;

    @ViewInject(R.id.id_nav_menu)
    private NavigationView mNavigationView;

    private long exitTime;//记录退出时间

    @Override
    public void init() {

        //设置NavigationView顶部布局
        mNavigationView.inflateHeaderView(R.layout.nav_header);
        //设置NavigationView菜单布局
        mNavigationView.inflateMenu(R.menu.nav_menu);
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
    public void onNavigationViewMenuItemSelected(NavigationView nav) {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    // TODO: 2016/3/5 点击跳转Activity，别忘了过场动画
                    case R.id.nav_item1:
                        break;
                    case R.id.nav_item2:
                        break;
                    case R.id.nav_item3:
                        break;
                    case R.id.nav_item4:
                        break;
                }

                //设置选中项  关闭菜单
                item.setChecked(true);
                closeDrawer();

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


}
