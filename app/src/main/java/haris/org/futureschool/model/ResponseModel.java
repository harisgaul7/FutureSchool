package haris.org.futureschool.model;

import java.util.List;

public class ResponseModel {
    private String kode, pesan;
    private List<TopikRekomendasiModel> hasilTopik;
    private List<JurusanRekomendasiModel> hasilJurusan;
    private List<TampilanSekolahModel> hasilTampilanSekolah;
    private List<DeskripsiJurusanModel> hasilDeskripsiJurusan;
    private List<FasilitasModel> hasilFasilitas;
    private List<EkstrakulikulerModel> hasilEkstrakurikuler;
    private List<PrestasiModel> hasilPrestasi;
    private List<GuruModel> hasilGuru;
    private List<BiayaModel> hasilBiaya;
    private List<KontakModel> hasilKontak;
    private List<TampilanSekolahModel> hasilCariSekolah;
    private List<FilterEkstrakulikulerModel> filterEkstrakurikuler;
    private List<FilterFasilitasModel> filterFasilitas;
    private List<TampilanSekolahModel> hasilFilterSekolah;
    private List<IdFasilitasModel> hasilIdSekolah;

    public ResponseModel(String kode, String pesan) {
        this.kode = kode;
        this.pesan = pesan;
    }

    public List<IdFasilitasModel> getHasilIdSekolah() {
        return hasilIdSekolah;
    }

    public List<TampilanSekolahModel> getHasilFilterSekolah() {
        return hasilFilterSekolah;
    }

    public List<FilterEkstrakulikulerModel> getFilterEkstrakurikuler() {
        return filterEkstrakurikuler;
    }

    public List<FilterFasilitasModel> getFilterFasilitas() {
        return filterFasilitas;
    }

    public List<TampilanSekolahModel> getHasilCariSekolah() {
        return hasilCariSekolah;
    }

    public List<KontakModel> getHasilKontak() {
        return hasilKontak;
    }

    public List<BiayaModel> getHasilBiaya() {
        return hasilBiaya;
    }

    public List<GuruModel> getHasilGuru() {
        return hasilGuru;
    }

    public List<PrestasiModel> getHasilPrestasi() {
        return hasilPrestasi;
    }

    public List<EkstrakulikulerModel> getHasilEkstrakurikuler() {
        return hasilEkstrakurikuler;
    }

    public List<FasilitasModel> getHasilFasilitas() {
        return hasilFasilitas;
    }

    public List<DeskripsiJurusanModel> getHasilDeskripsiJurusan() {
        return hasilDeskripsiJurusan;
    }

    public List<TampilanSekolahModel> getHasilTampilanSekolah() {
        return hasilTampilanSekolah;
    }

    public List<JurusanRekomendasiModel> getHasilJurusan() {
        return hasilJurusan;
    }

    public List<TopikRekomendasiModel> getHasilTopik() {
        return hasilTopik;
    }

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }
}
