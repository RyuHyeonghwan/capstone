package com.example.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyPage extends AppCompatActivity {
    private TextView u_id;
    private EditText u_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        u_id = findViewById(R.id.u_id);
        u_pw = (EditText) findViewById(R.id.u_pw);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        u_id.setText(userID);



        Button btn_up = findViewById(R.id.btn_up);
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = u_id.getText().toString();
                String userPassword = u_pw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            if(success) {//회원 정보 수정 성공시
                                Toast.makeText( getApplicationContext(), "회원 정보 수정에 성공하셨습니다.", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( MyPage.this, LoginActivity.class );
                                startActivity(intent);

                            } else {//회원 수정 실패시
                                Toast.makeText( getApplicationContext(), "회원 정보 수정 실패", Toast.LENGTH_SHORT ).show();
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                UpdateRequest updateRequest = new UpdateRequest( userID, userPassword, responseListener );
                RequestQueue queue = Volley.newRequestQueue( MyPage.this );
                queue.add( updateRequest );
            }
        });

    }
}