package com.example.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Cctvlist extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv_list);
        Button btn_cctv1 = findViewById(R.id.btn_cctv1);
        Button btn_cctv2 = findViewById(R.id.btn_cctv2);
        Button btn_cctv3 = findViewById(R.id.btn_cctv3);
        Button btn_cctv4 = findViewById(R.id.btn_cctv4);
        btn_cctv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cctvlist.this,Cctv.class);
                String msg = "CCTV 1번";
                intent.putExtra("message", msg);
                startActivity(intent);
            }
        });

        btn_cctv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cctvlist.this,Cctv.class);
                String msg = "CCTV 2번";
                intent.putExtra("message", msg);
                startActivity(intent);
            }
        });

        btn_cctv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cctvlist.this,Cctv.class);
                String msg = "CCTV 3번";
                intent.putExtra("message", msg);
                startActivity(intent);
            }
        });

        btn_cctv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cctvlist.this,Cctv.class);
                String msg = "CCTV 4번";
                intent.putExtra("message", msg);
                startActivity(intent);
            }
        });
    }
}
