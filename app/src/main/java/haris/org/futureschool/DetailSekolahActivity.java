package haris.org.futureschool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.github.aakira.expandablelayout.Utils;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import haris.org.futureschool.adapter.JurusanAdapter;
import haris.org.futureschool.adapter.PrestasiAdapter;
import haris.org.futureschool.library.DividerItemDecoration;
import haris.org.futureschool.model.ExpandableModel;
import haris.org.futureschool.model.GuruModel;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DetailSekolahActivity extends AppCompatActivity {

    // Modul slide image
    CarouselView carouselView;
    int slideImages[];

    // Modul favorit
    MaterialFavoriteButton mfbSekolah;

    // Modul selayang pandang
    LinearLayout selayangPandang;
    TextView klikJudulSelayang;
    boolean saklarKlikJudul = true;

    // Modul kalender sekolah
    TextView tahunKalender, judulKalender;
    ArrayList<String> bulanKalender;
    Spinner spinBulan;
    int tahun = 0, setBulan, bulan, awalHari, jumlahHari;
    TextView no11, no12, no13, no14, no15, no16, no17,
            no21, no22, no23, no24, no25, no26, no27,
            no31, no32, no33, no34, no35, no36, no37,
            no41, no42, no43, no44, no45, no46, no47,
            no51, no52, no53, no54, no55, no56, no57,
            no61, no62, no63, no64, no65, no66, no67;

    // Modul komposisi guru
    TableLayout tabelGuru;
    TextView no, namaGuru, jenisKelaminGuru, gelarGuru, mapelGuru;

    // Modul Persebaran Alumni
    Spinner spinAlumni;
    ArrayList<Integer> tahunAlumni;
    PieChartView pieChartView;

    // Modul akses sekolah
    LinearLayout chat, banding, daftar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_sekolah);

        modulSlideGambar();
        modulSekolahFavorit();
        modulSelayangPandang();
        modulJurusanSekolah();
        modulKalenderPendidikan();
        modulPrestasiSekolah();
        modulKomposisiGuru();
        modulPersebaranAlumni();
        modulAksesSekolah();
    }

    private void modulSlideGambar(){
        slideImages = new int[7];
        slideImages[0] = R.drawable.smkf_ikasari;
        slideImages[1] = R.drawable.bakti_1;
        slideImages[2] = R.drawable.bakti_2;
        slideImages[3] = R.drawable.bakti_3;
        slideImages[4] = R.drawable.lomba_1;
        slideImages[5] = R.drawable.lomba_2;
        slideImages[6] = R.drawable.lomba_3;

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(slideImages[position]);
            }
        };
        carouselView = findViewById(R.id.carousel_view);
        carouselView.setPageCount(slideImages.length);
        carouselView.setImageListener(imageListener);
    }

    private void modulSekolahFavorit(){
        mfbSekolah = findViewById(R.id.mfb_sekolah_favorit);
        mfbSekolah.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                if (mfbSekolah.isFavorite()){
                    Toast.makeText(DetailSekolahActivity.this, "Sekolah telah ditambahkan ke daftar favorit!", Toast.LENGTH_SHORT).show();
                }
                else if (!mfbSekolah.isFavorite()){
                    Toast.makeText(DetailSekolahActivity.this, "Sekolah telah dihapus dari daftar favorit!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void modulSelayangPandang(){
        selayangPandang = findViewById(R.id.layout_selayang);
        final LayoutParams params = selayangPandang.getLayoutParams();
        klikJudulSelayang = findViewById(R.id.klik);

        klikJudulSelayang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saklarKlikJudul){
                    params.height = LayoutParams.WRAP_CONTENT;
                    selayangPandang.setLayoutParams(params);
                    saklarKlikJudul = false;
                }
                else {
                    params.height = 160;
                    selayangPandang.setLayoutParams(params);
                    saklarKlikJudul = true;
                }
            }
        });
    }

    private void modulJurusanSekolah(){
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
    }

    private void modulPrestasiSekolah(){
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
    }

    private void modulKomposisiGuru(){
        tabelGuru = findViewById(R.id.tabel_daftar_guru);
        cleanTable(tabelGuru);

        ArrayList<GuruModel> isiGuru = new ArrayList<>();
        isiGuru.add(new GuruModel("HARIO PERDANA, S.Pd", "L", "S1", "Seni Budaya"));
        isiGuru.add(new GuruModel("LAILATUL FARHANI, S.Pd", "P", "S1", "Biologi"));
        isiGuru.add(new GuruModel("PUTRI KARTIKA SARI, S.Pd", "P", "S1", "Bahasa Inggris"));
        isiGuru.add(new GuruModel("SURYATRI NAWANG SARI, S.Pd", "P", "S1", "Bahasa Indonesia"));
        isiGuru.add(new GuruModel("DWI PUJIASTUTI S.Pd", "L", "S1", "Geografi"));
        isiGuru.add(new GuruModel("SHINTA OLIFIA, SE", "L", "S1", "Ekonomi"));
        isiGuru.add(new GuruModel("SANDI JUMAIGI, S.Pd", "L", "S1", "Sosiologi"));

        for (int i = 0; i < isiGuru.size(); i++) {
            // Isi data row
            TableRow dataGuru = (TableRow)getLayoutInflater().inflate(R.layout.row_guru, null);
            no = dataGuru.findViewById(R.id.kolom_nomor_guru);
            namaGuru = dataGuru.findViewById(R.id.kolom_nama_guru);
            jenisKelaminGuru = dataGuru.findViewById(R.id.kolom_gender_guru);
            gelarGuru = dataGuru.findViewById(R.id.kolom_ijazah_guru);
            mapelGuru = dataGuru.findViewById(R.id.kolom_mapel_guru);

            namaGuru.setSelected(true);
            mapelGuru.setSelected(true);

            no.setText(String.valueOf(i+1));
            namaGuru.setText(isiGuru.get(i).getNama());
            jenisKelaminGuru.setText(isiGuru.get(i).getJenisKelamin());
            gelarGuru.setText(isiGuru.get(i).getGelar());
            mapelGuru.setText(isiGuru.get(i).getMapel());
            tabelGuru.addView(dataGuru);
        }
    }

    private void modulKalenderPendidikan(){
        spinBulan = findViewById(R.id.spin_bulan_kalender);
        tahunKalender = findViewById(R.id.tv_tahun_kalender);
        judulKalender = findViewById(R.id.tv_judul_kalender);

        bulanKalender = new ArrayList<>();

        bulanKalender.add("Januari");
        bulanKalender.add("Februari");
        bulanKalender.add("Maret");
        bulanKalender.add("April");
        bulanKalender.add("Mei");
        bulanKalender.add("Juni");
        bulanKalender.add("Juli");
        bulanKalender.add("Agustus");
        bulanKalender.add("September");
        bulanKalender.add("Oktober");
        bulanKalender.add("November");
        bulanKalender.add("Desember");

        no11 = findViewById(R.id.tv_11);
        no12 = findViewById(R.id.tv_12);
        no13 = findViewById(R.id.tv_13);
        no14 = findViewById(R.id.tv_14);
        no15 = findViewById(R.id.tv_15);
        no16 = findViewById(R.id.tv_16);
        no17 = findViewById(R.id.tv_17);

        no21 = findViewById(R.id.tv_21);
        no22 = findViewById(R.id.tv_22);
        no23 = findViewById(R.id.tv_23);
        no24 = findViewById(R.id.tv_24);
        no25 = findViewById(R.id.tv_25);
        no26 = findViewById(R.id.tv_26);
        no27 = findViewById(R.id.tv_27);

        no31 = findViewById(R.id.tv_31);
        no32 = findViewById(R.id.tv_32);
        no33 = findViewById(R.id.tv_33);
        no34 = findViewById(R.id.tv_34);
        no35 = findViewById(R.id.tv_35);
        no36 = findViewById(R.id.tv_36);
        no37 = findViewById(R.id.tv_37);

        no41 = findViewById(R.id.tv_41);
        no42 = findViewById(R.id.tv_42);
        no43 = findViewById(R.id.tv_43);
        no44 = findViewById(R.id.tv_44);
        no45 = findViewById(R.id.tv_45);
        no46 = findViewById(R.id.tv_46);
        no47 = findViewById(R.id.tv_47);

        no51 = findViewById(R.id.tv_51);
        no52 = findViewById(R.id.tv_52);
        no53 = findViewById(R.id.tv_53);
        no54 = findViewById(R.id.tv_54);
        no55 = findViewById(R.id.tv_55);
        no56 = findViewById(R.id.tv_56);
        no57 = findViewById(R.id.tv_57);

        no61 = findViewById(R.id.tv_61);
        no62 = findViewById(R.id.tv_62);
        no63 = findViewById(R.id.tv_63);
        no64 = findViewById(R.id.tv_64);
        no65 = findViewById(R.id.tv_65);
        no66 = findViewById(R.id.tv_66);
        no67 = findViewById(R.id.tv_67);

        setBulan = 0;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM", Locale.US);
        String dataTahun[] = df.format(date).split("-");
        if (Integer.parseInt(dataTahun[1])<7){
            tahun = Integer.parseInt(dataTahun[0]);
        }




        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bulanKalender);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBulan.setAdapter(adapter2);

        for (int i = 0; i < bulanKalender.size(); i++) {
            if (Integer.parseInt(dataTahun[1]) == (i+1)){
                spinBulan.setSelection(i);
            }
        }

        spinBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinBulan.getSelectedItem().toString().equals("Januari")||spinBulan.getSelectedItem().toString().equals("Februari")||spinBulan.getSelectedItem().toString().equals("Maret")||spinBulan.getSelectedItem().toString().equals("April")||spinBulan.getSelectedItem().toString().equals("Mei")||spinBulan.getSelectedItem().toString().equals("Juni")){
                    tahunKalender.setText(String.valueOf(tahun));
                }
                else {
                    tahunKalender.setText(String.valueOf(tahun-1));
                }

                bulan = 0;

                if (spinBulan.getSelectedItem().toString().equals("Januari")){
                    bulan += 0;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Februari")){
                    bulan += 1;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Maret")){
                    bulan += 2;
                }
                else if (spinBulan.getSelectedItem().toString().equals("April")){
                    bulan += 3;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Mei")){
                    bulan += 4;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Juni")){
                    bulan += 5;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Juli")){
                    bulan += 6;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Agustus")){
                    bulan += 7;
                }
                else if (spinBulan.getSelectedItem().toString().equals("September")){
                    bulan += 8;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Oktober")){
                    bulan += 9;
                }
                else if (spinBulan.getSelectedItem().toString().equals("November")){
                    bulan += 10;
                }
                else if (spinBulan.getSelectedItem().toString().equals("Desember")){
                    bulan += 11;
                }

                DateFormat df = new SimpleDateFormat("yyyy-MM-EEEE", Locale.US);
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(tahunKalender.getText().toString()), bulan, 1);
                judulKalender.setText(spinBulan.getSelectedItem().toString()+"  "+tahunKalender.getText().toString());

                HashMap coba = new HashMap();
                coba.put(1, no11);
                coba.put(2, no12);
                coba.put(3, no13);
                coba.put(4, no14);
                coba.put(5, no15);
                coba.put(6, no16);
                coba.put(7, no17);

                coba.put(8, no21);
                coba.put(9, no22);
                coba.put(10, no23);
                coba.put(11, no24);
                coba.put(12, no25);
                coba.put(13, no26);
                coba.put(14, no27);

                coba.put(15, no31);
                coba.put(16, no32);
                coba.put(17, no33);
                coba.put(18, no34);
                coba.put(19, no35);
                coba.put(20, no36);
                coba.put(21, no37);

                coba.put(22, no41);
                coba.put(23, no42);
                coba.put(24, no43);
                coba.put(25, no44);
                coba.put(26, no45);
                coba.put(27, no46);
                coba.put(28, no47);

                coba.put(29, no51);
                coba.put(30, no52);
                coba.put(31, no53);
                coba.put(32, no54);
                coba.put(33, no55);
                coba.put(34, no56);
                coba.put(35, no57);

                coba.put(36, no61);
                coba.put(37, no62);
                coba.put(38, no63);
                coba.put(39, no64);
                coba.put(40, no65);
                coba.put(41, no66);
                coba.put(42, no67);

                awalHari = 0;
                jumlahHari = 0;

                String pecahData[] = df.format(cal.getTime()).split("-");
                if (pecahData[2].equals("Sunday")){
                    awalHari+=1;
                }
                else if (pecahData[2].equals("Monday")){
                    awalHari+=2;
                }
                else if (pecahData[2].equals("Tuesday")){
                    awalHari+=3;
                }
                else if (pecahData[2].equals("Wednesday")){
                    awalHari+=4;
                }
                else if (pecahData[2].equals("Thursday")){
                    awalHari+=5;
                }
                else if (pecahData[2].equals("Friday")){
                    awalHari+=6;
                }
                else if (pecahData[2].equals("Saturday")){
                    awalHari+=7;
                }

                if (Integer.parseInt(pecahData[1]) == 1){
                    jumlahHari += 31;
                }
                else if (Integer.parseInt(pecahData[1]) == 2){
                    if (Integer.parseInt(pecahData[0]) % 4 == 0){
                        jumlahHari += 29;
                    }
                    else if (Integer.parseInt(pecahData[0]) % 4 != 0){
                        jumlahHari += 28;
                    }
                }
                else if (Integer.parseInt(pecahData[1]) == 3){
                    jumlahHari += 31;
                }
                else if (Integer.parseInt(pecahData[1]) == 4){
                    jumlahHari += 30;
                }
                else if (Integer.parseInt(pecahData[1]) == 5){
                    jumlahHari += 31;
                }
                else if (Integer.parseInt(pecahData[1]) == 6){
                    jumlahHari += 30;
                }
                else if (Integer.parseInt(pecahData[1]) == 7){
                    jumlahHari += 31;
                }
                else if (Integer.parseInt(pecahData[1]) == 8){
                    jumlahHari += 31;
                }
                else if (Integer.parseInt(pecahData[1]) == 9){
                    jumlahHari += 30;
                }
                else if (Integer.parseInt(pecahData[1]) == 10){
                    jumlahHari += 31;
                }
                else if (Integer.parseInt(pecahData[1]) == 11){
                    jumlahHari += 30;
                }
                else if (Integer.parseInt(pecahData[1]) == 12){
                    jumlahHari += 31;
                }

                clearText();

                int hitung = 1;
                for (int j = 1; j <= coba.size(); j++) {
                    if (j >= awalHari && hitung<=jumlahHari){
                        TextView isi = (TextView)coba.get(j);
                        isi.setText(String.valueOf(hitung));
                        hitung++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void modulPersebaranAlumni(){
        spinAlumni = findViewById(R.id.spin_tahun_alumni);
        tahunAlumni = new ArrayList<>();
        tahunAlumni.add(2017);
        tahunAlumni.add(2018);
        tahunAlumni.add(2019);
        tahunAlumni.add(2020);

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, tahunAlumni);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAlumni.setAdapter(adapter2);

        pieChartView = findViewById(R.id.chart_alumni);

        DecimalFormat df = new DecimalFormat("#.##");

        String isi [][] = new String[5][3];
        isi[0][0] = "1";
        isi[0][1] = "#234233";
        isi[0][2] = "UIN Suska Riau";

        isi[1][0] = "20";
        isi[1][1] = "#958655";
        isi[1][2] = "Universitas Riau";

        isi[2][0] = "39";
        isi[2][1] = "#654854";
        isi[2][2] = "Universitas Gajah Mada";

        isi[3][0] = "20";
        isi[3][1] = "#785456";
        isi[3][2] = "Institut Teknologi Bandung";

        isi[4][0] = "20";
        isi[4][1] = "#326548";
        isi[4][2] = "Universitas Pahlawan";


        List pieData = new ArrayList<>();
        for (int i = 0; i < isi.length; i++) {
            pieData.add(new SliceValue((float) Double.parseDouble(isi[i][0]), Color.parseColor(isi[i][1])).setLabel(isi[i][2]+" "+df.format(Double.parseDouble(isi[i][0]))+"%"));
        }
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);
    }

    private void modulAksesSekolah(){
        banding = (LinearLayout) findViewById(R.id.btn_bandingkan);
        banding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(DetailSekolahActivity.this, PerbandinganActivity.class);
                startActivity(go);
            }
        });
    }

    // Dipakai oleh modul komposisi guru
    private void cleanTable (TableLayout table){
        int childCount = table.getChildCount();
        if (childCount > 0){
            table.removeViews(0, childCount);
        }
    }

    // Dipakai oleh modul kalender akademik
    private void clearText(){
        no11.setText("");
        no12.setText("");
        no13.setText("");
        no14.setText("");
        no15.setText("");
        no16.setText("");
        no17.setText("");

        no21.setText("");
        no22.setText("");
        no23.setText("");
        no24.setText("");
        no25.setText("");
        no26.setText("");
        no27.setText("");

        no31.setText("");
        no32.setText("");
        no33.setText("");
        no34.setText("");
        no35.setText("");
        no36.setText("");
        no37.setText("");

        no41.setText("");
        no42.setText("");
        no43.setText("");
        no44.setText("");
        no45.setText("");
        no46.setText("");
        no47.setText("");

        no51.setText("");
        no52.setText("");
        no53.setText("");
        no54.setText("");
        no55.setText("");
        no56.setText("");
        no57.setText("");

        no61.setText("");
        no62.setText("");
        no63.setText("");
        no64.setText("");
        no65.setText("");
        no66.setText("");
        no67.setText("");
    }
}
