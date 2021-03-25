package haris.org.futureschool.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;

import haris.org.futureschool.DetailSekolahActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.library.DownloadImageTask;
import haris.org.futureschool.model.SekolahModel;

public class SekolahAdapter extends RecyclerView.Adapter<SekolahAdapter.SekolahViewHolder> {

    private ArrayList<SekolahModel> dataList;
    private Context context;

    public SekolahAdapter(ArrayList<SekolahModel> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public SekolahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_sekolah, parent, false);
        return new SekolahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SekolahViewHolder sekolahViewHolder, int position) {

        new DownloadImageTask((RoundedImageView) sekolahViewHolder.riv_gambar)
                .execute(dataList.get(position).getGambar());
        sekolahViewHolder.nama.setText(dataList.get(position).getNama());
        sekolahViewHolder.alamat.setText(dataList.get(position).getAlamat());
        sekolahViewHolder.deskripsi.setText(dataList.get(position).getDeskripsi());
        sekolahViewHolder.jarak.setText("Jarak "+round(dataList.get(position).getJarak(), 2)+" km");

        // Event apabila sekolah di klik
        sekolahViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailSekolahActivity.class);
                view.getContext().startActivity(intent);
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
