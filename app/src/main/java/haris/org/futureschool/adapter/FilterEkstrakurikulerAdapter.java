package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.R;
import haris.org.futureschool.library.OnFilterClickListener;
import haris.org.futureschool.model.FilterEkstrakurikulerModel;

public class FilterEkstrakurikulerAdapter extends RecyclerView.Adapter<FilterEkstrakurikulerAdapter.FilterEkstrakurikulerViewHolder> {

    private ArrayList<FilterEkstrakurikulerModel> filterEkstrakurikulerModels;
    private OnFilterClickListener onFilterClickListener;
    private List<String> dataId = new ArrayList<>();

    public FilterEkstrakurikulerAdapter(ArrayList<FilterEkstrakurikulerModel> filterEkstrakurikulerModels, OnFilterClickListener onFilterClickListener) {
        this.filterEkstrakurikulerModels = filterEkstrakurikulerModels;
        this.onFilterClickListener = onFilterClickListener;
    }

    @NonNull
    @Override
    public FilterEkstrakurikulerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_filter, viewGroup, false);
        return new FilterEkstrakurikulerAdapter.FilterEkstrakurikulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterEkstrakurikulerViewHolder filterEkstrakurikulerViewHolder, int i) {
        filterEkstrakurikulerViewHolder.id.setText(filterEkstrakurikulerModels.get(i).getId_master_ekstrakurikuler());
        filterEkstrakurikulerViewHolder.nama.setText(filterEkstrakurikulerModels.get(i).getNama_ekstrakurikuler());
        final String id = filterEkstrakurikulerModels.get(i).getId_master_ekstrakurikuler();
        filterEkstrakurikulerViewHolder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterEkstrakurikulerViewHolder.centang.getVisibility() == View.INVISIBLE){
                    filterEkstrakurikulerViewHolder.centang.setVisibility(View.VISIBLE);
                    dataId.add(id);
                    onFilterClickListener.onFilterClick(dataId, "ekstrakurikuler");
                }
                else if (filterEkstrakurikulerViewHolder.centang.getVisibility() == View.VISIBLE){
                    filterEkstrakurikulerViewHolder.centang.setVisibility(View.INVISIBLE);
                    dataId.remove(id);
                    onFilterClickListener.onFilterClick(dataId, "ekstrakurikuler");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (filterEkstrakurikulerModels != null) ? filterEkstrakurikulerModels.size() : 0;
    }

    public class FilterEkstrakurikulerViewHolder extends RecyclerView.ViewHolder{
        TextView id, nama;
        ImageView centang;

        public FilterEkstrakurikulerViewHolder(@NonNull View itemView) {
            super(itemView);
            centang = itemView.findViewById(R.id.iv_centang_filter);
            id = itemView.findViewById(R.id.txt_id_filter);
            nama = itemView.findViewById(R.id.txt_nama_filter);
        }
    }
}
