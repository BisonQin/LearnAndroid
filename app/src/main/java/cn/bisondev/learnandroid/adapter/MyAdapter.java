package cn.bisondev.learnandroid.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.adapter.BaseViewHolder;
import cn.bisondev.learnandroid.learnlayout.callback.AddItemCallback;
import cn.bisondev.learnandroid.learnlayout.callback.RemoveItemCallback;

/**
 * Created by Basil on 2017/3/24.
 */

public class MyAdapter extends BaseRecyclerViewAdapter<String> {

    private List<String> datas;

    public MyAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        TextView tv = holder.getView(R.id.item_tv);
        tv.setText(data);
    }

    public void removeItem(int position, RemoveItemCallback callback) {
        String removed = datas.get(position);
        datas.remove(position);
        notifyItemRemoved(position);
        callback.onSuccess(removed);
    }

    public void addItem(int position, String string, AddItemCallback callback) {
        datas.add(position, string);
        notifyItemInserted(position);
        callback.onSuccess();
    }
}
