package haris.org.futureschool.model;

public class SekolahModel {
    private String nama, alamat, deskripsi, gambar;
    private double jarak;

    public SekolahModel(String gambar, String nama, String alamat, String deskripsi, double jarak){
        this.gambar = gambar;
        this.nama = nama;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.jarak = jarak;
    }

    public double getJarak() {
        return jarak;
    }

    public String getGambar() {
        return gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
