package fr.sanchez.devMobile.train;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    EditText dep, arr;
    ScrollView scroll;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dep = (EditText) findViewById(R.id.depart);
        arr = (EditText) findViewById(R.id.arrive);
        scroll = (ScrollView) findViewById(R.id.scroll);
        linear = (LinearLayout) findViewById(R.id.linear);

        ArrayList<Train> listeTrains = Train.makeTrainList(100) ;
        addToScrollList("Liste des trains par defaut");
        for (Train t: listeTrains) {
            addToScrollList(t.toString());
        }


        dep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                clearScrollList();

                if (dep.getText().length() == 0) {
                    addToScrollList("Liste des trains par defaut");
                    for (Train t: listeTrains) {
                        addToScrollList(t.toString());
                    }
                }else {
                    for (Train t: listeTrains) {
                        if (dep.getText().length() > 0 &&
                                (t.getDep().toUpperCase().contains(dep.getText().toString().toUpperCase()))) {

                            if (arr.getText().length() > 0) {
                                if (t.getArr().toUpperCase().contains(arr.getText().toString().toUpperCase())){
                                    addToScrollList(t.toString());
                                }
                            }else {
                                addToScrollList(t.toString());
                            }
                        }
                    }
                }

            }
        });

        arr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                clearScrollList();

                if (arr.getText().length() == 0) {
                    addToScrollList("Liste des trains par defaut");
                    for (Train t: listeTrains) {
                        addToScrollList(t.toString());
                    }
                }else {
                    for (Train t: listeTrains) {
                        if (arr.getText().length() > 0 &&
                                (t.getArr().toUpperCase().contains(arr.getText().toString().toUpperCase()))) {

                            if (dep.getText().length() > 0) {
                                if (t.getDep().toUpperCase().contains(dep.getText().toString().toUpperCase())) {
                                    addToScrollList(t.toString());
                                }
                            }else {
                                addToScrollList(t.toString());
                            }
                        }
                    }
                }

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