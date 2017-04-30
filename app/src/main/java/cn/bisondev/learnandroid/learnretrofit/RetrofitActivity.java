package cn.bisondev.learnandroid.learnretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import cn.bisondev.learnandroid.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        button = (Button) findViewById(R.id.button);
    }


    private void test() {

    }
}
