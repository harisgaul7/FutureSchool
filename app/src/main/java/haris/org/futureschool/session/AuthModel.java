package haris.org.futureschool.session;

import com.google.gson.annotations.SerializedName;

public class AuthModel {
    @SerializedName("kode")
    String kode;

    @SerializedName("pesan")
    String pesan;

    @SerializedName("result")
    AuthData result;

    public AuthModel(String kode, String pesan, AuthData result) {
        this.kode = kode;
        this.pesan = pesan;
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public AuthData getResult() {
        return result;
    }

    public void setResult(AuthData result) {
        this.result = result;
    }
}