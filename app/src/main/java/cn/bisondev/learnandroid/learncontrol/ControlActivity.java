package cn.bisondev.learnandroid.learncontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.bisondev.learnandroid.Config;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;
import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.learncontrol.securitycode.SecurityCodeActivity;

public class ControlActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("UI控件");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_control;
    }

    @Override
    protected int provideItemArratId() {
        return R.array.uicontrol;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void setItemOnClickListener() {
        mAdapter.setItemClickListner(new BaseRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = null;
                switch (position) {
                    case Config.SECURITYCODE_EDITTEXT:
                        intent = new Intent(ControlActivity.this, SecurityCodeActivity.class);
                        break;
                }
                if(null != intent) {
                    startActivity(intent);
                }
            }
        });
    }
}
