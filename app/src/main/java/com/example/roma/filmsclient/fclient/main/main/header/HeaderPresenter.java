package com.example.roma.filmsclient.fclient.main.main.header;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.Result;


public class HeaderPresenter implements HeaderContract.Presenter {

    private HeaderContract.View view;

    private Repository repository;

    Result film;

    public HeaderPresenter(Repository repository, HeaderContract.View view) {
        this.view = view;
        this.repository = repository;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.setCardView(film);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void setFilms(Result film) {
        this.film = film;
    }

    @Override
    public void startActivity() {
        view.startActivity(film.getId());
    }
}
