package cn.bisondev.learnandroid.learncontrol.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.bisondev.learnandroid.R;

public class ListViewActivity extends AppCompatActivity {

    //private ListView mListView;
    private FlexibleListView mListView;
    private ViewHolderAdapter mAdapter;

    private List<String> mData;

    private static final String TAG = "ListViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initView();
    }

    private void initView() {
        mData = new ArrayList<>();
        //mListView = (ListView) findViewById(R.id.list_view);
        mListView = (FlexibleListView) findViewById(R.id.flexible_list_view);
        mAdapter = new ViewHolderAdapter(ListViewActivity.this, mData);

        for(int i=0; i<20; i++) {
            mData.add(String.valueOf(i));
        }
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //触摸时的操作
                        Log.d(TAG, "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动时的操作
                        Log.d(TAG, "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        //离开时的操作
                        Log.d(TAG, "ACTION_MOVE");
                        break;
                }
                return false;
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch(scrollState) {
                    case SCROLL_STATE_IDLE:
                        //滑动停止时
                        Log.d(TAG, "SCROLL_STATE_IDLE");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        //正在滚动
                        Log.d(TAG, "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    case SCROLL_STATE_FLING:
                        //手指抛动时，即手指用力滑动
                        //在离开后ListVIew由于惯性继续滑动
                        Log.d(TAG, "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //滚动时一直调用
                Log.d(TAG, "onScroll");
            }
        });
    }
}
