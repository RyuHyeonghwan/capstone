package com.example.login;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PopupActivity extends AppCompatActivity {
    Button okBtn;
    Button cancleBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        okBtn = (Button) findViewById(R.id.okBtn);
        cancleBtn = (Button) findViewById(R.id.cancleBtn);
        setContentView(R.layout.sos_popup_activity);




    }

    //동작 버튼 클릭
    public void mOk(View v){

        finish();
    }


    //취소 버튼 클릭
    public void mCancle(View v){
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
