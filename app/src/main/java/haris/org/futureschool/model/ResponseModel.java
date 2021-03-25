package haris.org.futureschool.model;

import java.util.List;

public class ResponseModel {
    String kode, pesan;
    List<TopikRekomendasiModel> hasilTopik;
    List<JurusanRekomendasiModel> hasilJurusan;

    public List<JurusanRekomendasiModel> getHasilJurusan() {
        return hasilJurusan;
    }

    public void setHasilJurusan(List<JurusanRekomendasiModel> hasilJurusan) {
        this.hasilJurusan = hasilJurusan;
    }

    public List<TopikRekomendasiModel> getHasilTopik() {
        return hasilTopik;
    }

    public void setHasilTopik(List<TopikRekomendasiModel> hasilTopik) {
        this.hasilTopik = hasilTopik;
    }

    public ResponseModel(String kode, String pesan) {
        this.kode = kode;
        this.pesan = pesan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
