package fr.sanchez.devmobile.calendrier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListeEvenements extends AppCompatActivity {

    Button vueCal;
    LinearLayout linear;

    ArrayList<Evenement> listeEvenements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenements);

        linear = (LinearLayout) findViewById(R.id.listLayout) ;
        vueCal = (Button) findViewById(R.id.vueCal) ;
        listeEvenements = new ArrayList<>();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        clearScrollList();
        addToScrollList("Nombre d'évenements : " + extras.getInt("CountEvents"));
        for (int i = 0; i < extras.getInt("CountEvents"); i++) {
            addToScrollList(extras.getString(i+""));
            listeEvenements.add(Evenement.stringToEvenement(extras.getString(i+""))) ;
        }



        vueCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListeEvenements.this , MainActivity.class);

                intent.putExtra("CountEvents", listeEvenements.size()) ;
                for (int i = 0; i< listeEvenements.size(); i++) {
                    intent.putExtra(i+"", listeEvenements.get(i).toString()) ;
                }

                ListeEvenements.this.startActivity(intent);
            }
        });
    }

    public void addToScrollList(String texte) {
        TextView text = new TextView(this);
        text.setText(texte);
        linear.addView(text);
    }
    public void clearScrollList() {
        linear.removeAllViews();
    }
}