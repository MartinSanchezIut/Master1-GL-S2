package fr.sanchez.devMobile.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CallMeActivity extends AppCompatActivity {

    String numToCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_me);

        TextView num = (TextView) this.findViewById(R.id.textView);
        Button but = (Button) this.findViewById(R.id.button);

        Intent intent = getIntent();
        numToCall = intent.getExtras().getString("number");
        num.setText(numToCall);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   <uses-permission android:name="android.permission.CALL_PHONE" />
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numToCall));
                startActivity(intent);
            }
        });


    }
}