package com.example.painttest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.painttest.R;

/**
 * @ClassName: FragmentOne
 * @author: 张京伟
 * @date: 2016/12/8 12:03
 * @Description:
 * @version: 1.0
 */
public class FragmentOne extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, null);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
