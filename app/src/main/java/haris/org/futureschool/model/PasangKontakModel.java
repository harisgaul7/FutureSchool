package haris.org.futureschool.model;

public class PasangKontakModel {
    private int gambar;
    private String nama, detail, aksi;

    public PasangKontakModel(int gambar, String nama, String detail, String aksi) {
        this.gambar = gambar;
        this.nama = nama;
        this.detail = detail;
        this.aksi = aksi;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAksi() {
        return aksi;
    }

    public void setAksi(String aksi) {
        this.aksi = aksi;
    }
}
