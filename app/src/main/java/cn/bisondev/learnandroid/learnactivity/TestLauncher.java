package cn.bisondev.learnandroid.learnactivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.learncontrol.ControlActivity;
import cn.bisondev.learnandroid.learnlayout.LayoutActivity;
import cn.bisondev.learnandroid.leranhardware.HardActivity;

public class TestLauncher extends LauncherActivity {

    //Activity的名字
    private String[] actNames = {"布局", "Activity", "硬件", "UI控件"};
    private Class<?>[] clazzs = {LayoutActivity.class,
            ActListActivity.class,
            HardActivity.class,
            ControlActivity.class};

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.home_item, actNames);
        listView = getListView();
        if(null != listView) {
            //设置该窗口显示的裂变所需的Adapter
            listView.setAdapter(adapter);
        }
    }

    /**
     * 根据列表项返回指定Activity对应的Intent
     * @param position
     * @return
     */
    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(TestLauncher.this, clazzs[position]);
    }
}
