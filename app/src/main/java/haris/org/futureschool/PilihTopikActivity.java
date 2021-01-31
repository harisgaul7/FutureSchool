package haris.org.futureschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PilihTopikActivity extends AppCompatActivity {

    ArrayList<String> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pilih_topik);

        String topik[] = {"Akuntansi", "Tata Boga", "Perbankan", "Penerbangan", "Robotika", "Tahfidz", "Taruna", "Olahraga", "Perpajakan", "Keuangan", "Elektronika", "Pertanian", "Perkebunan", "Kesehatan", "Farmasi", "Mesin", "Perhotelan", "Manajemen", "Otomotif", "Kimia"};
        data = new ArrayList<>();
        Arrays.sort(topik);
        Collections.addAll(data, topik);

        ListView tampung = (ListView)findViewById(R.id.lv_topik);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.style_lv_topik, data);

        tampung.setAdapter(adapter);

        tampung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent x = new Intent(view.getContext(), JurusanActivity.class);
                view.getContext().startActivity(x);
            }
        });
    }
}
