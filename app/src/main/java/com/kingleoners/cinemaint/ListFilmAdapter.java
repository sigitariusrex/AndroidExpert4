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

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<Film> listFilm;

    public static final String EXTRA_FILM = "extra_film";


    public ListFilmAdapter(Context context, ArrayList<Film> listFilm) {
        this.context = context;
        this.listFilm = listFilm;
    }

    public ArrayList<Film> getListFilm(){
        return listFilm;
    }

    public void setDataFilm (ArrayList<Film> listFilm){
        this.listFilm = new ArrayList<>();
        this.listFilm.addAll(listFilm);
        notifyDataSetChanged();
    }

    private ListFilmAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(ListFilmAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_film, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
        final Film film = listFilm.get(i);

        Glide.with(listViewHolder.itemView.getContext())
                .load(film.getPoster_path())
                .apply(new RequestOptions().override(55,55))
                .into(listViewHolder.imgCover);

        listViewHolder.tvName.setText(film.getName());
        listViewHolder.tvFrom.setText(film.getOverview());

        Log.d(EXTRA_FILM,"OnBindViewHolder: Called.");

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Kamu memilih " + String.valueOf(film.getName()),Toast.LENGTH_SHORT).show();

                Log.d(EXTRA_FILM,"onClick: clicked on: " + film.getName());

                Intent intent = new Intent(context,Detail_Film_Activity.class);
                intent.putExtra(Detail_Film_Activity.EXTRA_FILM,listFilm.get(listViewHolder.getAdapterPosition()));
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCover;
        TextView tvName, tvFrom;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.img_item_cover);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
        }

        public void bind(Film film){
            Glide.with(itemView)
                    .load(film.getPoster_path())
                    .into(imgCover);

            tvName.setText(film.getName());
            tvFrom.setText(film.getOverview());
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Film data);

        void onItemClicked(View v, int position);
    }
}
