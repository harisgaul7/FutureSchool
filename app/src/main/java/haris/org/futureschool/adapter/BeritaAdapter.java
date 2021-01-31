package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.model.BeritaModel;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>{

    private ArrayList<BeritaModel> listBerita;

    public BeritaAdapter(ArrayList<BeritaModel> listBerita) {
        this.listBerita = listBerita;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Pakai inflate viewType instead R.layout.dkk untuk mengembalikan tampilan ke layout dari method getItemViewType
        View view = layoutInflater.inflate(viewType, parent, false);
        return new BeritaViewHolder(view);
    }

    // Ini digunakan untuk mengembalikan tampilan sesuai versi
    @Override
    public int getItemViewType(int position) {
        if (listBerita.get(position).getVersi() == 1){
            return R.layout.row_berita1;
        }
        else if (listBerita.get(position).getVersi() == 2){
            return R.layout.row_berita2;
        }
        else if (listBerita.get(position).getVersi() == 3){
            return R.layout.row_berita3;
        }
        else {
            return R.layout.row_berita4;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder beritaViewHolder, int position) {
        beritaViewHolder.civ_berita.setImageResource(listBerita.get(position).getGambarProfil());
        beritaViewHolder.tingkat.setImageResource(listBerita.get(position).getGambarTingkat());
        if (listBerita.get(position).getVersi() == 1){
            beritaViewHolder.gambar1.setImageResource(listBerita.get(position).getGambar1());
        }
        else if (listBerita.get(position).getVersi() == 2){
            beritaViewHolder.gambar1.setImageResource(listBerita.get(position).getGambar1());
            beritaViewHolder.gambar2.setImageResource(listBerita.get(position).getGambar2());
        }
        else if (listBerita.get(position).getVersi() == 3){
            beritaViewHolder.gambar1.setImageResource(listBerita.get(position).getGambar1());
            beritaViewHolder.gambar2.setImageResource(listBerita.get(position).getGambar2());
            beritaViewHolder.gambar3.setImageResource(listBerita.get(position).getGambar3());
        }
        else if (listBerita.get(position).getVersi() == 4){
            beritaViewHolder.gambar1.setImageResource(listBerita.get(position).getGambar1());
            beritaViewHolder.gambar2.setImageResource(listBerita.get(position).getGambar2());
            beritaViewHolder.gambar4.setBackgroundResource(listBerita.get(position).getGambar3());
        }
        beritaViewHolder.sekolah.setText(listBerita.get(position).getSekolah());
        beritaViewHolder.waktu.setText(listBerita.get(position).getWaktu());
        beritaViewHolder.judul.setText(listBerita.get(position).getJudul());
        beritaViewHolder.preview.setText(listBerita.get(position).getPreview());
    }

    @Override
    public int getItemCount() {
        return (listBerita != null) ? listBerita.size() : 0;
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        CircularImageView civ_berita;
        ImageView tingkat, gambar1, gambar2, gambar3;
        LinearLayout gambar4;
        TextView sekolah, waktu, judul, preview;

        public BeritaViewHolder(View itemView){
            super(itemView);
            civ_berita = itemView.findViewById(R.id.civ_gambar_berita);
            tingkat = itemView.findViewById(R.id.iv_tingkat_sekolah);
            gambar1 = itemView.findViewById(R.id.iv_berita_1);
            gambar2 = itemView.findViewById(R.id.iv_berita_2);
            gambar3 = itemView.findViewById(R.id.iv_berita_3);
            gambar4 = itemView.findViewById(R.id.iv_berita_4);
            sekolah = itemView.findViewById(R.id.txt_sekolah_berita);
            waktu = itemView.findViewById(R.id.txt_waktu_berita);
            judul = itemView.findViewById(R.id.txt_judul_berita);
            preview = itemView.findViewById(R.id.txt_preview_berita);
        }
    }
}
