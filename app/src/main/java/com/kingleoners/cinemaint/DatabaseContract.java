package com.kingleoners.cinemaint;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {


    static String TABLE_NAME = "film";

    static final class FilmColumns implements BaseColumns {

        static String NAME = "name";
        static String OVERVIEW = "overview";
        static String POSTER_PATH = "poster_path";
    }
}
