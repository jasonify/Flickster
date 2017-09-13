package com.example.jason.flickster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jason.flickster.R;
import com.example.jason.flickster.models.Movie;

import java.util.List;

/**
 * Created by jason on 9/12/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie>movies) {
     super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);

        if(convertView == null) {
            LayoutInflater inflater  = LayoutInflater.from(getContext());
            convertView =  inflater.inflate(R.layout.item_movie, parent, false);
        }

        ImageView ivImage =  (ImageView) convertView.findViewById(R.id.ivMovieImage);
        ivImage.setImageResource(0);

        EditText tvTitle = (EditText) convertView.findViewById(R.id.tvTitle);
        EditText tvOverview = (EditText) convertView.findViewById(R.id.tvOverview);

        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());


        // check if existing view is getting reused
        return super.getView(position, convertView, parent);
    }
}
