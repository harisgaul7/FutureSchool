package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.model.FavoritModel;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>{

    private ArrayList<FavoritModel> listFavorit;

    public FavoritAdapter(ArrayList<FavoritModel>listFavorit){
        this.listFavorit = listFavorit;
    }

    @NonNull
    @Override
    public FavoritViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_favorit, viewGroup, false);
        return new FavoritViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritViewHolder favoritViewHolder, int position) {
        favoritViewHolder.riv_sekolah.setImageResource(listFavorit.get(position).getGambarSekolah());
        favoritViewHolder.nama_sekolah.setText(listFavorit.get(position).getNama());
        favoritViewHolder.kuota_sekolah.setText("Kuota : "+listFavorit.get(position).getKuota()+" Siswa/i");
        favoritViewHolder.beasiswa_sekolah.setText(listFavorit.get(position).getRating()+" Beasiswa tersisa!");
        favoritViewHolder.cp_favorit.setProgress(listFavorit.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return (listFavorit != null) ? listFavorit.size() : 0;
    }

    public class FavoritViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView riv_sekolah;
        TextView nama_sekolah, kuota_sekolah, beasiswa_sekolah;
        ArcProgress cp_favorit;

        public FavoritViewHolder(View itemView){
            super(itemView);
            riv_sekolah = itemView.findViewById(R.id.riv_gambar_sekolah_favorit);
            nama_sekolah = itemView.findViewById(R.id.txt_nama_sekolah_favorit);
            kuota_sekolah = itemView.findViewById(R.id.txt_kuota_sekolah_favorit);
            beasiswa_sekolah = itemView.findViewById(R.id.txt_beasiswa_sekolah_favorit);
            cp_favorit = itemView.findViewById(R.id.progress_favorit);
        }
    }

}
