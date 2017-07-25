package cn.bisondev.learnandroid.learncontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.bisondev.learnandroid.Config;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.base.BaseRecyclerViewActivity;
import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.learncontrol.customize.CustomizeActivity;
import cn.bisondev.learnandroid.learncontrol.list.ChatActivity;
import cn.bisondev.learnandroid.learncontrol.list.FocusListViewTest;
import cn.bisondev.learnandroid.learncontrol.list.ListViewActivity;
import cn.bisondev.learnandroid.learncontrol.list.ScrollHideListView;
import cn.bisondev.learnandroid.learncontrol.scroll.DragViewGroupTest;
import cn.bisondev.learnandroid.learncontrol.scroll.DragViewTest;
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
    protected int provideItemArrayId() {
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
                Class<?> clazz = null;
                switch (position) {
                    case Config.SECURITY_CODE_EDIT_TEXT:
                        clazz = SecurityCodeActivity.class;
                        break;
                    case Config.CUSTOMIZE_VIEW:
                        clazz = CustomizeActivity.class;
                        break;
                    case Config.LIST_VIEW:
                        clazz = ListViewActivity.class;
                        break;
                    case Config.SCROLL_HIDE_LIST_VIEW:
                        clazz = ScrollHideListView.class;
                        break;
                    case Config.CHAT_ITEM_LIST_VIEW:
                        clazz = ChatActivity.class;
                        break;
                    case Config.FOCUS_LIST_VIEW:
                        clazz = FocusListViewTest.class;
                        break;
                    case Config.SCROLL_VIEW:
                        clazz = DragViewTest.class;
                        break;
                    case Config.DRAG_VIEW_HELPER:
                        clazz = DragViewGroupTest.class;
                        break;
                }
                if(null != clazz) {
                    startActivity(new Intent(ControlActivity.this, clazz));
                }
            }
        });
    }
}
