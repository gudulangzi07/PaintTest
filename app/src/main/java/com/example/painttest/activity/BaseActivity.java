package com.example.painttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @ClassName: BaseActivity
 * @author: 张京伟
 * @date: 2016/12/7 15:53
 * @Description:
 * @version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout();
        beforeInitView();
        initView();
        afterInitView();
    }

    /**
     * 设置布局文件
     */
    public abstract void setContentLayout();

    /**
     * 在实例化布局之前处理的逻辑
     */
    public abstract void beforeInitView();

    /**
     * 实例化布局文件/组件
     */
    public abstract void initView();

    /**
     * 在实例化布局之后处理的逻辑
     */
    public abstract void afterInitView();


    /**
     * onClick方法的封装，在此方法中处理点击
     * @param view 被点击的View对象
     */
    abstract public void onClickEvent(View view);

    @Override
    public void onClick(View view) {
        onClickEvent(view);
    }
}
