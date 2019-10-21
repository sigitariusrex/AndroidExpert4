package com.kingleoners.cinemaint;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {

    private Context context_movie;
    private ArrayList<Movie> listMovie;

    public static final String EXTRA_MOVIE = "extra_movie";

    public ListMovieAdapter(Context context_movie, ArrayList<Movie> listMovie) {
        this.context_movie = context_movie;
        this.listMovie = listMovie;
    }

    public ArrayList<Movie> getListMovie(){
        return listMovie;
    }

    public void setData (ArrayList<Movie> listMovie){
        this.listMovie = new ArrayList<>();
        this.listMovie.addAll(listMovie);
        notifyDataSetChanged();
    }


    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
        final Movie movie = listMovie.get(i);

        /*listViewHolder.bind(listMovie.get(i));*/

        Glide.with(listViewHolder.itemView.getContext())
                .load(movie.getPoster_path())
                .apply(new RequestOptions().override(55,55))
                .into(listViewHolder.imgCoverMovie);

        listViewHolder.tvNameMovie.setText(movie.getTitle());
        listViewHolder.tvFromMovie.setText(movie.getOverview());

        Log.d(EXTRA_MOVIE,"OnBindViewHolder: Called.");

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context_movie, "Kamu memilih " + String.valueOf(movie.getTitle()), Toast.LENGTH_SHORT).show();

                Log.d(EXTRA_MOVIE,"onClick: clicked on: " + movie.getTitle());

                Intent intent = new Intent(context_movie,Detail_Movie_Activity.class);
                intent.putExtra(Detail_Movie_Activity.EXTRA_MOVIE,listMovie.get(listViewHolder.getAdapterPosition()));
                context_movie.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCoverMovie;
        TextView tvNameMovie, tvFromMovie;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCoverMovie = itemView.findViewById(R.id.img_item_cover_movie);
            tvNameMovie = itemView.findViewById(R.id.tv_item_name_movie);
            tvFromMovie = itemView.findViewById(R.id.tv_item_from_movie);
        }

        public void bind(Movie movie){
            Glide.with(itemView)
                    .load(movie.getPoster_path())
                    .into(imgCoverMovie);

            tvNameMovie.setText(movie.getTitle());
            tvFromMovie.setText(movie.getOverview());
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Movie data);
    }
}
