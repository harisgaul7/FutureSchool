package haris.org.futureschool.fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
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

import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.JurusanActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.SekolahActivity;
import haris.org.futureschool.adapter.JurusanAdapter;
import haris.org.futureschool.database.ApiRequest;
import haris.org.futureschool.database.Retroserver;
import haris.org.futureschool.library.DividerItemDecoration;
import haris.org.futureschool.model.DeskripsiJurusanModel;
import haris.org.futureschool.model.ExpandableModel;
import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabJurusan extends Fragment {

    List<DeskripsiJurusanModel> deskripsiJurusanModels = new ArrayList<>();
    ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_jurusan, container, false);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading ...\nJika menunggu terlalu lama kemungkinan anda terputus dari server");

        if (getArguments().getString("id") != null){
            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<ResponseModel> send = api.getDataDeskripsiJurusan(Integer.parseInt(getArguments().getString("id")));
            send.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.dismiss();
                    try {
                        deskripsiJurusanModels = response.body().getHasilDeskripsiJurusan();

                        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_jurusan);
                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        final List<ExpandableModel> data = new ArrayList<>();

                        if (!response.body().getKode().equals("0")){
                            for (int i = 0; i < deskripsiJurusanModels.size(); i++) {
                                data.add(new ExpandableModel(
                                        response.body().getHasilDeskripsiJurusan().get(i).getNama_jurusan(),
                                        response.body().getHasilDeskripsiJurusan().get(i).getDeskripsi_jurusan(),
                                        R.color.judul_jurusan,
                                        R.color.material_grey_300,
                                        Utils.createInterpolator(Utils.FAST_OUT_LINEAR_IN_INTERPOLATOR)));
                            }
                        }

                        recyclerView.setAdapter(new JurusanAdapter(data));
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