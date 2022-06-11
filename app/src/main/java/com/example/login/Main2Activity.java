package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainview);

        Button btn_cctv = findViewById(R.id.btn_cctv);
        Button btn_sensor = findViewById(R.id.btn_sensor);
        Button btn_system = findViewById(R.id.btn_system);
        Button btn_my = findViewById(R.id.btn_my);

        btn_cctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this, com.example.login.Cctvlist.class);
                startActivity(intent);
            }
        });

        btn_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,com.example.login.Sensor.class);
                startActivity(intent);
            }
        });

        btn_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this, com.example.login.System.class);
                startActivity(intent);
            }
        });
        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stringIntent = getIntent();
                String userID = stringIntent.getStringExtra("userID");

                Intent intent=new Intent(Main2Activity.this, com.example.login.MyPage.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

    }
}