package com.birulia.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 7/20/16.
 */
public class Movie {
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w640/%s", backDropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }
    public String getOverview() {
        return overview;
    }
    public Double getVoteAverage() {
        return voteAverage;
    }
    public Boolean getHasVideo() {
        return hasVideo;
    }

    String posterPath;
    String originalTitle;
    String overview;
    String backDropPath;
    Double voteAverage;
    Boolean hasVideo;

    public Movie(JSONObject jsonObj) throws JSONException {
        this.posterPath = jsonObj.getString("poster_path");
        this.backDropPath = jsonObj.getString("backdrop_path");
        this.originalTitle = jsonObj.getString("original_title");
        this.overview = jsonObj.getString("overview");
        this.voteAverage = jsonObj.getDouble("vote_average");
        this.hasVideo = jsonObj.getBoolean("video");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray){
        ArrayList<Movie> resultArray = new ArrayList<>();

        for (int x=0; x< jsonArray.length(); x++){
            try {
                resultArray.add(new Movie(jsonArray.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultArray;
    }
}
