package fr.sanchez.devmobile.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Planning extends AppCompatActivity {

    private TextView H1;
    private TextView H2;
    private TextView H3;
    private TextView H4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);


        String file_name=getFilesDir() + "/"+"donner.json";
        File t = new File(file_name);
        if(!t.exists()) {
            init();
        }
        H1 = (TextView) findViewById(R.id.id8h);
        H2 = (TextView) findViewById(R.id.id10h);
        H3 = (TextView) findViewById(R.id.id14h);
        H4 = (TextView) findViewById(R.id.id16h);

        PlanningControllers planningModel = new ViewModelProvider(this).get(PlanningControllers.class);
        planningModel.init(getApplicationContext());

        H1.setText(planningModel.getCreneaux1());
        H2.setText(planningModel.getCreneaux2());
        H3.setText(planningModel.getCreneaux3());
        H4.setText(planningModel.getCreneaux4());

    }

    public void retour(View view) {
        Intent intention = new Intent(Planning.this, MainActivity.class);
        startActivity(intention);
    }


    public void init(){
        ArrayList<String[]> planning =  new ArrayList<>();
        String[] creneaux1= new String[5];
        creneaux1[0] = "24/03/2022";
        creneaux1[1] = "Rencontre client Dupont";
        creneaux1[2] = "Travailler le dossier recrutement";
        creneaux1[3] = "Réunion équipe";
        creneaux1[4] = "Préparation dossier vente";
        String[] creneaux2= new String[5];
        creneaux2[0] = "25/03/2022";
        creneaux2[1] = "Rencontre client Durant";
        creneaux2[2] = "Travailler le dossier pradeo";
        creneaux2[3] = "Réunion équipe avec pradeo";
        creneaux2[4] = "Préparation dossier achat";
        String[] creneaux3= new String[5];
        creneaux3[0] = "26/03/2022";
        creneaux3[1] = "TEST1";
        creneaux3[2] = "TEST2";
        creneaux3[3] = "TEST3";
        creneaux3[4] = "TEST4";
        planning.add(creneaux1);
        planning.add(creneaux2);
        planning.add(creneaux3);

        Gson gson = new Gson();
        String json = gson.toJson(planning);

        try {
            FileOutputStream fOut = openFileOutput("donner.json",0);
            fOut.write(json.getBytes());
            fOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}