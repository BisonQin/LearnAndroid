package cn.bisondev.learnandroid.learnnetwork.jsoup;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.base.BaseActivity;

/**
 * 测试Jsoup，抓取IT之家的新闻
 */
public class TestJsoupActivity extends BaseActivity {

    private static final String TAG = "TestJsoupActivity";

    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeFreshLayout;

    private JsoupAdapter mAdapter;
    private List<ArticleItem> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mSwipeFreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mToolbar.setTitle("测试Jsoup");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSwipeFreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent, getTheme()));
        } else {
            mSwipeFreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        }
        mSwipeFreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datas.clear();
                getData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new JsoupAdapter(getBaseContext(), datas, R.layout.item_article);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initLogic() {
        mSwipeFreshLayout.setRefreshing(true);
        getData();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_test_jsoup;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public boolean canBack() {
        return true;
    }

    private void getData() {
        try {
            //异步网络请求
            new AsyncTask<URL, Integer, String>() {
                @Override
                protected String doInBackground(URL... params) {
                    try {
                        //从URL加载一个Document对象
                        Document doc = Jsoup.connect(params[0].toString()).get();
                        //获取一个块
                        Elements elements = doc.select("div.new-list-1");

                        Elements elementsLiTop = elements.select("li.top");
                        for(int i = 0; i < elementsLiTop.size(); i++) {
                            Element element = elementsLiTop.get(i);

                            ArticleItem item = new ArticleItem();
                            item.setToTop(0);       //置顶
                            item.setTime(element.select("span.date").text());
                            item.setTitle(element.select("span.title").text());
                            item.setUrl(element.select("a").attr("href"));
                            datas.add(item);

                            Log.i(TAG, "【置顶】\n时间为:" + element.select("span.date").text() +
                                    "\n标题为：" + element.select("span.title").text() +
                                    "\n链接为：" + element.select("a").attr("href"));
                        }

                        Elements elementsLiNew = elements.select("li.new");
                        for(int i = 0; i < elementsLiNew.size(); i++) {
                            Element element = elementsLiNew.get(i);

                            ArticleItem item = new ArticleItem();
                            item.setTime(element.select("span.date").text());
                            item.setTitle(element.select("span.title").text());
                            item.setUrl(element.select("a").attr("href"));
                            datas.add(item);

                            Log.i(TAG, "\n时间为:" + element.select("span.date").text() +
                                    "\n标题为：" + element.select("span.title").text() +
                                    "\n链接为：" + element.select("a").attr("href"));
                        }

                    }catch (IOException e) {
                        Log.i(TAG, "initLogic: " + e.toString());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    mAdapter.notifyDataSetChanged();
                    mSwipeFreshLayout.setRefreshing(false);
                }
            }.execute(new URL("http://www.ithome.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
