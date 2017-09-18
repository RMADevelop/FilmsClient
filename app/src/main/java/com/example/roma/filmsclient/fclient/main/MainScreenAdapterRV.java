package com.example.roma.filmsclient.fclient.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.pojo.Result;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 18.09.2017.
 */

public class MainScreenAdapterRV extends RecyclerView.Adapter<MainScreenAdapterRV.ViewHolderMainScreen> {
    List<Result> movies = new ArrayList<>();

    public MainScreenAdapterRV(List<Result> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolderMainScreen onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_film_card, parent, false);

        return new ViewHolderMainScreen(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMainScreen holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderMainScreen extends RecyclerView.ViewHolder {
        public ImageView poster;
        public TextView tittle;
        public TextView description;

        public ViewHolderMainScreen(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.recycler_film_card_poster);
            tittle = (TextView) itemView.findViewById(R.id.recycler_film_card_tittle);
            description = (TextView) itemView.findViewById(R.id.recycler_film_card_description);
        }
    }
}
