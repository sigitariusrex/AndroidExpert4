package com.kingleoners.cinemaint;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ListMovieAdapter listMovieAdapter;
    private RecyclerView rvMovies;
    private ArrayList<Movie> listMovie = new ArrayList<>();
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.setListMutableLiveData();
        movieViewModel.getMovies().observe(this, getMovie);

        listMovieAdapter = new ListMovieAdapter(getContext(),listMovie);
        listMovieAdapter.notifyDataSetChanged();

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(listMovieAdapter);

        progressBar = view.findViewById(R.id.progressBar);
        showLoad(true);

        return view;
    }


    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);

        listMovieAdapter = new ListMovieAdapter(getContext(),listMovie);
        listMovieAdapter.notifyDataSetChanged();

        rvMovies = getActivity().findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(listMovieAdapter);

        progressBar = getActivity().findViewById(R.id.progressBar);

        movieViewModel.setListMutableLiveData();

    }
    */

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movies) {
            if (movies != null){
                listMovieAdapter.setData(movies);
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

}
