package com.test.ck.coordinatorlayout;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;

public class CooActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView iv;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<String> dataList;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coo);
        initView();
        initData();
        initTabVp();

        setAvatorChange();
    }

    private void initTabVp() {
        if (dataList != null){
            for (int i = 0;i < dataList.size();i++){
                fragments.add(new QJFragment());
                tabLayout.addTab(tabLayout.newTab());
            }
        }
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0;i < dataList.size();i++){
            tabLayout.getTabAt(i).setText(dataList.get(i));
        }
    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add("唐僧");
        dataList.add("猪八");
        dataList.add("沙僧");
        dataList.add("悟空");
    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv = (ImageView) findViewById(R.id.iv);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        collapsingToolbarLayout.setTitle("西天取经");


    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    /**
     * 渐变toolbar背景
     */
    private void setAvatorChange() {
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //verticalOffset始终为0以下的负数
                float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());

                toolbar.setBackgroundColor(changeAlpha(Color.WHITE,percent));
            }
        });
    }

    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}
