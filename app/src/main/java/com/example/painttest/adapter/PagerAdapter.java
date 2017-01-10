package com.example.painttest.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.painttest.R;

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
    private Context context;

    public void setLists(List<Map.Entry<String, Fragment>> lists) {
        this.lists = lists;
    }

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
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
        Drawable image;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            image = context.getDrawable(R.drawable.back_up);
        }else{
            image = context.getResources().getDrawable(R.drawable.back_up);
        }
        SpannableString spannableString = new SpannableString(lists.get(position).getKey());
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
