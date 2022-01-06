package haris.org.futureschool;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import haris.org.futureschool.session.SessionManager;

import static haris.org.futureschool.session.SessionManager.KEY_EMAIL;
import static haris.org.futureschool.session.SessionManager.KEY_FULLNAME;

public class SettingActivity extends AppCompatActivity {
    TextView nama, alamat, koordinat, gender, email, telepon, ubah_alamat, ubah_gender, ubah_email, ubah_telepon;
    Button simpan;
    private ProgressDialog pd;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sm = new SessionManager(SettingActivity.this);

        nama = (TextView) findViewById(R.id.txt_nama_siswa);
        alamat = (TextView) findViewById(R.id.txt_alamat_siswa);
        koordinat = (TextView) findViewById(R.id.txt_koordinat_siswa);
        gender = (TextView) findViewById(R.id.txt_jenis_kelamin_siswa);
        email = (TextView) findViewById(R.id.txt_email_siswa);
        telepon = (TextView) findViewById(R.id.txt_telepon_siswa);
        ubah_alamat = (TextView) findViewById(R.id.ubah_alamat_siswa);
        ubah_gender = (TextView) findViewById(R.id.ubah_jenis_kelamin_siswa);
        ubah_email = (TextView) findViewById(R.id.ubah_email_siswa);
        ubah_telepon = (TextView) findViewById(R.id.ubah_telepon_siswa);

        simpan = (Button)findViewById(R.id.btn_ubah);

        nama.setText(sm.getDetailLogin().get(KEY_FULLNAME).toString());
        email.setText(sm.getDetailLogin().get(KEY_EMAIL).toString());

        pd = new ProgressDialog(SettingActivity.this);
        pd.setMessage("Loading . . .");


    }
}
