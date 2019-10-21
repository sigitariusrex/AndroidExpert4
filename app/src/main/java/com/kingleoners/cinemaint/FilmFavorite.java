package com.kingleoners.cinemaint;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmFavorite implements Parcelable {

    private int id;
    private String name;
    private String overview;
    private String poster_path;


    public FilmFavorite() {

    }

    public FilmFavorite( int id, String name, String overview, String poster_path) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    protected FilmFavorite(Parcel in) {
        name = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        id = in.readInt();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<FilmFavorite> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<FilmFavorite> CREATOR = new Creator<FilmFavorite>() {
        @Override
        public FilmFavorite createFromParcel(Parcel in) {
            return new FilmFavorite(in);
        }

        @Override
        public FilmFavorite[] newArray(int size) {
            return new FilmFavorite[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeInt(id);
    }
}
