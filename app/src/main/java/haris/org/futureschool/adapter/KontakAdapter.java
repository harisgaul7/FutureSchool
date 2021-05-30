package haris.org.futureschool.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.R;
import haris.org.futureschool.model.KontakModel;
import haris.org.futureschool.model.PasangKontakModel;
import lecho.lib.hellocharts.model.Line;

import static android.Manifest.permission.CALL_PHONE;

public class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakViewHolder> {

    private ArrayList<PasangKontakModel> dataKontak;

    public KontakAdapter(ArrayList<PasangKontakModel> dataKontak) {
        this.dataKontak = dataKontak;
    }

    @NonNull
    @Override
    public KontakViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_kontak, viewGroup, false);
        return new KontakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontakViewHolder kontakViewHolder, final int i) {
        kontakViewHolder.iv_kontak.setImageResource(dataKontak.get(i).getGambar());
        kontakViewHolder.nama.setText(dataKontak.get(i).getNama());
        kontakViewHolder.detail.setText(dataKontak.get(i).getDetail());
        kontakViewHolder.detail.setSelected(true);
        final String inti = dataKontak.get(i).getDetail();
        final String aksi = dataKontak.get(i).getAksi();
        if (aksi.equals("telepon")) {
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" +inti));
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (aksi.equals("website")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    if (!inti.contains("http")){
                        intent.setData(Uri.parse("http://"+inti));
                    }
                    else if (inti.contains("http")){
                        intent.setData(Uri.parse(inti));
                    }
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (aksi.equals("email")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { inti });
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Bertanya info sekolah");
                    intent.putExtra(Intent.EXTRA_TEXT, "Assalamu'alaikum, bolehkan saya meminta informasi tentang sekolah ini?");
                    view.getContext().startActivity(Intent.createChooser(intent, ""));
                }
            });
        }
        else if (aksi.equals("facebook")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(inti));
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (aksi.equals("whatsapp")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    try {
                        String url = "https://api.whatsapp.com/send?phone=62"+inti+"&text=" + URLEncoder.encode("Assalamu'alaikum, bolehkan saya meminta informasi tentang sekolah ini?", "UTF-8");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        view.getContext().startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(view.getContext(), "Terjadi kesalahan pada Whatsapp, apakah kamu sudah menginstall Whatsapp?", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if (aksi.equals("telegram")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://telegram.me/"+inti));
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (aksi.equals("instagram")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(inti);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");

                    if (isIntentAvailable(view.getContext(), intent)){
                        view.getContext().startActivity(intent);
                    } else{
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inti)));
                    }
                }
            });
        }
        else if (aksi.equals("twitter")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(inti);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");

                    if (isIntentAvailable(view.getContext(), intent)){
                        view.getContext().startActivity(intent);
                    } else{
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inti)));
                    }
                }
            });
        }
        else if (aksi.equals("youtube")){
            kontakViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(inti);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.youtube.android");

                    if (isIntentAvailable(view.getContext(), intent)){
                        view.getContext().startActivity(intent);
                    } else{
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inti)));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (dataKontak != null) ? dataKontak.size() : 0;
    }

    public class KontakViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView iv_kontak;
        private TextView nama, detail;
        public KontakViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.ll_layout_kontak);
            iv_kontak = (ImageView)itemView.findViewById(R.id.iv_icon_kontak);
            nama = (TextView)itemView.findViewById(R.id.txt_nama_kontak);
            detail = (TextView)itemView.findViewById(R.id.txt_detail_kontak);
        }
    }

    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
