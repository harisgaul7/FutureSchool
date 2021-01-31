package haris.org.futureschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.github.aakira.expandablelayout.Utils;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import haris.org.futureschool.adapter.DetailAdapter;
import haris.org.futureschool.adapter.JurusanAdapter;
import haris.org.futureschool.adapter.PrestasiAdapter;
import haris.org.futureschool.model.ExpandableModel;

public class DetailSekolahActivity extends AppCompatActivity {

    LinearLayout expand;
    CarouselView carouselView;
    boolean saklar = true;
    int slideImages[] = {R.drawable.smkf_ikasari, R.drawable.bakti_1, R.drawable.bakti_2, R.drawable.bakti_3, R.drawable.lomba_1, R.drawable.lomba_2, R.drawable.lomba_3};

    RecyclerView recyclerView;
    ArrayList<Integer> source;
    ArrayList<String> news;
    ArrayList<String> date;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    DetailAdapter adapter;
    LinearLayoutManager HorizontalLayout;
    LinearLayout chat, banding, daftar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_sekolah);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(slideImages[position]);
            }
        };

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(slideImages.length);
        carouselView.setImageListener(imageListener);

        expand = findViewById(R.id.layout_selayang);
        final LayoutParams params = expand.getLayoutParams();


        TextView klik = findViewById(R.id.klik);

        klik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saklar){
                    params.height = LayoutParams.WRAP_CONTENT;
                    expand.setLayoutParams(params);
                    saklar = false;
                }
                else {
                    params.height = 160;
                    expand.setLayoutParams(params);
                    saklar = true;
                }
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rv_detail);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        AddItemsToRecyclerViewArrayList();
        adapter = new DetailAdapter(source, news, date);
        HorizontalLayout = new LinearLayoutManager(DetailSekolahActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_jurusan);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<ExpandableModel> data = new ArrayList<>();
        data.add(new ExpandableModel(
                "Analisis Kesehatan",
                "Adalah sebuah program studi yang mempelajari tentang bagaimana cara menjadi seorang tenaga kesehatan dan ilmuwan berketerampilan tinggi yang akan berkecimpung di sarana kesehatan yang melaksanakan pelayanan pemeriksaan, pengukuran, penetapan, dan pengujian terhadap bahan yang berasal dari manusia atau bahan bukan berasal dari manusia untuk penentuan jenis penyakit, penyebab penyakit, kondisi kesehatan atau faktor-faktor yang dapat berpengaruh pada kesehatan perorangan dan masyarakat. Sarana kesehatan ini berbentuk Laboratorium Kesehatan seperti Laboratorium Patologi Klinik yang memeriksa sampel berupa cairan-cairan tubuh manusia seperti darah, sputum, faeces, urine, liquor cerebro spinalis (cairan otak), dan lain-lain untuk mendapatkan data atau hasil sebagai penegakan diagnosa terhadap suatu penyakit. Cakupannya juga luas meliputi pemeriksaan mikrobiologi (bakteri), parasitologi (fungi, protozoa, cacing), hematologi (sel-sel darah serta plasma), imunologi (antigen, antibodi), kimia klinik (hormon, enzim, glukosa, lipid, protein, elektrolit, dll).",
                R.color.material_red_500,
                R.color.material_red_300,
                Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR)));
        data.add(new ExpandableModel(
                "Pengelolaan Pertanian",
                "Pengolahan Hasil Pertanian mempelajari bagaimana pengolahan hasil tani menjadi suatu produk hingga penjualan produk tersebut. Paket keahlian di jurusan ini adalah Teknologi Pengolahan Hasil Pertanian. Para siswa juga banyak mendapatkan materi soal kewirausahaan dan praktik produksi pengolahan hasil tani. Misalnya, pembuatan kue kering, susu kedelai, dan lain sebagainya. Kemampuan yang dimiliki lulusannya antara lain penerapan prosedur kerja (Good Manufacturing Procedure) serta pengolahan hasil pertanian dengan berbagai teknik seperti fermentasi, suhu tinggi, suhu rendah, teknik pemanasan tidak langsung dan lainnya. Selain itu dipelajari pula bagaimana penerapan kebersihan di sekitar lokasi pengolahan hasil tani, bagaiana menyimpan hasil tani dan olahannya, hingga pengelolaan limbah hasil tani.",
                R.color.material_pink_500,
                R.color.material_pink_300,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));
        data.add(new ExpandableModel(
                "Kimia Analisis",
                "Analis kimia memiliki tugas untuk membuat dan memperbaiki proses dan produk, seperti kosmetik, elektronik, dan obat-obatan. Mereka meneliti untuk menentukan bagaimana proses interaksi antar senyawa kimia. Hal tersebut dapat membantu meningkatkan kehidupan manusia sehari-hari dengan memberikan temuan aplikasi praktis. Dalam penelitiannya, analis kimia menggunakan peralatan laboratorium dan komputer canggih untuk melakukan analisis mereka.",
                R.color.material_purple_500,
                R.color.material_purple_300,
                Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR)));
        data.add(new ExpandableModel(
                "Farmasi",
                "Jurusan Farmasi adalah jurusan yang mempelajari segala hal tentang obat. Mulai dari bahan kimia yang ada di dalamnya, proses pembuatan obat, proses pengemasan obat, fungsi dan kegunaan obat, sampai cara distribusi dan pengelolaan stok obat. Singkatnya, segala hal tentang obat akan dipelajari di sini. Seorang ahli farmasi bertanggung jawab untuk memberikan obat yang tepat untuk berbagai penyakit yang sudah diagnosis Dokter. Bahkan tidak jarang apoteker menyarankan obat yang berbeda atau dosis yang berbeda dari yang diberikan dokter (tentunya setelah berkonsultasi dengan dokter tersebut), karena ahli farmasi seharusnya paham obat lebih banyak daripada Dokter.",
                R.color.material_deep_purple_500,
                R.color.material_deep_purple_300,
                Utils.createInterpolator(Utils.DECELERATE_INTERPOLATOR)));
        data.add(new ExpandableModel(
                "Kimia Industri",
                "Kimia Industri mempelajari tentang masalah dan kegiatan yang berkaitan dengan produksi sebuah barang yang memiliki nilai guna ekonomis, dengan langkah-langkah yang melibatkan peristiwa kimiawi maupun fisis.",
                R.color.material_indigo_500,
                R.color.material_indigo_300,
                Utils.createInterpolator(Utils.FAST_OUT_LINEAR_IN_INTERPOLATOR)));
        recyclerView.setAdapter(new JurusanAdapter(data));

        final RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.rv_prestasi);
        recyclerView2.addItemDecoration(new DividerItemDecoration(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        final List<ExpandableModel> data2 = new ArrayList<>();
        data2.add(new ExpandableModel(
                "Siswa",
                "siswa",
                R.color.material_red_500,
                R.color.material_red_300,
                Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR)));
        data2.add(new ExpandableModel(
                "Sekolah",
                "sekolah",
                R.color.material_pink_500,
                R.color.material_pink_300,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));
        data2.add(new ExpandableModel(
                "Guru",
                "guru",
                R.color.material_purple_500,
                R.color.material_purple_300,
                Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR)));
        recyclerView2.setAdapter(new PrestasiAdapter(data2));

        banding = (LinearLayout) findViewById(R.id.btn_bandingkan);
        banding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(DetailSekolahActivity.this, PerbandinganActivity.class);
                startActivity(go);
            }
        });
    }

    public void AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add(R.drawable.bakti_1);
        source.add(R.drawable.bakti_2);
        source.add(R.drawable.bakti_3);
        source.add(R.drawable.lomba_1);
        source.add(R.drawable.lomba_2);
        source.add(R.drawable.lomba_3);

        news = new ArrayList<>();
        news.add("SMKF Ikasari Mengajarkan Gotong Royong ke SD Al Ulum");
        news.add("Gotong Royong Bersama SMKF Ikasari di SD Jatiasih");
        news.add("SMKF Ikasari Mengawasi Kegiatan Adiwisata SDN 012 Pekanbaru");
        news.add("Tim Nasyid SMKF Ikasari Keluar Sebagai Juara Umum di Acara Islamic Festival");
        news.add("Tim Nasyid SMKF Ikasari, Pede dan Yakin Saja");
        news.add("Acara Berdoa Bersama Siswi SMKF Ikasari");

        date = new ArrayList<>();
        date.add("Dipublikasikan pada tanggal 17 Agustus 2020");
        date.add("Dipublikasikan pada tanggal 9 Agustus 2020");
        date.add("Dipublikasikan pada tanggal 12 Juli 2020");
        date.add("Dipublikasikan pada tanggal 7 Juli 2020");
        date.add("Dipublikasikan pada tanggal 4 April 2020");
        date.add("Dipublikasikan pada tanggal 2 Februari 2020");
    }
}
