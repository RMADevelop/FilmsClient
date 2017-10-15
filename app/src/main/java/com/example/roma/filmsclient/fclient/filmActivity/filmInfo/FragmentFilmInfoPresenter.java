package com.example.roma.filmsclient.fclient.filmActivity.filmInfo;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class FragmentFilmInfoPresenter implements FragmentFilmInfoContract.Presenter {

    FragmentFilmInfoContract.View view;

    Repository repository;

    public FragmentFilmInfoPresenter(FragmentFilmInfoContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void loadFilm(int id) {
        repository.getFilmInfo(id)
                .flatMapSingle(new Function<FilmDetail, SingleSource<FilmDetail>>() {
                    @Override
                    public SingleSource<FilmDetail> apply(@NonNull FilmDetail filmDetail) throws Exception {
                        return Single.just(filmDetail);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<FilmDetail>() {
                    @Override
                    public void onSuccess(@NonNull FilmDetail filmDetail) {
                        view.showMovie(filmDetail);
                        view.setVisibleView(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void loadRecommended(int id) {
        repository.loadRecommended(id)
                .subscribeOn(Schedulers.io())
                .cache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(@NonNull Movie movie) {
                        view.showRecommended(movie.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public void filmClick(int id) {
    }
}
