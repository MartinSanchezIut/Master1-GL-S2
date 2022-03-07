package fr.sanchez.devMobile.tp3;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FormFragment extends Fragment {

    private EditText nom, prenom, ddn, ndt, am;
    private CheckBox sport, musique, lecture, synchroniser;
    private Button soumettre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rooter = inflater.inflate(R.layout.fragment_form, container, false);
        soumettre = (Button) rooter.findViewById(R.id.button) ;

        sport = (CheckBox) rooter.findViewById(R.id.sport) ;
        musique = (CheckBox) rooter.findViewById(R.id.musique) ;
        lecture = (CheckBox) rooter.findViewById(R.id.lecture) ;

        synchroniser = (CheckBox) rooter.findViewById(R.id.sync) ;

        nom = (EditText) rooter.findViewById(R.id.nom);
        prenom = (EditText) rooter.findViewById(R.id.prenom);
        ddn = (EditText) rooter.findViewById(R.id.ddn);
        ndt = (EditText) rooter.findViewById(R.id.ndt);
        am = (EditText) rooter.findViewById(R.id.am);



        File file = new File(getActivity().getFilesDir() + "/" + MainActivity.fileName);
        if(file.exists() && file.length() > 0){
            // Si des données existend dans le fichier
            try {
                InputStream input= getActivity().openFileInput(MainActivity.fileName);
                System.out.println("Recup des info dans le fichier");

                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();

                String text = new String(buffer);
                Form f = new Gson().fromJson(text, Form.class);

                nom.setText(f.getNom());
                prenom.setText(f.getPrenom());
                ddn.setText(f.getDdn());
                ndt.setText(f.getNdt());
                am.setText(f.getAm());
                lecture.setChecked(f.isLecture());
                musique.setChecked(f.isMusique());
                sport.setChecked(f.isSport());

                // Apres avoir recup des donnée on vide le fichier
                getActivity().openFileOutput(MainActivity.fileName,0).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            // Fichier innexistant ou vide
            // Si des données existent déja dans le bundle
            if (this.getArguments() != null) {
                System.out.println("Recup des info dans le bundle");
                String form = this.getArguments().getString("Form");
                System.out.println(form);
                Form f = new Gson().fromJson(form, Form.class);
                nom.setText(f.getNom());
                prenom.setText(f.getPrenom());
                ddn.setText(f.getDdn());
                ndt.setText(f.getNdt());
                am.setText(f.getAm());
                lecture.setChecked(f.isLecture());
                musique.setChecked(f.isMusique());
                sport.setChecked(f.isSport());
            }
        }

        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Form f = new Form(nom.getText().toString(),
                                  prenom.getText().toString(),
                                  ddn.getText().toString(),
                                  ndt.getText().toString(),
                                  am.getText().toString(),
                                  sport.isChecked(),
                                  musique.isChecked(),
                                  lecture.isChecked());

                bundle.putString("Form", new Gson().toJson(f));

                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                ResumeFragment fragment  = new ResumeFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                fragmentTransaction.commit();
            }
        });

        return rooter;
    }
}