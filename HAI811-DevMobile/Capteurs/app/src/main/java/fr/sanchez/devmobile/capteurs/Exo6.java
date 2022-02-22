package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Exo6 extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximity;

    TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo6);

        texte = (TextView) findViewById(R.id.affichage);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {

            proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);

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
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float val = event.values[0];
        System.out.println(val);
        if (val == 0) {
            texte.setText("Proche");
        } else {
            texte.setText("Loin");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}