package com.example.roma.filmsclient.retrofit;

import com.example.roma.filmsclient.pojo.Movie;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.pojo.SessionId;
import com.example.roma.filmsclient.pojo.TokenLoginPass;
import com.example.roma.filmsclient.pojo.TokenRequest;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.roma.filmsclient.utils.Const.API_v3;


public interface Server {

    @GET("authentication/token/new")
    Single<TokenRequest> getTokenReauest(@Query("api_key") String key);

    @GET("authentication/token/validate_with_login")
    Single<TokenLoginPass> getTokenLoginPass(@Query("api_key") String key,
                                             @Query("username") String login,
                                             @Query("password") String pass,
                                             @Query("request_token") String requestToken);

    @GET("authentication/session/new")
    Single<SessionId> getSessionId(@Query("api_key") String key,
                                   @Query("request_token") String requestToken);


    @GET("movie/now_playing?api_key=0a94b53a493baafc9c1434714336e957&language=en-US&page=1")
    Single<Movie> getMovie();

    @GET("movie/now_playing")
    Single<Movie> getMoviewNowShow(@Query("api_key") String key,
                                   @Query("language") String lang,
                                   @Query("page") String page);

    @GET("movie/{movie_id}/recommendations?api_key=0a94b53a493baafc9c1434714336e957&language=en-US&page=1")
    Single<Movie> getRecommended(@Path("movie_id") int id);

    @GET("movie/popular?api_key=0a94b53a493baafc9c1434714336e957&language=en-US&page=1")
    Single<Movie> getPopular();

    @GET("movie/{movie_id}?api_key=" + API_v3)
    Maybe<FilmDetail> getFilmInfo(@Path("movie_id") int idd);

}
