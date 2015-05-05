package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.devnied.emvnfccard.R;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }




    public void doSell(View view) {
        Intent intent = new Intent(this, AddToCartActivity.class);
        startActivity(intent);
    }
}
