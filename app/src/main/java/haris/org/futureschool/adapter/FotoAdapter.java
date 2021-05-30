package haris.org.futureschool.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import haris.org.futureschool.R;
import haris.org.futureschool.database.BaseUrl;
import haris.org.futureschool.library.DownloadImageTask;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.MyView> {

    private List<String> fotoDetail;

    public class MyView extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyView(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.iv_detail_fasilitas_ekstrakulikuler);
        }
    }

    public FotoAdapter(List<String> urlFoto) {
        this.fotoDetail = urlFoto;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_foto, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        String url = new BaseUrl().GAMBAR_URL;
        new DownloadImageTask((ImageView) holder.imageView)
                .execute(url+fotoDetail.get(position));
    }

    @Override
    public int getItemCount() {
        return fotoDetail.size();
    }
}
