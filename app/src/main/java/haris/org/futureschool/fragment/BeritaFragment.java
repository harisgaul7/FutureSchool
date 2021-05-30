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

import haris.org.futureschool.R;
import haris.org.futureschool.adapter.BeritaAdapter;
import haris.org.futureschool.model.BeritaModel;

public class BeritaFragment extends Fragment {

    private RecyclerView rv;
    private BeritaAdapter adapter3;
    private ArrayList<BeritaModel> beritaModels3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_berita, container, false);

        rv = view.findViewById(R.id.rv_berita);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        beritaModels3 = new ArrayList<>();

//        beritaModels3.add(new BeritaModel(R.drawable.sd_al_ulum, R.drawable.sd, R.drawable.bakti_1, R.drawable.bakti_2, R.drawable.bakti_3, R.drawable.bakti_3, "SD Al Ulum Pekanbaru", "10 Januari 2020 pukul 08:32", "Kerja Bakti Memeringati Hari Adiwiyata Nasional", "Dalam memeringati Hari Adiwiyata Nasional, SD Al Ulum mengadakan kegiatan kerja bakti membersihkan seluruh halaman sekolah. Kegiatan ini dilakukan tidak hanya ketika hari adiwiyata saja, melainkan rutin setiap sebulan sekali", 3));
//        adapter3 = new BeritaAdapter(beritaModels3);
//        rv.setAdapter(adapter3);
//
//        beritaModels3.add(new BeritaModel(R.drawable.smp_babussalam, R.drawable.smp, R.drawable.lomba_1, R.drawable.lomba_2, R.drawable.lomba_3, R.drawable.bakti_3, "SMP Babussalam Pekanbaru", "13 November 2015 pukul 08:38", "Tim Hadrah SMP Babussalam Juara Ketiga Lomba XSchool Performance", "Tim Hadrah SMP Babussalam kembali memperlihatkan keandalannya. Kali ini seni islami dari anak-anak Pesantren Babussalam Pekanbaru tersebut meraih juara tiga dalam Lomba XSchool Performance dalam rangka peringatan hari jadi Mal Pekanbaru. (baca selanjutnya)", 4));
//        adapter3 = new BeritaAdapter(beritaModels3);
//        rv.setAdapter(adapter3);
//
//        beritaModels3.add(new BeritaModel(R.drawable.smkf_ikasari, R.drawable.sma, R.drawable.olahraga_1, R.drawable.olahraga_2, R.drawable.olahraga_3, R.drawable.bakti_3, "SMK Farmasi Ikasari Pekanbaru", "23 Februari 2014 pukul 16:14", "Cara SMKF Ikasari Berbaur dengan Masyarakat Melalui Gotong Royong", "Melihat kondisi cuaca akhir-akhir ini yang cenderung panas dan berasap, sehingga menimbulkan banyak sampah yang berserakan seperti sampah daun-daunan, dan lainnya, SMK Farmasi Ikasari Pekanbaru pun mengadakan gotong royong bersama, dimana seluruh warga sekolah mulai dari siswa, guru, staff TU sampai Kepala Sekolah turut membersihkan seluruh lingkungan sekolah. (baca selanjutnya)", 1));
//        adapter3 = new BeritaAdapter(beritaModels3);
//        rv.setAdapter(adapter3);

        return view;
    }
}
