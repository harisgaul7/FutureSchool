package haris.org.futureschool.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.FilterActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.library.OnFilterClickListener;
import haris.org.futureschool.model.FilterFasilitasModel;

public class FilterFasilitasAdapter extends RecyclerView.Adapter<FilterFasilitasAdapter.FilterFasilitasViewHolder>{

    private ArrayList<FilterFasilitasModel> filterFasilitasModels;
    private OnFilterClickListener onFilterClickListener;
    private List<String> dataId = new ArrayList<>();

    public FilterFasilitasAdapter(ArrayList<FilterFasilitasModel> filterFasilitasModels, OnFilterClickListener onFilterClickListener) {
        this.filterFasilitasModels = filterFasilitasModels;
        this.onFilterClickListener = onFilterClickListener;
    }

    @NonNull
    @Override
    public FilterFasilitasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_filter, viewGroup, false);
        return new FilterFasilitasAdapter.FilterFasilitasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterFasilitasViewHolder filterFasilitasViewHolder, int i) {
        filterFasilitasViewHolder.id.setText(filterFasilitasModels.get(i).getId_master_fasilitas());
        filterFasilitasViewHolder.nama.setText(filterFasilitasModels.get(i).getNama_fasilitas());
        final String id = filterFasilitasModels.get(i).getId_master_fasilitas();
        filterFasilitasViewHolder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterFasilitasViewHolder.centang.getVisibility() == View.INVISIBLE){
                    filterFasilitasViewHolder.centang.setVisibility(View.VISIBLE);
                    dataId.add(id);
                    onFilterClickListener.onFilterClick(dataId, "fasilitas");
                }
                else if (filterFasilitasViewHolder.centang.getVisibility() == View.VISIBLE){
                    filterFasilitasViewHolder.centang.setVisibility(View.INVISIBLE);
                    dataId.remove(id);
                    onFilterClickListener.onFilterClick(dataId, "fasilitas");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (filterFasilitasModels != null) ? filterFasilitasModels.size() : 0;
    }

    public class FilterFasilitasViewHolder extends RecyclerView.ViewHolder{
        TextView id, nama;
        ImageView centang;

        public FilterFasilitasViewHolder(@NonNull View itemView) {
            super(itemView);
            centang = itemView.findViewById(R.id.iv_centang_filter);
            id = itemView.findViewById(R.id.txt_id_filter);
            nama = itemView.findViewById(R.id.txt_nama_filter);
        }
    }
}
