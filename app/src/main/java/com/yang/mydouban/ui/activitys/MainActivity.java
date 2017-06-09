package com.yang.mydouban.ui.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yang.mydouban.base.BaseActivity;
import com.yang.mydouban.ui.fragments.HomeFragment;
import com.yang.mydouban.ui.fragments.MovieFragment;
import com.yang.mydouban.R;
import com.yang.mydouban.ui.views.NoScrollViewPager;

public class MainActivity extends BaseActivity {


    private NoScrollViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyViewPagerAdapter mAdapter;

    //Tab 文字
    private final int[] TAB_TITLES = new int[]{R.string.tab_home,R.string.tab_movie,R.string.tab_me};
    //Tab 图片
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_home_selector,R.drawable.tab_movie_selector,R.drawable.tab_me_selector};
    //Fragment 数组
    private final Fragment[] TAB_FRAGMENTS = new Fragment[] {new HomeFragment(),new MovieFragment(),new MovieFragment()};
    //Tab 数目
    private final int COUNT = TAB_TITLES.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }


    private void initViews() {
        mTabLayout = (TabLayout)findViewById(R.id.tab_bottom);
        setTabs(mTabLayout,this.getLayoutInflater(),TAB_TITLES,TAB_IMGS);

        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager = (NoScrollViewPager) findViewById(R.id.tab_content);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setScroll(false);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    /**
     * @description: 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs){
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom,null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView)view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);

        }
    }

    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }
}
