package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.adapter.BiayaAdapter;
import haris.org.futureschool.adapter.EkstrakurikulerAdapter;
import haris.org.futureschool.adapter.FasilitasAdapter;
import haris.org.futureschool.adapter.SekolahAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.BiayaModel;
import haris.org.futureschool.model.EkstrakurikulerModel;
import haris.org.futureschool.model.FasilitasModel;
import haris.org.futureschool.model.ResponseModel;
import haris.org.futureschool.model.TampilanSekolahModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihKriteriaActivity extends AppCompatActivity {

    ArrayList<String> cb_fasilitas, cb_ekstrakurikuler, checklist;
    CheckBox lokasi, kualitas, prestasi, akreditasi, biaya, fasilitas, ekstrakurikuler;
    LinearLayout tampung_lokasi, tampung_kualitas,tampung_prestasi,tampung_akreditasi, tampung_biaya, tampung_fasilitas, fasilitas_checkbox, tampung_ekstrakurikuler, ekstrakurikuler_checkbox;
    SeekBar presentasi_lokasi, presentasi_kualitas, presentasi_prestasi, presentasi_akreditasi, presentasi_biaya, presentasi_fasilitas, presentasi_ekstrakurikuler;
    TextView nilai_lokasi, nilai_kualitas, nilai_prestasi, nilai_akreditasi, nilai_biaya, nilai_fasilitas, nilai_ekstrakurikuler;
    EditText input_biaya, input_daftar;
    Button sekolah;
    ProgressDialog pd;
    private ArrayList<String> isi_sekolah, isi_accesory, lokasi_sekolah, kualitas_sekolah, prestasi_sekolah, akreditasi_sekolah, biaya_sekolah, fasilitas_sekolah, ekstrakurikuler_sekolah, bobot;
    String id_sekolah;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    double jumlah;
    String [][] rumus;
    String data_kirim[];

    String peringkat_sekolah, nilai_sekolah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pilih_kriteria);

        isi_sekolah = new ArrayList<>();
        isi_accesory = new ArrayList<>();
        this.id_sekolah = "";

        pd = new ProgressDialog(PilihKriteriaActivity.this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        lokasi = findViewById(R.id.cb_lokasi);
        kualitas = findViewById(R.id.cb_kualitas);
        prestasi = findViewById(R.id.cb_prestasi);
        akreditasi = findViewById(R.id.cb_akreditasi);
        biaya = findViewById(R.id.cb_biaya);
        fasilitas = findViewById(R.id.cb_fasilitas);
        ekstrakurikuler = findViewById(R.id.cb_ekstrakurikuler);

        tampung_lokasi = findViewById(R.id.ll_lokasi);
        tampung_kualitas = findViewById(R.id.ll_kualitas);
        tampung_prestasi = findViewById(R.id.ll_prestasi);
        tampung_akreditasi = findViewById(R.id.ll_akreditasi);
        tampung_biaya = findViewById(R.id.ll_biaya);
        tampung_fasilitas = findViewById(R.id.ll_fasilitas);
//        fasilitas_checkbox = findViewById(R.id.ll_cb_fasilitas);
        tampung_ekstrakurikuler = findViewById(R.id.ll_ekstrakurikuler);
//        ekstrakurikuler_checkbox = findViewById(R.id.ll_cb_ekstrakurikuler);

        presentasi_lokasi = findViewById(R.id.sb_lokasi);
        presentasi_kualitas = findViewById(R.id.sb_kualitas);
        presentasi_prestasi = findViewById(R.id.sb_prestasi);
        presentasi_akreditasi = findViewById(R.id.sb_akreditasi);
        presentasi_biaya = findViewById(R.id.sb_biaya);
        presentasi_fasilitas = findViewById(R.id.sb_fasilitas);
        presentasi_ekstrakurikuler = findViewById(R.id.sb_ekstrakurikuler);

        nilai_lokasi = findViewById(R.id.tv_lokasi);
        nilai_kualitas = findViewById(R.id.tv_kualitas);
        nilai_prestasi = findViewById(R.id.tv_prestasi);
        nilai_akreditasi = findViewById(R.id.tv_akreditasi);
        nilai_biaya = findViewById(R.id.tv_biaya);
        nilai_fasilitas = findViewById(R.id.tv_fasilitas);
        nilai_ekstrakurikuler = findViewById(R.id.tv_ekstrakurikuler);

        input_biaya = findViewById(R.id.et_biaya_spp);
        input_daftar = findViewById(R.id.et_biaya_masuk);

        sekolah = findViewById(R.id.btn_kriteria);

        cb_fasilitas = new ArrayList<>();
        cb_ekstrakurikuler = new ArrayList<>();

        // Untuk menampung checklist yang dipilih pengguna
        checklist = new ArrayList<>();

        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lokasi.isChecked()){
                    tampung_lokasi.setVisibility(View.VISIBLE);
                    checklist.add("lokasi");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    lokasi_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getAkreditasiSekolah(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                pd.dismiss();
                                String daftar_lokasi = "";
                                try {
                                    if (!response.body().getKode().equals("0")){
                                        for (int i =0; i<response.body().getHasilAkreditasiSekolah().size(); i++){
                                            String bersihkan = response.body().getHasilAkreditasiSekolah().get(i).getKoordinat_sekolah().replace(" ", "");
                                            String lokasi[] = bersihkan.split(",");
                                            // Kode untuk mengukur 2 jarak
                                            Location loc1 = new Location("");
                                            loc1.setLatitude(Double.parseDouble(lokasi[0]));
                                            loc1.setLongitude(Double.parseDouble(lokasi[1]));

                                            Location loc2 = new Location("");
                                            loc2.setLatitude(0.47240675672083277);
                                            loc2.setLongitude(101.35311354129196);

                                            // Lokasi UIN SUSKA = 0.47240675672083277, 101.35311354129196

                                            float jarak = loc1.distanceTo(loc2)/1000.0f;

                                            if (jarak < 5){
                                                daftar_lokasi += "50";
                                            }
                                            else if (jarak >= 5 && jarak < 15){
                                                daftar_lokasi += "30";
                                            }
                                            else if (jarak > 15){
                                                daftar_lokasi += "20";
                                            }
                                        }
                                        // Cocokkan rata-rata kualitas guru dengan id sekolah yang dimaksud
                                        daftar_lokasi+=" "+getData().get(finalI);
                                        addLokasi(daftar_lokasi);
                                    }
                                } catch (Exception t){
                                    Log.d("Error Lokasi = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!lokasi.isChecked()){
                    tampung_lokasi.setVisibility(View.GONE);
                    checklist.remove("lokasi");
                }
            }
        });

        kualitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kualitas.isChecked()){
                    tampung_kualitas.setVisibility(View.VISIBLE);
                    checklist.add("kualitas");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    kualitas_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getDataGuru(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.dismiss();
                                    String daftar_kualitas = "";
                                    if (!response.body().getKode().equals("0")){
                                        double nilai = 0;
                                        for (int i = 0; i < response.body().getHasilGuru().size(); i++) {
                                            if (response.body().getHasilGuru().get(i).getPendidikan_guru().equals("D3")){
                                                nilai+=0.8;
                                            }
                                            else if (response.body().getHasilGuru().get(i).getPendidikan_guru().equals("S1")){
                                                nilai+=1;
                                            }
                                            else if (response.body().getHasilGuru().get(i).getPendidikan_guru().equals("S2")){
                                                nilai+=2;
                                            }
                                            else if (response.body().getHasilGuru().get(i).getPendidikan_guru().equals("S3")){
                                                nilai+=3;
                                            }
                                        }
                                        // Tambahkan hasil rata rata penilaian kualitas guru
                                        if ((nilai/response.body().getHasilGuru().size()) < 1.1){
                                            daftar_kualitas+="10";
                                        }
                                        else if ((nilai/response.body().getHasilGuru().size()) >= 1.1 && (nilai/response.body().getHasilGuru().size()) < 1.6){
                                            daftar_kualitas+="20";
                                        }
                                        else if ((nilai/response.body().getHasilGuru().size()) >= 1.6 && (nilai/response.body().getHasilGuru().size()) < 2.1){
                                            daftar_kualitas+="30";
                                        }
                                        else if ((nilai/response.body().getHasilGuru().size()) >= 2.1 && (nilai/response.body().getHasilGuru().size()) < 2.7){
                                            daftar_kualitas+="40";
                                        }
                                        else if ((nilai/response.body().getHasilGuru().size()) >= 2.7){
                                            daftar_kualitas+="50";
                                        }
                                        // Cocokkan rata-rata kualitas guru dengan id sekolah yang dimaksud
                                        daftar_kualitas+=" "+getData().get(finalI);
                                        addKualitas(daftar_kualitas);
                                    }
                                } catch (Exception e){
                                    Log.d("Error getDataGuru = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!kualitas.isChecked()){
                    tampung_kualitas.setVisibility(View.GONE);
                    checklist.remove("kualitas");
                    removeKualitas();
                }
            }
        });

        prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestasi.isChecked()){
                    tampung_prestasi.setVisibility(View.VISIBLE);
                    checklist.add("prestasi");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    prestasi_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getDataPrestasi(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.dismiss();
                                    String daftar_prestasi = "";
                                    if (!response.body().getKode().equals("0")){
                                        double nilai = 0;
                                        for (int i = 0; i < response.body().getHasilPrestasi().size(); i++) {
                                            String peringkat = response.body().getHasilPrestasi().get(i).getPeringkat_prestasi();
                                            String tingkat = response.body().getHasilPrestasi().get(i).getTingkat_prestasi();
                                            int nilai_peringkat = 0;
                                            int nilai_tingkat = 0;

                                            if (peringkat.equals("1")){
                                                nilai_peringkat = 100;
                                            }
                                            else if (peringkat.equals("2")){
                                                nilai_peringkat = 80;
                                            }
                                            else if (peringkat.equals("3")){
                                                nilai_peringkat = 50;
                                            }

                                            switch (tingkat){
                                                case "Internasional" : nilai_tingkat = 50;
                                                break;
                                                case "Nasional" : nilai_tingkat = 40;
                                                break;
                                                case "Provinsi" : nilai_tingkat = 30;
                                                break;
                                                case "Kabupaten" : nilai_tingkat = 20;
                                                break;
                                                case "Kecamatan" : nilai_tingkat = 10;
                                                break;
                                            }

                                            nilai += (nilai_peringkat*nilai_tingkat);
                                        }
                                        // Tambahkan hasil rata rata prestasi sekolah
                                        if ((nilai/response.body().getHasilPrestasi().size()) < 1500){
                                            daftar_prestasi+="10";
                                        }
                                        else if ((nilai/response.body().getHasilPrestasi().size()) >= 1500 && (nilai/response.body().getHasilPrestasi().size()) < 1700){
                                            daftar_prestasi+="20";
                                        }
                                        else if ((nilai/response.body().getHasilPrestasi().size()) >= 1700 && (nilai/response.body().getHasilPrestasi().size()) < 1900){
                                            daftar_prestasi+="30";
                                        }
                                        else if ((nilai/response.body().getHasilPrestasi().size()) >= 1900 && (nilai/response.body().getHasilPrestasi().size()) < 2100){
                                            daftar_prestasi+="40";
                                        }
                                        else if ((nilai/response.body().getHasilPrestasi().size()) >= 2100){
                                            daftar_prestasi+="50";
                                        }
                                        // Cocokkan rata-rata kualitas guru dengan id sekolah yang dimaksud
                                        daftar_prestasi+=" "+getData().get(finalI);
                                        addPrestasi(daftar_prestasi);
                                    }
                                } catch (Exception e){
                                    Log.d("Error  Get Prestasi = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!prestasi.isChecked()){
                    tampung_prestasi.setVisibility(View.GONE);
                    checklist.remove("prestasi");
                    removePrestasi();
                }
            }
        });

        akreditasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (akreditasi.isChecked()){
                    tampung_akreditasi.setVisibility(View.VISIBLE);
                    checklist.add("akreditasi");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    akreditasi_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getAkreditasiSekolah(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                pd.dismiss();
                                String daftar_akreditasi = "";
                                try {
                                    if (!response.body().getKode().equals("0")){
                                        for (int i =0; i<response.body().getHasilAkreditasiSekolah().size(); i++){
                                            String akreditasi = response.body().getHasilAkreditasiSekolah().get(i).getAkreditasi_sekolah();
                                            if (akreditasi.equals("A")){
                                                daftar_akreditasi += "50";
                                            }
                                            else if (akreditasi.equals("B")){
                                                daftar_akreditasi += "30";
                                            }
                                            else if (akreditasi.equals("C")){
                                                daftar_akreditasi += "20";
                                            }
                                            else if (akreditasi.equals("TT")){
                                                daftar_akreditasi += "10";
                                            }
                                        }
                                        // Cocokkan rata-rata kualitas guru dengan id sekolah yang dimaksud
                                        daftar_akreditasi+=" "+getData().get(finalI);
                                        addAkreditasi(daftar_akreditasi);
                                    }
                                } catch (Exception t){
                                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!akreditasi.isChecked()){
                    tampung_akreditasi.setVisibility(View.GONE);
                    checklist.remove("akreditasi");
                    removeAkreditasi();
                }
            }
        });

        biaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (biaya.isChecked()){
                    tampung_biaya.setVisibility(View.VISIBLE);
                    checklist.add("biaya");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    biaya_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getDataBiaya(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.dismiss();
                                    String daftar_biaya = "";
                                    int total1 = 0;
                                    int total2 = 0;
                                    for (int i = 0; i < response.body().getHasilBiaya().size(); i++) {
                                        if (response.body().getHasilBiaya().get(i).getJenis_biaya().equals("bulanan")){
                                            total1 += Integer.parseInt(response.body().getHasilBiaya().get(i).getJumlah_biaya());
                                        }
                                        else if (response.body().getHasilBiaya().get(i).getJenis_biaya().equals("awal")){
                                            total2 += Integer.parseInt(response.body().getHasilBiaya().get(i).getJumlah_biaya());
                                        }
                                    }
                                    // Cocokkan rata-rata kualitas guru dengan id sekolah yang dimaksud
                                    daftar_biaya += total1 +" "+ total2 +" "+getData().get(finalI);
                                    addBiaya(daftar_biaya);
                                } catch (Exception e){
                                    Log.d("Error Biaya = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!biaya.isChecked()){
                    tampung_biaya.setVisibility(View.GONE);
                    checklist.remove("biaya");
                    removeBiaya();
                }
            }
        });

        fasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fasilitas.isChecked()){
                    tampung_fasilitas.setVisibility(View.VISIBLE);
                    checklist.add("fasilitas");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    fasilitas_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getDataFasilitas(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                pd.dismiss();
                                try {
                                    String daftar_fasilitas = "";
                                    if (!response.body().getKode().equals("0")){
                                        // Cocokkan jumlah fasilitas dengan id sekolah yang dimaksud
                                        int jumlah_fasilitas = response.body().getHasilFasilitas().size();
                                        if (jumlah_fasilitas < 10){
                                            daftar_fasilitas += "10";
                                        }
                                        else if (jumlah_fasilitas >= 10 && jumlah_fasilitas < 15){
                                            daftar_fasilitas += "20";
                                        }
                                        else if (jumlah_fasilitas >= 15 && jumlah_fasilitas < 20){
                                            daftar_fasilitas += "30";
                                        }
                                        else if (jumlah_fasilitas >= 20 && jumlah_fasilitas < 25){
                                            daftar_fasilitas += "40";
                                        }
                                        else if (jumlah_fasilitas >= 25){
                                            daftar_fasilitas += "50";
                                        }
                                        daftar_fasilitas += " " + getData().get(finalI);
                                        addFasilitas(daftar_fasilitas);
                                    }
                                } catch (Exception e){
                                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!fasilitas.isChecked()){
                    tampung_fasilitas.setVisibility(View.GONE);
                    checklist.remove("fasilitas");
                    removeFasilitas();
                }
            }
        });

        ekstrakurikuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ekstrakurikuler.isChecked()){
                    tampung_ekstrakurikuler.setVisibility(View.VISIBLE);
                    checklist.add("ekstrakurikuler");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    ekstrakurikuler_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getDataEkstrakurikuler(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                pd.dismiss();
                                try {
                                    String daftar_ekstrakurikuler = "";
                                    if (!response.body().getKode().equals("0")){
                                        // Cocokkan jumlah fasilitas dengan id sekolah yang dimaksud
                                        int jumlah_ekstrakurikuler = response.body().getHasilEkstrakurikuler().size();
                                        if (jumlah_ekstrakurikuler < 3){
                                            daftar_ekstrakurikuler += "20";
                                        }
                                        else if (jumlah_ekstrakurikuler >= 3 && jumlah_ekstrakurikuler < 10){
                                            daftar_ekstrakurikuler += "30";
                                        }
                                        else if (jumlah_ekstrakurikuler >= 10){
                                            daftar_ekstrakurikuler += "50";
                                        }
                                        daftar_ekstrakurikuler += " " + getData().get(finalI);
                                        addEkstrakurikuler(daftar_ekstrakurikuler);
                                    }
                                } catch (Exception e){
                                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!ekstrakurikuler.isChecked()){
                    tampung_ekstrakurikuler.setVisibility(View.GONE);
                    checklist.remove("ekstrakurikuler");
                    removeEkstrakurikuler();
                }
            }
        });

        presentasi_lokasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_lokasi.setText(String.valueOf(presentasi_lokasi.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_lokasi.setText(String.valueOf(presentasi_lokasi.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_lokasi.setText(String.valueOf(presentasi_lokasi.getProgress()+1));
            }
        });

        presentasi_kualitas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_kualitas.setText(String.valueOf(presentasi_kualitas.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_kualitas.setText(String.valueOf(presentasi_kualitas.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_kualitas.setText(String.valueOf(presentasi_kualitas.getProgress()+1));
            }
        });

        presentasi_prestasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_prestasi.setText(String.valueOf(presentasi_prestasi.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_prestasi.setText(String.valueOf(presentasi_prestasi.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_prestasi.setText(String.valueOf(presentasi_prestasi.getProgress()+1));
            }
        });

        presentasi_akreditasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_akreditasi.setText(String.valueOf(presentasi_akreditasi.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_akreditasi.setText(String.valueOf(presentasi_akreditasi.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_akreditasi.setText(String.valueOf(presentasi_akreditasi.getProgress()+1));
            }
        });

        presentasi_biaya.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_biaya.setText(String.valueOf(presentasi_biaya.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_biaya.setText(String.valueOf(presentasi_biaya.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_biaya.setText(String.valueOf(presentasi_biaya.getProgress()+1));
            }
        });

        presentasi_fasilitas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_fasilitas.setText(String.valueOf(presentasi_fasilitas.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_fasilitas.setText(String.valueOf(presentasi_fasilitas.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_fasilitas.setText(String.valueOf(presentasi_fasilitas.getProgress()+1));
            }
        });

        presentasi_ekstrakurikuler.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_ekstrakurikuler.setText(String.valueOf(presentasi_ekstrakurikuler.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_ekstrakurikuler.setText(String.valueOf(presentasi_ekstrakurikuler.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_ekstrakurikuler.setText(String.valueOf(presentasi_ekstrakurikuler.getProgress()+1));
            }
        });

        input_biaya.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try
                {
                    input_biaya.removeTextChangedListener(this);
                    String value = input_biaya.getText().toString();


                    if (value != null && !value.equals(""))
                    {

                        if(value.startsWith(".")){
                            input_biaya.setText("0.");
                        }
                        if(value.startsWith("0") && !value.startsWith("0.")){
                            input_biaya.setText("");

                        }


                        String str = input_biaya.getText().toString().replaceAll(",", "");
                        if (!value.equals(""))
                            input_biaya.setText(getDecimalFormattedString(str));
                        input_biaya.setSelection(input_biaya.getText().toString().length());
                    }
                    input_biaya.addTextChangedListener(this);
                    return;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    input_biaya.addTextChangedListener(this);
                }
            }
        });

        input_daftar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try
                {
                    input_daftar.removeTextChangedListener(this);
                    String value = input_daftar.getText().toString();


                    if (value != null && !value.equals(""))
                    {

                        if(value.startsWith(".")){
                            input_daftar.setText("0.");
                        }
                        if(value.startsWith("0") && !value.startsWith("0.")){
                            input_daftar.setText("");

                        }


                        String str = input_daftar.getText().toString().replaceAll(",", "");
                        if (!value.equals(""))
                            input_daftar.setText(getDecimalFormattedString(str));
                        input_daftar.setSelection(input_daftar.getText().toString().length());
                    }
                    input_daftar.addTextChangedListener(this);
                    return;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    input_daftar.addTextChangedListener(this);
                }
            }
        });

//        for(int i = 0; i < 5; i++) {
//            final CheckBox gg = new CheckBox(getApplicationContext());
//            gg.setText("Aku angka "+i);
//            gg.setId(i);
//            fasilitas_checkbox.addView(gg);
//
//            gg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (gg.isChecked()){
//                        cb_fasilitas.add(gg.getText().toString());
//                    }
//                    else if (!gg.isChecked()){
//                        cb_fasilitas.remove(gg.getText().toString());
//                    }
//                }
//            });
//        }
//
//        for(int i = 0; i < 5; i++) {
//            final CheckBox gg = new CheckBox(getApplicationContext());
//            gg.setText("Aku angka "+i);
//            gg.setId(i);
//            ekstrakurikuler_checkbox.addView(gg);
//
//            gg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (gg.isChecked()){
//                        cb_ekstrakurikuler.add(gg.getText().toString());
//                    }
//                    else if (!gg.isChecked()){
//                        cb_ekstrakurikuler.remove(gg.getText().toString());
//                    }
//                }
//            });
//        }

        // Mencari ID sekolah yang terkait dengan Jurusan yang dipilih oleh calon siswa
        if (getIntent().getStringExtra("id") != null){
            String [] tampung_id = getIntent().getStringExtra("id").split(" ");

            for (int i = 0; i < tampung_id.length; i++) {
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<ResponseModel> send = api.getSekolahDariJurusan(tampung_id[i]);
                send.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        try {
                            if (!response.body().getKode().equals("0")){
                                for (int i =0; i<response.body().getHasilIdSekolah().size(); i++){
                                    addData(response.body().getHasilIdSekolah().get(i).getId_sekolah());
                                    addAccesory(response.body().getHasilIdSekolah().get(i).getId_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getNama_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getGambar_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getAlamat_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getAkreditasi_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getKurikulum_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getVisi_misi_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getDeskripsi_sekolah()+"="+response.body().getHasilIdSekolah().get(i).getSlide_sekolah());
                                }
                            }
                        } catch (Exception t){
                            Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Terjadi Kesalahan")
                                .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                .show();

                        Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                    }
                });
            }
        }

        // Ambil data gambar dan

        sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Mengatur nilai fuzzy dari biaya
                if (checkBiaya(checklist).length() != 0){
                    String biaya = checkBiaya(checklist).replace(",", "");
                    String rincian[] = biaya.split(" ");
                    for (int i = 0; i < getData().size(); i++) {
                        String data_biaya[] = getBiaya().get(i).split(" ");
                        double bulanan = Double.parseDouble(rincian[0]);
                        double daftar = Double.parseDouble(rincian[1]);

                        double persen_bulanan = bulanan/Double.parseDouble(data_biaya[0])*100;
                        double persen_daftar = daftar/Double.parseDouble(data_biaya[1])*100;

//                        Log.d("Rincian Biaya", "Bulanan = "+persen_bulanan+" Daftar = "+persen_daftar+" keduanya = "+((persen_bulanan+persen_daftar)/2));

                        if (((persen_bulanan+persen_daftar)/2) < 100){
                            getBiaya().set(i, "50 "+data_biaya[2]);
                        }
                        else if (((persen_bulanan+persen_daftar)/2) < 110 && ((persen_bulanan+persen_daftar)/2) >=100){
                            getBiaya().set(i, "40 "+data_biaya[2]);
                        }
                        else if (((persen_bulanan+persen_daftar)/2) < 120 && ((persen_bulanan+persen_daftar)/2) >=110){
                            getBiaya().set(i, "30 "+data_biaya[2]);
                        }
                        else if (((persen_bulanan+persen_daftar)/2) < 130 && ((persen_bulanan+persen_daftar)/2) >=120){
                            getBiaya().set(i, "20 "+data_biaya[2]);
                        }
                        else if (((persen_bulanan+persen_daftar)/2) >=130){
                            getBiaya().set(i, "10 "+data_biaya[2]);
                        }
                    }
                }

                jumlah = 0;
                for (int i = 0; i < checklist.size(); i++) {
                    jumlah+=Double.parseDouble(nilaiPersentase(checklist).get(i));
                }

                // Memasukkan bobot atribut kedalam arraylist
                bobot = new ArrayList<>();
                for (int i = 0; i < checklist.size(); i++) {
                    double persentase = Double.parseDouble(nilaiPersentase(checklist).get(i))/jumlah*100;
                    bobot.add(String.valueOf(persentase));
//                    Log.d("Checklist", "Kriteria = "+checklist.get(i)+" Bobot = "+df.format(bobot));
                }

                // Step 2 Moora, memasukkan semua nilai setiap atribut sesuai dengan klasifikasi fuzzynya
                rumus = new String [getData().size()][checklist.size()];
                for (int i = 0; i < getData().size(); i++) {
                    for (int j = 0; j < checklist.size(); j++) {
                        if (checklist.get(j).equals("lokasi")){
                            for (int k = 0; k < getLokasi().size(); k++) {
                                String ambil[] = getLokasi().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
//                                    Log.d("Jumpa Lokasi", ambil[1]+" hasil = "+ambil[0]);
                                }
                            }
                        }
                        if (checklist.get(j).equals("kualitas")){
                            for (int k = 0; k < getKualitas().size(); k++) {
                                String ambil[] = getKualitas().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
                                }
                            }
                        }
                        if (checklist.get(j).equals("prestasi")){
                            for (int k = 0; k < getPrestasi().size(); k++) {
                                String ambil[] = getPrestasi().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
                                }
                            }
                        }
                        if (checklist.get(j).equals("akreditasi")){
                            for (int k = 0; k < getAkreditasi().size(); k++) {
                                String ambil[] = getAkreditasi().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
                                }
                            }
                        }
                        if (checklist.get(j).equals("biaya")){
                            for (int k = 0; k < getBiaya().size(); k++) {
                                String ambil[] = getBiaya().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
                                }
                            }
                        }
                        if (checklist.get(j).equals("fasilitas")){
                            for (int k = 0; k < getFasilitas().size(); k++) {
                                String ambil[] = getFasilitas().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
                                }
                            }
                        }
                        if (checklist.get(j).equals("ekstrakurikuler")){
                            for (int k = 0; k < getEkstrakurikuler().size(); k++) {
                                String ambil[] = getEkstrakurikuler().get(k).split(" ");
                                if (ambil[1].equals(getData().get(i))){
                                    rumus[i][j] = ambil[0];
                                }
                            }
                        }
                    }
                }

                // Hanya untuk keperluan visualisasi data berbentuk tabel
//                String data;
//                for (int i = 0; i < rumus.length; i++) {
//                    data = "";
//                    for (int j = 0; j < rumus[i].length; j++) {
//                        data += rumus[i][j] + " ";
//                    }
//                    Log.d("Hasil", "Sekolah = "+getData().get(i)+" "+data);
//                }

                // Step ketiga dan keempat, mendapatkan nilai X1 masing2 elemen dan mengalikannya dengan bobot
                int jumlah = 0;
                double pembagi = 0, step_akhir = 0;
                String [] kumpul = new String[checklist.size()];
                // Array penampung langkah ketiga, sisakan satu space untuk menyimpan id sekolah
                double [][] step_tiga = new double[getData().size()][checklist.size()+1];
                for (int i = 0; i < checklist.size(); i++) {
                    kumpul[i] = "";
                    jumlah = 0;
                    for (int j = 0; j < getData().size(); j++) {
                        jumlah += (Integer.parseInt(rumus[j][i])*Integer.parseInt(rumus[j][i]));
                    }
                    for (int j = 0; j < getData().size(); j++) {
                        // Step ketiga
                        pembagi = Double.parseDouble(rumus[j][i])/Math.sqrt(jumlah);

                        // Step keempat
                        double step_empat = (pembagi*Double.parseDouble(bobot.get(i)))/100;

                        // Masukkan ke array penampung
                        step_tiga[j][i] = step_empat;

                        // Masukkan id sekolah di ujung
                        step_tiga[j][checklist.size()] = Double.parseDouble(getData().get(j));

//                        Log.d("Detail", "Sekolah = "+getData().get(j)+" Bagian = "+i+" step tiga = "+pembagi+" bobot = "+bobot.get(i)+" hasil = "+step_empat);
                    }
                }

                // Step terakhir moora, mendapatkan peringkat sekolah dan mengirimkannya ke activity selanjutnya
                double semuanya = 0;
                peringkat_sekolah = "";
                double []nilai = new double[step_tiga.length];
                int[]id_sekolah = new int[step_tiga.length];
                for (int i = 0; i < step_tiga.length; i++) {
                    semuanya = 0;
                    for (int j = 0; j < step_tiga[i].length-1; j++) {
                        semuanya += step_tiga[i][j];
                    }
                    peringkat_sekolah += df.format(semuanya*10)+"="+(int)(step_tiga[i][step_tiga[i].length-1])+" ";
                    nilai[i] = semuanya;
                    id_sekolah[i] = (int)(step_tiga[i][step_tiga[i].length-1]);
//                    Log.d("Peringkat", "Sekolah = "+step_tiga[i][step_tiga[i].length-1]+" Total = "+semuanya);
                }

                String [] detail = peringkat_sekolah.split(" ");

                data_kirim = new String[detail.length];
                for (int i = 0; i < detail.length; i++) {
                    String pecahkan[] = detail[i].split("=");
                    for (int j = 0; j < getAccesory().size(); j++) {
                        String pecah[] = getAccesory().get(j).split("=");
                        if (pecahkan[1].equals(pecah[0])){
                            data_kirim[i] = pecah[1]+"="+pecah[0]+"="+pecahkan[0]+"="+pecah[2]+"="+pecah[3]+"="+pecah[4]+"="+pecah[5]+"="+pecah[6]+"="+pecah[7]+"="+pecah[8];
                        }
                    }
                }

                Intent go = new Intent(PilihKriteriaActivity.this, SekolahRekomendasiActivity.class);
                // Kirim hasil peringkat ke activity berikutnya
                go.putExtra("data", data_kirim);
                startActivity(go);
            }
        });
    }

    private ArrayList<String> nilaiPersentase (ArrayList<String> daftar_checklist){
        ArrayList<String> nilai = new ArrayList<>();

        for (int i = 0; i < daftar_checklist.size(); i++) {
            if (daftar_checklist.get(i).equals("lokasi")){
                nilai.add(nilai_lokasi.getText().toString());
            }
            if (daftar_checklist.get(i).equals("kualitas")){
                nilai.add(nilai_kualitas.getText().toString());
            }
            if (daftar_checklist.get(i).equals("prestasi")){
                nilai.add(nilai_prestasi.getText().toString());
            }
            if (daftar_checklist.get(i).equals("akreditasi")){
                nilai.add(nilai_akreditasi.getText().toString());
            }
            if (daftar_checklist.get(i).equals("biaya")){
                nilai.add(nilai_biaya.getText().toString());
            }
            if (daftar_checklist.get(i).equals("fasilitas")){
                nilai.add(nilai_fasilitas.getText().toString());
            }
            if (daftar_checklist.get(i).equals("ekstrakurikuler")){
                nilai.add(nilai_ekstrakurikuler.getText().toString());
            }
        }
        return nilai;
    }

    private String checkBiaya (ArrayList<String> daftar_checklist){
        String total_biaya = "";

        for (int i = 0; i < daftar_checklist.size(); i++) {
            if (daftar_checklist.get(i).equals("biaya")){
                total_biaya = input_biaya.getText().toString() + " " + input_daftar.getText().toString();
            }

        }
        return total_biaya;
    }

    public static String getDecimalFormattedString(String value)
    {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1)
        {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt( -1 + str1.length()) == '.')
        {
            j--;
            str3 = ".";
        }
        for (int k = j;; k--)
        {
            if (k < 0)
            {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3)
            {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }

    public static String trimCommaOfString(String string) {
//        String returnString;
        if(string.contains(",")){
            return string.replace(",","");}
        else {
            return string;
        }

    }

    private ArrayList<String> addData(String data){
        if (!this.isi_sekolah.contains(data)){
            this.isi_sekolah.add(data);
        }
        return this.isi_sekolah;
    }

    private ArrayList<String>  getData (){
        return this.isi_sekolah;
    }

    private ArrayList<String> addAccesory(String data){
        if (!this.isi_accesory.contains(data)){
            this.isi_accesory.add(data);
        }
        return this.isi_accesory;
    }

    private ArrayList<String>  getAccesory (){
        return this.isi_accesory;
    }

    private ArrayList<String> addLokasi(String data){
        this.lokasi_sekolah.add(data);

        return this.lokasi_sekolah;
    }

    private void removeLokasi(){
        getLokasi().clear();
    }

    private ArrayList<String>  getLokasi (){
        return this.lokasi_sekolah;
    }

    private ArrayList<String> addKualitas(String data){
        this.kualitas_sekolah.add(data);

        return this.kualitas_sekolah;
    }

    private void removeKualitas(){
        getKualitas().clear();
    }

    private ArrayList<String>  getKualitas (){
        return this.kualitas_sekolah;
    }

    private ArrayList<String> addPrestasi(String data){
        this.prestasi_sekolah.add(data);

        return this.prestasi_sekolah;
    }

    private void removePrestasi(){
        getPrestasi().clear();
    }

    private ArrayList<String>  getPrestasi (){
        return this.prestasi_sekolah;
    }


    private ArrayList<String> addAkreditasi(String data){
        this.akreditasi_sekolah.add(data);

        return this.akreditasi_sekolah;
    }

    private void removeAkreditasi(){
        getAkreditasi().clear();
    }

    private ArrayList<String>  getAkreditasi (){
        return this.akreditasi_sekolah;
    }

    private ArrayList<String> addBiaya(String data){
        this.biaya_sekolah.add(data);

        return this.biaya_sekolah;
    }

    private void removeBiaya(){
        getBiaya().clear();
    }

    private ArrayList<String>  getBiaya (){
        return this.biaya_sekolah;
    }

    private ArrayList<String> addFasilitas(String data){
        this.fasilitas_sekolah.add(data);

        return this.fasilitas_sekolah;
    }

    private void removeFasilitas(){
        getFasilitas().clear();
    }

    private ArrayList<String>  getFasilitas (){
        return this.fasilitas_sekolah;
    }

    private ArrayList<String> addEkstrakurikuler(String data){
        this.ekstrakurikuler_sekolah.add(data);

        return this.ekstrakurikuler_sekolah;
    }

    private void removeEkstrakurikuler(){
        getEkstrakurikuler().clear();
    }

    private ArrayList<String>  getEkstrakurikuler (){
        return this.ekstrakurikuler_sekolah;
    }

}
