package com.example.roma.filmsclient.fclient.main.premiers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.utils.Api;

import java.util.ArrayList;
import java.util.List;


public class PremiersAdapterRV extends RecyclerView.Adapter<PremiersAdapterRV.ViewHolderMainScreen> {
    List<Result> movies = new ArrayList<>();
    private Context context;

    private PremiersContract.AdapterListener listener;

    public PremiersAdapterRV(List<Result> movies, Context context, PremiersContract.AdapterListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolderMainScreen onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_film_card, parent, false);

        return new ViewHolderMainScreen(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMainScreen holder, int position) {
        holder.bindTo(movies.get(position), holder);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderMainScreen extends RecyclerView.ViewHolder {
        public ImageView poster;
        public TextView tittle;
        public TextView description;
        TextView date;
        TextView rating;

        public ViewHolderMainScreen(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.recycler_film_card_poster);
            tittle = (TextView) itemView.findViewById(R.id.recycler_film_card_tittle);
            description = (TextView) itemView.findViewById(R.id.recycler_film_card_description);
            rating = (TextView) itemView.findViewById(R.id.rating);
            date = (TextView) itemView.findViewById(R.id.date_card_film);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.filmClick(movies.get(getAdapterPosition()).getId());
                }
            });
        }

        public void bindTo(Result item, ViewHolderMainScreen holder) {
            tittle.setText(item.getTitle());
            description.setText(item.getOverview().subSequence(0, 75) + "...");
            rating.setText(String.valueOf(item.getVoteAverage()));
            date.setText(item.getReleaseDate());
            Glide.with(context)
                    .load(Api.getPathPoster(item.getPosterPath()))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new BitmapImageViewTarget(holder.poster));

            Log.v("itemMovie", item.getTitle());

        }
    }

    public void setMovies(List<Result> movies) {
        Log.v("itemMovie", "setMovie " + movies.size() + " "+ movies.get(0).getId());

        this.movies = movies;
        notifyDataSetChanged();
    }
}
