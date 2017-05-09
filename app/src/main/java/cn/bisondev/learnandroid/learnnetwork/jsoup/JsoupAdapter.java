package cn.bisondev.learnandroid.learnnetwork.jsoup;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.adapter.BaseRecyclerViewAdapter;
import cn.bisondev.learnandroid.adapter.BaseViewHolder;

/**
 * Created by Bison on 2017/5/9.
 */

public class JsoupAdapter extends BaseRecyclerViewAdapter<ArticleItem> {

    private List<ArticleItem> datas;

    public JsoupAdapter(Context context, List<ArticleItem> datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    protected void bindData(BaseViewHolder holder, ArticleItem data, int position) {
        //时间TextView
        TextView tvTime = holder.getView(R.id.tv_time);
        //标题
        TextView tvTitle = holder.getView(R.id.tv_title);

        tvTime.setText(datas.get(position).getTime());
        tvTitle.setText(datas.get(position).getTitle());
    }
}
