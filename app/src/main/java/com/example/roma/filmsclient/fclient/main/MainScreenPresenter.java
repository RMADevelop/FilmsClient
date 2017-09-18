package com.example.roma.filmsclient.fclient.main;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MainScreenPresenter implements MainScreenContract.Presenter {

    private MainScreenContract.View view;

    private Repository repository;

    private CompositeDisposable disposable = new CompositeDisposable();


    public MainScreenPresenter(MainScreenContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadMovies();
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
                        view.showMoviews(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });


    }
}
