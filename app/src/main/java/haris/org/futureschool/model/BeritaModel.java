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

    public void setGambar4(int gambar4) {
        this.gambar4 = gambar4;
    }

    public int getVersi() {
        return versi;
    }

    public void setVersi(int versi) {
        this.versi = versi;
    }

    public int getGambarProfil() {
        return gambarProfil;
    }

    public void setGambarProfil(int gambarProfil) {
        this.gambarProfil = gambarProfil;
    }

    public int getGambarTingkat() {
        return gambarTingkat;
    }

    public void setGambarTingkat(int gambarTingkat) {
        this.gambarTingkat = gambarTingkat;
    }

    public int getGambar1() {
        return gambar1;
    }

    public void setGambar1(int gambar1) {
        this.gambar1 = gambar1;
    }

    public int getGambar2() {
        return gambar2;
    }

    public void setGambar2(int gambar2) {
        this.gambar2 = gambar2;
    }

    public int getGambar3() {
        return gambar3;
    }

    public void setGambar3(int gambar3) {
        this.gambar3 = gambar3;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
