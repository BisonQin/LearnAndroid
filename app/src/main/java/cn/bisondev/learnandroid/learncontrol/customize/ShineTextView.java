package cn.bisondev.learnandroid.learncontrol.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 一个文字闪动的TextView
 * Author: Bison
 * Date: 2017/7/22
 * Email: bisonqin@gmail.com
 */
public class ShineTextView extends AppCompatTextView {

    private int mViewWidth = 0;
    private float mTranslate;

    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;

    public ShineTextView(Context context) {
        super(context);
    }

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0) {
                //获取当前绘制TextView的Paint对象
                mPaint = getPaint();
                //创建一个渐变渲染器
                mLinearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{
                                Color.BLUE,
                                0xffffffff,
                                Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP
                );

                //给Paint对象设置原生TextView没有的LinearGradient属性
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //通过矩阵的方式来不断平移渐变效果，从而在绘制文字时，产生动态的闪动效果
        if(mGradientMatrix != null) {
            //平移的量
            mTranslate += mViewWidth / 5;
            //当平移的超过显示范围的时候
            if(mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
