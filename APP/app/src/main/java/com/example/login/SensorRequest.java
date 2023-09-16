package com.example.login;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class SensorRequest extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://dc7178.dothome.co.kr/sensor.php";
    private Map<String, String> map;

    public SensorRequest(String userID, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        Log.i("userID",userID);

        map = new HashMap<>();
        map.put("userID", userID);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}