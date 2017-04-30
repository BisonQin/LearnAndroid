package cn.bisondev.learnandroid;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.bisondev.learnandroid.utils.LogUtils;

/**
 * 测试Application等的生命周期
 * Created by Bison on 2017/4/27.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e(TAG, LogUtils.logThis());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, LogUtils.logThis());
    }
}
