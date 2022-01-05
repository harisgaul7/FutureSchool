package haris.org.futureschool.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.SekolahActivity;
import haris.org.futureschool.library.DownloadImageTask;
import haris.org.futureschool.model.RekomendasiModel;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewHolder> {

    private ArrayList<RekomendasiModel> dataRekomendasi;
    Context context;

    public RekomendasiAdapter(ArrayList<RekomendasiModel> dataRekomendasi) {
        this.dataRekomendasi = dataRekomendasi;
    }

    @NonNull
    @Override
    public RekomendasiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_rekomendasi, viewGroup, false);
        return new RekomendasiAdapter.RekomendasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RekomendasiViewHolder rekomendasiViewHolder, int i) {
        new DownloadImageTask((RoundedImageView) rekomendasiViewHolder.riv_rekomendasi)
                .execute(dataRekomendasi.get(i).getGambar_sekolah());
        rekomendasiViewHolder.nama.setText(dataRekomendasi.get(i).getNama_sekolah());
        rekomendasiViewHolder.nilai.setText(dataRekomendasi.get(i).getSkor_sekolah());
        rekomendasiViewHolder.peringkat.setImageResource(dataRekomendasi.get(i).getGambar_peringkat());

        rekomendasiViewHolder.btn_kunjungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SekolahActivity.class);

                intent.putExtra("id", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getId_sekolah());
                intent.putExtra("nama", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getNama_sekolah());
                intent.putExtra("alamat", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getAlamat_sekolah());
                intent.putExtra("akreditasi", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getAkreditasi_sekolah());
                intent.putExtra("deskripsi", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getDeskripsi_sekolah());
                intent.putExtra("visi_misi", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getVisi_misi_sekolah());
                intent.putExtra("kurikulum", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getKurikulum_sekolah());
                intent.putExtra("slide", dataRekomendasi.get(rekomendasiViewHolder.getAdapterPosition()).getSlide_sekolah());

                ((Activity) context).startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataRekomendasi != null) ? dataRekomendasi.size() : 0;
    }

    public class RekomendasiViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView riv_rekomendasi;
        private TextView nama, nilai, btn_kunjungi;
        private ImageView peringkat;

        public RekomendasiViewHolder(@NonNull View itemView) {
            super(itemView);
            riv_rekomendasi = itemView.findViewById(R.id.riv_sekolah_rekomendasi);
            nama = itemView.findViewById(R.id.tv_sekolah_rekomendasi);
            nilai = itemView.findViewById(R.id.tv_skor_rekomendasi);
            btn_kunjungi = itemView.findViewById(R.id.btn_kunjungi_sekolah);
            peringkat = itemView.findViewById(R.id.iv_peringkat_sekolah);
        }
    }
}
