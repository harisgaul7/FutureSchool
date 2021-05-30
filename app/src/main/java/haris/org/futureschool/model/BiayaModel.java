package haris.org.futureschool.model;

public class BiayaModel {
    private String jenis_biaya, nama_biaya, jumlah_biaya;

    public BiayaModel(String nama_biaya, String jumlah_biaya) {
        this.nama_biaya = nama_biaya;
        this.jumlah_biaya = jumlah_biaya;
    }

    public String getJenis_biaya() {
        return jenis_biaya;
    }

    public String getNama_biaya() {
        return nama_biaya;
    }

    public String getJumlah_biaya() {
        return jumlah_biaya;
    }
}
