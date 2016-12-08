package com.example.painttest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PagerAdapter
 * @author: 张京伟
 * @date: 2016/12/8 10:36
 * @Description:
 * @version: 1.0
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Map.Entry<String, Fragment>> lists;

    public void setLists(List<Map.Entry<String, Fragment>> lists) {
        this.lists = lists;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position).getValue();
    }

    @Override
    public int getCount() {
        return lists != null ? lists.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lists.get(position).getKey();
    }
}
