package com.example.roma.filmsclient.fclient.filmActivity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.filmsclient.R;

/**
 * Created by RomanM on 16.10.2017.
 */

public class FilmActorsAdapter extends RecyclerView.Adapter<FilmActorsAdapter.ActorsViewHolder> {



    @Override
    public ActorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_actor,parent,false);

        return new ActorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ActorsViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ImageView imagePhoto;
        public ActorsViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.name_film_actor);
            imagePhoto = (ImageView) itemView.findViewById(R.id.image_film_actor);
        }
    }
}
