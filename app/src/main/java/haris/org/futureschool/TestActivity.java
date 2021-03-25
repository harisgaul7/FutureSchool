package haris.org.futureschool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.InputStream;

import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.library.DownloadImageTask;


public class TestActivity extends AppCompatActivity {

    ImageView ivTes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tes);

        ivTes = findViewById(R.id.iv_tes);

        String url = new BaseUrl().BASE_URL;
        new DownloadImageTask((ImageView) findViewById(R.id.iv_tes))
                .execute(url+"future_picture/slider/lomba_1.jpg");

    }
}
