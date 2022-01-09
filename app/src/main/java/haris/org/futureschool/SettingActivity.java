package haris.org.futureschool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import haris.org.futureschool.session.SessionManager;

import static haris.org.futureschool.session.SessionManager.KEY_EMAIL;
import static haris.org.futureschool.session.SessionManager.KEY_FULLNAME;

public class SettingActivity extends AppCompatActivity implements LocationListener {
    TextView nama, koordinat, ubah_alamat, ubah_email, ubah_telepon, ambil_lokasi;
    EditText alamat, email, telepon;
    Spinner gender;
    Button simpan;
    private ProgressDialog pd;
    LocationManager locationManager;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sm = new SessionManager(SettingActivity.this);

        nama = (TextView) findViewById(R.id.txt_nama_siswa);
        alamat = (EditText) findViewById(R.id.txt_alamat_siswa);
        koordinat = (TextView) findViewById(R.id.txt_koordinat_siswa);
        gender = (Spinner) findViewById(R.id.txt_jenis_kelamin_siswa);
        ambil_lokasi = (TextView) findViewById(R.id.txt_ambil_lokasi);
        email = (EditText) findViewById(R.id.txt_email_siswa);
        telepon = (EditText) findViewById(R.id.txt_telepon_siswa);
        ubah_alamat = (TextView) findViewById(R.id.ubah_alamat_siswa);
        ubah_email = (TextView) findViewById(R.id.ubah_email_siswa);
        ubah_telepon = (TextView) findViewById(R.id.ubah_telepon_siswa);

        simpan = (Button) findViewById(R.id.btn_ubah);

        nama.setText(sm.getDetailLogin().get(KEY_FULLNAME).toString());
        email.setText(sm.getDetailLogin().get(KEY_EMAIL).toString());

        pd = new ProgressDialog(SettingActivity.this);
        pd.setMessage("Loading . . .");

        ubah_alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamat.setEnabled(true);
            }
        });

        ambil_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions(SettingActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                        Toast.makeText(SettingActivity.this, "Akses lokasi belum diberikan!", Toast.LENGTH_SHORT).show();
                    }
                    else if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
                        try {
                            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                                OnGPS();
                            } else {
                                getLocation();
                            }
                        }
                        catch(SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        String[] items = new String[]{"Pria", "Wanita"};
        final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, items);
        gender.setAdapter(adapter);
        gender.setSelection(0);

        ubah_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setEnabled(true);
            }
        });

        ubah_telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telepon.setEnabled(true);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Berhasil")
                        .setContentText("Profil berhasil diubah!")
                        .setConfirmText("Kembali ke Akun")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                finish();
                            }
                        })
                        .show();
            }
        });
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                SettingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                SettingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(longi);
                koordinat.setText(latitude + ", " + longitude);
//                Toast.makeText(this, "Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(this, "Location cant be found but keep trying . . . .", Toast.LENGTH_SHORT).show();
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(longi);
                koordinat.setText(latitude + ", " + longitude);
//                Toast.makeText(this, "Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.get(0).getLocality()!=null){
//                provinsi = addresses.get(0).getAdminArea();
//                kabupaten = addresses.get(0).getSubAdminArea();
//                kecamatan = addresses.get(0).getLocality();
//                desa = addresses.get(0).getSubLocality();
                String latitude = String.valueOf(addresses.get(0).getLatitude());
                String longitude = String.valueOf(addresses.get(0).getLongitude());
                String lokasi = "Lokal : " + addresses.get(0).getLocality() + "\n"
                        + "Sublocal : " + addresses.get(0).getSubLocality() + "\n"
                        + "Country Name : " + addresses.get(0).getCountryName() + "\n"
                        + "Troughfare : " + addresses.get(0).getThoroughfare() + "\n"
                        + "subTroughfare : " + addresses.get(0).getSubThoroughfare() + "\n"
                        + "latitude : " + addresses.get(0).getLatitude() + "\n"
                        + "longitude : " + addresses.get(0).getLongitude() + "\n"
                        + "admin area : " + addresses.get(0).getAdminArea() + "\n"
                        + "sub admin area : " + addresses.get(0).getSubAdminArea() + "\n";

                koordinat.setText(latitude + ", " + longitude);

//                Toast.makeText(SettingActivity.this, latitude+", "+longitude, Toast.LENGTH_SHORT).show();
//                et_alamatkos.setText("" + addresses.get(0).getThoroughfare() + "," + addresses.get(0).getSubLocality() + "," + addresses.get(0).getSubLocality() + "," + addresses.get(0).getSubAdminArea() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
//                et_alamatkos.setText("" + addresses.get(0).getSubLocality() + "," + addresses.get(0).getLocality() + "," + addresses.get(0).getSubAdminArea() + "," + addresses.get(0).getAdminArea());
            }
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(SettingActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }
}
