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
    private ArrayList<String> checklist, nilaiPersentase;
    private List tampung_jurusan;
    private String id_jurusan = "", biaya;
    private double jumlah;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    ProgressDialog pd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sekolah_rekomendasi);

        pd = new ProgressDialog(SekolahRekomendasiActivity.this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        checklist = new ArrayList<>();
        nilaiPersentase = new ArrayList<>();
        tampung_jurusan = new LinkedList();

        if (getIntent().getStringArrayListExtra("isi_checklist") != null){
            checklist = getIntent().getStringArrayListExtra("isi_checklist");
        }
        if (getIntent().getStringArrayListExtra("persentase_checklist") != null){
            nilaiPersentase = getIntent().getStringArrayListExtra("persentase_checklist");
        }

        if (getIntent().getStringExtra("isi_biaya") != null){
            biaya = getIntent().getStringExtra("isi_biaya");
        }
        if (getIntent().getStringExtra("id") != null){
            id_jurusan = getIntent().getStringExtra("id");
        }

        Log.e("String", "Id = "+id_jurusan+" Biaya = "+biaya);

        jumlah = 0;
        for (int i = 0; i < checklist.size(); i++) {
            jumlah+=Double.parseDouble(nilaiPersentase.get(i));
        }

        for (int i = 0; i < checklist.size(); i++) {
            double bobot = Double.parseDouble(nilaiPersentase.get(i))/jumlah*100;
            Log.e("Checklist", "Checklist = "+checklist.get(i)+" Percent = "+nilaiPersentase.get(i)+" Bobot = "+df.format(bobot));
        }



        // Pecahkan daftar jurusan yang ada lalu cari sekolah yang mempunyai jurusan yang dimaksud
        String [] ambil_jurusan = id_jurusan.split(" ");
        for (int i = 0; i < ambil_jurusan.length; i++) {

        }

        for (int i = 0; i < tampung_jurusan.size(); i++) {
            Log.d("ISI TAMPUNG!!!!", "Isinya = "+tampung_jurusan.size());
        }



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
