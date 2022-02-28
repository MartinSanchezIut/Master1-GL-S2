package fr.sanchez.devmobile.capteurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class Exo7 extends AppCompatActivity implements LocationListener, EasyPermissions.PermissionCallbacks {

    LocationManager locationManager;
    TextView latitude, longitude;

    private int READ_STORAGE_PERMISSION_REQUEST = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo7);

        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;

        if (!EasyPermissions.hasPermissions(this, permission)) {
            EasyPermissions.requestPermissions(this, "Our App Requires a permission to access your storage", READ_STORAGE_PERMISSION_REQUEST
                    , permission);
        }

        if (!EasyPermissions.hasPermissions(this, permission2)) {
            EasyPermissions.requestPermissions(this, "Our App Requires a permission to access your storage", READ_STORAGE_PERMISSION_REQUEST
                    , permission2);
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Context context = getApplicationContext();
            CharSequence text = "Nous n'avons pas la permission !!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
           // Intent i = new Intent(getApplicationContext(), MainActivity.class);
           // startActivity(i);
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude() ;
        double lon = location.getLongitude();
        System.out.println(lat + " / " + lon);
        latitude.setText(lat + "");
        longitude.setText(lon + "");
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //LocationListener.super.onStatusChanged(provider, status, extras);
    }
}