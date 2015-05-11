package com.github.devnied.emvnfccard.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.adapter.ListViewInventoryAdapter;
import com.github.devnied.emvnfccard.adapter.ListViewRemovableAdapter;

import java.util.ArrayList;

public class CartActivity extends ListActivity {
    ArrayList<Integer> prices;
    ArrayList<Integer> quantities; //1 to 1 correspondance with prices
    ArrayList<String> listItems;
    ArrayList<String> inventoryItems; //items for sale

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    //ArrayAdapter<Integer> adapter;
    ListViewRemovableAdapter adapter;
    ListViewInventoryAdapter inventoryAdapter;
    public final static String EXTRA_CART_CONTENTS = "com.rbrjas.vappid.CART_CONTENTS";
    public final static String EXTRA_CART_QUANTITIES = "com.rbrjas.vappid.CART_QUANTITIES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listItems = new ArrayList<String>();
        prices = new ArrayList<Integer>();
        quantities = new ArrayList<Integer>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Setting up the adapter for the cart itself
        adapter = new ListViewRemovableAdapter(listItems, this, prices, quantities);
        setListAdapter(adapter);


        inventoryItems = new ArrayList<String>();
        inventoryItems.add("Bindi");
        inventoryItems.add("Slaufa");
        inventoryAdapter = new ListViewInventoryAdapter(inventoryItems, this);

        ListView lwInventory = (ListView) findViewById(R.id.inventory_list);
        lwInventory.setAdapter(inventoryAdapter);



        EditText qty = (EditText) findViewById(R.id.quantity);
        qty.setText("1");
    }



    /*
    public void addToCart(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_price);
        Integer price = Integer.parseInt(editText.getText().toString());
        prices.add(price);
        editText.setText("");

        EditText qty = (EditText) findViewById(R.id.quantity);
        Integer quantity = Integer.parseInt(qty.getText().toString());
        quantities.add(quantity);
        qty.setText("1");
        listItems.add(price.toString() + "x" + quantity.toString());
        adapter.notifyDataSetChanged();
    } */

    public void goPay(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void addQty(View view){
        EditText qty = (EditText) findViewById(R.id.quantity);
        Integer quantity = Integer.parseInt(qty.getText().toString());
        quantity++;
        qty.setText(quantity.toString());
    }

    public void subtractQty(View view) {
        EditText qty = (EditText) findViewById(R.id.quantity);
        Integer quantity = Integer.parseInt(qty.getText().toString());
        quantity--;
        qty.setText(quantity.toString());
    }

    public void addToCart(String item) {
        Log.d("hi2u", item);
    }
}

