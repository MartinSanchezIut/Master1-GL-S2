package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Exo4 extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor orientation;
    TextView text;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo4);

        text = (TextView) findViewById(R.id.idview) ;
        img = (ImageView) findViewById(R.id.image) ;


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) {

            orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, orientation, SensorManager.SENSOR_DELAY_NORMAL);

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
        sensorManager.registerListener(this, orientation, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int x = Math.round(event.values[0]);
        int y = Math.round(event.values[1]);
        int z = Math.round(event.values[2]);
        System.out.print("x" + x);
        System.out.print(" y" + y);
        System.out.println(" z" + z);
        text.setText("x: " + x + "\ny: " + y + "\nz: " + z);

        if (x < 0) { // Droite
            if (y < 0) { // Haut
                img.setVisibility(View.VISIBLE);
                img.setRotation(90+45);
            }else if (y > 0) { // Bas
                img.setVisibility(View.VISIBLE);
                img.setRotation(180+45);
            } else {
                img.setVisibility(View.VISIBLE);
                img.setRotation(180);
            }
        }else if (x > 0) { // Gauche
            if (y < 0) { // Haut
                img.setVisibility(View.VISIBLE);
                img.setRotation(45);
            }else if (y > 0) { // Bas
                img.setVisibility(View.VISIBLE);
                img.setRotation(-45);
            } else {
                img.setVisibility(View.VISIBLE);
                img.setRotation(0);
            }
        }else {
            if (y < 0) { // Haut
                img.setVisibility(View.VISIBLE);
                img.setRotation(90);
            }else if (y > 0) { // Bas
                img.setVisibility(View.VISIBLE);
                img.setRotation(-90);
            } else {
                img.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}