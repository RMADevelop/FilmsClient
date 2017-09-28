package com.example.roma.filmsclient.fclient.main.premiers;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Roma on 26.09.2017.
 */

public class PremiersPresenter implements PremiersContract.Presenter {

    Repository repository;

    PremiersContract.View view;


    public PremiersPresenter(Repository repository, PremiersContract.View view) {
        this.repository = repository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadMovies() {
        repository.loadMoviesNowPlaying()
                .map(new Function<Movie, List<Result>>() {
                    @Override
                    public List<Result> apply(@NonNull Movie movie) throws Exception {
                        return movie.getResults();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Result>>() {
                    @Override
                    public void onSuccess(@NonNull List<Result> results) {
                        view.showMovies(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void showFilmActivity(int id) {

    }
}
