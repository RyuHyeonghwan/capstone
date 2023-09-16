package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity{
    private EditText login_id, login_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        login_id = findViewById( R.id.login_id );
        login_password = findViewById( R.id.login_password );
        btn_login = findViewById( R.id.btn_login);

        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserID = login_id.getText().toString();
                String UserPassword = login_password.getText().toString();
                String ID = UserID;


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            if(success) {//로그인 성공시
                                String UserID = jsonObject.getString( "UserID" );
                                String UserPassword = jsonObject.getString( "UserPassword" );


                                Toast.makeText( getApplicationContext(), "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( LoginActivity.this, Main2Activity.class );
                                intent.putExtra( "UserID", ID );


                                startActivity(intent);

                            } else {//로그인 실패시
                                Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest( UserID, UserPassword, responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add( loginRequest );
            }
        });
    }
}
