package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.Toast;

public class Exo3 extends AppCompatActivity implements SensorEventListener {

    ConstraintLayout color;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    int vert, noir, rouge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo3);

        color = (ConstraintLayout) findViewById(R.id.color) ;
        color.setBackgroundColor(Color.CYAN);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            // Je réduit car impossible a atteindre les valeur max
            float maxValue = accelerometer.getMaximumRange() /2;

            vert = Float.valueOf(maxValue * 0f).intValue();
            noir = Float.valueOf(maxValue * 0.33f).intValue();
            rouge = Float.valueOf(maxValue * 0.66f).intValue();
            System.out.println("MAX : "   + accelerometer.getMaximumRange());
            System.out.println("1 : "   + vert);
            System.out.println("2 : "   + noir);
            System.out.println("3 : "   + rouge);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Recupérer les valeurs de l'accélérometre
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        System.out.print("x" + x);
        System.out.print(" y" + y);
        System.out.println(" z" + z);

        if ((Math.abs(x) > vert) || (Math.abs(y) > vert) || (Math.abs(z) > vert)) {
            color.setBackgroundColor(Color.GREEN);
        }
        if ((Math.abs(x) > noir) || (Math.abs(y) > noir) || (Math.abs(z) > noir)) {
            color.setBackgroundColor(Color.YELLOW);
        }
        if ((Math.abs(x) > rouge) || (Math.abs(y) > rouge) || (Math.abs(z) > rouge)) {
            color.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}