package com.example.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Cctv extends AppCompatActivity {
    Button btn_sos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);
        Button.OnClickListener btnListener = new View.OnClickListener(){

            public void onClick(View v){
                switch (v.getId()){

                    case R.id.btn_sos:

                        Intent intent = new Intent(Cctv.this, PopupActivity.class);
                        startActivityForResult(intent, 1);

                        break;

                }
            }
        };

        Intent stringIntent = getIntent();
        String message = stringIntent.getStringExtra("message");

        TextView tv = findViewById(R.id.a_cctv_time);
        tv.setText(message);
        btn_sos = (Button)findViewById(R.id.btn_sos);
        btn_sos.setOnClickListener(btnListener);
    }



}
