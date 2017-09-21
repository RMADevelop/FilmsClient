package com.example.roma.filmsclient.data.source.remote;


import com.example.roma.filmsclient.data.source.DataSource;
import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.SessionId;
import com.example.roma.filmsclient.retrofit.Server;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.roma.filmsclient.utils.Const.API_v3;
import static com.example.roma.filmsclient.utils.Const.URL_TMDb;

public class RemoteSource implements DataSource {

    private static RemoteSource INSTANCE;

    private RemoteSource() {

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_TMDb)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(Server.class).getMovie();
    }


}
