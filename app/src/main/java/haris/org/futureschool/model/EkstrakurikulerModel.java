package haris.org.futureschool.model;

public class EkstrakurikulerModel {
    private String nama_ekstrakurikuler, gambar_ekstrakurikuler;

    public EkstrakurikulerModel(String nama_ekstrakurikuler, String gambar_ekstrakurikuler) {
        this.nama_ekstrakurikuler = nama_ekstrakurikuler;
        this.gambar_ekstrakurikuler = gambar_ekstrakurikuler;
    }

    public String getNama_ekstrakurikuler() {
        return nama_ekstrakurikuler;
    }

    public String getGambar_ekstrakurikuler() {
        return gambar_ekstrakurikuler;
    }
}
