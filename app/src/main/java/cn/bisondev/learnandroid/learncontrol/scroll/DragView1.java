package cn.bisondev.learnandroid.learncontrol.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 视图坐标
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class DragView1 extends View {

    private int lastX;
    private int lastY;

    public DragView1(Context context) {
        super(context);
        initView();
    }

    public DragView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 给View设置背景颜色，便于观察
        setBackgroundColor(Color.BLUE);
    }

    // 视图坐标方式
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left、top、right、bottom的基础上加上偏移量
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);

                //以下方法是系统提供的一个对左右、上下移动的API封装
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //通过setLayoutParams改变偏移量
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //使用scrollBy改变偏移
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
        }
        return true;
    }
}