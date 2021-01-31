package haris.org.futureschool.model;

public class SekolahModel {
    private int Gambar;
    private String nama, alamat, deskripsi;
    private double jarak;

    public SekolahModel(int gambar, String nama, String alamat, String deskripsi, double jarak){
        this.Gambar = gambar;
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

    public int getGambar() {
        return Gambar;
    }

    public void setGambar(int gambar) {
        Gambar = gambar;
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
