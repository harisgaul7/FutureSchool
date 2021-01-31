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
import haris.org.futureschool.model.RecentlyModel;

public class RecentlyAdapter extends RecyclerView.Adapter<RecentlyAdapter.RecentlyViewHolder>{

    private ArrayList<RecentlyModel> listRecently;

    public RecentlyAdapter(ArrayList<RecentlyModel>listRecently){
        this.listRecently = listRecently;
    }

    @NonNull
    @Override
    public RecentlyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_recently, viewGroup, false);
        return new RecentlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewHolder recentlyViewHolder, int position) {
        recentlyViewHolder.riv_recently.setImageResource(listRecently.get(position).getGambarRecently());
        recentlyViewHolder.nama_recently.setText(listRecently.get(position).getNama());
        recentlyViewHolder.alamat_recently.setText(listRecently.get(position).getAlamat());
        recentlyViewHolder.pendaftaran_recently.setText("Pendaftaran : Rp. "+convert(listRecently.get(position).getPendaftaran()));
        recentlyViewHolder.bulanan_recently.setText("SPP : Rp. "+convert(listRecently.get(position).getBulanan()));
        recentlyViewHolder.jarak_recently.setText("Jarak "+listRecently.get(position).getJarak()+" km");
        recentlyViewHolder.akreditasi_recently.setText(listRecently.get(position).getAkreditasi());
    }

    @Override
    public int getItemCount() {
        return (listRecently != null) ? listRecently.size() : 0;
    }

    public class RecentlyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView riv_recently;
        TextView nama_recently, alamat_recently, pendaftaran_recently, bulanan_recently, jarak_recently, akreditasi_recently;

        public RecentlyViewHolder(View itemView){
            super(itemView);
            riv_recently = itemView.findViewById(R.id.riv_gambar_recently);
            nama_recently = itemView.findViewById(R.id.txt_nama_recently);
            alamat_recently = itemView.findViewById(R.id.txt_alamat_recently);
            pendaftaran_recently = itemView.findViewById(R.id.txt_pendaftaran_recently);
            bulanan_recently = itemView.findViewById(R.id.txt_spp_recently);
            jarak_recently = itemView.findViewById(R.id.txt_jarak_recently);
            akreditasi_recently = itemView.findViewById(R.id.txt_akreditasi_recently);
        }
    }

    private String convert (int money){
        String result = "";
        int x = 0;
        for (int i = String.valueOf(money).length()-1; i >= 0; i--){
            result+=String.valueOf(money).charAt(x);
            if (i%3==0){
                result+=".";
            }
            x++;
        }
        String hasil = result.substring(0, result.length()-1);
        return hasil;
    }

}
