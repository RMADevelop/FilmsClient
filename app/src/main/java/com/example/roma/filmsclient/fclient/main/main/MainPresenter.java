package com.example.roma.filmsclient.fclient.main.main;


import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private Repository repository;

    private MainContract.View view;

    private List<Result> films;

    private List<Result> popularFilms;

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
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void setFilmForActivity(int position) {
        view.startActivity(position);
    }

    @Override
    public void getPopular() {
        repository.loadPopular()
                .subscribeOn(Schedulers.io())
                .map(new Function<Movie, List<Result>>() {
                    @Override
                    public List<Result> apply(@NonNull Movie movie) throws Exception {
                        return movie.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Result>>() {
                    @Override
                    public void onSuccess(@NonNull List<Result> results) {
                        popularFilms = results;
                        view.showPopular(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public void getTopRated() {
        repository.loadTopRated()
                .subscribeOn(Schedulers.io())
                .map(new Function<Movie, List<Result>>() {
                    @Override
                    public List<Result> apply(@NonNull Movie movie) throws Exception {
                        return movie.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Result>>() {
                    @Override
                    public void onSuccess(@NonNull List<Result> results) {
                        view.showTopRated(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void getUpcoming() {
        repository.loadUpcoming()
                .subscribeOn(Schedulers.io())
                .map(new Function<Movie, List<Result>>() {
                    @Override
                    public List<Result> apply(@NonNull Movie movie) throws Exception {
                        return movie.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Result>>() {
                    @Override
                    public void onSuccess(@NonNull List<Result> results) {
                        view.showUpcoming(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
