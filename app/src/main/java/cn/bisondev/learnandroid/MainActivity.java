package cn.bisondev.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;
import cn.bisondev.learnandroid.learnactivity.ActListActivity;
import cn.bisondev.learnandroid.learnactivity.TestList;
import cn.bisondev.learnandroid.learncontrol.ControlActivity;
import cn.bisondev.learnandroid.learnlayout.LayoutActivity;
import cn.bisondev.learnandroid.leranhardware.HardActivity;
import cn.bisondev.learnandroid.utils.LogUtils;

public class MainActivity extends BaseRecyclerViewActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, LogUtils.logThis());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.e(TAG, LogUtils.logThis());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, LogUtils.logThis());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, LogUtils.logThis());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, LogUtils.logThis());
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, LogUtils.logThis());
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, LogUtils.logThis());
        super.onDestroy();
    }

    @Override
    protected void setItemOnClickListener() {
        mAdapter.setItemClickListner(new BaseRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = null;
                switch (position) {
                    case Config.LAYOUT:
                        intent = new Intent(MainActivity.this, LayoutActivity.class);
                        break;
                    case Config.ACTIVITY:
                        intent = new Intent(MainActivity.this, ActListActivity.class);
                        break;
                    case Config.HARDWARE:
                        intent = new Intent(MainActivity.this, HardActivity.class);
                        break;
                    case Config.CONTROL:
                        intent = new Intent(MainActivity.this, ControlActivity.class);
                        break;
                }
                if(null != intent) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int provideItemArratId() {
        return R.array.home;
    }
}
