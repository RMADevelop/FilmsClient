package com.example.roma.filmsclient.fclient.main.main.adapters;

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
import com.example.roma.filmsclient.fclient.main.main.MainContract;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.utils.Api;

import java.util.List;


public class AdapterPopularityRecyclerView extends RecyclerView.Adapter<AdapterPopularityRecyclerView.ViewHolderPopularity> {


    private Context context;

    private List<Result> films;

    private MainContract.RecyclerViewListener listener;

    public AdapterPopularityRecyclerView(Context context, List<Result> films, MainContract.RecyclerViewListener listener) {
        this.context = context;
        this.films = films;
        this.listener = listener;
    }

    @Override
    public ViewHolderPopularity onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_card_min, parent, false);
        return new ViewHolderPopularity(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPopularity holder, int position) {
        holder.bindTo(holder, films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolderPopularity extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;

        public ViewHolderPopularity(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.recycler_film_card_min_poster);
            title = (TextView) itemView.findViewById(R.id.recycler_film_card_min_tittle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(films.get(getAdapterPosition()).getId());
                }
            });
        }

        public void bindTo(ViewHolderPopularity holder, Result film) {
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
