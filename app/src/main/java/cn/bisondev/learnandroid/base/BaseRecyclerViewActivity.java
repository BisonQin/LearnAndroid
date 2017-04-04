package cn.bisondev.learnandroid.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.MyAdapter;
import cn.bisondev.learnandroid.learnlayout.callback.AddItemCallback;
import cn.bisondev.learnandroid.learnlayout.callback.RemoveItemCallback;
import cn.bisondev.learnandroid.utils.SnackbarUtil;

/**
 * 封装RecyclerView的Activity
 * Created by Bison on 2017/3/28.
 */

public abstract class BaseRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar mToolbar;
    private ItemTouchHelper itemTouchHelper;
    protected MyAdapter mAdapter;

    private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar); //把Toolbar当做ActionBar给设置
            if (canBack()) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(true);//设置ActionBar一个返回箭头，主界面没有，次级界面有
            }
        }

        initRecyclerView();
        initItemHelper();
        itemTouchHelper.attachToRecyclerView(recyclerView);     //对RecyclerView进行绑定
    }

    public void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List list = Arrays.asList(getResources().getStringArray(provideItemArratId()));
        data = new ArrayList<>(list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new MyAdapter(this, data, R.layout.item_layout));

        setItemOnClickListener();
    }

    public void initItemHelper() {
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag = 0;
                int swipeFlag = 0;
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;        //设置为左右可滑动
                return makeMovementFlags(dragFlag, swipeFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(data, from, to);
                mAdapter.notifyItemMoved(from, to);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                mAdapter.removeItem(position, new RemoveItemCallback() {
                    @Override
                    public void onSuccess(final String string) {
                        SnackbarUtil.ShortSnackbar(recyclerView,
                                "你删除了第" + position + "个item",
                                SnackbarUtil.Warning).setAction("撤销",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mAdapter.addItem(position, string, new AddItemCallback() {
                                            @Override
                                            public void onSuccess() {
                                                SnackbarUtil.ShortSnackbar(recyclerView,
                                                        "撤销了删除第" + position + "个item",
                                                        SnackbarUtil.Confirm).show();
                                                recyclerView.scrollToPosition(position);
                                            }
                                        });
                                    }
                                }).setActionTextColor(Color.WHITE).show();
                    }
                });
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if(actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 判断当前 Activity 是否允许返回
     * 主界面不允许返回，次级界面允许返回
     *
     * @return false
     */
    protected boolean canBack() {
        return false;
    }

    abstract protected  void setItemOnClickListener();

    abstract protected int provideContentViewId();          //用于引入布局文件
    abstract protected int provideItemArratId();            //引入RecyclerView的数组资源
}
