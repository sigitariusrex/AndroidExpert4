package com.kingleoners.cinemaint;

import android.database.Cursor;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.NAME;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.OVERVIEW;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.POSTER_PATH;

public class MappingHelper {
    public static ArrayList<FilmFavorite> mapCursorToArrayList(Cursor filmCursor) {
        ArrayList<FilmFavorite> filmFavoritesList = new ArrayList<>();

        while (filmCursor.moveToNext()) {
            int id = filmCursor.getInt(filmCursor.getColumnIndexOrThrow(_ID));
            String name = filmCursor.getString(filmCursor.getColumnIndexOrThrow(NAME));
            String overview = filmCursor.getString(filmCursor.getColumnIndexOrThrow(OVERVIEW));
            String poster_path = filmCursor.getString(filmCursor.getColumnIndexOrThrow(POSTER_PATH));
            filmFavoritesList.add(new FilmFavorite(id, name, overview, poster_path));
        }

        return filmFavoritesList;
    }
}
