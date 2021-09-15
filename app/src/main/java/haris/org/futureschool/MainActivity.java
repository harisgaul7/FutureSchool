package haris.org.futureschool;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import haris.org.futureschool.fragment.AkunFragment;
import haris.org.futureschool.fragment.BerandaFragment;
import haris.org.futureschool.fragment.BeritaFragment;
import haris.org.futureschool.fragment.InboxFragment;
import haris.org.futureschool.fragment.RekomendasiFragment;
import haris.org.futureschool.fragment.SekolahFragment;

// Ask perizinan
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.SEND_SMS;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView btnNavView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        loadFragmentUtama(new BerandaFragment());

        Intent intent = getIntent();

        // Ini method untuk memilih sekolah yang akan dibandingkan
        if (intent.hasExtra("id")) {
            loadFragmentUtamaWithMessage(new SekolahFragment(), intent.getStringExtra("id"), intent.getStringExtra("nama_sekolah"));
//            Toast.makeText(this, "Pesan sudah disampaikan = "+intent.getStringExtra("id"), Toast.LENGTH_SHORT).show();
        }

        btnNavView = findViewById(R.id.bn_main);
        btnNavView.setOnNavigationItemSelectedListener(this);

        tanyaPerizinan();

//        showBadge(this, btnNavView, R.id.berita_menu, "3");
//        showBadge(this, btnNavView, R.id.inbox_menu, "2");
    }

    private boolean loadFragmentUtama(Fragment fr){
        if (fr != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fr).commit();
            return true;
        }
        return false;
    }

    private boolean loadFragmentUtamaWithMessage(Fragment fr, String id_sekolah, String nama_sekolah){
        if (fr != null){
            Bundle arguments = new Bundle();
            arguments.putString("bandingkan_sebelum" , id_sekolah);
            arguments.putString("nama_bandingkan_sebelum" , nama_sekolah);
            fr.setArguments(arguments);
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
            case R.id.rekomendasi_menu:
                fragment = new RekomendasiFragment();
                break;
//            case R.id.inbox_menu:
//                fragment = new InboxFragment();
//                removeBadge(btnNavView, R.id.inbox_menu);
//                break;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void tanyaPerizinan(){
        for (int i = 0; i < 6; i++) {
            if (i==0 && ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
            if (i==1 && ContextCompat.checkSelfPermission(MainActivity.this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{INTERNET}, 1);
            }
            if (i==2 && ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 1);
            }
            if (i==3 && ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_COARSE_LOCATION}, 1);
            }
            if (i==4 && ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_NETWORK_STATE}, 1);
            }
            if (i==5 && ContextCompat.checkSelfPermission(MainActivity.this, SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{SEND_SMS}, 1);
            }
        }
    }
}

