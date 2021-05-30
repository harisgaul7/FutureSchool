package haris.org.futureschool.model;

public class FasilitasModel {
    private String nama_fasilitas, foto_fasilitas;

    public FasilitasModel(String nama_fasilitas, String foto_fasilitas) {
        this.nama_fasilitas = nama_fasilitas;
        this.foto_fasilitas = foto_fasilitas;
    }

    public String getNama_fasilitas() {
        return nama_fasilitas;
    }

    public String getFoto_fasilitas() {
        return foto_fasilitas;
    }
}
