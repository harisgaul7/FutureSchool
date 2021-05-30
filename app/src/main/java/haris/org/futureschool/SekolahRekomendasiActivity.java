package haris.org.futureschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import java.util.ArrayList;

import haris.org.futureschool.adapter.RekomendasiAdapter;
import haris.org.futureschool.model.RekomendasiModel;

public class SekolahRekomendasiActivity extends AppCompatActivity {

    private RecyclerView rv_rekomendasi;
    private RekomendasiAdapter rekAd;
    private ArrayList<RekomendasiModel> rekomendasiModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sekolah_rekomendasi);

        rv_rekomendasi = findViewById(R.id.rv_rekomendasi);
        rekomendasiModels = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_rekomendasi.setLayoutManager(layoutManager);

//        rekomendasiModels.add(new RekomendasiModel(R.drawable.smkf_ikasari, R.drawable.rank1, "SMKF Ikasari Pekanbaru", "Skor 9,5"));
//        rekAd = new RekomendasiAdapter(rekomendasiModels);
//
//        rv_rekomendasi.setAdapter(rekAd);
//
//        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank2, "SD Al Ulum Pekanbaru", "Skor 9,3"));
//        rekAd = new RekomendasiAdapter(rekomendasiModels);
//
//        rv_rekomendasi.setAdapter(rekAd);
    }
}
