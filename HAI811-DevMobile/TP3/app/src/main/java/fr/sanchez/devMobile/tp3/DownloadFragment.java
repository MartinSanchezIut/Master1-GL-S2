package fr.sanchez.devMobile.tp3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class DownloadFragment extends Fragment {


    EditText id ;
    Form f ;
    boolean hasended = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rooter = inflater.inflate(R.layout.fragment_download, container, false);


        assert this.getArguments() != null;
        String form =this.getArguments().getString("Form");

        rooter.findViewById(R.id.ret).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Form", form);

                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FormFragment fragment  = new FormFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                fragmentTransaction.commit();
            }
        });

        id = (EditText) rooter.findViewById(R.id.edittext) ;
        id.setText("6231f282cdbb5e37f295acad");

        RequestQueue queue = Volley.newRequestQueue(rooter.getContext());
        rooter.findViewById(R.id.val).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="http://192.168.0.13:8888/form/_id/" + id.getText();
                System.out.println(url);
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson(); //Create a Gson object
                                System.out.println("\n\n");
                                System.out.println("nanani " + response);
                                System.out.println("\n\n");
                                f = gson.fromJson(response, Form.class);
                                hasended = true;

                                Bundle bundle = new Bundle();
                                bundle.putString("Form", new Gson().toJson(f));

                                assert getFragmentManager() != null;
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                ResumeFragment fragment  = new ResumeFragment();
                                fragment.setArguments(bundle);
                                fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
                                fragmentTransaction.commit();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erreur de téléchargement !");
                    }
                });
                queue.add(stringRequest);

            }
        });



        return rooter;
    }
}