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
import haris.org.futureschool.adapter.DetailAdapter;
import haris.org.futureschool.adapter.FotoAdapter;
import haris.org.futureschool.database.BaseUrl;

public class TabGaleri extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    FotoAdapter adapter;
    LinearLayoutManager horizontalLayout;
    ArrayList<String> foto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_galeri, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_kunjungan);
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        addItemsToRecyclerViewArrayList();
        adapter = new FotoAdapter(foto);
        horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void addItemsToRecyclerViewArrayList() {
        String url = new BaseUrl().BASE_URL;
        foto = new ArrayList<>();
        foto.add(url+"future-school/future_picture/sma_as_shofa/sma_as_shofa.jpg");
        foto.add(url+"future-school/future_picture/sma_as_shofa/sma_as_shofa.jpg");
    }
}