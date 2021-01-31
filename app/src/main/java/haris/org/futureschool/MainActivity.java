package haris.org.futureschool;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import haris.org.futureschool.fragment.AkunFragment;
import haris.org.futureschool.fragment.BerandaFragment;
import haris.org.futureschool.fragment.BeritaFragment;
import haris.org.futureschool.fragment.InboxFragment;
import haris.org.futureschool.fragment.SekolahFragment;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView btnNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        loadFragmentUtama(new BerandaFragment());

        btnNavView = findViewById(R.id.bn_main);
        btnNavView.setOnNavigationItemSelectedListener(this);

        showBadge(this, btnNavView, R.id.berita_menu, "3");
        showBadge(this, btnNavView, R.id.inbox_menu, "2");
    }

    private boolean loadFragmentUtama(Fragment fr){
        if (fr != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fr).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.beranda_menu:
                fragment = new BerandaFragment();
                break;
            case R.id.sekolah_menu:
                fragment = new SekolahFragment();
                break;
            case R.id.berita_menu:
                fragment = new BeritaFragment();
                removeBadge(btnNavView, R.id.berita_menu);
                break;
            case R.id.inbox_menu:
                fragment = new InboxFragment();
                removeBadge(btnNavView, R.id.inbox_menu);
                break;
            case R.id.akun_menu:
                fragment = new AkunFragment();
                break;
        }
        return loadFragmentUtama(fragment);
    }

    public static void showBadge(Context context, BottomNavigationView
            bottomNavigationView, @IdRes int itemId, String value) {
        removeBadge(bottomNavigationView, itemId);
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        View badge = LayoutInflater.from(context).inflate(R.layout.notification, bottomNavigationView, false);

        TextView text = badge.findViewById(R.id.notification_badge);
        text.setText(value);
        itemView.addView(badge);
    }

    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
    }
}

