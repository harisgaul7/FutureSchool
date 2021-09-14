package haris.org.futureschool;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import haris.org.futureschool.adapter.TabAdapter;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.fragment.SekolahFragment;
import haris.org.futureschool.fragment.Tab1Fragment;
import haris.org.futureschool.fragment.Tab2Fragment;
import haris.org.futureschool.fragment.TabBiaya;
import haris.org.futureschool.fragment.TabDaftarGuru;
import haris.org.futureschool.fragment.TabEkstrakurikuler;
import haris.org.futureschool.fragment.TabFasilitas;
import haris.org.futureschool.fragment.TabGaleri;
import haris.org.futureschool.fragment.TabJurusan;
import haris.org.futureschool.fragment.TabPrestasi;
import haris.org.futureschool.fragment.TabRiwayatSekolah;
import haris.org.futureschool.WrapContentHeightViewPager;
import haris.org.futureschool.library.DownloadImageTask;
import lecho.lib.hellocharts.model.Line;

public class SekolahActivity extends AppCompatActivity {

    // Modul slide image
    CarouselView carouselView;
    int slideImages[];

    // Modul perbandingan, kontak, daftar
    LinearLayout kontak, perbandingan, daftar;

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String data, slide, dataSlide[];
    HashMap<String, String> riwayat;

    TextView namaSekolah, alamatSekolah, akreditasiSekolah, likeSekolah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sekolah);

        data = getIntent().getStringExtra("id");

        kontak = (LinearLayout)findViewById(R.id.btn_kontak);
        perbandingan = (LinearLayout)findViewById(R.id.btn_bandingkan);

        kontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(SekolahActivity.this, KontakActivity.class);
                x.putExtra("id", data);
                startActivity(x);
            }
        });

        perbandingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(SekolahActivity.this, MainActivity.class);
                x.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(x);
            }
        });

        namaSekolah = findViewById(R.id.txt_detail_nama);
        alamatSekolah = findViewById(R.id.txt_detail_alamat);
        akreditasiSekolah = findViewById(R.id.txt_detail_akreditasi);
        likeSekolah = findViewById(R.id.txt_jumlah_like);

        namaSekolah.setText(getIntent().getStringExtra("nama"));
        alamatSekolah.setText(getIntent().getStringExtra("alamat"));
        akreditasiSekolah.setText("Akreditasi "+getIntent().getStringExtra("akreditasi"));
        likeSekolah.setText("288");

        riwayat = new HashMap<>();
        riwayat.put("deskripsi", getIntent().getStringExtra("deskripsi"));
        riwayat.put("visi_misi", getIntent().getStringExtra("visi_misi"));
        riwayat.put("kurikulum", getIntent().getStringExtra("kurikulum"));

        slide = getIntent().getStringExtra("slide");

        slide = slide.replace("[", "");
        slide = slide.replace("]", "");
        slide = slide.replace(" ", "");
        slide = slide.replace("\"", "");

        dataSlide = slide.split(",");

        String url = new BaseUrl().GAMBAR_URL;
        for (int i = 0; i < dataSlide.length; i++) {
            dataSlide[i] = url+dataSlide[i];
        }

        modulSlideGambar(dataSlide);

        viewPager = findViewById(R.id.view_pager_sekolah);
        tabLayout = findViewById(R.id.tab_layout_sekolah);

        tabAdapter = new TabAdapter(getSupportFragmentManager());

        Bundle argsBundle = new Bundle();
        argsBundle.putString("id",data);

        tabAdapter.addFragmentBundle(new TabRiwayatSekolah(), "Riwayat Sekolah", argsBundle);
        tabAdapter.addFragmentBundle(new TabJurusan(), "Jurusan", argsBundle);
        tabAdapter.addFragmentBundle(new TabFasilitas(), "Fasilitas", argsBundle);
        tabAdapter.addFragmentBundle(new TabEkstrakurikuler(), "Ekstrakurikuler", argsBundle);
        tabAdapter.addFragmentBundle(new TabPrestasi(), "Prestasi", argsBundle);
        tabAdapter.addFragmentBundle(new TabDaftarGuru(), "Daftar Guru", argsBundle);
        tabAdapter.addFragmentBundle(new TabBiaya(), "Biaya", argsBundle);

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public HashMap<String, String> sendRiwayat() {
        return this.riwayat;
    }

    private void modulSlideGambar(final String slideImages[]){
//        int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};image_5
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(sampleImages[position]); ->> cara kalau pakai array drawable
                Picasso.get().load(slideImages[position]).into(imageView);
            }
        };
        carouselView = findViewById(R.id.carousel_view);
        carouselView.setPageCount(slideImages.length);
        carouselView.setImageListener(imageListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
