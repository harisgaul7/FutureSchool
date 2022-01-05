package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.session.SessionManager;

public class DaftarActivity extends AppCompatActivity {
    EditText user, pass;
    TextView daftar;
    Button login;
    private ProgressDialog pd;
    private SessionManager sm;
    private static final String TAG = DaftarActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.txt_username);
        pass = (EditText) findViewById(R.id.txt_password);
        login = (Button) findViewById(R.id.btnLogin);
        daftar = (TextView) findViewById(R.id.txt_daftar_akun);

        pd = new ProgressDialog(DaftarActivity.this);
        sm = new SessionManager(DaftarActivity.this);
        pd.setMessage("Loading . . .");

        final ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(i);

//                pd.show();
//                Call<AuthModel> login = api.getDataLogin(user.getText().toString(), pass.getText().toString());
//                login.enqueue(new Callback<AuthModel>() {
//                    @Override
//                    public void onResponse(Call<AuthModel> call, Response<AuthModel> response) {
//                        pd.dismiss();
//                        Log.d(TAG, "Kode : "+response.body().getKode());
//                        Log.d(TAG, "" +
//                                "Pesan : "+response.body().getKode());
//
//                        AuthModel res = response.body();
//                        AuthData user =res.getResult();
//                        if (res.getKode().equals("1")){
//                            sm.storeLogin(user.getEmail(), user.getFullname());
//                            Intent in = new Intent(Login.this, Dashboard.class);
//                            in.putExtra("email", user.getEmail());
//                            in.putExtra("fullname", user.getFullname());
//                            startActivity(in);
//                        }
//                        else {
//                            Toast.makeText(Login.this, "username/password salah", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AuthModel> call, Throwable t) {
//                        pd.dismiss();
//                        Log.e(TAG, "onFailure: "+t.toString() );
//                    }
//                });
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
