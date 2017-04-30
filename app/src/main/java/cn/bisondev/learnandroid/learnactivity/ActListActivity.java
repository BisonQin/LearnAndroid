package cn.bisondev.learnandroid.learnactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.bisondev.learnandroid.Config;
import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;

public class ActListActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.menu_item_info:

                break;
        }
        return true;
    }

    @Override
    protected void setItemOnClickListener() {
        mAdapter.setItemClickListner(new BaseRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = null;
                switch (position) {
                    case Config.LISTACTIVITY:
                        intent = new Intent(ActListActivity.this, TestList.class);
                        break;
                    case Config.LAUNCHERACTIVITY:
                        intent = new Intent(ActListActivity.this, TestLauncher.class);
                        break;
                    case Config.EXPANDABLELISTACTIVITY:
                        intent = new Intent(ActListActivity.this, ExpandableListActivityTest.class);
                        break;
                    case Config.PREFERENCEACTICITY:
                        intent = new Intent(ActListActivity.this, PreferenceActivityTest.class);
                        break;
                }
                if(null != intent) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected boolean canBack() {
        return true;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_act_list;
    }

    @Override
    protected int provideItemArratId() {
        return R.array.activity;
    }
}
