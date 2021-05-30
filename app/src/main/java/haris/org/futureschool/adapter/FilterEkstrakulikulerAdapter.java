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
import haris.org.futureschool.model.FilterEkstrakulikulerModel;

public class FilterEkstrakulikulerAdapter extends RecyclerView.Adapter<FilterEkstrakulikulerAdapter.FilterEkstrakulikulerViewHolder> {

    private ArrayList<FilterEkstrakulikulerModel> filterEkstrakulikulerModels;
    private OnFilterClickListener onFilterClickListener;
    private List<String> dataId = new ArrayList<>();

    public FilterEkstrakulikulerAdapter(ArrayList<FilterEkstrakulikulerModel> filterEkstrakulikulerModels, OnFilterClickListener onFilterClickListener) {
        this.filterEkstrakulikulerModels = filterEkstrakulikulerModels;
        this.onFilterClickListener = onFilterClickListener;
    }

    @NonNull
    @Override
    public FilterEkstrakulikulerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_filter, viewGroup, false);
        return new FilterEkstrakulikulerAdapter.FilterEkstrakulikulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterEkstrakulikulerViewHolder filterEkstrakulikulerViewHolder, int i) {
        filterEkstrakulikulerViewHolder.id.setText(filterEkstrakulikulerModels.get(i).getId_master_ekstrakurikuler());
        filterEkstrakulikulerViewHolder.nama.setText(filterEkstrakulikulerModels.get(i).getNama_ekstrakurikuler());
        final String id = filterEkstrakulikulerModels.get(i).getId_master_ekstrakurikuler();
        filterEkstrakulikulerViewHolder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterEkstrakulikulerViewHolder.centang.getVisibility() == View.INVISIBLE){
                    filterEkstrakulikulerViewHolder.centang.setVisibility(View.VISIBLE);
                    dataId.add(id);
                    onFilterClickListener.onFilterClick(dataId, "ekstrakulikuler");
                }
                else if (filterEkstrakulikulerViewHolder.centang.getVisibility() == View.VISIBLE){
                    filterEkstrakulikulerViewHolder.centang.setVisibility(View.INVISIBLE);
                    dataId.remove(id);
                    onFilterClickListener.onFilterClick(dataId, "ekstrakulikuler");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (filterEkstrakulikulerModels != null) ? filterEkstrakulikulerModels.size() : 0;
    }

    public class FilterEkstrakulikulerViewHolder extends RecyclerView.ViewHolder{
        TextView id, nama;
        ImageView centang;

        public FilterEkstrakulikulerViewHolder(@NonNull View itemView) {
            super(itemView);
            centang = itemView.findViewById(R.id.iv_centang_filter);
            id = itemView.findViewById(R.id.txt_id_filter);
            nama = itemView.findViewById(R.id.txt_nama_filter);
        }
    }
}
