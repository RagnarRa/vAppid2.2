package com.github.devnied.emvnfccard.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.adapter.ListViewInventoryAdapter;
import com.github.devnied.emvnfccard.adapter.ListViewRemovableAdapter;

import java.util.ArrayList;

public class CartActivity extends ListActivity {
    ArrayList<Integer> prices;
    ArrayList<Integer> quantities; //1 to 1 correspondance with prices
    ArrayList<String> listItems;
    ArrayList<String> inventoryItems; //items for sale
    Integer currentTotal;

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    //ArrayAdapter<Integer> adapter;
    ListViewRemovableAdapter adapter;
    ListViewInventoryAdapter inventoryAdapter;
    public final static String EXTRA_CART_CONTENTS = "com.rbrjas.vappid.CART_CONTENTS";
    public final static String EXTRA_CART_QUANTITIES = "com.rbrjas.vappid.CART_QUANTITIES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentTotal = 0;
        listItems = new ArrayList<String>();
        prices = new ArrayList<Integer>();
        quantities = new ArrayList<Integer>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Setting up the adapter for the cart itself
        adapter = new ListViewRemovableAdapter(listItems, this, prices, quantities);
        setListAdapter(adapter);


        inventoryItems = new ArrayList<String>();
        inventoryItems.add("Bindi 3000");
        inventoryItems.add("Slaufa 3500");
        inventoryAdapter = new ListViewInventoryAdapter(inventoryItems, this);

        ListView lwInventory = (ListView) findViewById(R.id.inventory_list);
        lwInventory.setAdapter(inventoryAdapter);


        /*
        EditText qty = (EditText) findViewById(R.id.quantity);
        qty.setText("1"); */
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

    /*
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
    } */

    public void addToCart(String item) {
        Log.d("hi2u", item);
        Integer cartIndex = containsString(item);
        Integer priceBeingAdded = 0;
        if (cartIndex == -1) {
            listItems.add(item + "x1");
            String[] parts = item.split(" ");
            priceBeingAdded = Integer.parseInt(parts[parts.length - 1]);
            prices.add(priceBeingAdded);
            quantities.add(1);
        }

        else {
            /* Thetta stak er til i index cartIndex */
            Integer quantity = quantities.get(cartIndex) + 1;
            quantities.set(cartIndex, quantity);
            String oldListItem = listItems.get(cartIndex);
            Integer quantityStartsAt = oldListItem.lastIndexOf("x");
            String newListItem = oldListItem.substring(0, (quantityStartsAt + 1)) + quantity.toString();
            listItems.set(cartIndex, newListItem);
            priceBeingAdded = prices.get(cartIndex);
        }

        TextView total = (TextView) findViewById(R.id.text_total);
        currentTotal += priceBeingAdded;
        total.setText("Heildarupphæð: " + currentTotal.toString());

        adapter.notifyDataSetChanged();
    }

    public void recalculateTotal() {
        currentTotal = 0;
        for (int i = 0; i < prices.size(); i++) {
            currentTotal += prices.get(i) * quantities.get(i);
        }

        TextView total = (TextView) findViewById(R.id.text_total);
        total.setText("Heildarupphæð: " + currentTotal.toString());
    }

    /* Ef listItems er med thetta item.. skilum index.. annars -1.. mix.. */
    private int containsString(String str) {
        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).startsWith(str)) {
                return i;
            }
        }

        return -1;
    }
}

