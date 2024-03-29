package com.example.login;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://dc7178.dothome.co.kr/Login.php";
    private Map<String, String> map;

    public LoginRequest(String UserID, String UserPassword, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("UserID", UserID);
        map.put("UserPassword", UserPassword);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

