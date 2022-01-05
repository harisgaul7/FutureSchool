package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    EditText depan, belakang, email, pass;
    TextView login;
    Button daftar;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        depan = (EditText) findViewById(R.id.txt_nama_depan);
        belakang = (EditText) findViewById(R.id.txt_nama_belakang);
        email = (EditText) findViewById(R.id.txt_username);
        pass = (EditText) findViewById(R.id.txt_password);
        login = (TextView) findViewById(R.id.txt_login);
        daftar = (Button) findViewById(R.id.btn_daftar);

        pd = new ProgressDialog(SettingActivity.this);
        pd.setMessage("Loading . . .");

        final ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(i);
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<ResponseModel> send = api.addAccount(depan.getText().toString(), belakang.getText().toString(), email.getText().toString(), pass.getText().toString());
                send.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.dismiss();
                        try {
                            if (response.body().getKode().equals("1")){
                                Intent i = new Intent(SettingActivity.this, LoginActivity.class);
                                startActivity(i);

                                new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Berhasil!")
                                        .setContentText("Pendaftaran sukses, silakan login!")
                                        .show();
                            }
                            else if (response.body().getKode().equals("2")){
                                new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Kesalahan")
                                        .setContentText("Terjadi kesalahan saat pendaftaran, mohon cek inputan anda!")
                                        .show();
                            }
                        } catch (Exception t){
                            new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Kesalahan")
                                    .setContentText("Email yang anda daftarkan sudah pernah digunakan, silakan gunakan email lain!")
                                    .show();

                            Log.e("Error Daftar", String.valueOf(t));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Terjadi Kesalahan")
                                .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                .show();

                        Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                    }
                });
            }
        });
    }
}
