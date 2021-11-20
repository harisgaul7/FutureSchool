package haris.org.futureschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.adapter.JurusanAdapter;
import haris.org.futureschool.adapter.PerbandinganAdapter;
import haris.org.futureschool.adapter.TabAdapter;
import haris.org.futureschool.fragment.TabBiaya;
import haris.org.futureschool.fragment.TabBiayaDouble;
import haris.org.futureschool.fragment.TabDaftarGuru;
import haris.org.futureschool.fragment.TabDaftarGuruDouble;
import haris.org.futureschool.fragment.TabEkstrakurikuler;
import haris.org.futureschool.fragment.TabEkstrakurikulerDouble;
import haris.org.futureschool.fragment.TabFasilitas;
import haris.org.futureschool.fragment.TabFasilitasDouble;
import haris.org.futureschool.fragment.TabJurusan;
import haris.org.futureschool.fragment.TabJurusanDouble;
import haris.org.futureschool.fragment.TabPrestasi;
import haris.org.futureschool.fragment.TabPrestasiDouble;
import haris.org.futureschool.fragment.TabRiwayatSekolah;
import haris.org.futureschool.model.ExpandableModel;
import haris.org.futureschool.model.InboxModel;

public class PerbandinganActivity extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_perbandingan);

        Intent intent = getIntent();

        viewPager = findViewById(R.id.view_pager_sekolah);
        tabLayout = findViewById(R.id.tab_layout_sekolah);
        tabAdapter = new TabAdapter(getSupportFragmentManager());

        Bundle argsBundle = new Bundle();
        argsBundle.putString("id",intent.getStringExtra("sekolah_awal")+"="+intent.getStringExtra("sekolah_akhir"));
        argsBundle.putString("nama",intent.getStringExtra("nama_sekolah_awal")+"="+intent.getStringExtra("nama_sekolah_akhir"));

        tabAdapter.addFragmentBundle(new TabJurusanDouble(), "Jurusan", argsBundle);
        tabAdapter.addFragmentBundle(new TabFasilitasDouble(), "Fasilitas", argsBundle);
        tabAdapter.addFragmentBundle(new TabEkstrakurikulerDouble(), "Ekstrakurikuler", argsBundle);
        tabAdapter.addFragmentBundle(new TabPrestasiDouble(), "Prestasi", argsBundle);
        tabAdapter.addFragmentBundle(new TabDaftarGuruDouble(), "Daftar Guru", argsBundle);
        tabAdapter.addFragmentBundle(new TabBiayaDouble(), "Biaya", argsBundle);

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
