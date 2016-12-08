package com.example.painttest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.painttest.activity.BaseTitleActivity;
import com.example.painttest.adapter.PagerAdapter;
import com.example.painttest.fragment.FragmentOne;
import com.example.painttest.fragment.FragmentThree;
import com.example.painttest.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseTitleActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private PagerAdapter pagerAdapter;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;

    private List<Map.Entry<String, Fragment>> lists;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void beforeInitView() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();

        lists = new ArrayList<>();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    @Override
    public void initView() {
        //不显示返回按钮
        hideLeftLayout();
        setTitle("首页");
    }

    @Override
    public void afterInitView() {
        LinkedHashMap<String, Fragment> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("卡片一", fragmentOne);
        linkedHashMap.put("卡片二", fragmentTwo);
        linkedHashMap.put("卡片三", fragmentThree);

        for (Map.Entry entry : linkedHashMap.entrySet()){
            lists.add(entry);
        }

        pagerAdapter.setLists(lists);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
