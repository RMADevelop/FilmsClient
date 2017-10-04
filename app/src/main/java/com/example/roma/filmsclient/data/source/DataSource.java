package com.example.roma.filmsclient.data.source;

import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.pojo.SessionId;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


public interface DataSource {

    void saveSessionId(SessionId sessionId);

    Single<Movie> loadMoviesNowPlaying();

    Maybe<FilmDetail> getFilmInfo(int id);

    Single<Movie> loadRecommended(int id);

    void saveFilmInfo(FilmDetail film);

}
