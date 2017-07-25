package cn.bisondev.learnandroid.learncontrol.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 自定义TextView
 * (为TextView添加一个外边框和一个内边框)
 * Author: Bison
 * Date: 2017/7/21
 * Email: bisonqin@gmail.com
 */
public class MyTextView extends AppCompatTextView {

    private Paint mPaint1;
    private Paint mPaint2;

    //在Java代码创建视图的时候调用，如果从xml填充的视图，就不会调用这个
    public MyTextView(Context context) {
        super(context);
    }

    //在xml创建但是没有指定style的时候被调用
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //定义画内框和外边框的画笔
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //在回调父类方法前，实现自己的逻辑，对TextView来说即是在绘制文本内容前
        //绘制外层矩形
        canvas.drawRect(
                0,
                0,
                getMeasuredWidth(),
                getMeasuredHeight(),
                mPaint1
        );

        //绘制内层矩形
        canvas.drawRect(
                10,
                10,
                getMeasuredWidth() - 10,
                getMeasuredHeight() - 10,
                mPaint2
        );
        canvas.save();
        //绘制文字前平移10像素
        canvas.translate(10, 0);
        //父类完成的方法，即绘制文本
        super.onDraw(canvas);
        //在回调父类方法后，实现自己的逻辑，对TextView来说即是在绘制文本内容后
        canvas.restore();
    }
}
