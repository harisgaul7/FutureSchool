package haris.org.futureschool.fragment;
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
import haris.org.futureschool.model.FavoritModel;

public class Tab1Fragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritAdapter favoritAdapter;
    private ArrayList<FavoritModel> favoritModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_favorit);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        favoritModels = new ArrayList<>();
        // Isi model harus disesuaikan dengan urutan yang ada di FavoritModel.java
        favoritModels.add(new FavoritModel(R.drawable.smkf_ikasari, 151, 4,67, "SMKF Ikasari Pekanbaru"));
        favoritAdapter = new FavoritAdapter(favoritModels);
        recyclerView.setAdapter(favoritAdapter);

        favoritModels.add(new FavoritModel(R.drawable.sd_al_ulum, 34, 3, 77, "SD Al Ulum Pekanbaru"));
        favoritAdapter = new FavoritAdapter(favoritModels);
        recyclerView.setAdapter(favoritAdapter);

        favoritModels.add(new FavoritModel(R.drawable.smp_babussalam, 87, 5, 89, "SMP Babussalam Pekanbaru"));
        favoritAdapter = new FavoritAdapter(favoritModels);
        recyclerView.setAdapter(favoritAdapter);

        return view;
    }
}