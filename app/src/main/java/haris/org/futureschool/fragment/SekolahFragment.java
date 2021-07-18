package haris.org.futureschool.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.R;
import haris.org.futureschool.FilterActivity;
import haris.org.futureschool.adapter.SekolahAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.FilterBiayaAwalModel;
import haris.org.futureschool.model.FilterBiayaBulananModel;
import haris.org.futureschool.model.FilterEkstrakurikulerSekolahModel;
import haris.org.futureschool.model.FilterFasilitasSekolahModel;
import haris.org.futureschool.model.ResponseModel;
import haris.org.futureschool.model.TampilanSekolahModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class SekolahFragment extends Fragment {

    private RecyclerView rv;
    private SekolahAdapter adapter;
    private ArrayList<TampilanSekolahModel> sekolahModelArrayList;
    private String sortir, tingkat, biayaAwal, biayaBulanan, ketersediaanFasilitas, ketersediaanEkstrakurikuler;

    private List<Integer> filterFasilitas;
    private List<Integer> filterEkstrakurikuler;
    private List<Integer> filterBiayaAwal;
    private List<Integer> filterFasilitasEkstrakurikuler;
    private List<Integer> filterBiayaBulanan;
    private List<Integer> filterSemua;

    private LinearLayout filter;

    private List<TampilanSekolahModel> dataTampilanSekolah = new ArrayList<>();
    private List<FilterEkstrakurikulerSekolahModel> filterEkstrakurikulerSekolahModels = new ArrayList<>();
    private List<FilterFasilitasSekolahModel> filterFasilitasSekolahModels = new ArrayList<>();
    private List<FilterBiayaAwalModel> filterBiayaAwalModels = new ArrayList<>();
    private List<FilterBiayaBulananModel> filterBiayaBulananModels = new ArrayList<>();


    private ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_sekolah, container, false);

        filter = (LinearLayout)view.findViewById(R.id.ll_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), FilterActivity.class);
                startActivityForResult(i, 1);
            }
        });
        
        rv = (RecyclerView)view.findViewById(R.id.rv_sekolah);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        sekolahModelArrayList = new ArrayList<>();

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        final EditText cari = (EditText)view.findViewById(R.id.et_pencarian);
        final ImageView loop = (ImageView) view.findViewById(R.id.iv_loop);
        loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sekolahModelArrayList.clear();
                adapter = new SekolahAdapter(sekolahModelArrayList);
                rv.setAdapter(adapter);

                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<ResponseModel> send = api.getCariSekolah(cari.getText().toString());
                send.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.dismiss();
                        try {
                            String url = new BaseUrl().GAMBAR_URL;
                            // Kode untuk mengukur 2 jarak
                            Location loc1 = new Location("");
                            loc1.setLatitude(0.464971);
                            loc1.setLongitude(101.347580);

                            Location loc2 = new Location("");
                            loc2.setLatitude(0.465429);
                            loc2.setLongitude(101.373432);

                            float jarak = loc1.distanceTo(loc2)/1000.0f;

                            dataTampilanSekolah = response.body().getHasilCariSekolah();
                            if (!response.body().getKode().equals("0")){
                                for (int i =0; i<dataTampilanSekolah.size(); i++){
                                    sekolahModelArrayList.add(new TampilanSekolahModel(dataTampilanSekolah.get(i).getId_sekolah(), url+dataTampilanSekolah.get(i).getGambar_sekolah(), dataTampilanSekolah.get(i).getNama_sekolah(), dataTampilanSekolah.get(i).getAlamat_sekolah(), dataTampilanSekolah.get(i).getDeskripsi_sekolah(), jarak, dataTampilanSekolah.get(i).getAkreditasi_sekolah(), dataTampilanSekolah.get(i).getVisi_misi_sekolah(), dataTampilanSekolah.get(i).getKurikulum_sekolah(), dataTampilanSekolah.get(i).getSlide_sekolah()));
                                    adapter = new SekolahAdapter(sekolahModelArrayList);
                                    rv.setAdapter(adapter);
                                }
                            }
                        } catch (Exception t){
                            Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Terjadi Kesalahan")
                                .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                .show();

                        Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                    }
                });
            }
        });

        // Kode untuk mengukur 2 jarak
        Location loc1 = new Location("");
        loc1.setLatitude(0.464971);
        loc1.setLongitude(101.347580);

        Location loc2 = new Location("");
        loc2.setLatitude(0.465429);
        loc2.setLongitude(101.373432);

        float jarak = loc1.distanceTo(loc2);

        // Ikasari : 0.478355, 101.370420
//        Toast.makeText(getActivity(), "Jarak = "+jarak+" m", Toast.LENGTH_SHORT).show();

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponseModel> send = api.getDataTampilanSekolah();
        send.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.dismiss();
                try {
                    String url = new BaseUrl().GAMBAR_URL;
                    // Kode untuk mengukur 2 jarak
                    Location loc1 = new Location("");
                    loc1.setLatitude(0.464971);
                    loc1.setLongitude(101.347580);

                    Location loc2 = new Location("");
                    loc2.setLatitude(0.465429);
                    loc2.setLongitude(101.373432);

                    float jarak = loc1.distanceTo(loc2)/1000.0f;

                    dataTampilanSekolah = response.body().getHasilTampilanSekolah();
                    if (!response.body().getKode().equals("0")){
                        for (int i =0; i<dataTampilanSekolah.size(); i++){
                            sekolahModelArrayList.add(new TampilanSekolahModel(response.body().getHasilTampilanSekolah().get(i).getId_sekolah(), url+response.body().getHasilTampilanSekolah().get(i).getGambar_sekolah(), response.body().getHasilTampilanSekolah().get(i).getNama_sekolah(), response.body().getHasilTampilanSekolah().get(i).getAlamat_sekolah(), response.body().getHasilTampilanSekolah().get(i).getDeskripsi_sekolah(), jarak, response.body().getHasilTampilanSekolah().get(i).getAkreditasi_sekolah(),response.body().getHasilTampilanSekolah().get(i).getVisi_misi_sekolah(), response.body().getHasilTampilanSekolah().get(i).getKurikulum_sekolah(), response.body().getHasilTampilanSekolah().get(i).getSlide_sekolah()));
                            adapter = new SekolahAdapter(sekolahModelArrayList);
                            rv.setAdapter(adapter);
                        }
                    }
                } catch (Exception t){
                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Terjadi Kesalahan")
                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                        .show();

                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                sortir = data.getStringExtra("sortir");
                tingkat = data.getStringExtra("tingkat");
                biayaAwal = data.getStringExtra("biayaAwal");
                biayaBulanan = data.getStringExtra("biayaBulanan");
                ketersediaanFasilitas = data.getStringExtra("ketersediaanFasilitas");
                ketersediaanEkstrakurikuler = data.getStringExtra("ketersediaanEkstrakurikuler");

                sekolahModelArrayList.clear();
                adapter = new SekolahAdapter(sekolahModelArrayList);
                rv.setAdapter(adapter);

                Log.d("Kelengkapan = ", sortir+"\n"+tingkat+"\n"+biayaAwal+"\n"+biayaBulanan+"\n"+ketersediaanFasilitas+"\n"+ketersediaanEkstrakurikuler);

                if (sortir == null || tingkat == null || biayaAwal == null || biayaBulanan == null || ketersediaanFasilitas == null || ketersediaanEkstrakurikuler == null){
                    // nanti set apabila kosong semua
                    Toast.makeText(getContext(), "KOSONG CUY!", Toast.LENGTH_SHORT).show();
                }
                else {

                    final String pisahFasilitas[] = ketersediaanFasilitas.split(" ");
                    final String pisahEkstrakurikuler[] = ketersediaanEkstrakurikuler.split(" ");
                    String bA = biayaAwal.replace(",", "");
                    String bB = biayaBulanan.replace(",", "");
                    final String pisahBiayaAwal[] = bA.split(" ");
                    final String pisahBiayaBulanan[] = bB.split(" ");

                    final String fasilitas = ketersediaanFasilitas.replace(" ", ",");
                    final String ekstrakurikuler = ketersediaanEkstrakurikuler.replace(" ", ",");

                    filterFasilitas = new ArrayList();
                    filterEkstrakurikuler = new ArrayList();
                    filterFasilitasEkstrakurikuler = new ArrayList();
                    filterBiayaAwal = new ArrayList();
                    filterBiayaBulanan = new ArrayList();
                    filterSemua = new ArrayList();

                    final HashMap<Integer, List<FilterFasilitasSekolahModel>> tampungData = new HashMap<>();

                    ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                    Call<ResponseModel> send = api.getFilterSekolah(sortir, tingkat, Integer.parseInt(pisahBiayaAwal[0]), Integer.parseInt(pisahBiayaAwal[1]), Integer.parseInt(pisahBiayaBulanan[0]), Integer.parseInt(pisahBiayaBulanan[1]), fasilitas, ekstrakurikuler);
                    send.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            pd.dismiss();
                            try {
                                // ambil sekolah yang memiliki fasilitas yang difilter
                                filterFasilitasSekolahModels = response.body().getHasilFilterFasilitasSekolah();
                                int x;
                                if (!response.body().getKode().equals("0")){
                                    for (int i =0; i<filterFasilitasSekolahModels.size(); i++){
                                        x = 0;
                                        for (int j = 0; j < filterFasilitasSekolahModels.size(); j++) {
                                            if (filterFasilitasSekolahModels.get(i).getId_sekolah() == filterFasilitasSekolahModels.get(j).getId_sekolah()){
                                                x++;
                                            }
                                        }
                                        if (x == pisahFasilitas.length){
                                            if (!filterFasilitas.contains(filterFasilitasSekolahModels.get(i).getId_sekolah())){
                                                filterFasilitas.add(filterFasilitasSekolahModels.get(i).getId_sekolah());
                                            }
                                        }
                                    }
                                }

                                // ambil sekolah yang memiliki ekstrakurikuler yang difilter
                                filterEkstrakurikulerSekolahModels = response.body().getHasilFilterEkstrakurikulerSekolah();
                                int y;
                                if (!response.body().getKode().equals("0")){
                                    for (int i =0; i<filterEkstrakurikulerSekolahModels.size(); i++){
                                        y = 0;
                                        for (int j = 0; j < filterEkstrakurikulerSekolahModels.size(); j++) {
                                            if (filterEkstrakurikulerSekolahModels.get(i).getId_sekolah() == filterEkstrakurikulerSekolahModels.get(j).getId_sekolah()){
                                                y++;
                                            }
                                        }
                                        if (y == pisahEkstrakurikuler.length){
                                            if (!filterEkstrakurikuler.contains(filterEkstrakurikulerSekolahModels.get(i).getId_sekolah())){
                                                filterEkstrakurikuler.add(filterEkstrakurikulerSekolahModels.get(i).getId_sekolah());
                                            }
                                        }
                                    }
                                }

                                if (filterEkstrakurikuler.size() == 0 && filterFasilitas.size() == 0){
                                    Toast.makeText(getContext(), "Hasil filter tidak ditemukan!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    for (int i = 0; i < filterEkstrakurikuler.size(); i++) {
                                        if (filterFasilitas.contains(filterEkstrakurikuler.get(i))){
                                            Log.d("Filter = ", String.valueOf(filterEkstrakurikuler.get(i)));
                                            filterFasilitasEkstrakurikuler.add(filterEkstrakurikuler.get(i));
                                        }
                                    }
                                }

                                // ambil sekolah yang memiliki biaya awal yang difilter
                                filterBiayaAwalModels = response.body().getHasilFilterAwalSekolah();
                                if (!response.body().getKode().equals("0")){
                                    for (int i =0; i<filterBiayaAwalModels.size(); i++){
                                        if (!filterBiayaAwal.contains(filterBiayaAwalModels.get(i).getId_sekolah())){
                                            filterBiayaAwal.add(filterBiayaAwalModels.get(i).getId_sekolah());
                                        }
                                        Log.d("Data Biaya Awal", String.valueOf(filterBiayaAwalModels.get(i).getId_sekolah()));
                                    }
                                }

                                // ambil sekolah yang memiliki biaya bulanan yang difilter
                                filterBiayaBulananModels = response.body().getHasilFilterBulananSekolah();
                                if (!response.body().getKode().equals("0")){
                                    for (int i =0; i<filterBiayaBulananModels.size(); i++){
                                        if (!filterBiayaBulanan.contains(filterBiayaBulananModels.get(i).getId_sekolah())){
                                            filterBiayaBulanan.add(filterBiayaBulananModels.get(i).getId_sekolah());
                                        }
                                        Log.d("Data Biaya Bulanan", String.valueOf(filterBiayaBulananModels.get(i).getId_sekolah()));
                                    }
                                }


                                filterSemua.addAll(filterFasilitasEkstrakurikuler);
                                filterSemua.addAll(filterBiayaAwal);
                                filterSemua.addAll(filterBiayaBulanan);

                                // Membersihkan duplikat
                                Set<Integer> tampung = new LinkedHashSet<Integer>();
                                tampung.addAll(filterSemua);
                                filterSemua.clear();
                                filterSemua.addAll(tampung);

                                // Memfilter sekolah yang memiliki fasilitas, ekstrakurikuler, biaya awal dan biaya bulanan yang diinginkan
                                sekolahModelArrayList = new ArrayList<>();

                                for (int i = 0; i < filterSemua.size(); i++) {
                                    if (filterFasilitasEkstrakurikuler.contains(filterSemua.get(i)) && filterBiayaAwal.contains(filterSemua.get(i)) && filterBiayaBulanan.contains(filterSemua.get(i))){
                                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                                        Call<ResponseModel> send = api.getDataTampilanSekolahById(filterSemua.get(i));
                                        send.enqueue(new Callback<ResponseModel>() {
                                            @Override
                                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                                pd.dismiss();
                                                try {
                                                    String url = new BaseUrl().GAMBAR_URL;
                                                    // Kode untuk mengukur 2 jarak
                                                    Location loc1 = new Location("");
                                                    loc1.setLatitude(0.464971);
                                                    loc1.setLongitude(101.347580);

                                                    Location loc2 = new Location("");
                                                    loc2.setLatitude(0.465429);
                                                    loc2.setLongitude(101.373432);

                                                    float jarak = loc1.distanceTo(loc2)/1000.0f;

                                                    dataTampilanSekolah = response.body().getHasilTampilanSekolah();
                                                    if (!response.body().getKode().equals("0")){
                                                        for (int i =0; i<dataTampilanSekolah.size(); i++){
                                                            sekolahModelArrayList.add(new TampilanSekolahModel(response.body().getHasilTampilanSekolah().get(i).getId_sekolah(), url+response.body().getHasilTampilanSekolah().get(i).getGambar_sekolah(), response.body().getHasilTampilanSekolah().get(i).getNama_sekolah(), response.body().getHasilTampilanSekolah().get(i).getAlamat_sekolah(), response.body().getHasilTampilanSekolah().get(i).getDeskripsi_sekolah(), jarak, response.body().getHasilTampilanSekolah().get(i).getAkreditasi_sekolah(),response.body().getHasilTampilanSekolah().get(i).getVisi_misi_sekolah(), response.body().getHasilTampilanSekolah().get(i).getKurikulum_sekolah(), response.body().getHasilTampilanSekolah().get(i).getSlide_sekolah()));
                                                            adapter = new SekolahAdapter(sekolahModelArrayList);
                                                            rv.setAdapter(adapter);
                                                        }
                                                    }
                                                } catch (Exception t){
                                                    Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText("Terjadi Kesalahan")
                                                        .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                                        .show();

                                                Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                                            }
                                        });
                                    }
                                }

                            } catch (Exception t){
                                Log.e("Error Filter = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+t);
                            }
                        }


                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Terjadi Kesalahan")
                                    .setContentText("Terjadi kesalahan pada server, mohon cek koneksi anda!")
                                    .show();

                            Log.e("Keterangan Error", "Error terjadi, masalah = "+t);
                        }
                    });
                }
            }
        }
    }
}
