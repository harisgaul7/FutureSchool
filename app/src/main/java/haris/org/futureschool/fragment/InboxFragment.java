package haris.org.futureschool.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haris.org.futureschool.DetailSekolahActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.adapter.InboxAdapter;
import haris.org.futureschool.model.InboxModel;

public class InboxFragment extends Fragment {

    private RecyclerView recyclerView;
    private InboxAdapter inboxAdapter;
    private ArrayList<InboxModel>inboxModels;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LinearLayoutManager HorizontalLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_inbox, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_inbox);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Kepentingan horizontal
//        RecyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
//        HorizontalLayout = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

//        inboxModels = new ArrayList<>();
//
//        inboxModels.add(new InboxModel(R.drawable.smkf_ikasari, "SMKF Ikasari Pekanbaru", "Penawaran Beasiswa", "Dalam rangka memenuhi niat kami memajukan pendidikan di Indonesia, khususnya bagi calon siswa yang ber", "15 Jun", "Promo", R.drawable.promo));
//        inboxAdapter = new InboxAdapter(inboxModels);
////        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
////        recyclerView.setLayoutManager(HorizontalLayout);
//        recyclerView.setAdapter(inboxAdapter);
//
//        inboxModels.add(new InboxModel(R.drawable.sd_al_ulum, "SD Al Ulum Pekanbaru", "Diskon Biaya Masuk", "Bapak Ibu calon murid yang kami hormati, berkaitan dengan kondisi bencana non alam yang sedang ", "14 Jun", "Promo", R.drawable.promo));
//        inboxAdapter = new InboxAdapter(inboxModels);
////        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
////        recyclerView.setLayoutManager(HorizontalLayout);
//        recyclerView.setAdapter(inboxAdapter);
//
//        inboxModels.add(new InboxModel(R.drawable.education, "Developer Future School", "Fitur Baru", "Hai calon murid masa depan, kami baru saja meluncurkan fitur baru, lho! Sekarang kamu bisa melakukan pendaftaran melalui fitur daftar ketika melihat sekolah yang kamu inginkan", "13 Jun", "Info", R.drawable.ic_info_black_24dp));
//        inboxAdapter = new InboxAdapter(inboxModels);
////        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
////        recyclerView.setLayoutManager(HorizontalLayout);
//        recyclerView.setAdapter(inboxAdapter);

        return view;
    }
}
