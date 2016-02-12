package com.example.sameer.foursquaresearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by s1.ballewar on 11/2/16.
 */
public class SearchListAdaptor extends BaseAdapter {
    private final Context context;
    private List<VenueContent.VenueItem> venueItemList = null;


    public SearchListAdaptor(Context context) {
//        super(context, R.layout.artist_list_item, values);
//        Log.d("AlbumListAdaptor", ".......creating with artistID:" + artistID);

        this.context = context;
        venueItemList = VenueContent.venueItemsList;
    }

    public int getCount() {
        int count = 0;
        if (venueItemList!=null)
            count = venueItemList.size();
        Log.d("AlbumListAdaptor", ".......getCount:" + count);
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

        Log.d("AlbumListAdaptor", ".......getView @ " + position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.venue_list_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.textViewAlbumTitle);
        textView.setText( venueItemList.get(position).getName() );

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewAlbumPicture);
//        Ion.with(imageView).placeholder(R.mipmap.ic_launcher).load(venueItemList.get(position).getPicture());

        return rowView;
    }
}