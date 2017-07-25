package cn.bisondev.learnandroid.learncontrol.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.bisondev.learnandroid.R;

/**
 * 显示聊天信息的Adapter
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class ChatItemListViewAdapter extends BaseAdapter {

    private List<ChatItemListViewBean> mData;
    private LayoutInflater mInflater;

    public ChatItemListViewAdapter(Context context, List<ChatItemListViewBean> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
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
    public int getItemViewType(int position) {
        ChatItemListViewBean bean = mData.get(position);
        return bean.getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            //通过判断类型值来决定实例化哪一个布局，从而实现在一个ListView中多个布局内容的添加
            if(getItemViewType(position) == 0) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.chat_item_in, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon_in);
                holder.text = (TextView) convertView.findViewById(R.id.text_in);
            } else {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.chat_item_out, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon_out);
                holder.text = (TextView) convertView.findViewById(R.id.text_out);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageBitmap(mData.get(position).getIcon());
        holder.text.setText(mData.get(position).getText());
        return convertView;
    }

    private final class ViewHolder {
        public ImageView icon;
        public TextView text;
    }
}
