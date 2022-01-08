package haris.org.futureschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DaftarSekolahActivity extends AppCompatActivity {
    WebView daftar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_sekolah);

        daftar = (WebView) findViewById(R.id.wv_daftar_sekolah);
        daftar.setWebViewClient(new MyBrowser());

        daftar.getSettings().setLoadsImagesAutomatically(true);
        daftar.getSettings().setJavaScriptEnabled(true);
        daftar.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        daftar.loadUrl("https://future-school.bagussjm.space/");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
