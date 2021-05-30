package haris.org.futureschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class TestActivity extends AppCompatActivity {

    Button filter;
    HashMap<String, String> riwayat;
    String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tes);

        onBackPressed();

//        Toast.makeText(this, String.valueOf(new SekolahFragment().boleh), Toast.LENGTH_SHORT).show();

        filter = (Button)findViewById(R.id.btn_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new SekolahFragment().boleh = true;
//                riwayat = new HashMap<>();
//                riwayat.put("deskripsi", getIntent().getStringExtra("deskripsi"));
//                riwayat.put("visi_misi", getIntent().getStringExtra("visi_misi"));
//                riwayat.put("kurikulum", getIntent().getStringExtra("kurikulum"));
//                onBackPressed();
                Intent i = new Intent(TestActivity.this, FilterActivity.class);
                startActivityForResult(i, 1);
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("isi");
                TextView ubah = findViewById(R.id.txt_isi);
                ubah.setText(strEditText);
                Toast.makeText(this, strEditText, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        data = getIntent().getStringExtra("id");

        Intent intent = new Intent();
        intent.putExtra("isi", data);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    public HashMap<String, String> sendRiwayat() {
        Toast.makeText(this, "Ada datanya!", Toast.LENGTH_SHORT).show();
        return this.riwayat;
    }
}
