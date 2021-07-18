package haris.org.futureschool.fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.R;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.PrestasiModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabPrestasi extends Fragment {

    private TableLayout tableLayoutPrestasi;
    private TextView txt_perlombaan, txt_juara, txt_tingkat, txt_tahun;
    private List<PrestasiModel> dataPrestasi = new ArrayList<>();
    private ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_prestasi, container, false);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        tableLayoutPrestasi = view.findViewById(R.id.tabel_daftar_prestasi);
        cleanTable(tableLayoutPrestasi);

        if (getArguments().getString("id") != null){
            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataPrestasi(Integer.parseInt(getArguments().getString("id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    try {
                        pd.dismiss();
                        dataPrestasi = response.body().getHasilPrestasi();
                        if (!response.body().getKode().equals("0")){
                            for (int i = 0; i < dataPrestasi.size(); i++) {
                                TableRow rowPrestasi = (TableRow)getLayoutInflater().inflate(R.layout.row_prestasi, null);
                                txt_perlombaan = rowPrestasi.findViewById(R.id.kolom_perlombaan);
                                txt_juara = rowPrestasi.findViewById(R.id.kolom_juara);
                                txt_tingkat = rowPrestasi.findViewById(R.id.kolom_tingkat);
                                txt_tahun = rowPrestasi.findViewById(R.id.kolom_tahun);

                                txt_perlombaan.setSelected(true);
                                txt_tingkat.setSelected(true);

                                txt_perlombaan.setText(response.body().getHasilPrestasi().get(i).getNama_prestasi());
                                txt_juara.setText(response.body().getHasilPrestasi().get(i).getPeringkat_prestasi());
                                txt_tingkat.setText(response.body().getHasilPrestasi().get(i).getTingkat_prestasi());
                                txt_tahun.setText(response.body().getHasilPrestasi().get(i).getTahun_prestasi());

                                tableLayoutPrestasi.addView(rowPrestasi);
                            }
                        }
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

    private void cleanTable (TableLayout table){
        int childCount = table.getChildCount();
        if (childCount > 0){
            table.removeViews(0, childCount);
        }
    }
}