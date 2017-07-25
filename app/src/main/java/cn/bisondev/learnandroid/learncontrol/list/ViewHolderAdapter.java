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
 * ViewHolder模式提高listView效率
 * （提高50%以上的效率）
 * Author: Bison
 * Date: 2017/7/23
 * Email: bisonqin@gmail.com
 */
public class ViewHolderAdapter extends BaseAdapter {

    private List<String> mData;
    private LayoutInflater mInflater;

    public ViewHolderAdapter(Context context, List<String> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //判断是否缓存
        if(convertView == null) {
            holder = new ViewHolder();
            //通过LayoutInflater实例化布局
            convertView = mInflater.inflate(R.layout.viewholder_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imageView);
            holder.title = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            //通过tag找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }
        //设置布局中控件要显示的视图
        holder.img.setBackgroundResource(R.mipmap.ic_launcher);
        holder.title.setText(mData.get(position));
        return convertView;
    }

    private final class ViewHolder {
        private ImageView img;
        private TextView title;
    }
}
