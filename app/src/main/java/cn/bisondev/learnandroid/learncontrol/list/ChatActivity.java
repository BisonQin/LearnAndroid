package cn.bisondev.learnandroid.learncontrol.list;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.bisondev.learnandroid.R;

public class ChatActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_item_list_view);

        mListView = (ListView) findViewById(R.id.listView_chat);
        ChatItemListViewBean bean1 = new ChatItemListViewBean(
                0,
                "Hello,how are you?",
                BitmapFactory.decodeResource(getResources(), R.drawable.in_icon));

        ChatItemListViewBean bean2 = new ChatItemListViewBean(
                1,
                "I'm fine.Thank you,and you?",
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        ChatItemListViewBean bean3 = new ChatItemListViewBean(
                0,
                "I'm fine,too.",
                BitmapFactory.decodeResource(getResources(), R.drawable.in_icon));

        ChatItemListViewBean bean4 = new ChatItemListViewBean(
                1,
                "Bye bye",
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        ChatItemListViewBean bean5 = new ChatItemListViewBean(
                0,
                "See you",
                BitmapFactory.decodeResource(getResources(), R.drawable.in_icon));

        List<ChatItemListViewBean> data = new ArrayList<ChatItemListViewBean>();
        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        data.add(bean5);
        mListView.setAdapter(new ChatItemListViewAdapter(this, data));
    }
}
