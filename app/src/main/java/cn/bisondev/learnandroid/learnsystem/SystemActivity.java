package cn.bisondev.learnandroid.learnsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.bisondev.learnandroid.Config;
import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;
import cn.bisondev.learnandroid.learncontrol.ControlActivity;
import cn.bisondev.learnandroid.learncontrol.securitycode.SecurityCodeActivity;
import cn.bisondev.learnandroid.learnsystem.sms.SMSActivity;

public class SystemActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("系统服务");
    }

    @Override
    protected void setItemOnClickListener() {
        mAdapter.setItemClickListner(new BaseRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = null;
                switch (position) {
                    case Config.SMS:
                        intent = new Intent(SystemActivity.this, SMSActivity.class);
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
        return R.layout.activity_system;
    }

    @Override
    protected int provideItemArrayId() {
        return R.array.system_service;
    }

    @Override
    protected boolean canBack() {
        return true;
    }
}
