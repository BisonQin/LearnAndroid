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
import cn.bisondev.learnandroid.learnnetwork.NetworkActivity;
import cn.bisondev.learnandroid.learnsystem.SystemActivity;
import cn.bisondev.learnandroid.leranhardware.HardActivity;
import cn.bisondev.learnandroid.utils.LogUtils;

public class MainActivity extends BaseRecyclerViewActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, LogUtils.logThis());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, LogUtils.logThis());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, LogUtils.logThis());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, LogUtils.logThis());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, LogUtils.logThis());
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, LogUtils.logThis());
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
                Class<?> clazz = null;
                switch (position) {
                    case Config.LAYOUT:         //布局
                        clazz = LayoutActivity.class;
                        break;
                    case Config.ACTIVITY:       //Activity
                        clazz = ActListActivity.class;
                        break;
                    case Config.HARDWARE:       //硬件
                        clazz = HardActivity.class;
                        break;
                    case Config.CONTROL:        //UI控件
                        clazz = ControlActivity.class;
                        break;
                    case Config.SYSTEM_SERVICE: //系统服务
                        clazz = SystemActivity.class;
                        break;
                    case Config.NETWORK:        //网络
                        clazz = NetworkActivity.class;
                        break;
                }
                if(null != clazz) {
                    startActivity(new Intent(MainActivity.this, clazz));
                }
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int provideItemArrayId() {
        return R.array.home;
    }
}
