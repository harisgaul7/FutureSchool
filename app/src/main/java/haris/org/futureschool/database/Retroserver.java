package haris.org.futureschool.database;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
    private static Retrofit retro;

    // Method untuk menghubungkan ke server
    public static Retrofit getClient(String url){
        final OkHttpClient cobaOk = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(cobaOk)
                    .build();
        }
        return retro;
    }
}
