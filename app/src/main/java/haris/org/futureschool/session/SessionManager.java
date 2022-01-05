package haris.org.futureschool.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import haris.org.futureschool.LoginActivity;


public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FULLNAME = "nama";
    public static final String IS_LOGIN = "loginstatus";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager (Context context){
        this._context = context;
        sp =  _context.getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(int id, String email, String nama){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, String.valueOf(id));
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_FULLNAME, nama);
        editor.commit();
    }

    public HashMap getDetailLogin(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_ID, sp.getString(KEY_ID, null));
        map.put(KEY_EMAIL, sp.getString(KEY_EMAIL, null));
        map.put(KEY_FULLNAME, sp.getString(KEY_FULLNAME, null));
        return map;
    }

    public void checkLogin(){
        if (!this.Login()){
            Intent login = new Intent(_context, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(login);
        }
    }

    private Boolean Login(){
        return sp.getBoolean(IS_LOGIN, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

}