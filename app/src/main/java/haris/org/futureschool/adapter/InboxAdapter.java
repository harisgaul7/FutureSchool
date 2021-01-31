package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import haris.org.futureschool.R;
import haris.org.futureschool.model.InboxModel;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder>{

    private ArrayList<InboxModel> dataList;

    public InboxAdapter(ArrayList<InboxModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_inbox, viewGroup, false);
        return new InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder inboxViewHolder, int i) {
        inboxViewHolder.civ_gambar.setImageResource(dataList.get(i).getGambar_akun());
        inboxViewHolder.nama.setText(dataList.get(i).getNama());
        inboxViewHolder.judul.setText(dataList.get(i).getJudul());
        inboxViewHolder.deskripsi.setText(dataList.get(i).getDeskripsi());
        inboxViewHolder.tanggal.setText(dataList.get(i).getTanggal());
        inboxViewHolder.jenis.setImageResource(dataList.get(i).getGambar_jenis());
        inboxViewHolder.keterangan.setText(dataList.get(i).getJenis());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class InboxViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView civ_gambar;
        private TextView nama, judul, deskripsi, tanggal, keterangan;
        private ImageView jenis;

        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);
            civ_gambar = (CircularImageView)itemView.findViewById(R.id.civ_gambar_sekolah);
            nama = (TextView)itemView.findViewById(R.id.txt_nama_inbox);
            judul = (TextView)itemView.findViewById(R.id.txt_judul_inbox);
            deskripsi = (TextView)itemView.findViewById(R.id.txt_deskripsi_sekolah);
            tanggal = (TextView)itemView.findViewById(R.id.txt_tanggal_inbox);
            keterangan = (TextView)itemView.findViewById(R.id.txt_keterangan_inbox);
            jenis = (ImageView)itemView.findViewById(R.id.iv_jenis_inbox);
        }
    }
}
