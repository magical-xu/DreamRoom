package com.idreamsky.dreamroom.ui;

import android.content.Intent;
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
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.ui.activity.GalleryActivity;
import com.idreamsky.dreamroom.ui.activity.InspirationActivity;
import com.idreamsky.dreamroom.ui.activity.RecommendActivity;
import com.idreamsky.dreamroom.ui.fragment.BrandShowFragment;
import com.idreamsky.dreamroom.ui.fragment.DecorationFragment;
import com.idreamsky.dreamroom.ui.fragment.EventFragment;
import com.idreamsky.dreamroom.ui.fragment.GeomancyFragment;
import com.idreamsky.dreamroom.ui.fragment.HomeFragment;
import com.idreamsky.dreamroom.ui.fragment.TestFragAdapter;
import com.idreamsky.dreamroom.util.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager
        .OnPageChangeListener {

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

    private TestFragAdapter mFragAdapter;

    private long exitTime;//记录退出时间
    private Menu nav_menu;//navigation菜单
    private String[] tab_titles;//TabLayout中的标题
    private List<Fragment> fragmentList;//ViewPager中的Fragment

    private HomeFragment mHomeFragment;//首页
    private DecorationFragment mDecorationFragment;//家装公司
    private EventFragment mEventFragment;//优选活动
    private BrandShowFragment mBrandShowFragment;//品牌展厅
    private GeomancyFragment mGeomancyFragment;//装修风水


    @Override
    public void init() {

        //获取tab标题
        tab_titles = getResources().getStringArray(R.array.tab_titles);
        initFragments();

        //设置显示ToolBar
        setSupportActionBar(mToolbar);

        //设置DrawerLayout开关指示器，即左边Icon
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mToggle.syncState();
        mDrawerLayout.setDrawerListener(mToggle);

        //设置NavigationView顶部布局
        mNavigationView.inflateHeaderView(R.layout.nav_header);
        //设置NavigationView菜单布局
        mNavigationView.inflateMenu(R.menu.nav_menu);
        //获取nav_menu
        nav_menu = mNavigationView.getMenu();
        //设置NavigationView菜单点击监听
        onNavigationViewMenuItemSelected(mNavigationView);

        mFragAdapter = new TestFragAdapter(getSupportFragmentManager(), tab_titles, fragmentList);
        mViewPager.setAdapter(mFragAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        mTabLayout.setupWithViewPager(mViewPager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        mTabLayout.setTabsFromPagerAdapter(mFragAdapter);
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
                ToastUtil.ToastShort(this, ConstantString.CLICK_MORE);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sys_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //ToolBar上的菜单
        if (id == R.id.id_menu_share) {
            ToastUtil.ToastShort(this, "分享");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * NavigationView菜单点击监听
     *
     * @param nav
     */
    public void onNavigationViewMenuItemSelected(final NavigationView nav) {

        getSupportFragmentManager();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                String msg = "";
                switch (item.getItemId()) {

                    // TODO: 2016/3/5 点击跳转Activity，别忘了过场动画
                    case R.id.nav_recommend:
                        msg = "精彩推荐";
                        intent.putExtra("data", msg);
                        startActivity(new Intent(MainActivity.this, RecommendActivity.class));
                        break;
                    case R.id.nav_gallery:
                        startActivity(new Intent(MainActivity.this, GalleryActivity.class));
                        break;
                    case R.id.nav_inspiration:
                        startActivity(new Intent(MainActivity.this, InspirationActivity.class));
                        break;
                    case R.id.nav_collection:
                        msg = "我的收藏";
                        intent.putExtra("data", msg);
                        startActivity(intent);
                        break;
                    case R.id.nav_about_author:
                        msg = "关于作者";
                        intent.putExtra("data", msg);
                        startActivity(intent);
                        break;
                    case R.id.nav_setting:
                        msg = "设置";
                        intent.putExtra("data", msg);
                        startActivity(intent);
                        break;
                }

                unCheckMenu();
                item.setChecked(true);

                return true;
            }
        });
    }

    public void switchPage(int index){

    }

    /**
     * 初始化导航Fragment
     */
    private void initFragments() {
        //初始化Fragment集合
        fragmentList = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mDecorationFragment = new DecorationFragment();
        mEventFragment = new EventFragment();
        mBrandShowFragment = new BrandShowFragment();
        mGeomancyFragment = new GeomancyFragment();

        fragmentList.add(mHomeFragment);
        fragmentList.add(mDecorationFragment);
        fragmentList.add(mEventFragment);
        fragmentList.add(mBrandShowFragment);
        fragmentList.add(mGeomancyFragment);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mToolbar.setTitle(tab_titles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
