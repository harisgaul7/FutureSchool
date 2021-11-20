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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.R;
import haris.org.futureschool.adapter.FasilitasAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.FasilitasModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabFasilitasDouble extends Fragment {

    private RecyclerView rv_fasilitas, rv_fasilitas2;
    private FasilitasAdapter fasilitasAdapter, fasilitasAdapter2;
    private ArrayList<FasilitasModel> fasilitasModels, fasilitasModels2;
    private List<FasilitasModel> dataFasilitas = new ArrayList<>();
    private List<FasilitasModel> dataFasilitas2 = new ArrayList<>();
    private ProgressDialog pd;
    private TextView sekolah_awal, sekolah_akhir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fasilitas_double, container, false);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        rv_fasilitas = (RecyclerView)view.findViewById(R.id.rv_fasilitas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_fasilitas.setLayoutManager(layoutManager);

        rv_fasilitas2 = (RecyclerView)view.findViewById(R.id.rv_fasilitas2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        rv_fasilitas2.setLayoutManager(layoutManager2);

        fasilitasModels = new ArrayList<>();
        fasilitasModels2 = new ArrayList<>();

        sekolah_awal = view.findViewById(R.id.txt_fasilitas_sekolah_awal);
        sekolah_akhir = view.findViewById(R.id.txt_fasilitas_sekolah_akhir);

        if (getArguments().getString("id") != null){
            String nama_sekolah[] = getArguments().getString("nama").split("=");
            sekolah_awal.setText("Fasilitas di "+nama_sekolah[0]);
            sekolah_akhir.setText("Fasilitas di "+nama_sekolah[1]);

            String pisah[] = getArguments().getString("id").split("=");

            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataFasilitas(Integer.parseInt(pisah[0]));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
                        dataFasilitas = response.body().getHasilFasilitas();
                        if (!response.body().getKode().equals("0")){
                            for (int i = 0; i < dataFasilitas.size(); i++) {
                                fasilitasModels.add(new FasilitasModel(response.body().getHasilFasilitas().get(i).getNama_fasilitas(), response.body().getHasilFasilitas().get(i).getFoto_fasilitas()));
                                fasilitasAdapter = new FasilitasAdapter(fasilitasModels);
                                rv_fasilitas.setAdapter(fasilitasAdapter);
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

            ApiRequest api2 = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send2 = api2.getDataFasilitas(Integer.parseInt(pisah[1]));
            send2.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
                        dataFasilitas2 = response.body().getHasilFasilitas();
                        if (!response.body().getKode().equals("0")){
                            for (int i = 0; i < dataFasilitas2.size(); i++) {
                                fasilitasModels2.add(new FasilitasModel(response.body().getHasilFasilitas().get(i).getNama_fasilitas(), response.body().getHasilFasilitas().get(i).getFoto_fasilitas()));
                                fasilitasAdapter2 = new FasilitasAdapter(fasilitasModels2);
                                rv_fasilitas2.setAdapter(fasilitasAdapter2);
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
}