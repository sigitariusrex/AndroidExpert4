package com.kingleoners.cinemaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_Movie_Activity extends AppCompatActivity {

    ImageView imgCoverMovie;
    TextView txtNameMovie, txtFromMovie;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__movie_);

        Log.d(EXTRA_MOVIE,"onCreate: started.");

        imgCoverMovie = findViewById(R.id.img_coverview_movie);
        txtNameMovie = findViewById(R.id.txt_name_detailmovie);
        txtFromMovie = findViewById(R.id.txt_from_detailmovie);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String coverMovie = movie.getPoster_path();
        String nameMovie = movie.getTitle();
        String fromMovie = movie.getOverview();


        Glide.with(this)
                .asBitmap()
                .load(coverMovie)
                .into(imgCoverMovie);
        txtNameMovie.setText(nameMovie);
        txtFromMovie.setText(fromMovie);
    }
}
