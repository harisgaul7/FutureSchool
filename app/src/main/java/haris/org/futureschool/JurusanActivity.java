package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.JurusanRekomendasiModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JurusanActivity extends AppCompatActivity {

    ArrayList<String> jurusan = new ArrayList<>();
    ArrayList<Integer> idJurusan = new ArrayList<>();
    List<JurusanRekomendasiModel> dataJurusan = new ArrayList<>();
    ArrayList<String> cekbox;
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jurusan);

        pd = new ProgressDialog(JurusanActivity.this);
        pd.setMessage("Loading . . .\nMohon menunggu dengan sabar");
        pd.show();

        if (getIntent().getStringExtra("topik_id") != null){
            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataJurusanRekomendasi(Integer.parseInt(getIntent().getStringExtra("topik_id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
                        dataJurusan = response.body().getHasilJurusan();
                        if (!response.body().getKode().equals("0")){
                            for (int i =0; i<dataJurusan.size(); i++){
                                jurusan.add(response.body().getHasilJurusan().get(i).getNama_jurusan());
                                idJurusan.add(response.body().getHasilJurusan().get(i).getId_master_jurusan());
                            }
                            final LinearLayout tampung = findViewById(R.id.ll_tampung_jurusan);
                            cekbox = new ArrayList<>();

                            for(int i = 0; i < jurusan.size(); i++) {
                                final CheckBox gg = new CheckBox(getApplicationContext());
                                gg.setText(jurusan.get(i));
                                gg.setId(i);
                                tampung.addView(gg);

                                final Integer id = idJurusan.get(i);

                                gg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (gg.isChecked()){
                                            addCheckbox(String.valueOf(id));
                                        }
                                        else if (!gg.isChecked()){
                                            removeCheckbox(String.valueOf(id));
                                        }
                                    }
                                });
                            }
                        }
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

        Button hasil = findViewById(R.id.btn_penentuan);
        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kumpulan_id = "";

                for (int i = 0; i < getCheckbox().size(); i++) {
                    kumpulan_id+=getCheckbox().get(i)+" ";
                }

                Intent go = new Intent(view.getContext(), PilihKriteriaActivity.class);
                go.putExtra("id", kumpulan_id);
                view.getContext().startActivity(go);

            }
        });
    }

    private ArrayList<String> addCheckbox(String data){
        if (!this.cekbox.contains(data)){
            this.cekbox.add(data);
        }
        return this.cekbox;
    }

    private ArrayList<String> removeCheckbox(String data){
        if (this.cekbox.contains(data)){
            this.cekbox.remove(data);
        }
        return this.cekbox;
    }

    private ArrayList<String>  getCheckbox (){
        return this.cekbox;
    }
}
