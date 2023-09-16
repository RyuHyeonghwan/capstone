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

public class Sensor extends AppCompatActivity {
    private TextView a_cctv_time;
    private TextView co;
    private TextView fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        a_cctv_time = findViewById(R.id.a_cctv_time);
        co = findViewById(R.id.co);
        fire = findViewById(R.id.fire);

        String userID = "test";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );
                    if(success) {
                        int sensorNumber = jsonObject.getInt( "SensorNumber" );
                        double coValue = jsonObject.getDouble( "CoValue" );
                        String measure_TIME = jsonObject.getString( "Measure_TIME" );

                        a_cctv_time.setText(Integer.toString(sensorNumber));
                        co.setText(Double.toString(coValue));
                        fire.setText(measure_TIME);

                    } else {
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SensorRequest sensorRequest = new SensorRequest( userID, responseListener );
        RequestQueue queue = Volley.newRequestQueue( Sensor.this );
        queue.add( sensorRequest );
    }
}
