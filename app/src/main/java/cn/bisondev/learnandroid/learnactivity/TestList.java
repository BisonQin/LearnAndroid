package cn.bisondev.learnandroid.learnactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.bisondev.learnandroid.R;

public class TestList extends ListActivity {

    private Toolbar toolbar;

    private int[] img = { R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher };
    private String[] text = { "列表测试1", "列表测试2", "列表测试3", "列表测试4" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);        //注意，这个布局里面要包含ListView且id为android:id="@android:id/list"
        initView();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setActionBar(toolbar);
        }
        setTitle("ListActivity");

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < img.length; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();

            item.put("img", img[i]);
            item.put("text", text[i] + ": ListActivity");

            dataList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, dataList,
                R.layout.list_main, new String[] { "img", "text" }, new int[] {
                R.id.imgView, R.id.tView });

        ListView listView = this.getListView();         //获取布局中的ListView
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
