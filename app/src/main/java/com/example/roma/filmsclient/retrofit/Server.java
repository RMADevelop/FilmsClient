package com.example.roma.filmsclient.retrofit;

import com.example.roma.filmsclient.pojo.TokenLoginPass;
import com.example.roma.filmsclient.pojo.TokenRequest;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface Server {

    @GET("authentication/token/new")
    Single<TokenRequest> getTokenReauest(@Query("api_key") String key);

    @GET("authentication/token/validate_with_login")
    Single<TokenLoginPass> getTokenLoginPass(@Query("api_key") String key,
                                             @Query("username") String login,
                                             @Query("password") String pass,
                                             @Query("request_token") String requestToken);

//    @GET("authentication/session/new")


}
