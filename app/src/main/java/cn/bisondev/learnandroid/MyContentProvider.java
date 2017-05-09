package cn.bisondev.learnandroid;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.bisondev.learnandroid.utils.LogUtils;

/**
 * Created by Bison on 2017/4/27.
 */

public class MyContentProvider extends ContentProvider {

    private static final String TAG = "MyContentProvider";

    @Override
    public boolean onCreate() {
        Log.d(TAG, LogUtils.logThis());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, LogUtils.logThis());
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, LogUtils.logThis());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, LogUtils.logThis());
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, LogUtils.logThis());
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, LogUtils.logThis());
        return 0;
    }
}
