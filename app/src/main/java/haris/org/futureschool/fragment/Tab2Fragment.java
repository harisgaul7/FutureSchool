package haris.org.futureschool.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.adapter.FavoritAdapter;
import haris.org.futureschool.adapter.RecentlyAdapter;
import haris.org.futureschool.adapter.SekolahAdapter;
import haris.org.futureschool.model.FavoritModel;
import haris.org.futureschool.model.RecentlyModel;
import haris.org.futureschool.model.SekolahModel;

import static haris.org.futureschool.adapter.SekolahAdapter.round;

public class Tab2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecentlyAdapter recentlyAdapter;
    private ArrayList<RecentlyModel> recentlyModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_recently);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recentlyModels = new ArrayList<>();

        // Kode untuk mengukur 2 jarak
        Location loc1 = new Location("");
        loc1.setLatitude(0.464971);
        loc1.setLongitude(101.347580);

        Location loc2 = new Location("");
        loc2.setLatitude(0.465429);
        loc2.setLongitude(101.373432);

        float jarak = loc1.distanceTo(loc2)/1000.0f;

//        // Isi model harus disesuaikan dengan urutan yang ada di FavoritModel.java
//        recentlyModels.add(new RecentlyModel(R.drawable.smkf_ikasari, 3450000, 450000, "SMKF Ikasari Pekanbaru", "Jl. Mawar No.98, Simpang Baru, Kec.Tampan", "Akreditasi A", round(jarak, 2)));
//        recentlyAdapter = new RecentlyAdapter(recentlyModels);
//        recyclerView.setAdapter(recentlyAdapter);
//
//        recentlyModels.add(new RecentlyModel(R.drawable.sd_al_ulum, 1540000, 150000, "SD Al Ulum Pekanbaru", "Jl. Tuanku Tambusai No.696, Delima, Kec. Tampan", "Akreditasi C", round(jarak, 2)));
//        recentlyAdapter = new RecentlyAdapter(recentlyModels);
//        recyclerView.setAdapter(recentlyAdapter);

        return view;
    }
}