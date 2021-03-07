package haris.org.futureschool.model;

public class GuruModel {
    private String nama, jenisKelamin, gelar, mapel;

    public GuruModel(String nama, String jenisKelamin, String gelar, String mapel) {
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.gelar = gelar;
        this.mapel = mapel;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }
}
