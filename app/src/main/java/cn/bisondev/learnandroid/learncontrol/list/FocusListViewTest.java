package cn.bisondev.learnandroid.learncontrol.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.bisondev.learnandroid.R;

/**
 * 动态改变ListView布局的测试
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class FocusListViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        ListView listView = (ListView) findViewById(R.id.focus_listView);
        List<String> data = new ArrayList<String>();
        data.add("I am item 1");
        data.add("I am item 2");
        data.add("I am item 3");
        data.add("I am item 4");
        data.add("I am item 5");

        final FocusListViewAdapter adapter = new FocusListViewAdapter(this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.setCurrentItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
