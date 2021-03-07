package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JurusanActivity extends AppCompatActivity {

    ArrayList<String> cekbox;
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jurusan);

        pd = new ProgressDialog(JurusanActivity.this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        if (getIntent().getStringExtra("topik_id") != null){
            ApiRequest api = Retroserver.getClient("http://192.168.43.41/coba_fs/").create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataJurusanRekomendasi(Integer.parseInt(getIntent().getStringExtra("topik_id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
//                        dataTopik = response.body().getHasilTopik();
//                        if (!response.body().getKode().equals("0")){
//                            for (int i =0; i<dataTopik.size(); i++){
//                                data.add(response.body().getHasilTopik().get(i).getTopik_nama());
//                                idTopik.add(response.body().getHasilTopik().get(i).getTopik_id());
//                            }
//                        }
                    } catch (Exception t){
                        Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    new SweetAlertDialog(JurusanActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Terjadi Kesalahan")
                            .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                            .show();

                    Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                }
            });
        }

        final LinearLayout tampung = findViewById(R.id.ll_tampung_jurusan);
        cekbox = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            final CheckBox gg = new CheckBox(getApplicationContext());
            gg.setText("Aku angka "+i);
            gg.setId(i);
            tampung.addView(gg);

            gg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (gg.isChecked()){
                        cekbox.add(gg.getText().toString());
                    }
                    else if (!gg.isChecked()){
                        cekbox.remove(gg.getText().toString());
                    }
                }
            });
        }

        Button hasil = findViewById(R.id.btn_penentuan);
        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isi = "";
                for (int i = 0; i < cekbox.size(); i++) {
                    isi+=cekbox.get(i);
                }
//                Toast.makeText(JurusanActivity.this, "Hasil = "+isi, Toast.LENGTH_SHORT).show();
                Intent go = new Intent(view.getContext(), PilihKriteriaActivity.class);
                view.getContext().startActivity(go);

            }
        });

    }
}
