package cn.bisondev.learnandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.bisondev.learnandroid.utils.LogUtils;

/**
 * Created by Bison on 2017/4/27.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, LogUtils.logThis());
    }
}
