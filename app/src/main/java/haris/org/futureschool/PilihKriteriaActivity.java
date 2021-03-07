package haris.org.futureschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class PilihKriteriaActivity extends AppCompatActivity {

    ArrayList<String> cb_fasilitas, cb_ekstrakulikuler;
    CheckBox lokasi, kualitas, prestasi, akreditasi, biaya, fasilitas, ekstrakulikuler;
    LinearLayout tampung_lokasi, tampung_kualitas,tampung_prestasi,tampung_akreditasi, tampung_biaya, tampung_fasilitas, fasilitas_checkbox, tampung_ekstrakulikuler, ekstrakulikuler_checkbox;
    SeekBar presentasi_lokasi, presentasi_kualitas, presentasi_prestasi, presentasi_akreditasi, presentasi_biaya, presentasi_fasilitas, presentasi_ekstrakulikuler;
    TextView nilai_lokasi, nilai_kualitas, nilai_prestasi, nilai_akreditasi, nilai_biaya, nilai_fasilitas, nilai_ekstrakulikuler;
    EditText input_biaya, input_daftar;
    Button sekolah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pilih_kriteria);

        lokasi = findViewById(R.id.cb_lokasi);
        kualitas = findViewById(R.id.cb_kualitas);
        prestasi = findViewById(R.id.cb_prestasi);
        akreditasi = findViewById(R.id.cb_akreditasi);
        biaya = findViewById(R.id.cb_biaya);
        fasilitas = findViewById(R.id.cb_fasilitas);
        ekstrakulikuler = findViewById(R.id.cb_ekstrakulikuler);

        tampung_lokasi = findViewById(R.id.ll_lokasi);
        tampung_kualitas = findViewById(R.id.ll_kualitas);
        tampung_prestasi = findViewById(R.id.ll_prestasi);
        tampung_akreditasi = findViewById(R.id.ll_akreditasi);
        tampung_biaya = findViewById(R.id.ll_biaya);
        tampung_fasilitas = findViewById(R.id.ll_fasilitas);
        fasilitas_checkbox = findViewById(R.id.ll_cb_fasilitas);
        tampung_ekstrakulikuler = findViewById(R.id.ll_ekstrakulikuler);
        ekstrakulikuler_checkbox = findViewById(R.id.ll_cb_ekstrakulikuler);

        presentasi_lokasi = findViewById(R.id.sb_lokasi);
        presentasi_kualitas = findViewById(R.id.sb_kualitas);
        presentasi_prestasi = findViewById(R.id.sb_prestasi);
        presentasi_akreditasi = findViewById(R.id.sb_akreditasi);
        presentasi_biaya = findViewById(R.id.sb_biaya);
        presentasi_fasilitas = findViewById(R.id.sb_fasilitas);
        presentasi_ekstrakulikuler = findViewById(R.id.sb_ekstrakulikuler);

        nilai_lokasi = findViewById(R.id.tv_lokasi);
        nilai_kualitas = findViewById(R.id.tv_kualitas);
        nilai_prestasi = findViewById(R.id.tv_prestasi);
        nilai_akreditasi = findViewById(R.id.tv_akreditasi);
        nilai_biaya = findViewById(R.id.tv_biaya);
        nilai_fasilitas = findViewById(R.id.tv_fasilitas);
        nilai_ekstrakulikuler = findViewById(R.id.tv_ekstrakulikuler);

        input_biaya = findViewById(R.id.et_biaya_spp);
        input_daftar = findViewById(R.id.et_biaya_masuk);

        sekolah = findViewById(R.id.btn_kriteria);

        cb_fasilitas = new ArrayList<>();
        cb_ekstrakulikuler = new ArrayList<>();



        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lokasi.isChecked()){
                    tampung_lokasi.setVisibility(View.VISIBLE);
                }
                else if (!lokasi.isChecked()){
                    tampung_lokasi.setVisibility(View.GONE);
                }
            }
        });

        kualitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kualitas.isChecked()){
                    tampung_kualitas.setVisibility(View.VISIBLE);
                }
                else if (!kualitas.isChecked()){
                    tampung_kualitas.setVisibility(View.GONE);
                }
            }
        });

        prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestasi.isChecked()){
                    tampung_prestasi.setVisibility(View.VISIBLE);
                }
                else if (!prestasi.isChecked()){
                    tampung_prestasi.setVisibility(View.GONE);
                }
            }
        });

        akreditasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (akreditasi.isChecked()){
                    tampung_akreditasi.setVisibility(View.VISIBLE);
                }
                else if (!akreditasi.isChecked()){
                    tampung_akreditasi.setVisibility(View.GONE);
                }
            }
        });

        biaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (biaya.isChecked()){
                    tampung_biaya.setVisibility(View.VISIBLE);
                }
                else if (!biaya.isChecked()){
                    tampung_biaya.setVisibility(View.GONE);
                }
            }
        });

        fasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fasilitas.isChecked()){
                    tampung_fasilitas.setVisibility(View.VISIBLE);
                }
                else if (!fasilitas.isChecked()){
                    tampung_fasilitas.setVisibility(View.GONE);
                }
            }
        });

        ekstrakulikuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ekstrakulikuler.isChecked()){
                    tampung_ekstrakulikuler.setVisibility(View.VISIBLE);
                }
                else if (!ekstrakulikuler.isChecked()){
                    tampung_ekstrakulikuler.setVisibility(View.GONE);
                }
            }
        });

        presentasi_lokasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_lokasi.setText(String.valueOf(presentasi_lokasi.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_lokasi.setText(String.valueOf(presentasi_lokasi.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_lokasi.setText(String.valueOf(presentasi_lokasi.getProgress()+1));
            }
        });

        presentasi_kualitas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_kualitas.setText(String.valueOf(presentasi_kualitas.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_kualitas.setText(String.valueOf(presentasi_kualitas.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_kualitas.setText(String.valueOf(presentasi_kualitas.getProgress()+1));
            }
        });

        presentasi_prestasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_prestasi.setText(String.valueOf(presentasi_prestasi.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_prestasi.setText(String.valueOf(presentasi_prestasi.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_prestasi.setText(String.valueOf(presentasi_prestasi.getProgress()+1));
            }
        });

        presentasi_akreditasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_akreditasi.setText(String.valueOf(presentasi_akreditasi.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_akreditasi.setText(String.valueOf(presentasi_akreditasi.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_akreditasi.setText(String.valueOf(presentasi_akreditasi.getProgress()+1));
            }
        });

        presentasi_biaya.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_biaya.setText(String.valueOf(presentasi_biaya.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_biaya.setText(String.valueOf(presentasi_biaya.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_biaya.setText(String.valueOf(presentasi_biaya.getProgress()+1));
            }
        });

        presentasi_fasilitas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_fasilitas.setText(String.valueOf(presentasi_fasilitas.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_fasilitas.setText(String.valueOf(presentasi_fasilitas.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_fasilitas.setText(String.valueOf(presentasi_fasilitas.getProgress()+1));
            }
        });

        presentasi_ekstrakulikuler.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilai_ekstrakulikuler.setText(String.valueOf(presentasi_ekstrakulikuler.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                nilai_ekstrakulikuler.setText(String.valueOf(presentasi_ekstrakulikuler.getProgress()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nilai_ekstrakulikuler.setText(String.valueOf(presentasi_ekstrakulikuler.getProgress()+1));
            }
        });

        input_biaya.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try
                {
                    input_biaya.removeTextChangedListener(this);
                    String value = input_biaya.getText().toString();


                    if (value != null && !value.equals(""))
                    {

                        if(value.startsWith(".")){
                            input_biaya.setText("0.");
                        }
                        if(value.startsWith("0") && !value.startsWith("0.")){
                            input_biaya.setText("");

                        }


                        String str = input_biaya.getText().toString().replaceAll(",", "");
                        if (!value.equals(""))
                            input_biaya.setText(getDecimalFormattedString(str));
                        input_biaya.setSelection(input_biaya.getText().toString().length());
                    }
                    input_biaya.addTextChangedListener(this);
                    return;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    input_biaya.addTextChangedListener(this);
                }
            }
        });

        input_daftar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try
                {
                    input_daftar.removeTextChangedListener(this);
                    String value = input_daftar.getText().toString();


                    if (value != null && !value.equals(""))
                    {

                        if(value.startsWith(".")){
                            input_daftar.setText("0.");
                        }
                        if(value.startsWith("0") && !value.startsWith("0.")){
                            input_daftar.setText("");

                        }


                        String str = input_daftar.getText().toString().replaceAll(",", "");
                        if (!value.equals(""))
                            input_daftar.setText(getDecimalFormattedString(str));
                        input_daftar.setSelection(input_daftar.getText().toString().length());
                    }
                    input_daftar.addTextChangedListener(this);
                    return;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    input_daftar.addTextChangedListener(this);
                }
            }
        });

        for(int i = 0; i < 5; i++) {
            final CheckBox gg = new CheckBox(getApplicationContext());
            gg.setText("Aku angka "+i);
            gg.setId(i);
            fasilitas_checkbox.addView(gg);

            gg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (gg.isChecked()){
                        cb_fasilitas.add(gg.getText().toString());
                    }
                    else if (!gg.isChecked()){
                        cb_fasilitas.remove(gg.getText().toString());
                    }
                }
            });
        }

        for(int i = 0; i < 5; i++) {
            final CheckBox gg = new CheckBox(getApplicationContext());
            gg.setText("Aku angka "+i);
            gg.setId(i);
            ekstrakulikuler_checkbox.addView(gg);

            gg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (gg.isChecked()){
                        cb_ekstrakulikuler.add(gg.getText().toString());
                    }
                    else if (!gg.isChecked()){
                        cb_ekstrakulikuler.remove(gg.getText().toString());
                    }
                }
            });
        }

        sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(PilihKriteriaActivity.this, SekolahRekomendasiActivity.class);
                startActivity(go);
            }
        });
    }

    public static String getDecimalFormattedString(String value)
    {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1)
        {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt( -1 + str1.length()) == '.')
        {
            j--;
            str3 = ".";
        }
        for (int k = j;; k--)
        {
            if (k < 0)
            {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3)
            {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }

    public static String trimCommaOfString(String string) {
//        String returnString;
        if(string.contains(",")){
            return string.replace(",","");}
        else {
            return string;
        }

    }
}
