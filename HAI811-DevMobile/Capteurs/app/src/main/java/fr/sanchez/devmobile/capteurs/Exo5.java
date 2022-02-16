package fr.sanchez.devmobile.capteurs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class Exo5 extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    int seuil;
    private CameraManager mCameraManager;
    private String mCameraId;

    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo5);

        status = false;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            // Je réduit car impossible a atteindre les valeur max
            float maxValue = accelerometer.getMaximumRange() * 0.75f;
            seuil = Float.valueOf(maxValue /2).intValue();
            System.out.println("MAX : "   + accelerometer.getMaximumRange());
            System.out.println("seuil : "   + seuil);



            boolean isFlashAvailable = getApplicationContext().getPackageManager()
                    .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);

            if (!isFlashAvailable) {
                Context context = getApplicationContext();
                CharSequence text = "Nous n'avons pas trouvé le flash !!!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }else {
                mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                try {
                    mCameraId = mCameraManager.getCameraIdList()[0];
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Nous n'avons pas trouvé le capteur !!!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Recupérer les valeurs de l'accélérometre
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        System.out.print("x" + x);
        System.out.print(" y" + y);
        System.out.println(" z" + z);

        if ((Math.abs(x) > seuil) || (Math.abs(y) > seuil) || (Math.abs(z) > seuil)) {
            // Turn on flash
            try {
                mCameraManager.setTorchMode(mCameraId, status);
                status = !status;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}