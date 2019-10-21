package com.kingleoners.cinemaint;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment {

    /*View view;
    private RecyclerView rvFilms;
    private ArrayList<Film> listFilm;
    */

    private ListFilmAdapter listFilmAdapter;
    private RecyclerView rvFilms;
    private ArrayList<Film> listFilm = new ArrayList<>();
    private ProgressBar progressBar;
    private FilmViewModel filmViewModel;


    public FilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_film, container, false);

        filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
        filmViewModel.setListMutableLiveDataFilm();
        filmViewModel.getFilms().observe(this, getFilm);

        listFilmAdapter = new ListFilmAdapter(getContext(),listFilm);
        listFilmAdapter.notifyDataSetChanged();

        rvFilms = view.findViewById(R.id.rv_films);
        rvFilms.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFilms.setAdapter(listFilmAdapter);

        progressBar = view.findViewById(R.id.progressBar);
        showLoad(true);

        return view;
    }

    private Observer<ArrayList<Film>> getFilm = new Observer<ArrayList<Film>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Film> films) {
            if (films != null){
                listFilmAdapter.setDataFilm(films);
                showLoad(false);
            }
        }
    };

    private void showLoad(boolean b) {
        if (b){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    /*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFilm = new ArrayList<>();
        listFilm.addAll(FilmsData.getListData());

    }
    */

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(LocaleHelper.onAttach(context,"in"));
    }
    */

}