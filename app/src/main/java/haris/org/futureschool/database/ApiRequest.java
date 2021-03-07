package haris.org.futureschool.database;

import haris.org.futureschool.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {

    // Untuk mendapatkan data topik rekomendasi
    @GET("api/getTopik")
    Call<ResponseModel> getDataTopikRekomendasi();

    // Untuk mendapatkan data jurusan rekomendasi
    @FormUrlEncoded
    @POST("api/getJurusan")
    Call<ResponseModel> getDataJurusanRekomendasi(@Field("topik_id") int topik_id);


    @FormUrlEncoded
    @POST("api/insert_terhubung")
    Call<ResponseModel> sendDataKepuasan(@Field("kpsn_petugas") int petugas,
                                         @Field("kpsn_ptn") int pertanyaan,
                                         @Field("kpsn_lynn") int layanan,
                                         @Field("kpsn_jwb") int jawaban,
                                         @Field("kpsn_klhn") String keluhan);
}
