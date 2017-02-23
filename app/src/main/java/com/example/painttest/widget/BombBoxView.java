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
import android.view.ViewGroup;

import com.example.painttest.R;

/**
 * @ClassName: BombBoxView
 * @author: 张京伟
 * @date: 2017/1/10 18:03
 * @Description:
 * @version: 1.0
 */
public class BombBoxView extends ViewGroup {

    private int[] widthAndHeightPixels;//点击控件的宽高
    private int[] location;//点击控件在屏幕上坐标

    private Paint paint;
    private RectF rectF;
    private Path path;

    private int rectF_width;
    private int rectF_height;
    private boolean isCustom;//是否使用自定义的宽高

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
            isCustom = typedArray.getBoolean(R.styleable.BombBoxView_isCustom_width_and_height, false);
            typedArray.recycle();
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
        for (int i = 1; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            //这里获取上面计算过的 子View的宽、高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            childView.layout(0, 0, childWidth , childHeight);
        }
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isCustom){
            setMeasuredDimension(rectF_width, rectF_height);
        }else{
            int parentWidthSize = 0;
            int parentHeightSize = 0;
            for (int i = 1; i < getChildCount(); i++) {
                View childView = getChildAt(i);
                // 设定子view宽、高
                childView.measure(
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY));
                parentWidthSize = childView.getMeasuredWidth();
                parentHeightSize += childView.getMeasuredHeight();
            }
            setMeasuredDimension(parentWidthSize, parentHeightSize);
        }
    }

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
