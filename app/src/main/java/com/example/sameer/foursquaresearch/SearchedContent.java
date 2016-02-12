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
        private String picture;
        private String genres;
        private String description;

        public VenueItem(String id, String name, String picture, String genres, String description) {
            this.id = id;
            this.name = name;
            this.picture = picture;
            this.genres = genres;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPicture() {
            return picture;
        }

        public String getGenres() {
            return genres;
        }

        public String getDescription() {
            return description;
        }
    }
}
