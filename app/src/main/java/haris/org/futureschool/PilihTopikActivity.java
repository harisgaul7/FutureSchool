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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.ResponseModel;
import haris.org.futureschool.model.TopikRekomendasiModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihTopikActivity extends AppCompatActivity {

    ArrayList<String> data = new ArrayList<>();
    ArrayList<Integer> idTopik = new ArrayList<>();
    List<TopikRekomendasiModel> dataTopik = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pilih_topik);

        pd = new ProgressDialog(PilihTopikActivity.this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponseModel> send = api.getDataTopikRekomendasi();
        send.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.dismiss();
                try {
                    dataTopik = response.body().getHasilTopik();
                    if (!response.body().getKode().equals("0")){
                        for (int i =0; i<dataTopik.size(); i++){
                            data.add(response.body().getHasilTopik().get(i).getNama_topik());
                            idTopik.add(response.body().getHasilTopik().get(i).getId_master_topik());
                        }
                        final ListView tampung = (ListView)findViewById(R.id.lv_topik);
                        ArrayAdapter adapter = new ArrayAdapter(PilihTopikActivity.this, R.layout.style_lv_topik, data);

                        tampung.setAdapter(adapter);

                        tampung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent x = new Intent(view.getContext(), JurusanActivity.class);
                                x.putExtra("topik_id", idTopik.get(i).toString());
                                view.getContext().startActivity(x);
                            }
                        });
                    }
                } catch (Exception t){
                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                new SweetAlertDialog(PilihTopikActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Terjadi Kesalahan")
                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                        .show();

                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
            }
        });
    }
}
