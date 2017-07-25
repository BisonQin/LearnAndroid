package cn.bisondev.learnandroid.learncontrol.customize;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Author: Bison
 * Date: 2017/7/22
 * Email: bisonqin@gmail.com
 */
public class MyScrollView extends ViewGroup {

    private int mScreenHeight;
    private Scroller mScroller;
    private int mLastY;
    private int mStart;
    private int mEnd;

    public MyScrollView(Context context) {
        super(context);
        initView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //获取屏幕参数
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);
    }


    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //在滚动之前，要先放置好它的子View，使用遍历的方式来通知子View对自身进行测量
        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 定位
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed,
                            int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        //每个子View占一屏的高度，因此整个ViewGroup的高度即子View的个数乘以屏幕的高度
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);

        //遍历放置子View
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    /**
     * 添加该控件的触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                //记录触摸起点
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:         //手指离开后的处理事件，产生粘性效果
                int dScrollY = checkAlignment();
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, mScreenHeight - dScrollY);
                    }
                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    private int checkAlignment() {
        int mEnd = getScrollY();
        boolean isUp = ((mEnd - mStart) > 0);
        int lastPrev = mEnd % mScreenHeight;
        int lastNext = mScreenHeight - lastPrev;
        if (isUp) {
            //向上的
            return lastPrev;
        } else {
            return -lastNext;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}