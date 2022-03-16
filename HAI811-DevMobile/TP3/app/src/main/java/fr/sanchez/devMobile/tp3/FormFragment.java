package fr.sanchez.devMobile.tp3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Button telecharger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rooter = inflater.inflate(R.layout.fragment_form, container, false);
        soumettre = (Button) rooter.findViewById(R.id.button) ;
        telecharger = (Button) rooter.findViewById(R.id.button2) ;

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
               // System.out.println(form);
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

                RequestQueue queue = Volley.newRequestQueue(rooter.getContext());
                // Si synchroniser == true alors envoyer au serveur
                if (synchroniser.isChecked()) {
                    try {
                        String url = "http://192.168.0.13:8888/form/add";
                        JSONObject parameters = new JSONObject(new Gson().toJson(f));

                        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, parameters,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                    //    Context context = FormFragment.this.getActivity().getApplicationContext();
                                    //    Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                                    }
                                },  new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            //    Context context = FormFragment.this.getActivity().getApplicationContext();
                            //    Toast.makeText(context, "Erreur !", Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(postRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                ResumeFragment fragment  = new ResumeFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                fragmentTransaction.commit();
            }
        });

        telecharger.setOnClickListener(new View.OnClickListener() {
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
                DownloadFragment fragment  = new DownloadFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                fragmentTransaction.commit();
            }
        });

        return rooter;
    }
}