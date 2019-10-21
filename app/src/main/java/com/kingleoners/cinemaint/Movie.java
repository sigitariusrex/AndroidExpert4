package com.kingleoners.cinemaint;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {
    private String title;
    private String overview;
    private String poster_path;

    private String URL = "https://image.tmdb.org/t/p/w154";

    public Movie() {

    }

    public Movie(String title, String overview, String poster_path, String URL) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public Movie(JSONObject object){
        try {

            this.title = object.getString("title");
            this.overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            this.poster_path = URL + poster_path;

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.URL);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.URL = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
