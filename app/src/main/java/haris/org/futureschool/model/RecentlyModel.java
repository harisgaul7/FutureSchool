package haris.org.futureschool.model;

public class RecentlyModel {
    int gambarRecently, pendaftaran, bulanan;
    String nama;
    String alamat;
    String akreditasi;
    double jarak;

    public RecentlyModel(int gambarRecently, int pendaftaran, int bulanan, String nama, String alamat, String akreditasi, double jarak) {
        this.gambarRecently = gambarRecently;
        this.pendaftaran = pendaftaran;
        this.bulanan = bulanan;
        this.nama = nama;
        this.alamat = alamat;
        this.akreditasi = akreditasi;
        this.jarak = jarak;
    }

    public String getAkreditasi() {
        return akreditasi;
    }

    public void setAkreditasi(String akreditasi) {
        this.akreditasi = akreditasi;
    }

    public void setJarak(double jarak) {
        this.jarak = jarak;
    }

    public int getGambarRecently() {
        return gambarRecently;
    }

    public void setGambarRecently(int gambarRecently) {
        this.gambarRecently = gambarRecently;
    }

    public int getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(int pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public int getBulanan() {
        return bulanan;
    }

    public void setBulanan(int bulanan) {
        this.bulanan = bulanan;
    }

    public double getJarak() {
        return jarak;
    }

    public void setJarak(int jarak) {
        this.jarak = jarak;
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
}
