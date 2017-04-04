package cn.bisondev.learnandroid.learncontrol.securitycode;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.bisondev.learnandroid.R;

/**
 * Created by Bison on 2017/3/28.
 */

public class SecurityCodeView extends RelativeLayout {

    private EditText editText;
    private TextView[] textViews;
    private StringBuffer stringBuffer = new StringBuffer();
    private int count = 4;                                          //验证码方框的个数
    private String inputContent;
    private InputCompleteListener inputCompleteListener;

    public SecurityCodeView(Context context) {
        this(context, null);
    }

    public SecurityCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecurityCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textViews = new TextView[4];
        View.inflate(context, R.layout.view_security_code, this);

        editText = (EditText) findViewById(R.id.item_edittext);
        textViews[0] = (TextView) findViewById(R.id.item_code_iv1);
        textViews[1] = (TextView) findViewById(R.id.item_code_iv2);
        textViews[2] = (TextView) findViewById(R.id.item_code_iv3);
        textViews[3] = (TextView) findViewById(R.id.item_code_iv4);

        editText.setCursorVisible(false);           //隐藏光标
        setListener();
    }

    private void setListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //注意，字符不为""时才进行操作
                if(!editable.toString().equals("")) {
                    if(stringBuffer.length() > 3) {         //数字满了再接收输入
                        editText.setText("");
                        return;
                    } else {
                        //将数字添加到StringBuffer中
                        stringBuffer.append(editable);
                        editText.setText("");           //添加后将EditText置空，造成没有文字输入的错觉
                        //Log.e("TAG", "afterTextChanged():stringBuffer" + stringBuffer);

                        count = stringBuffer.length();              //记录StringBuffer的长度
                        inputContent = stringBuffer.toString();
                        if(stringBuffer.length() == 4) {
                            //文字长度为4，则调用完成输入的监听
                            if(inputCompleteListener != null) {
                                inputCompleteListener.inputComplete();
                            }
                        }
                    }

                    /**
                     * 给TextView加边框
                     */
                    for(int i = 0;i < stringBuffer.length(); i++) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                        textViews[i].setBackgroundResource(R.mipmap.bg_verify_press);
                    }
                }
            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if(onKeyDelete()) {
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public boolean onKeyDelete() {
        if (count == 0) {
            count = 4;
            return true;
        }
        if (stringBuffer.length() > 0) {
            //删除相应位置的字符
            stringBuffer.delete((count - 1), count);
            count--;

            //   Log.e(TAG, "afterTextChanged: stringBuffer is " + stringBuffer);
            inputContent = stringBuffer.toString();
            textViews[stringBuffer.length()].setText("");           //给最后一个TextView置空
            textViews[stringBuffer.length()].setBackgroundResource(R.mipmap.bg_verify);

            if (inputCompleteListener != null)
                inputCompleteListener.deleteContent(true);          //有删除就通知manger

        }
        return false;
    }

    /**
     * 清空输入内容
     */
    public void clearEditText() {
        stringBuffer.delete(0, stringBuffer.length());
        inputContent = stringBuffer.toString();
        for(int i = 0; i < textViews.length; i++) {
            textViews[i].setText("");
            textViews[i].setBackgroundResource(R.mipmap.bg_verify);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {
        void inputComplete();

        void deleteContent(boolean isDelete);
    }

    /**
     * 获取输入文本
     */
    public String getEditContent() {
        return inputContent;
    }
}
