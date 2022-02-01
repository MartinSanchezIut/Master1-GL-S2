package fr.sanchez.devMobile.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactSheetActivity extends AppCompatActivity {

    TextView nom, prenom, age, domaine, num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sheet);

        nom = (TextView) this.findViewById(R.id.nomLabel);
        prenom = (TextView) this.findViewById(R.id.prenomLabel);
        age = (TextView) this.findViewById(R.id.ageLabel);
        num = (TextView) this.findViewById(R.id.numLabel);
        domaine = (TextView) this.findViewById(R.id.domaineLabel);


        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        System.out.println();
        nom.setText(extras.getString("nom"));
        prenom.setText(extras.getString("prenom"));
        age.setText(extras.getString("age"));
        num.setText(extras.getString("num"));
        domaine.setText(extras.getString("domaine"));


        Button call = (Button) this.findViewById(R.id.appeler) ;
        Button retour = (Button) this.findViewById(R.id.retour) ;

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactSheetActivity.this, CallMeActivity.class);
                intent.putExtra("number", num.getText().toString()) ;
                ContactSheetActivity.this.startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactSheetActivity.this, MainActivity.class);
                ContactSheetActivity.this.startActivity(intent);
            }
        });
    }
}