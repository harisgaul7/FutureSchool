package haris.org.futureschool.model;

public class FilterFasilitasModel {
    private String nama_fasilitas, id_master_fasilitas;

    public FilterFasilitasModel(String nama_fasilitas, String id_master_fasilitas) {
        this.nama_fasilitas = nama_fasilitas;
        this.id_master_fasilitas = id_master_fasilitas;
    }

    public String getNama_fasilitas() {
        return nama_fasilitas;
    }

    public String getId_master_fasilitas() {
        return id_master_fasilitas;
    }
}
