package com.birulia.flickster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String overview = extras.getString("overview");
            String imageUrl = extras.getString("img_url");
            String voteAverage = extras.getString("vote_average");

            ImageView ivImage = (ImageView) findViewById(R.id.ivMovieImage);
            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
            RatingBar rtRating = (RatingBar) findViewById(R.id.rbRating);
            TextView tvRating = (TextView) findViewById(R.id.tvRating);

            Picasso.with(this).load(imageUrl)
                    .transform(new RoundedCornersTransformation(5, 5))
                    .into(ivImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            findViewById(R.id.ivPlay).setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {

                        }

                    });
            tvTitle.setText(title);
            tvOverview.setText(overview);
            rtRating.setRating(Float.parseFloat(voteAverage));
            tvRating.setText("Rating: " + voteAverage);


        }
    }

    public void showVideo(View view) {
        Intent videoClient = new Intent(Intent.ACTION_VIEW);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        videoClient.setData(Uri.parse("https://www.youtube.com/results?search_query=" + tvTitle.getText() + " trailer"));
        startActivity(videoClient);
    }
}
