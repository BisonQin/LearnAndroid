package cn.bisondev.learnandroid.learnnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.bisondev.learnandroid.Config;
import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;
import cn.bisondev.learnandroid.learnnetwork.jsoup.TestJsoupActivity;

public class NetworkActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("网络");
    }

    @Override
    protected void setItemOnClickListener() {
        mAdapter.setItemClickListner(new BaseRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Class<?> clazz = null;
                switch (position) {
                    case Config.TEST_JSOUP:
                        clazz = TestJsoupActivity.class;
                        break;
                }
                if(null != clazz) {
                    startActivity(new Intent(NetworkActivity.this, clazz));
                }
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_network;
    }

    @Override
    protected int provideItemArratId() {
        return R.array.network;
    }

    @Override
    protected boolean canBack() {
        return true;
    }
}
