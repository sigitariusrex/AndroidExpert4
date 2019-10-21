package com.kingleoners.cinemaint;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.NAME;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.OVERVIEW;
import static com.kingleoners.cinemaint.DatabaseContract.FilmColumns.POSTER_PATH;

public class FavoriteFilmAdapter extends RecyclerView.Adapter<FavoriteFilmAdapter.ListFavoriteViewHolder> {

    private Context context;
    private Cursor cursor;
    private ArrayList<FilmFavorite> listFilmFavorite = new ArrayList<>();

    FavoriteFilmAdapter favoriteFilmAdapter;

    public static final String EXTRA_FILM_FAVORITE = "extra_film_favorite";

    public FavoriteFilmAdapter(Context context, ArrayList<FilmFavorite> listFilmFavorite) {
        this.context = context;
        this.listFilmFavorite = listFilmFavorite;
    }

    public ArrayList<FilmFavorite> getListFavoriteFilm() {
        return listFilmFavorite;
    }

    public void setListFavoriteFilm(ArrayList<FilmFavorite> listFilmFavorite) {

        if (listFilmFavorite.size() > 0) {
            this.listFilmFavorite.clear();
        }
        this.listFilmFavorite.addAll(listFilmFavorite);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_favorite, viewGroup, false);
        return new ListFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFavoriteViewHolder listFavoriteViewHolder, int i) {

        final FilmFavorite filmFavorite = listFilmFavorite.get(i);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
        String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
        String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH));

        listFavoriteViewHolder.tvName.setText(name);
        listFavoriteViewHolder.tvFrom.setText(overview);
        Glide.with(listFavoriteViewHolder.itemView.getContext())
                .load(poster_path)
                .apply(new RequestOptions().override(55,55))
                .into(listFavoriteViewHolder.imgCover);
    }

    @Override
    public int getItemCount() {
        return listFilmFavorite.size();
    }

    public class ListFavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCover;
        TextView tvName, tvFrom;

        public ListFavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCover = itemView.findViewById(R.id.rv_filmfavorites);
            tvName = itemView.findViewById(R.id.tv_item_favoritename);
            tvFrom = itemView.findViewById(R.id.tv_item_favoritefrom);
        }

        public void bind(FilmFavorite filmFavorite){
            Glide.with(itemView)
                    .load(filmFavorite.getPoster_path())
                    .into(imgCover);

            tvName.setText(filmFavorite.getName());
            tvFrom.setText(filmFavorite.getOverview());
        }

        public void onClick(View view){
            int position = getAdapterPosition();
        }
    }

    public void setListFilmFavorite(ArrayList<FilmFavorite> filmFavorite) {

        if (listFilmFavorite.size() > 0) {
            this.listFilmFavorite.clear();
        }

        this.listFilmFavorite.addAll(listFilmFavorite);
        notifyDataSetChanged();
    }

    public void addItem(FilmFavorite filmFavorite){
        this.listFilmFavorite.add(filmFavorite);
        notifyItemInserted(listFilmFavorite.size() - 1);
    }

    public void removeItem(int i){
        this.listFilmFavorite.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, listFilmFavorite.size());
    }
}
