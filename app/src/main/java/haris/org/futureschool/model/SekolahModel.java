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

    public void setJarak(double jarak) {
        this.jarak = jarak;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
