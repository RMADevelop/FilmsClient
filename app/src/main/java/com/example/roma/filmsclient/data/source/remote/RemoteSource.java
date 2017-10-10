package com.example.roma.filmsclient.data.source.remote;


import android.util.Log;

import com.example.roma.filmsclient.data.source.DataSource;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.SessionId;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;
import com.example.roma.filmsclient.retrofit.Server;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.roma.filmsclient.utils.Const.URL_TMDb;

public class RemoteSource implements DataSource {

    private static RemoteSource INSTANCE;

    private Retrofit retrofit;

    private RemoteSource() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_TMDb)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RemoteSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteSource();
        }
        return INSTANCE;
    }


    @Override
    public void saveSessionId(SessionId sessionId) {

    }

    @Override
    public Single<Movie> loadMoviesNowPlaying() {
        return retrofit.create(Server.class).getMovie();
    }

    @Override
    public Single<Movie> loadPopular() {
        return retrofit.create(Server.class)
                .getPopular();
    }

    @Override
    public Single<Movie> loadTopRated() {
        return retrofit.create(Server.class)
                .getTopRated();
    }

    @Override
    public Single<Movie> loadUpcoming() {
        return retrofit.create(Server.class)
                .getUpcoming();
    }

    @Override
    public Maybe<FilmDetail> getFilmInfo(int id) {

        Log.v("remoteLocal", "fetch film");

        return retrofit.create(Server.class)
                .getFilmInfo(id);
    }

    @Override
    public Single<Movie> loadRecommended(int id) {

        return retrofit.create(Server.class)
                .getRecommended(id);
    }

    @Override
    public void saveFilmInfo(FilmDetail film) {

    }


}
