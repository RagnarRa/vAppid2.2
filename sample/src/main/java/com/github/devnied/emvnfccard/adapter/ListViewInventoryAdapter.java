package com.github.devnied.emvnfccard.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.activity.CartActivity;

import java.util.ArrayList;

/**
 * Created by Ragnar on 5.5.2015.
 *
 */
public class ListViewInventoryAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;



    public ListViewInventoryAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_inventory, null);
        }

        //Handle TextView and display string from your list
        /*
        TextView listItemText = (TextView)view.findViewById(R.id.list_item);
        listItemText.setText(list.get(position)); */

        //Setjum textann a takkanum a nafn voru
        Button btnAddItem = (Button)view.findViewById(R.id.btn_add_item);
        btnAddItem.setText(list.get(position));

        /* Handle on click (add item to cart) */
        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                //list.remove(position); //or some other task
                //Kollum a addToCart i parent activity..
                ((CartActivity) context).addToCart((((Button) v).getText()).toString());
                notifyDataSetChanged();
            }
        });

        /*btnAddItem.setOnClickListener(((CartActivity)this.context).meow); */

        return view;
    }
}
