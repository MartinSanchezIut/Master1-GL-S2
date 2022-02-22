package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Exo1 extends AppCompatActivity {

    private SensorManager mSensorManager;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        linear = (LinearLayout) findViewById(R.id.linear) ;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorsList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        TextView t = new TextView(this);
        t.setText("     Liste des capteurs trouvés: ");
        linear.addView(t);
        for (Sensor s: sensorsList) {
            t = new TextView(this);
            t.setText("         " + s.getType() + " " + s.getName());
            linear.addView(t);
        }
    }
}