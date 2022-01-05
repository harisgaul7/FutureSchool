package haris.org.futureschool.session;

import com.google.gson.annotations.SerializedName;

public class AuthData {
    @SerializedName("email")
    String email;

    @SerializedName("fullname")
    String fullname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}