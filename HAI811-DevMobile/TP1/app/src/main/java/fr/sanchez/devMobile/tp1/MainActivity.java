package fr.sanchez.devMobile.tp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nom, prenom, age, numero, domaine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ex1: Hello world
        //setContentView(R.layout.helloworld);
        /*
        TextView t = new TextView(this);
        t.setText("Hello world !");
        setContentView(t);
        */

        // Ex2 :
        setContentView(R.layout.form);
        nom = (EditText) this.findViewById(R.id.Nom);
        prenom = (EditText) this.findViewById(R.id.Prenom);
        age = (EditText) this.findViewById(R.id.Age);
        numero = (EditText) this.findViewById(R.id.numero);
        domaine = (EditText) this.findViewById(R.id.Domaine);


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText prenom = new EditText(this);
        prenom.setHint(getString(R.string.prenom));
        EditText nom = new EditText(this);
        nom.setHint(getString(R.string.nom));
        EditText age = new EditText(this);
        age.setHint(getString(R.string.age));
        EditText numero = new EditText(this);
        numero.setHint(getString(R.string.num));
        EditText domaine = new EditText(this);
        domaine.setHint(getString(R.string.domaine));
        Button bouton = new Button(this);
        bouton.setText(getString(R.string.valider));
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }});

        layout.addView(prenom);
        layout.addView(nom);
        layout.addView(age);
        layout.addView(numero);
        layout.addView(domaine);
        layout.addView(bouton);

        setContentView(layout);
    }

    public void click(View v) {
        AlertDialog.Builder b = new AlertDialog.Builder(this) ;
        b.setMessage(getString(R.string.alertDialog));

        b.setPositiveButton(getString(R.string.oui), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, getString(R.string.prenom) + " :" + prenom.getText().toString() +"\n"+
                        getString(R.string.nom) + " :" + nom.getText().toString() + "\n"+
                        getString(R.string.age) + " :" + age.getText().toString() + "\n"+
                        getString(R.string.num) + " :" + numero.getText().toString()+"\n"+
                        getString(R.string.domaine) + " :" + domaine.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        b.setNegativeButton(getString(R.string.non), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = b.create();
        dialog.show();
    }
}