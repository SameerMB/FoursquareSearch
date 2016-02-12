package com.example.sameer.foursquaresearch;

import java.util.ArrayList;

/**
 * Created by s1.ballewar on 11/2/16.
 */
public class SearchedContent {

    // list of VenueItem
    public static ArrayList<VenueItem> venueItemsList = new ArrayList<>();

    /**
     * Venue item.
     */
    public static class VenueItem {
        private String id;
        private String name;

        public VenueItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }
}
