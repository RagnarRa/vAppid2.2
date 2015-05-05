package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.devnied.emvnfccard.R;

import java.util.ArrayList;


public class PayActivity extends Activity {
    ArrayList<Integer> prices;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Naum i text ur intent
        Intent intent = getIntent(); //Naum i Intent sem invoke-adi.. og data associated..
        prices = intent.getIntegerArrayListExtra(AddToCartActivity.EXTRA_CART_CONTENTS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Integer total = 0;
        for (Integer num : prices) {
            total += num;
        }

        TextView temp = (TextView) findViewById(R.id.text_total);
        temp.setText("Upphæð til greiðslu: " + total);

        button = (Button) findViewById(R.id.scanCard);
        button.setOnClickListener(buttonHadler);





    }

    View.OnClickListener buttonHadler = new View.OnClickListener(){
        public void onClick(View v){
            Intent i = new Intent(PayActivity.this, ScanActivity.class);
            startActivity(i);
        }
    };


    public void goBack(View view) {
        finish();
    }

    public void enterCardInfo(View view) {
        Intent intent = new Intent(this, ManualPayByCardActivity.class);
        startActivity(intent);
    }
}
