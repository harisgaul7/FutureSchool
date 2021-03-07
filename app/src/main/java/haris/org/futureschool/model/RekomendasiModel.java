package haris.org.futureschool.model;

public class RekomendasiModel {
    private int gambar_sekolah, gambar_peringkat;
    private String nama_sekolah, skor_sekolah;

    public RekomendasiModel(int gambar_sekolah, int gambar_peringkat, String nama_sekolah, String skor_sekolah) {
        this.gambar_sekolah = gambar_sekolah;
        this.gambar_peringkat = gambar_peringkat;
        this.nama_sekolah = nama_sekolah;
        this.skor_sekolah = skor_sekolah;
    }

    public int getGambar_sekolah() {
        return gambar_sekolah;
    }

    public void setGambar_sekolah(int gambar_sekolah) {
        this.gambar_sekolah = gambar_sekolah;
    }

    public int getGambar_peringkat() {
        return gambar_peringkat;
    }

    public void setGambar_peringkat(int gambar_peringkat) {
        this.gambar_peringkat = gambar_peringkat;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getSkor_sekolah() {
        return skor_sekolah;
    }

    public void setSkor_sekolah(String skor_sekolah) {
        this.skor_sekolah = skor_sekolah;
    }
}
