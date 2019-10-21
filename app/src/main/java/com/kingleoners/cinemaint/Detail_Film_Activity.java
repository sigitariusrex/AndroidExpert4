package com.kingleoners.cinemaint;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.internal.NavigationMenuItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

import io.paperdb.Paper;

import static com.kingleoners.cinemaint.DatabaseContract.TABLE_NAME;

public class Detail_Film_Activity extends AppCompatActivity implements View.OnClickListener{

    ImageView imgCoverFilm;
    TextView txtNameFilm, txtFromFilm;
    ProgressBar loadingDetail;
    FilmHelper filmHelper;
    ImageView imgFavorite;

    public static final String EXTRA_FILM = "extra_film";
    public static final String EXTRA_FILM_FAVORITE = "extra_film_favorite";
    public static final String EXTRA_POSITION = "extra_position";

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static SQLiteDatabase database;
    private boolean isEdit = false;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_ADD = 201;

    private int position;
    private FilmFavorite filmFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__film_);

        Log.d(EXTRA_FILM,"onCreate: started.");

        imgCoverFilm = findViewById(R.id.img_coverview_film);
        txtNameFilm = findViewById(R.id.txt_name_detailfilm);
        txtFromFilm = findViewById(R.id.txt_from_detailfilm);
        imgFavorite = findViewById(R.id.img_favorite);
        loadingDetail = findViewById(R.id.progressBar);

        imgFavorite.setOnClickListener(this);

        filmHelper = FilmHelper.getInstance(getApplicationContext());
        filmHelper.open();
        filmFavorite = getIntent().getParcelableExtra(EXTRA_FILM_FAVORITE);

        if (filmFavorite != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
            imgFavorite.setVisibility(View.GONE);
            loadingDetail.setVisibility(View.VISIBLE);
        } else {
            filmFavorite = new FilmFavorite();
        }

        if (savedInstanceState != null) {
            Film film = getIntent().getParcelableExtra(EXTRA_FILM);

            txtNameFilm.setText(film.getName());
            txtFromFilm.setText(film.getOverview());
            imgCoverFilm.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(film.getPoster_path())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imgCoverFilm);

            imgFavorite.setVisibility(View.VISIBLE);
            loadingDetail.setVisibility(View.GONE);

        } else {
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Film film = getIntent().getParcelableExtra(EXTRA_FILM);

                            txtNameFilm.setText(film.getName());
                            txtFromFilm.setText(film.getOverview());

                            imgCoverFilm.setVisibility(View.VISIBLE);
                            Glide.with(Detail_Film_Activity.this)
                                    .load(film.getPoster_path())
                                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                                    .into(imgCoverFilm);

                            imgFavorite.setVisibility(View.VISIBLE);
                            loadingDetail.setVisibility(View.GONE);

                            if (getSupportActionBar() != null) {
                                getSupportActionBar().setTitle(film.getName());
                            }
                        }
                    });
                }
            }).start();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_favorite) {
            String name = txtNameFilm.getText().toString().trim();
            String from = txtFromFilm.getText().toString().trim();
            String cover = imgCoverFilm.toString().trim();

            filmFavorite.setName(name);
            filmFavorite.setOverview(from);
            filmFavorite.setPoster_path(cover);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_FILM_FAVORITE, filmFavorite);
            intent.putExtra(EXTRA_POSITION, position);

            ContentValues values = new ContentValues();

            /*
            values.put(DatabaseContract.FilmColumns.NAME, name);
            values.put(DatabaseContract.FilmColumns.OVERVIEW, from);
            values.put(DatabaseContract.FilmColumns.POSTER_PATH, cover);
            database.insert(DATABASE_TABLE, null, values);
            filmHelper.close();
            */

            if (!isEdit) {
                filmHelper.open();
                long result = filmHelper.insertFilm(filmFavorite);

                if (result > 0) {
                    filmFavorite.setId((int) result);
                    setResult(RESULT_ADD, intent);
                    Toast.makeText(Detail_Film_Activity.this, "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(Detail_Film_Activity.this, "Gagal ditambahkan", Toast.LENGTH_LONG).show();
                }
                filmHelper.close();
            }
        }
    }
}
