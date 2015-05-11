package com.github.devnied.emvnfccard.adapter;

import android.content.Context;
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
public class ListViewRemovableAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private ArrayList<Integer> prices = new ArrayList<Integer>();
    private ArrayList<Integer> quantities = new ArrayList<Integer>();



    public ListViewRemovableAdapter(ArrayList<String> list, Context context, ArrayList<Integer> prices, ArrayList<Integer> quantities) {
        this.list = list;
        this.context = context;
        this.prices = prices;
        this.quantities = quantities;
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
            view = inflater.inflate(R.layout.listview_removable_items, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                prices.remove(position);
                quantities.remove(position);
                notifyDataSetChanged();
                ((CartActivity) context).recalculateTotal();
            }
        });

        return view;
    }
}
