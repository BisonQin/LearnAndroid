package cn.bisondev.learnandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.bisondev.learnandroid.utils.LogUtils;

/**
 * Created by Bison on 2017/4/27.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, LogUtils.logThis());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, LogUtils.logThis());
        return super.onStartCommand(intent, flags, startId);
    }
}
