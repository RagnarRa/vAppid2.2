package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.github.devnied.emvnfccard.R;


public class RegisterUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }



    public void finishRegistration(View view) {
        finish();
    }
}
