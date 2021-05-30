package haris.org.futureschool.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import haris.org.futureschool.R;
import haris.org.futureschool.SekolahActivity;

public class TabRiwayatSekolah extends Fragment {

    TextView selayang, visi, misi, kurikulum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_riwayat_sekolah, container, false);
        SekolahActivity activity = (SekolahActivity) getActivity();
        HashMap getRiwayat = activity.sendRiwayat();

        selayang = view.findViewById(R.id.txt_isi_selayang);
        visi = view.findViewById(R.id.txt_isi_visi);
        misi = view.findViewById(R.id.txt_isi_misi);
        kurikulum = view.findViewById(R.id.txt_isi_kurikulum);

        String visi_misi[] = getRiwayat.get("visi_misi").toString().split("\\r?\\n");


        String isi_misi = "";
        for (int i = 1; i < visi_misi.length; i++) {
            isi_misi+=visi_misi[i]+System.getProperty("line.separator");
        }

//        String tampil_misi = isi_misi.replace("\\r?\\n", System.getProperty("line.separator")); <- dipakai JIKA isi_misi+=visi_misi[i]+"\n";

        selayang.setText(getRiwayat.get("deskripsi").toString());
        visi.setText(visi_misi[0]);
        misi.setText(isi_misi);
        kurikulum.setText("Kurikulum : "+getRiwayat.get("kurikulum").toString());

        return view;
    }
}