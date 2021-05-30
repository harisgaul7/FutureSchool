package haris.org.futureschool.model;

public class BeritaModel {
    int gambarProfil, gambarTingkat, gambar1, gambar2, gambar3, gambar4, versi;
    String sekolah, waktu, judul, preview;

    public BeritaModel(int gambarProfil, int gambarTingkat, int gambar1, int gambar2, int gambar3, int gambar4, String sekolah, String waktu, String judul, String preview, int versi) {
        this.gambarProfil = gambarProfil;
        this.gambarTingkat = gambarTingkat;
        this.gambar1 = gambar1;
        this.gambar2 = gambar2;
        this.gambar3 = gambar3;
        this.gambar4 = gambar4;
        this.sekolah = sekolah;
        this.waktu = waktu;
        this.judul = judul;
        this.preview = preview;
        this.versi = versi;
    }

    public int getGambar4() {
        return gambar4;
    }

    public int getVersi() {
        return versi;
    }

    public int getGambarProfil() {
        return gambarProfil;
    }

    public int getGambarTingkat() {
        return gambarTingkat;
    }

    public int getGambar1() {
        return gambar1;
    }

    public int getGambar2() {
        return gambar2;
    }

    public int getGambar3() {
        return gambar3;
    }

    public String getSekolah() {
        return sekolah;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getJudul() {
        return judul;
    }

    public String getPreview() {
        return preview;
    }
}
