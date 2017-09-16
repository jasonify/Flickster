package com.example.jason.flickster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jason.flickster.R;
import com.example.jason.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jason on 9/12/17.
 */


// TODO Heterogenous views
    // [ ] 2 view, one with a bg poster movies with > 5 stars (prop named: vote_average)
    // [ ] create the layout type  with background


public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView movieImage;
        ImageView postertImage;
        int viewType;

        // TODO: viewholder for photo?
    }
    public MovieArrayAdapter(Context context, List<Movie>movies) {
     super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;

        int viewType = getItemViewType(position);
        if(convertView == null) {
            viewHolder = new ViewHolder();

            convertView = getInflatedLayoutForType(viewType);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (EditText) convertView.findViewById(R.id.tvOverview);
            viewHolder.movieImage =  (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.postertImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
            viewHolder.viewType = viewType;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            // Check if convertView is of same type as we are loading now:
            if (viewType != viewHolder.viewType) {
                // Need to change out views:
                viewHolder = new ViewHolder();

                convertView = getInflatedLayoutForType(viewType);
                // TODO: refactor this is repeated code!
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.overview = (EditText) convertView.findViewById(R.id.tvOverview);
                viewHolder.movieImage =  (ImageView) convertView.findViewById(R.id.ivMovieImage);
                viewHolder.postertImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);


                viewHolder.viewType = viewType;
                convertView.setTag(viewHolder);
            }
        }

        if (viewHolder.title != null ) {
            viewHolder.title.setText( Integer.toString(position) +  " - " + movie.getOriginalTitle() );
        }
        if (viewHolder.overview != null) {
            viewHolder.overview.setText(movie.getOverview());
        }

        if (   viewHolder.postertImage != null ) {
            viewHolder.postertImage.setImageResource(0);

            Picasso.with(getContext()).load(movie.getBackgroundPath())
                    .fit().centerCrop()
                    .placeholder(R.drawable.loadingsmall)
                    .error(R.drawable.error)
                    .into(viewHolder.postertImage);
        }

        if ( viewHolder.movieImage != null ) {
            viewHolder.movieImage.setImageResource(0);

            Picasso.with(getContext()).load(movie.getPosterPath())
                    .fit().centerCrop()
                    .placeholder(R.drawable.loadingsmall)
                    .error(R.drawable.error)
                    .into(viewHolder.movieImage);
        }




        return convertView;
    }

    // Heterogenous Views


    @Override
    public int getViewTypeCount() {
         return 2;
//        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = getItem(position);
        if (movie.getRating() >= 5.0) {
            return 0
;        } else {
            return 1;
        }
        // return super.getItemViewType(position);
    }

    private View getInflatedLayoutForType(int type) {
        LayoutInflater inflater  = LayoutInflater.from(getContext());

        Log.d("type", "type: " + Integer.toString(type));
        if (type == 0 ) {
            return inflater.inflate(R.layout.item_movie_toprated, null);
        } else  if (type == 1) {
            return inflater.inflate(R.layout.item_movie, null);
        }

        return null;
    }
}
