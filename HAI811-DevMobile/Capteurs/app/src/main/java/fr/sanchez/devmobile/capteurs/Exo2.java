package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Exo2 extends AppCompatActivity {

    private SensorManager mSensorManager;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo2);

        linear = (LinearLayout) findViewById(R.id.linear) ;
        int nbCapteur = 0;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        TextView t = new TextView(this);
        t.setText("     Liste des capteurs trouvés: ");
        linear.addView(t);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_ACCELEROMETER manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_ACCELEROMETER_UNCALIBRATED manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_AMBIENT_TEMPERATURE manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_GAME_ROTATION_VECTOR manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_GEOMAGNETIC_ROTATION_VECTOR manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_GRAVITY manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_GYROSCOPE manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_GYROSCOPE_UNCALIBRATED manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_HEART_BEAT manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_HEART_RATE manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_HINGE_ANGLE) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_HINGE_ANGLE manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_LIGHT manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_LINEAR_ACCELERATION manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_LOW_LATENCY_OFFBODY_DETECT manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_MAGNETIC_FIELD manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_MAGNETIC_FIELD_UNCALIBRATED manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_MOTION_DETECT manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_POSE_6DOF) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_POSE_6DOF manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_PRESSURE manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_PROXIMITY manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_RELATIVE_HUMIDITY manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_ROTATION_VECTOR manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_SIGNIFICANT_MOTION manquant");
            linear.addView(t);
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_STATIONARY_DETECT manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_STEP_COUNTER manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_STEP_DETECTOR manquant");
            linear.addView(t);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null){ //S'il n'y a pas de magnétomètre
            nbCapteur++;
            t = new TextView(this);
            t.setText("         TYPE_AMBIENT_TEMPERATURE manquant");
            linear.addView(t);
        }


        t = new TextView(this);
        t.setText("Total : " + nbCapteur);
        linear.addView(t);
    }
}