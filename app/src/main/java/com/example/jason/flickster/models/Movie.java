package com.example.jason.flickster.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jason on 9/12/17.
 */

public class Movie {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    String posterPath;
    String originalTitle;
    String overview;
    Double rating;

    public String getBackgropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backgropPath);
    }

    String backgropPath;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backgropPath = jsonObject.getString("backdrop_path");
        this.rating =  jsonObject.getDouble("vote_average");
        Log.d("rating.. ", Double.toString(rating));
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray movies) {
        ArrayList<Movie> results = new ArrayList<Movie>();

        for(int i = 0; i <  movies.length(); i++) {
            try {
                results.add(new Movie(movies.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}
