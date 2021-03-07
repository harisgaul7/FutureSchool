package haris.org.futureschool.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import haris.org.futureschool.DetailSekolahActivity;
import haris.org.futureschool.PilihTopikActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.TestActivity;
import haris.org.futureschool.adapter.DetailAdapter;
import haris.org.futureschool.adapter.RekomendasiAdapter;
import haris.org.futureschool.model.RekomendasiModel;

public class BerandaFragment extends Fragment {

    private RecyclerView rv_favorit;
    private RekomendasiAdapter rekAd;
    private ArrayList<RekomendasiModel> rekomendasiModels;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    DetailAdapter adapter;
    LinearLayoutManager horizontalLayout;
    ArrayList<Integer> source;
    ArrayList<String> news;
    ArrayList<String> date;

    @Nullable
    @Override
    
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_beranda, container, false);

        rv_favorit = view.findViewById(R.id.rv_terfavorit);
        rekomendasiModels = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_favorit.setLayoutManager(layoutManager);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.smkf_ikasari, R.drawable.rank1, "SMKF Ikasari Pekanbaru", "Skor 9,5"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank2, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank3, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank4, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank5, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank6, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank7, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank8, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);

        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank9, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);
        rekomendasiModels.add(new RekomendasiModel(R.drawable.sd_al_ulum, R.drawable.rank10, "SD Al Ulum Pekanbaru", "Skor 9,3"));
        rekAd = new RekomendasiAdapter(rekomendasiModels);

        rv_favorit.setAdapter(rekAd);


        recyclerView = (RecyclerView)view.findViewById(R.id.rv_kunjungan);
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        addItemsToRecyclerViewArrayList();
        adapter = new DetailAdapter(source, news, date);
        horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void addItemsToRecyclerViewArrayList() {
        // Tambahkan item ke arraylist
        source = new ArrayList<>();
        source.add(R.drawable.bakti_1);
        source.add(R.drawable.bakti_2);
        source.add(R.drawable.bakti_3);
        source.add(R.drawable.lomba_1);
        source.add(R.drawable.lomba_2);
        source.add(R.drawable.lomba_3);

        news = new ArrayList<>();
        news.add("SMKF Ikasari Mengajarkan Gotong Royong ke SD Al Ulum");
        news.add("Gotong Royong Bersama SMKF Ikasari di SD Jatiasih");
        news.add("SMKF Ikasari Mengawasi Kegiatan Adiwisata SDN 012 Pekanbaru");
        news.add("Tim Nasyid SMKF Ikasari Keluar Sebagai Juara Umum di Acara Islamic Festival");
        news.add("Tim Nasyid SMKF Ikasari, Pede dan Yakin Saja");
        news.add("Acara Berdoa Bersama Siswi SMKF Ikasari");

        date = new ArrayList<>();
        date.add("Dipublikasikan pada tanggal 17 Agustus 2020");
        date.add("Dipublikasikan pada tanggal 9 Agustus 2020");
        date.add("Dipublikasikan pada tanggal 12 Juli 2020");
        date.add("Dipublikasikan pada tanggal 7 Juli 2020");
        date.add("Dipublikasikan pada tanggal 4 April 2020");
        date.add("Dipublikasikan pada tanggal 2 Februari 2020");
    }
}
