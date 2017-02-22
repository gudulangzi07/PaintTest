package com.example.painttest.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.painttest.R;
import com.example.painttest.widget.BombBoxView;

/**
 * @ClassName: FragmentOne
 * @author: 张京伟
 * @date: 2016/12/8 12:03
 * @Description:
 * @version: 1.0
 */
public class FragmentOne extends BaseFragment implements View.OnClickListener {

    private Button button;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        button = (Button) view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        button.setOnClickListener(this);
    }

    private void showPop() {
        //加载布局
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_layout, null);

        BombBoxView bombBoxView = (BombBoxView) layout.findViewById(R.id.bombBoxView);
        bombBoxView.setWidthAndHeightPixels(new int[]{button.getMeasuredWidth(), button.getMeasuredHeight()});

        int[] location = new int[2];
        button.getLocationOnScreen(location);
        bombBoxView.setLocation(location);

        //实例化popuWindow
        PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        //点击窗口外消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.showAsDropDown(button);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                showPop();
                break;
        }
    }
}
