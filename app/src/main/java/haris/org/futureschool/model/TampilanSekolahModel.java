package haris.org.futureschool.model;

public class TampilanSekolahModel {
    private String id_sekolah, gambar_sekolah, nama_sekolah, alamat_sekolah, deskripsi_sekolah, akreditasi_sekolah, visi_misi_sekolah, kurikulum_sekolah, slide_sekolah;
    private double jarak;

    public TampilanSekolahModel(String id_sekolah, String gambar_sekolah, String nama_sekolah, String alamat_sekolah, String deskripsi_sekolah, double jarak, String akreditasi_sekolah, String visi_misi_sekolah, String kurikulum_sekolah, String slide_sekolah) {
        this.id_sekolah = id_sekolah;
        this.gambar_sekolah = gambar_sekolah;
        this.nama_sekolah = nama_sekolah;
        this.alamat_sekolah = alamat_sekolah;
        this.deskripsi_sekolah = deskripsi_sekolah;
        this.jarak = jarak;
        this.akreditasi_sekolah = akreditasi_sekolah;
        this.visi_misi_sekolah = visi_misi_sekolah;
        this.kurikulum_sekolah = kurikulum_sekolah;
        this.slide_sekolah = slide_sekolah;
    }

    public String getVisi_misi_sekolah() {
        return visi_misi_sekolah;
    }

    public String getKurikulum_sekolah() {
        return kurikulum_sekolah;
    }

    public String getAkreditasi_sekolah() {
        return akreditasi_sekolah;
    }

    public double getJarak() {
        return jarak;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public String getGambar_sekolah() {
        return gambar_sekolah;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public String getAlamat_sekolah() {
        return alamat_sekolah;
    }

    public String getDeskripsi_sekolah() {
        return deskripsi_sekolah;
    }

    public String getSlide_sekolah() {
        return slide_sekolah;
    }
}
