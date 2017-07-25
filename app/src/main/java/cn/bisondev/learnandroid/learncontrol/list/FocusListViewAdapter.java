package cn.bisondev.learnandroid.learncontrol.list;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.bisondev.learnandroid.R;

/**
 * 动态改变ListView布局的Adapter
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class FocusListViewAdapter extends BaseAdapter {

    private List<String> mData;
    private Context mContext;
    private int mCurrentItem = 0;

    public FocusListViewAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        if(mCurrentItem == position) {
            layout.addView(addFocusView(position));
        } else {
            layout.addView(addNormalView(position));
        }
        return layout;
    }

    public void setCurrentItem(int currentItem) {
        this.mCurrentItem = currentItem;
    }

    private View addFocusView(int i) {
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(R.mipmap.ic_launcher);
        return iv;
    }

    private View addNormalView(int i) {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(R.drawable.in_icon);
        layout.addView(iv, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView tv = new TextView(mContext);
        tv.setText(mData.get(i));
        layout.addView(tv, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        layout.setGravity(Gravity.CENTER);
        return layout;
    }
}
