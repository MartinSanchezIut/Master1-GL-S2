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

        dep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                addToScrollList("Chercher trains");

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
                addToScrollList("Chercher trains");

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