package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorsList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);

        for (Sensor s: sensorsList) {
            TextView t = new TextView(this);
            t.setText(s.getType() + " " + s.getName());
            linear.addView(t);
        }


        setContentView(linear);
    }
}