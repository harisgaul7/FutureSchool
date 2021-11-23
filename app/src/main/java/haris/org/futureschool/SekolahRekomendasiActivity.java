package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.adapter.RekomendasiAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.RekomendasiModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SekolahRekomendasiActivity extends AppCompatActivity {

    private RecyclerView rv_rekomendasi;
    private RekomendasiAdapter rekAd;
    private ArrayList<RekomendasiModel> rekomendasiModels;
    String peringkat, nilai;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sekolah_rekomendasi);

        if (getIntent().getStringExtra("peringkat") != null){
            peringkat = getIntent().getStringExtra("peringkat");
        }
        if (getIntent().getStringExtra("nilai") != null){
            nilai = getIntent().getStringExtra("nilai");
        }

        rv_rekomendasi = findViewById(R.id.rv_rekomendasi);
        rekomendasiModels = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_rekomendasi.setLayoutManager(layoutManager);


        rekomendasiModels.add(new RekomendasiModel(R.drawable.twitter, R.drawable.rank1, "SMKF Ikasari Pekanbaru", "Skor 9,5"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_rekomendasi.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.twitter, R.drawable.rank2, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_rekomendasi.setAdapter(rekAd);
    }
}
