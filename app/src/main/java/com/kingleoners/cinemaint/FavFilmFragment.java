package com.kingleoners.cinemaint;


import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.NAME;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.OVERVIEW;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.POSTER_PATH;
import static com.kingleoners.cinemaint.DatabaseContract.TABLE_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavFilmFragment extends Fragment implements LoadFilmFavoriteCallback{

    View view;
    private ProgressBar progressBar;
    private RecyclerView rvFilmFavorites;

    private ArrayList<FilmFavorite> listFilmFavorite = new ArrayList<>();
    private FavoriteFilmAdapter listFilmFavoriteAdapter;
    private FilmHelper filmHelper;

    private SQLiteDatabase database;
    private static DatabaseHelper databaseHelper;
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static final String SEND_STATE = "SEND_STATE";
    private static final String EXTRA_STATE = "EXTRA_STATE";

    private Context context;
    private Cursor cursor;

    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;


    public FavFilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fav_film, container, false);

        rvFilmFavorites = view.findViewById(R.id.rv_filmfavorites);


        filmHelper = FilmHelper.getInstance(getContext());
        filmHelper.open();

        rvFilmFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        listFilmFavoriteAdapter = new FavoriteFilmAdapter(getContext(), filmHelper.getAllFilm());



        rvFilmFavorites.setHasFixedSize(true);
        rvFilmFavorites.setAdapter(listFilmFavoriteAdapter);


        if (savedInstanceState == null) {
            new LoadFilmFavoriteAsync(filmHelper, this).execute();
        } else {
            ArrayList<FilmFavorite> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                listFilmFavoriteAdapter.setListFavoriteFilm(list);
            }
        }



        return view;

    }


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Detail_Film_Activity.REQUEST_ADD) {
            if (resultCode == Detail_Film_Activity.RESULT_ADD) {
                FilmFavorite filmFavorite = data.getParcelableExtra(Detail_Film_Activity.EXTRA_FILM_FAVORITE);
                listFilmFavoriteAdapter.addItem(filmFavorite);
                rvFilmFavorites.smoothScrollToPosition(listFilmFavoriteAdapter.getItemCount() + 1);
            }
        }
    }
    */



    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, listFilmFavoriteAdapter.getListFavoriteFilm());
    }


    @Override
    public void preExecute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }



    @Override
    public void postExecute(ArrayList<FilmFavorite> filmFavorites) {


        if (filmFavorites.size() > 0) {
            listFilmFavoriteAdapter.setListFavoriteFilm(filmFavorites);
        } else {
            listFilmFavoriteAdapter.setListFavoriteFilm(new ArrayList<FilmFavorite>());
        }

    }


    private static class LoadFilmFavoriteAsync extends AsyncTask<Void, Void, ArrayList<FilmFavorite>> {

        private final WeakReference<FilmHelper> weakFilmHelper;
        private final WeakReference<LoadFilmFavoriteCallback> weakCallback;

        private LoadFilmFavoriteAsync(FilmHelper filmHelper, LoadFilmFavoriteCallback callback) {
            weakFilmHelper= new WeakReference<>(filmHelper);
            weakCallback = new WeakReference<>(callback);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }



        @Override
        protected ArrayList<FilmFavorite> doInBackground(Void... voids) {
            Cursor dataCursor = weakFilmHelper.get().queryAll();
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<FilmFavorite> filmFavorites) {
            super.onPostExecute(filmFavorites);

            weakCallback.get().postExecute(filmFavorites);

        }

    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        filmHelper.close();
    }

}


interface LoadFilmFavoriteCallback{
    void preExecute();
    void postExecute(ArrayList<FilmFavorite> filmFavorites);
}


