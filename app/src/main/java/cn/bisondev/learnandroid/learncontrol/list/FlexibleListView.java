package cn.bisondev.learnandroid.learncontrol.list;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * 具有弹性的ListView
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class FlexibleListView extends ListView {

    private static int mMaxOverDistance = 50;
    private Context mContext;

    public FlexibleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public FlexibleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public FlexibleListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        //获取分辨率，使不同分辨率的弹性距离基本一致
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverDistance = (int) (density * mMaxOverDistance);
    }

    /**
     * 通过修改maxOverScrollY的值，达到使ListView具有弹性
     * @param deltaX
     * @param deltaY
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX
     * @param maxOverScrollY
     * @param isTouchEvent
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY,
                                   boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY,
                scrollX, scrollY,
                scrollRangeX, scrollRangeY,
                maxOverScrollX, mMaxOverDistance,
                isTouchEvent);
    }
}
