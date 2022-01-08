package haris.org.futureschool;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import haris.org.futureschool.session.SessionManager;

import static haris.org.futureschool.session.SessionManager.KEY_EMAIL;
import static haris.org.futureschool.session.SessionManager.KEY_FULLNAME;

public class SettingActivity extends AppCompatActivity {
    TextView nama, koordinat, ubah_alamat, ubah_email, ubah_telepon, ambil_lokasi;
    EditText alamat, email, telepon;
    Spinner gender;
    Button simpan;
    private ProgressDialog pd;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sm = new SessionManager(SettingActivity.this);

        nama = (TextView) findViewById(R.id.txt_nama_siswa);
        alamat = (EditText) findViewById(R.id.txt_alamat_siswa);
        koordinat = (TextView) findViewById(R.id.txt_koordinat_siswa);
        gender = (Spinner) findViewById(R.id.txt_jenis_kelamin_siswa);
        ambil_lokasi = (TextView) findViewById(R.id.txt_ambil_lokasi);
        email = (EditText) findViewById(R.id.txt_email_siswa);
        telepon = (EditText) findViewById(R.id.txt_telepon_siswa);
        ubah_alamat = (TextView) findViewById(R.id.ubah_alamat_siswa);
        ubah_email = (TextView) findViewById(R.id.ubah_email_siswa);
        ubah_telepon = (TextView) findViewById(R.id.ubah_telepon_siswa);

        simpan = (Button) findViewById(R.id.btn_ubah);

        nama.setText(sm.getDetailLogin().get(KEY_FULLNAME).toString());
        email.setText(sm.getDetailLogin().get(KEY_EMAIL).toString());

        pd = new ProgressDialog(SettingActivity.this);
        pd.setMessage("Loading . . .");

        ubah_alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamat.setEnabled(true);
            }
        });

        String[] items = new String[]{"Pria", "Wanita"};
        final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, items);
        gender.setAdapter(adapter);
        gender.setSelection(1);

        ubah_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setEnabled(true);
            }
        });

        ubah_telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telepon.setEnabled(true);
            }
        });
    }

}
