package haris.org.futureschool.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;
import haris.org.futureschool.R;
// The adapter class which
// extends RecyclerView Adapter
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyView> {

    // List with String type
    private List<Integer> list;
    private List<String> list2;
    private List<String> list3;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        // Text View
        ImageView imageView;
        TextView textView, textView2;
        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(View view) {
            super(view);
            // initialise TextView with id
            imageView = (ImageView) view.findViewById(R.id.detail_gambar);
            textView = (TextView) view.findViewById(R.id.detail_judul);
            textView2 = (TextView) view.findViewById(R.id.detail_tanggal);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public DetailAdapter(List<Integer> horizontalList, List<String> judulList, List<String> tanggalList) {
        this.list = horizontalList;
        this.list2 = judulList;
        this.list3 = tanggalList;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item.xml using LayoutInflator
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_detail, parent, false);
        // return itemView
        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        // Set the text of each item of
        // Recycler view with the list items
        holder.imageView.setImageResource(list.get(position));
        holder.textView.setText(list2.get(position));
        holder.textView2.setText(list3.get(position));
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount() {
        return list.size();
    }
}
