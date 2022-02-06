package fr.sanchez.devmobile.calendrier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListeEvenements extends AppCompatActivity {

    Button vueCal;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenements);

        linear = (LinearLayout) findViewById(R.id.listLayout) ;
        vueCal = (Button) findViewById(R.id.vueCal) ;


        //clearScrollList();
        addToScrollList("Ceci est un test");



        vueCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListeEvenements.this , MainActivity.class);
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