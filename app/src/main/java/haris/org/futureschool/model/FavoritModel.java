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

    public void setGambarSekolah(int gambarSekolah) {
        this.gambarSekolah = gambarSekolah;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
