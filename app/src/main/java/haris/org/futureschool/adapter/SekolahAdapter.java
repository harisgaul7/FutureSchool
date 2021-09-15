package haris.org.futureschool.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;

import haris.org.futureschool.PerbandinganActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.library.DownloadImageTask;
import haris.org.futureschool.SekolahActivity;
import haris.org.futureschool.model.TampilanSekolahModel;

public class SekolahAdapter extends RecyclerView.Adapter<SekolahAdapter.SekolahViewHolder> {

    private ArrayList<TampilanSekolahModel> dataList;
    Context context;

    public SekolahAdapter(ArrayList<TampilanSekolahModel> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public SekolahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_sekolah, parent, false);
        return new SekolahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SekolahViewHolder sekolahViewHolder, int position) {

        new DownloadImageTask((RoundedImageView) sekolahViewHolder.riv_gambar)
                .execute(dataList.get(position).getGambar_sekolah());
        sekolahViewHolder.nama.setText(dataList.get(position).getNama_sekolah());
        sekolahViewHolder.alamat.setText(dataList.get(position).getAlamat_sekolah());
        sekolahViewHolder.deskripsi.setText(dataList.get(position).getDeskripsi_sekolah());
        sekolahViewHolder.jarak.setText("Jarak "+round(dataList.get(position).getJarak(), 2)+" km");

        // Event apabila sekolah di klik
        sekolahViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataList.get(sekolahViewHolder.getAdapterPosition()).getStatus_sekolah().equals("tampilkan")){
                    Intent intent = new Intent(view.getContext(), SekolahActivity.class);

                    intent.putExtra("id", dataList.get(sekolahViewHolder.getAdapterPosition()).getId_sekolah());
                    intent.putExtra("nama", dataList.get(sekolahViewHolder.getAdapterPosition()).getNama_sekolah());
                    intent.putExtra("alamat", dataList.get(sekolahViewHolder.getAdapterPosition()).getAlamat_sekolah());
                    intent.putExtra("akreditasi", dataList.get(sekolahViewHolder.getAdapterPosition()).getAkreditasi_sekolah());
                    intent.putExtra("deskripsi", dataList.get(sekolahViewHolder.getAdapterPosition()).getDeskripsi_sekolah());
                    intent.putExtra("visi_misi", dataList.get(sekolahViewHolder.getAdapterPosition()).getVisi_misi_sekolah());
                    intent.putExtra("kurikulum", dataList.get(sekolahViewHolder.getAdapterPosition()).getKurikulum_sekolah());
                    intent.putExtra("slide", dataList.get(sekolahViewHolder.getAdapterPosition()).getSlide_sekolah());

                    ((Activity) context).startActivityForResult(intent, 1);
                }

                // Menuju tempat perbandingan
                else if (dataList.get(sekolahViewHolder.getAdapterPosition()).getStatus_sekolah().contains("bandingkan")){
                    Intent i = new Intent(view.getContext(), PerbandinganActivity.class);

                    String id[] = dataList.get(sekolahViewHolder.getAdapterPosition()).getStatus_sekolah().split("=");

                    i.putExtra("sekolah_awal", id[1]);
                    i.putExtra("nama_sekolah_awal", id[2]);
                    i.putExtra("sekolah_akhir", dataList.get(sekolahViewHolder.getAdapterPosition()).getId_sekolah());
                    i.putExtra("nama_sekolah_akhir", dataList.get(sekolahViewHolder.getAdapterPosition()).getNama_sekolah());

                    ((Activity) context).startActivityForResult(i, 1);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class SekolahViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView riv_gambar;
        private TextView nama, alamat, deskripsi, jarak;
        private LinearLayout layout;

        public SekolahViewHolder(View itemView){
            super(itemView);
            layout = (LinearLayout)itemView.findViewById(R.id.layout_row_sekolah);
            riv_gambar = (RoundedImageView)itemView.findViewById(R.id.riv_gambar_sekolah);
            nama = (TextView)itemView.findViewById(R.id.txt_nama_sekolah);
            alamat = (TextView)itemView.findViewById(R.id.txt_alamat_sekolah);
            deskripsi = (TextView)itemView.findViewById(R.id.txt_deskripsi_sekolah);
            jarak = (TextView)itemView.findViewById(R.id.txt_jarak_sekolah);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
