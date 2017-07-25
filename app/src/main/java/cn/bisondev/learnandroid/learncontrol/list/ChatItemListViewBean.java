package cn.bisondev.learnandroid.learncontrol.list;

import android.graphics.Bitmap;

/**
 * 聊天内容的Bean类
 * Author: Bison
 * Date: 2017/7/24
 * Email: bisonqin@gmail.com
 */
public class ChatItemListViewBean {

    private int type;
    private String text;
    private Bitmap icon;

    public ChatItemListViewBean() {

    }

    public ChatItemListViewBean(int type, String text, Bitmap icon) {
        this.type = type;
        this.text = text;
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
