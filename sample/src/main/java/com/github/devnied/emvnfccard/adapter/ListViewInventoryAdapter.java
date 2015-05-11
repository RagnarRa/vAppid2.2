package com.github.devnied.emvnfccard.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.activity.CartActivity;

import java.util.ArrayList;

/**
 * Created by Ragnar on 5.5.2015.
 *
 */
public class ListViewInventoryAdapter extends BaseAdapter implements ListAdapter, Filterable {
    private ArrayList<String> list;
    private ArrayList<String> originalList; //Notad f. filtering
    private Context context;



    public ListViewInventoryAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.originalList = list;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //Log.d(Constants.TAG, "**** PUBLISHING RESULTS for: " + constraint);
                //myData = (List<MyDataType>) results.values;
                list = (ArrayList<String>) results.values;
                ListViewInventoryAdapter.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //Log.d(Constants.TAG, "**** PERFORM FILTERING for: " + constraint);
                //List<MyDataType> filteredResults = getFilteredResults(constraint);
                ArrayList<String> filteredResults = getFilteredResults(constraint);

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }

            private ArrayList<String> getFilteredResults(CharSequence constraint) {
                ArrayList<String> newList = new ArrayList<String>();

                if (constraint.toString().isEmpty()) {
                    newList = originalList;
                }
                else {
                    String searchString = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalList.size(); i++) {
                        if (originalList.get(i).toLowerCase().startsWith(searchString)) {
                            newList.add(originalList.get(i));
                        }
                    }
                }

                return newList;
            }
        };
    }
}
