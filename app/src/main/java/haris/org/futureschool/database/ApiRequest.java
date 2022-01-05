package haris.org.futureschool.database;

import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {

    // Untuk mendapatkan data topik rekomendasi
    @GET("tampil_topik")
    Call<ResponseModel> getDataTopikRekomendasi();

    // Untuk mendapatkan data tampilan sekolah
    @GET("tampilan_sekolah")
    Call<ResponseModel> getDataTampilanSekolah();

    // Untuk mendapatkan data tampilan sekolah by id
    @FormUrlEncoded
    @POST("tampilan_sekolah_id")
    Call<ResponseModel> getDataTampilanSekolahById(@Field("id_sekolah") int id_sekolah);

    // Untuk mendapatkan data jurusan rekomendasi
    @FormUrlEncoded
    @POST("tampil_jurusan")
    Call<ResponseModel> getDataJurusanRekomendasi(@Field("id_topik") int topik_id);

    // Untuk mendapatkan data jurusan dari id sekolah
    @FormUrlEncoded
    @POST("deskripsi_jurusan")
    Call<ResponseModel> getDataDeskripsiJurusan(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data fasilitas dari id sekolah
    @FormUrlEncoded
    @POST("tampil_fasilitas")
    Call<ResponseModel> getDataFasilitas(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data ekstrakurikuler dari id sekolah
    @FormUrlEncoded
    @POST("tampil_ekstrakurikuler")
    Call<ResponseModel> getDataEkstrakurikuler(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data prestasi dari id sekolah
    @FormUrlEncoded
    @POST("tampil_prestasi")
    Call<ResponseModel> getDataPrestasi(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data guru dari id sekolah
    @FormUrlEncoded
    @POST("tampil_guru")
    Call<ResponseModel> getDataGuru(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data biaya dari id sekolah
    @FormUrlEncoded
    @POST("tampil_biaya")
    Call<ResponseModel> getDataBiaya(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data kontak dari id sekolah
    @FormUrlEncoded
    @POST("tampil_kontak")
    Call<ResponseModel> getDataKontak(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data pencarian nama sekolah
    @FormUrlEncoded
    @POST("cari_sekolah")
    Call<ResponseModel> getCariSekolah(@Field("nama_sekolah") String sekolah_nama);

    // Untuk mendapatkan data akreditasi sekolah
    @FormUrlEncoded
    @POST("akreditasi_sekolah")
    Call<ResponseModel> getAkreditasiSekolah(@Field("id_sekolah") int sekolah_id);

    // Untuk mendapatkan data filter fasilitas
    @GET("filter_fasilitas")
    Call<ResponseModel> getFilterFasilitas();

    // Untuk mendapatkan data filter ekstrakurikuler
    @GET("filter_ekstrakurikuler")
    Call<ResponseModel> getFilterEkstrakurikuler();

    // Untuk mendapatkan filter sekolah
    @FormUrlEncoded
    @POST("filter_sekolah")
    Call<ResponseModel> getFilterSekolah(@Field("sortir") String sortir,
                                         @Field("tingkat") String tingkat,
                                         @Field("awal_kecil") int awal_kecil,
                                         @Field("awal_besar") int awal_besar,
                                         @Field("bulanan_kecil") int bulanan_kecil,
                                         @Field("bulanan_besar") int bulanan_besar,
                                         @Field("id_master_fasilitas") String fasilitas,
                                         @Field("id_master_ekstrakurikuler") String ekstrakurikuler);

    // Untuk dipakai mengambil daftar sekolah yang memiliki jurusan yang dimaksud
    @FormUrlEncoded
    @POST("ambil_id_sekolah_dari_jurusan")
    Call<ResponseModel> getSekolahDariJurusan(@Field("id_jurusan") String id_jurusan);

    // Mengambil data login
    @FormUrlEncoded
    @POST("cek_login")
    Call<ResponseModel> getLoginData(@Field("email") String email,
                                     @Field("password") String password);

    // Daftar akun siswa
    @FormUrlEncoded
    @POST("tambah_akun")
    Call<ResponseModel> addAccount(@Field("nama_depan") String nama_depan,
                                   @Field("nama_belakang") String nama_belakang,
                                   @Field("email") String email,
                                     @Field("password") String password);
}
