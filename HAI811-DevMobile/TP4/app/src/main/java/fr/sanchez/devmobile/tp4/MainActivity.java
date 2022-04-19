package fr.sanchez.devmobile.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.sanchez.devmobile.tp4.model.Form;

public class MainActivity extends AppCompatActivity {

    EditText IDnom;
    EditText IDprenom;
    EditText IDage;
    EditText IDnumero;
    EditText IDndp;

    Button soumettre;
    Button planing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IDnom = (EditText) findViewById(R.id.idNom);
        IDprenom = (EditText) findViewById(R.id.idPrenom);
        IDage = (EditText) findViewById(R.id.idage);
        IDnumero = (EditText) findViewById(R.id.idnum);
        IDndp = (EditText) findViewById(R.id.idmdp);


        soumettre = (Button) findViewById(R.id.idsoumettre);
        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormViewer.class);
                Form f = new Form(IDnom.getText().toString(),IDprenom.getText().toString(),IDage.getText().toString(),IDnumero.getText().toString(),IDndp.getText().toString());
                intent.putExtra("FORMULAIRE",f);
                startActivityForResult(intent, 200);
                // startActivity(intent);
            }
        });

        planing = (Button) findViewById(R.id.idplanning);
        planing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Planning.class);
                startActivity(intent);
            }
        });
    }
}