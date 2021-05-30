package haris.org.futureschool.model;

public class FavoritModel {
    int gambarSekolah, kuota, rating, progress;
    String nama;

    public FavoritModel(int gambarSekolah, int kuota, int rating, int progress, String nama) {
        this.gambarSekolah = gambarSekolah;
        this.kuota = kuota;
        this.rating = rating;
        this.progress = progress;
        this.nama = nama;
    }

    public int getGambarSekolah() {
        return gambarSekolah;
    }

    public int getKuota() {
        return kuota;
    }

    public int getRating() {
        return rating;
    }

    public int getProgress() {
        return progress;
    }

    public String getNama() {
        return nama;
    }
}
