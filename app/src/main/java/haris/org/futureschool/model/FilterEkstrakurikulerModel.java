package haris.org.futureschool.model;

public class FilterEkstrakurikulerModel {
    private String nama_ekstrakurikuler, id_master_ekstrakurikuler;

    public FilterEkstrakurikulerModel(String nama_ekstrakurikuler, String id_master_ekstrakurikuler) {
        this.nama_ekstrakurikuler = nama_ekstrakurikuler;
        this.id_master_ekstrakurikuler = id_master_ekstrakurikuler;
    }

    public String getNama_ekstrakurikuler() {
        return nama_ekstrakurikuler;
    }

    public String getId_master_ekstrakurikuler() {
        return id_master_ekstrakurikuler;
    }
}
