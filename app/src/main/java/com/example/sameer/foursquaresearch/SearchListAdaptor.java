package com.example.sameer.foursquaresearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by s1.ballewar on 11/2/16.
 */
public class SearchListAdaptor extends BaseAdapter {
    private final Context context;
    private List<SearchedContent.VenueItem> venueItemList = null;


    public SearchListAdaptor(Context context) {
        this.context = context;
        venueItemList = SearchedContent.venueItemsList;
    }

    public int getCount() {
        int count = 0;
        if (venueItemList!=null)
            count = venueItemList.size();
        Log.d("SearchListAdaptor", ".......getCount:" + count);
        return count;
    }

    public Object getItem(int position) {
        Object item = null;
        if (venueItemList!=null)
            item = venueItemList.get(position);
        return item;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d("SearchListAdaptor", ".......getView @ " + position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.search_list_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.textViewAlbumTitle);
        textView.setText( venueItemList.get(position).getName() );

        return rowView;
    }
}