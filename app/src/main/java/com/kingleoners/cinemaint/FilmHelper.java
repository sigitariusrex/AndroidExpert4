package com.kingleoners.cinemaint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.NAME;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.OVERVIEW;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.POSTER_PATH;
import static com.kingleoners.cinemaint.DatabaseContract.TABLE_NAME;

public class FilmHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static FilmHelper INSTANCE;

    private static SQLiteDatabase database;

    private FilmHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FilmHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                INSTANCE = new FilmHelper(context);
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }


    public ArrayList<FilmFavorite> getAllFilm() {
        ArrayList<FilmFavorite> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null
        );
        cursor.moveToFirst();
        FilmFavorite filmFavorite;

        if (cursor.getCount() > 0) {
            do {
                filmFavorite = new FilmFavorite();
                filmFavorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                filmFavorite.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                filmFavorite.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                filmFavorite.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insertFilm(FilmFavorite filmFavorite) {
        ContentValues args = new ContentValues();
        args.put(NAME, filmFavorite.getName());
        args.put(OVERVIEW, filmFavorite.getOverview());
        args.put(POSTER_PATH, filmFavorite.getPoster_path());

        if (isAlreadyLoved(filmFavorite.getId())) {
            database.update(DATABASE_TABLE, args, "id = " + filmFavorite.getId(), null);
        } else {
            database.insert(DATABASE_TABLE, null, args);
        }

        return database.insert(DATABASE_TABLE, null, args);
    }

    public boolean isAlreadyLoved(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        boolean isFavorite = false;

        try {
            Cursor cursor;
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id =" + id; // you can check it by comparing any unique value
            cursor = db.rawQuery(sql, null);
            isFavorite = cursor.getCount() > 0;
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFavorite;
    }

    public int deleteFilm(int id) {
        return database.delete(TABLE_NAME, _ID + "='" + id + "'", null);
    }
}