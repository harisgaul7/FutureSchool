package haris.org.futureschool.database;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
    private static Retrofit retro;
    private final static String URL = "future-school/public/api/";

    // Method untuk menghubungkan ke server
    public static Retrofit getClient(){
        final OkHttpClient cobaOk = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(new BaseUrl().BASE_URL+URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(cobaOk)
                    .build();
        }
        return retro;
    }
}
