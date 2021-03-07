package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.model.RekomendasiModel;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewHolder> {

    private ArrayList<RekomendasiModel> dataRekomendasi;

    public RekomendasiAdapter(ArrayList<RekomendasiModel> dataRekomendasi) {
        this.dataRekomendasi = dataRekomendasi;
    }

    @NonNull
    @Override
    public RekomendasiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_rekomendasi, viewGroup, false);
        return new RekomendasiAdapter.RekomendasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekomendasiViewHolder rekomendasiViewHolder, int i) {
        rekomendasiViewHolder.riv_rekomendasi.setImageResource(dataRekomendasi.get(i).getGambar_sekolah());
        rekomendasiViewHolder.nama.setText(dataRekomendasi.get(i).getNama_sekolah());
        rekomendasiViewHolder.nilai.setText(dataRekomendasi.get(i).getSkor_sekolah());
        rekomendasiViewHolder.peringkat.setImageResource(dataRekomendasi.get(i).getGambar_peringkat());
    }

    @Override
    public int getItemCount() {
        return (dataRekomendasi != null) ? dataRekomendasi.size() : 0;
    }

    public class RekomendasiViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView riv_rekomendasi;
        private TextView nama, nilai;
        private ImageView peringkat;

        public RekomendasiViewHolder(@NonNull View itemView) {
            super(itemView);
            riv_rekomendasi = itemView.findViewById(R.id.riv_sekolah_rekomendasi);
            nama = itemView.findViewById(R.id.tv_sekolah_rekomendasi);
            nilai = itemView.findViewById(R.id.tv_skor_rekomendasi);
            peringkat = itemView.findViewById(R.id.iv_peringkat_sekolah);
        }
    }
}
