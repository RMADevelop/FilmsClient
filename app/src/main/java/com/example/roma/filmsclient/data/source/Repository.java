package com.example.roma.filmsclient.data.source;

import android.util.Log;

import com.example.roma.filmsclient.data.source.local.LocalSource;
import com.example.roma.filmsclient.data.source.preference.SharedPref;
import com.example.roma.filmsclient.data.source.remote.RemoteSource;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.SessionId;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Roma on 09.09.2017.
 */

public class Repository implements DataSource {

    LocalSource local;

    RemoteSource remote;

    SharedPref sp;

    private static Repository INSTANCE;

    private Repository(LocalSource local, RemoteSource remote, SharedPref sp) {
        this.local = local;
        this.remote = remote;
        this.sp = sp;
    }

    public static Repository getInstance(LocalSource local, RemoteSource remote, SharedPref sp) {
        if (INSTANCE == null)
            INSTANCE = new Repository(local, remote, sp);
        return INSTANCE;
    }


    @Override
    public void saveSessionId(SessionId sessionId) {
        sp.saveSessionId(sessionId);
    }

    @Override
    public Single<Movie> loadMoviesNowPlaying() {
        return remote.loadMoviesNowPlaying();
    }

    @Override
    public Maybe<FilmDetail> getFilmInfo(int id) {
        return Maybe.concat(
                local.getFilmInfo(id),
                remote.getFilmInfo(id)
                        .doOnSuccess(new Consumer<FilmDetail>() {
                            @Override
                            public void accept(@NonNull FilmDetail filmDetail) throws Exception {
                                saveFilmInfo(filmDetail);
                            }
                        })
        )
                .firstElement();
    }

    @Override
    public Single<Movie> loadRecommended(int id) {
        return remote.loadRecommended(id);
    }

    @Override
    public void saveFilmInfo(FilmDetail film) {
        local.saveFilmInfo(film);
        Log.v("remoteLocal", "save film");
    }

}
