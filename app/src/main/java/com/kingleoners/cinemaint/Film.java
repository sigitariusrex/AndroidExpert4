package com.kingleoners.cinemaint;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Film implements Parcelable {

    private String name;
    private String overview;
    private String poster_path;

    private String URL = "https://image.tmdb.org/t/p/w154";



    /*
    private String name;
    private String from;
    private String cover;

    protected Film(Parcel in) {
        name = in.readString();
        from = in.readString();
        cover = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(from);
        dest.writeString(cover);
    }
    */

    public Film() {

    }

    public Film(String name, String overview, String poster_path, String URL) {
        this.name = name;
        this.overview = overview;
        this.poster_path = poster_path;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Film(JSONObject object){
        try {

            this.name = object.getString("name");
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
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.URL);
    }

    protected Film(Parcel in) {
        this.name = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.URL = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
