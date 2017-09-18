package com.example.roma.filmsclient.data.source;

import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.SessionId;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public interface DataSource {

    void saveSessionId(SessionId sessionId);

    Single<Movie> loadMoviesNowPlaying();

}
