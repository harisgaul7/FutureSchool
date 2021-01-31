package haris.org.futureschool;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class JurusanActivity extends AppCompatActivity {

    String isi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jurusan);

        LinearLayout tampung = findViewById(R.id.ll_tampung_jurusan);
        isi = "";
        for(int i = 0; i < 5; i++) {
            final CheckBox ch = new CheckBox(getApplicationContext());
            ch.setText("Aku angka "+i);
            ch.setId(i);
            tampung.addView(ch);

            ch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ch.isChecked()){
                        isi+=ch.getText();
                    }
                }
            });
        }

        Button hasil = findViewById(R.id.btn_penentuan);
        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JurusanActivity.this, "Hasil = "+isi, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
