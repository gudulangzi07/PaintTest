package com.example.painttest.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.painttest.R;

/**
 * @ClassName: BombBoxView
 * @author: 张京伟
 * @date: 2017/1/10 18:03
 * @Description:
 * @version: 1.0
 */
public class BombBoxView extends LinearLayout {

    private int[] widthAndHeightPixels;//点击控件的宽高
    private int[] location;//点击控件在屏幕上坐标

    private Paint paint;
    private RectF rectF;
    private Path path;

    private int rectF_width;
    private int rectF_height;

    public BombBoxView(Context context) {
        this(context, null);
    }

    public BombBoxView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BombBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @SuppressLint("NewApi")
    public BombBoxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
        if (attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BombBoxView);
            rectF_width = typedArray.getDimensionPixelSize(R.styleable.BombBoxView_rectF_width, 0);
            rectF_height = typedArray.getDimensionPixelSize(R.styleable.BombBoxView_rectF_height, 0);
        }

        init();
    }

    public void setWidthAndHeightPixels(int[] widthAndHeightPixels) {
        this.widthAndHeightPixels = widthAndHeightPixels;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    //加载默认属性
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);//消除锯齿

        rectF = new RectF();
        path = new Path();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        int layoutWidth = 0;    // 容器已经占据的宽度
        int layoutHeight = 0;   // 容器已经占据的宽度
        int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少
        for(int i = 0; i<count; i++){
            View child = getChildAt(i);
            //注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            if(layoutWidth<getWidth()){
                //如果一行没有排满，继续往右排列
                left = layoutWidth;
                right = left+childMeasureWidth;
                top = layoutHeight;
                bottom = top+childMeasureHeight;
            } else{
                //排满后换行
                layoutWidth = 0;
                layoutHeight += maxChildHeight;
                maxChildHeight = 0;

                left = layoutWidth;
                right = left+childMeasureWidth;
                top = layoutHeight;
                bottom = top+childMeasureHeight;
            }

            layoutWidth += childMeasureWidth;  //宽度累加
            if(childMeasureHeight>maxChildHeight){
                maxChildHeight = childMeasureHeight;
            }

            //确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, right, bottom);
        }
    }

//    /**
//     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
//     */
//    @SuppressLint("NewApi")
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // 计算出所有的childView的宽和高
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_perent都是填充屏幕)
//        //稍后会重新写这个方法，能达到wrap_content的效果
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画控件的三角形
        path.moveTo(location[0] + widthAndHeightPixels[0] / 2, 0);//画三角的起点
        path.lineTo(location[0] + widthAndHeightPixels[0] / 2 - widthAndHeightPixels[1] / 4, widthAndHeightPixels[1] / 4);
        path.lineTo(location[0] + widthAndHeightPixels[0] / 2 + widthAndHeightPixels[1] / 4, widthAndHeightPixels[1] / 4);
        path.close();
        canvas.drawPath(path, paint);

        int left = location[0] + widthAndHeightPixels[0] / 2 - rectF_width / 2;
        int right = location[0] + widthAndHeightPixels[0] / 2 + rectF_width / 2;
        int top = widthAndHeightPixels[1] / 4;
        int bottom = widthAndHeightPixels[1] / 4 + rectF_height;

        //画的控件大小
        rectF.set(left, top, right, bottom);
        canvas.drawRoundRect(rectF, 20, 20, paint);
        super.onDraw(canvas);
    }

}
