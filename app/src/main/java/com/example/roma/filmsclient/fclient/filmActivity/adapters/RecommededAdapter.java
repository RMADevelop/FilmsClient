package com.example.roma.filmsclient.fclient.filmActivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.FilmActivity;
import com.example.roma.filmsclient.fclient.filmActivity.FilmContract;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.utils.Api;

import java.util.List;


public class RecommededAdapter extends RecyclerView.Adapter<RecommededAdapter.RecommendedViewHolder> {

    private Context context;

    private List<Result> films;


    public RecommededAdapter(Context context, List<Result> films) {
        this.context = context;
        this.films = films;
    }

    @Override
    public RecommendedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_film_card_min, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendedViewHolder holder, int position) {
        holder.bindTo(holder, films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;

        public RecommendedViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.recycler_film_card_min_poster);
            title = (TextView) itemView.findViewById(R.id.recycler_film_card_min_tittle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        public void bindTo(RecommendedViewHolder holder, Result film) {
            holder.title.setText(film.getTitle());
            Glide.with(context)
                    .load(Api.getPathPoster(film.getPosterPath()))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new BitmapImageViewTarget(holder.poster));
        }
    }

    public void setList(List<Result> films) {
        this.films = films;
        notifyDataSetChanged();
    }

}
