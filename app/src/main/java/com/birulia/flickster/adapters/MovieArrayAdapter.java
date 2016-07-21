package com.birulia.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.birulia.flickster.R;
import com.birulia.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by admin on 7/20/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {


    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Movie moview = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag
        // check the existing view being reused
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();

        }

        // clear out image from convertView
        viewHolder.ivImage.setImageResource(0);
        // populate data
        viewHolder.tvTitle.setText(moview.getOriginalTitle());

        String imageURL = "";
        int orientation = parent.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageURL = moview.getPosterPath();
            int truncateLength = 250;
            if (moview.getOriginalTitle().length() > 20){
                truncateLength = 200;
            }

            viewHolder.tvOverview.setText(ellipsis(moview.getOverview(), truncateLength));

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageURL = moview.getBackDropPath();
            viewHolder.tvOverview.setText(moview.getOverview());
        }

        Picasso.with(getContext()).load(imageURL)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(viewHolder.ivImage);


        return convertView;

    }

    public static String ellipsis(final String text, int length)
    {
        // The letters [iIl1] are slim enough to only count as half a character.
        length += Math.ceil(text.replaceAll("[^iIl]", "").length() / 2.0d);

        if (text.length() > length)
        {
            return text.substring(0, length - 3) + "...";
        }

        return text;
    }
}
