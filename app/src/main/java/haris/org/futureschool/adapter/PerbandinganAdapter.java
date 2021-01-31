package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.model.InboxModel;

public class PerbandinganAdapter extends RecyclerView.Adapter<PerbandinganAdapter.InboxViewHolder>{

    private ArrayList<InboxModel> dataList;

    public PerbandinganAdapter(ArrayList<InboxModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(i, viewGroup, false);
        return new InboxViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getGambar_akun() == 1){
            return R.layout.row_perbandingan_selayang;
        }
        else if (dataList.get(position).getGambar_akun() == 2){
            return R.layout.row_perbandingan_fasilitas;
        }
        else if (dataList.get(position).getGambar_akun() == 3){
            return R.layout.row_perbandingan_jadwal;
        }
        else {
            return R.layout.row_perbandingan_biaya;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder inboxViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class InboxViewHolder extends RecyclerView.ViewHolder {


        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
