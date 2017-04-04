package cn.bisondev.learnandroid.learnlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.bisondev.learnandroid.Config;
import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;

/**
 * 承载各种布局入口的Activity
 */
public class LayoutActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("布局");
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
                    case Config.CONSTRAINTACTIVITY:
                        intent = new Intent(LayoutActivity.this, ConstraintActivity.class);
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
        return R.layout.activity_layout;
    }

    @Override
    protected int provideItemArratId() {
        return R.array.layout;
    }
}
