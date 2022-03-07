package fr.sanchez.devMobile.tp3;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;

public class ResumeFragment extends Fragment {

    private TextView nom, prenom, ddn, ndt, am;
    private TextView sport, musique, lecture;
    private Button valider , retour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rooter = inflater.inflate(R.layout.fragment_resume, container, false);
        valider = (Button) rooter.findViewById(R.id.button) ;
        retour = (Button) rooter.findViewById(R.id.retour) ;

        sport = (TextView) rooter.findViewById(R.id.sport) ;
        musique = (TextView) rooter.findViewById(R.id.musique) ;
        lecture = (TextView) rooter.findViewById(R.id.lecture) ;

        nom = (TextView) rooter.findViewById(R.id.nom);
        prenom = (TextView) rooter.findViewById(R.id.prenom);
        ddn = (TextView) rooter.findViewById(R.id.ddn);
        ndt = (TextView) rooter.findViewById(R.id.ndt);
        am = (TextView) rooter.findViewById(R.id.am);


        assert this.getArguments() != null;
        String form =this.getArguments().getString("Form");
        System.out.println(form);
        Form f = new Gson().fromJson(form, Form.class) ;

        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());
        ddn.setText(f.getDdn());
        ndt.setText(f.getNdt());
        am.setText(f.getAm());
        if (f.isLecture()) {lecture.setTextColor(Color.GREEN); }
        if (f.isMusique()) {musique.setTextColor(Color.GREEN); }
        if (f.isSport()) {sport.setTextColor(Color.GREEN); }


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fOut = getActivity().openFileOutput(MainActivity.fileName,0);
                    fOut.write(form.getBytes());
                    fOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "Données enregistrés !", Toast.LENGTH_LONG).show();



                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FormFragment fragment  = new FormFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                fragmentTransaction.commit();
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Form", new Gson().toJson(f));

                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FormFragment fragment  = new FormFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                fragmentTransaction.commit();
            }
        });

        return rooter;
    }
}