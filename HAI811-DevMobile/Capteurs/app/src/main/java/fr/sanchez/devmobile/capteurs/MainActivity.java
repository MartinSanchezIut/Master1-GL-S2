package fr.sanchez.devmobile.capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        TextView t = new TextView(this);
        t.setText("     Liste des capteurs trouvés: ");
        linear.addView(t);
        for (Sensor s: sensorsList) {
            t = new TextView(this);
            t.setText("         " + s.getType() + " " + s.getName());
            linear.addView(t);
        }

        // ----------------------------------------------------------------

        Button bEx2 = new Button(this);
        bEx2.setText("Exercice 2");
        bEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent vers l'ex 2
            }
        });
        linear.addView(bEx2);

        // ----------------------------------------------------------------

        Button bEx3 = new Button(this);
        bEx3.setText("Exercice 3");
        bEx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Exo3.class);
                startActivity(i);
            }
        });
        linear.addView(bEx3);

        // ----------------------------------------------------------------

        Button bEx4 = new Button(this);
        bEx4.setText("Exercice 4");
        bEx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent vers l'ex 4
            }
        });
        linear.addView(bEx4);

        // ----------------------------------------------------------------

        Button bEx5 = new Button(this);
        bEx5.setText("Exercice 5");
        bEx5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Exo5.class);
                startActivity(i);            }
        });
        linear.addView(bEx5);

        // ----------------------------------------------------------------

        Button bEx6 = new Button(this);
        bEx6.setText("Exercice 6");
        bEx6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent vers l'ex 6
            }
        });
        linear.addView(bEx6);

        // ----------------------------------------------------------------

        Button bEx7 = new Button(this);
        bEx7.setText("Exercice 7");
        bEx7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent vers l'ex 7
            }
        });
        linear.addView(bEx7);




        setContentView(linear);
    }
}