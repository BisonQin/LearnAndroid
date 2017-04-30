package cn.bisondev.learnandroid.learnactivity;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.bisondev.learnandroid.R;

public class ExpandableListActivityTest extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logos = new int[]{
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher_round,
                    R.mipmap.ic_launcher
            };
            private String[][] strs = new String[][]{
                    {"测试1", "测试1.1", "测试1.2", "测试1.3"},
                    {"测试2", "测试2.1", "测试2.2", "测试2.3", "测试2.4"},
                    {"测试3", "测试3.1", "测试2.2"}
            };

            @Override
            public int getGroupCount() {
                return strs.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return strs[groupPosition].length;
            }

            /**
             * 获取指定组位置处的组数据
             * @param groupPosition
             * @return
             */
            @Override
            public Object getGroup(int groupPosition) {
                return strs[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return strs[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            /**
             * 该方法决定每个组选项的外观
             * @param groupPosition
             * @param isExpanded
             * @param convertView
             * @param parent
             * @return
             */
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableListActivityTest.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                ImageView logo = new ImageView(ExpandableListActivityTest.this);
                logo.setImageResource(logos[groupPosition]);

                ll.addView(logo);

                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(ExpandableListActivityTest.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0 ,0);
                textView.setTextSize(20);
                return textView;
            }

            /**
             * 该方法决定每个子选项的外观
             * @param groupPosition
             * @param childPosition
             * @param isLastChild
             * @param convertView
             * @param parent
             * @return
             */
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

        setListAdapter(adapter);
    }
}
