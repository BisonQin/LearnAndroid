package cn.bisondev.learnandroid.learncontrol.securitycode;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import cn.bisondev.learnandroid.R;

/**
 * 方框验证码
 */
public class SecurityCodeActivity extends AppCompatActivity implements SecurityCodeView.InputCompleteListener{

    private SecurityCodeView editText;
    private TextView textView;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code);

        findViews();
        setListener();
    }

    public void setListener() {
        editText.setInputCompleteListener(this);
    }

    public void findViews() {
        editText = (SecurityCodeView) findViewById(R.id.scv_edittext);
        textView = (TextView) findViewById(R.id.tv_text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("方框验证码");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void inputComplete() {
        Toast.makeText(getApplicationContext(), "验证码是：" + editText.getEditContent(), Toast.LENGTH_LONG).show();
        if (!editText.getEditContent().equals("1234")) {
            textView.setText("验证码输入错误");
            textView.setTextColor(Color.RED);
        }
    }

    @Override
    public void deleteContent(boolean isDelete) {
        if (isDelete){
            textView.setText("输入验证码表示同意《用户协议》");
            textView.setTextColor(Color.BLACK);
        }
    }
}
