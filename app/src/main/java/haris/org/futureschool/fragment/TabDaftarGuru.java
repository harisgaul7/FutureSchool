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
import haris.org.futureschool.model.GuruModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabDaftarGuru extends Fragment {

    private TableLayout tabelGuru;
    private TextView no, namaGuru, jenisKelaminGuru, gelarGuru, mapelGuru;
    private List<GuruModel> dataGuru = new ArrayList<>();
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_daftar_guru, container, false);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        tabelGuru = view.findViewById(R.id.tabel_daftar_guru);
        cleanTable(tabelGuru);

        if (getArguments().getString("id") != null){
            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataGuru(Integer.parseInt(getArguments().getString("id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    try {
                        pd.dismiss();
                        dataGuru = response.body().getHasilGuru();
                        if (!response.body().getKode().equals("0")){
                            for (int i = 0; i < dataGuru.size(); i++) {
                                TableRow dataGuru = (TableRow)getLayoutInflater().inflate(R.layout.row_guru, null);
                                no = dataGuru.findViewById(R.id.kolom_nomor_guru);
                                namaGuru = dataGuru.findViewById(R.id.kolom_nama_guru);
                                jenisKelaminGuru = dataGuru.findViewById(R.id.kolom_gender_guru);
                                gelarGuru = dataGuru.findViewById(R.id.kolom_ijazah_guru);
                                mapelGuru = dataGuru.findViewById(R.id.kolom_mapel_guru);

                                namaGuru.setSelected(true);
                                mapelGuru.setSelected(true);

                                no.setText(String.valueOf(i+1));
                                namaGuru.setText(response.body().getHasilGuru().get(i).getNama_guru());
                                String jenisKelamin = response.body().getHasilGuru().get(i).getJk_guru();
                                if (jenisKelamin.equals("laki-laki")){
                                    jenisKelamin = "L";
                                }
                                else if (jenisKelamin.equals("perempuan")){
                                    jenisKelamin = "P";
                                }
                                jenisKelaminGuru.setText(jenisKelamin);
                                gelarGuru.setText(response.body().getHasilGuru().get(i).getPendidikan_guru());
                                mapelGuru.setText(response.body().getHasilGuru().get(i).getMapel_guru());

                                tabelGuru.addView(dataGuru);
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