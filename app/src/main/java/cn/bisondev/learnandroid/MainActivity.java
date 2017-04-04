package cn.bisondev.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;
import cn.bisondev.learnandroid.learncontrol.ControlActivity;
import cn.bisondev.learnandroid.learnlayout.LayoutActivity;
import cn.bisondev.learnandroid.leranhardware.HardActivity;

public class MainActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
