package cn.bisondev.learnandroid.learncontrol.list;

import android.support.v7.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.bisondev.learnandroid.R;

/**
 * 滑动隐藏的ListView
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class ScrollHideListView extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;

    private String[] mStr = new String[20];
    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = true;

    View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirstY > mTouchSlop) {
                        direction = 0;// down
                    } else if (mFirstY - mCurrentY > mTouchSlop) {
                        direction = 1;// up
                    }
                    if (direction == 1) {           //向上
                        if (mShow) {
                            toolbarAnim(1);         //hide
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {    //向下
                        if (!mShow) {
                            toolbarAnim(0);         //show
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_hide);

        //获取系统认为的最低滑动距离
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView) findViewById(R.id.list_view);

        for (int i = 0; i < mStr.length; i++) {
            mStr[i] = "Item " + i;
        }

        //给ListView增加一个HeadView，避免第一个Item被Toolbar遮挡
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(
                        R.dimen.abc_action_bar_default_height_material//通过该属性获取系统Actionbar的高度
                )));
        mListView.addHeaderView(header);

        mListView.setAdapter(new ArrayAdapter<String>(
                ScrollHideListView.this,
                android.R.layout.simple_expandable_list_item_1,
                mStr));
        mListView.setOnTouchListener(mTouchListener);
    }

    /**
     * 隐藏Toolbar
     * 0即显示
     * 1即隐藏
     * @param flag
     */
    private void toolbarAnim(int flag) {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (flag == 0) {
            mAnimator = ObjectAnimator.ofFloat(
                    mToolbar,
                    "translationY",
                    mToolbar.getTranslationY(),
                    0);
        } else {
            mAnimator = ObjectAnimator.ofFloat(
                    mToolbar,
                    "translationY",
                    mToolbar.getTranslationY(),
                    -mToolbar.getHeight());
        }
        mAnimator.start();
    }
}