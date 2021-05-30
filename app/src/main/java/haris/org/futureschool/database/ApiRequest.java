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

    // Untuk mendapatkan data ekstrakulikuler dari id sekolah
    @FormUrlEncoded
    @POST("tampil_ekstrakulikuler")
    Call<ResponseModel> getDataEkstrakulikuler(@Field("id_sekolah") int sekolah_id);

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

    // Untuk mendapatkan data filter fasilitas
    @GET("filter_fasilitas")
    Call<ResponseModel> getFilterFasilitas();

    // Untuk mendapatkan data filter ekstrakulikuler
    @GET("filter_ekstrakulikuler")
    Call<ResponseModel> getFilterEkstrakulikuler();

    // Untuk mendapatkan filter sekolah
    @FormUrlEncoded
    @POST("filter_sekolah")
    Call<ResponseModel> getFilterSekolah(@Field("id_master_fasilitas") String fasilitas);

    @FormUrlEncoded
    @POST("api/insert_terhubung")
    Call<ResponseModel> sendDataKepuasan(@Field("kpsn_petugas") int petugas,
                                         @Field("kpsn_ptn") int pertanyaan,
                                         @Field("kpsn_lynn") int layanan,
                                         @Field("kpsn_jwb") int jawaban,
                                         @Field("kpsn_klhn") String keluhan);
}
