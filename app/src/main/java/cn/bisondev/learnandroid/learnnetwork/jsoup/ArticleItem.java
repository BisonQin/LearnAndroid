package cn.bisondev.learnandroid.learnnetwork.jsoup;

/**
 * IT之家的文章Item的bean类
 * Created by Bison on 2017/5/9.
 */

public class ArticleItem {

    private int toTop = 1;  //是否为置顶，是为0，不是为1，默认为1
    private String time;    //发布的时间
    private String title;   //标题
    private String url;     //链接地址

    public int getToTop() {
        return toTop;
    }

    public void setToTop(int toTop) {
        this.toTop = toTop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
