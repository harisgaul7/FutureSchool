package haris.org.futureschool.model;

public class InboxModel {
    private int gambar_akun, gambar_jenis;
    private String nama, judul, deskripsi, tanggal, jenis;

    public InboxModel(int gambar_akun, String nama, String judul, String deskripsi, String tanggal, String jenis, int gambar_jenis) {
        this.gambar_akun = gambar_akun;
        this.nama = nama;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.gambar_jenis = gambar_jenis;
    }


    public int getGambar_jenis() {
        return gambar_jenis;
    }

    public void setGambar_jenis(int gambar_jenis) {
        this.gambar_jenis = gambar_jenis;
    }

    public int getGambar_akun() {
        return gambar_akun;
    }

    public void setGambar_akun(int gambar_akun) {
        this.gambar_akun = gambar_akun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
