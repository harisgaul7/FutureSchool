package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Arrays;

import haris.org.futureschool.R;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.library.DownloadImageTask;
import haris.org.futureschool.model.FasilitasModel;

public class FasilitasAdapter extends RecyclerView.Adapter<FasilitasAdapter.FasilitasViewHolder>{

    private ArrayList<FasilitasModel> fasilitasModels;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    FotoAdapter adapter;
    ArrayList<String> foto;
    LinearLayoutManager horizontalLayout;

    public FasilitasAdapter(ArrayList<FasilitasModel> fasilitasModels) {
        this.fasilitasModels = fasilitasModels;
    }

    @NonNull
    @Override
    public FasilitasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_fasilitas, viewGroup, false);
        return new FasilitasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FasilitasViewHolder fasilitasViewHolder, int i) {
        fasilitasViewHolder.klik_fasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fasilitasViewHolder.klik_fasilitas.getText().equals("Lihat Foto")){
                    fasilitasViewHolder.rv_foto.setVisibility(View.VISIBLE);
                    fasilitasViewHolder.klik_fasilitas.setText("Sembunyikan");
                }
                else if (fasilitasViewHolder.klik_fasilitas.getText().equals("Sembunyikan")){
                    fasilitasViewHolder.rv_foto.setVisibility(View.GONE);
                    fasilitasViewHolder.klik_fasilitas.setText("Lihat Foto");
                }
            }
        });

        recyclerViewLayoutManager = new LinearLayoutManager(fasilitasViewHolder.rv_foto.getContext());
        fasilitasViewHolder.rv_foto.setLayoutManager(recyclerViewLayoutManager);

        foto = new ArrayList<>();

        String slide = fasilitasModels.get(i).getFoto_fasilitas();
        slide = slide.replace("[", "");
        slide = slide.replace("]", "");
        slide = slide.replace(" ", "");
        slide = slide.replace("\"", "");
        String dataFoto[] = slide.split(",");

        foto.addAll(Arrays.asList(dataFoto));

        adapter = new FotoAdapter(foto);
        horizontalLayout = new LinearLayoutManager(fasilitasViewHolder.rv_foto.getContext(), LinearLayoutManager.HORIZONTAL, false);
        fasilitasViewHolder.rv_foto.setLayoutManager(horizontalLayout);
        fasilitasViewHolder.rv_foto.setAdapter(adapter);

        fasilitasViewHolder.nama_fasilitas.setText(fasilitasModels.get(i).getNama_fasilitas());
    }

    @Override
    public int getItemCount() {
        return (fasilitasModels != null) ? fasilitasModels.size() : 0;
    }

    public class FasilitasViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_foto;
        TextView nama_fasilitas, klik_fasilitas;

        public FasilitasViewHolder(View itemView){
            super(itemView);
            rv_foto = itemView.findViewById(R.id.rv_detail_fasilitas);
            nama_fasilitas = itemView.findViewById(R.id.txt_nama_fasilitas);
            klik_fasilitas = itemView.findViewById(R.id.txt_lihat_fasilitas);
        }
    }
}
