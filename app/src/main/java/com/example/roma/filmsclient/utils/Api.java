package com.example.roma.filmsclient.utils;


public class Api {

    public final static String URL_IMAGE = "http://image.tmdb.org/t/p/w185"; //342
    public final static String URL_BACK = "http://image.tmdb.org/t/p/w780";

    public static String getPathPoster(String posterPath) {
        return URL_IMAGE+posterPath;
    }
    public static String getBackPoster(String posterPath) {
        return URL_BACK+posterPath;
    }
}
