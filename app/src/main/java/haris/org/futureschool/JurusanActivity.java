package haris.org.futureschool;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class JurusanActivity extends AppCompatActivity {

    ArrayList<String> cekbox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jurusan);

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
                Toast.makeText(JurusanActivity.this, "Hasil = "+isi, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
