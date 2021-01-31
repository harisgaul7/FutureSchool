package haris.org.futureschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.adapter.JurusanAdapter;
import haris.org.futureschool.adapter.PerbandinganAdapter;
import haris.org.futureschool.model.ExpandableModel;
import haris.org.futureschool.model.InboxModel;

public class PerbandinganActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PerbandinganAdapter inboxAdapter;
    private ArrayList<InboxModel>inboxModels;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LinearLayoutManager HorizontalLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_perbandingan);

        recyclerView = (RecyclerView)findViewById(R.id.rv_perbandingan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Kepentingan horizontal
        RecyclerViewLayoutManager = new LinearLayoutManager(this);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        inboxModels = new ArrayList<>();

        inboxModels.add(new InboxModel(1, "SMKF Ikasari Pekanbaru", "Penawaran Beasiswa", "Dalam rangka memenuhi niat kami memajukan pendidikan di Indonesia, khususnya bagi calon siswa yang ber", "15 Jun", "Promo", R.drawable.promo));
        inboxAdapter = new PerbandinganAdapter(inboxModels);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(inboxAdapter);

        inboxModels.add(new InboxModel(2, "SD Al Ulum Pekanbaru", "Diskon Biaya Masuk", "Bapak Ibu calon murid yang kami hormati, berkaitan dengan kondisi bencana non alam yang sedang ", "14 Jun", "Promo", R.drawable.promo));
        inboxAdapter = new PerbandinganAdapter(inboxModels);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(inboxAdapter);

        inboxModels.add(new InboxModel(3, "Developer Future School", "Fitur Baru", "Hai calon murid masa depan, kami baru saja meluncurkan fitur baru, lho! Sekarang kamu bisa melakukan pendaftaran melalui fitur daftar ketika melihat sekolah yang kamu inginkan", "13 Jun", "Info", R.drawable.ic_info_black_24dp));
        inboxAdapter = new PerbandinganAdapter(inboxModels);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(inboxAdapter);

        inboxModels.add(new InboxModel(4, "Developer Future School", "Fitur Baru", "Hai calon murid masa depan, kami baru saja meluncurkan fitur baru, lho! Sekarang kamu bisa melakukan pendaftaran melalui fitur daftar ketika melihat sekolah yang kamu inginkan", "13 Jun", "Info", R.drawable.ic_info_black_24dp));
        inboxAdapter = new PerbandinganAdapter(inboxModels);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(inboxAdapter);
    }
}
