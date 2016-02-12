package com.example.sameer.foursquaresearch;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // URL to get venues JSON
    private String jsonURLFormat = "https://api.foursquare.com/v2/venues/search" +
            "?client_id=00VLX0MBZAR3T0LXJ1OY3R1MQTUYSRBSW1J23OWPXDBJ4XUJ" +
            "&client_secret=0USL31TB2CLEZSGLNFZ4ZTSMVQA5FYE1TJS0P1OTKKRV4SGG" +
            "&v=20130815" +
            "&ll=40.7,-74" +
            "&query=";

    private String jsonURL;

    // JSON Node names
    private static final String TAG_RESPONSE = "response";
    private static final String TAG_VENUES = "venues";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton)findViewById(R.id.search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                jsonURL = jsonURLFormat + editText.getText().toString().trim();

                //clear existing list
                SearchedContent.venueItemsList.clear();

                // Calling async task to get Venues from json
                new GetVenues().execute();
            }
        });
    }


    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetVenues extends AsyncTask<Void, Void, Boolean> {

        // venues JSONArray
        JSONArray venues = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            // Showing progress dialog
//            pDialog = new ProgressDialog(getContext());
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            boolean result = false;

            // Creating service handler class instance
            httpHandler sh = new httpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(jsonURL, httpHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);//string to JSON object
                    jsonObj = jsonObj.getJSONObject(TAG_RESPONSE);//get response json object
                    // Getting JSON Array node
                    venues = jsonObj.getJSONArray(TAG_VENUES);

                    // looping through All venues
                    for (int i = 0; i < venues.length(); i++) {
                        JSONObject c = venues.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);

                        // adding venues to venues list
                        SearchedContent.VenueItem item = new SearchedContent.VenueItem(id, name);
                        SearchedContent.venueItemsList.add(item);
                    }
                    result = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("httpHandler", "Couldn't get any data from the url");
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
//            // Dismiss the progress dialog
//            if (pDialog.isShowing())
//                pDialog.dismiss();

            if (result)//success
                ((ListView)findViewById(R.id.searchListView)).setAdapter(new SearchListAdaptor(getApplicationContext()));
            else {//failed to get the data
                // adding a dummy item to show no data!
                SearchedContent.VenueItem item = new SearchedContent.VenueItem(null, "No result found!!!");
                SearchedContent.venueItemsList.add(item);
            }
        }
    }
}
