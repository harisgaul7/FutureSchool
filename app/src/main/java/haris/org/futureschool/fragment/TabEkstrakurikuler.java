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

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.R;
import haris.org.futureschool.adapter.EkstrakurikulerAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.model.EkstrakurikulerModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabEkstrakurikuler extends Fragment {

    private RecyclerView rv_ekstrakurikuler;
    private EkstrakurikulerAdapter ekstrakurikulerAdapter;
    private ArrayList<EkstrakurikulerModel> ekstrakurikulerModels;
    private List<EkstrakurikulerModel> dataEkstrakurikuler = new ArrayList<>();
    private ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_ekstrakurikuler, container, false);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        rv_ekstrakurikuler = (RecyclerView)view.findViewById(R.id.rv_ekstrakurikuler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_ekstrakurikuler.setLayoutManager(layoutManager);

        ekstrakurikulerModels = new ArrayList<>();

        if (getArguments().getString("id") != null){
            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataEkstrakurikuler(Integer.parseInt(getArguments().getString("id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
                        dataEkstrakurikuler = response.body().getHasilEkstrakurikuler();
                        if (!response.body().getKode().equals("0")){
                            for (int i = 0; i < dataEkstrakurikuler.size(); i++) {
                                ekstrakurikulerModels.add(new EkstrakurikulerModel(response.body().getHasilEkstrakurikuler().get(i).getNama_ekstrakurikuler(), response.body().getHasilEkstrakurikuler().get(i).getGambar_ekstrakurikuler()));
                                ekstrakurikulerAdapter = new EkstrakurikulerAdapter(ekstrakurikulerModels);
                                rv_ekstrakurikuler.setAdapter(ekstrakurikulerAdapter);
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