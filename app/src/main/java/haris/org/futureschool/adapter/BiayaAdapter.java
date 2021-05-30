package haris.org.futureschool.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;

import haris.org.futureschool.R;
import haris.org.futureschool.model.BiayaModel;

public class BiayaAdapter extends RecyclerView.Adapter<BiayaAdapter.BiayaViewHolder>{

    private ArrayList<BiayaModel> biayaModels;

    public BiayaAdapter(ArrayList<BiayaModel> biayaModels) {
        this.biayaModels = biayaModels;
    }

    @NonNull
    @Override
    public BiayaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_biaya, viewGroup, false);
        return new BiayaAdapter.BiayaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BiayaViewHolder biayaViewHolder, int i) {
        biayaViewHolder.detail.setText(biayaModels.get(i).getNama_biaya());

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);

        //nf.format(total1).trim() -> buat 3 titik tiap ribuan
        //string..substring(0, string.length() - 3) -> buat ilangin ,00 tiap format decimal

        biayaViewHolder.nominal.setText(nf.format(Integer.parseInt(biayaModels.get(i).getJumlah_biaya())).trim().substring(0, nf.format(Integer.parseInt(biayaModels.get(i).getJumlah_biaya())).trim().length() - 3));
    }

    @Override
    public int getItemCount() {
        return (biayaModels != null) ? biayaModels.size() : 0;
    }

    public class BiayaViewHolder extends RecyclerView.ViewHolder {
        TextView detail, nominal;

        public BiayaViewHolder(View itemView){
            super(itemView);
            detail = itemView.findViewById(R.id.txt_detail_biaya);
            nominal = itemView.findViewById(R.id.txt_nominal_biaya);
        }
    }
}
