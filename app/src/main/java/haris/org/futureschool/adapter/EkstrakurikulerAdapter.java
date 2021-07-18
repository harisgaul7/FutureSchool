package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import haris.org.futureschool.R;
import haris.org.futureschool.model.EkstrakurikulerModel;

public class EkstrakurikulerAdapter extends RecyclerView.Adapter<EkstrakurikulerAdapter.EkstrakurikulerViewHolder>{

    private ArrayList<EkstrakurikulerModel> ekstrakurikulerModels;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    FotoAdapter adapter;
    ArrayList<String> foto;
    LinearLayoutManager horizontalLayout;

    public EkstrakurikulerAdapter(ArrayList<EkstrakurikulerModel> ekstrakurikulerModels) {
        this.ekstrakurikulerModels = ekstrakurikulerModels;
    }

    @NonNull
    @Override
    public EkstrakurikulerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_ekstrakurikuler, viewGroup, false);
        return new EkstrakurikulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EkstrakurikulerViewHolder ekstrakurikulerViewHolder, int i) {
        ekstrakurikulerViewHolder.klik_ekstrakurikuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ekstrakurikulerViewHolder.klik_ekstrakurikuler.getText().equals("Lihat Foto")){
                    ekstrakurikulerViewHolder.rv_foto.setVisibility(View.VISIBLE);
                    ekstrakurikulerViewHolder.klik_ekstrakurikuler.setText("Sembunyikan");
                }
                else if (ekstrakurikulerViewHolder.klik_ekstrakurikuler.getText().equals("Sembunyikan")){
                    ekstrakurikulerViewHolder.rv_foto.setVisibility(View.GONE);
                    ekstrakurikulerViewHolder.klik_ekstrakurikuler.setText("Lihat Foto");
                }
            }
        });

        recyclerViewLayoutManager = new LinearLayoutManager(ekstrakurikulerViewHolder.rv_foto.getContext());
        ekstrakurikulerViewHolder.rv_foto.setLayoutManager(recyclerViewLayoutManager);

        foto = new ArrayList<>();

        String slide = ekstrakurikulerModels.get(i).getGambar_ekstrakurikuler();
        slide = slide.replace("[", "");
        slide = slide.replace("]", "");
        slide = slide.replace(" ", "");
        slide = slide.replace("\"", "");
        String dataFoto[] = slide.split(",");

        foto.addAll(Arrays.asList(dataFoto));

        adapter = new FotoAdapter(foto);
        horizontalLayout = new LinearLayoutManager(ekstrakurikulerViewHolder.rv_foto.getContext(), LinearLayoutManager.HORIZONTAL, false);
        ekstrakurikulerViewHolder.rv_foto.setLayoutManager(horizontalLayout);
        ekstrakurikulerViewHolder.rv_foto.setAdapter(adapter);
        
        ekstrakurikulerViewHolder.nama_ekstrakurikuler.setText(ekstrakurikulerModels.get(i).getNama_ekstrakurikuler());
    }

    @Override
    public int getItemCount() {
        return (ekstrakurikulerModels != null) ? ekstrakurikulerModels.size() : 0;
    }

    public class EkstrakurikulerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_foto;
        TextView nama_ekstrakurikuler, klik_ekstrakurikuler;

        public EkstrakurikulerViewHolder(View itemView){
            super(itemView);
            rv_foto = itemView.findViewById(R.id.rv_detail_ekstrakurikuler);
            nama_ekstrakurikuler = itemView.findViewById(R.id.txt_nama_ekstrakurikuler);
            klik_ekstrakurikuler = itemView.findViewById(R.id.txt_lihat_ekstrakurikuler);
        }
    }
}
