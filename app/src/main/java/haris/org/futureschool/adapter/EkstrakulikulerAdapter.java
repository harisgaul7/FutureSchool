package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import haris.org.futureschool.R;
import haris.org.futureschool.library.DownloadImageTask;
import haris.org.futureschool.model.EkstrakulikulerModel;

public class EkstrakulikulerAdapter extends RecyclerView.Adapter<EkstrakulikulerAdapter.EkstrakulikulerViewHolder>{

    private ArrayList<EkstrakulikulerModel> ekstrakulikulerModels;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    FotoAdapter adapter;
    ArrayList<String> foto;
    LinearLayoutManager horizontalLayout;

    public EkstrakulikulerAdapter(ArrayList<EkstrakulikulerModel> ekstrakulikulerModels) {
        this.ekstrakulikulerModels = ekstrakulikulerModels;
    }

    @NonNull
    @Override
    public EkstrakulikulerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_ekstrakulikuler, viewGroup, false);
        return new EkstrakulikulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EkstrakulikulerViewHolder ekstrakulikulerViewHolder, int i) {
        ekstrakulikulerViewHolder.klik_ekstrakulikuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ekstrakulikulerViewHolder.klik_ekstrakulikuler.getText().equals("Lihat Foto")){
                    ekstrakulikulerViewHolder.rv_foto.setVisibility(View.VISIBLE);
                    ekstrakulikulerViewHolder.klik_ekstrakulikuler.setText("Sembunyikan");
                }
                else if (ekstrakulikulerViewHolder.klik_ekstrakulikuler.getText().equals("Sembunyikan")){
                    ekstrakulikulerViewHolder.rv_foto.setVisibility(View.GONE);
                    ekstrakulikulerViewHolder.klik_ekstrakulikuler.setText("Lihat Foto");
                }
            }
        });

        recyclerViewLayoutManager = new LinearLayoutManager(ekstrakulikulerViewHolder.rv_foto.getContext());
        ekstrakulikulerViewHolder.rv_foto.setLayoutManager(recyclerViewLayoutManager);

        foto = new ArrayList<>();

        String slide = ekstrakulikulerModels.get(i).getGambar_ekstrakurikuler();
        slide = slide.replace("[", "");
        slide = slide.replace("]", "");
        slide = slide.replace(" ", "");
        slide = slide.replace("\"", "");
        String dataFoto[] = slide.split(",");

        foto.addAll(Arrays.asList(dataFoto));

        adapter = new FotoAdapter(foto);
        horizontalLayout = new LinearLayoutManager(ekstrakulikulerViewHolder.rv_foto.getContext(), LinearLayoutManager.HORIZONTAL, false);
        ekstrakulikulerViewHolder.rv_foto.setLayoutManager(horizontalLayout);
        ekstrakulikulerViewHolder.rv_foto.setAdapter(adapter);
        
        ekstrakulikulerViewHolder.nama_ekstrakulikuler.setText(ekstrakulikulerModels.get(i).getNama_ekstrakurikuler());
    }

    @Override
    public int getItemCount() {
        return (ekstrakulikulerModels != null) ? ekstrakulikulerModels.size() : 0;
    }

    public class EkstrakulikulerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_foto;
        TextView nama_ekstrakulikuler, klik_ekstrakulikuler;

        public EkstrakulikulerViewHolder(View itemView){
            super(itemView);
            rv_foto = itemView.findViewById(R.id.rv_detail_ekstrakulikuler);
            nama_ekstrakulikuler = itemView.findViewById(R.id.txt_nama_ekstrakulikuler);
            klik_ekstrakulikuler = itemView.findViewById(R.id.txt_lihat_ekstrakulikuler);
        }
    }
}
