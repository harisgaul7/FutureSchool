package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.LoginModel;
import haris.org.futureschool.model.ResponseModel;
import haris.org.futureschool.session.AuthModel;
import haris.org.futureschool.session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText user, pass;
    TextView daftar;
    Button login;
    private ProgressDialog pd;
    private SessionManager sm;
    private static final String TAG = LoginActivity.class.getSimpleName();
    List<LoginModel> dataLogin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.txt_username);
        pass = (EditText) findViewById(R.id.txt_password);
        login = (Button) findViewById(R.id.btnLogin);
        daftar = (TextView) findViewById(R.id.txt_daftar_akun);

        pd = new ProgressDialog(LoginActivity.this);
        sm = new SessionManager(LoginActivity.this);
        pd.setMessage("Loading . . .");

        final ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();

                if (android.util.Patterns.EMAIL_ADDRESS.matcher(user.getText().toString()).matches()){
                    pd.dismiss();

                    ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                    Call<ResponseModel> send = api.getLoginData(user.getText().toString(), pass.getText().toString());
                    send.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            dataLogin = response.body().getHasilLogin();
                            if (!response.body().getKode().equals("0")){
                                if (response.body().getKode().equals("1")){
                                    sm.storeLogin(dataLogin.get(0).getId(), dataLogin.get(0).getEmail(), dataLogin.get(0).getNama_depan()+" "+dataLogin.get(0).getNama_belakang());
                                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(in);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "username/password salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {

                        }
                    });

                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(i);
                }
                else {
                    pd.dismiss();
                    Toast.makeText(LoginActivity.this, "Email anda tidak valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DaftarActivity.class);
                view.getContext().startActivity(i);
            }
        });
    }
}
