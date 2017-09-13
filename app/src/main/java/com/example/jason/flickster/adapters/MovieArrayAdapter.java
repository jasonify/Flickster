package com.example.jason.flickster.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.jason.flickster.models.Movie;

import java.util.List;

/**
 * Created by jason on 9/12/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie>movies) {
     super(context, android.R.layout.simple_list_item_1, movies);
    }

}
