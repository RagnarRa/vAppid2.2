package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.devnied.emvnfccard.R;

import java.util.ArrayList;


public class AddToCartActivity extends Activity {
    ArrayList<Integer> prices;
    public final static String EXTRA_CART_CONTENTS = "com.rbrjas.vappid.CART_CONTENTS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prices = new ArrayList<Integer>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
    }




    public void addToCart(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_price);
        Integer price = Integer.parseInt(editText.getText().toString());
        prices.add(price);
        editText.setText("");
    }

    public void goPay(View view) {
        Intent intent = new Intent(this, PayActivity.class);
        intent.putIntegerArrayListExtra(EXTRA_CART_CONTENTS, prices);
        startActivity(intent);
    }
}
