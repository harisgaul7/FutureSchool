package haris.org.futureschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.adapter.FilterEkstrakurikulerAdapter;
import haris.org.futureschool.adapter.FilterFasilitasAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.library.OnFilterClickListener;
import haris.org.futureschool.model.FilterEkstrakurikulerModel;
import haris.org.futureschool.model.FilterFasilitasModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static haris.org.futureschool.PilihKriteriaActivity.getDecimalFormattedString;


public class FilterActivity extends AppCompatActivity {

    private ImageView cancel;
    private TextView hasilFasilitas, hasilEkstrakurikuler, terapkan;
    private Spinner sort, tingkat;
    private EditText dariAwal, sampaiAwal, dariBulanan, sampaiBulanan;
    private RecyclerView rvFasilitas, rvEkstrakurikuler;
    private FilterFasilitasAdapter filterFasilitasAdapter;
    private FilterEkstrakurikulerAdapter filterEkstrakurikulerAdapter;
    private ArrayList<FilterFasilitasModel> filterFasilitasModels;
    private ArrayList<FilterEkstrakurikulerModel> filterEkstrakurikulerModels;
    private List<FilterFasilitasModel> dataFilterFasilitas = new ArrayList<>();
    private List<FilterEkstrakurikulerModel> dataFilterEkstrakurikuler = new ArrayList<>();
    private ProgressDialog pd;
    private OnFilterClickListener onFilterClickListener;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_filter);

        Intent intent = new Intent();
        intent.putExtra("isi", "value_here");
        setResult(RESULT_OK, intent);

        hasilFasilitas = findViewById(R.id.hasil_filter_fasilitas);
        hasilEkstrakurikuler = findViewById(R.id.hasil_filter_ekstrakurikuler);

        onFilterClickListener = new OnFilterClickListener() {
            @Override
            public void onFilterClick(List<String> isi, String jenis) {
                String tampil = "";
                for (int i = 0; i < isi.size(); i++) {
                    tampil+=isi.get(i)+" ";
                }
                if (jenis.equals("fasilitas")){
                    setTextHasil(tampil, hasilFasilitas);
                }
                else if (jenis.equals("ekstrakurikuler")){
                    setTextHasil(tampil, hasilEkstrakurikuler);
                }
            }
        };

        cancel = findViewById(R.id.iv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sort = findViewById(R.id.spin_filter);
        tingkat = findViewById(R.id.spin_tingkat);

        aturSpinner(sort, R.array.sorting);
        aturSpinner(tingkat, R.array.tingkat);

        dariAwal = findViewById(R.id.et_dari_awal);
        sampaiAwal = findViewById(R.id.et_sampai_awal);
        dariBulanan = findViewById(R.id.et_dari_bulanan);
        sampaiBulanan = findViewById(R.id.et_sampai_bulanan);

        aturNominal(dariAwal);
        aturNominal(sampaiAwal);
        aturNominal(dariBulanan);
        aturNominal(sampaiBulanan);

        pd = new ProgressDialog(FilterActivity.this);
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        rvFasilitas = findViewById(R.id.rv_filter_fasilitas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FilterActivity.this);
        rvFasilitas.setLayoutManager(layoutManager);

        filterFasilitasModels = new ArrayList<>();

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponseModel> send = api.getFilterFasilitas();
        send.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.dismiss();
                try {
                    dataFilterFasilitas = response.body().getFilterFasilitas();
                    if (!response.body().getKode().equals("0")){
                        for (int i = 0; i < dataFilterFasilitas.size(); i++) {
                            filterFasilitasModels.add(new FilterFasilitasModel(dataFilterFasilitas.get(i).getNama_fasilitas(), dataFilterFasilitas.get(i).getId_master_fasilitas()));
                            filterFasilitasAdapter = new FilterFasilitasAdapter(filterFasilitasModels, onFilterClickListener);
                            rvFasilitas.setAdapter(filterFasilitasAdapter);
                        }
                    }
                } catch (Exception e){
                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                new SweetAlertDialog(FilterActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Terjadi Kesalahan")
                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                        .show();

                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
            }
        });


        rvEkstrakurikuler = findViewById(R.id.rv_filter_ekstrakurikuler);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(FilterActivity.this);
        rvEkstrakurikuler.setLayoutManager(layoutManager2);

        filterEkstrakurikulerModels = new ArrayList<>();

        ApiRequest api2 = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponseModel> send2 = api2.getFilterEkstrakurikuler();
        send2.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.dismiss();
                try {
                    dataFilterEkstrakurikuler = response.body().getFilterEkstrakurikuler();
                    if (!response.body().getKode().equals("0")){
                        for (int i = 0; i < dataFilterEkstrakurikuler.size(); i++) {
                            filterEkstrakurikulerModels.add(new FilterEkstrakurikulerModel(dataFilterEkstrakurikuler.get(i).getNama_ekstrakurikuler(), dataFilterEkstrakurikuler.get(i).getId_master_ekstrakurikuler()));
                            filterEkstrakurikulerAdapter = new FilterEkstrakurikulerAdapter(filterEkstrakurikulerModels, onFilterClickListener);
                            rvEkstrakurikuler.setAdapter(filterEkstrakurikulerAdapter);
                        }
                    }
                } catch (Exception e){
                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                new SweetAlertDialog(FilterActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Terjadi Kesalahan")
                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                        .show();

                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
            }
        });

        terapkan = findViewById(R.id.txt_terapkan_filter);
        terapkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("sortir", sort.getSelectedItem().toString());
                intent.putExtra("tingkat", tingkat.getSelectedItem().toString());
                intent.putExtra("biayaAwal", dariAwal.getText().toString()+" "+sampaiAwal.getText().toString());
                intent.putExtra("biayaBulanan", dariBulanan.getText().toString()+" "+sampaiBulanan.getText().toString());
                intent.putExtra("ketersediaanFasilitas", hasilFasilitas.getText());
                intent.putExtra("ketersediaanEkstrakurikuler", hasilEkstrakurikuler.getText());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void aturSpinner(Spinner spin, int array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FilterActivity.this, array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void aturNominal (final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
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
                    editText.removeTextChangedListener(this);
                    String value = editText.getText().toString();


                    if (value != null && !value.equals(""))
                    {

                        if(value.startsWith(".")){
                            editText.setText("0.");
                        }
                        if(value.startsWith("0") && !value.startsWith("0.")){
                            editText.setText("");

                        }


                        String str = editText.getText().toString().replaceAll(",", "");
                        if (!value.equals(""))
                            editText.setText(getDecimalFormattedString(str));
                        editText.setSelection(editText.getText().toString().length());
                    }
                    editText.addTextChangedListener(this);
                    return;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    editText.addTextChangedListener(this);
                }
            }
        });
    }

    public void setTextHasil(String isi, TextView tulis){
        tulis.setText(isi);
    }
}
