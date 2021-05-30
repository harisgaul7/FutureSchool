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

    public int getGambarRecently() {
        return gambarRecently;
    }

    public int getPendaftaran() {
        return pendaftaran;
    }

    public int getBulanan() {
        return bulanan;
    }

    public double getJarak() {
        return jarak;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
}
