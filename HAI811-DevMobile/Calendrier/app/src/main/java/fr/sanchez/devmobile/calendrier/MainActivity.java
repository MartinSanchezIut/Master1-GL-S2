package fr.sanchez.devmobile.calendrier;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarView cal;
    EditText evnt;
    Button ajouter, vueEvent;

    ArrayList<Evenement> listeEvenements;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeEvenements = new ArrayList<>();
        cal = (CalendarView) findViewById(R.id.calendarView);
        evnt = (EditText) findViewById(R.id.eventInput);
        ajouter = (Button) findViewById(R.id.button) ;
        vueEvent = (Button) findViewById(R.id.VueEvnt) ;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = sdf.format(new Date(cal.getDate()));

        Intent inte = getIntent();
        Bundle extras = inte.getExtras();
        if (extras != null) {
            for (int i = 0; i < extras.getInt("CountEvents"); i++) {
                listeEvenements.add(Evenement.stringToEvenement(extras.getString(i+""))) ;
            }
        }else {
            for(int i = 0; i<10; i++) {
                listeEvenements.add(new Evenement(date, "Test généré en dur !"));
            }
        }

        vueEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ListeEvenements.class);
                intent.putExtra("CountEvents", listeEvenements.size()) ;
                for (int i = 0; i< listeEvenements.size(); i++) {
                    intent.putExtra(i+"", listeEvenements.get(i).toString()) ;
                }
                MainActivity.this.startActivity(intent);
            }
        });
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month+1);

                MainActivity.this.date = curDate + "/" + Month + "/" + Year;
            }
        });
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (evnt.getText().length() > 0) {
                    Toast.makeText(MainActivity.this, getString(R.string.taskadded) + "\n" +
                             evnt.getText() + "\n" + date , Toast.LENGTH_LONG).show();

                    listeEvenements.add(new Evenement(date, evnt.getText().toString()));
                    evnt.setText("");
                }
            }
        });
    }
}