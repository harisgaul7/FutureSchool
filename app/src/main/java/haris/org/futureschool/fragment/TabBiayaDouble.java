package haris.org.futureschool.fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.R;
import haris.org.futureschool.adapter.BiayaAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.BiayaModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabBiayaDouble extends Fragment {

    private RecyclerView rv_biaya_awal, rv_biaya_bulanan, rv_biaya_awal2, rv_biaya_bulanan2;
    private BiayaAdapter biayaAdapter, biayaAdapter2;
    private ArrayList<BiayaModel> biayaModels, biayaModels2, biayaModels3, biayaModels4;
    private List<BiayaModel> dataBiaya = new ArrayList<>();
    private List<BiayaModel> dataBiaya2 = new ArrayList<>();
    private ProgressDialog pd;
    private TextView biaya1, biaya2, biaya3, biaya4;
    int total1, total2, total3, total4;
    private TextView sekolah_awal, sekolah_akhir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_biaya_double, container, false);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        rv_biaya_awal = (RecyclerView)view.findViewById(R.id.rv_biaya_awal);
        rv_biaya_bulanan = (RecyclerView)view.findViewById(R.id.rv_biaya_bulanan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        rv_biaya_awal.setLayoutManager(layoutManager);
        rv_biaya_bulanan.setLayoutManager(layoutManager2);

        rv_biaya_awal2 = (RecyclerView)view.findViewById(R.id.rv_biaya_awal2);
        rv_biaya_bulanan2 = (RecyclerView)view.findViewById(R.id.rv_biaya_bulanan2);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        rv_biaya_awal2.setLayoutManager(layoutManager3);
        rv_biaya_bulanan2.setLayoutManager(layoutManager4);

        biaya1 = view.findViewById(R.id.txt_jumlah_biaya_awal);
        biaya2 = view.findViewById(R.id.txt_jumlah_biaya_bulanan);
        biaya3 = view.findViewById(R.id.txt_jumlah_biaya_awal2);
        biaya4 = view.findViewById(R.id.txt_jumlah_biaya_bulanan2);

        biayaModels = new ArrayList<>();
        biayaModels2 = new ArrayList<>();
        biayaModels3 = new ArrayList<>();
        biayaModels4 = new ArrayList<>();

        total1 = 0;
        total2 = 0;
        total3 = 0;
        total4 = 0;

        sekolah_awal = view.findViewById(R.id.txt_biaya_sekolah_awal);
        sekolah_akhir = view.findViewById(R.id.txt_biaya_sekolah_akhir);

        if (getArguments().getString("id") != null){
            String nama_sekolah[] = getArguments().getString("nama").split("=");
            sekolah_awal.setText("Biaya sekolah di "+nama_sekolah[0]);
            sekolah_akhir.setText("Biaya sekolah di "+nama_sekolah[1]);

            String pisah[] = getArguments().getString("id").split("=");

            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataBiaya(Integer.parseInt(pisah[0]));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    try {
                        pd.dismiss();
                        dataBiaya = response.body().getHasilBiaya();

                        for (int i = 0; i < dataBiaya.size(); i++) {
                            if (response.body().getHasilBiaya().get(i).getJenis_biaya().equals("awal")){
                                total1 += Integer.parseInt(dataBiaya.get(i).getJumlah_biaya());
                                biayaModels.add(new BiayaModel(dataBiaya.get(i).getNama_biaya(), dataBiaya.get(i).getJumlah_biaya()));
                                biayaAdapter = new BiayaAdapter(biayaModels);
                                rv_biaya_awal.setAdapter(biayaAdapter);
                            }
                            else if (response.body().getHasilBiaya().get(i).getJenis_biaya().equals("bulanan")){
                                total2 += Integer.parseInt(dataBiaya.get(i).getJumlah_biaya());
                                biayaModels2.add(new BiayaModel(dataBiaya.get(i).getNama_biaya(), dataBiaya.get(i).getJumlah_biaya()));
                                biayaAdapter = new BiayaAdapter(biayaModels2);
                                rv_biaya_bulanan.setAdapter(biayaAdapter);
                            }
                        }

                        NumberFormat nf = NumberFormat.getCurrencyInstance();
                        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
                        decimalFormatSymbols.setCurrencySymbol("");
                        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);

                        //nf.format(total1).trim() -> buat 3 titik tiap ribuan
                        //string..substring(0, string.length() - 3) -> buat ilangin ,00 tiap format decimal

                        biaya1.setText(nf.format(total1).trim().substring(0, nf.format(total1).trim().length() - 3));
                        biaya2.setText(nf.format(total2).trim().substring(0, nf.format(total2).trim().length() - 3));
                    } catch (Exception e){
                        Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
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

            ApiRequest api2 = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send2 = api2.getDataBiaya(Integer.parseInt(pisah[1]));
            send2.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    try {
                        pd.dismiss();
                        dataBiaya2 = response.body().getHasilBiaya();

                        for (int i = 0; i < dataBiaya2.size(); i++) {
                            if (response.body().getHasilBiaya().get(i).getJenis_biaya().equals("awal")){
                                total3 += Integer.parseInt(dataBiaya2.get(i).getJumlah_biaya());
                                biayaModels3.add(new BiayaModel(dataBiaya2.get(i).getNama_biaya(), dataBiaya2.get(i).getJumlah_biaya()));
                                biayaAdapter2 = new BiayaAdapter(biayaModels3);
                                rv_biaya_awal2.setAdapter(biayaAdapter2);
                            }
                            else if (response.body().getHasilBiaya().get(i).getJenis_biaya().equals("bulanan")){
                                total4 += Integer.parseInt(dataBiaya2.get(i).getJumlah_biaya());
                                biayaModels4.add(new BiayaModel(dataBiaya2.get(i).getNama_biaya(), dataBiaya2.get(i).getJumlah_biaya()));
                                biayaAdapter2 = new BiayaAdapter(biayaModels4);
                                rv_biaya_bulanan2.setAdapter(biayaAdapter2);
                            }
                        }

                        NumberFormat nf = NumberFormat.getCurrencyInstance();
                        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
                        decimalFormatSymbols.setCurrencySymbol("");
                        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);

                        //nf.format(total1).trim() -> buat 3 titik tiap ribuan
                        //string..substring(0, string.length() - 3) -> buat ilangin ,00 tiap format decimal

                        biaya3.setText(nf.format(total3).trim().substring(0, nf.format(total3).trim().length() - 3));
                        biaya4.setText(nf.format(total4).trim().substring(0, nf.format(total4).trim().length() - 3));
                    } catch (Exception e){
                        Log.d("Error = ", "Sepertinya anda terputus dari server atau server tidak diatur dengan benar. Masalah = "+e);
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

        return view;
    }
}