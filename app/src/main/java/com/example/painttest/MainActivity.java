package com.example.painttest;

import android.view.MotionEvent;

import com.example.painttest.activity.BaseTitleActivity;

public class MainActivity extends BaseTitleActivity {

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        //不显示返回按钮
        hideLeftLayout();
        setTitle("首页");
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideActionBar();
        return super.onTouchEvent(event);
    }
}
