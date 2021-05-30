package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.adapter.KontakAdapter;
import haris.org.futureschool.adapter.PerbandinganAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.InboxModel;
import haris.org.futureschool.model.KontakModel;
import haris.org.futureschool.model.PasangKontakModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;

public class KontakActivity extends AppCompatActivity {

    private ImageView kembali;
    private RecyclerView rv_kontak;
    private ProgressDialog pd;
    private List<KontakModel> dataKontak = new ArrayList<>();
    private ArrayList<PasangKontakModel> pasangKontakModels;
    private KontakAdapter kontakAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kontak);

        kembali = (ImageView) findViewById(R.id.btn_back);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pd = new ProgressDialog(this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        rv_kontak = (RecyclerView)findViewById(R.id.rv_kontak);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_kontak.setLayoutManager(layoutManager);

        pasangKontakModels = new ArrayList<>();

        String id = getIntent().getStringExtra("id");


        if (getIntent().getStringExtra("id") != null){
            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataKontak(Integer.parseInt(getIntent().getStringExtra("id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
                        dataKontak = response.body().getHasilKontak();

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.telephone, "Telepon", dataKontak.get(0).getTelepon_sekolah(), "telepon"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.world_wide_web, "Website", dataKontak.get(0).getWebsite_sekolah(), "website"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.gmail, "Email", dataKontak.get(0).getEmail_sekolah(), "email"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.facebook, "Facebook", dataKontak.get(0).getFacebook_sekolah(), "facebook"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.whatsapp, "Whatsapp", dataKontak.get(0).getWhatsapp_sekolah(), "whatsapp"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.telegram, "Telegram", dataKontak.get(0).getTelegram_sekolah(), "telegram"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.instagram, "Instagram", dataKontak.get(0).getInstagram_sekolah(), "instagram"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.twitter, "Twitter", dataKontak.get(0).getTwitter_sekolah(), "twitter"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                        pasangKontakModels.add(new PasangKontakModel(R.drawable.youtube, "Youtube", dataKontak.get(0).getYoutube_sekolah(), "youtube"));
                        kontakAdapter = new KontakAdapter(pasangKontakModels);
                        rv_kontak.setAdapter(kontakAdapter);

                    } catch (Exception e){
                        Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    new SweetAlertDialog(KontakActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Terjadi Kesalahan")
                            .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                            .show();

                    Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                }
            });
        }
    }
}
