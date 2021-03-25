package haris.org.futureschool.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.adapter.SekolahAdapter;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.library.DownloadImageTask;
import haris.org.futureschool.model.SekolahModel;

public class SekolahFragment extends Fragment {

    private RecyclerView rv;
    private SekolahAdapter adapter;
    private ArrayList<SekolahModel> sekolahModelArrayList;

    Spinner spin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_sekolah, container, false);
        final EditText cari = (EditText)view.findViewById(R.id.et_pencarian);
        final ImageView loop = (ImageView) view.findViewById(R.id.iv_loop);
        loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kode untuk mengukur 2 jarak
                Location loc1 = new Location("");
                loc1.setLatitude(0.464971);
                loc1.setLongitude(101.347580);

                Location loc2 = new Location("");
                loc2.setLatitude(0.465429);
                loc2.setLongitude(101.373432);

                float jarak = loc1.distanceTo(loc2);

                // Ikasari : 0.478355, 101.370420

                Toast.makeText(getActivity(), "Jarak = "+jarak+" m", Toast.LENGTH_SHORT).show();
            }
        });

        // Kode untuk mengukur 2 jarak
        Location loc1 = new Location("");
        loc1.setLatitude(0.464971);
        loc1.setLongitude(101.347580);

        Location loc2 = new Location("");
        loc2.setLatitude(0.465429);
        loc2.setLongitude(101.373432);

        float jarak = loc1.distanceTo(loc2)/1000.0f;

        rv = (RecyclerView)view.findViewById(R.id.rv_sekolah);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        sekolahModelArrayList = new ArrayList<>();


        String url = new BaseUrl().BASE_URL;



        sekolahModelArrayList.add(new SekolahModel(url+"future_picture/slider/lomba_1.jpg", "SMKF Ikasari Pekanbaru", "Jl. Mawar No.98, Simpang Baru, Kec.Tampan", "''SMK Farmasi Ikasari Yayasan UR Pekanbaru merupakan salah satu Institusi pendidikan Tenaga Kesehatan Tingkat Menengah yang saat ini berkembang menjadi Sekolah Menengah Kejuruan yang sudah berdiri sejak tahun 1962.''", jarak));
        adapter = new SekolahAdapter(sekolahModelArrayList);
        rv.setAdapter(adapter);

        sekolahModelArrayList.add(new SekolahModel(url+"future_picture/slider/lomba_2.jpg", "SD Al Ulum Pekanbaru", "Jl. Tuanku Tambusai No.696, Delima, Kec. Tampan", "''SD Al Ulum Islamic School adalah sekolah swasta yang terletak di Pekanbaru, Riau. SD ini berbasis Agama Islam sebagai pengangan utama kurikulum pendidikannya.''", jarak));
        adapter = new SekolahAdapter(sekolahModelArrayList);
        rv.setAdapter(adapter);

        aturSpinner(view);
        return view;
    }

    private void aturSpinner(View vw){
        spin = (Spinner)vw.findViewById(R.id.spin_tingkat_sekolah);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tingkat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), "Anda memilih = "+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
