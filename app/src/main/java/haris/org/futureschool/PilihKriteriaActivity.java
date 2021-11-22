package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihKriteriaActivity extends AppCompatActivity {

    ArrayList<String> cb_fasilitas, cb_ekstrakulikuler, checklist;
    CheckBox lokasi, kualitas, prestasi, akreditasi, biaya, fasilitas, ekstrakulikuler;
    LinearLayout tampung_lokasi, tampung_kualitas,tampung_prestasi,tampung_akreditasi, tampung_biaya, tampung_fasilitas, fasilitas_checkbox, tampung_ekstrakulikuler, ekstrakulikuler_checkbox;
    SeekBar presentasi_lokasi, presentasi_kualitas, presentasi_prestasi, presentasi_akreditasi, presentasi_biaya, presentasi_fasilitas, presentasi_ekstrakulikuler;
    TextView nilai_lokasi, nilai_kualitas, nilai_prestasi, nilai_akreditasi, nilai_biaya, nilai_fasilitas, nilai_ekstrakulikuler;
    EditText input_biaya, input_daftar;
    Button sekolah;
    ProgressDialog pd;
    private ArrayList<String> isi_sekolah, kualitas_sekolah;
    String id_sekolah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pilih_kriteria);

        isi_sekolah = new ArrayList<>();
        this.id_sekolah = "";

        pd = new ProgressDialog(PilihKriteriaActivity.this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

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
//        fasilitas_checkbox = findViewById(R.id.ll_cb_fasilitas);
        tampung_ekstrakulikuler = findViewById(R.id.ll_ekstrakulikuler);
//        ekstrakulikuler_checkbox = findViewById(R.id.ll_cb_ekstrakulikuler);

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

        // Untuk menampung checklist yang dipilih pengguna
        checklist = new ArrayList<>();

        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lokasi.isChecked()){
                    tampung_lokasi.setVisibility(View.VISIBLE);
                    checklist.add("lokasi");
                }
                else if (!lokasi.isChecked()){
                    tampung_lokasi.setVisibility(View.GONE);
                    checklist.remove("lokasi");
                }
            }
        });

        kualitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kualitas.isChecked()){
                    tampung_kualitas.setVisibility(View.VISIBLE);
                    checklist.add("kualitas");

                    // Kalau mau ambil data dari dalam onResponse, arraylist WAJIB dideklarasikan ulang
                    kualitas_sekolah = new ArrayList<>();
                    for (int i = 0; i < getData().size(); i++) {
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        Call<ResponseModel> send = api.getDataGuru(Integer.parseInt(getData().get(i)));
                        final int finalI = i;
                        send.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.dismiss();
                                    String daftar_kualitas = "";
                                    if (!response.body().getKode().equals("0")){
                                        for (int i = 0; i < response.body().getHasilGuru().size(); i++) {
                                            daftar_kualitas+=response.body().getHasilGuru().get(i).getPendidikan_guru()+" ";
                                        }
                                        Log.d("ID Sekolah", "ID = "+getData().get(finalI));
                                        addKualitas(Integer.parseInt(getData().get(finalI)), daftar_kualitas);
                                    }
                                } catch (Exception e){
                                    Log.d("Error getDataGuru = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Terjadi Kesalahan")
                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                        .show();

                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                            }
                        });
                    }
                }
                else if (!kualitas.isChecked()){
                    tampung_kualitas.setVisibility(View.GONE);
                    checklist.remove("kualitas");
                    removeKualitas();
                }
            }
        });

        prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestasi.isChecked()){
                    tampung_prestasi.setVisibility(View.VISIBLE);
                    checklist.add("prestasi");
                }
                else if (!prestasi.isChecked()){
                    tampung_prestasi.setVisibility(View.GONE);
                    checklist.remove("prestasi");
                }
            }
        });

        akreditasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (akreditasi.isChecked()){
                    tampung_akreditasi.setVisibility(View.VISIBLE);
                    checklist.add("akreditasi");
                }
                else if (!akreditasi.isChecked()){
                    tampung_akreditasi.setVisibility(View.GONE);
                    checklist.remove("akreditasi");
                }
            }
        });

        biaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (biaya.isChecked()){
                    tampung_biaya.setVisibility(View.VISIBLE);
                    checklist.add("biaya");
                }
                else if (!biaya.isChecked()){
                    tampung_biaya.setVisibility(View.GONE);
                    checklist.remove("biaya");
                }
            }
        });

        fasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fasilitas.isChecked()){
                    tampung_fasilitas.setVisibility(View.VISIBLE);
                    checklist.add("fasilitas");
                }
                else if (!fasilitas.isChecked()){
                    tampung_fasilitas.setVisibility(View.GONE);
                    checklist.remove("fasilitas");
                }
            }
        });

        ekstrakulikuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ekstrakulikuler.isChecked()){
                    tampung_ekstrakulikuler.setVisibility(View.VISIBLE);
                    checklist.add("ekstrakulikuler");
                }
                else if (!ekstrakulikuler.isChecked()){
                    tampung_ekstrakulikuler.setVisibility(View.GONE);
                    checklist.remove("ekstrakulikuler");
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

//        for(int i = 0; i < 5; i++) {
//            final CheckBox gg = new CheckBox(getApplicationContext());
//            gg.setText("Aku angka "+i);
//            gg.setId(i);
//            fasilitas_checkbox.addView(gg);
//
//            gg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (gg.isChecked()){
//                        cb_fasilitas.add(gg.getText().toString());
//                    }
//                    else if (!gg.isChecked()){
//                        cb_fasilitas.remove(gg.getText().toString());
//                    }
//                }
//            });
//        }
//
//        for(int i = 0; i < 5; i++) {
//            final CheckBox gg = new CheckBox(getApplicationContext());
//            gg.setText("Aku angka "+i);
//            gg.setId(i);
//            ekstrakulikuler_checkbox.addView(gg);
//
//            gg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (gg.isChecked()){
//                        cb_ekstrakulikuler.add(gg.getText().toString());
//                    }
//                    else if (!gg.isChecked()){
//                        cb_ekstrakulikuler.remove(gg.getText().toString());
//                    }
//                }
//            });
//        }

        if (getIntent().getStringExtra("id") != null){
            String [] tampung_id = getIntent().getStringExtra("id").split(" ");

            for (int i = 0; i < tampung_id.length; i++) {
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<ResponseModel> send = api.getSekolahDariJurusan(tampung_id[i]);
                send.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        try {
                            if (!response.body().getKode().equals("0")){
                                for (int i =0; i<response.body().getHasilIdSekolah().size(); i++){
                                    addData(response.body().getHasilIdSekolah().get(i).getId_sekolah());
                                }
                            }
                        } catch (Exception t){
                            Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        new SweetAlertDialog(PilihKriteriaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Terjadi Kesalahan")
                                .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                .show();

                        Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                    }
                });
            }
        }

        sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go = new Intent(PilihKriteriaActivity.this, SekolahRekomendasiActivity.class);

                // Amankan ID jurusan yang akan diteruskan ke proses perankingan sekolah yang paling cocok dengan calon siswa
                if (getIntent().getStringExtra("id") != null){
                    go.putExtra("id", getIntent().getStringExtra("id"));
                }

                for (int i = 0; i < getData().size(); i++) {
                    Log.d("Isi Get Prestasi", "Sekolah = "+getData().get(i)+" mendapat nilai sebesar = "+getKualitas().get(Integer.parseInt(getData().get(i))));
                }

                go.putStringArrayListExtra("isi_checklist", checklist);
                go.putStringArrayListExtra("persentase_checklist", nilaiPersentase(checklist));

                if (checkBiaya(checklist).length() != 0){
                    go.putExtra("isi_biaya", checkBiaya(checklist));
                }
                else if (checkBiaya(checklist).length() == 0){
                    go.putExtra("isi_biaya", "0");
                }
                startActivity(go);
            }
        });
    }

    private ArrayList<String> nilaiPersentase (ArrayList<String> daftar_checklist){
        ArrayList<String> nilai = new ArrayList<>();

        for (int i = 0; i < daftar_checklist.size(); i++) {
            if (daftar_checklist.get(i).equals("lokasi")){
                nilai.add(nilai_lokasi.getText().toString());
            }
            if (daftar_checklist.get(i).equals("kualitas")){
                nilai.add(nilai_kualitas.getText().toString());
            }
            if (daftar_checklist.get(i).equals("prestasi")){
                nilai.add(nilai_prestasi.getText().toString());
            }
            if (daftar_checklist.get(i).equals("akreditasi")){
                nilai.add(nilai_akreditasi.getText().toString());
            }
            if (daftar_checklist.get(i).equals("biaya")){
                nilai.add(nilai_biaya.getText().toString());
            }
            if (daftar_checklist.get(i).equals("fasilitas")){
                nilai.add(nilai_fasilitas.getText().toString());
            }
            if (daftar_checklist.get(i).equals("ekstrakulikuler")){
                nilai.add(nilai_ekstrakulikuler.getText().toString());
            }
        }
        return nilai;
    }

    private String checkBiaya (ArrayList<String> daftar_checklist){
        String total_biaya = "";

        for (int i = 0; i < daftar_checklist.size(); i++) {
            if (daftar_checklist.get(i).equals("biaya")){
                total_biaya = input_biaya.getText().toString() + " " + input_daftar.getText().toString();
            }

        }
        return total_biaya;
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

    private ArrayList<String> addData(String data){
        if (!this.isi_sekolah.contains(data)){
            this.isi_sekolah.add(data);
        }
        return this.isi_sekolah;
    }

    private ArrayList<String>  getData (){
        return this.isi_sekolah;
    }

    private ArrayList<String> addKualitas(int kunci, String data){
        this.kualitas_sekolah.add(kunci, data);

        return this.kualitas_sekolah;
    }

    private void removeKualitas(){
        getKualitas().clear();
    }

    private ArrayList<String>  getKualitas (){
        return this.kualitas_sekolah;
    }

}
