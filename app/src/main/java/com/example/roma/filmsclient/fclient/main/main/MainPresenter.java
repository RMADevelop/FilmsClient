package com.example.roma.filmsclient.fclient.main.main;


import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private Repository repository;

    private MainContract.View view;

    private List<Result> films;

    public MainPresenter(Repository repository, MainContract.View view) {
        this.repository = repository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        repository.loadMoviesNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(@NonNull Movie movie) {
                        view.showFilms(films = movie.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void setFilmForActivity(int position) {
        view.startActivity(films.get(position).getId());
    }
}
