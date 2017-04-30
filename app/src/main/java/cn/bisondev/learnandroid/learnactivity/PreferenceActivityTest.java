package cn.bisondev.learnandroid.learnactivity;

import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import cn.bisondev.learnandroid.R;

public class PreferenceActivityTest extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //该方法用于为该界面设置一个标题按钮
        if(hasHeaders()) {
            Button button = new Button(this);
            button.setText("设置操作");
            //将该按钮添加到该界面上
            setListFooter(button);
        }
    }

    /**
     * 重写该方法，负责加载界面布局文件
     * @param target
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        //加载选项设置列表的布局文件
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    /**
     * 重写该方法，验证个PreferenceFragment是否有效
     * @param fragmentName
     * @return
     */
    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    /**
     * Fragment1
     */
    public static class Prefs1Fragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    /**
     * Fragment2
     */
    public static class Prefs2Fragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.display_prefs);
            //获取传入该Fragment的参数
            String website = getArguments().getString("website");
            Toast.makeText(getActivity(), "拿到的数据是：" + website, Toast.LENGTH_LONG).show();
        }
    }
}
