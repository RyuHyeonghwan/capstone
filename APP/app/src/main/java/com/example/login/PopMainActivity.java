package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PopMainActivity extends Activity implements OnClickListener{



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cctv);

        setLayout();

    }

    @Override
    public void onClick(View v){
        startActivity(new Intent(this, com.example.login.PopupActivity.class));

    }
    public void onClick2(View v){
        startActivity(new Intent(this, com.example.login.SosokActivity.class));

    }






    /*

     * Layout

     */

    private Button Btn;



    private void setLayout(){

        Btn = (Button) findViewById(R.id.btn_sos);

        Btn.setOnClickListener(this);

    }



}

