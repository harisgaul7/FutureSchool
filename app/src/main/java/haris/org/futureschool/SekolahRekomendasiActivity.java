package haris.org.futureschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.adapter.RekomendasiAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.BaseUrl;
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
    String data[];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sekolah_rekomendasi);

        if (getIntent().getStringArrayExtra("data") != null){
            data = getIntent().getStringArrayExtra("data");
        }

        int [] bilangan = new int[data.length];
        int [] id_sekolah = new int[data.length];
        String [] aksesoris_sekolah = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            String [] detail = data[i].split("=");
            String ubah = detail[2].replace(",",".");
            int nilai_asli = (int)(Double.parseDouble(ubah)*100);

            bilangan[i] = nilai_asli;
            id_sekolah[i] = Integer.parseInt(detail[1]);

            aksesoris_sekolah[i] = detail[1]+"="+detail[0]+"="+detail[3]+"="+detail[4]+"="+detail[5]+"="+detail[6]+"="+detail[7]+"="+detail[8]+"="+detail[9];
        }

        // Urutkan pakai BubbleSort
        int tampung = 0;
        int id = 0;
        String accesory = "";
        for (int i = 0; i < bilangan.length; i++) {
            for (int j = 0; j < bilangan.length-i-1; j++) {
                if (bilangan[j] < bilangan[j+1]){
                    tampung = bilangan[j];
                    bilangan[j] = bilangan[j+1];
                    bilangan[j+1]=tampung;

                    id = id_sekolah[j];
                    id_sekolah[j] = id_sekolah[j+1];
                    id_sekolah[j+1] = id;

                    accesory = aksesoris_sekolah[j];
                    aksesoris_sekolah[j] = aksesoris_sekolah[j+1];
                    aksesoris_sekolah[j+1] = accesory;
                }
            }
        }

        // Tampilkan
//        for (int i = 0; i < bilangan.length; i++) {
//            Log.d("Setelah diurutkan", "Nilai = "+bilangan[i]+" ID = "+ id_sekolah[i]+" Aksesoris = "+aksesoris_sekolah[i]);
//        }

        String url = new BaseUrl().GAMBAR_URL;

        rv_rekomendasi = findViewById(R.id.rv_rekomendasi);
        rekomendasiModels = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_rekomendasi.setLayoutManager(layoutManager);

        int rank;
        for (int i = 0; i < bilangan.length; i++) {
            rank = 0;
            if (i+1 == 1){
                rank = R.drawable.rank1;
            }
            else if (i+1 == 2){
                rank = R.drawable.rank2;
            }
            else if (i+1 == 3){
                rank = R.drawable.rank3;
            }
            else if (i+1 == 4){
                rank = R.drawable.rank4;
            }
            else if (i+1 == 5){
                rank = R.drawable.rank5;
            }
            else if (i+1 == 6){
                rank = R.drawable.rank6;
            }
            else if (i+1 == 7){
                rank = R.drawable.rank7;
            }
            else if (i+1 == 8){
                rank = R.drawable.rank8;
            }
            else if (i+1 == 9){
                rank = R.drawable.rank9;
            }
            else if (i+1 == 10){
                rank = R.drawable.rank10;
            }

            String pecahan_aksesoris[] = aksesoris_sekolah[i].split("=");

            rekomendasiModels.add(new RekomendasiModel(rank, pecahan_aksesoris[0],url+pecahan_aksesoris[2], pecahan_aksesoris[1], "Skor "+bilangan[i], pecahan_aksesoris[3], pecahan_aksesoris[7], pecahan_aksesoris[4], pecahan_aksesoris[6], pecahan_aksesoris[5], pecahan_aksesoris[8]));
            rekAd = new RekomendasiAdapter(rekomendasiModels);

            rv_rekomendasi.setAdapter(rekAd);
        }
    }
}
